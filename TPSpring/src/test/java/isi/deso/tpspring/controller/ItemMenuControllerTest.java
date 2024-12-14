/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tpspring.controller;


import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import isi.deso.tpspring.controller.ClienteController;
import isi.deso.tpspring.dto.ClienteDTO;
import isi.deso.tpspring.dto.ItemMenuDTO;
import isi.deso.tpspring.model.Bebida;
import isi.deso.tpspring.model.Categoria;
import isi.deso.tpspring.model.Cliente;
import isi.deso.tpspring.model.Coordenada;
import isi.deso.tpspring.model.ItemMenu;
import isi.deso.tpspring.model.Plato;
import isi.deso.tpspring.model.TipoItem;
import isi.deso.tpspring.model.Vendedor;
import isi.deso.tpspring.service.CategoriaService;
import isi.deso.tpspring.service.ClienteService;
import isi.deso.tpspring.service.ItemMenuService;
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

public class ItemMenuControllerTest {

    @Mock
    private ItemMenuService itemMenuService;
    @Mock
    private CategoriaService categoriaService;
    @Mock
    private VendedorService vendedorService;
    
    @Mock
    private Model modelo;

    @InjectMocks
    private ItemMenuController itemMenuController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListItemMenu() {
        List<ItemMenu> itemsSimulados = Arrays.asList(
                new Bebida(1, "Coca Cola", "Bebida gaseosa", 1.5f, Categoria.valueOf("gaseosas"), 
                0.0f, 500, true, new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0))),
                new Plato(4, "Ensalada Verde", "Ensalada fresca con lechuga, rúcula, espinaca y aderezo de oliva", 150, true,
                180.0f, 2.8f, Categoria.valueOf("verduras"), true, new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)))
        );

        when(itemMenuService.getAllItemMenu()).thenReturn(itemsSimulados);

        String vista = itemMenuController.listItemsMenu(modelo);

        verify(itemMenuService, times(1)).getAllItemMenu();
        verify(modelo, times(1)).addAttribute("items", itemsSimulados);
        assertThat(vista).isEqualTo("items-menu");
    }
    
    @Test
    public void testListItemMenuEmpty() {
        List<ItemMenu> itemsSimulados = Arrays.asList();

        when(itemMenuService.getAllItemMenu()).thenReturn(itemsSimulados);

        String vista = itemMenuController.listItemsMenu(modelo);

        verify(itemMenuService, times(1)).getAllItemMenu();
        verify(modelo, times(1)).addAttribute("items", itemsSimulados);
        assertThat(vista).isEqualTo("items-menu");
    }
    
    @Test
    public void testNewItemMenuForm(){
        List<Categoria> categoriasSimuladas = Arrays.asList(
                Categoria.valueOf("gaseosas"),
                Categoria.valueOf("hamburguesas"),
                Categoria.valueOf("verduras")
        );
        List<Vendedor> vendedoresSimulados = Arrays.asList(
            new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)),
            new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0))
        );
        
        when(categoriaService.getAllCategorias()).thenReturn(categoriasSimuladas);
        when(vendedorService.getAllVendedores()).thenReturn(vendedoresSimulados);
        
        String vista = itemMenuController.newItemMenuForm(modelo);
        
        verify(modelo, times(1)).addAttribute("itemMenu", new ItemMenuDTO());
        verify(modelo, times(1)).addAttribute("categorias", categoriasSimuladas);
        verify(modelo, times(1)).addAttribute("vendedores", vendedoresSimulados);
        assertThat(vista).isEqualTo("nuevo_itemMenu_from_items");
    }
    
    @Test
    public void testSaveItemMenu() {
        Categoria categoria = Categoria.valueOf("hamburguesas");
        Vendedor vendedor = new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0));
        
        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        itemMenuDTO.setTipo(TipoItem.PLATO);
        itemMenuDTO.setCalorias(600);
        itemMenuDTO.setAptoCeliaco(false);
        itemMenuDTO.setPeso(350);
        itemMenuDTO.setNombre("Doble Cheeseburguer");
        itemMenuDTO.setDescripcion("Dos medallones de carne, queso cheddar y pan de papa");
        itemMenuDTO.setPrecio(4.5f);
        itemMenuDTO.setAptoVegano(false);
        itemMenuDTO.setCategoriaId(categoria.getId());
        itemMenuDTO.setVendedorId(5);

        Plato plato = new Plato();
        plato.setCalorias(600);
        plato.setAptoCeliaco(false);
        plato.setPeso(350);
        ItemMenu item = plato;
        item.setNombre("Doble Cheeseburguer");
        item.setDescripcion("Dos medallones de carne, queso cheddar y pan de papa");
        item.setPrecio(4.5f);
        item.setAptoVegano(false);
        item.setCategoria(categoria);
        item.setVendedor(vendedor);
        
        when(categoriaService.getCategoriaById(categoria.getId())).thenReturn(categoria);
        when(vendedorService.getByIdVendedor(5)).thenReturn(vendedor);
        
        String vista = itemMenuController.saveItemMenu(itemMenuDTO);

        verify(categoriaService, times(1)).getCategoriaById(categoria.getId());
        verify(vendedorService, times(1)).getByIdVendedor(5);
        verify(itemMenuService, times(1)).createItemMenu(item);
        assertThat(vista).isEqualTo("redirect:/items-menu");
    }
    
    @Test
    public void testEditItemMenuForm(){
        Categoria categoria = Categoria.valueOf("hamburguesas");
        Vendedor vendedor = new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0));
        
        Plato plato = new Plato();
        plato.setCalorias(600);
        plato.setAptoCeliaco(false);
        plato.setPeso(350);
        ItemMenu item = plato;
        item.setId(2);
        item.setNombre("Doble Cheeseburguer");
        item.setDescripcion("Dos medallones de carne, queso cheddar y pan de papa");
        item.setPrecio(4.5f);
        item.setAptoVegano(false);
        item.setCategoria(categoria);
        item.setVendedor(vendedor);
        
        List<Categoria> categoriasSimuladas = Arrays.asList(
                Categoria.valueOf("gaseosas"),
                Categoria.valueOf("hamburguesas"),
                Categoria.valueOf("verduras")
        );
        List<Vendedor> vendedoresSimulados = Arrays.asList(
            new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)),
            new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0))
        );
        
        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        itemMenuDTO.setId(2);
        itemMenuDTO.setTipo(TipoItem.PLATO);
        itemMenuDTO.setCalorias(600);
        itemMenuDTO.setAptoCeliaco(false);
        itemMenuDTO.setPeso(350);
        itemMenuDTO.setNombre("Doble Cheeseburguer");
        itemMenuDTO.setDescripcion("Dos medallones de carne, queso cheddar y pan de papa");
        itemMenuDTO.setPrecio(4.5f);
        itemMenuDTO.setAptoVegano(false);
        itemMenuDTO.setCategoriaId(categoria.getId());
        itemMenuDTO.setVendedorId(5);
        
        when(categoriaService.getAllCategorias()).thenReturn(categoriasSimuladas);
        when(vendedorService.getAllVendedores()).thenReturn(vendedoresSimulados);
        when(itemMenuService.getItemMenuById(2)).thenReturn(item);
        
        String vista = itemMenuController.editItemMenuForm(2, modelo);
        
        verify(modelo, times(1)).addAttribute("itemMenu", itemMenuDTO);
        verify(modelo, times(1)).addAttribute("categorias", categoriasSimuladas);
        verify(modelo, times(1)).addAttribute("vendedores", vendedoresSimulados);
        assertThat(vista).isEqualTo("editar_itemMenu_from_items");
    }
    
    @Test
    public void testUpdateItemMenu() {
        Categoria categoria = Categoria.valueOf("hamburguesas");
        Vendedor vendedor = new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0));
        
        Plato plato = new Plato();
        plato.setCalorias(600);
        plato.setAptoCeliaco(false);
        plato.setPeso(350);
        ItemMenu item_existente = plato;
        item_existente.setId(2);
        item_existente.setNombre("Doble Cheeseburguer");
        item_existente.setDescripcion("Dos medallones de carne, queso cheddar y pan de papa");
        item_existente.setPrecio(4.5f);
        item_existente.setAptoVegano(false);
        item_existente.setCategoria(categoria);
        item_existente.setVendedor(vendedor);

        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        itemMenuDTO.setTipo(TipoItem.PLATO);
        itemMenuDTO.setCalorias(600);
        itemMenuDTO.setAptoCeliaco(false);
        itemMenuDTO.setPeso(350);
        itemMenuDTO.setNombre("Doble Cheeseburguer");
        itemMenuDTO.setDescripcion("Dos medallones de carne, queso cheddar, bacon y pan de papa");
        itemMenuDTO.setPrecio(6.0f);
        itemMenuDTO.setAptoVegano(false);
        itemMenuDTO.setCategoriaId(categoria.getId());
        itemMenuDTO.setVendedorId(5);
        
        ItemMenu item_modificado = plato;
        item_modificado.setId(2);
        item_modificado.setNombre("Doble Cheeseburguer");
        item_modificado.setDescripcion("Dos medallones de carne, queso cheddar, bacon y pan de papa");
        item_modificado.setPrecio(6.0f);
        item_modificado.setAptoVegano(false);
        item_modificado.setCategoria(categoria);
        item_modificado.setVendedor(vendedor);
        
        when(itemMenuService.getItemMenuById(2)).thenReturn(item_existente);
        when(categoriaService.getCategoriaById(categoria.getId())).thenReturn(categoria);
        when(vendedorService.getByIdVendedor(5)).thenReturn(vendedor);
        
        String vista = itemMenuController.updateItemMenu(2, itemMenuDTO);

        verify(itemMenuService, times(1)).getItemMenuById(2);
        verify(categoriaService, times(1)). getCategoriaById(categoria.getId());
        verify(vendedorService, times(1)).getByIdVendedor(5);
        verify(itemMenuService, times(1)).updateItemMenu(item_modificado);
        assertThat(vista).isEqualTo("redirect:/items-menu");
    }
    
    @Test
    public void testDeleteItemMenu(){
        String vista = itemMenuController.deleteItemMenu(2);
        
        verify(itemMenuService, times(1)).deleteItemMenu(2);
        assertThat(vista).isEqualTo("redirect:/items-menu");
    }
    
    @Test
    public void TestGetMenuByVendedor(){
        Vendedor vendedor = new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0));
        
        List<ItemMenu> itemsSimulados = Arrays.asList(
                new Bebida(1, "Coca Cola", "Bebida gaseosa", 1.5f, Categoria.valueOf("gaseosas"), 
                0.0f, 500, true, vendedor),
                new Plato(2, "Doble Cheeseburguer", "Dos medallones de carne, queso cheddar, bacon y pan de papa", 600, false,
                350.0f, 6.0f, Categoria.valueOf("hamburguesas"), false, vendedor)
        );
        
        when(vendedorService.getByIdVendedor(5)).thenReturn(vendedor);
        when(vendedorService.getItemsMenuByVendedor(5)).thenReturn(itemsSimulados);
        
        String vista = itemMenuController.getMenuByVendedor(5, modelo);
        
        verify(modelo, times(1)).addAttribute("vendedor", vendedor);
        verify(modelo, times(1)).addAttribute("items", itemsSimulados);
        assertThat(vista).isEqualTo("menu_vendedor");
    }
    
    @Test
    public void TestNewItemMenuFormFormVendedor(){
        Vendedor vendedor = new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0));
        
        List<Categoria> categoriasSimuladas = Arrays.asList(
                Categoria.valueOf("gaseosas"),
                Categoria.valueOf("hamburguesas"),
                Categoria.valueOf("verduras")
        );
        List<Vendedor> vendedoresSimulados = Arrays.asList(
            new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)),
            new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0))
        );
        
        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        itemMenuDTO.setVendedorId(5);
        
        when(vendedorService.getByIdVendedor(5)).thenReturn(vendedor);
        when(categoriaService.getAllCategorias()).thenReturn(categoriasSimuladas);
        when(vendedorService.getAllVendedores()).thenReturn(vendedoresSimulados);
        
        String vista = itemMenuController.newItemMenuFormFromVendedor(5, modelo);
        
        verify(modelo, times(1)).addAttribute("itemMenu", itemMenuDTO);
        verify(modelo, times(1)).addAttribute("vendedor", vendedor);
        verify(modelo, times(1)).addAttribute("categorias", categoriasSimuladas);
        verify(modelo, times(1)).addAttribute("vendedores", vendedoresSimulados);
        assertThat(vista).isEqualTo("nuevo_itemMenu_from_menu_vendedor");
    }
    
    @Test
    public void testEditItemMenuFormFromVendedor(){
        Categoria categoria = Categoria.valueOf("hamburguesas");
        Vendedor vendedor = new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0));
        
        Plato plato = new Plato();
        plato.setCalorias(600);
        plato.setAptoCeliaco(false);
        plato.setPeso(350);
        ItemMenu item = plato;
        item.setId(2);
        item.setNombre("Doble Cheeseburguer");
        item.setDescripcion("Dos medallones de carne, queso cheddar y pan de papa");
        item.setPrecio(4.5f);
        item.setAptoVegano(false);
        item.setCategoria(categoria);
        item.setVendedor(vendedor);
        
        List<Categoria> categoriasSimuladas = Arrays.asList(
                Categoria.valueOf("gaseosas"),
                Categoria.valueOf("hamburguesas"),
                Categoria.valueOf("verduras")
        );
        List<Vendedor> vendedoresSimulados = Arrays.asList(
            new Vendedor(4, "Lo de Nestor", "Pedro de Vega 1423", new Coordenada(1, 10.0, 20.0)),
            new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0))
        );
        
        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        itemMenuDTO.setId(2);
        itemMenuDTO.setTipo(TipoItem.PLATO);
        itemMenuDTO.setCalorias(600);
        itemMenuDTO.setAptoCeliaco(false);
        itemMenuDTO.setPeso(350);
        itemMenuDTO.setNombre("Doble Cheeseburguer");
        itemMenuDTO.setDescripcion("Dos medallones de carne, queso cheddar y pan de papa");
        itemMenuDTO.setPrecio(4.5f);
        itemMenuDTO.setAptoVegano(false);
        itemMenuDTO.setCategoriaId(categoria.getId());
        itemMenuDTO.setVendedorId(5);
        
        when(vendedorService.getByIdVendedor(5)).thenReturn(vendedor);
        when(categoriaService.getAllCategorias()).thenReturn(categoriasSimuladas);
        when(vendedorService.getAllVendedores()).thenReturn(vendedoresSimulados);
        when(itemMenuService.getItemMenuById(2)).thenReturn(item);
        
        String vista = itemMenuController.editItemMenuFormFromVendedor(5, 2, modelo);
        
        verify(modelo, times(1)).addAttribute("itemMenu", itemMenuDTO);
        verify(modelo, times(1)).addAttribute("categorias", categoriasSimuladas);
        verify(modelo, times(1)).addAttribute("vendedores", vendedoresSimulados);
        assertThat(vista).isEqualTo("editar_itemMenu_from_menu_vendedor");
    }
    
    @Test
    public void testSaveItemMenuFromVendedor(){
        Categoria categoria = Categoria.valueOf("hamburguesas");
        Vendedor vendedor = new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0));
        
        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        itemMenuDTO.setTipo(TipoItem.PLATO);
        itemMenuDTO.setCalorias(600);
        itemMenuDTO.setAptoCeliaco(false);
        itemMenuDTO.setPeso(350);
        itemMenuDTO.setNombre("Doble Cheeseburguer");
        itemMenuDTO.setDescripcion("Dos medallones de carne, queso cheddar y pan de papa");
        itemMenuDTO.setPrecio(4.5f);
        itemMenuDTO.setAptoVegano(false);
        itemMenuDTO.setCategoriaId(categoria.getId());
        itemMenuDTO.setVendedorId(5);

        Plato plato = new Plato();
        plato.setCalorias(600);
        plato.setAptoCeliaco(false);
        plato.setPeso(350);
        ItemMenu item = plato;
        item.setNombre("Doble Cheeseburguer");
        item.setDescripcion("Dos medallones de carne, queso cheddar y pan de papa");
        item.setPrecio(4.5f);
        item.setAptoVegano(false);
        item.setCategoria(categoria);
        item.setVendedor(vendedor);
        
        when(categoriaService.getCategoriaById(categoria.getId())).thenReturn(categoria);
        when(vendedorService.getByIdVendedor(5)).thenReturn(vendedor);
        
        String vista = itemMenuController.saveItemMenuFromVendedor(5, itemMenuDTO);

        verify(categoriaService, times(1)).getCategoriaById(categoria.getId());
        verify(vendedorService, times(1)).getByIdVendedor(5);
        verify(itemMenuService, times(1)).createItemMenu(item);
        assertThat(vista).isEqualTo("redirect:/vendedor/5/menu");
    }
    
    @Test
    public void testUpdateItemMenuFromVendedor(){
        Categoria categoria = Categoria.valueOf("hamburguesas");
        Vendedor vendedor = new Vendedor(5, "McDonalds", "San Martin 384", new Coordenada(2, 30.0, 40.0));
        
        Plato plato = new Plato();
        plato.setCalorias(600);
        plato.setAptoCeliaco(false);
        plato.setPeso(350);
        ItemMenu item_existente = plato;
        item_existente.setId(2);
        item_existente.setNombre("Doble Cheeseburguer");
        item_existente.setDescripcion("Dos medallones de carne, queso cheddar y pan de papa");
        item_existente.setPrecio(4.5f);
        item_existente.setAptoVegano(false);
        item_existente.setCategoria(categoria);
        item_existente.setVendedor(vendedor);

        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        itemMenuDTO.setTipo(TipoItem.PLATO);
        itemMenuDTO.setCalorias(600);
        itemMenuDTO.setAptoCeliaco(false);
        itemMenuDTO.setPeso(350);
        itemMenuDTO.setNombre("Doble Cheeseburguer");
        itemMenuDTO.setDescripcion("Dos medallones de carne, queso cheddar, bacon y pan de papa");
        itemMenuDTO.setPrecio(6.0f);
        itemMenuDTO.setAptoVegano(false);
        itemMenuDTO.setCategoriaId(categoria.getId());
        itemMenuDTO.setVendedorId(5);
        
        ItemMenu item_modificado = plato;
        item_modificado.setId(2);
        item_modificado.setNombre("Doble Cheeseburguer");
        item_modificado.setDescripcion("Dos medallones de carne, queso cheddar, bacon y pan de papa");
        item_modificado.setPrecio(6.0f);
        item_modificado.setAptoVegano(false);
        item_modificado.setCategoria(categoria);
        item_modificado.setVendedor(vendedor);
        
        when(itemMenuService.getItemMenuById(2)).thenReturn(item_existente);
        when(categoriaService.getCategoriaById(categoria.getId())).thenReturn(categoria);
        when(vendedorService.getByIdVendedor(5)).thenReturn(vendedor);
        
        String vista = itemMenuController.updateItemMenuFromVendedor(5, 2, itemMenuDTO);

        verify(itemMenuService, times(1)).getItemMenuById(2);
        verify(categoriaService, times(1)). getCategoriaById(categoria.getId());
        verify(vendedorService, times(1)).getByIdVendedor(5);
        verify(itemMenuService, times(1)).updateItemMenu(item_modificado);
        assertThat(vista).isEqualTo("redirect:/vendedor/5/menu");
    }
    
    @Test
    public void testDeleteItemMenuFromVendedor(){
        String vista = itemMenuController.deleteItemMenuFromVendedor(5, 2);
        
        verify(itemMenuService, times(1)).deleteItemMenu(2);
        assertThat(vista).isEqualTo("redirect:/vendedor/5/menu");
    }
}

