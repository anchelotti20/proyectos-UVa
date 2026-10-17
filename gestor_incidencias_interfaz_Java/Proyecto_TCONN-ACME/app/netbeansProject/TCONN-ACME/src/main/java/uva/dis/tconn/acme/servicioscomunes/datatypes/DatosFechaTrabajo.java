/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.datatypes;

import java.time.LocalDate;

/**
 *
 * 
 */
public class DatosFechaTrabajo {
    
    private LocalDate fechaIncorporacion;
    private LocalDate fechaFinContrato;
    
    public DatosFechaTrabajo(LocalDate fechaIncorporacion,LocalDate fechaFinContrato){
        
        if(fechaIncorporacion==null){
            throw new IllegalArgumentException("Las fecha de incorporacion no puede ser nula.");
        }
        
        this.fechaIncorporacion=fechaIncorporacion;
        this.fechaFinContrato=fechaFinContrato;
        
    }
    
    public LocalDate getFechaIncorporacion(){
        
        return this.fechaIncorporacion;
        
    }
    
    public LocalDate getFechaFinContrato(){
        
        return this.fechaFinContrato;
        
    }
    
    
}
