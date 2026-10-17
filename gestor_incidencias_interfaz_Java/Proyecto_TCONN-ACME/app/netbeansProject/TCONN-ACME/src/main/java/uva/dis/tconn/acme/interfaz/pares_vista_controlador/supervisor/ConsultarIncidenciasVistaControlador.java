/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.interfaz.pares_vista_controlador.supervisor;

import java.io.StringReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

import uva.dis.tconn.acme.negocio.modelos.enums.EstadoIncidencia;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.NoHayIncidenciasException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.TiempoInvalidoException;
import uva.dis.tconn.acme.interfaz.GestorVistas;
import uva.dis.tconn.acme.negocio.controladorescasouso.ConsultarIncidenciasControlador;

/**
 * Controlador de la vista de consultar incidencias, se encarga de procesar
 * los eventos generados en la vista y comunicarse con el controlador de dominio
 * para realizar el proceso de consulta de incidencias
 * 
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class ConsultarIncidenciasVistaControlador {

    private ConsultarIncidenciasVista vista;
    private ConsultarIncidenciasControlador controlador;

    /**
     * Constructor del controlador, recibe la vista asociada para poder interactuar
     * con ella
     * 
     * @param vista La vista de consultar incidencias asociada a este controlador
     */
    public ConsultarIncidenciasVistaControlador(ConsultarIncidenciasVista vista) {
        this.vista = vista;
        this.controlador = new ConsultarIncidenciasControlador();
    }

    /**
     * Procesa el evento de confirmación de consulta
     */
    public void procesaConfirmarConsulta() {
        LocalDate fecha;
        try{
            fecha = LocalDate.of(vista.getAnio(), vista.getMes(), vista.getDia());
            
        } catch(DateTimeException e){
            vista.mostrarMensajeError("La fecha seleccionada es inválida.");
            return;
        }
        int estadoSeleccionado = vista.getEstadoSeleccionado();

        List<EstadoIncidencia> filtros;

        switch (estadoSeleccionado) {
            case 1:
                filtros = List.of(EstadoIncidencia.RESUELTA_REMOTAMENTE);
                break;
            case 2:
                filtros = List.of(EstadoIncidencia.RESUELTA_IN_SITU);
                break;
            case 3:
                filtros = List.of(EstadoIncidencia.SIN_RESOLVER, EstadoIncidencia.PARCIALMENTE_RESUELTA,
                        EstadoIncidencia.ESCALADA_A_ESPECIALISTA);
                break;
            default:
                filtros = null;
                break;
        }

        try {

            List<String> incidencias = controlador.obtenerIncidencas(fecha, filtros);

            String[] datosIncidencias = convertirArray(incidencias);
            vista.mostrarIncidencias(datosIncidencias);

        } catch (TiempoInvalidoException t) {

            vista.mostrarMensajeError("La fecha es posterior a la fecha actual.");

        } catch (ErrorSistemaException e) {

            vista.mostrarMensajeError("Error en el sistema.");

        } catch (NoHayIncidenciasException n) {

            vista.mostrarMensajeError("No hay incidencias para la fecha y filtro seleccionados.");
        }

    }

    /**
     * Procesa el evento de selección de detalles de una incidencia
     */
    public void procesarEventoDetalles() {
        GestorVistas.getInstancia().mostrarVistaDetallesIncidencia();
    }

    private String[] convertirArray(List<String> incidencias) {
        String[] array = new String[incidencias.size()];

        JsonReaderFactory factory = Json.createReaderFactory(null);

        for (int i = 0; i < incidencias.size(); i++) {
            try (JsonReader reader = factory.createReader(new StringReader(incidencias.get(i)))) {
                JsonObject incidenciaJSON = reader.readObject();

                String cod = incidenciaJSON.getString("codigo");
                String hora = incidenciaJSON.getString("hora");
                String criticidad = incidenciaJSON.getString("criticidad");
                String estado = incidenciaJSON.getString("estado");

                array[i] = cod + " - " + hora + " - " + criticidad + " - " + estado;
            }
        }
        return array;
    }

}
