package isi.deso.tpspring.service;

import isi.deso.tpspring.model.ItemMenu;
import java.util.List;

public interface ItemMenuService {
    public List<ItemMenu> getAllItemMenu();
    public ItemMenu getItemMenuById(Integer id);
    public void updateItemMenu(ItemMenu itemMenu);
    public void deleteItemMenu(Integer id);
    public void createItemMenu(ItemMenu itemMenu);
}
