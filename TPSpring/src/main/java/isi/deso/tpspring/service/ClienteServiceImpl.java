package isi.deso.tpspring.service;

import isi.deso.tpspring.dao.ClienteRepository;
import isi.deso.tpspring.dao.CoordenadaRepository;
import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.Coordenada;
import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repositorioCliente;
    
    @Autowired
    private CoordenadaRepository repositorioCoordenada;
    
    @Override
    public List<Cliente> getAllClientes() {
        return repositorioCliente.findAll();
    }

    @Override
    public Cliente getByIdCliente(int id) {
        return repositorioCliente.findById(id).orElse(null);
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        repositorioCoordenada.save(cliente.getCoordenadas());
        return repositorioCliente.save(cliente);
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        Coordenada coordenadas = cliente.getCoordenadas();
        if (coordenadas != null) {
            repositorioCoordenada.save(coordenadas);
        }
        return repositorioCliente.save(cliente);
    }

    @Transactional
    @Override
    public void deleteCliente(Integer id) {
        Cliente c = repositorioCliente.findById(id).orElse(null);
        if(c!=null){
            repositorioCoordenada.delete(c.getCoordenadas());
            repositorioCliente.deleteById(id);
        }
    }
    
}
