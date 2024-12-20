package isi.deso.tpspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plato")

public class Plato extends ItemMenu{

    private static final double FACTOR_ENVASE = 1.1;
    @Column(name = "calorias")
    private int calorias;

    @Column(name = "apto_celiaco")
    private boolean aptoCeliaco;

    @Column(name = "peso")
    private float peso;

    public Plato(int id, String nombre, String descripcion, int calorias, boolean aptoCeliaco, float peso, float precio, Categoria categoria, boolean aptoVegano, Vendedor vendedor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.calorias = calorias;
        this.aptoCeliaco = aptoCeliaco;
        this.peso = peso;
        this.precio = precio;
        this.categoria = categoria;
        this.aptoVegano = aptoVegano;
        this.vendedor = vendedor;
    }
    
    @Override
    public String toString() {
        return "Plato{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", calorias=" + calorias +
                ", aptoCeliaco=" + aptoCeliaco +
                ", peso=" + peso +
                '}';
    }
     
    @Override
    public float peso(){
        return (float)(peso*FACTOR_ENVASE);
    }

    @Override
    public boolean esComida(){
        return true;
    }

    @Override
    public boolean esBebida(){
        return false;
    }

    @Override
    public boolean aptoVegano(){
        return aptoVegano;
    }

    @Override
    public float getPrecio() {
        return this.precio;
    }

}
