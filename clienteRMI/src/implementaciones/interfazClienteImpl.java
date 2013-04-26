package implementaciones;
import java.rmi.*;
import java.rmi.server.*;
import interfaz.*;
import vistas.*;

public class interfazClienteImpl extends UnicastRemoteObject implements interfazCliente{

    public interfazClienteImpl() throws RemoteException{
        super();
    }
    public void notificar(String mensaje) throws RemoteException{
        vistaSecundaria.getInstanciaVista().aviso(mensaje);
    }
}
