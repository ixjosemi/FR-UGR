/*
 * Jose Miguel Hernandez Garcia
 * Miguel Jimenez Cazorla
 * Fundamentos de Redes
 * 3 º B
 */
package trackingpackages;

import java.io.*;
import java.net.*;
import java.util.*;

public class TrackingPackages {

    public static void main(String[] args){
        
        int port = 8989;
        String host = "localhost";
        int bytesLeidos = 0;
        
        byte []bufferEnvio;
        byte []bufferRecepcion = new byte[1024];
        
        try{
            // Creamos un socket que se conecte al puerto que queremos
            Socket socketServicio = new Socket(host, port);
            
            // Creamos flujos de entrada y salida
            InputStream inputStream = socketServicio.getInputStream();
            OutputStream outputStream = socketServicio.getOutputStream();
            
            bufferEnvio="Quiero conectarme".getBytes();
            
            // Enviamos el mensaje de que queremos conectarnos
            outputStream.write(bufferEnvio, 0, bufferEnvio.length);
            outputStream.flush();
            
            // Leemos respuesta del servidor
            bytesLeidos = inputStream.read(bufferRecepcion); // OK. Introduce tus datos
            String aceptado = "";
            
            for(int i = 0; i < bytesLeidos; i++)
                aceptado += (char)bufferRecepcion[i];
            
            // Mostramos la aceptacion por parte del servidor de que queremos conectarnos
            System.out.println(aceptado);
            
            // Realizamos lectura de login y contraseña
            String username;
            String password;
            
            Scanner scan = new Scanner(System.in);
             
            System.out.println("Username: "); // usuario
            username = scan.next();

            System.out.println("Password: "); // contraseña
            password = scan.next();
            
            String envio = username + password; // enviamos las dos cadenas unidas
            
            bufferEnvio = envio.getBytes();
            
            outputStream.write(bufferEnvio, 0, bufferEnvio.length);
            outputStream.flush();
            
            // Esperamos confirmacion de conexion exitosa
            bytesLeidos = inputStream.read(bufferRecepcion); // OK / ERROR
            String respuesta = "";
            
            for(int i = 0; i < bytesLeidos; i++)
                respuesta += (char)bufferRecepcion[i];
            
            
            // Si nos hemos conectado, accedemos al menu
            if(respuesta.equals("OK")){
                
                System.out.println("\nConexion Exitosa.");
                
                String mensaje_tracking = "Introduce el numero de seguimiento: ";
                
                System.out.println(mensaje_tracking);
                
                String tracking_number = scan.next();
                
                // Enviamos tracking number al servidor
                bufferEnvio = tracking_number.getBytes();
                outputStream.write(bufferEnvio, 0, bufferEnvio.length);
                outputStream.flush();

                // Esperamos confirmacion de conexion exitosa
                bytesLeidos = inputStream.read(bufferRecepcion); // Localizacion / ERROR
                String localizacion = "";
                
                // Comprobamos que el tracking number es correcto y si lo es pintamos la ubicacion
                for(int i = 0; i < bytesLeidos; i++)
                    localizacion += (char)bufferRecepcion[i];
                
                if(!localizacion.equals("El tracking number es incorrecto")){
                    // Pintamos la localizacion del paquete
                    System.out.println(localizacion);
                }
                else
                    System.err.println("El tracking number no existe");
            }
            // Sino se termina el programa
            else
               System.err.println("ERROR de conexion");
            
            
            socketServicio.close();

        }
        catch(IOException e){
            System.err.println("ERROR al crear el socket");
        }
        
    }
   
}
