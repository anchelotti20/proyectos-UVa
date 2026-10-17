/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.excepciones;

/**
 * Excepciones por error en BD, fallo al crear json, stringWriter, etc
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class ErrorSistemaException extends Exception {

    /**
     * Constructs an instance of <code>ErrorSistemaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ErrorSistemaException(String msg, Throwable causa) {
        super(msg, causa);
    }
}
