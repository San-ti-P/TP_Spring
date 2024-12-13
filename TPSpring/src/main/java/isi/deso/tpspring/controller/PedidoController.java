package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.ItemPedidoDTO;
import isi.deso.tpspring.dto.PedidoDTO;
import isi.deso.tpspring.model.*;
import isi.deso.tpspring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
