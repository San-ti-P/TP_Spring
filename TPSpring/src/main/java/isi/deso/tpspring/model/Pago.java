package isi.deso.tpspring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
//import java.util.ArrayList;
import java.util.Date;
//import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pago")
public class Pago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "monto_final")
    private double montoFinal;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    private Pedido pedido;

    @OneToOne
    @JoinColumn(name = "estrategia_de_pago_id")    
    private EstrategiaDePago estrategia;

    
    public Pago(Date fecha, Pedido pedido, EstrategiaDePago estrategia){
        this.fecha = fecha;
        this.estrategia = estrategia;
        this.setPedido(pedido);
        this.montoFinal = estrategia.precioFinal(pedido.getPrecio());
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", montoFinal=" + montoFinal +
                ", pedido=" + pedido +
                ", estrategia=" + estrategia +
                '}';
    }

    @Override
    public boolean equals(Object o){
        return id==(((Pago) o).getId());
    }
}
