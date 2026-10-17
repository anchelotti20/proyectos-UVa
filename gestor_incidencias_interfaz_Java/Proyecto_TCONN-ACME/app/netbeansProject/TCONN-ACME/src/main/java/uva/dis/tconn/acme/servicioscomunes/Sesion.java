package uva.dis.tconn.acme.servicioscomunes;

import uva.dis.tconn.acme.servicioscomunes.datatypes.IdEmpleado;

/**
 * 
 * Clase que representa la sesión de un empleado en el sistema. Permite almacenar y 
 * acceder al ID del empleado actualmente autenticado, así como cerrar la sesión cuando 
 * sea necesario
 * 
 * Se implementa el patron Singleton
 * 
 * 
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */

public class Sesion {
    
    private static Sesion instanciaSesion;//aplicando Singleton

    private static IdEmpleado idEmpleadoActual;
    
    /**
     * Constructor privado para evitar varias instancias y hacer que la sesion sea unica
     */
    private Sesion(){
        
    }
    /**
     * Permite modificar el identificador del empleado que se encuentra logueado 
     * @param idEmpleado para modificar
     */
    public static synchronized void setIdEmpleadoActual(IdEmpleado idEmpleado){
        idEmpleadoActual = idEmpleado;
    }
    /**
     * Obtiene el identificador del empleado de la sesion
     * @return identificador de empleado
     */
    public synchronized IdEmpleado getIdEmpleadoActual(){
        return idEmpleadoActual;
    }
    
    /**
     * Cierra la sesion
     */
    public static synchronized void cerrarSesion(){
        idEmpleadoActual = null;
    }
    /**
     * Obtiene la instancia de la sesion para poder aplicar el patron singleton correctamentente
     * si la instancia era nula, entonces crea una sesion, en caso contrario la devuelvo
     * @return instanciaSesion
     */
    public static synchronized Sesion getInstancia(){
        if(instanciaSesion==null){
            instanciaSesion= new Sesion();
        }
        return instanciaSesion;
    }
}
