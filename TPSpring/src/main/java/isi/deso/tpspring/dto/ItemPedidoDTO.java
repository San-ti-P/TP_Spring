package isi.deso.tpspring.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPedidoDTO {

    private Integer item;
    private String nombre;
    private Float precio;
    private Integer cantidad;

}
