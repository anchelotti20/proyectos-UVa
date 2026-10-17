/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.excepciones;

/**
 * Excepcion para cuando el tecnico no estaba asignado a una incidencia
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class TecnicoNoAsignadoException extends Exception {

   
    /**
     * Constructs an instance of <code>TecnicoNoAsignadoException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public TecnicoNoAsignadoException(String msg) {
        super(msg);
    }
}
