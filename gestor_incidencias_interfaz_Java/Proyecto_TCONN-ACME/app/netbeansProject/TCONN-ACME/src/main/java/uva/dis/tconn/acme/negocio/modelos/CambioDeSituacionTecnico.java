package uva.dis.tconn.acme.negocio.modelos;

import uva.dis.tconn.acme.servicioscomunes.datatypes.CoordenadasGPS;
import uva.dis.tconn.acme.negocio.modelos.enums.EstadoTecnico;

import java.time.LocalDate;
import java.time.LocalTime;

import java.io.StringWriter;



import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;

/**
 * Modela el cambio de situacion de un tecnico 
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
    public class CambioDeSituacionTecnico {
     
        private EstadoTecnico estado;
        private CoordenadasGPS ubicacion;
        private LocalDate fecha;
        private LocalTime hora;
        private TurnoTecnico turno;
        /**
         * Constructor del cambio de base
         * @param estado referente al estado del tecnico
         * @param ubicacion coordenadasGPS
         * @param fecha 
         * @param hora
         * @param turno 
         * @throws IllegalArgumentException si estado es nulo
         * @throws IllegalArgumentException si ubicacion es nula
         * @throws IllegalArgumentException si fecha es nula
         * @throws IllegalArgumentException si hora es nula
         * @throws IllegalArgumentException si turno es nulo
         */
        public CambioDeSituacionTecnico(EstadoTecnico estado, CoordenadasGPS ubicacion, LocalDate fecha, LocalTime hora, TurnoTecnico turno){
         
            esNulo(estado);
            esNulo(ubicacion);
            esNulo(fecha);
            esNulo(hora);
            esNulo(turno);


           this.estado = estado;
           this.ubicacion = ubicacion;
           this.fecha = fecha;
           this.hora = hora;
           this.turno = turno;



        }
        /**
         * Obteiene el estado del estado en que se encuentra el tecnico
         * @return Estado del tecnico
         */
        public EstadoTecnico getEstado(){

            return this.estado;

        }
        /**
         * Devuelve la ubicacion en forma de GPS del tecnico
         * @return ubicacion
         */
        public CoordenadasGPS getUbicacion(){
            return this.ubicacion;
        }
        /**
         * Se encarga de obtener de la fecha 
         * @return fecha
         */
        public LocalDate getFecha(){

            return this.fecha;
        }
        /**
         * Getter de cambio de situacion para devolver la hora 
         * @return hora de tipo LocalTime 
         */
        public LocalTime getHora(){
            return this.hora;
        }
        /**
         * Devuleve el turno del tecnico asociado
         * @return turno de tipo TurnoTecnico
         */
        public TurnoTecnico getTurno(){
            
            return this.turno;
        }
        /**
         * Devuelve true o false en funcion de si se encontraba asigando o no
         * @return 
         */
        public boolean estabaAsignado(){
            
            return this.estado == EstadoTecnico.ASIGNADO;
            
        }
        /**
         * Se encarga de converti y devolver un String desde JSON, se va parseando los diferentes
         * campos que son necesarios
         * @return cambioTecnicoJSON en formato String
         */
        public String toJSON() throws ErrorSistemaException{

            String cambioTecnicoJSON = "";

            JsonObject json = Json.createObjectBuilder()
                   .add("id_turno", getTurno().getIdTurno())
                   .add("estado", getEstado().getValorBD())
                   .add("lat", getUbicacion().getLatitud())
                   .add("lon", getUbicacion().getLongitud())
                   .add("fecha", getFecha().toString())
                   .add("hora", getHora().toString())
                   .build();

            try(

                   StringWriter stringWriter = new StringWriter();
                   JsonWriter writer = Json.createWriter(stringWriter);

            ){

               writer.writeObject(json);
               cambioTecnicoJSON = stringWriter.toString();


            }catch (java.io.IOException e){

               throw new ErrorSistemaException("Error al construir el JSON", e);
            }


            return cambioTecnicoJSON;

        }


        /**
         * Debe lanzar excepciones en caso de ser objetos nulos, lo hace escribiendo un mensaje informando del motivo de 
         * la excepcion
         * @param objeto a comprobar si es nulo
         * @throws IllegalArgumentException si efectivamente el objeto es nulo
         */
        private void esNulo (Object objeto){

            if (objeto == null){

               throw new IllegalArgumentException("Campo de cambio de situacion nulo");
           }

       }
    
    
}
