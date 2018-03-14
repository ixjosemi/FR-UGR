/*
 * Jose Miguel Hernandez Garcia
 * Miguel Jimenez Cazorla
 * Fundamentos de Redes
 * 3 º B
 */
package trackingpackages;

import java.io.*;
import java.net.*;

public class Tracking {
    
    // Llamamos a la clase BDTracking
    BDTracking track = new BDTracking();
    
    // Abrimos un socket 
    private Socket socketServicio;
    
    // Flujo de lectura
    private InputStream inputStream;
            
    // Flujo de salida
    private OutputStream outputStream;
    
    // Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public Tracking(Socket socketServicio) {
		this.socketServicio=socketServicio;
	}
    
    void procesa(){
        
        byte []bufferEnvio;
        byte []bufferLectura = new byte[1024];
        int bytesRecibidos = 0;
        
        // Obtenemos flujos de salida y entrada
        try {
			// Obtiene los flujos de escritura/lectura
			inputStream=socketServicio.getInputStream();
			outputStream=socketServicio.getOutputStream();
            
            // Leemos la frase de que queremos conectarnos
            bytesRecibidos = inputStream.read(bufferLectura);
            
            // Convertimos la frase a string
            String peticion = "";
            
            for(int i = 0; i < bytesRecibidos; i++)
                peticion += (char)bufferLectura[i];
            
            if(peticion.equals("Quiero conectarme")){
                
                // Pintamos el mensaje de bienvenida en el servidor y en el cliente
                System.out.println(Bienvenida());
                
                // Enviamos el mensaje de bienvenida al cliente para pintarlo
                String mensajeBienvenida = Bienvenida()+"\nConectado. Introduce tus datos";
                bufferEnvio = mensajeBienvenida.getBytes();
                outputStream.write(bufferEnvio, 0, bufferEnvio.length);
                outputStream.flush();
                
                // Leemos la cadena de usuario y contraseña y comprobamos que es correcto
                bytesRecibidos = inputStream.read(bufferLectura);
                
                // Si la conexion es exitosa, pedimos el numero de seguimiento
                if(login(bufferLectura, bytesRecibidos)){
                    
                    // Mostramos mensaje de exito al conectarse
                    bufferEnvio = "OK".getBytes();
                    outputStream.write(bufferEnvio, 0, bufferEnvio.length);
                    outputStream.flush();
                    
                    // Esperamos la lectura del tracking number
                    bytesRecibidos = inputStream.read(bufferLectura);
                    
                    if(seguimiento(bufferLectura, bytesRecibidos)){
                        
                        // Pintamos la ciudad en la que se encuentra el paquete
                        // y el dia en que lo recibiremos
                        
                        String ubicacion = "\nEl paquete esta en " + track.getCiudad()
                                + "\n" + "Y sera entregado el proximo " 
                                + track.getDiaEntrega();
                        
                        bufferEnvio = ubicacion.getBytes();
                        outputStream.write(bufferEnvio, 0, bufferEnvio.length);
                        outputStream.flush();
                    }
                    else{
                       
                        // Mostramos mensaje de error al buscar el numero de seguimiento
                        bufferEnvio = "El tracking number es incorrecto".getBytes();
                        outputStream.write(bufferEnvio, 0, bufferEnvio.length);
                        outputStream.flush();
                    }
                }
                else{
                    
                    // Mostramos mensaje de error
                    bufferEnvio = "ERROR al conectar".getBytes();
                    outputStream.write(bufferEnvio, 0, bufferEnvio.length);
                    outputStream.flush();
                }
            }
            else
                System.err.println("ERROR de peticion de conexion");


		} catch (IOException e) {
			System.err.println("Error al obtener los flujos de entrada/salida.");
		}
        
    }
    
    boolean login(byte []conexion, int tam){
        boolean conectado = false;
        
        String recibido = "";
        
        for(int i = 0; i < tam; i++)
            recibido += (char)conexion[i];
        
        if(track.UsuarioCon(recibido))
            conectado = true;
        
        return conectado;     
    }
    
    boolean seguimiento(byte []seguimiento, int tam){
        boolean encontrado = false;
        
        String recibido = "";
        
        for(int i = 0; i < tam; i++)
            recibido += (char)seguimiento[i];

        if(track.TrackingN(recibido))
           encontrado = true;

        return encontrado;     
    }
    
    String Bienvenida(){
        
        String bienvenida = "";

        bienvenida = "*********************************************\n" +
                     "*  PROGRAMA DE SEGUIMIENTO DE PAQUETERIA    *\n" +
                     "*  Fundamentos de Redes                     *\n" +
                     "*  Jose Miguel Hernandez Garcia             *\n" +
                     "*  Miguel Jimenez Cazorla                   *\n" +
                     "*********************************************\n";
        
        return bienvenida;
    }
}
