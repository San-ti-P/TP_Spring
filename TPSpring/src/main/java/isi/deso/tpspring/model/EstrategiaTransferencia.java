package isi.deso.tpspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estrategia_transferencia")
public class EstrategiaTransferencia extends EstrategiaDePago {

    @Column(name = "cuit", nullable = false, unique = true)
    private String cuit;

    @Column(name = "cbu", nullable = false, unique = true)
    private String cbu;
    private static final double RECARGO = 1.02;

    @Override
    public double precioFinal(double monto) {
        return monto * RECARGO;
    }
}
