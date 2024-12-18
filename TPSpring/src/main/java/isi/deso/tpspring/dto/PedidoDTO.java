package isi.deso.tpspring.dto;

import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.EstadoPedido;
import isi.deso.tpspring.model.Vendedor;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PedidoDTO {
    private Integer id;
//    private Vendedor vendedor;
//    private Cliente cliente;
    private Integer idVendedor;
    private Integer idCliente;
    private List<ItemPedidoDTO> items = new ArrayList<>();
    private EstadoPedido estado;
    private String medioDePago;
    private String alias;
    private String cbu;
    private String cuit;
    private Double total;
    private Double subtotal;
}