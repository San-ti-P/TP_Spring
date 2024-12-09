package isi.deso.tpspring.dao;

import isi.deso.tpspring.model.Coordenada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadaRepository extends JpaRepository<Coordenada, Integer> {

}
