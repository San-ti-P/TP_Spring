package isi.deso.tpspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido implements Observable, Comparable<Pedido> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido")
    private ArrayList<ItemPedido> items;
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor; 
    @OneToOne
    @JoinColumn(name = "pago_id")
    private Pago pago;
    //Que onda como seria la union con un enun averiguar
    private EstadoPedido estado;
    @Column(name = "precio")
    private double precio = 0;
    //REVISAR ESTO
    private ArrayList<PedidoObserver> observadores = new ArrayList<>();

    public Pedido(int id, Cliente cliente, ArrayList<ItemPedido> items, Vendedor vendedor, EstadoPedido estado) {
        this.id = id;
        this.cliente = cliente;
        this.items = items;
        this.vendedor = vendedor;
        this.estado = estado;
        this.calcularPrecio();
    }
   /*
    public double getPrecio() {
        return precio;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<ItemPedido> getItems() {
        return items;
    }

    public Vendedor getVendedor(){
        return vendedor;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    */
    public void setItems(ArrayList<ItemPedido> items) throws VendedoresDistintosException {
        if (validarVendedorUnico(items)) {
           this.items = items;
           this.calcularPrecio();
       }
       else {
           throw new VendedoresDistintosException("No hay unicidad de vendedores");
       }   
    }        
    
    private boolean validarVendedorUnico(ArrayList<ItemPedido> items) {
        HashSet<Vendedor> vendedores = new HashSet<>();
        for (ItemPedido item : items) {
            vendedores.add(item.getItem().getVendedor());
        }
        return vendedores.size() == 1;
    }
    /*
    public void setVendedor(Vendedor vendedor){
        this.vendedor = vendedor;
    }
    */
    private void calcularPrecio() {
        for (ItemPedido item : this.items) {
            this.precio = this.precio + (item.getItem().getPrecio() * item.getCantidad());
        }
    }
    
    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", cliente=" + cliente.getNombre() + ", items=" + items + ", vendedor=" + vendedor.getNombre() + '}';
    }
    
    @Override
    public void addObserver(PedidoObserver o){
        this.observadores.add(o);
    }
    
    @Override
    public void removeObserver(PedidoObserver o){
        this.observadores.remove(o); 
    }
    
    @Override
    public void notifyObservers(){
        Iterator<PedidoObserver> i = observadores.iterator();
        while(i.hasNext()){
            i.next().update(this); 
        }
    }
    
    @Override
    public int compareTo(Pedido p) {
        return this.id - p.getId();
    }
    /*
    @Override
    public boolean equals(Object o){
        Pedido otro = (Pedido) o;
        return id == otro.getId();
    }*/
}