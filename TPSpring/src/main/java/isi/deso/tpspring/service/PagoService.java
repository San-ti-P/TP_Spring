package isi.deso.tpspring.service;

import isi.deso.tpspring.model.Pago;

import java.util.List;

public interface PagoService {
    public List<Pago> getAllPagos();
    public Pago getByIdPago(Integer id);
    public Pago savePago(Pago p);
    public Pago updatePago(Pago p);
    public void deletePago(Integer id);
}
