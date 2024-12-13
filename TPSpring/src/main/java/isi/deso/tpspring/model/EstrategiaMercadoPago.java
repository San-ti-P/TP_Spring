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
@Table(name = "estrategia_mercado_pago")
public class EstrategiaMercadoPago extends EstrategiaDePago {

    @Column(name = "alias", nullable = false)
    private String alias;
    private static final double RECARGO = 1.04;

    @Override
    public double precioFinal(double monto) {
        return monto * RECARGO;
    }
}
