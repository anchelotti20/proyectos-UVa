/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.controladorescasouso;

import java.io.StringReader;

import uva.dis.tconn.acme.negocio.modelos.TurnoTecnico;
import uva.dis.tconn.acme.negocio.modelos.CambioDeSituacionTecnico;
import uva.dis.tconn.acme.negocio.modelos.enums.EstadoTecnico;
import uva.dis.tconn.acme.servicioscomunes.excepciones.TecnicoNoAsignadoException;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

import uva.dis.tconn.acme.negocio.modelos.Base;
import uva.dis.tconn.acme.negocio.modelos.Empleado;
import uva.dis.tconn.acme.servicioscomunes.datatypes.CoordenadasGPS;
import uva.dis.tconn.acme.servicioscomunes.datatypes.IdEmpleado;
import uva.dis.tconn.acme.negocio.modelos.Tecnico;
import uva.dis.tconn.acme.negocio.modelos.ZonaGeografica;
import uva.dis.tconn.acme.negocio.modelos.enums.NivelTecnico;
import uva.dis.tconn.acme.persistencia.daos.CambioDeSituacionDAO;
import uva.dis.tconn.acme.persistencia.daos.TurnoTecnicoDAO;
import uva.dis.tconn.acme.persistencia.daos.ZonaGeograficaDAO;
import uva.dis.tconn.acme.servicioscomunes.Sesion;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.TecnicoNoEnTurnoException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.TecnicosDisponiblesInsuficientesException;

/**
 *
 * Controlador del caso de uso Cmabiar Disponibilidad
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class CambiarDisponibilidadControlador {
    
    private TurnoTecnico turnoActual;
    private Sesion empleadoLogueado;
    
    /**
     * Permite comporbar si esta en turno y en estado correctso
     * @throws TecnicoNoAsignadoException
     * @throws ErrorSistemaException
     * @throws TecnicoNoEnTurnoException 
     */
    public void verificarTurnoYEstado() throws TecnicoNoAsignadoException, ErrorSistemaException, TecnicoNoEnTurnoException{
        
        empleadoLogueado = Sesion.getInstancia();
        
        IdEmpleado idTecnico = empleadoLogueado.getIdEmpleadoActual();
        
        String turnoJSON = TurnoTecnicoDAO.obtenerTurnoActual(idTecnico);
        
        
        this.turnoActual = turnoDesdeJSON(turnoJSON);
        
        String cambioSituacionJSON = CambioDeSituacionDAO.obtenerUltimoCambio(turnoActual.getIdTurno());
        
        CambioDeSituacionTecnico ultimoCambio = cambioDesdeJSON(cambioSituacionJSON, this.turnoActual);
        
        if(!ultimoCambio.estabaAsignado()){
            
            throw new TecnicoNoAsignadoException("El tecnico no se encuentra asignado");
            
        }
        
    }
    
    /**
     * Metodo que permite confirmar el nuevo estado del tecnico
     * @param disponible con la nueva disponibilidad del tecnico
     * @throws TecnicosDisponiblesInsuficientesException
     * @throws ErrorSistemaException 
     */
    public void confirmarNuevoEstado(boolean disponible) throws TecnicosDisponiblesInsuficientesException, ErrorSistemaException{
        
        EstadoTecnico nuevoEstado = EstadoTecnico.DISPONIBLE;
        
        if(!disponible){
            
            String idZona = this.turnoActual.getIdZona();
            int tecnicosDisponibles = ZonaGeograficaDAO.contarTecnicosDisponibles(idZona);
            
            
            if (tecnicosDisponibles < 2){
                
                throw new TecnicosDisponiblesInsuficientesException("No se cumple el mínimo de técnicos. Debes pasar a Disponible");
                
            }
            
            nuevoEstado = EstadoTecnico.NO_DISPONIBLE;
                    
        }
        
        
        CambioDeSituacionTecnico nuevaSituacion = new CambioDeSituacionTecnico(
                                                            nuevoEstado,
                                                            this.turnoActual.getUbicacionBase(),
                                                            LocalDate.now(),
                                                            LocalTime.now(),
                                                            this.turnoActual);
        
        String nuevaSituacionJSON = nuevaSituacion.toJSON();
        CambioDeSituacionDAO.registrarCambio(nuevaSituacionJSON);
           
    }
    
    /**
     *  Permite obtener un turno del tecnioc desde el JSON
     * @param json desde donde se va parseando el turno del tecnico que lo tiene
     * @return TurnoTecnico con el nuevo turno del tecnico 
     */
    private TurnoTecnico turnoDesdeJSON (String json){
        
        JsonReaderFactory factory = Json.createReaderFactory(null);
        
        try(JsonReader reader = factory.createReader(new StringReader(json));){
            
            JsonObject turnoJSON = reader.readObject();
            
            
            int idTurno = turnoJSON.getInt("id_turno");
            LocalDate fechaInicio = LocalDate.parse(turnoJSON.getString("fecha_inicio"));
            LocalTime horaInicio = LocalTime.parse(turnoJSON.getString("hora_inicio"));
            LocalDate fechaFin = LocalDate.parse(turnoJSON.getString("fecha_fin"));
            LocalTime horaFin = LocalTime.parse(turnoJSON.getString("hora_fin"));
            String textoNivel = turnoJSON.getString("nivel_tecnico");
            LocalDate fechaInicioPuesto = LocalDate.parse(turnoJSON.getString("fecha_inicio_puesto"));
            int idBase = turnoJSON.getInt("id_base");
            String idZona = turnoJSON.getString("id_zona");
            double latitud = turnoJSON.getJsonNumber("lat").doubleValue();
            double longitud = turnoJSON.getJsonNumber("lon").doubleValue();
            
            NivelTecnico nivel = NivelTecnico.desdeBD(textoNivel);
            ZonaGeografica zona = new ZonaGeografica(idZona);
            CoordenadasGPS ubicacion = new CoordenadasGPS(latitud, longitud);
            Base base = new Base(idBase, zona, ubicacion);
            
            
            
            
            Empleado empleadoFalso = new Empleado(empleadoLogueado.getIdEmpleadoActual());
            
            
            Tecnico tecnico = new Tecnico(fechaInicioPuesto, nivel, base, empleadoFalso);
            
            return new TurnoTecnico(idTurno, fechaInicio, horaInicio, fechaFin, horaFin, tecnico);
            
            
        }
    
        
    }
    
    /**
     * Se encarrga de obtener el cambio de turno desde un objeto JSON y el turno de este
     * @param json objeto JSON desde donde se parsea
     * @param turno turno del tecnico
     * @return CambioDeSituacion con el nuevo turno
     */
    private CambioDeSituacionTecnico cambioDesdeJSON (String json, TurnoTecnico turno){
        
        JsonReaderFactory factory = Json.createReaderFactory(null);
        
        try(JsonReader reader = factory.createReader(new StringReader(json));){
            
            JsonObject cambioJSON = reader.readObject();
        
            String estadoString = cambioJSON.getString("estado");
            double latitud = cambioJSON.getJsonNumber("lat").doubleValue();
            double longitud = cambioJSON.getJsonNumber("lon").doubleValue();
            LocalDate fecha = LocalDate.parse(cambioJSON.getString("fecha"));
            LocalTime hora = LocalTime.parse(cambioJSON.getString("hora"));
            
            EstadoTecnico estado = EstadoTecnico.desdeBD(estadoString);
            
            CoordenadasGPS ubicacion = new CoordenadasGPS(latitud, longitud);
            
            return new CambioDeSituacionTecnico(estado, ubicacion, fecha, hora, turno);
        }
            
            
    }
    
   
    
    
}
