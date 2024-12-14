package isi.deso.tpspring.dao;

import isi.deso.tpspring.model.EstrategiaTransferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstrategiaTransferenciaRepository extends JpaRepository<EstrategiaTransferencia, Integer> {
}
