package isi.deso.tpspring.service;

import isi.deso.tpspring.dao.ClienteRepository;
import isi.deso.tpspring.dao.CoordenadaRepository;
import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.Coordenada;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
//        Coordenada c = repositorioCoordenada.findById(cliente.getCoordenadas().getId()).orElse(null);
//        if(c == null) c = new Coordenada();
//        c.setLat(cliente.getCoordenadas().getLat());
//        c.setLat(cliente.getCoordenadas().getLng());
//        cliente.setCoordenadas(c);
//        return repositorioCliente.save(cliente);
//        
//        Coordenada c = repositorioCoordenada.findById(cliente.getCoordenadas().getId()).orElse(null);
//        if(c != null) cliente.getCoordenadas().setId(c.getId());
//        return repositorioCliente.save(cliente);
        Coordenada coordenadas = cliente.getCoordenadas();
        if (coordenadas != null) {
            repositorioCoordenada.save(coordenadas);
        }
        return repositorioCliente.save(cliente);
    }

    @Override
    public void deleteCliente(Integer id) {
        repositorioCliente.deleteById(id);
    }
    
}
