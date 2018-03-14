/*
 * Jose Miguel Hernandez Garcia
 * Miguel Jimenez Cazorla
 * Fundamentos de Redes
 * 3 º B
 */
package trackingpackages;

import java.io.*;
import java.net.*;


public class Servidor {

    public static void main(String[] args) {

        int port = 8989;
        
        try{
            // Abrimos un socket al servidor
            ServerSocket socketservicio = new ServerSocket(port);
            
            do{
                // Aceptamos una nueva conexion
                Socket socket = socketservicio.accept();
               
                
                Tracking tracking = new Tracking(socket);
                tracking.procesa();
                
            }while(true);
            
        }
        catch(IOException e){
            System.err.println("ERROR escuchar el puerto" + port);
        }
    }
    
}
