package interfaz;
import java.rmi.*;

public interface interfazCliente extends Remote{
    public void notificar(String mensaje) throws RemoteException;
}
