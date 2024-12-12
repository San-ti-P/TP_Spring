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
        model.addAttribute("items", itemMenuService.getAllItemMenu());
        return "items-menu";
    }

    @GetMapping("/items-menu/nuevo")
    public String newItemMenuForm(Model model) {
        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        model.addAttribute("itemMenu", itemMenuDTO);
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

        Categoria categoria = categoriaService.getCategoriaById(itemMenuDTO.getCategoriaId());
        itemMenu.setCategoria(categoria);

        Vendedor vendedor = vendedorService.getByIdVendedor(itemMenuDTO.getVendedorId());
        itemMenu.setVendedor(vendedor);

        itemMenuService.createItemMenu(itemMenu);
        return "redirect:/items-menu";
    }

    @GetMapping("/items-menu/editar/{id}")
    public String editItemMenuForm(@PathVariable Integer id, Model model) {
        ItemMenu itemMenu = itemMenuService.getItemMenuById(id);
        if (itemMenu == null) {
            return "redirect:/items-menu";
        }
        model.addAttribute("itemMenu", itemMenu);
        return "editar_itemMenu_form";
    }

    @PostMapping("/items-menu/{id}")
    public String updateItemMenu(@PathVariable Integer id, @ModelAttribute("itemMenu") ItemMenu itemMenu) {
        ItemMenu existingItemMenu = itemMenuService.getItemMenuById(id);
        if (existingItemMenu == null) {
            return "redirect:/items-menu";
        }

        existingItemMenu.setNombre(itemMenu.getNombre());
        existingItemMenu.setDescripcion(itemMenu.getDescripcion());
        existingItemMenu.setPrecio(itemMenu.getPrecio());
        existingItemMenu.setAptoVegano(itemMenu.isAptoVegano());
        existingItemMenu.setCategoria(itemMenu.getCategoria());
        existingItemMenu.setVendedor(itemMenu.getVendedor());

        if (itemMenu instanceof Plato) {
            Plato plato = (Plato) itemMenu;
            ((Plato) existingItemMenu).setCalorias(plato.getCalorias());
            ((Plato) existingItemMenu).setAptoCeliaco(plato.isAptoCeliaco());
            ((Plato) existingItemMenu).setPeso(plato.getPeso());
        } else if (itemMenu instanceof Bebida) {
            Bebida bebida = (Bebida) itemMenu;
            ((Bebida) existingItemMenu).setGraduacionAlcoholica(bebida.getGraduacionAlcoholica());
            ((Bebida) existingItemMenu).setTamanio(bebida.getTamanio());
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