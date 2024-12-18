package isi.deso.tpspring.service;

import isi.deso.tpspring.dao.CoordenadaRepository;
import isi.deso.tpspring.dao.VendedorRepository;
import isi.deso.tpspring.model.Coordenada;
import isi.deso.tpspring.model.ItemMenu;
import isi.deso.tpspring.model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorServiceImpl implements VendedorService{

    @Autowired
    private VendedorRepository repositorio;

    @Autowired
    private CoordenadaRepository coordenadaRepositorio;


    @Override
    public List<Vendedor> getAllVendedores() {
        return repositorio.findAll();
    }

    @Override
    public Vendedor getByIdVendedor(Integer id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Vendedor saveVendedor(Vendedor v) {
        Coordenada coordenadas = v.getCoordenadas();
        if (coordenadas != null) {
            coordenadaRepositorio.save(coordenadas);
        }
        return repositorio.save(v);
    }

    @Override
    public Vendedor updateVendedor(Vendedor v) {
        Coordenada coordenadas = v.getCoordenadas();
        if (coordenadas != null) {
            coordenadaRepositorio.save(coordenadas);
        }
        return repositorio.save(v);
    }

    @Override
    public void deleteVendedor(Integer id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<ItemMenu> getItemsMenuByVendedor(Integer vendedorId) {
        Vendedor vendedor = repositorio.findById(vendedorId).orElseThrow(() -> new RuntimeException("No se encontro vendedor"));
        return vendedor.getMenu();
    }


}