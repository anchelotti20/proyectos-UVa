/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.excepciones;

/**
 * Excepcion para cuando la password y el nif no corresponden
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class CredencialesInvalidasException extends Exception {

    /**
     * Constructs an instance of <code>CredencialesInvalidasException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CredencialesInvalidasException(String msg) {
        super(msg);
    }
}
