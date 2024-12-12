package isi.deso.tpspring.dao;

import isi.deso.tpspring.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
    @Query("SELECT p FROM Pedido p WHERE p.cliente_id LIKE %?1% OR p.vendedor_id LIKE %?1% OR p.estado LIKE %?1%")
    List<Pedido> findAll(String palabraClave);
}
