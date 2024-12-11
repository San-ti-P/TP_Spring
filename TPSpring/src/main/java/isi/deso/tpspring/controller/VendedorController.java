package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.VendedorDTO;
import isi.deso.tpspring.model.Coordenada;
import isi.deso.tpspring.model.Vendedor;
import isi.deso.tpspring.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VendedorController {

    @Autowired
    private VendedorService servicio;

    @GetMapping("/vendedores")
    public String listVendedores(Model modelo){
        modelo.addAttribute("vendedores", servicio.getAllVendedores());
        return "vendedores";
    }

    @GetMapping("/vendedores/nuevo")
    public String newClienteForm(Model modelo){
        VendedorDTO vendedorDTO = new VendedorDTO();
        modelo.addAttribute("vendedorDTO", vendedorDTO);
        return "nuevo_vendedor_form";
    }

    @PostMapping("/vendedores")
    public String saveVendedor(@ModelAttribute("vendedorDTO") VendedorDTO vendedorDTO){

        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(vendedorDTO.getNombre());
        vendedor.setDireccion(vendedorDTO.getDireccion());

        Coordenada coordenadas = new Coordenada();
        coordenadas.setLat(vendedorDTO.getLat());
        coordenadas.setLng(vendedorDTO.getLng());
        vendedor.setCoordenadas(coordenadas);

        servicio.saveVendedor(vendedor);
        return "redirect:/vendedores";
    }

    @GetMapping("/vendedores/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model modelo) {
        modelo.addAttribute("vendedor", servicio.getByIdVendedor(id));
        return "editar_vendedor";
    }

    @PostMapping("/vendedores/{id}")
    public String updateVendedor(@PathVariable Integer id, @ModelAttribute("vendedor") Vendedor vendedor, Model modelo) {
        Vendedor v_existente = servicio.getByIdVendedor(id);
        v_existente.setId(id);
        v_existente.setNombre(vendedor.getNombre());
        v_existente.setDireccion(vendedor.getDireccion());

        Coordenada coordenadas = v_existente.getCoordenadas();
        coordenadas.setLat(vendedor.getCoordenadas().getLat());
        coordenadas.setLng(vendedor.getCoordenadas().getLng());

        v_existente.setCoordenadas(coordenadas);

        servicio.updateVendedor(v_existente);
        return "redirect:/vendedores";
    }

    @GetMapping("/vendedores/{id}")
    public String deleteVendedor(@PathVariable Integer id) {
        servicio.deleteVendedor(id);
        return "redirect:/vendedores";
    }

}