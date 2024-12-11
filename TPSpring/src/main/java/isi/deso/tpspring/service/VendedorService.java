package isi.deso.tpspring.service;

import isi.deso.tpspring.model.Vendedor;
import java.util.List;

public interface VendedorService {
    public List<Vendedor> getAllVendedores();
    public Vendedor getByIdVendedor(Integer id);
    public Vendedor saveVendedor(Vendedor v);
    public Vendedor updateVendedor(Vendedor v);
    public void deleteVendedor(Integer id);
}