package clienteservidor;

import java.io.*;
import java.net.*;
import java.util.*;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
public class YodafyServidorIterativo {

	public static void main(String[] args) {
	
        // Abrimos el socket UDP
        DatagramSocket socketServidor = null;
        
		// Puerto de escucha
		int port=8989;
        		
        // Creamos los paquetes que seran enviados
        DatagramPacket paquete = null;
		DatagramPacket paqueteModificado = null;

        
		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			//////////////////////////////////////////////////
            socketServidor = new DatagramSocket(port);
            
            do{
                ProcesadorYodafy procesador = new ProcesadorYodafy(socketServidor);
                procesador.procesa();
            }while(true);
	
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}
}
}
