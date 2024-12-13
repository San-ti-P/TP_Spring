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
public abstract class ItemMenu implements Comparable<ItemMenu>{
    
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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "vendedor_id", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
//    protected Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    protected Vendedor vendedor;

    /*
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    protected Vendedor vendedor;
*/
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

    @Override
    public int compareTo(ItemMenu item) {
        return this.id - item.getId();
    }
//    public abstract int getId();    
//    public abstract String getNombre();
//    public abstract String getDescripcion();
      public abstract float getPrecio();
//    public abstract Categoria getCategoria();
//    public abstract boolean getAptoVegano();
//    public abstract Vendedor getVendedor();
//    public abstract void setId(int id);
//    public abstract void setNombre(String nombre);
//    public abstract void setDescripcion(String descripcion);
//    public abstract void setPrecio(float precio);
//    public abstract void setCategoria(Categoria categoria);
//    public abstract void setAptoVegano(boolean aptoVegano);
//    public abstract void setVendedor(Vendedor vendedor);
}

