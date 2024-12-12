package isi.deso.tpspring.dto;

import isi.deso.tpspring.model.ItemMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PedidoDTO {
    private ClienteDTO clienteDTO;
    private VendedorDTO vendedorDTO;
    private List<ItemMenu> items;
    private Double subtotal;
}
