package isi.deso.tpspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estrategia_mercado_pago")
public class EstrategiaMercadoPago extends EstrategiaDePago {

    private static final double RECARGO = 1.04;

    @Column(name = "alias", nullable = false)
    private String alias;

    @Override
    public double precioFinal(double monto) {
        return monto * RECARGO;
    }

    @Override
    public boolean esMercadoPago() {
        return true;
    }

    @Override
    public boolean esTransferencia() {
        return false;
    }
}
