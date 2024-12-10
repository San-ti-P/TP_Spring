package isi.deso.tpspring.service;

import isi.deso.tpspring.dao.ClienteRepository;
import isi.deso.tpspring.model.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repositorio;
    @Override
    public List<Cliente> getAllClientes() {
        return repositorio.findAll();
    }

    @Override
    public Cliente getByIdCliente(int id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return repositorio.save(cliente);
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        return repositorio.save(cliente);
    }

    @Override
    public void deleteCliente(Integer id) {
        repositorio.deleteById(id);
    }
    
}
