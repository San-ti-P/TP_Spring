/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package isi.deso.tpspring.model;

/**
 *
 * @author santi
 */
public interface Observable {
    public void addObserver(PedidoObserver o);
    public void removeObserver(PedidoObserver o);
    public void notifyObservers();
}
