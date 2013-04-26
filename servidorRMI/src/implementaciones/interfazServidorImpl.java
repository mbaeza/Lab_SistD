package implementaciones;
import interfaz.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;


public class interfazServidorImpl extends UnicastRemoteObject implements interfazServidor{

    private ArrayList clientes = new ArrayList();
    private ArrayList NombresClientes = new ArrayList();
    public String Historial = new String();

    public interfazServidorImpl() throws RemoteException{
        super();
    }
    //Este método implementa el servicio de iniciar sesión que se definio en la interfaz
    public boolean inicioSesion(String nombre, String pass) throws RemoteException {
        if (nombre.equals("Juan") && pass.equals("juan")){
            return true;
        }
        else if(nombre.equals("Pedro") && pass.equals("pedro")){
            return true;
        }
        else if (nombre.equals("Jose") && pass.equals("jose")){
            return true;
        }
        return false;
    }
    //Este método registra clientes que se conectan
    public synchronized void registrarCliente(interfazCliente cliente, String Nombre) throws RemoteException{
        if (!(clientes.contains(cliente)) && !(NombresClientes.contains(Nombre))) {
            clientes.add(cliente);
            NombresClientes.add(Nombre);
            //clientesNombre.addElement(Nombre);
           /* for (int i=0;i<clientes.size();i++){
                interfazCliente nextClient = (interfazCliente)clientes.get(i);
                if (!cliente.toString().equals(nextClient.toString())){
                    //Mando la notificacion de que se conecto otro usuario
                    nextClient.notificar("Se CONECTO "+Nombre);
                }
            }*/
        }
    }
    //Este método elimina clientes y notifica la desconexion de alguno.
    public synchronized void desregistrarCliente(interfazCliente cliente, String Nombre) throws RemoteException{
        if (clientes.remove(cliente) && NombresClientes.remove(Nombre)) {
            //clientesNombre.removeElement(Nombre);
          /*  for (int i=0;i<clientes.size();i++){
                interfazCliente nextClient = (interfazCliente)clientes.get(i);
                //Mando la notificacion de que se conecto otro usuario
                nextClient.notificar("Se DESCONECTO "+Nombre);
            }*/
        }
        else{
            System.out.print("Cliente no estaba registrado");
        }
    }
     public synchronized void EnvioMensajes(String Mensaje,interfazCliente cliente) throws RemoteException{
            
         int auxiliar = 0;
         System.out.println(auxiliar);
            //clientesNombre.removeElement(Nombre);
            for (int i=0;i<clientes.size();i++){
                System.out.println(clientes.get(i)+"\n");
                System.out.println(cliente+"\n");
                if((clientes.contains(cliente))){
                    
                    auxiliar = i;
                }
            }
            System.out.println(auxiliar);
            for (int i=0;i<clientes.size();i++){
                interfazCliente nextClient = (interfazCliente)clientes.get(i);
                //Mando la notificacion de que se conecto otro usuario
                nextClient.notificar(NombresClientes.get(auxiliar)+": "+Mensaje+"\n");
            }
        
    }
}
