package isi.deso.tpspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isi.deso.tpspring.model.ItemMenu;
import isi.deso.tpspring.model.Plato;
import isi.deso.tpspring.model.Bebida;
import isi.deso.tpspring.dao.PlatoRepository;
import isi.deso.tpspring.dao.BebidaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemMenuServiceImpl implements ItemMenuService {

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private BebidaRepository bebidaRepository;

    @Override
    public List<ItemMenu> getAllItemMenu() {
        List<ItemMenu> items = new ArrayList<>();
        items.addAll(platoRepository.findAll());
        items.addAll(bebidaRepository.findAll());
        return items;
    }

    @Override
    public ItemMenu getItemMenuById(Integer id) {
        ItemMenu item = platoRepository.findById(id).orElse(null);
        if (item == null) {
            item = bebidaRepository.findById(id).orElse(null);
        }
        return item;
    }

    @Override
    public void updateItemMenu(ItemMenu itemMenu) {
        if (itemMenu instanceof Plato) {
            platoRepository.save((Plato) itemMenu);
        } else if (itemMenu instanceof Bebida) {
            bebidaRepository.save((Bebida) itemMenu);
        }
    }

    @Override
    public void deleteItemMenu(Integer id) {
        if (platoRepository.existsById(id)) {
            platoRepository.deleteById(id);
        } else if (bebidaRepository.existsById(id)) {
            bebidaRepository.deleteById(id);
        }
    }

    @Override
    public void createItemMenu(ItemMenu itemMenu) {
        if (itemMenu instanceof Plato) {
            platoRepository.save((Plato) itemMenu);
        } else if (itemMenu instanceof Bebida) {
            bebidaRepository.save((Bebida) itemMenu);
        }
    }
}