/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorrmi;

import interfaz.interfazCliente;

/**
 *
 * @author Lalo
 */
public class Cliente implements Runnable {

    private String NombreCliente;
    private interfazCliente cliente;

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public interfazCliente getCliente() {
        return cliente;
    }

    public void setCliente(interfazCliente cliente) {
        this.cliente = cliente;
    }

    public void run() {

        throw new UnsupportedOperationException("Not supported yet.");
    }

}
