/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Modela una llamada correcta
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class LlamadaCorrecta extends LlamadaContrato{
    
    
    public LlamadaCorrecta(LocalDate fecha, int numeroTelefono, LocalTime hora, int duracion,
            Coordinador coordinador,Contrato contrato){
        
        super(fecha, numeroTelefono, hora, duracion, coordinador, contrato);
        
    }
    
}
