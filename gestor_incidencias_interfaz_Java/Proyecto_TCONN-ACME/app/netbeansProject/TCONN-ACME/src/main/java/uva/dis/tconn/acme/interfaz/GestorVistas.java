package uva.dis.tconn.acme.interfaz;

import uva.dis.tconn.acme.interfaz.pares_vista_controlador.empleado.IdentificarUsuarioVista;
import uva.dis.tconn.acme.interfaz.pares_vista_controlador.supervisor.ConsultarIncidenciasVista;
import uva.dis.tconn.acme.interfaz.pares_vista_controlador.supervisor.DetallesIncidenciaVista;
import uva.dis.tconn.acme.interfaz.pares_vista_controlador.tecnico.CambiarDisponibilidadVista;
import javax.swing.JFrame;
import uva.dis.tconn.acme.interfaz.pares_vista_controlador.coordinador.AtenderLlamadaVista;
import uva.dis.tconn.acme.interfaz.pares_vista_controlador.coordinador.RegistrarIncidenciaVista;

/**
 * Clase encargada de gestionar las vistas de la aplicación usando el patrón Singleton, 
 * permitiendo mostrar y ocultar las diferentes ventanas según sea necesario
 */
public class GestorVistas {

    
    private static GestorVistas instancia;
    
    private JFrame vistaActual;

    
    private GestorVistas() {}

    
    public static GestorVistas getInstancia() {
        if (instancia == null) {
            instancia = new GestorVistas();
        }
        return instancia;
    }

    /**
     * Muestra la vista de login
     */
    public void mostrarVistaLogin() {
        if (vistaActual != null) {
            vistaActual.setVisible(false);
            vistaActual.dispose();
        }

        vistaActual = new IdentificarUsuarioVista();
        vistaActual.setLocationRelativeTo(null); 
        vistaActual.setVisible(true);
    }

    /**
     * Muestra la vista de cambio disponibilidad para el técnico
     */
    public void mostrarVistaCambioDisponibilidad() {
        cerrarVistaActual();

        vistaActual = new CambiarDisponibilidadVista();
        vistaActual.setLocationRelativeTo(null); 
        vistaActual.setVisible(true);
    }

    /**
     * Muestra la vista de consulta de incidencias para el supervisor
     */
    public void mostrarVistaConsultarIncidencias() {
        cerrarVistaActual();

        vistaActual = new ConsultarIncidenciasVista();
        vistaActual.setLocationRelativeTo(null); 
        vistaActual.setVisible(true);
    }

    public void mostrarVistaDetallesIncidencia() {
        DetallesIncidenciaVista vistaDetalles = new DetallesIncidenciaVista();
        vistaDetalles.setLocationRelativeTo(vistaActual);
        vistaDetalles.setVisible(true);
    }
    
    public void mostrarVistaAtenderLlamada(){
        cerrarVistaActual();

        vistaActual = new AtenderLlamadaVista();
        vistaActual.setLocationRelativeTo(null); 
        vistaActual.setVisible(true);
    }
    
    public void mostrarVistaRegistrarIncidencia() {
        RegistrarIncidenciaVista vistaRegistrar = new RegistrarIncidenciaVista();
        vistaRegistrar.setLocationRelativeTo(vistaActual);
        vistaRegistrar.setVisible(true);
    }

    private void cerrarVistaActual(){
        if (vistaActual != null) {
            vistaActual.setVisible(false);
            vistaActual.dispose();
        }
    }
}