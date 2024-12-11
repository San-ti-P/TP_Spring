package isi.deso.tpspring.dao;

import isi.deso.tpspring.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer>{
    @Query("SELECT v FROM Vendedor v WHERE v.nombre LIKE %?1% OR v.direccion LIKE %?1%")
    List<Vendedor> findAll(String palabraClave);
}