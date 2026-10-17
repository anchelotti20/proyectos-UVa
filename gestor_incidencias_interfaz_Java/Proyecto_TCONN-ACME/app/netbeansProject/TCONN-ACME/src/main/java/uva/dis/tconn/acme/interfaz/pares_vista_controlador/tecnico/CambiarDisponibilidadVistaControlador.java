package uva.dis.tconn.acme.interfaz.pares_vista_controlador.tecnico;

import uva.dis.tconn.acme.negocio.controladorescasouso.CambiarDisponibilidadControlador;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.TecnicoNoAsignadoException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.TecnicoNoEnTurnoException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.TecnicosDisponiblesInsuficientesException;
/**
 * Controlador de la vista del caso de uso de cambio de vista
 * @author marcos Gomez
 * @author Angel Antolin 
 * @author Elisa Martinez
 */
public class CambiarDisponibilidadVistaControlador {

    private CambiarDisponibilidadVista vista;
    private CambiarDisponibilidadControlador controlador;
    
    /**
     * Contrcutor del controlador
     * @param vista 
     */
    public CambiarDisponibilidadVistaControlador(CambiarDisponibilidadVista vista){
        if(vista==null){
            throw new IllegalArgumentException("Vista no puede ser nula");
        }
        
        this.vista=vista;
        controlador=new CambiarDisponibilidadControlador();
       
    }
    /**
     * Se encarga de procesar el evento de llegada llmando al controlador, en caso de excepciones se muestra
     * el mensaje correspondiente en la vista
     */
    public boolean procesaEventoLlegadaBase(){
        
        try{
            
            controlador.verificarTurnoYEstado();
            vista.mostrarMensajePositivo("Se esta registrando su cambio de estado");
            return true;
        
        }catch(TecnicoNoAsignadoException t){
            
            vista.mostrarMensajeError("Tecnico no asignado");
            return false;
        
        }catch(ErrorSistemaException e){
            vista.mostrarMensajeError("Error sistema");
            return false;
            
        }catch(TecnicoNoEnTurnoException n){
            vista.mostrarMensajeError("El tecnico no esta en turno");
            return false;
        }
        
    }
    /**
     * Se encarga de actualizar el nuevo estado del tecnico llamando al controlador
     * En caso de excpeciones se muestran los correspondientes mensjaes en la vista
     * @param disponible booleando que muestra si el tecnico cambia de situacion a disponible o no 
     */
    public void procesaEventoConfirmarNuevoEstado(boolean disponible){
        
        try{
            controlador.confirmarNuevoEstado(disponible);
            vista.mostrarMensajePositivo("El nuevo estado ha sido guardado correctamente");
        }catch(TecnicosDisponiblesInsuficientesException t){
            vista.mostrarMensajeError("No hay tecnicos disponibles suficientes,debes estar disponible obligatoriamente");
            vista.activarDisponible();
        }catch(ErrorSistemaException e){
            vista.mostrarMensajeError("Error en el sistema");
            
        }
        
    }
    
}
