/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * Clase que modela una llamada con un contrato asociado, hereda de la clase llamada pero contiene un contrato
 * @author Marcos Gomez
 * @author Angel Antolin+
 * @author Elisa Martinez
 */
public class LlamadaContrato extends Llamada {
    
    private Contrato contrato;
    /**
     * Constytrutye un objeto LlamadaContrato heredando del contrato de su clase madre y el contrato
     * @param fecha de tipo LocalDate
     * @param numeroTelefono siendo de tipo int
     * @param hora perteneciente a tipo LocalTime
     * @param duracion siendo un int
     * @param coordinador de tipo Coordinador
     * @param contrato de tipo Contrato
     * @throws IllegalArgumentException si contrato es nulo
     */
    public LlamadaContrato(LocalDate fecha, int numeroTelefono, LocalTime hora, int duracion,
            Coordinador coordinador,Contrato contrato) {
        super(fecha, numeroTelefono, hora, duracion, coordinador);
        
        if(contrato==null){
            throw new IllegalArgumentException("Contrato no puede ser nulo");
        }
        this.contrato=contrato;
    }
    /**
     * Obtiene el contrato asociado
     * @return contrato
     */
    public Contrato getContrato(){
        return this.contrato;
    }
    
}
