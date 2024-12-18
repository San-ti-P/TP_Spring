/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tpspring.controller;


import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import isi.deso.tpspring.dto.ItemPedidoDTO;
import isi.deso.tpspring.dto.PedidoDTO;
import isi.deso.tpspring.model.Bebida;
import isi.deso.tpspring.model.Categoria;
import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.Coordenada;
import isi.deso.tpspring.model.EstadoPedido;
import isi.deso.tpspring.model.EstrategiaDePago;
import isi.deso.tpspring.model.EstrategiaMercadoPago;
import isi.deso.tpspring.model.ItemMenu;
import isi.deso.tpspring.model.ItemPedido;
import isi.deso.tpspring.model.Pago;
import isi.deso.tpspring.model.Pedido;
import isi.deso.tpspring.model.Plato;
import isi.deso.tpspring.model.Vendedor;
import isi.deso.tpspring.model.VendedoresDistintosException;
import isi.deso.tpspring.service.ClienteService;
import isi.deso.tpspring.service.EstrategiaDePagoService;
import isi.deso.tpspring.service.ItemMenuService;
import isi.deso.tpspring.service.ItemPedidoService;
import isi.deso.tpspring.service.PagoService;
import isi.deso.tpspring.service.PedidoService;
import isi.deso.tpspring.service.VendedorService;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author santi
 */

