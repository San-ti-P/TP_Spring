package isi.deso.tpspring.service;

import isi.deso.tpspring.dao.*;
import isi.deso.tpspring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    PedidoRepository repositorio;
    @Autowired
    VendedorRepository vendedorRepositorio;
    @Autowired
    PagoRepository pagoRepositorio;
    @Autowired
    ClienteRepository clienteRepositorio;

    @Override
    public List<Pedido> getAllPedidos() {
        return repositorio.findAll();
    }

    @Override
    public Pedido getByIdPedido(Integer id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Pedido savePedido(Pedido p) {
        Vendedor vendedor = p.getVendedor();
        Cliente cliente = p.getCliente();
        Pago pago = p.getPago();

        if (vendedor != null && cliente != null && pago != null) {
            vendedorRepositorio.save(vendedor);
            clienteRepositorio.save(cliente);
            pagoRepositorio.save(pago);
        }
        return repositorio.save(p);
    }

    @Override
    public Pedido updatePedido(Pedido p) {
        Vendedor vendedor = p.getVendedor();
        Cliente cliente = p.getCliente();
        Pago pago = p.getPago();

        if (vendedor != null && cliente != null && pago != null) {
            vendedorRepositorio.save(vendedor);
            clienteRepositorio.save(cliente);
            pagoRepositorio.save(pago);
        }
        return repositorio.save(p);
    }

    @Override
    public void deletePedido(Integer id) {
        repositorio.deleteById(id);
    }

}