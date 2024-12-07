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
@Table(name = "coordenada")
@SequenceGenerator(name = "coordenada_seq", sequenceName = "coordenada_seq", allocationSize = 1)
public class Coordenada {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_seq")
    private int id;
    @Column(name = "lat")
    private double lat;
    @Column(name = "lng")
    private double lng;
}
