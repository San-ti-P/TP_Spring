package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.ItemMenuDTO;
import isi.deso.tpspring.model.Bebida;
import isi.deso.tpspring.model.ItemMenu;
import isi.deso.tpspring.model.Plato;
import isi.deso.tpspring.model.TipoItem;
import isi.deso.tpspring.service.CategoriaService;
import isi.deso.tpspring.service.ItemMenuService;
import isi.deso.tpspring.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemMenuRestController {

    @Autowired
    private ItemMenuService itemMenuService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/items-menu/api")
    @ResponseBody
    public ResponseEntity<List<ItemMenu>> getAllItemsMenuApi() {
        return ResponseEntity.ok(itemMenuService.getAllItemMenu());
    }

    @GetMapping("/items-menu/api/{id}")
    @ResponseBody
    public ResponseEntity<ItemMenu> getItemMenuByIdApi(@PathVariable Integer id) {
        ItemMenu item = itemMenuService.getItemMenuById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/items-menu/api")
    @ResponseBody
    public ResponseEntity<ItemMenu> saveItemMenuApi(@RequestBody ItemMenuDTO itemMenuDTO) {
        ItemMenu itemMenu;
        if (itemMenuDTO.getTipo() == TipoItem.PLATO) {
            Plato plato = new Plato();
            plato.setCalorias(itemMenuDTO.getCalorias());
            plato.setAptoCeliaco(itemMenuDTO.isAptoCeliaco());
            plato.setPeso(itemMenuDTO.getPeso());
            itemMenu = plato;
        } else if (itemMenuDTO.getTipo() == TipoItem.BEBIDA) {
            Bebida bebida = new Bebida();
            bebida.setGraduacionAlcoholica(itemMenuDTO.getGraduacionAlcoholica());
            bebida.setTamanio(itemMenuDTO.getTamanio());
            itemMenu = bebida;
        } else {
            throw new IllegalArgumentException("Tipo de ItemMenu no soportado");
        }

        itemMenu.setNombre(itemMenuDTO.getNombre());
        itemMenu.setDescripcion(itemMenuDTO.getDescripcion());
        itemMenu.setPrecio(itemMenuDTO.getPrecio());
        itemMenu.setAptoVegano(itemMenuDTO.isAptoVegano());
        itemMenu.setCategoria(categoriaService.getCategoriaById(itemMenuDTO.getCategoriaId()));
        itemMenu.setVendedor(vendedorService.getByIdVendedor(itemMenuDTO.getVendedorId()));

        itemMenuService.createItemMenu(itemMenu);
        return ResponseEntity.ok(itemMenu);
    }

    @PutMapping("/items-menu/api/{id}")
    @ResponseBody
    public ResponseEntity<ItemMenu> updateItemMenuApi(@PathVariable Integer id, @RequestBody ItemMenuDTO itemMenuDTO) {
        ItemMenu itemMenuExistente = itemMenuService.getItemMenuById(id);
        if (itemMenuExistente == null) {
            return ResponseEntity.notFound().build();
        }

        itemMenuExistente.setNombre(itemMenuDTO.getNombre());
        itemMenuExistente.setDescripcion(itemMenuDTO.getDescripcion());
        itemMenuExistente.setPrecio(itemMenuDTO.getPrecio());
        itemMenuExistente.setAptoVegano(itemMenuDTO.isAptoVegano());
        itemMenuExistente.setCategoria(categoriaService.getCategoriaById(itemMenuDTO.getCategoriaId()));
        itemMenuExistente.setVendedor(vendedorService.getByIdVendedor(itemMenuDTO.getVendedorId()));

        if (itemMenuExistente.esComida()) {
            Plato plato = (Plato) itemMenuExistente;
            plato.setCalorias(itemMenuDTO.getCalorias());
            plato.setAptoCeliaco(itemMenuDTO.isAptoCeliaco());
            plato.setPeso(itemMenuDTO.getPeso());
        } else if (itemMenuExistente.esBebida()) {
            Bebida bebida = (Bebida) itemMenuExistente;
            bebida.setGraduacionAlcoholica(itemMenuDTO.getGraduacionAlcoholica());
            bebida.setTamanio(itemMenuDTO.getTamanio());
        }

        itemMenuService.updateItemMenu(itemMenuExistente);
        return ResponseEntity.ok(itemMenuExistente);
    }

    @DeleteMapping("/items-menu/api/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteItemMenuApi(@PathVariable Integer id) {
        itemMenuService.deleteItemMenu(id);
        return ResponseEntity.ok("ItemMenu eliminado correctamente");
    }
}