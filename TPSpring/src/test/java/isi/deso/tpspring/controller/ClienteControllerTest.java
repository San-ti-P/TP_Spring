/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tpspring.controller;


import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import isi.deso.tpspring.controller.ClienteController;
import isi.deso.tpspring.model.Cliente;
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
    public void testSaveCliente() {
//        List<Cliente> clientesSimulados = Arrays.asList();
//
//        when(clienteService.getAllClientes()).thenReturn(clientesSimulados);
//
//        String vista = clienteController.listClientes(modelo);
//
//        verify(clienteService, times(1)).getAllClientes();
//        verify(modelo, times(1)).addAttribute("clientes", clientesSimulados);
//        assertThat(vista).isEqualTo("clientes");
    }
    
}

