package isi.deso.tpspring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "estrategia_de_pago")
public abstract class EstrategiaDePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public abstract double precioFinal(double monto);
    public abstract boolean esMercadoPago();
    public abstract boolean esTransferencia();
}

