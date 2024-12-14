package isi.deso.tpspring.dao;

import isi.deso.tpspring.model.EstrategiaMercadoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstrategiaMercadoPagoRepository extends JpaRepository<EstrategiaMercadoPago, Integer> {
}
