package isi.deso.tpspring.service;

import isi.deso.tpspring.model.ItemPedido;
import java.util.List;

public interface ItemPedidoService {
    public ItemPedido getByIdItemPedido(Integer id);
    List<ItemPedido> getAllItems();
    List<ItemPedido> getItemsByVendedor(Integer vendedorId);
}