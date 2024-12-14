/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tpspring.controller;


import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import isi.deso.tpspring.dto.VendedorDTO;
import isi.deso.tpspring.model.Coordenada;
import isi.deso.tpspring.model.Vendedor;
import isi.deso.tpspring.service.VendedorService;

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

public class VendedorControllerTest {

    @Mock
    private VendedorService vendedorService;

    @Mock
    private Model modelo;

    @InjectMocks
    private VendedorController vendedorController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListVendedores() {
        List<Vendedor> vendedoresSimulados = Arrays.asList(
            new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(2, 10.0, 20.0)),
            new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(4, 30.0, 40.0))
        );

        when(vendedorService.getAllVendedores()).thenReturn(vendedoresSimulados);

        String vista = vendedorController.listVendedores(modelo);

        verify(vendedorService, times(1)).getAllVendedores();
        verify(modelo, times(1)).addAttribute("vendedores", vendedoresSimulados);
        assertThat(vista).isEqualTo("vendedores");
    }
    
    @Test
    public void testListVendedoresEmpty() {
        List<Vendedor> vendedoresSimulados = Arrays.asList();

        when(vendedorService.getAllVendedores()).thenReturn(vendedoresSimulados);

        String vista = vendedorController.listVendedores(modelo);

        verify(vendedorService, times(1)).getAllVendedores();
        verify(modelo, times(1)).addAttribute("vendedores", vendedoresSimulados);
        assertThat(vista).isEqualTo("vendedores");
    }
    
    @Test
    public void testNewVendedorForm(){
        String vista = vendedorController.newVendedorForm(modelo);
        verify(modelo, times(1)).addAttribute("vendedorDTO", new VendedorDTO());
        assertThat(vista).isEqualTo("nuevo_vendedor_form");
    }
    
    @Test
    public void testSaveVendedor() {
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNombre("Nombre Test");
        vendedorDTO.setDireccion("Dirección Test");
        vendedorDTO.setLat(123.45);
        vendedorDTO.setLng(67.89);

        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(vendedorDTO.getNombre());
        vendedor.setDireccion(vendedorDTO.getDireccion());

        Coordenada coordenadas = new Coordenada();
        coordenadas.setLat(vendedorDTO.getLat());
        coordenadas.setLng(vendedorDTO.getLng());

        vendedor.setCoordenadas(coordenadas);
        
        
        String vista = vendedorController.saveVendedor(vendedorDTO);

        verify(vendedorService, times(1)).saveVendedor(vendedor);
        assertThat(vista).isEqualTo("redirect:/vendedores");
    }
    
    @Test
    public void testFormularioEditar(){
        Vendedor vendedor = new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(2, 10.0, 20.0));
        
        when(vendedorService.getByIdVendedor(4)).thenReturn(vendedor);
        
        String vista = vendedorController.formularioEditar(4, modelo);
        verify(modelo, times(1)).addAttribute("vendedor", vendedor);
        assertThat(vista).isEqualTo("editar_vendedor");
    }
    
    @Test
    public void testUpdateVendedor() {
        Coordenada coordenadas = new Coordenada(2, 10.0, 20.0);

        Vendedor vendedor_existente = new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423",  coordenadas);

        Coordenada coordenadasNuevas = new Coordenada(null, 11.0, 18.0);
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre("Lo de Nestor");
        vendedor.setDireccion("San Martin 355");
        vendedor.setCoordenadas(coordenadasNuevas);
        Coordenada coordenadasNuevasConID = new Coordenada(2, 11.0, 18.0);
        Vendedor vendedor_modificado = new Vendedor(4, "Lo de Nestor", "San Martin 355",  coordenadasNuevasConID);
        
        when(vendedorService.getByIdVendedor(4)).thenReturn(vendedor_existente);
        
        String vista = vendedorController.updateVendedor(4, vendedor, modelo);

        verify(vendedorService, times(1)).updateVendedor(vendedor_modificado);
        assertThat(vista).isEqualTo("redirect:/vendedores");
    }
    
    @Test
    public void testDeleteVendedor(){

        String vista = vendedorController.deleteVendedor(5);
        
        verify(vendedorService, times(1)).deleteVendedor(5);
        assertThat(vista).isEqualTo("redirect:/vendedores");
    }
    
}

