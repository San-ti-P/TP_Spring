package isi.deso.tpspring.service;

import isi.deso.tpspring.model.ItemPedido;
import java.util.List;

public interface ItemPedidoService {
    List<ItemPedido> getAllItems();
    List<ItemPedido> getItemsByVendedor(Integer vendedorId);
}