package isi.deso.tpspring.service;

import isi.deso.tpspring.model.Pedido;
import java.util.List;

public interface PedidoService {
    public List<Pedido> getAllPedidos();
    public Pedido getByIdPedido(Integer id);
    public Pedido savePedido(Pedido p);
    public Pedido updatePedido(Pedido p);
    public void deletePedido(Integer id);
}
