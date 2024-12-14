package isi.deso.tpspring.service;

import isi.deso.tpspring.model.EstrategiaDePago;
import isi.deso.tpspring.model.EstrategiaTransferencia;
import org.springframework.stereotype.Service;


public interface EstrategiaDePagoService {
    public EstrategiaDePago saveEstrategiaDePago(EstrategiaDePago e);
    public EstrategiaDePago updateEstrategiaDePago(EstrategiaDePago e);
}
