/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.ClienteDTO;
import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.Coordenada;
import isi.deso.tpspring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author santi
 */
@Controller
public class ClienteController {
    @Autowired
    private ClienteService servicio;
    
    @GetMapping("/clientes")
    public String listClientes(Model modelo){
        modelo.addAttribute("clientes", servicio.listAllClientes());
        return "clientes";
    }
    
    @PostMapping("/clientes")
    public String saveCliente(@ModelAttribute("cliente") ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setCuit(clienteDTO.getCuit());
        cliente.setEmail(clienteDTO.getEmail());

        Coordenada coordenadas = new Coordenada();
        coordenadas.setLat(clienteDTO.getLat());
        coordenadas.setLng(clienteDTO.getLng());

        // Establece la relación entre cliente y coordenadas si corresponde
        cliente.setCoordenadas(coordenadas);

        // Guarda las entidades en la base de datos
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
    
    //Obtención de formularios
    @GetMapping("/clientes/nuevo")
    public String newClienteForm(Model modelo){
        ClienteDTO clienteDTO = new ClienteDTO();
        modelo.addAttribute("clienteDTO", clienteDTO);
        //modelo.addAttribute("coordenadas", coordenadas);
        return "nuevo_cliente_form";
    }
}
