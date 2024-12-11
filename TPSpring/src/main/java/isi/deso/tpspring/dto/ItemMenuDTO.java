package isi.deso.tpspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemMenuDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private float precio;
    private boolean aptoVegano;
    private Integer categoriaId;
    private Integer vendedorId;
    private Integer calorias; // Solo para Plato
    private boolean aptoCeliaco; // Solo para Plato
    private float peso; // Solo para Plato
    private float graduacionAlcoholica; // Solo para Bebida
    private int tamaño; // Solo para Bebida
}