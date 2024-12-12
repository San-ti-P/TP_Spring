package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.ItemPedidoDTO;
import isi.deso.tpspring.dto.PedidoDTO;
import isi.deso.tpspring.model.*;
import isi.deso.tpspring.service.ClienteService;
import isi.deso.tpspring.service.ItemMenuService;
import isi.deso.tpspring.service.ItemPedidoService;
import isi.deso.tpspring.service.PedidoService;
import isi.deso.tpspring.service.VendedorService;
import java.util.ArrayList;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if (vendedorId != null) {
            List<ItemMenu> items = vendedorService.getItemsMenuByVendedor(vendedorId);
            for (ItemMenu item : items) {
                ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
                itemPedidoDTO.setItem(item.getId()); // Inicializar con el objeto ItemMenu
                itemPedidoDTO.setNombre(item.getNombre());
                itemPedidoDTO.setPrecio(item.getPrecio());
                itemPedidoDTO.setCantidad(0); // Valor predeterminado para cantidad
                itemPedidoDTOs.add(itemPedidoDTO);
            
            }
            
        }
        else System.out.println("VENDEDOR ID ES NULL");
        pedidoDTO.setItems(itemPedidoDTOs);
        System.out.println("LISTA DE ITEMS ENVIADA: "+pedidoDTO.getItems());
        modelo.addAttribute("pedidoDTO", pedidoDTO);
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("vendedores", vendedores);
        //modelo.addAttribute("items", items);
        modelo.addAttribute("selectedVendedorId", vendedorId);
        return "nuevo_pedido_form";
    }

    @PostMapping("/pedidos")
    public String savePedido(@ModelAttribute PedidoDTO pedidoDTO) throws VendedoresDistintosException {
        Pedido pedido = new Pedido();
        System.out.println("HOLAAAAAAAAAAAA");
        System.out.println("HOLAAAAAAAAAAAA");
        System.out.println("HOLAAAAAAAAAAAA");
        System.out.println("HOLAAAAAAAAAAAA");
        pedido.setCliente(clienteService.getByIdCliente(pedidoDTO.getCliente().getId()));
        pedido.setVendedor(vendedorService.getByIdVendedor(pedidoDTO.getVendedor().getId()));
        pedido = pedidoService.savePedido(pedido);
        List<ItemPedido> items = new ArrayList<>();
        for (ItemPedidoDTO i : pedidoDTO.getItems()){
            System.out.println("Id item: "+i);
            ItemPedido nuevo = new ItemPedido();
            nuevo.setItem(itemMenuService.getItemMenuById(i.getItem()));
            nuevo.setCantidad(i.getCantidad());
            nuevo.setPedido(pedido);
            itemPedidoService.saveItemPedido(nuevo);
            items.add(nuevo);
        }
        pedido.setItems(items);
        pedido.setPrecio(items.stream()
                .mapToDouble(item -> item.getItem().getPrecio() * item.getCantidad())
                .sum());
        
        
        System.out.println("Vendedor ID: " + pedidoDTO.getVendedor().getId());
        System.out.println("Cliente ID: " + pedidoDTO.getCliente().getId());
        pedidoDTO.getItems().forEach(itemPedido -> {
            System.out.println("Item ID: " + itemPedido.getItem());
            System.out.println("Cantidad: " + itemPedido.getCantidad());
        });
        return "redirect:/pedidos";
    }

}
