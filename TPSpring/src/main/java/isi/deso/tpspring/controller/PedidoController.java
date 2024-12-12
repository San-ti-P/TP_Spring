package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.PedidoDTO;
import isi.deso.tpspring.model.*;
import isi.deso.tpspring.service.ClienteService;
import isi.deso.tpspring.service.ItemPedidoService;
import isi.deso.tpspring.service.PedidoService;
import isi.deso.tpspring.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private ItemPedidoService itemPedidoService;

    @GetMapping("/pedidos")
    public String listPedidos(Model modelo) {
        modelo.addAttribute("pedidos", pedidoService.getAllPedidos());
        return "pedidos";
    }

//    @GetMapping("/pedidos/nuevo")
//    public String newPedidoForm(Model modelo) {
//        PedidoDTO pedidoDTO = new PedidoDTO();
//        List<Cliente> clientes = clienteService.getAllClientes();
//        List<Vendedor> vendedores = vendedorService.getAllVendedores();
//        List<ItemPedido> items = itemPedidoService.getAllItems();
//
//        modelo.addAttribute("pedidoDTO", pedidoDTO);
//        modelo.addAttribute("clientes", clientes);
//        modelo.addAttribute("vendedores", vendedores);
//        modelo.addAttribute("items", items);
//        return "nuevo_pedido_form";
//    }

    @GetMapping("/pedidos/nuevo")
    public String newPedidoForm(@RequestParam(value = "vendedorId", required = false) Integer vendedorId, Model modelo) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        List<Cliente> clientes = clienteService.getAllClientes();
        List<Vendedor> vendedores = vendedorService.getAllVendedores();
        List<ItemMenu> items = (vendedorId != null)
                ? vendedorService.getItemsMenuByVendedor(vendedorId)
                : List.of();

        modelo.addAttribute("pedidoDTO", pedidoDTO);
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("vendedores", vendedores);
        modelo.addAttribute("items", items);
        modelo.addAttribute("selectedVendedorId", vendedorId);
        return "nuevo_pedido_form";
    }


    @PostMapping("/pedidos")
    public String savePedido(@ModelAttribute PedidoDTO pedidoDTO) throws VendedoresDistintosException {
        Pedido pedido = new Pedido();
        pedido.setCliente(pedidoDTO.getCliente());
        pedido.setVendedor(pedidoDTO.getVendedor());
        pedido.setItems(pedidoDTO.getItems());
        pedido.setPrecio(pedidoDTO.getItems().stream()
                .mapToDouble(item -> item.getItem().getPrecio() * item.getCantidad())
                .sum());
        pedidoService.savePedido(pedido);
        return "redirect:/pedidos";
    }

}
