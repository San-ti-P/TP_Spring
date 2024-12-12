package isi.deso.tpspring.dto;

import isi.deso.tpspring.model.ItemPedido;
import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.Vendedor;
import isi.deso.tpspring.model.Pago;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private Vendedor vendedor;
    private Cliente cliente;
    private List<ItemPedidoDTO> items = new ArrayList<>(); // Lista inicializada
    private BigDecimal total;

    // Getters y Setters
    public List<ItemPedidoDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoDTO> items) {
        this.items = items;
    }
}