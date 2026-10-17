/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.excepciones;

/**
 * Excepcion para cuando no existe ningun cliente con asociado a cierto NIF/CIF
 * incicados en el contrato 6a
 * 
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class ClienteNoExisteException extends Exception {

    /**
     * Constructs an instance of <code>ClienteNoExisteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ClienteNoExisteException(String msg) {
        super(msg);
    }
}
