/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * Clase que modela una Llamada Incorrecta heredando su comportamiento(cosntructor) de la clase madre Llamada
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class LlamadaIncorrecta extends Llamada{
    /**
     * Cosntructor de LLamadaIncorrecta
     * @param fecha tipo LocalDate
     * @param numeroTelefono tipo int
     * @param hora siendo un LocalTime
     * @param duracion de tipo int
     * @param coordinador siendo un coordinador que atiende la llamada de tipo Coordinador
     */
    public LlamadaIncorrecta(LocalDate fecha, int numeroTelefono, LocalTime hora, int duracion, Coordinador coordinador) {
        super(fecha, numeroTelefono, hora, duracion, coordinador);
    }
    
}
