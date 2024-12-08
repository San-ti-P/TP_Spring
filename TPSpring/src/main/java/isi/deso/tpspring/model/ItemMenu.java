package isi.deso.tpspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_menu")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "item_menu_seq", sequenceName = "item_menu_seq", allocationSize = 1)
public abstract class ItemMenu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_menu_seq")
    protected int id;
    @Column(name = "nombre")
    protected String nombre;
    @Column(name = "descripcion")
    protected String descripcion;
    @Column(name = "precio")
    protected float precio;
    @OneToOne
    @JoinColumn(name = "categoria_id")
    protected Categoria categoria;
    @Column(name = "apto_vegano")
    protected boolean aptoVegano;
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    protected Vendedor vendedor;
    
    public abstract float peso();
    public abstract boolean esComida();
    public abstract boolean esBebida();
    public abstract boolean aptoVegano();
    
    public abstract int getId();    
    public abstract String getNombre();
    public abstract String getDescripcion();
    public abstract float getPrecio();
    public abstract Categoria getCategoria();
    public abstract boolean getAptoVegano();
    public abstract Vendedor getVendedor();
    public abstract void setId(int id);
    public abstract void setNombre(String nombre);
    public abstract void setDescripcion(String descripcion);
    public abstract void setPrecio(float precio);
    public abstract void setCategoria(Categoria categoria);
    public abstract void setAptoVegano(boolean aptoVegano);
    public abstract void setVendedor(Vendedor vendedor);
}

