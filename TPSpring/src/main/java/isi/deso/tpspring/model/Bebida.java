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
@Table(name = "bebida")
public class Bebida extends ItemMenu{

    private static final double FACTOR_ALCOHOLICA = 0.99;
    private static final double FACTOR_ANALCOHOLICA = 1.04;
    private static final double FACTOR_ENVASE = 1.2;

    @Column(name = "graduacion_alcoholica")
    private float graduacionAlcoholica;

    @Column(name = "tamaño")
    private int tamanio;

    @Override
    public float getPrecio() {
        return this.precio;
    }

    public Bebida(int id, String nombre, String descripcion, float precio, Categoria categoria, float graduacion, int tam, boolean aptoVegano, Vendedor vendedor){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.aptoVegano = aptoVegano;
        this.vendedor = vendedor;
        graduacionAlcoholica = graduacion;
        tamanio = tam;
    }

    @Override
    public float peso(){
        if(this.esAlcoholica()){
            return (float)(tamanio*FACTOR_ALCOHOLICA*FACTOR_ENVASE);
        }
        else{
            return (float)(tamanio*FACTOR_ANALCOHOLICA*FACTOR_ENVASE);
        }
    }

    @Override
    public boolean esComida(){
        return false;
    }

    @Override
    public boolean esBebida(){
        return true;
    }

    @Override
    public boolean aptoVegano(){
        return aptoVegano;
    };

    public boolean esAlcoholica(){
        return graduacionAlcoholica > 0;
    }

}

