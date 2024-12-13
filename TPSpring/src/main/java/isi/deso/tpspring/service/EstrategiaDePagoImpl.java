package isi.deso.tpspring.service;

import isi.deso.tpspring.dao.EstrategiaDePagoRepository;
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
    private EstrategiaMercadoPagoRepository estMPRepository;
    
    @Autowired
    private EstrategiaTransferenciaRepository estTransferenciaRepository;

    @Override
    public EstrategiaDePago saveEstrategiaDePago(EstrategiaDePago e) {
        if (e instanceof EstrategiaMercadoPago) {
            return estMPRepository.save((EstrategiaMercadoPago) e);
        } else if (e instanceof EstrategiaTransferencia) {
            return estTransferenciaRepository.save((EstrategiaTransferencia) e);
        }
        return null;
    }
}