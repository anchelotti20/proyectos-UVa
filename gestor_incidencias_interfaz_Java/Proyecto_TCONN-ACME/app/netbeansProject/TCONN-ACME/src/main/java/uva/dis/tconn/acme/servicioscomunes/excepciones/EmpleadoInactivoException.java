/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.excepciones;

/**
 * Excepcion para cuando los empleados no estan activos
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class EmpleadoInactivoException extends Exception {

    

    /**
     * Constructs an instance of <code>EmpleadoInactivoException</code> with the
     * specified detail message.
     * @param msg the detail message.
     */
    public EmpleadoInactivoException(String msg) {
        super(msg);
    }
}
