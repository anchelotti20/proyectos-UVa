/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que modela una llamada
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */

public class Llamada {
    
    private LocalDate fecha;
    private int numeroTelefono;
    private LocalTime hora;
    private int duracion;
    private Coordinador coordinador;
    
    /**
     * Construye una llamada a traves de fecha, numero de telfono, hora, duracion, y un coordinador que atiende la llamada
     * @param fecha fecha en la que se registra la llamada
     * @param numeroTelefono numero de telefono que realiza la llamada
     * @param hora hora a la que se produce
     * @param duracion duracion de la llamada
     * @param coordinador coordinador que atiende la llamada
     * @throws IllegalArgumentException si fecha es nulo
     * @throws IllegalArgumentException si numeroTelefono es nulo
     * @throws IllegalArgumentException si hora es nula
     * @throws IllegalArgumentException si si duracion es nulo
     * @throws IllegalArgumentException si coordinador es nulo
     * @throws IllegalArgumentException si telefono es menor que cero
     * @throws IllegalArgumentException si la duracion es menor o igual que cero
     */
    public Llamada(LocalDate fecha,int numeroTelefono,LocalTime hora,int duracion,Coordinador coordinador){
        
        esNulo(fecha);
        esNulo(numeroTelefono);
        esNulo(hora);
        esNulo(duracion);
        esNulo(coordinador);
        
        this.fecha=fecha;
        
        if(numeroTelefono<0){
            throw new IllegalArgumentException("Numero de telefono incorrecto");
        }
        
        this.numeroTelefono=numeroTelefono;
        this.hora=hora;
        
        if(duracion<0){
            throw new IllegalArgumentException("La duracion de una llamada no puede ser < 0");
        }
        this.duracion=duracion;
        this.coordinador=coordinador;
        
        
    }
    /**
     * Obtiene la fecha en la que se produce la llamada
     * @return fecha de tipo LocalDate
     */
    public LocalDate getFecha(){
        return this.fecha;
    }
    /**
     * Devuelve el numero de telefono que realiza la llamada
     * @return numeroTelefono de tipo int
     */
    public int getNumeroTelefono(){
        return this.numeroTelefono;
    }
    /**
     * Obtiene la hora a la que ocurre la llamada
     * @return hora, de tipo LocalTime
     */
    public LocalTime getHora(){
        return this.hora;
    }
    /**
     * Consulta y devuelve la duracion de la llamada
     * @return duracion de tipo int
     */
    public int getDuracion(){
        return this.duracion;
    }
    /**
     * Obteiene el coordinador que atiende la llamada
     * @return coordinador
     */
    public Coordinador getCoordinadorLlamada(){
        return this.coordinador;
    }
    /**
     * Metodo que lanza excepcion si el objeto es nulo
     * @param obj objeto a comprobar si es nulo
     * @throws IllegalArgumentException si el objeto es nulo
     */
    private void esNulo(Object obj){
        if(obj==null){
            throw new IllegalArgumentException("Campo nulo.");
        }
    }
    
    
}
