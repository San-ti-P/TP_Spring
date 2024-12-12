package isi.deso.tpspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cantidad")
    private int cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemMenu item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

//    La relación @ManyToOne con fetch = FetchType.LAZY significa que la entidad ItemPedido no cargará la entidad Pedido asociada hasta que se acceda explícitamente a ella.

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", item=" + item.getNombre() +
                '}';
    }
}