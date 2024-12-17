package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.ClienteDTO;
import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.Coordenada;
import isi.deso.tpspring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteRestController {

    @Autowired
    private ClienteService servicio;


    @GetMapping("/clientes/api")
    @ResponseBody
    public ResponseEntity<List<Cliente>> getAllClientesApi() {
        return ResponseEntity.ok(servicio.getAllClientes());
    }

    @GetMapping("/clientes/api/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteByIdApi(@PathVariable Integer id) {
        Cliente cliente = servicio.getByIdCliente(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/clientes/api")
    @ResponseBody
    public ResponseEntity<Cliente> saveClienteApi(@RequestBody ClienteDTO clienteDTO) {
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
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/clientes/api/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> updateClienteApi(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO) {
        Cliente c_existente = servicio.getByIdCliente(id);
        if (c_existente != null) {
            c_existente.setNombre(clienteDTO.getNombre());
            c_existente.setDireccion(clienteDTO.getDireccion());
            c_existente.setCuit(clienteDTO.getCuit());
            c_existente.setEmail(clienteDTO.getEmail());

            Coordenada coordenadas = c_existente.getCoordenadas();
            coordenadas.setLat(clienteDTO.getLat());
            coordenadas.setLng(clienteDTO.getLng());
            c_existente.setCoordenadas(coordenadas);

            servicio.updateCliente(c_existente);
            return ResponseEntity.ok(c_existente);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/clientes/api/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteClienteApi(@PathVariable Integer id) {
        servicio.deleteCliente(id);
        return ResponseEntity.ok("Cliente eliminado correctamente");
    }
}
