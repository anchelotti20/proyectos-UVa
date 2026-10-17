/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

import java.time.LocalDate;
import uva.dis.tconn.acme.servicioscomunes.datatypes.Direccion;
import uva.dis.tconn.acme.servicioscomunes.datatypes.IdContrato;

/**
 *
 * Clase que modela un contrato
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class Contrato {
    
    private IdContrato numeroContrato;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Direccion ubicacion;
    private boolean activo;
    /**
     * Construye un contrato
     * @param numeroContrato Id contrato
     * @param fechaInicio de tipo LocalDate
     * @param fechaFin de tipo LocalDate, puede no existir
     * @param ubicacion Direccion
     * @param activo booleano
     * @throws IllegalArgumentException si numeroContrato es nulo
     * @throws IllegalArgumentException si fechaIncio es nulo
     * @throws IllegalArgumentException si ubicacion es nula
     * @throws IllegalArgumentException si activo es nulo
     */
    public Contrato(IdContrato numeroContrato,LocalDate fechaInicio,LocalDate fechaFin,
            Direccion ubicacion,boolean activo){
        
            esNulo(numeroContrato);
            esNulo(fechaInicio);
            esNulo(ubicacion);
            esNulo(activo);
            
            this.numeroContrato=numeroContrato;
            this.fechaInicio=fechaInicio;
            this.ubicacion=ubicacion;
            this.activo=activo;
        
        
        
    }
     
    /**
     * Getter del identificador de contrato
     * @return numero de contrato
     */
    public IdContrato getIdContrato(){
        return this.numeroContrato;
    }
    /**
     * Obtiene la fecha de inicio del contrato
     * @return fechaIncio
     */
    public LocalDate getFechaInicio(){
        return this.fechaInicio;
    }
    
    /**
     * Obtiene la fecha de fin de un contrato(si es que existe)
     * @return fecha fin 
     */
    public LocalDate getFechaFin(){
        return this.fechaFin;
    }
    /**
     * Devuelve la direccion asociada a un contrato
     * @return ubicacion
     */
    public Direccion getDireccion(){
        return this.ubicacion;
    }
    /**
     * Obtiene si un usuario tiene activo el contrato
     * @return activo
     */
    public boolean getActivo(){
        return this.activo;
    }
    /**
     * Metodo que lanza excepcion si el objeto es nulo
     * @param obj objeto a comprobar si es nulo
     * @throws IllegalArgumentException si objeto es nulo
     */
    private void esNulo(Object obj){
        if(obj==null){
            throw new IllegalArgumentException("Campo nulo.");
        }
    }
    
}
