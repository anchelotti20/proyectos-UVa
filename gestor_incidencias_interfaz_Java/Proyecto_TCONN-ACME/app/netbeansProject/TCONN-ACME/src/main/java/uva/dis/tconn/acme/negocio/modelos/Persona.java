/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

import uva.dis.tconn.acme.servicioscomunes.datatypes.Direccion;
import uva.dis.tconn.acme.servicioscomunes.datatypes.Nif;

/**
 * Clase que represneta a una persona
 * @author Marcos Gomez
 * @author Angel Antolin 
 * @author Elisa Martinez
 */

public class Persona {
    
    private String nombre;
    private String apellidos;
    private int telefonoContacto;
    private Direccion direccion;
    private Nif nif;
    
    /**
     * Constructor de persona
     * @param nombre referente a un string que representa el nombre de la persona
     * @param apellidos lo mismo para los apellidos de una persona
     * @param telefonoDeContacto de tipo int para modelar un telefono de contacto
     * @param direccion de tipo Direccion
     * @param nif de tipo Nif
     * @throws IllegalArgumentException si nombre es nulo o vacio
     * @throws IllegalArgumentException si apellidos son nulos o vacios
     * @throws IllegalArgumentException si la direccion o el nif son nulos
     */
    
    public Persona(String nombre,String apellidos,int telefonoDeContacto,Direccion direccion,Nif nif){
        
       esNuloOVacio(nombre);
       esNuloOVacio(apellidos);
       
       if(direccion==null){
           throw new IllegalArgumentException("Direccion no puede ser nula");
       }
       
       if(nif==null){
           throw new IllegalArgumentException("Nif no puede ser nulo");
       }
       
       this.nombre=nombre;
       this.apellidos=apellidos;
       this.telefonoContacto=telefonoDeContacto;
       this.direccion=direccion;
       this.nif=nif;
        
       
    }
    /**
     * Constructor vacio que es necesario para luego crear Empleado
     */
    protected Persona (){
        
        
    }
    
   /**
    * Getter del nombre, obtiene el nombre de la persona
    * @return nombre 
    */
    public String getNombre(){
        return this.nombre;
    }
    /**
     * Se encarga de conseguir los apellidos de una persona
     * @return apellidos de tipo String
     */
    public String getApellidos(){
        return this.apellidos;
    }
    /**
     * Obtiene el telefono de contacto de una persona
     * @return telefonoDeContacto
     */
    public int getTelefonoDeContacto(){
        return this.telefonoContacto;
    }
    /**
     * Devuelve la direccion
     * @return direccion de tipo Direccion
     */
    public Direccion getDireccion(){
        return this.direccion;
    }
    /**
     * Se encarga de devolver el nif correspondiente a una persona
     * @return nif de tipo Nif
     */
    public Nif getNif(){
        return this.nif;
    }
    
    /**
     * Metodo que se encarga de lanzar de excepciones si el texto es nulo o vacio
     * @param texto de tipo String que se va a comprobar
     * @throws IllegalArgumentException si el teto es nulo o vacio
     */
    private void esNuloOVacio (String texto){

           if (texto == null || texto.isEmpty()){

               throw new IllegalArgumentException("Campo de empleado nulo");
           }

       }
    
    
    
    
}