public class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;
    @Mock
    private ClienteService clienteService;
    @Mock
    private VendedorService vendedorService;
    @Mock
    private ItemMenuService itemMenuService;
    @Mock
    private ItemPedidoService itemPedidoService;
    @Mock
    private PagoService pagoService;
    @Mock
    private EstrategiaDePagoService estrategiaDePagoService;
    
    @Mock
    private Model modelo;

    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListPedidos() {
        List<Pedido> pedidosSimulados = Arrays.asList(
            new Pedido(1, new Cliente(1, "Juan Perez", "20-12345678-9", "juan.perez@gmail.com", "Av. Siempreviva 742", new Coordenada(1, 12.0, 25.0)),
                new ArrayList<>(), 
                new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(2, 10.0, 20.0)), EstadoPedido.RECIBIDO),
            new Pedido(2, new Cliente(2, "Maria Lopez", "27-98765432-1", "maria.lopez@gmail.com", "Calle Falsa 123", new Coordenada(3, 15.0, 30.0)),
                new ArrayList<>(), 
                new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(4, 30.0, 40.0)), EstadoPedido.RECIBIDO)
        );

        when(pedidoService.getAllPedidos()).thenReturn(pedidosSimulados);

        String vista = pedidoController.listPedidos(modelo);

        verify(pedidoService, times(1)).getAllPedidos();
        verify(modelo, times(1)).addAttribute("pedidos", pedidosSimulados);
        assertThat(vista).isEqualTo("pedidos");
    }
    
    @Test
    public void testListPedidosEmpty() {
        List<Pedido> pedidosSimulados = Arrays.asList();

        when(pedidoService.getAllPedidos()).thenReturn(pedidosSimulados);

        String vista = pedidoController.listPedidos(modelo);

        verify(pedidoService, times(1)).getAllPedidos();
        verify(modelo, times(1)).addAttribute("pedidos", pedidosSimulados);
        assertThat(vista).isEqualTo("pedidos");
    }
    
    @Test
    public void testNewClienteFormSinIdVendedor(){
        List<Cliente> clientesSimulados = Arrays.asList(
            new Cliente(1, "Cliente 1", "Calle Falsa1 123"),
            new Cliente(2, "Cliente 2", "Calle Falsa2 123")
        );
        List<Vendedor> vendedoresSimulados = Arrays.asList(
            new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)),
            new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0))
        );
        List<ItemMenu> itemsSimulados = Arrays.asList(
                new Bebida(1, "Coca Cola", "Bebida gaseosa", 1.5f, Categoria.valueOf("gaseosas"), 
                0.0f, 500, true, new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0))),
                new Plato(4, "Ensalada Verde", "Ensalada fresca con lechuga, rúcula, espinaca y aderezo de oliva", 150, true,
                180.0f, 2.8f, Categoria.valueOf("verduras"), true, new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)))
        );
        List<ItemPedidoDTO> itemPedidoDTOSimulados = new ArrayList();
        List<String> mediosDePagoSimulados = new ArrayList<>();
        mediosDePagoSimulados.add("MERCADOPAGO");
        mediosDePagoSimulados.add("TRANSFERENCIA");
        
        PedidoDTO pedidoDTOSimulado = new PedidoDTO();
        pedidoDTOSimulado.setItems(itemPedidoDTOSimulados);
        
        when(clienteService.getAllClientes()).thenReturn(clientesSimulados);
        when(vendedorService.getAllVendedores()).thenReturn(vendedoresSimulados);
        when(vendedorService.getItemsMenuByVendedor(4)).thenReturn(itemsSimulados);
        
        String vista = pedidoController.newPedidoForm(null, modelo);
        
        verify(clienteService, times(1)).getAllClientes();
        verify(vendedorService, times(1)).getAllVendedores();
        verify(vendedorService, never()).getItemsMenuByVendedor(anyInt());
        verify(modelo, times(1)).addAttribute("pedidoDTO", pedidoDTOSimulado);
        verify(modelo, times(1)).addAttribute("clientes", clientesSimulados);
        verify(modelo, times(1)).addAttribute("vendedores", vendedoresSimulados);
        verify(modelo, times(1)).addAttribute("selectedVendedorId", null);
        verify(modelo, times(1)).addAttribute("estados", EstadoPedido.values());
        verify(modelo, times(1)).addAttribute("mediosdepago", mediosDePagoSimulados);
        assertThat(vista).isEqualTo("nuevo_pedido_form");
    }
    
    @Test
    public void testNewClienteFormConIdVendedor(){
        List<Cliente> clientesSimulados = Arrays.asList(
            new Cliente(1, "Cliente 1", "Calle Falsa1 123"),
            new Cliente(2, "Cliente 2", "Calle Falsa2 123")
        );
        List<Vendedor> vendedoresSimulados = Arrays.asList(
            new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)),
            new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0))
        );
        List<ItemMenu> itemsSimulados = Arrays.asList(
                new Bebida(1, "Coca Cola", "Bebida gaseosa", 1.5f, Categoria.valueOf("gaseosas"), 
                0.0f, 500, true, new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0))),
                new Plato(4, "Ensalada Verde", "Ensalada fresca con lechuga, rúcula, espinaca y aderezo de oliva", 150, true,
                180.0f, 2.8f, Categoria.valueOf("verduras"), true, new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)))
        );
        List<ItemPedidoDTO> itemPedidoDTOSimulados = new ArrayList();
        for (ItemMenu item : itemsSimulados) {
                ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
                itemPedidoDTO.setItem(item.getId());
                itemPedidoDTO.setNombre(item.getNombre());
                itemPedidoDTO.setPrecio(item.getPrecio());
                itemPedidoDTO.setCantidad(0);
                itemPedidoDTOSimulados.add(itemPedidoDTO);
        }
        List<String> mediosDePagoSimulados = new ArrayList<>();
        mediosDePagoSimulados.add("MERCADOPAGO");
        mediosDePagoSimulados.add("TRANSFERENCIA");
        
        PedidoDTO pedidoDTOSimulado = new PedidoDTO();
        pedidoDTOSimulado.setItems(itemPedidoDTOSimulados);
        
        when(clienteService.getAllClientes()).thenReturn(clientesSimulados);
        when(vendedorService.getAllVendedores()).thenReturn(vendedoresSimulados);
        when(vendedorService.getItemsMenuByVendedor(4)).thenReturn(itemsSimulados);
        
        String vista = pedidoController.newPedidoForm(4, modelo);
        
        verify(clienteService, times(1)).getAllClientes();
        verify(vendedorService, times(1)).getAllVendedores();
        verify(vendedorService, times(1)).getItemsMenuByVendedor(4);
        verify(modelo, times(1)).addAttribute("pedidoDTO", pedidoDTOSimulado);
        verify(modelo, times(1)).addAttribute("clientes", clientesSimulados);
        verify(modelo, times(1)).addAttribute("vendedores", vendedoresSimulados);
        verify(modelo, times(1)).addAttribute("selectedVendedorId", 4);
        verify(modelo, times(1)).addAttribute("estados", EstadoPedido.values());
        verify(modelo, times(1)).addAttribute("mediosdepago", mediosDePagoSimulados);
        assertThat(vista).isEqualTo("nuevo_pedido_form");
    }
