package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.ItemMenuDTO;
import isi.deso.tpspring.model.*;
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
        return "nuevo_itemMenu_from_items";
    }

    @PostMapping("/items-menu")
    public String saveItemMenu(@ModelAttribute("itemMenu") ItemMenuDTO itemMenuDTO) {
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
        if (itemMenu.esComida()) {
            Plato plato = (Plato) itemMenu;
            itemMenuDTO.setTipo(TipoItem.PLATO);
            itemMenuDTO.setCalorias(plato.getCalorias());
            itemMenuDTO.setAptoCeliaco(plato.isAptoCeliaco());
            itemMenuDTO.setPeso(plato.getPeso());
        } else if (itemMenu.esBebida()) {
            Bebida bebida = (Bebida) itemMenu;
            itemMenuDTO.setTipo(TipoItem.BEBIDA);
            itemMenuDTO.setGraduacionAlcoholica(bebida.getGraduacionAlcoholica());
            itemMenuDTO.setTamanio(bebida.getTamanio());
        }
        model.addAttribute("itemMenu", itemMenuDTO);
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        model.addAttribute("vendedores", vendedorService.getAllVendedores());
        return "editar_itemMenu_from_items";
    }

    @PostMapping("/items-menu/{id}")
    public String updateItemMenu(@PathVariable Integer id, @ModelAttribute("itemMenu") ItemMenuDTO itemMenuDTO) {
        ItemMenu itemMenuExistente = itemMenuService.getItemMenuById(id);
        if (itemMenuExistente == null) {
            return "redirect:/items-menu";
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
        return "redirect:/items-menu";
    }

    @GetMapping("/items-menu/{id}")
    public String deleteItemMenu(@PathVariable Integer id) {
        itemMenuService.deleteItemMenu(id);
        return "redirect:/items-menu";
    }

    @GetMapping("/vendedor/{id}/menu")
    public String getMenuByVendedor(@PathVariable Integer id, Model model) {
        Vendedor vendedor = vendedorService.getByIdVendedor(id);
        if (vendedor == null) {
            return "redirect:/vendedores";
        }
        List<ItemMenu> items = vendedorService.getItemsMenuByVendedor(id);
        model.addAttribute("vendedor", vendedor);
        model.addAttribute("items", items);
        return "menu_vendedor";
    }

    @GetMapping("/vendedor/{id}/items-menu/nuevo")
    public String newItemMenuFormFromVendedor(@PathVariable Integer id, Model model) {
        Vendedor vendedor = vendedorService.getByIdVendedor(id);
        if (vendedor == null) {
            return "redirect:/vendedores";
        }
        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        itemMenuDTO.setVendedorId(id);
        model.addAttribute("itemMenu", itemMenuDTO);
        model.addAttribute("vendedor", vendedor);
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        model.addAttribute("vendedores", vendedorService.getAllVendedores());
        return "nuevo_itemMenu_from_menu_vendedor";
    }

    @GetMapping("/vendedor/{id}/items-menu/editar/{itemId}")
    public String editItemMenuFormFromVendedor(@PathVariable Integer id, @PathVariable Integer itemId, Model model) {
        Vendedor vendedor = vendedorService.getByIdVendedor(id);
        if (vendedor == null) {
            return "redirect:/vendedores";
        }
        ItemMenu itemMenu = itemMenuService.getItemMenuById(itemId);
        if (itemMenu == null) {
            return "redirect:/vendedor/" + id + "/menu";
        }
        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        itemMenuDTO.setId(itemMenu.getId());
        itemMenuDTO.setNombre(itemMenu.getNombre());
        itemMenuDTO.setDescripcion(itemMenu.getDescripcion());
        itemMenuDTO.setPrecio(itemMenu.getPrecio());
        itemMenuDTO.setAptoVegano(itemMenu.isAptoVegano());
        itemMenuDTO.setCategoriaId(itemMenu.getCategoria().getId());
        itemMenuDTO.setVendedorId(itemMenu.getVendedor().getId());
        if (itemMenu.esComida()) {
            Plato plato = (Plato) itemMenu;
            itemMenuDTO.setTipo(TipoItem.PLATO);
            itemMenuDTO.setCalorias(plato.getCalorias());
            itemMenuDTO.setAptoCeliaco(plato.isAptoCeliaco());
            itemMenuDTO.setPeso(plato.getPeso());
        } else if (itemMenu.esBebida()) {
            Bebida bebida = (Bebida) itemMenu;
            itemMenuDTO.setTipo(TipoItem.BEBIDA);
            itemMenuDTO.setGraduacionAlcoholica(bebida.getGraduacionAlcoholica());
            itemMenuDTO.setTamanio(bebida.getTamanio());
        }
        model.addAttribute("itemMenu", itemMenuDTO);
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        model.addAttribute("vendedores", vendedorService.getAllVendedores());
        return "editar_itemMenu_from_menu_vendedor";
    }

    @PostMapping("/vendedor/{id}/items-menu")
    public String saveItemMenuFromVendedor(@PathVariable Integer id, @ModelAttribute("itemMenu") ItemMenuDTO itemMenuDTO) {
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
        return "redirect:/vendedor/" + id + "/menu";
    }

    @PostMapping("/vendedor/{id}/items-menu/{itemId}")
    public String updateItemMenuFromVendedor(@PathVariable Integer id, @PathVariable Integer itemId, @ModelAttribute("itemMenu") ItemMenuDTO itemMenuDTO) {
        ItemMenu itemMenuExistente = itemMenuService.getItemMenuById(itemId);
        if (itemMenuExistente == null) {
            return "redirect:/vendedor/" + id + "/menu";
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
        return "redirect:/vendedor/" + id + "/menu";
    }

    @GetMapping("/vendedor/{id}/items-menu/{itemId}/eliminar")
    public String deleteItemMenuFromVendedor(@PathVariable Integer id, @PathVariable Integer itemId) {
        itemMenuService.deleteItemMenu(itemId);
        return "redirect:/vendedor/" + id + "/menu";
    }
}