/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Modela una llamada Inconsistente
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class LlamadaInconsistente extends LlamadaContrato{
    
    private String datosInconsistentes;
    
    
    public LlamadaInconsistente(LocalDate fecha, int numeroTelefono, LocalTime hora, int duracion,
            Coordinador coordinador,Contrato contrato, String datosInconsistentes){
        
        
        super(fecha, numeroTelefono, hora, duracion, coordinador, contrato);
        
        esNulooVacio(datosInconsistentes);
        
        this.datosInconsistentes = datosInconsistentes;
        
        
    }
    
    public String getDatosInconsistentes(){
        
        
        return this.datosInconsistentes;
    }
    
    
    
    private void esNulooVacio (String texto){

            if (texto == null || texto.isEmpty()){

               throw new IllegalArgumentException("Campo de llamada nulo");
           }

    }
    
}
