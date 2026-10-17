/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

import uva.dis.tconn.acme.servicioscomunes.datatypes.CoordenadasGPS;
import java.time.LocalDate;
import uva.dis.tconn.acme.negocio.modelos.enums.NivelTecnico;


/**
 * Modela el puesto de Tecnico heredando parte de su comportamiento  y atributos de Puesto
 * @author Marcos Gomez Medina
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class Tecnico extends Puesto{
   
    
    private NivelTecnico nivel;
    private Base base;
   /**
    * Construccion de un Tecnico
    * @param fechaInicio de tipo LocalDate
    * @param nivel siendo un tipo Enum
    * @param base de tipo Base
    * @param empleado 
    * @throws IllegalArgumentException si la base es nula
    * @throws IllegalArgumentException si el nivel del tecnico es nulo
    */
   public Tecnico(LocalDate fechaInicio, NivelTecnico nivel, Base base, Empleado empleado){
       
      
       super(fechaInicio, empleado);//Constructor de puesto
        
       
       esNulo(nivel);
       esNulo(base);
       
       
       this.nivel=nivel;
       this.base = base;
       
   }
   
   
   public Tecnico() {
       
       super();
   }
    
   
   
   /**
    * Devuelve el nivel del tecnico
    * @return nivel asociado al tecnico
    */   
   public NivelTecnico getNivelTecnico(){
       return this.nivel;
   }
   
   /**
    * Se encarga de devolver la base a la que el tecnico esta asociado
    * @return base de tipo Base
    */
   public Base getBase(){
       
       return this.base;
   }
   /**
    * Obtiene el identificador de la zona en la que se encuentra el tecnico
    * @return idZona,identificador de la zona
    */
   public String getIdZona(){
       
       return getBase().getIdZona();
       
   }
   /**
    * Se retorna las coordenadasGPS 
    * @return CoordenadasGPS del tecnico
    */
   public CoordenadasGPS getUbicacionBase(){
       
       return getBase().getUbicacion();
       
   }
   
   @Override
    public String getRolIdentificador(){
        
        return "TECNICO";
        
    }
   
   /**
    * Devuelve si un objeto es nulo
    * @param objeto
    * @throws IllegalArgumentException si el objeto es nulo
    */
   private void esNulo (Object objeto){

            if (objeto == null){

               throw new IllegalArgumentException("Campo de tecnico nulo");
           }

    }
   
}

