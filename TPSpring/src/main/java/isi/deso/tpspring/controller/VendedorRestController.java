package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.VendedorDTO;
import isi.deso.tpspring.model.Coordenada;
import isi.deso.tpspring.model.Vendedor;
import isi.deso.tpspring.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VendedorRestController {

    @Autowired
    private VendedorService servicio;

    @GetMapping("/vendedores/api")
    @ResponseBody
    public ResponseEntity<List<Vendedor>> getAllVendedoresApi() {
        return ResponseEntity.ok(servicio.getAllVendedores());
    }

    @GetMapping("/vendedores/api/{id}")
    @ResponseBody
    public ResponseEntity<Vendedor> getVendedorByIdApi(@PathVariable Integer id) {
        Vendedor vendedor = servicio.getByIdVendedor(id);
        if (vendedor != null) {
            return ResponseEntity.ok(vendedor);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/vendedores/api")
    @ResponseBody
    public ResponseEntity<Vendedor> saveVendedorApi(@RequestBody VendedorDTO vendedorDTO) {
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(vendedorDTO.getNombre());
        vendedor.setDireccion(vendedorDTO.getDireccion());

        Coordenada coordenadas = new Coordenada();
        coordenadas.setLat(vendedorDTO.getLat());
        coordenadas.setLng(vendedorDTO.getLng());
        vendedor.setCoordenadas(coordenadas);

        servicio.saveVendedor(vendedor);
        return ResponseEntity.ok(vendedor);
    }

    @PutMapping("/vendedores/api/{id}")
    @ResponseBody
    public ResponseEntity<Vendedor> updateVendedorApi(@PathVariable Integer id, @RequestBody VendedorDTO vendedorDTO) {
        Vendedor v_existente = servicio.getByIdVendedor(id);
        if (v_existente != null) {
            v_existente.setNombre(vendedorDTO.getNombre());
            v_existente.setDireccion(vendedorDTO.getDireccion());

            Coordenada coordenadas = v_existente.getCoordenadas();
            coordenadas.setLat(vendedorDTO.getLat());
            coordenadas.setLng(vendedorDTO.getLng());
            v_existente.setCoordenadas(coordenadas);

            servicio.updateVendedor(v_existente);
            return ResponseEntity.ok(v_existente);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/vendedores/api/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteVendedorApi(@PathVariable Integer id) {
        servicio.deleteVendedor(id);
        return ResponseEntity.ok("Vendedor eliminado correctamente");
    }
}