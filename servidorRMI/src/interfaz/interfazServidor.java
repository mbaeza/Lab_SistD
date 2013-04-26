package interfaz;
import java.rmi.*;

public interface interfazServidor extends Remote{
    public boolean inicioSesion(String nombre, String pass) throws RemoteException;
    public void registrarCliente(interfazCliente cliente, String Nombre) throws RemoteException;
    public void desregistrarCliente(interfazCliente cliente, String Nombre) throws RemoteException;
    public void EnvioMensajes(String Mensaje,interfazCliente cliente) throws RemoteException;
}
