/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

import java.time.LocalDate;

/**
 * Clase que modela un Coordinador
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */

public class Coordinador extends Puesto {
    
    
    /**
     * Construcor de Coordinador, usa el super de Puesto
     * @param fechaInicio
     * @param empleado 
     */
    public Coordinador(LocalDate fechaInicio,Empleado empleado){
        super(fechaInicio,empleado);
    }
    
    public Coordinador(){
        
        super();
    }

    @Override
    public String getRolIdentificador(){
        
        return "COORDINADOR";
        
    }
}
