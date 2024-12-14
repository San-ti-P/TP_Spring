package isi.deso.tpspring.dao;

import isi.deso.tpspring.model.EstrategiaMercadoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstrategiaMercadoPagoRepository extends JpaRepository<EstrategiaMercadoPago, Integer> {
}
