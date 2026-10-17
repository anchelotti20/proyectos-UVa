package uva.dis.tconn.acme.negocio.modelos;

import uva.dis.tconn.acme.servicioscomunes.datatypes.CoordenadasGPS;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Modela el turno de trabajo de un técnico, incluyendo su vigencia temporal
 * y el acceso delegado a la información de su base y zona.
 */
public class TurnoTecnico {

    private int idTurno;
    private LocalDate fechaInicio;
    private LocalTime horaInicio;
    private LocalDate fechaFin;
    private LocalTime horaFin;
    private Tecnico tecnico;

    /**
     * Constructor con validaciones Fail-Fast.
     */
    public TurnoTecnico(int idTurno, LocalDate fechaInicio, LocalTime horaInicio, LocalDate fechaFin, LocalTime horaFin, Tecnico tecnico) {
        
        esNulo(tecnico);
        esNulo(fechaInicio);
        esNulo(horaInicio);
        esNulo(fechaFin);
        esNulo(horaFin);
       
        
        this.idTurno = idTurno;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.tecnico = tecnico;
    }
    
    
    /**
     * Obtiene el identificador de la zona delegando la petición al técnico.
     * El turno no conoce la zona directamente, pero sabe quién sí la conoce.
     */
    public String getIdZona() {
        return this.tecnico.getIdZona();
    }

    /**
     * Obtiene la ubicación GPS de la base delegando la petición al técnico.
     */
    public CoordenadasGPS getUbicacionBase() {
        return this.tecnico.getUbicacionBase();
    }

    
    public int getIdTurno() {
        return idTurno;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }
    
    private void esNulo (Object objeto){

            if (objeto == null){

               throw new IllegalArgumentException("Campo de turno tecnico nulo");
           }

    }
}