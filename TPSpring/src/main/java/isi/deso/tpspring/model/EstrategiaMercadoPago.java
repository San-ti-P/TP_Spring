/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tpspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "estrategia_mercado_pago")
@SequenceGenerator(name = "est_mp_seq", sequenceName = "est_mp_seq", allocationSize = 1)
public class EstrategiaMercadoPago implements EstrategiaDePago{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "est_mp_seq")
    private int id;
    @Column(name = "alias")
    private String alias;
    private static final double RECARGO = 1.04;

    @Override
    public double precioFinal(double monto) {
        return monto*RECARGO;
    }
}
