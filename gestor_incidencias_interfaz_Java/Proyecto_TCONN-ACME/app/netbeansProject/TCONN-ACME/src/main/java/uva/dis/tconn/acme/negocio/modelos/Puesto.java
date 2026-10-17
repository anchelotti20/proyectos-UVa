/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

import java.time.LocalDate;
import uva.dis.tconn.acme.servicioscomunes.datatypes.IdEmpleado;

/**
 * Clase que modela un puesto de un empleado
 * @author Marcos Gomez Medina
 * @author Angel Antolin 
 * @author Elisma Martinez
 */

public abstract class Puesto {
    private LocalDate fechaInicio;
    private Empleado empleado;
    
    /**
     * Constructor de Puesto
     * @param fechaInicio de tipo LocalDate para la fecha de incio
     * @param empleado para el Empleado al que esta asociado el puesto
     */
    protected Puesto(LocalDate fechaInicio,Empleado empleado){
        this.fechaInicio=fechaInicio;
        this.empleado=empleado;
    }
    
    
    protected Puesto(){
        
        
    }
    
    /**
     * Permite conseguir la fecha de inicio en el puesto
     * @return la fecha de inicio
     */
    public LocalDate getFechaInicio(){
        return fechaInicio;
    }
    
    
    public IdEmpleado getIdEmpleado(){
        
        return this.empleado.getIdentificadorEmpleado();
        
    }
    
    
    public abstract String getRolIdentificador();
    
    
}
