/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.excepciones;

/**
 *
 * Excepcion para tiempo no correcto en la consulta de incidencias
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class TiempoInvalidoException extends Exception {
    /**
     * Constructor de la excpecion que hereda de la excepcion general
     * @param msg 
     */
    public TiempoInvalidoException(String msg) {
        super(msg);
    }
}
    

