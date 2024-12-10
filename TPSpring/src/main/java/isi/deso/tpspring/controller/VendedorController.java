package isi.deso.tpspring.controller;

import isi.deso.tpspring.model.Vendedor;
import isi.deso.tpspring.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class VendedorController {

    @Autowired
    private VendedorService servicio;

    @GetMapping("/vendedores")
    public String listVendedores(Model modelo){
        modelo.addAttribute("vendedores", servicio.getAllVendedores());
        return "vendedores";
    }

    @PostMapping("/vendedores")
    public String saveVendedor(@ModelAttribute("vendedor") Vendedor vendedor){
        servicio.saveVendedor(vendedor);
        return "redirect:/vendedores";
    }

    @PutMapping("/vendedores")
    public String updateVendedor(@ModelAttribute("vendedor") Vendedor vendedor){
        servicio.updateVendedor(vendedor);
        return "redirect:/vendedores";
    }

    @DeleteMapping("/vendedores/{id}")
    public String deleteVendedor(@PathVariable Integer id){
        servicio.deleteVendedor(id);
        return "redirect:/vendedores";
    }

    @GetMapping("/vendedores/{id}")
    public ResponseEntity<Vendedor> getByIdVendedor(@PathVariable int id) {
        Vendedor v = servicio.getByIdVendedor(id);
        if (v == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(v);
    }
}