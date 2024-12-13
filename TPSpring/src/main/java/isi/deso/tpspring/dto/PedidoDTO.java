package isi.deso.tpspring.dto;

import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.EstadoPedido;
import isi.deso.tpspring.model.Vendedor;
import java.math.BigDecimal;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PedidoDTO {
    private Long id;
    private Vendedor vendedor;
    private Cliente cliente;
    private List<ItemPedidoDTO> items = new ArrayList<>();
    private BigDecimal total;
    private EstadoPedido estado;
    private String medioDePago;
    private String alias;
    private String cbu;
    private String cuit;
}