//    
//    @Test
//    public void testSavePedido() throws VendedoresDistintosException {
//        Vendedor vendedor = new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0));
//        Cliente cliente = new Cliente(1, "Cliente 1", "Calle Falsa1 123");
//        PedidoDTO pedidoDTO = new PedidoDTO();
//        pedidoDTO.setVendedor(vendedor);
//        pedidoDTO.setCliente(cliente);
//        ArrayList<ItemPedidoDTO> items = new ArrayList();
//        items.add(new ItemPedidoDTO(null, 1, "Coca Cola", 1.5f, 2));
//        items.add(new ItemPedidoDTO(null, 4, "Ensalada Verde", 2.8f, 0));
//        pedidoDTO.setItems(items);
//        pedidoDTO.setEstado(EstadoPedido.RECIBIDO);
//        pedidoDTO.setMedioDePago("MERCADOPAGO");
//        pedidoDTO.setAlias("alias.de.prueba1");
//
//        EstrategiaMercadoPago nueva = new EstrategiaMercadoPago("alias.de.prueba1");
//        nueva.setId(1);
//        Pedido pedido = new Pedido();
//        pedido.setCliente(cliente);
//        pedido.setVendedor(vendedor);
//        pedido.setEstado(EstadoPedido.RECIBIDO);
//        double subtotal = 0.0;
//        for (ItemPedidoDTO i : pedidoDTO.getItems()) subtotal += i.getCantidad() * i.getPrecio();
//
//        pedido.setPrecio(subtotal);
//
//        Pedido pedidoConID = new Pedido(1, cliente, Arrays.asList(), vendedor, EstadoPedido.RECIBIDO);
//        pedidoConID.setPrecio(3.0);
//        pedidoConID.setItems(new ArrayList<>());
//        System.out.println(pedidoConID);
//        Pago p = new Pago(new Date(), pedido, (EstrategiaDePago) nueva);
//        Pago pConID = new Pago(1, new Date(), 3.0, pedido, (EstrategiaDePago) nueva);
//        pedido.setPago(p);
//        ItemMenu coca = new Bebida(1, "Coca Cola", "Bebida gaseosa", 1.5f, Categoria.valueOf("gaseosas"),
//                0.0f, 500, true, new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)));
//        ItemMenu ensalada = new Plato(4, "Ensalada Verde", "Ensalada fresca con lechuga, rúcula, espinaca y aderezo de oliva", 150, true,
//                180.0f, 2.8f, Categoria.valueOf("verduras"), true, new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)));
//        ItemPedido itemPedidoCoca = new ItemPedido();
//        itemPedidoCoca.setItem(coca);
//        itemPedidoCoca.setCantidad(2);
//        itemPedidoCoca.setPedido(pedidoConID);
//        ItemPedido itemPedidoEnsalada = new ItemPedido();
//        itemPedidoEnsalada.setItem(ensalada);
//        itemPedidoEnsalada.setCantidad(0);
//        itemPedidoEnsalada.setPedido(pedidoConID);
//        ItemPedido itemPedidoCocaConID = new ItemPedido(1, 2, coca, pedidoConID);
//        ItemPedido itemPedidoEnsaladaConID = new ItemPedido(2, 0, ensalada, pedidoConID);
//
//        when(clienteService.getByIdCliente(1)).thenReturn(cliente);
//        when(vendedorService.getByIdVendedor(4)).thenReturn(vendedor);
//        when(estrategiaDePagoService.saveEstrategiaDePago((EstrategiaDePago) (new EstrategiaMercadoPago("alias.de.prueba1")))).thenReturn((EstrategiaDePago)nueva);
//        when(pagoService.savePago(p)).thenReturn(pConID);
//        when(pedidoService.savePedido(pedido)).thenReturn(pedidoConID);
//        when(itemMenuService.getItemMenuById(1)).thenReturn(coca);
//        when(itemMenuService.getItemMenuById(4)).thenReturn(ensalada);
//        when(itemPedidoService.saveItemPedido(itemPedidoCoca)).thenReturn(itemPedidoCocaConID);
//        when(itemPedidoService.saveItemPedido(itemPedidoEnsalada)).thenReturn(itemPedidoEnsaladaConID);
//
//        String vista = pedidoController.savePedido(pedidoDTO);
//
//        verify(clienteService, times(1)).getByIdCliente(1);
//        verify(vendedorService, times(1)).getByIdVendedor(4);
//        verify(estrategiaDePagoService, times(1)).saveEstrategiaDePago((EstrategiaDePago) (new EstrategiaMercadoPago("alias.de.prueba1")));
//        verify(pagoService, times(1)).savePago(p);
//        verify(pedidoService, times(1)).savePedido(pedido);
//        verify(itemMenuService, times(1)).getItemMenuById(1);
//        verify(itemPedidoService, times(1)).saveItemPedido(itemPedidoCoca);
//
//        assertThat(vista).isEqualTo("redirect:/pedidos");
//    }
    
