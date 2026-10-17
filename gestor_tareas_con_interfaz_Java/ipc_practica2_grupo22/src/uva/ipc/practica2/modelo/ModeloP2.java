/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.ipc.practica2.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Se encarga de trabajar con los diferentes datos de y procesar la informacion correspondiente
 * @author Marcos Gomez Medina
 * @author Angel Antolin Caramazana
 */
public class ModeloP2 {
    
    private HashMap<String, ArrayList<EntradaHistorial>> listas;
    private ArrayList<String> nombres;
    
    
    
    /**
     * Inicializa un modelo con un mapa para guardar las tareas y la lista de nombres para la seleccion
     * Ademas, guarda una serie de tareas y crea una lista default
     */
    public ModeloP2(){
        
        this.nombres = new ArrayList<>();
        this.listas = new HashMap<>();
        crearListaDefault();
    }
    
    
    /**
     * Retorna el mapa de listas de tareas
     * @return listas, hashmap con las listas 
     */
    
     public HashMap<String, ArrayList<EntradaHistorial>> getListas(){
        return this.listas;
    }
     
     
    /**
     * Retorna el array de nombres de listas
     * @return nombres, arraylist de nombres
     */ 
    public ArrayList<String> getNombres(){
         
        return this.nombres;
    }
     
     
     /**
      * Permite crear un objeto de entradaHistorial parra poder trabajar con la informacion correspondiente y lo añade
      * a la lista indicada
      * @param nombreTarea, de tipo string con el nombre asignado a la tarea
      * @param descripcionTarea, de tipo string con una breve descripcion de la tarea
      * @param fechaTarea,de tipo Date  con la fecha de realizacion de la tarea
      * @param porcentajeTarea, de tipo int asociado a la compleccion de la tarea
      * @param prioridadTarea, de tipo String con la prioridad de la tarea
      * @param completadaTarea ,string asociado a si la tarea se ha finalizado o no
      * @param lista, string asociado a la lista a guardar la tarea
      */
     
    public void crearEntradaHistorial(String nombreTarea, String descripcionTarea, Date fechaTarea, int porcentajeTarea,
        String prioridadTarea, String completadaTarea, String lista){
        
        EntradaHistorial entradaHistorial = new EntradaHistorial(nombreTarea, descripcionTarea, fechaTarea, porcentajeTarea, prioridadTarea, completadaTarea);
        
        addEntradaHistorial(lista, entradaHistorial);
        
    }
     
  
    /**
     * Permite aniadir una entrada del historial a la lista correspondiente
     * @param lista, string con el nombre de la lista
     * @param entrada de tipo EntradaHistorial
     * 
     */
    
    public void addEntradaHistorial(String lista, EntradaHistorial entrada){
        
           
        listas.get(lista).add(entrada);
            
       
        
    }
    
    /**
     * Crea una lista de tareas con el nombre indicado y añade el nombre a la lista de nombres
     * @param nombre, string con el nombre d ela nueva lista 
     */
    public void crearLista(String nombre){
        
        
        ArrayList<EntradaHistorial> lista = new ArrayList<>();
        
        this.listas.put(nombre, lista);
        this.nombres.add(nombre);
        
    }
    
    /**
     * Inicializa la lista default con las tareas por defecto
     */
    public void crearListaDefault(){
        
        String nombre = "IPC";
        
        crearLista(nombre);
        
        Date fecha1 = new Date(2025 - 1900,3 - 1,5);
        Date fecha2 = new Date(2025 - 1900,3 - 1,11);
        Date fecha3 = new Date(2025 - 1900,3 - 1,30);
        Date fecha4 = new Date(2025 - 1900,4 - 1,28);
        
        crearEntradaHistorial("TE 1", "Realizar un análisis de una aplicación", fecha1, 50, "Alta", "Pendiente", nombre);
        crearEntradaHistorial("Lectura", "Leer un artículo sobre el uso de deshacer para el tratamiento de errores", 
                                fecha2, 100, "Baja", "Completada", nombre);
        crearEntradaHistorial("Boceto", "Realizar un boceto de la práctica 2", fecha3, 25, "Alta", "Pendiente", nombre);
        crearEntradaHistorial("TE 2", "Realizar una aplicación web", fecha4, 100, "Media", "Completada", nombre);
        
        
    }
    
    /**
     * Calcula la diferencia en dias entre la fecha actual y la de finalizacion de la tarea
     * @param fechaTarea, objeto Date con la fecha de finalizacion prevista de la tarea
     * @param fechaActual, objeto Date con la fecha actual
     * @return dias, entero con la diferencia calculada
     */
    public int calcularDiferenciaFechas(Date fechaTarea, Date fechaActual){
        
        long milisegundos = fechaTarea.getTime() - fechaActual.getTime();
        
        int dias = (int) (milisegundos / (1000 * 60 * 60 * 24));
        
        
        return dias;
        
    }
}
