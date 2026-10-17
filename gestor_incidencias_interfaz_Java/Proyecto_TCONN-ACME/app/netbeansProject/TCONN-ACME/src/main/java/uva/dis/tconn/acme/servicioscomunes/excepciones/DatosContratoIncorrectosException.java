/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.excepciones;

/**
 * Excepcion para cuando los datos no concuerdan con el contrato
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class DatosContratoIncorrectosException extends Exception {

    /**
     * Constructs an instance of <code>DatosContratoIncorrectosException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DatosContratoIncorrectosException(String msg) {
        super(msg);
    }
}
