package isi.deso.tpspring.service;

import isi.deso.tpspring.dao.EstrategiaDePagoRepository;
import isi.deso.tpspring.model.EstrategiaDePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstrategiaDePagoImpl implements EstrategiaDePagoService{

    @Autowired
    private EstrategiaDePagoRepository repository;

    public EstrategiaDePago saveEstrategiaDePago(EstrategiaDePago e) {
        return repository.save(e);
    }
}