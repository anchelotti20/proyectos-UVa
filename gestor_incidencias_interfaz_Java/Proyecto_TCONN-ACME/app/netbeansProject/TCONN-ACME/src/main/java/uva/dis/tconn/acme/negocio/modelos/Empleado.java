/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;


import uva.dis.tconn.acme.servicioscomunes.datatypes.DatosFechaTrabajo;
import uva.dis.tconn.acme.servicioscomunes.datatypes.IdEmpleado;
import uva.dis.tconn.acme.negocio.modelos.enums.Disponibilidad;
import uva.dis.tconn.acme.negocio.modelos.enums.Vinculacion;

import java.io.StringWriter;


import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;

/**
 * Modela un empleado de la TCONN-ACME, con sus datos personales y su rol en la empresa
 * Empleado se trata de una especializacion de la clase Persona
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class Empleado extends Persona {
    private Persona persona;
    private String email;
    private Disponibilidad disponibilidad;
    private Vinculacion vinculacion;
    private IdEmpleado identificadorEmpleado;
    private DatosFechaTrabajo fechas;
    private Puesto puesto;
    
    /**
     * Constructor de la clase Empleado
     * @param persona representa la persona asociada
     * @param puesto representa el puesto que representa el empleado
     * @param email String con el correo electronico
     * @param fechas clase que tiene las fecha de incorporacion y finalizacion de contrato del empleado
     * @param identificadorEmpleado datatype que identifica al empleado
     * @param disponibilidad enum con la disponibilidad del empleado
     * @param vinculacion  enum con la vinculacion  del empleado
     * @throws IllegalArgumentException si puesto es nulo si el email es nulo o vacio, o vinculacion y/o disponibilidad son nuelos
     * 
     */
    public Empleado (Persona persona,Puesto puesto,String email,
            DatosFechaTrabajo fechas,IdEmpleado identificadorEmpleado,Disponibilidad disponibilidad,Vinculacion vinculacion){
        
        if(persona==null){
            throw new IllegalArgumentException("Persona nula");
        }
        
        if(puesto == null){
            
            throw new IllegalArgumentException("Puesto nulo");
        }
        
        if(email==null||email.isBlank()){
            throw new IllegalArgumentException("El email no puede ser nulo");
        }
        
        if(fechas==null){
            throw new IllegalArgumentException("No puede ser nulo.");
        }
        
        if(disponibilidad==null){
            throw new IllegalArgumentException("La disponibilidad no puede ser nula");
        }
        
        if(vinculacion==null){
            throw new IllegalArgumentException("La vinculacion no puede ser nula");
        }
        
        this.puesto=puesto;
        this.identificadorEmpleado=identificadorEmpleado;
        this.email=email;
        this.disponibilidad = disponibilidad;
        this.vinculacion = vinculacion;
    }
    
    public Persona getPersona(){
        return this.persona;
    }
   
    
    public Empleado(IdEmpleado id){
        
        super();
        
        this.identificadorEmpleado = id;
        
    }
    
  
    /**
     * Obtiene el puesto del empleado
     * @return Puesto del empleado
     */
    public Puesto getPuesto(){
        
        return this.puesto;
    }
    
    /**
     * Fucnion que devuelve el valor correspondiente a la disponibilidad del empleado
     * @return valor con la disponibilidad 
     */
    public Disponibilidad getDisponibilidad(){
        return this.disponibilidad;
    }
    /**
     * Se encarga de devolver el correspondiente a la vinculacion
     * @return vinculacion del empleado
     */
    public Vinculacion getVinculacion(){
        return this.vinculacion;
    }
    /**
     * Obtiene el email del empleado
     * @return String asociado al email del empleado
     */
    public String getEmail(){
        return this.email;
    }
    
    public DatosFechaTrabajo getFechas(){
        return this.fechas;
    }
    
    
    /**
     * Funcion que se encarga de comprobar si el empleado esta enActivo o no 
     * @return valor booleano en funcion de la anterior premisa
     */
    public boolean estaEnActivo(){
        
        return disponibilidad==Disponibilidad.TRABAJANDO && vinculacion==Vinculacion.CONTRATADO;
        
    }
    public IdEmpleado getIdentificadorEmpleado() {
        return identificadorEmpleado;
    }
    

    /**
     * Permite construir un String a traves de un objeto de tipo JSON
     * @return  string del empleado que se va componiendo segun se van anidiendo los diferentes campos correspondientes
     * @throws uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException
     */
    public String toJSON() throws ErrorSistemaException{
        
        String empleadoJSON = "";
        
        JsonObject json = Json.createObjectBuilder()
                .add("nif", getNif().toString())
                .add("nombre", getNombre())
                .add("email", getEmail())
                .add("puesto", getPuesto().toString())
                .add("disponibilidad", getDisponibilidad().toString())
                .add("vinculacion", getVinculacion().toString())
                .build();
        
        try(
                
                StringWriter stringWriter = new StringWriter();
                JsonWriter writer = Json.createWriter(stringWriter);
                
        ){
            
            writer.writeObject(json);
            empleadoJSON = stringWriter.toString();
            
            
        }catch (java.io.IOException e){
            
            throw new ErrorSistemaException("Error al construir el JSON", e);
        }
        
        
        return empleadoJSON;
           
    }
    
  
}
