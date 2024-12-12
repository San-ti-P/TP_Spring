package isi.deso.tpspring.dto;

import isi.deso.tpspring.model.TipoItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemMenuDTO {
    private TipoItem tipo;
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
    private int tamanio; // Solo para Bebida
}