package isi.deso.tpspring.service;

import isi.deso.tpspring.dao.EstrategiaMercadoPagoRepository;
import isi.deso.tpspring.dao.EstrategiaTransferenciaRepository;
import isi.deso.tpspring.model.EstrategiaDePago;
import isi.deso.tpspring.model.EstrategiaMercadoPago;
import isi.deso.tpspring.model.EstrategiaTransferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstrategiaDePagoImpl implements EstrategiaDePagoService{

    @Autowired
    private EstrategiaMercadoPagoRepository mercadoPagoRepositorio;
    
    @Autowired
    private EstrategiaTransferenciaRepository transferenciaRepositorio;

    @Override
    public EstrategiaDePago saveEstrategiaDePago(EstrategiaDePago e) {
        if (e instanceof EstrategiaMercadoPago) {
            return mercadoPagoRepositorio.save((EstrategiaMercadoPago) e);
        } else if (e instanceof EstrategiaTransferencia) {
            return transferenciaRepositorio.save((EstrategiaTransferencia) e);
        }
        return null;
    }

    @Override
    public EstrategiaDePago updateEstrategiaDePago(EstrategiaDePago e) {
        if (e instanceof EstrategiaMercadoPago) {
            return mercadoPagoRepositorio.save((EstrategiaMercadoPago) e);
        } else if (e instanceof EstrategiaTransferencia) {
            return transferenciaRepositorio.save((EstrategiaTransferencia) e);
        }
        return null;
    }
}