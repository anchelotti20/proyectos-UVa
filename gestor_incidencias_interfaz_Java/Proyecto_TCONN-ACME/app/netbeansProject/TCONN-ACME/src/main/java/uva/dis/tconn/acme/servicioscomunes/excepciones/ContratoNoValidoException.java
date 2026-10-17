/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.excepciones;

/**
 * Excepcion para cuando no exista un contrato en vigor que corresponga al
 * numero contrato 4a
 * 
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class ContratoNoValidoException extends Exception {

    /**
     * Constructs an instance of <code>ContratoNoValidoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ContratoNoValidoException(String msg) {
        super(msg);
    }
}
