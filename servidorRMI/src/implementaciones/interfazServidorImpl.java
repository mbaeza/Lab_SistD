package implementaciones;
import interfaz.*;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class interfazServidorImpl extends UnicastRemoteObject implements interfazServidor{

    private ArrayList clientes = new ArrayList();
    private ArrayList NombresClientes = new ArrayList();
    private ArrayList RutClientes = new ArrayList();
    public String Historial = new String();

    public interfazServidorImpl() throws RemoteException{
        super();
    }
    //Este método implementa el servicio de iniciar sesión que se definio en la interfaz
    public boolean inicioSesion(String nombre, String pass) throws RemoteException {
        Connection conexion;
            boolean indicador =false;
            if(!(RutClientes.contains(nombre))){
                try {
                    conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio", "root", "(markus123)");
                    Statement st = conexion.createStatement();
                    ResultSet rs =  st.executeQuery ("select * from usuario");

                    while (rs.next() ){
                       if(rs.getString("RUT_USUARIO").equals(nombre) && rs.getString("CONTRASENA").equals(pass)){
                          indicador = true;
                          RutClientes.add(nombre);
                          break;
                       }else{
                          indicador = false;
                       }                        
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(interfazServidorImpl.class.getName()).log(Level.SEVERE, null, ex);
                    indicador = false;
                }
            }else{
                indicador=false;
            }

        return indicador;
    }
    //Este método registra clientes que se conectan
    public synchronized void registrarCliente(interfazCliente cliente, String Nombre) throws RemoteException{
        Connection conexion;
        if (!(clientes.contains(cliente))) {
            clientes.add(cliente);
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio", "root", "(markus123)");
                Statement st = conexion.createStatement();
                ResultSet rs2 =  st.executeQuery ("select * from profesor");

                while (rs2.next() ){
                   if(rs2.getString("RUT_USUARIO").equals(Nombre)){
                       NombresClientes.add(rs2.getString("NOMBRE1_PROFESOR"));
                   }                       
                }
                
                ResultSet rs3 =  st.executeQuery ("select * from administrador");
                
                while (rs3.next() ){
                   if(rs3.getString("RUT_USUARIO").equals(Nombre)){
                       NombresClientes.add("(ADMINISTRADOR)");
                       
                   }                       
                }
                ResultSet rs4 =  st.executeQuery ("select * from estudiante");
                while (rs4.next() ){
                   if(rs4.getString("RUT_USUARIO").equals(Nombre)){
                       NombresClientes.add(rs4.getString("NOMBRE1_ESTUDIANTE"));                    
                   }                       
                }

            } catch (SQLException ex) {
                Logger.getLogger(interfazServidorImpl.class.getName()).log(Level.SEVERE, null, ex);
                
            }

            
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
        public synchronized void registrarClienteenBD(interfazCliente cliente, String Nombre) throws RemoteException{
        Connection conexion;
        if (!(clientes.contains(cliente))) {
            clientes.add(cliente);
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio", "root", "(markus123)");
                Statement st = conexion.createStatement();
                ResultSet rs2 =  st.executeQuery ("select * from profesor");

                while (rs2.next() ){
                   if(rs2.getString("RUT_USUARIO").equals(Nombre)){
                       NombresClientes.add(rs2.getString("NOMBRE1_PROFESOR"));
                   }                       
                }
                
                ResultSet rs3 =  st.executeQuery ("select * from administrador");
                
                while (rs3.next() ){
                   if(rs3.getString("RUT_USUARIO").equals(Nombre)){
                       NombresClientes.add("(ADMINISTRADOR)");
                       
                   }                       
                }
                ResultSet rs4 =  st.executeQuery ("select * from estudiante");
                while (rs4.next() ){
                   if(rs4.getString("RUT_USUARIO").equals(Nombre)){
                       NombresClientes.add(rs4.getString("NOMBRE1_ESTUDIANTE"));                    
                   }                       
                }

            } catch (SQLException ex) {
                Logger.getLogger(interfazServidorImpl.class.getName()).log(Level.SEVERE, null, ex);
                
            }

            
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
