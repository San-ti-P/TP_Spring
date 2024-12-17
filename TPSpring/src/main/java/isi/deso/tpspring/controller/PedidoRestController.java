package isi.deso.tpspring.controller;

import isi.deso.tpspring.dto.ItemPedidoDTO;
import isi.deso.tpspring.dto.PedidoDTO;
import isi.deso.tpspring.model.*;
import isi.deso.tpspring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PedidoRestController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VendedorService vendedorService;

    @Autowired
    private ItemMenuService itemMenuService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private PagoService pagoService;

    @Autowired
    private EstrategiaDePagoService estrategiaDePagoService;

    @GetMapping("/pedidos/api")
    @ResponseBody
    public ResponseEntity<List<Pedido>> getAllPedidosApi() {
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @GetMapping("/pedidos/api/{id}")
    @ResponseBody
    public ResponseEntity<Pedido> getPedidoByIdApi(@PathVariable Integer id) {
        Pedido pedido = pedidoService.getByIdPedido(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/pedidos/api")
    @ResponseBody
    public ResponseEntity<Pedido> savePedidoApi(@RequestBody PedidoDTO pedidoDTO) throws VendedoresDistintosException {
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteService.getByIdCliente(pedidoDTO.getCliente().getId()));
        pedido.setVendedor(vendedorService.getByIdVendedor(pedidoDTO.getVendedor().getId()));
        pedido.setEstado(pedidoDTO.getEstado());

        List<ItemPedido> items = new ArrayList<>();
        double subtotal = 0.0;
        for (ItemPedidoDTO i : pedidoDTO.getItems()) subtotal += i.getCantidad() * i.getPrecio();

        pedido.setPrecio(subtotal);
        EstrategiaDePago estrategiaDePago;
        if (pedidoDTO.getMedioDePago().equalsIgnoreCase("mercadopago")) {
            EstrategiaMercadoPago nueva = new EstrategiaMercadoPago();
            nueva.setAlias(pedidoDTO.getAlias());
            estrategiaDePago = nueva;
        } else {
            EstrategiaTransferencia nueva = new EstrategiaTransferencia();
            nueva.setCbu(pedidoDTO.getCbu());
            nueva.setCuit(pedidoDTO.getCuit());
            estrategiaDePago = nueva;
        }
        estrategiaDePago = estrategiaDePagoService.saveEstrategiaDePago(estrategiaDePago);
        Pago p = new Pago(new Date(), pedido, estrategiaDePago);
        pagoService.savePago(p);
        pedido.setPago(p);
        pedido = pedidoService.savePedido(pedido);

        for (ItemPedidoDTO i : pedidoDTO.getItems()) {
            if (i.getCantidad() != 0) {
                ItemPedido nuevo = new ItemPedido();
                nuevo.setItem(itemMenuService.getItemMenuById(i.getItem()));
                nuevo.setCantidad(i.getCantidad());
                nuevo.setPedido(pedido);
                itemPedidoService.saveItemPedido(nuevo);
                items.add(nuevo);
            }
        }
        pedido.setItems(items);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/pedidos/api/{id}")
    @ResponseBody
    public ResponseEntity<Pedido> updatePedidoApi(@PathVariable Integer id, @RequestBody PedidoDTO pedidoDTO) throws VendedoresDistintosException {
        Pedido pedidoExistente = pedidoService.getByIdPedido(id);

        Vendedor vendedorSeleccionado = vendedorService.getByIdVendedor(pedidoDTO.getVendedor().getId());
        pedidoExistente.setVendedor(vendedorSeleccionado);

        pedidoExistente.setCliente(clienteService.getByIdCliente(pedidoDTO.getCliente().getId()));
        pedidoExistente.setEstado(pedidoDTO.getEstado());
        pedidoExistente.setPrecio(pedidoDTO.getSubtotal());

        pedidoExistente.getItems().clear();

        for (ItemPedidoDTO itemDTO : pedidoDTO.getItems()) {
            if (itemDTO.getCantidad() > 0) {
                ItemPedido item;
                if (itemDTO.getId() != -1) {
                    item = itemPedidoService.getByIdItemPedido(itemDTO.getId());
                } else {
                    item = new ItemPedido(null, itemDTO.getCantidad(), itemMenuService.getItemMenuById(itemDTO.getItem()), pedidoExistente);
                    itemPedidoService.saveItemPedido(item);
                }

                ItemMenu itemMenu = itemMenuService.getItemMenuById(itemDTO.getItem());

                if (!(itemMenu.getVendedor().getId() == (vendedorSeleccionado.getId()))) {
                    throw new VendedoresDistintosException("El ítem no pertenece al vendedor seleccionado");
                }

                item.setItem(itemMenu);
                item.setCantidad(itemDTO.getCantidad());
                item.setPedido(pedidoExistente);
                pedidoExistente.getItems().add(item);
            }
        }

        EstrategiaDePago estrategia;
        Pago existingPago = pedidoExistente.getPago();

        if (pedidoDTO.getMedioDePago().equalsIgnoreCase("MERCADOPAGO")) {
            if (existingPago != null && existingPago.getEstrategia().esMercadoPago()) {
                EstrategiaMercadoPago mercadoPago = (EstrategiaMercadoPago) existingPago.getEstrategia();
                mercadoPago.setAlias(pedidoDTO.getAlias());
                estrategia = estrategiaDePagoService.saveEstrategiaDePago(mercadoPago);
            } else {
                EstrategiaMercadoPago mercadoPago = new EstrategiaMercadoPago();
                mercadoPago.setAlias(pedidoDTO.getAlias());
                estrategia = estrategiaDePagoService.saveEstrategiaDePago(mercadoPago);
            }
        } else {
            if (existingPago != null && existingPago.getEstrategia().esTransferencia()) {
                EstrategiaTransferencia transferencia = (EstrategiaTransferencia) existingPago.getEstrategia();
                transferencia.setCbu(pedidoDTO.getCbu());
                transferencia.setCuit(pedidoDTO.getCuit());
                estrategia = estrategiaDePagoService.saveEstrategiaDePago(transferencia);
            } else {
                EstrategiaTransferencia transferencia = new EstrategiaTransferencia();
                transferencia.setCbu(pedidoDTO.getCbu());
                transferencia.setCuit(pedidoDTO.getCuit());
                estrategia = estrategiaDePagoService.saveEstrategiaDePago(transferencia);
            }
        }

        Pago pago;
        if (existingPago != null) {
            existingPago.setEstrategia(estrategia);
            pago = pagoService.updatePago(existingPago);
        } else {
            pago = new Pago(new Date(), pedidoExistente, estrategia);
            pago = pagoService.savePago(pago);
        }

        pedidoExistente.setPago(pago);
        pedidoService.updatePedido(pedidoExistente);

        return ResponseEntity.ok(pedidoExistente);
    }

    @DeleteMapping("/pedidos/api/{id}")
    @ResponseBody
    public ResponseEntity<String> deletePedidoApi(@PathVariable Integer id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.ok("Pedido eliminado correctamente");
    }
}