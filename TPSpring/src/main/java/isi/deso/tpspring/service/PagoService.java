package isi.deso.tpspring.service;

import isi.deso.tpspring.model.Pedido;
import java.util.List;

public interface PagoService {
    public List<Pago> getAllPago();
    public Pedido getByIdPago(Integer id);
    public Pedido savePago(Pago p);
    public Pedido updatePago(Pago p);
    public void deletePago(Integer id);
}
