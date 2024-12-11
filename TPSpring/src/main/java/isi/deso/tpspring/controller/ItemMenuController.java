package isi.deso.tpspring.controller;

import isi.deso.tpspring.model.ItemMenu;
import isi.deso.tpspring.service.ItemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import isi.deso.tpspring.dto.ItemMenuDTO;

@Controller
public class ItemMenuController {

    @Autowired
    private ItemMenuService itemMenuService;

    @GetMapping("/items-menu")
    public String getItemMenuPage(Model model) {
        model.addAttribute("items", itemMenuService.getAllItemMenu());
        return "items-menu";
    }

    @GetMapping("/items-menu/{id}")
    public ResponseEntity<ItemMenu> getItemMenuById(@PathVariable Integer id) {
        ItemMenu item = itemMenuService.getItemMenuById(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @PostMapping("/items-menu")
    public String createItemMenu(@ModelAttribute("items-menu") ItemMenu itemMenu) {
        itemMenuService.createItemMenu(itemMenu);
        return "redirect:/items-menu";
    }

    @PutMapping("/items-menu")
    public String updateItemMenu(@ModelAttribute("items-menu") ItemMenu itemMenu) {
        itemMenuService.updateItemMenu(itemMenu);
        return "redirect:/items-menu";
    }

    @DeleteMapping("/items-menu/{id}")
    public String deleteItemMenu(@PathVariable Integer id) {
        itemMenuService.deleteItemMenu(id);
        return "redirect:/items-menu";
    }

    @GetMapping("/items-menu/nuevo")
    public String newItemMenuForm(Model model) {
        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        model.addAttribute("itemMenuDTO", itemMenuDTO);
        return "nuevo_itemMenu_form";
    }
}