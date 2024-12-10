package isi.deso.tpspring.service;

import isi.deso.tpspring.dao.VendedorRepository;
import isi.deso.tpspring.model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorServiceImpl implements VendedorService{

    @Autowired
    private VendedorRepository repositorio;

    @Override
    public List<Vendedor> getAllVendedores() {
        return repositorio.findAll();
    }

    @Override
    public Vendedor getByIdVendedor(int id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Vendedor saveVendedor(Vendedor v) {
        return repositorio.save(v);
    }

    @Override
    public Vendedor updateVendedor(Vendedor v) {
        return repositorio.save(v);
    }

    @Override
    public void deleteVendedor(int id) {
        repositorio.deleteById(id);
    }
}