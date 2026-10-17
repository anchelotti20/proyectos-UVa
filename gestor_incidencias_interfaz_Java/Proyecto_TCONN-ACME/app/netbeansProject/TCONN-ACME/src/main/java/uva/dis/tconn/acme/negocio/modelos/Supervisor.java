/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

import java.time.LocalDate;

/**
 * Clase que representa un puesto de tipo Supervisor, hereda de puesto
 * @author Marcos Gomez 
 * @author Angel Antolin 
 * @author Elisa Martinez
 */


public class Supervisor extends Puesto {
    
    
    /**
     * Constructor de la clase, que de momento, simplemente con el super de Puesto consigue crearse 
     * @param fechaInicio
     * @param empleado 
     */
    public Supervisor(LocalDate fechaInicio,Empleado empleado){
        super(fechaInicio,empleado);
    }
    
    public Supervisor(){
        
        
    }
    
    @Override
    public String getRolIdentificador(){
        
        return "SUPERVISOR";
        
    }
    
}
