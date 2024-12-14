package isi.deso.tpspring.service;

import isi.deso.tpspring.dao.ItemPedidoRepository;
import isi.deso.tpspring.model.ItemPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService{

    @Autowired
    ItemPedidoRepository repositorio;
    
    @Override
    public ItemPedido getByIdItemPedido(Integer id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public List<ItemPedido> getAllItems() {
        return repositorio.findAll();
    }

    @Override
    public List<ItemPedido> getItemsByVendedor(Integer vendedorId) {
        return repositorio.findByVendedorId(vendedorId);
    }

    @Override
    public ItemPedido getItemById(Integer id) {
        return repositorio.findById(id).orElse(null);
    }
    
    @Override
    public ItemPedido saveItemPedido(ItemPedido ip) {
        return repositorio.save(ip);
    }

    @Override
    public void deleteItemPedido(Integer id) {
        repositorio.deleteById(id);
    }
}
