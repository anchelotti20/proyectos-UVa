/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.datatypes;

/**
 * Clase que modela el DataType de Direccion 
 * @author Marcos Gomez Medina
 * @author Angel Antolin Caramazana
 * @author Elisa Martinez 
 */

public class Direccion {
    
    private String nombreDeLaVia;
    private int numero;
    private int piso;
    private String puerta;
    private int codigoPostal;
    private String localidad;
    private String provincia;
    
    /**
     * Contructor de la Direccion
     * @param nombreDeLaVia tipo de dato String que representa al nombre de la via de la direccion
     * @param numero  tipo int para el numero
     * @param piso de tipo int puede ser 0
     * @param puerta de tipo String puede ser null
     * @param codigoPostal de tipo entero para modelar el codigo postal pertenieciente a una direccion
     * @param localidad string
     * @param provincia string
     * @throws IllegalArgumentException si el nombreDeLaVia es nulo
     * @throws IllegalArgumentException si localidad es nulo
     * @throws IllegalArgumentException si provincia es nulo
     */
    public Direccion(String nombreDeLaVia,int numero, int piso,String puerta,int codigoPostal,String localidad,String provincia){
        
        esNuloOVacio(nombreDeLaVia);
        esNuloOVacio(localidad);
        esNuloOVacio(provincia);
        
        this.nombreDeLaVia=nombreDeLaVia;
        this.numero=numero;
        this.piso=piso;
        this.puerta=puerta;
        this.codigoPostal=codigoPostal;
        this.localidad=localidad;
        this.provincia=provincia;
        
  
    }
   
    /**
     * Devuelve el nombre de la via
     * @return string con el nombre de la via
     */
    public String getNombreDeLaVia(){
        
        return this.nombreDeLaVia;
        
    }
    
    /**
     * Devuelve el numero de la direccion
     * @return int con el numero
     */
    public int getNumero(){
        
        return this.numero;
    }
    
    /**
     * Devuelve el piso de la direccion
     * @return int con el piso
     */
    public int getPiso(){
        
        return this.piso;
    }
    
    /**
     * Devuelve la puerta 
     * @return string con la puerta
     */
    public String getPuerta(){
        
        return this.puerta;
    }
    
    /**
     * Devuelve el codigo postal
     * @return int con el codigo postal
     */
    public int getCodigoPostal(){
        
        return this.codigoPostal;
    }
    
    /**
     * Devuelve la localidad
     * @return string con la localidad
     */
    public String getLocalidad(){
        
        return this.localidad;
        
    }
    
    /**
     * Devuelve la provincia
     * @return string con la provincia
     */
    public String getProvincia(){
        
        return this.provincia;
    }
    
    /**
     * Comprueba si un texto es nulo o vacio
     * @param texto, String con el texto
     * @throws IllegalArgumentException si es nulo
     */
    private void esNuloOVacio (String texto){
        
        if (texto == null || texto.isEmpty()){
            
            throw new IllegalArgumentException("Campo de empleado nulo");
        }
        
    }
    
}
