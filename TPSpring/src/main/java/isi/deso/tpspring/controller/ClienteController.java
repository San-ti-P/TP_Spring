package isi.deso.tpspring.controller;

import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    @Autowired
    private ClienteService servicio;
    
    @GetMapping("/clientes")
    public String listClientes(Model modelo){
        modelo.addAttribute("clientes", servicio.getAllClientes());
        return "clientes";
    }
    
    @PostMapping("/clientes")
    public String saveCliente(@ModelAttribute("cliente") Cliente cliente){
        servicio.saveCliente(cliente);
        return "redirect:/clientes";
    }
    
    @PutMapping("/clientes")
    public String updateCliente(@ModelAttribute("cliente") Cliente cliente){
        servicio.updateCliente(cliente);
        return "redirect:/clientes";
    }
    
    @DeleteMapping("/clientes/{id}")
    public String deleteCliente(@PathVariable Integer id){
        servicio.deleteCliente(id);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> getByIdCliente(@PathVariable int id) {
        Cliente c = servicio.getByIdCliente(id);
        if (c == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(c);
    }
}
