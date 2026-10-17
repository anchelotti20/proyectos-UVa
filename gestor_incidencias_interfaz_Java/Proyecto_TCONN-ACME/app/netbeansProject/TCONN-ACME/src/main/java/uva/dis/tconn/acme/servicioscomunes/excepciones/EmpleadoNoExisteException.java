/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.excepciones;

/**
 * Excepcion para cuando el nif no existe
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class EmpleadoNoExisteException extends Exception {

    /**
     * Constructs an instance of <code>EmpleadoNoExisteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmpleadoNoExisteException(String msg) {
        super(msg);
    }
}
