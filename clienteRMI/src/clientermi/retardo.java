package clientermi;

import java.util.logging.Level;
import java.util.logging.Logger;
import vistas.*;

/**
 *
 * @author Administrador
 */
public class retardo implements Runnable{
    private vistaSecundaria miVista;
    
    public retardo(vistaSecundaria vista){
        miVista = vista;
    }   
    public void run() {
        try {
            Thread.sleep(4000);
            //this.miVista.ocultarLabel();
        } catch (InterruptedException ex) {
            Logger.getLogger(retardo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
