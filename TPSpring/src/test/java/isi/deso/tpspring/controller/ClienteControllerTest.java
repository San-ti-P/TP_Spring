/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tpspring.controller;


import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import isi.deso.tpspring.controller.ClienteController;
import isi.deso.tpspring.dto.ClienteDTO;
import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.Coordenada;
import isi.deso.tpspring.service.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author santi
 */

public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private Model modelo;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListClientes() {
        List<Cliente> clientesSimulados = Arrays.asList(
            new Cliente(1, "Cliente 1", "Calle Falsa1 123"),
            new Cliente(2, "Cliente 2", "Calle Falsa2 123")
        );

        when(clienteService.getAllClientes()).thenReturn(clientesSimulados);

        String vista = clienteController.listClientes(modelo);

        verify(clienteService, times(1)).getAllClientes();
        verify(modelo, times(1)).addAttribute("clientes", clientesSimulados);
        assertThat(vista).isEqualTo("clientes");
    }
    
    @Test
    public void testListClientesEmpty() {
        List<Cliente> clientesSimulados = Arrays.asList();

        when(clienteService.getAllClientes()).thenReturn(clientesSimulados);

        String vista = clienteController.listClientes(modelo);

        verify(clienteService, times(1)).getAllClientes();
        verify(modelo, times(1)).addAttribute("clientes", clientesSimulados);
        assertThat(vista).isEqualTo("clientes");
    }
    
    @Test
    public void testNewClienteForm(){
        String vista = clienteController.newClienteForm(modelo);
        verify(modelo, times(1)).addAttribute("clienteDTO", new ClienteDTO());
        assertThat(vista).isEqualTo("nuevo_cliente_form");
    }
    
    @Test
    public void testSaveCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Nombre Test");
        clienteDTO.setDireccion("Dirección Test");
        clienteDTO.setCuit("20-12345678-9");
        clienteDTO.setEmail("email@test.com");
        clienteDTO.setLat(123.45);
        clienteDTO.setLng(67.89);

        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setCuit(clienteDTO.getCuit());
        cliente.setEmail(clienteDTO.getEmail());

        Coordenada coordenadas = new Coordenada();
        coordenadas.setLat(clienteDTO.getLat());
        coordenadas.setLng(clienteDTO.getLng());

        cliente.setCoordenadas(coordenadas);
        
        
        String vista = clienteController.saveCliente(clienteDTO);

        verify(clienteService, times(1)).saveCliente(cliente);
        assertThat(vista).isEqualTo("redirect:/clientes");
    }
    
    @Test
    public void testFormularioEditar(){
        Cliente cliente = new Cliente(1, "Cliente 1", "Calle Falsa1 123");
        
        when(clienteService.getByIdCliente(1)).thenReturn(cliente);
        
        String vista = clienteController.formularioEditar(1, modelo);
        verify(modelo, times(1)).addAttribute("cliente", cliente);
        assertThat(vista).isEqualTo("editar_cliente");
    }
    
    @Test
    public void testUpdateCliente() {
        Coordenada coordenadas = new Coordenada();
        coordenadas.setLat(123.45);
        coordenadas.setLng(67.89);

        Cliente cliente_existente = new Cliente(1, "Nombre Test", "20-12345678-9", "email@test.com", "Dirección Test", coordenadas);

        Cliente cliente = new Cliente();
        cliente.setNombre("Cliente 1");
        cliente.setDireccion("Calle Falsa1 123");
        cliente.setCuit("20-12345678-9");
        cliente.setEmail("email@test.com");
        cliente.setCoordenadas(coordenadas);
        
        Cliente cliente_modificado = new Cliente(1, "Cliente 1", "20-12345678-9", "email@test.com", "Calle Falsa1 123", coordenadas);
        
        when(clienteService.getByIdCliente(1)).thenReturn(cliente_existente);
        
        String vista = clienteController.updateCliente(1, cliente, modelo);

        verify(clienteService, times(1)).updateCliente(cliente_modificado);
        assertThat(vista).isEqualTo("redirect:/clientes");
    }
    
    @Test
    public void testDeleteCliente(){

        String vista = clienteController.deleteCliente(1);
        
        verify(clienteService, times(1)).deleteCliente(1);
        assertThat(vista).isEqualTo("redirect:/clientes");
    }
    
}

