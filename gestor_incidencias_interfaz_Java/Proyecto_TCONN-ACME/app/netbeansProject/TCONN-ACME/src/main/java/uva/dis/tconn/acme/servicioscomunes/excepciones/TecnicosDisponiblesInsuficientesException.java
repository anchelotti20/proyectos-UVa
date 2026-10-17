/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.excepciones;

/**
 * Excepcion para cuando se incumple la regla de negocio 3 (al menos 2 tecnicos disponibles por zona)
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class TecnicosDisponiblesInsuficientesException extends Exception {


    /**
     * Constructs an instance of
     * <code>TecnicosDisponiblesInsuficientesException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public TecnicosDisponiblesInsuficientesException(String msg) {
        super(msg);
    }
}
