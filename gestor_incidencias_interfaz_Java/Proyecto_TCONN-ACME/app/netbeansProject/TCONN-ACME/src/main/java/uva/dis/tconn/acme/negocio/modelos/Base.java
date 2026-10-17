package uva.dis.tconn.acme.negocio.modelos;

import uva.dis.tconn.acme.servicioscomunes.datatypes.CoordenadasGPS;


/**
 * Modela una Base física de TCONN-ACME.
 * @author Marcos Gomez 
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class Base {
    
    
    private int idBase; 
    private ZonaGeografica zona;
    private CoordenadasGPS ubicacion;
    
    /**
     * Constructor de la base
     * @param idBase numero de identificador para poder identificar la base
     * @param zona de tipo ZonaGeografica
     * @param ubicacion de tipo CoordenadasGPS
     * @throws IllegalArgumentException si la zona o la ubicacion es nula 
     */
    public Base(int idBase, ZonaGeografica zona, CoordenadasGPS ubicacion) {
        
        esNulo(zona);
        esNulo(ubicacion);
        
        this.idBase = idBase;
        this.zona = zona;
        this.ubicacion = ubicacion;
    }

   /**
    * Devuelve le identificador de la zona
    * @return de tipo String asociado al identificador de la zona
    */
    public String getIdZona() {
        return this.zona.getId();
    }
    
   /**
    * Obtiene el idnetificador de la base
    * @return String asociado al identificador de la base
    */
    public int getIdBase() {
        return this.idBase;
    }
    /**
     * Permite obtener las coordenadas gps de la ubicacion 
     * @return Coordenadas gps de la zona
     */
    public CoordenadasGPS getUbicacion() {
        return this.ubicacion;
    }
    /**
     * Metodo que se encarga de lanzar excepcion si el objeto es nulo
     * @param objeto objeto a comprobar si es nulo
     * @throws IllegalArgumentExcpetion si el objeto es efectivamete nulo
     */
    private void esNulo (Object objeto){

            if (objeto == null){

               throw new IllegalArgumentException("Campo de base nulo");
           }

    }
}
