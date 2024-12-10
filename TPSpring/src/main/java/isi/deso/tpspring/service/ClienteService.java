package isi.deso.tpspring.service;

import isi.deso.tpspring.model.Cliente;
import java.util.List;

public interface ClienteService {
    public List<Cliente> getAllClientes();
    public Cliente getByIdCliente(int id);
    public Cliente saveCliente(Cliente cliente);
    public Cliente updateCliente(Cliente cliente);
    public void deleteCliente(Integer id);
}
