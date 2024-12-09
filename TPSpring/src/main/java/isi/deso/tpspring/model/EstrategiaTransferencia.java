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
@Table(name = "estrategia_transferencia")
public class EstrategiaTransferencia extends EstrategiaDePago {
    
    @Column(name = "cuit")
    private String cuit;
    @Column(name = "cbu")
    private String cbu;
    private static final double RECARGO = 1.02;
    
    @Override
    public double precioFinal(double monto){
        return monto*RECARGO;
    }
}
