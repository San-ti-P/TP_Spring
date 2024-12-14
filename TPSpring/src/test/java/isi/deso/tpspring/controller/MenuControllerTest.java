/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tpspring.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;


/**
 *
 * @author santi
 */
public class MenuControllerTest {

    @InjectMocks
    private MenuController menuController;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testMenu(){
        assertThat(menuController.menu()).isEqualTo("menu");
    }
    
    @Test
    public void testRoot(){
        assertThat(menuController.root()).isEqualTo("redirect:/menu");
    }
}
