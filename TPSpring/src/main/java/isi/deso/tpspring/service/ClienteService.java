/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package isi.deso.tpspring.service;

import isi.deso.tpspring.model.Cliente;
import java.util.List;

/**
 *
 * @author santi
 */
public interface ClienteService {
    public List<Cliente> listAllClientes();
    public Cliente saveCliente(Cliente cliente);
    public Cliente updateCliente(Cliente cliente);
    public void deleteCliente(Integer id);
}
