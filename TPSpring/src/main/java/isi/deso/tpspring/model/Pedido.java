package isi.deso.tpspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido implements Observable/*, Comparable<Pedido>*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemPedido> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

//    @OneToOne
//    @JoinColumn(name = "pago_id")
//    private Pago pago;

    @OneToOne(optional = true, cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "pago_id", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Pago pago;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoPedido estado;

    @Column(name = "precio")
    private double precio = 0;

    // No se almacena en la BD
    @Transient
    private List<PedidoObserver> observadores = new ArrayList<>();

    public Pedido(int id, Cliente cliente, List<ItemPedido> items, Vendedor vendedor, EstadoPedido estado) {
        this.id = id;
        this.cliente = cliente;
        this.items = items;
        this.vendedor = vendedor;
        this.estado = estado;
        this.calcularPrecio();
    }

    public void setItems(List<ItemPedido> items) throws VendedoresDistintosException {
        if (validarVendedorUnico(items)) {
            this.items = items;
            this.calcularPrecio();
        } else {
            throw new VendedoresDistintosException("No hay unicidad de vendedores");
        }
    }

    private boolean validarVendedorUnico(List<ItemPedido> items) {
        if(items.size()>0){
            Integer primer_id = items.getFirst().getItem().getVendedor().getId();
            for (ItemPedido item : items) {
                if (!(primer_id.equals(item.getItem().getVendedor().getId()))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void calcularPrecio() {
        this.precio = 0;
        for (ItemPedido item : this.items) {
            this.precio += item.getItem().getPrecio() * item.getCantidad();
        }
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", cliente=" + cliente.toString() + ", items=" + items + ", vendedor=" + vendedor.toString() + '}';
    }

    @Override
    public void addObserver(PedidoObserver o) {
        this.observadores.add(o);
    }

    @Override
    public void removeObserver(PedidoObserver o) {
        this.observadores.remove(o);
    }

    @Override
    public void notifyObservers() {
        Iterator<PedidoObserver> i = observadores.iterator();
        while (i.hasNext()) {
            i.next().update(this);
        }
    }

//    @Override
//    public int compareTo(Pedido p) {
//        return this.id - p.getId();
//    }
    
    @Override
    public boolean equals(Object o){
        return id==(((Pedido) o).getId());
    }
}