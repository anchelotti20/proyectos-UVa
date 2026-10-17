/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.excepciones;

/**
 * Excepcion para cuando un tecnico no esta en turno
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class TecnicoNoEnTurnoException extends Exception {

    /**
     * Constructs an instance of <code>TecnicoNoEnTurnoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TecnicoNoEnTurnoException(String msg) {
        super(msg);
    }
}
