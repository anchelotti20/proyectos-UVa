/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.excepciones;

/**
 *
 * Excepcion para cuando no existen incidencias en la fecha introducida
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class NoHayIncidenciasException extends Exception {

    public NoHayIncidenciasException(String msg) {
        super(msg);
    }
    
}
