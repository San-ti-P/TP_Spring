package isi.deso.tpspring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonBackReference
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ItemPedido> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @JsonBackReference
    private Vendedor vendedor;

    @OneToOne(optional = true, cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "pago_id", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @JsonManagedReference
    private Pago pago;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoPedido estado;

    @Column(name = "precio")
    private double precio = 0;

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
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", items=" + items +
                ", vendedor=" + vendedor +
                ", pago=" + pago +
                ", estado=" + estado +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o){
        return id==(((Pedido) o).getId());
    }
}