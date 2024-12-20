package isi.deso.tpspring.service;

import isi.deso.tpspring.dao.*;
import isi.deso.tpspring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService{

    @Autowired
    PagoRepository repositorio;

    @Autowired
    PedidoRepository repositorioPedido;

    @Override
    public List<Pago> getAllPagos() {
        return repositorio.findAll();
    }

    @Override
    public Pago getByIdPago(Integer id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Pago savePago(Pago p) {
        Pedido pedido = p.getPedido();

        if (pedido != null) {
            repositorioPedido.save(pedido);
        }
        return repositorio.save(p);
    }

    @Override
    public Pago updatePago(Pago p) {
        Pedido pedido = p.getPedido();

        if (pedido != null) {
            repositorioPedido.save(pedido);
        }
        return repositorio.save(p);
    }

    @Override
    public void deletePago(Integer id) {
        repositorio.deleteById(id);
    }

}