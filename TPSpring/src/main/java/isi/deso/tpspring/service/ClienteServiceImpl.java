/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tpspring.service;

import isi.deso.tpspring.dao.ClienteRepository;
import isi.deso.tpspring.dao.CoordenadaRepository;
import isi.deso.tpspring.model.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author santi
 */

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repositorioCliente;
    
    @Autowired
    private CoordenadaRepository repositorioCoordenada;
    
    @Override
    public List<Cliente> listAllClientes() {
        return repositorioCliente.findAll();
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        repositorioCoordenada.save(cliente.getCoordenadas());
        return repositorioCliente.save(cliente);
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        return repositorioCliente.save(cliente);
    }

    @Override
    public void deleteCliente(Integer id) {
        repositorioCliente.deleteById(id);
    }
    
}
