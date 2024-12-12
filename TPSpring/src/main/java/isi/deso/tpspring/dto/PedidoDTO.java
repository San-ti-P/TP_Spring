package isi.deso.tpspring.dto;

import isi.deso.tpspring.model.ItemPedido;
import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.Vendedor;
import isi.deso.tpspring.model.Pago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Integer id;
    private Integer cliente;
    private Integer vendedor;
    private ArrayList<ItemPedido> items;
    private Pago pago;
    private double precio; // Subtotal + porcentaje de la estrategia de pago
    
    public ArrayList<ItemPedido> getItems(){
        return items;
    }
}