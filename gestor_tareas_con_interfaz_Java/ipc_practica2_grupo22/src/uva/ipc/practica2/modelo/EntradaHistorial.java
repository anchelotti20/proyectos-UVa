/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.ipc.practica2.modelo;

import java.util.Date;


/**
 * Clase que nos permite poder crear los objetos entradaHistorial que iran asociados en un arrayList posteriormente 
 * para poder trabajr correctamente en el modelo con los datos precisos
 * @author Marcos Gomez Medina
 * @author Angel Antonlin Caramazana
 */
public class EntradaHistorial {
    
    private String nombreTarea;
    private String descripcionTarea;
    private Date fechaTarea;
    private int porcentajeTarea;
    private String prioridadTarea;
    private String completadaTarea;
    
    /**
     * Iniciliza EntradaHistorial con los diferentes atributos necesarios para las tareas
     * @param nombreTarea, string con el nombre de la tarea
     * @param descripcionTarea, string con descripcion sobre la tarea
     * @param fechaTarea, Date on la fecha de la tarea
     * @param porcentajeTarea, int asociado al grado del 0 al 100 de compleccion de la tarea
     * @param prioridadTarea, string con la prioridad de la tarea
     * @param completadaTarea , string sobre si la tarea esta acabada o no
     */
    
    public EntradaHistorial(String nombreTarea, String descripcionTarea, Date fechaTarea,int porcentajeTarea,
            String prioridadTarea,String completadaTarea){
            this.nombreTarea=nombreTarea;
            this.descripcionTarea=descripcionTarea;
            this.fechaTarea=fechaTarea;
            this.porcentajeTarea=porcentajeTarea;
            this.prioridadTarea=prioridadTarea;
            this.completadaTarea=completadaTarea;
    }
    
    /**
     * Devuelve el nombre de la tarea 
     * @return nombreTarea, string con el nombre de la tarea
     */
    
    public String getNombreTarea(){
        return nombreTarea;
    }
    
    /**
     * Permite modicar el valor del nombre de la tarea
     * @param nombreTarea de tipo string con el nombre de la nueva tarea
     */
    
    public void setNombreTarea(String nombreTarea){
        this.nombreTarea=nombreTarea;
    }
    
    /**
     * Devuelve la descripcion de la fecha
     * @return descripcionTarea, string con la descripcion de la tarea
     */
    
    public String getDescripcionTarea(){
        return descripcionTarea;
    }
    
    /**
     * Permite modificar la descripcion de una tarea
     * @param descripcionTarea string con la nueva descripcion 
     */
    
    public void setDescripcionTarea(String descripcionTarea){
        this.descripcionTarea=descripcionTarea;
    }
    
    /**
     * Devuelve la fecha de la tarea
     * @return fechaTarea, objeto Date de la fecha de la tarea
     */
    public Date getFechaTarea(){
        return fechaTarea;
    }
    
    /**
     * Permite modificar el valor de la fecha de la tarea
     * @param fechaTarea string, con la nueva fecha de la tarea
     */
    
    public void setFechaTarea(Date fechaTarea){
        this.fechaTarea=fechaTarea;
    }
    
    /**
     * Devuleve el porcentaje de completud de la tarea
     * @return  porcentajeTarea, entero con el porcentaje
     */
    
    
    public int getPorcentajeTarea(){
        return porcentajeTarea;
    }
    
    /**
     * Permite modificar el valor asociado al grado de completitud de la tarea
     * @param porcentajeTarea string con el nuevo valor de porcentaje completado
     */
    
    public void setPorcentajeTarea(int porcentajeTarea){
        this.porcentajeTarea=porcentajeTarea;
    }
    
    /**
     * Devuelve la prioridad de una tarea
     * @return prioridadTarea, string con la prioridad de la tarea
     */
    
    public String getprioridadTarea(){
        return prioridadTarea;
    }
    
    /**
     * Permite modificar el valor de prioridad de una tarea
     * @param prioridadTarea string con la nueva prioridad
     */
    
    public void setPrioridadTarea(String prioridadTarea){
        this.prioridadTarea=prioridadTarea;
    }
    
    
    /**
     * Devuelve un string sobre si la tarea esta finalizada o no, "Completada" en caso afirmativo
     * "Pendiente" en el negativo
     * @return completadaTarea, string con el estado de finalizacion de la tarea
     */
    
    public String getCompletadaTarea(){
        return completadaTarea;
    }
    
    /**
     * Permite modificar el string asociado a sobre si una tarea esta finalizada o no
     * @param completadaTarea string con el nuevo estado de la tarea
     */
    
    public void setCompletadaTarea(String completadaTarea){
        this.completadaTarea=completadaTarea;
    }
    
}
