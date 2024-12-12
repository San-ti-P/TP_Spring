package isi.deso.tpspring.dao;

import isi.deso.tpspring.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
    @Query("SELECT ip FROM ItemPedido ip WHERE ip.item.vendedor.id = :vendedorId")
    List<ItemPedido> findByVendedorId(@Param("vendedorId") Integer vendedorId);
}