//    @Test
//    public void testUpdatePedido() throws VendedoresDistintosException {
//
//
//        Vendedor vendedor = new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0));
//        Cliente cliente = new Cliente(1, "Cliente 1", "Calle Falsa1 123");
//
//
//
//        EstrategiaMercadoPago nueva = new EstrategiaMercadoPago("alias.de.prueba1");
//        nueva.setId(1);
//        Pedido pedidoExistente = new Pedido(1, cliente, new ArrayList<>(), vendedor, null, EstadoPedido.RECIBIDO, 3.12);
//
//        Pago pagoExistente = new Pago(1, new Date(), 3.0, pedidoExistente, (EstrategiaDePago) nueva);
//        pedidoExistente.setPago(pagoExistente);
//
//
//        ItemMenu coca = new Bebida(1, "Coca Cola", "Bebida gaseosa", 1.5f, Categoria.valueOf("gaseosas"),
//                0.0f, 500, true, new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)));
//        ItemMenu ensalada = new Plato(4, "Ensalada Verde", "Ensalada fresca con lechuga, rúcula, espinaca y aderezo de oliva", 150, true,
//                180.0f, 2.8f, Categoria.valueOf("verduras"), true, new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)));
//        ItemPedido itemPedidoCoca = new ItemPedido();
//        itemPedidoCoca.setItem(coca);
//        itemPedidoCoca.setCantidad(2);
//        ItemPedido itemPedidoEnsalada = new ItemPedido();
//        itemPedidoEnsalada.setItem(ensalada);
//        itemPedidoEnsalada.setCantidad(0);
//        ItemPedido itemPedidoCocaConID = new ItemPedido(1, 2, coca, pedidoExistente);
//        ArrayList<ItemPedido> itemsExistentes = new ArrayList<>();
//        itemsExistentes.add(itemPedidoCocaConID);
//        pedidoExistente.setItems(itemsExistentes);
//
//        PedidoDTO pedidoDTO = new PedidoDTO();
//        pedidoDTO.setVendedor(vendedor);
//        pedidoDTO.setCliente(cliente);
//        ArrayList<ItemPedidoDTO> items = new ArrayList();
//        items.add(new ItemPedidoDTO(1, 1, "Coca Cola", 1.5f, 2));
//        items.add(new ItemPedidoDTO(-1, 4, "Ensalada Verde", 2.8f, 2));
//        pedidoDTO.setItems(items);
//        pedidoDTO.setEstado(EstadoPedido.RECIBIDO);
//        pedidoDTO.setMedioDePago("MERCADOPAGO");
//        pedidoDTO.setAlias("alias.de.prueba1");
//        pedidoDTO.setSubtotal(8.6);
//
//
//        Pedido pedidoActualizado = new Pedido(1, cliente, new ArrayList<>(), vendedor, null, EstadoPedido.RECIBIDO, 8.944);
//        Pago pagoActualizado = new Pago(1, new Date(), 8.6, pedidoActualizado, (EstrategiaDePago) nueva);
//        pedidoActualizado.setPago(pagoActualizado);
//        ArrayList<ItemPedido> itemsActualizados = new ArrayList<>();
//        ItemPedido itemPedidoCocaActualizado = new ItemPedido(1, 2, coca, pedidoActualizado);
//        ItemPedido itemPedidoEnsaladaActualizado = new ItemPedido(2, 2, ensalada, pedidoActualizado);
//        itemsActualizados.add(itemPedidoCocaActualizado);
//        itemsActualizados.add(itemPedidoEnsaladaActualizado);
//        pedidoActualizado.setItems(itemsActualizados);
//
//        when(pedidoService.getByIdPedido(1)).thenReturn(pedidoExistente);
//        when(clienteService.getByIdCliente(1)).thenReturn(cliente);
//        when(vendedorService.getByIdVendedor(4)).thenReturn(vendedor);
//        //when(itemPedidoService.getByIdItemPedido())
//        when(estrategiaDePagoService.saveEstrategiaDePago((EstrategiaDePago) (new EstrategiaMercadoPago("alias.de.prueba1")))).thenReturn((EstrategiaDePago)nueva);
//        when(pagoService.updatePago(pagoActualizado)).thenReturn(pagoActualizado);
//        when(pedidoService.updatePedido(pedidoActualizado)).thenReturn(pedidoActualizado);
//        when(itemMenuService.getItemMenuById(1)).thenReturn(coca);
//        when(itemMenuService.getItemMenuById(4)).thenReturn(ensalada);
//        when(itemPedidoService.getByIdItemPedido(1)).thenReturn(itemPedidoCocaConID);
//        when(itemPedidoService.saveItemPedido(itemPedidoEnsalada)).thenReturn(itemPedidoEnsaladaActualizado);
//
//        String vista = pedidoController.updatePedido(1, pedidoDTO);
//
//        verify(clienteService, times(1)).getByIdCliente(1);
//        verify(vendedorService, times(1)).getByIdVendedor(4);
//        verify(estrategiaDePagoService, times(1)).saveEstrategiaDePago((EstrategiaDePago) (new EstrategiaMercadoPago("alias.de.prueba1")));
//        verify(pagoService, times(1)).updatePago(pagoActualizado);
//        verify(pedidoService, times(1)).updatePedido(pedidoActualizado);
//        verify(itemMenuService, times(1)).getItemMenuById(1);
//        verify(itemPedidoService, times(1)).saveItemPedido(new ItemPedido(null, 2, ensalada, pedidoActualizado));
//
//        assertThat(vista).isEqualTo("redirect:/pedidos");
//    }
    
    @Test
    public void testGetItemsVendedor(){
        Vendedor vendedor = new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0));

        List<ItemMenu> itemsSimulados = Arrays.asList(
                new Bebida(1, "Coca Cola", "Bebida gaseosa", 1.5f, Categoria.valueOf("gaseosas"), 
                0.0f, 500, true, vendedor),
                new Plato(4, "Ensalada Verde", "Ensalada fresca con lechuga, rúcula, espinaca y aderezo de oliva", 150, true,
                180.0f, 2.8f, Categoria.valueOf("verduras"), true, vendedor)
        );
        
        ArrayList<ItemPedidoDTO> itemsPedidoDTO = new ArrayList();
        for (ItemMenu i : itemsSimulados){
            ItemPedidoDTO nuevo = new ItemPedidoDTO(-1, i.getId(), i.getNombre(), i.getPrecio(), 0);
            itemsPedidoDTO.add(nuevo);
        }
        when(vendedorService.getItemsMenuByVendedor(4)).thenReturn(itemsSimulados);
        
        assertThat(pedidoController.getItemsVendedor(4)).isEqualTo(itemsPedidoDTO);
    }
    
    @Test
    public void testViewPedidoItems() throws VendedoresDistintosException{
        Vendedor vendedor = new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0));
        Cliente cliente = new Cliente(1, "Cliente 1", "Calle Falsa1 123");

        List<ItemMenu> itemsSimulados = Arrays.asList(
                new Bebida(1, "Coca Cola", "Bebida gaseosa", 1.5f, Categoria.valueOf("gaseosas"), 
                0.0f, 500, true, vendedor),
                new Plato(4, "Ensalada Verde", "Ensalada fresca con lechuga, rúcula, espinaca y aderezo de oliva", 150, true,
                180.0f, 2.8f, Categoria.valueOf("verduras"), true, vendedor)
        );
        List<ItemPedido> itemsPedido = new ArrayList<>();
        List<ItemPedidoDTO> itemPedidoDTOSimulados = new ArrayList();
        
        Pedido pedidoConID = new Pedido(1, cliente, Arrays.asList(), vendedor, EstadoPedido.RECIBIDO);
        
        int i = 1;
        for (ItemMenu item : itemsSimulados) {
                ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
                itemPedidoDTO.setId(i);
                itemPedidoDTO.setItem(item.getId());
                itemPedidoDTO.setNombre(item.getNombre());
                itemPedidoDTO.setPrecio(item.getPrecio());
                itemPedidoDTO.setCantidad(1);
                itemPedidoDTOSimulados.add(itemPedidoDTO);
                ItemPedido itemPedido = new ItemPedido(i++, 1, item, pedidoConID);
                itemsPedido.add(itemPedido);
        }
        
        EstrategiaMercadoPago nueva = new EstrategiaMercadoPago("alias.de.prueba1");
        nueva.setId(1);
        
        pedidoConID.setPrecio(3.0);
        pedidoConID.setItems(itemsPedido);
        
        when(pedidoService.getByIdPedido(1)).thenReturn(pedidoConID);
        
        String vista = pedidoController.viewPedidoItems(1, modelo);
        
        verify(modelo, times(1)).addAttribute("items", itemPedidoDTOSimulados);
        assertThat(vista).isEqualTo("pedido_items");
    }
    
    @Test
    public void testDeletePedido(){

        String vista = pedidoController.deletePedido(1);
        
        verify(pedidoService, times(1)).deletePedido(1);
        assertThat(vista).isEqualTo("redirect:/pedidos");
    }
    
}