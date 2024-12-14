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

        List<ItemPedidoDTO> itemsTodos = getItemsVendedor(pedido.getVendedor().getId());
        List<ItemPedidoDTO> itemsBuenos =  pedido.getItems().stream()
                .map(item -> new ItemPedidoDTO(
                        item.getId(),
                        item.getItem().getId(),
                        item.getItem().getNombre(),
                        item.getItem().getPrecio(),
                        item.getCantidad()
                ))
                .collect(Collectors.toList());

        List<Integer> ids_item_menu = new ArrayList<>();
        for (ItemPedidoDTO item : itemsBuenos) ids_item_menu.add(item.getItem());

        for (ItemPedidoDTO item : itemsTodos) {
            if (!(ids_item_menu.contains(item.getItem()))) {
                itemsBuenos.add(item);
            }
        }

        pedidoDTO.setItems(itemsBuenos);

        double subtotal = pedido.getItems().stream()
                .mapToDouble(item -> item.getCantidad() * item.getItem().getPrecio())
                .sum();
        pedidoDTO.setSubtotal(subtotal);

        if (pedido.getPago() != null) {
            EstrategiaDePago estrategia = pedido.getPago().getEstrategia();
            if (estrategia.esMercadoPago()) {
                EstrategiaMercadoPago mercadoPago = (EstrategiaMercadoPago) estrategia;
                pedidoDTO.setMedioDePago("MERCADOPAGO");
                pedidoDTO.setAlias(mercadoPago.getAlias());
                pedidoDTO.setTotal(subtotal * 1.04);
            } else if (estrategia.esTransferencia()) {
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

        Vendedor vendedorSeleccionado = vendedorService.getByIdVendedor(pedidoDTO.getVendedor().getId());
        pedidoExistente.setVendedor(vendedorSeleccionado);

        pedidoExistente.setCliente(clienteService.getByIdCliente(pedidoDTO.getCliente().getId()));
        pedidoExistente.setEstado(pedidoDTO.getEstado());
        pedidoExistente.setPrecio(pedidoDTO.getSubtotal());

        pedidoExistente.getItems().clear();

        for (ItemPedidoDTO itemDTO : pedidoDTO.getItems()) {
            if (itemDTO.getCantidad() > 0) {
                ItemPedido item;
                if (itemDTO.getId() != -1) {
                    item = itemPedidoService.getByIdItemPedido(itemDTO.getId());
                } else {
                    item = new ItemPedido(null, itemDTO.getCantidad(), itemMenuService.getItemMenuById(itemDTO.getItem()), pedidoExistente);
                    itemPedidoService.saveItemPedido(item);
                }

                ItemMenu itemMenu = itemMenuService.getItemMenuById(itemDTO.getItem());

                if (!(itemMenu.getVendedor().getId() == (vendedorSeleccionado.getId()))) {
                    throw new VendedoresDistintosException("El ítem no pertenece al vendedor seleccionado");
                }

                item.setItem(itemMenu);
                item.setCantidad(itemDTO.getCantidad());
                item.setPedido(pedidoExistente);
                pedidoExistente.getItems().add(item);
            }
        }

        EstrategiaDePago estrategia;
        Pago existingPago = pedidoExistente.getPago();

        if (pedidoDTO.getMedioDePago().equalsIgnoreCase("MERCADOPAGO")) {
            if (existingPago != null && existingPago.getEstrategia().esMercadoPago()) {
                EstrategiaMercadoPago mercadoPago = (EstrategiaMercadoPago) existingPago.getEstrategia();
                mercadoPago.setAlias(pedidoDTO.getAlias());
                estrategia = estrategiaDePagoService.saveEstrategiaDePago(mercadoPago);
            } else {
                EstrategiaMercadoPago mercadoPago = new EstrategiaMercadoPago();
                mercadoPago.setAlias(pedidoDTO.getAlias());
                estrategia = estrategiaDePagoService.saveEstrategiaDePago(mercadoPago);
            }
        } else {
            if (existingPago != null && existingPago.getEstrategia().esTransferencia()) {
                EstrategiaTransferencia transferencia = (EstrategiaTransferencia) existingPago.getEstrategia();
                transferencia.setCbu(pedidoDTO.getCbu());
                transferencia.setCuit(pedidoDTO.getCuit());
                estrategia = estrategiaDePagoService.saveEstrategiaDePago(transferencia);
            } else {
                EstrategiaTransferencia transferencia = new EstrategiaTransferencia();
                transferencia.setCbu(pedidoDTO.getCbu());
                transferencia.setCuit(pedidoDTO.getCuit());
                estrategia = estrategiaDePagoService.saveEstrategiaDePago(transferencia);
            }
        }

        Pago pago;
        if (existingPago != null) {
            existingPago.setEstrategia(estrategia);
            pago = pagoService.updatePago(existingPago);
        } else {
            pago = new Pago(new Date(), pedidoExistente, estrategia);
            pago = pagoService.savePago(pago);
        }

        pedidoExistente.setPago(pago);
        pedidoService.updatePedido(pedidoExistente);

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
                .map(item -> {
                    ItemPedidoDTO ipdt = new ItemPedidoDTO();
                    ipdt.setId(-1);
                    ipdt.setItem(item.getId());
                    ipdt.setNombre(item.getNombre());
                    ipdt.setPrecio(item.getPrecio());
                    ipdt.setCantidad(0);
                    return ipdt;
                }).collect(Collectors.toList());
    }

    @GetMapping("/pedido/{id}/items")
    public String viewPedidoItems(@PathVariable Integer id, Model model) {
        Pedido pedido = pedidoService.getByIdPedido(id);
        if (pedido == null) {
            return "redirect:/pedidos";
        }
        List<ItemPedidoDTO> items = pedido.getItems().stream()
                .map(item -> new ItemPedidoDTO(item.getId(), item.getItem().getId(), item.getItem().getNombre(), item.getItem().getPrecio(), item.getCantidad()))
                .collect(Collectors.toList());
        model.addAttribute("items", items);
        return "pedido_items";
    }

}