/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

import java.time.LocalDate;
import java.time.LocalTime;
import uva.dis.tconn.acme.negocio.modelos.enums.NivelCriticidad;
import uva.dis.tconn.acme.negocio.modelos.enums.EstadoIncidencia;
import uva.dis.tconn.acme.servicioscomunes.datatypes.CodigoIncidencia;


/**
 * Clase que modela una incidencia
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */

public class Incidencia {
    
    private CodigoIncidencia codigo;
    private String descripcion;
    private NivelCriticidad criticidad;
    private EstadoIncidencia estado;
    private int tiempoTotalResolucionEnMinutos;
    
    private LlamadaCorrecta llamada;
    /**
     * Construye una incidencia a traves del codigo de incidencia,la descripcion de tipo String,la criticidad
     * el estado de una incidencia,el tiempo de rsolucion en minutos y la llamada asociada a un contrato
     * @param codigo de tipo CodigoIncidencia
     * @param descripcion de tipo String
     * @param criticidad de tipo NivelCriticidad
     * @param estado del tipo EstadoIncidencia
     * @param tiempoTotalResolucionEnMinutos int
     * @param llamada LLamada
     * @throws IllegalArgumentException si codigo es nulo
     * @throws IllegalArgumentException si descripcion es nula
     * @throws IllegalArgumentException si nivel de criticidad es nula
     * @throws IllegalArgumentException si estdo es nulo
     * @throws IllegalArgumentException si llamada es nula
     */
    public Incidencia(CodigoIncidencia codigo,String descripcion,NivelCriticidad criticidad,
            EstadoIncidencia estado,int tiempoTotalResolucionEnMinutos,LlamadaCorrecta llamada){
        
        
        esNulo(codigo);
        esNulo(descripcion);
        esNulo(criticidad);
        esNulo(estado);
        esNulo(llamada);
        
        this.codigo=codigo;
        this.descripcion=descripcion;
        this.criticidad=criticidad;
        this.estado=estado;
        this.tiempoTotalResolucionEnMinutos=tiempoTotalResolucionEnMinutos;
        this.llamada=llamada;
        
        
    }
    
    /**
     * Getter del codigo de incidencia
     * @return codigo
     */
    public CodigoIncidencia getCodigo(){
        return this.codigo;
    }
    
    /**
     * Obtiene la descripcion de una incidencia
     * @return incidencia
     */
    
    public String getDescripcion(){
        return this.descripcion;
    }
    /**
     * Devuelve el nivel de criticidad asociado a una incidencia
     * @return criticidad
     */
    public NivelCriticidad getNivelCriticidad(){
        return this.criticidad;
    }
    /**
     * Obtiene el estado de la incidencia
     * @return estado de la inicidencia
     */
    public EstadoIncidencia getEstadoIncidencia(){
        return this.estado;
    }
    /**
     * Obtiene el tiempo de resolucion de la incidencia
     * @return de tipo entero
     */
    public int getTiempoTotalResolucionEnMinutos(){
        return this.tiempoTotalResolucionEnMinutos;
    }
    
    /**
     * Devuelve la llamada asociada a la incidencia
     * @return llamada
     */
    public LlamadaCorrecta getLlamada(){
        
        
        return this.llamada;
        
    }
    
    /**
     * Devuelve la fecha de incidencia
     * @return fecha
     */
    public LocalDate getFechaIncidencia(){
        return llamada.getFecha();
    }
    /**
     * Obtiene la hora a la que se produce la incidencia
     * @return hora
     */
    public LocalTime getHoraIncidencia(){
        return llamada.getHora();
    }
    /**
     * Metodo que se encarga de lanzar excepciones si el objeto es nulo
     * @param obj objeto a comprobar si es nulo
     * @throws IllegalArgumentException si el objeto es nulo
     */
    private void esNulo(Object obj){
        if(obj==null){
            throw new IllegalArgumentException("Campo nulo.");
        }
    }
}
