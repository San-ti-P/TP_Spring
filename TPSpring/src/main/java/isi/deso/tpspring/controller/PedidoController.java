package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.PedidoDTO;
import isi.deso.tpspring.model.Coordenada;
import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.Vendedor;
import isi.deso.tpspring.model.Pedido;
import isi.deso.tpspring.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PedidoController {
    @Autowired
    private PedidoService servicio;

    @GetMapping("/pedidos")
    public String listPedidos(Model modelo){
        modelo.addAttribute("pedidos", servicio.getAllPedidos());
        return "pedidos";
    }

    @GetMapping("/pedidos/nuevo")
    public String newClienteForm(Model modelo){
        PedidoDTO pedidoDTO = new PedidoDTO();
        modelo.addAttribute("pedidoDTO", pedidoDTO);
        return "nuevo_vendedor_form";
    }
//
//    @PostMapping("/vendedores")
//    public String saveVendedor(@ModelAttribute("vendedorDTO") VendedorDTO vendedorDTO){
//
//        Vendedor vendedor = new Vendedor();
//        vendedor.setNombre(vendedorDTO.getNombre());
//        vendedor.setDireccion(vendedorDTO.getDireccion());
//
//        Coordenada coordenadas = new Coordenada();
//        coordenadas.setLat(vendedorDTO.getLat());
//        coordenadas.setLng(vendedorDTO.getLng());
//        vendedor.setCoordenadas(coordenadas);
//
//        servicio.saveVendedor(vendedor);
//        return "redirect:/vendedores";
//    }
//
//    @GetMapping("/vendedores/editar/{id}")
//    public String formularioEditar(@PathVariable Integer id, Model modelo) {
//        modelo.addAttribute("vendedor", servicio.getByIdVendedor(id));
//        return "editar_vendedor";
//    }
//
//    @PostMapping("/vendedores/{id}")
//    public String updateVendedor(@PathVariable Integer id, @ModelAttribute("vendedor") Vendedor vendedor, Model modelo) {
//        Vendedor v_existente = servicio.getByIdVendedor(id);
//        v_existente.setId(id);
//        v_existente.setNombre(vendedor.getNombre());
//        v_existente.setDireccion(vendedor.getDireccion());
//
//        Coordenada coordenadas = v_existente.getCoordenadas();
//        coordenadas.setLat(vendedor.getCoordenadas().getLat());
//        coordenadas.setLng(vendedor.getCoordenadas().getLng());
//
//        v_existente.setCoordenadas(coordenadas);
//
//        servicio.updateVendedor(v_existente);
//        return "redirect:/vendedores";
//    }
//
//    @GetMapping("/vendedores/{id}")
//    public String deleteVendedor(@PathVariable Integer id) {
//        servicio.deleteVendedor(id);
//        return "redirect:/vendedores";
//    }

}