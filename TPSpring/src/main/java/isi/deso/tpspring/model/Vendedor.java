package isi.deso.tpspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendedor")
@SequenceGenerator(name = "vendedor_seq", sequenceName = "vendedor_seq", allocationSize = 1)
public class Vendedor {
    final static private double RADIOTIERRA = 6378.0;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendedor_seq")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @OneToOne
    private Coordenada coordenadas;
    private TreeSet<ItemMenu> menu;
    private TreeSet<Pedido> pedidos;

    public Vendedor(){
        menu = new TreeSet<ItemMenu>();
        pedidos = new TreeSet<Pedido>();
    }

    public Vendedor(int id, String nombre, String direccion, Coordenada coordenadas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenadas = coordenadas;
        menu = new TreeSet<ItemMenu>();
        pedidos = new TreeSet<Pedido>();
    }
    /*
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Coordenada getCoordenadas() {
        return coordenadas;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCoordenadas(Coordenada coordenadas) {
        this.coordenadas = coordenadas;
    }
    */
    private static double semiverseno(double angulo){
        return Math.pow(Math.sin(angulo/((double)2)), 2);
    }
    
    public double distancia(Cliente cliente){
        double latCliente = Math.toRadians(cliente.getCoordenadas().getLat());
        double lngCliente = Math.toRadians(cliente.getCoordenadas().getLng());
        double latVendedor = Math.toRadians(coordenadas.getLat());
        double lngVendedor = Math.toRadians(coordenadas.getLng());
        return 2*RADIOTIERRA*Math.asin(Math.sqrt(Vendedor.semiverseno(latCliente-latVendedor)+Math.cos(latCliente)*Math.cos(latVendedor)*Vendedor.semiverseno(lngCliente-lngVendedor)));
    }
    
    public void agregarItemMenu(ItemMenu item){
        menu.add(item);
    }
    
    public void eliminarItemMenu(ItemMenu item){
        menu.remove(item);
    }
    
    public void agregarPedido(Pedido pedido){
        pedidos.add(pedido);
    }
    
    public void eliminarPedido(Pedido pedido){
        pedidos.remove(pedido);
    }
    
    public ArrayList<Bebida> listaBebidas(){
        Iterator<ItemMenu> i = menu.iterator();
        ArrayList<Bebida> bebidas = new ArrayList<Bebida>();
        while(i.hasNext()){
            ItemMenu item = i.next();
            if(item.esBebida()){
                bebidas.add((Bebida)item);
            }
        }
        return bebidas;
    }
    
    public ArrayList<Bebida> listaBebidasSinAlcohol(){
        Iterator<ItemMenu> i = menu.iterator();
        ArrayList<Bebida> bebidas = new ArrayList<Bebida>();
        while(i.hasNext()){
            ItemMenu item = i.next();
            if(item.esBebida()){
                Bebida bebida = (Bebida)item;
                if(!bebida.esAlcoholica()){
                    bebidas.add(bebida);
                }
            }
        }
        return bebidas;
    }
    
    public ArrayList<Plato> listaComidas(){
        Iterator<ItemMenu> i = menu.iterator();
        ArrayList<Plato> platos = new ArrayList<Plato>();
        while(i.hasNext()){
            ItemMenu item = i.next();
            if(item.esComida()){
                platos.add((Plato)item);
            }
        }
        return platos;
    }
    
    public ArrayList<Plato> listaComidasVeganas(){
        Iterator<ItemMenu> i = menu.iterator();
        ArrayList<Plato> platos = new ArrayList<Plato>();
        while(i.hasNext()){
            ItemMenu item = i.next();
            if(item.esComida()){
                Plato plato = (Plato)item;
                if(plato.aptoVegano()){
                    platos.add(plato);
                }
            }
        }
        return platos;
    }
    public ArrayList<Pedido> pedidosPorEstado(EstadoPedido estado){
        Iterator<Pedido> i = pedidos.iterator();
        ArrayList<Pedido> subPedidos = new ArrayList<Pedido>();
        while(i.hasNext()){
            Pedido p = i.next();
            if (p.getEstado() == estado){
                subPedidos.add(p);
            }
        }
        return subPedidos;
    }
    
    @Override
    public String toString() {
        return this.nombre;
    }
/*
    public boolean equals(Object o){
        Vendedor otro = (Vendedor) o;
        return id == otro.getId() &&
            nombre.equals(otro.getNombre()) &&
            direccion.equals((otro.getDireccion())) &&
            coordenadas.getLat() == otro.getCoordenadas().getLat() &&
            coordenadas.getLng() == otro.getCoordenadas().getLng();
    }
}

*/