package isi.deso.tpspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ItemPedidoDTO {

    private Integer id;
    private Integer item;
    private String nombre;
    private Float precio;
    private Integer cantidad;

}
