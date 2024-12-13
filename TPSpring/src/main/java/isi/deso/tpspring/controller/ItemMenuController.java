package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.ItemMenuDTO;
import isi.deso.tpspring.model.Bebida;
import isi.deso.tpspring.model.Categoria;
import isi.deso.tpspring.model.ItemMenu;
import isi.deso.tpspring.model.Plato;
import isi.deso.tpspring.model.TipoItem;
import isi.deso.tpspring.model.Vendedor;
import isi.deso.tpspring.service.CategoriaService;
import isi.deso.tpspring.service.ItemMenuService;
import isi.deso.tpspring.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ItemMenuController {

    @Autowired
    private ItemMenuService itemMenuService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/items-menu")
    public String listItemsMenu(Model model) {
        List<ItemMenu> items = itemMenuService.getAllItemMenu();
        // Sort items by id
        List<ItemMenu> sortedItems = items.stream()
                .sorted((item1, item2) -> Integer.compare(item1.getId(), item2.getId()))
                .collect(Collectors.toList());
        model.addAttribute("items", sortedItems);
        return "items-menu";
    }

    @GetMapping("/items-menu/nuevo")
    public String newItemMenuForm(Model model) {
        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        model.addAttribute("itemMenu", itemMenuDTO);
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        model.addAttribute("vendedores", vendedorService.getAllVendedores());
        return "nuevo_itemMenu_form";
    }

    @PostMapping("/items-menu")
    public String saveItemMenu(@ModelAttribute("itemMenuDTO") ItemMenuDTO itemMenuDTO) {
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
        return "redirect:/items-menu";
    }

    @GetMapping("/items-menu/editar/{id}")
    public String editItemMenuForm(@PathVariable Integer id, Model model) {
        ItemMenu itemMenu = itemMenuService.getItemMenuById(id);
        if (itemMenu == null) {
            return "redirect:/items-menu";
        }
        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        itemMenuDTO.setId(itemMenu.getId());
        itemMenuDTO.setNombre(itemMenu.getNombre());
        itemMenuDTO.setDescripcion(itemMenu.getDescripcion());
        itemMenuDTO.setPrecio(itemMenu.getPrecio());
        itemMenuDTO.setAptoVegano(itemMenu.isAptoVegano());
        itemMenuDTO.setCategoriaId(itemMenu.getCategoria().getId());
        itemMenuDTO.setVendedorId(itemMenu.getVendedor().getId());
        if (itemMenu instanceof Plato) {
            Plato plato = (Plato) itemMenu;
            itemMenuDTO.setTipo(TipoItem.PLATO);
            itemMenuDTO.setCalorias(plato.getCalorias());
            itemMenuDTO.setAptoCeliaco(plato.isAptoCeliaco());
            itemMenuDTO.setPeso(plato.getPeso());
        } else if (itemMenu instanceof Bebida) {
            Bebida bebida = (Bebida) itemMenu;
            itemMenuDTO.setTipo(TipoItem.BEBIDA);
            itemMenuDTO.setGraduacionAlcoholica(bebida.getGraduacionAlcoholica());
            itemMenuDTO.setTamanio(bebida.getTamanio());
        }
        model.addAttribute("itemMenu", itemMenuDTO);
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        model.addAttribute("vendedores", vendedorService.getAllVendedores());
        return "editar_itemMenu";
    }

    @PostMapping("/items-menu/{id}")
    public String updateItemMenu(@PathVariable Integer id, @ModelAttribute("itemMenuDTO") ItemMenuDTO itemMenuDTO, Model model) {
        ItemMenu existingItemMenu = itemMenuService.getItemMenuById(id);
        if (existingItemMenu == null) {
            return "redirect:/items-menu";
        }

        existingItemMenu.setNombre(itemMenuDTO.getNombre());
        existingItemMenu.setDescripcion(itemMenuDTO.getDescripcion());
        existingItemMenu.setPrecio(itemMenuDTO.getPrecio());
        existingItemMenu.setAptoVegano(itemMenuDTO.isAptoVegano());
        existingItemMenu.setCategoria(categoriaService.getCategoriaById(itemMenuDTO.getCategoriaId()));
        existingItemMenu.setVendedor(vendedorService.getByIdVendedor(itemMenuDTO.getVendedorId()));

        if (existingItemMenu instanceof Plato) {
            Plato plato = (Plato) existingItemMenu;
            plato.setCalorias(itemMenuDTO.getCalorias());
            plato.setAptoCeliaco(itemMenuDTO.isAptoCeliaco());
            plato.setPeso(itemMenuDTO.getPeso());
        } else if (existingItemMenu instanceof Bebida) {
            Bebida bebida = (Bebida) existingItemMenu;
            bebida.setGraduacionAlcoholica(itemMenuDTO.getGraduacionAlcoholica());
            bebida.setTamanio(itemMenuDTO.getTamanio());
        }

        itemMenuService.updateItemMenu(existingItemMenu);
        return "redirect:/items-menu";
    }

    @GetMapping("/items-menu/{id}")
    public String deleteItemMenu(@PathVariable Integer id) {
        itemMenuService.deleteItemMenu(id);
        return "redirect:/items-menu";
    }
}