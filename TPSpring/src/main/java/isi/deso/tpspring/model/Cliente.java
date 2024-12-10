/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tpspring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
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
@Table(name = "cliente")
public class Cliente implements PedidoObserver {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cuit")
    private String cuit;
    @Column(name = "email")
    private String email;
    @Column(name = "direccion")
    private String direccion;
    @OneToOne
    private Coordenada coordenadas;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();
    @Column(name = "activo")
    private boolean activo;
    
//    public Cliente(){
//        this.pedidos = new ArrayList<Pedido>(); 
//    }

    public Cliente(int id, String nombre, String cuit, String email, String direccion, Coordenada coordenadas) {
        this.id = id;
        this.nombre = nombre;
        this.cuit = cuit;
        this.email = email;
        this.direccion = direccion;
        this.coordenadas = coordenadas;
        this.pedidos = new ArrayList<>();
        this.activo = true;
    }

    public Cliente(int id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.activo = true;
    }

    public void agregarPedido(Pedido pedido){
        pedidos.add(pedido);
    }
    
    public void eliminarPedido(Pedido pedido){
        pedidos.remove(pedido);
    }
    
//    public int getId() {
//        return id;
//    }
//
//    public String getNombre(){
//        return nombre;
//    }
//    
//    public String getCuit() {
//        return cuit;
//    }
//    
//    public String getEmail() {
//        return email;
//    }
//    
//    public String getDireccion() {
//        return direccion;
//    }
//    
//    public Coordenada getCoordenadas() {
//        return coordenadas;
//    }
//
//    public boolean getActivo() {
//        return this.activo;
//    }
//
//    public void setActivo(boolean b) {
//        this.activo = true;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setNombre(String nombre){
//        this.nombre = nombre;
//    }
//
//    public void setCuit(String cuit) {
//        this.cuit = cuit;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setDireccion(String direccion) {
//        this.direccion = direccion;
//    }
//
//    public void setCoordenadas(Coordenada coordenadas) {
//        this.coordenadas = coordenadas;
//    }



    @Override
    public void update(Pedido p){
        if(p.getEstado() == EstadoPedido.EN_ENVIO){
        
            Scanner scanner = new Scanner(System.in); 
            System.out.println("Cliente 1: Como le gustaria pagar?: ");
            System.out.println("Las opciones son: TRANSFERENCIA (2% de recargo), MERCADOPAGO (4% de recargo)");
            String entradaMetodoPago = scanner.nextLine().toLowerCase().strip();
            EstrategiaDePago e; 
            
            if (entradaMetodoPago.equalsIgnoreCase("transferencia")) {
                EstrategiaTransferencia transferencia = new EstrategiaTransferencia(); 
                System.out.println("Ingrese cuit: ");
                String entradaCuit = scanner.nextLine().toLowerCase().strip();
                System.out.println("Ingrese cbu: ");
                String entradaCbu= scanner.nextLine().toLowerCase().strip();
                transferencia.setCuit(entradaCuit);
                transferencia.setCbu(entradaCbu); 
                e = transferencia;
            }
            else if (entradaMetodoPago.equalsIgnoreCase("mercadopago")) {
                EstrategiaMercadoPago mp = new EstrategiaMercadoPago(); 
                System.out.println("Ingrese alias: ");
                String entradaAlias = scanner.nextLine().toLowerCase().strip();
                mp.setAlias(entradaAlias);
                e = mp; 
            }
            else {
                System.out.println("Método de pago no soportado");
                return; 
            }
            Pago pago = new Pago(new Date(), p, e);
            //(new PagoSQL()).agregarPago(pago, entradaMetodoPago);
            p.setPago(pago);
        }
    }
//    @Override
//    public String toString() {
//        return this.nombre;
//    }

//    @Override
//    public boolean equals(Object o){
//        Cliente otro = (Cliente) o;
//        return id == otro.getId() &&
//                nombre.equals(otro.getNombre()) &&
//                cuit.equals(otro.getCuit()) &&
//                email.equals(otro.getEmail()) &&
//                direccion.equals((otro.getDireccion())) &&
//                coordenadas.getLat() == otro.getCoordenadas().getLat() &&
//                coordenadas.getLng() == otro.getCoordenadas().getLng();
//    }
}

