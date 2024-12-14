package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.ItemPedidoDTO;
import isi.deso.tpspring.dto.PedidoDTO;
import isi.deso.tpspring.model.*;
import isi.deso.tpspring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VendedorService vendedorService;

    @Autowired
    private ItemMenuService itemMenuService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private PagoService pagoService;

    @Autowired
    private EstrategiaDePagoService estrategiaDePagoService;


    @GetMapping("/pedidos")
    public String listPedidos(Model modelo) {
        modelo.addAttribute("pedidos", pedidoService.getAllPedidos());
        return "pedidos";
    }

    @GetMapping("/pedidos/nuevo")
    public String newPedidoForm(@RequestParam(value = "vendedorId", required = false) Integer vendedorId, Model modelo) {

        PedidoDTO pedidoDTO = new PedidoDTO();
        List<Cliente> clientes = clienteService.getAllClientes();
        List<Vendedor> vendedores = vendedorService.getAllVendedores();
        List<ItemPedidoDTO> itemPedidoDTOs = new ArrayList<>();
        List<String> mediosDePago = new ArrayList<>();
        mediosDePago.add("MERCADOPAGO");
        mediosDePago.add("TRANSFERENCIA");

        if (vendedorId != null) {
            List<ItemMenu> items = vendedorService.getItemsMenuByVendedor(vendedorId);
            for (ItemMenu item : items) {
                ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
                itemPedidoDTO.setItem(item.getId());
                itemPedidoDTO.setNombre(item.getNombre());
                itemPedidoDTO.setPrecio(item.getPrecio());
                itemPedidoDTO.setCantidad(0);
                itemPedidoDTOs.add(itemPedidoDTO);
            }
        } else System.out.println("VENDEDOR ID ES NULL");

        pedidoDTO.setItems(itemPedidoDTOs);
        System.out.println("LISTA DE ITEMS ENVIADA: " + pedidoDTO.getItems());

        modelo.addAttribute("pedidoDTO", pedidoDTO);
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("vendedores", vendedores);
        modelo.addAttribute("selectedVendedorId", vendedorId);
        modelo.addAttribute("estados", EstadoPedido.values());
        modelo.addAttribute("mediosdepago", mediosDePago);

        return "nuevo_pedido_form";
    }

    @PostMapping("/pedidos")
    public String savePedido(@ModelAttribute PedidoDTO pedidoDTO) throws VendedoresDistintosException {
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteService.getByIdCliente(pedidoDTO.getCliente().getId()));
        pedido.setVendedor(vendedorService.getByIdVendedor(pedidoDTO.getVendedor().getId()));
        pedido.setEstado(pedidoDTO.getEstado());

        List<ItemPedido> items = new ArrayList<>();
        double subtotal = 0.0;
        for (ItemPedidoDTO i : pedidoDTO.getItems()) subtotal += i.getCantidad() * i.getPrecio();

        pedido.setPrecio(subtotal);
        EstrategiaDePago estrategiaDePago;
        if (pedidoDTO.getMedioDePago().equalsIgnoreCase("mercadopago")) {
            EstrategiaMercadoPago nueva = new EstrategiaMercadoPago();
            nueva.setAlias(pedidoDTO.getAlias());
            estrategiaDePago = nueva;
        } else {
            EstrategiaTransferencia nueva = new EstrategiaTransferencia();
            nueva.setCbu(pedidoDTO.getCbu());
            nueva.setCuit(pedidoDTO.getCuit());
            estrategiaDePago = nueva;
        }
        estrategiaDePago = estrategiaDePagoService.saveEstrategiaDePago(estrategiaDePago);
        Pago p = new Pago(new Date(), pedido, estrategiaDePago);
        pagoService.savePago(p);
        pedido.setPago(p);
        pedido = pedidoService.savePedido(pedido);

        for (ItemPedidoDTO i : pedidoDTO.getItems()) {
            if (i.getCantidad() != 0) {
                ItemPedido nuevo = new ItemPedido();
                nuevo.setItem(itemMenuService.getItemMenuById(i.getItem()));
                nuevo.setCantidad(i.getCantidad());
                nuevo.setPedido(pedido);
                itemPedidoService.saveItemPedido(nuevo);
                items.add(nuevo);
            }
        }

        pedido.setItems(items);
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model modelo) {
        Pedido pedido = pedidoService.getByIdPedido(id);
        PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setCliente(pedido.getCliente());
        pedidoDTO.setVendedor(pedido.getVendedor());
        pedidoDTO.setEstado(pedido.getEstado());
        pedidoDTO.setItems(
                pedido.getItems().stream()
                        .map(item -> new ItemPedidoDTO(
                                item.getId(),
                                item.getItem().getId(),
                                item.getItem().getNombre(),
                                item.getItem().getPrecio(),
                                item.getCantidad()
                        ))
                        .collect(Collectors.toList())
        );

        double subtotal = pedido.getItems().stream()
                .mapToDouble(item -> item.getCantidad() * item.getItem().getPrecio())
                .sum();
        pedidoDTO.setSubtotal(subtotal);

        if (pedido.getPago() != null) {
            EstrategiaDePago estrategia = pedido.getPago().getEstrategia();
            if (estrategia instanceof EstrategiaMercadoPago) {
                EstrategiaMercadoPago mercadoPago = (EstrategiaMercadoPago) estrategia;
                pedidoDTO.setMedioDePago("MERCADOPAGO");
                pedidoDTO.setAlias(mercadoPago.getAlias());
                pedidoDTO.setTotal(subtotal * 1.04);
            } else if (estrategia instanceof EstrategiaTransferencia) {
                EstrategiaTransferencia transferencia = (EstrategiaTransferencia) estrategia;
                pedidoDTO.setMedioDePago("TRANSFERENCIA");
                pedidoDTO.setCbu(transferencia.getCbu());
                pedidoDTO.setCuit(transferencia.getCuit());
                pedidoDTO.setTotal(subtotal * 1.02);
            }
        }

        List<Cliente> clientes = clienteService.getAllClientes();
        List<Vendedor> vendedores = vendedorService.getAllVendedores();
        List<String> mediosDePago = List.of("MERCADOPAGO", "TRANSFERENCIA");

        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("vendedores", vendedores);
        modelo.addAttribute("estados", EstadoPedido.values());
        modelo.addAttribute("mediosdepago", mediosDePago);
        modelo.addAttribute("pedidoDTO", pedidoDTO);

        return "editar_pedido";
    }

    @PostMapping("/pedidos/{id}")
    public String updatePedido(@PathVariable Integer id, @ModelAttribute PedidoDTO pedidoDTO) throws VendedoresDistintosException {
        Pedido pedidoExistente = pedidoService.getByIdPedido(id);

        pedidoExistente.setCliente(clienteService.getByIdCliente(pedidoDTO.getCliente().getId()));
        pedidoExistente.setVendedor(vendedorService.getByIdVendedor(pedidoDTO.getVendedor().getId()));
        pedidoExistente.setEstado(pedidoDTO.getEstado());

        List<ItemPedido> items = new ArrayList<>();
        double subtotal = 0.0;

        for (ItemPedidoDTO itemDTO : pedidoDTO.getItems()) {
            if (itemDTO.getCantidad() > 0) {
                ItemPedido item = new ItemPedido();
                item.setItem(itemMenuService.getItemMenuById(itemDTO.getItem()));
                item.setCantidad(itemDTO.getCantidad());
                item.setPedido(pedidoExistente);
                subtotal += itemDTO.getCantidad() * item.getItem().getPrecio();
                items.add(item);
            }
        }

        pedidoExistente.setItems(items);
        pedidoExistente.setPrecio(subtotal);

        EstrategiaDePago estrategia;
        if (pedidoDTO.getMedioDePago().equalsIgnoreCase("MERCADOPAGO")) {
            EstrategiaMercadoPago mercadoPago = new EstrategiaMercadoPago();
            mercadoPago.setAlias(pedidoDTO.getAlias());
            estrategia = mercadoPago;
        } else {
            EstrategiaTransferencia transferencia = new EstrategiaTransferencia();
            transferencia.setCbu(pedidoDTO.getCbu());
            transferencia.setCuit(pedidoDTO.getCuit());
            estrategia = transferencia;
        }

        estrategia = estrategiaDePagoService.saveEstrategiaDePago(estrategia);
        Pago pago = new Pago(new Date(), pedidoExistente, estrategia);
        pagoService.savePago(pago);
        pedidoExistente.setPago(pago);

        pedidoService.savePedido(pedidoExistente);

        return "redirect:/pedidos";
    }


    @GetMapping("/pedidos/{id}")
    public String deletePedido(@PathVariable Integer id) {
        pedidoService.deletePedido(id);
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/items/{vendedorId}")
    @ResponseBody
    public List<ItemPedidoDTO> getItemsVendedor(@PathVariable Integer vendedorId) {
        List<ItemMenu> items = vendedorService.getItemsMenuByVendedor(vendedorId);
        return items.stream()
                .map(item -> new ItemPedidoDTO(null, item.getId(), item.getNombre(), item.getPrecio(), 0))
                .collect(Collectors.toList());
    }
}