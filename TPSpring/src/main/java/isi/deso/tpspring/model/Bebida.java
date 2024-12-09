/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tpspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author santi
 */
@Data
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
    private int tamaño;
    
//    public Bebida(int id, String nombre, String descripcion, float precio, Categoria categoria, float graduacion, int tam, boolean aptoVegano, Vendedor vendedor){
//        this.id = id;
//        this.nombre = nombre;
//        this.descripcion = descripcion;
//        this.precio = precio;
//        this.categoria = categoria;
//        this.aptoVegano = aptoVegano;
//        this.vendedor = vendedor;
//        graduacionAlcoholica = graduacion;
//        tamaño = tam;
//    }
    
//    @Override
//    public int getId() {
//        return id;
//    }
//    @Override
//    public String getNombre() {
//        return nombre;
//    }
//    @Override
//    public String getDescripcion() {
//        return descripcion;
//    }
//    @Override
//    public float getPrecio() {
//        return precio;
//    }
//    @Override
//    public Categoria getCategoria() {
//        return categoria;
//    }
//    @Override
//    public boolean getAptoVegano() {
//        return aptoVegano;
//    }
//    public float getGraduacionAlcoholica() {
//        return graduacionAlcoholica;
//    }
//    public int getTamaño() {
//        return tamaño;
//    }
//    @Override
//    public Vendedor getVendedor(){
//        return vendedor;
//    }
//    
//    @Override
//    public void setId(int id) {
//        this.id = id;
//    }
//    @Override
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//    @Override
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
//    @Override
//    public void setPrecio(float precio) {
//        this.precio = precio;
//    }
//    @Override
//    public void setCategoria(Categoria categoria) {
//        this.categoria = categoria;
//    }  
//    @Override
//    public void setAptoVegano(boolean aptoVegano) {
//        this.aptoVegano = aptoVegano;
//    }
//    public void setGraduacionAlcoholica(float graduacionAlcoholica) {
//        this.graduacionAlcoholica = graduacionAlcoholica;
//    }
//    public void setTamaño(int tamaño) {
//        this.tamaño = tamaño;
//    }
//    @Override
//    public void setVendedor(Vendedor vendedor){
//        this.vendedor = vendedor;
//    }
    @Override
    public float peso(){
        if(this.esAlcoholica()){
            return (float)(tamaño*FACTOR_ALCOHOLICA*FACTOR_ENVASE);
        }
        else{
            return (float)(tamaño*FACTOR_ANALCOHOLICA*FACTOR_ENVASE);
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
        if(graduacionAlcoholica > 0){
            return true;
        }
        else{
            return false;
        }
    }
    
//    @Override
//    public String toString() {
//        return "[Id=" + id + ", nombre=" + nombre + ']';
//    }
    
//    public boolean equals(Object o){
//        ItemMenu otro = (ItemMenu) o;
//        return id == otro.getId();
//    }
}

