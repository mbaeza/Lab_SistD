package clientermi;

import implementaciones.interfazClienteImpl;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import interfaz.*;

public class conexionRMI {

    private static Registry registry;
    private static interfazServidor servidor;
    private static interfazCliente cliente;

    public boolean iniciarRegistry() throws RemoteException {
        try {
            //Se inicia RMIREGISTRY para el registro de objetos
            java.security.AllPermission a = new java.security.AllPermission();
            System.setProperty("java.security.policy", "rmi.policy");
            //startRegistry(direccion del registry,puerto de comunicación);
            startRegistry("127.0.0.1", 1099);
            //Vamos al Registry y miramos el Objeto "Implementacion" para poder usarlo.
            servidor = (interfazServidor) registry.lookup("Implementacion");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void startRegistry(String host, int Puerto) throws RemoteException {
        try {
            registry = LocateRegistry.getRegistry(host, Puerto);
            registry.list();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    //Con esto no es necesario hacer un lookup al objeto remoto cada vez que deseemos usarlo
    //basta con llamar a la instancia de la interfaz que fue llamada la primera vez.

    public interfazServidor getServidor() {
        return servidor;
    }

    public void registrarCliente(String Nombre) throws RemoteException {
        cliente = new interfazClienteImpl();
        servidor.registrarCliente(cliente, Nombre);
    }

    public void desregistrarCliente(String Nombre) throws RemoteException {
        servidor.desregistrarCliente(cliente, Nombre);
    }

    public void EnvioMensajes(String Mensaje) throws RemoteException {
        servidor.EnvioMensajes(Mensaje, cliente);
    }
}
