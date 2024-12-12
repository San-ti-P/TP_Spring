/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tpspring.dto;

import isi.deso.tpspring.model.ItemMenu;

/**
 *
 * @author santi
 */
public class ItemPedidoDTO {

    private Integer item;
    private String nombre;
    private Float precio;
    private Integer cantidad;

    // Getters y Setters
    
    public Integer getItem() {
        return item;
    }

    public String getNombre(){
        return nombre;
    }
    
    public Float getPrecio() {
        return precio;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }
    
    public void setItem(Integer item) {
        this.item = item;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
