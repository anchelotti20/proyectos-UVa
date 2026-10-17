/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.datatypes;

/**
 * Clase que representa al tipo de datos correspondientes a una coordenada del estilo GPS
 * @author Marcos Gomez 
 * @author Angel Antolin 
 * @author Elisa Martinez
 */

public class CoordenadasGPS {
    
    private double latitud;
    private double longitud;
    /**
     * Construye el tipo Coordenada a partir de los datos primitivo de tipo double de latidu y longitud
     * @param latitud de tipo double
     * @param longitud a su vez double representando la longitud
     */
    public CoordenadasGPS(double latitud,double longitud){
        this.latitud=latitud;
        this.longitud=longitud;
    }
    /**
     * Permite obtener la latidud
     * @return latitud 
     */
    public double getLatitud(){
        return this.latitud;
    }
    /**
     * Devuelve el valor de la longitud
     * @return longitud
     */
    public double getLongitud(){
        return this.longitud;
    }
    
}
