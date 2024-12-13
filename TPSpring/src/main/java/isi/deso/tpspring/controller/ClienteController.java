package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.ClienteDTO;
import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.Coordenada;
import isi.deso.tpspring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class ClienteController {
    @Autowired
    private ClienteService servicio;
    
    @GetMapping("/clientes")
    public String listClientes(Model modelo){
        modelo.addAttribute("clientes", servicio.getAllClientes());
        return "clientes";
    }

    @GetMapping("/clientes/nuevo")
    public String newClienteForm(Model modelo){
        ClienteDTO clienteDTO = new ClienteDTO();
        modelo.addAttribute("clienteDTO", clienteDTO);
        return "nuevo_cliente_form";
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

        cliente.setCoordenadas(coordenadas);
        servicio.saveCliente(cliente);

        return "redirect:/clientes";
    }

//    @GetMapping("/clientes/editar/{id}")
//    public String formularioEditar(@PathVariable Integer id, Model modelo) {
//        modelo.addAttribute("cliente", servicio.getByIdCliente(id));
//        return "editar_cliente";
//    }

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

    @GetMapping("/clientes/{id}")
    public String deleteCliente(@PathVariable Integer id) {
        servicio.deleteCliente(id);
        return "redirect:/clientes";
    }

//    @PutMapping("/clientes/{id}")
//    public String updateCliente(@PathVariable Integer id, @ModelAttribute("cliente") Cliente cliente, Model modelo){
//        Cliente c_existente = servicio.getByIdCliente(id);
//        c_existente.setId(id);
//        c_existente.setNombre(cliente.getNombre());
//        c_existente.setDireccion(cliente.getDireccion());
//
//        Coordenada coordenadas = c_existente.getCoordenadas();
//        coordenadas.setLat(cliente.getCoordenadas().getLat());
//        coordenadas.setLng(cliente.getCoordenadas().getLng());
//
//        c_existente.setCoordenadas(coordenadas);
//
//        servicio.updateCliente(c_existente);
//        return "redirect:/clientes";
//    }
//
//    @DeleteMapping("/clientes/{id}")
//    public String deleteCliente(@PathVariable Integer id){
//        servicio.deleteCliente(id);
//        return "redirect:/clientes";
//    }
//
//    @GetMapping("/clientes/{id}")
//    public ResponseEntity<Cliente> getByIdCliente(@PathVariable Integer id) {
//        Cliente c = servicio.getByIdCliente(id);
//        if (c == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(c);
//    }
}
