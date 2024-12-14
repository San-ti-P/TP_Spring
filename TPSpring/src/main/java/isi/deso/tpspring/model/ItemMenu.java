package isi.deso.tpspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_menu")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemMenu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "nombre")
    protected String nombre;

    @Column(name = "descripcion")
    protected String descripcion;

    @Column(name = "precio")
    protected float precio;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    protected Categoria categoria;

    @Column(name = "apto_vegano")
    protected boolean aptoVegano;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    protected Vendedor vendedor;

    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ItemPedido> itemPedidos;
    
    public abstract float peso();
    public abstract boolean esComida();
    public abstract boolean esBebida();
    public abstract boolean aptoVegano();

    @Override
    public String toString() {
        return "ItemMenu{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria.getDescripcion() +
                ", aptoVegano=" + aptoVegano +
                ", vendedor=" + vendedor.getNombre() +
                '}';
    }

}

