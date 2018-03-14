/*
 * Jose Miguel Hernandez Garcia
 * Miguel Jimenez Cazorla
 * Fundamentos de Redes
 * 3 º B
 */
package trackingpackages;

import java.util.*;

public class BDTracking {

    private Random random;
    private ArrayList<String> ciudades = new ArrayList();
    private ArrayList<String> dia_entrega = new ArrayList();
    private ArrayList<String> UC = new ArrayList();
    private ArrayList<String> TN = new ArrayList();

    public BDTracking() {
        
        this.random = new Random();
        
        ciudades.add("La Coruña");
        ciudades.add("Alava");
        ciudades.add("Albacete");
        ciudades.add("Alicante");
        ciudades.add("Almeria");
        ciudades.add("Asturias");
        ciudades.add("Avila");
        ciudades.add("Badajoz");
        ciudades.add("Islas Baleares");
        ciudades.add("Barcelona");
        ciudades.add("Burgos");
        ciudades.add("Caceres");
        ciudades.add("Cadiz");
        ciudades.add("Cantabria");
        ciudades.add("Castellon");
        ciudades.add("Ciudad Real");
        ciudades.add("Cordoba");
        ciudades.add("Cuenca");
        ciudades.add("Girona");
        ciudades.add("Granada");
        ciudades.add("Guadalajara");
        ciudades.add("Guipuzcoa");
        ciudades.add("Huelva");
        ciudades.add("Huesca");
        ciudades.add("Jaen");
        ciudades.add("La Rioja");
        ciudades.add("Las Palmas");
        ciudades.add("Leon");
        ciudades.add("Lleida");
        ciudades.add("Lugo");
        ciudades.add("Madrid");
        ciudades.add("Malaga");
        ciudades.add("Murcia");
        ciudades.add("Navarra");
        ciudades.add("Ourense");
        ciudades.add("Palencia");
        ciudades.add("Pontevedra");
        ciudades.add("Salamanca");
        ciudades.add("Segovia");
        ciudades.add("Sevilla");
        ciudades.add("Soria");
        ciudades.add("Tarragona");
        ciudades.add("Santa Cruz de Tenerife");
        ciudades.add("Teruel");
        ciudades.add("Toledo");
        ciudades.add("Valencia");
        ciudades.add("Valladolid");
        ciudades.add("Vizcaya");
        ciudades.add("Zamora");
        ciudades.add("Zaragoza");
        
        dia_entrega.add("Lunes");
        dia_entrega.add("Martes");
        dia_entrega.add("Miercoles");
        dia_entrega.add("Jueves");
        dia_entrega.add("Viernes");
        
        UC.add("adminadmin");
        UC.add("admin1234");
        UC.add("usuariousuario");
        UC.add("ixjosemi12344");
        UC.add("imiguel12345");
        UC.add("pepe234445");
        UC.add("mariadbzabbix");
        UC.add("whiresharkTCP");
        
        TN.add("TN123456");
        TN.add("TN789123");
        TN.add("TN098765");
        TN.add("TN654321");
        TN.add("TN111111");
        TN.add("TN192991");
        
                     
        Collections.shuffle(ciudades);
        Collections.shuffle(dia_entrega);
        
    }
    public String getCiudad() {
        int n = random.nextInt() % ciudades.size();
        if(n < 0) n = -n;
        return ciudades.get(n);
    }
    
    public String getDiaEntrega() {
        int n = random.nextInt() % dia_entrega.size();
        if(n < 0) n = -n;
        return dia_entrega.get(n);
    }
    
    public boolean UsuarioCon(String loggin) {
        return UC.contains(loggin);
    }
    
    public boolean TrackingN (String tracking) {
        return TN.contains(tracking);
    }
}
