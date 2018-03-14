package clienteservidor;    
//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.*;
import java.net.*;

public class YodafyClienteTCP {

	public static void main(String[] args) throws SocketException{
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
        
        InetAddress direccion = null;
        DatagramPacket paquete;
        byte[] buffer = new byte[256];
        DatagramSocket socketServicio = new DatagramSocket();
		
		try {
			direccion = InetAddress.getByName(host);
             // Cadena que vamos a enviar
            String mensaje = "Al monte del volcan debes ir sin demora";

            buffer = mensaje.getBytes();

            paquete = new DatagramPacket(buffer, buffer.length, direccion, port);
            socketServicio.send(paquete);
          

            paquete = new DatagramPacket(buffer, buffer.length);
             socketServicio.receive(paquete);
           

            byte[] leido = paquete.getData();

            // Mostramos al cadena de caracteres recibidos
            System.out.println("Recibido: ");
            String respuesta = new String(leido, 0, leido.length);

            System.out.println(respuesta);

            // Una vez terminado el servicio, cerramos el socket
            socketServicio.close();
            
        }catch(UnknownHostException e){
            System.err.println("ERROR al obtener la direccion");
        }catch(IOException e){
            System.err.println("ERROR de entrada/ salida al abrir el socket");
        }
        
       
    }
}
