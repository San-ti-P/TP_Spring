package isi.deso.tpspring.dao;

import isi.deso.tpspring.model.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Integer> {
    
}
