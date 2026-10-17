/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.ipc.practica2.vista;

import java.util.Date;
import java.util.ArrayList;
import uva.ipc.practica2.modelo.EntradaHistorial;
import uva.ipc.practica2.modelo.ModeloP2;
import uva.ipc.practica2.MainP2;

/**
 * Se encarga de manejar todos los eventos y llamar a los metodos de vista y de modelo cuando sea necesario.
 * Guarda una referencia a la vista y al modelo para poder inicializarlos
 * @author Ángel Antolín
 * @author Marcos Gómez
 */
public class ControladorTareas {
    
    private VistaTareas miVista;
    private ModeloP2 miModelo;
    
    /**
     * Inicializa el controlador con la vista y crea el modelo
     * @param vista, diseño de la interfaz con el registro de eventos
     */
    public ControladorTareas(VistaTareas vista){
        this.miVista = vista;
        this.miModelo = MainP2.getListas();
    }
    
    
    /**
     * Permite actualizar la vista asocada al modelo de tareas
     */
    
    public void inicializar(){
        
        this.miVista.actualizarCombobox(this.miModelo.getNombres());
        
        
    }
    
    
    /**
     * Maneja el evento de cuando se pulsa el boton de guardar. Utiliza la vista para obtener toda la informacion
     * de la tarea comprobando que sea correcta y cumpla los requisitos y despues lo añade al historial que maneja el modelo
     * y llama a la vista para que actualice la vista y elimine la informacion introducida de los campos para que se puedan volver
     * a usar
     */
    public void procesaEventoGuardar(){
        
        String nombreTarea;
        String descripcionTarea;
        Date fechaTarea;
        int porcentajeTarea;
        String prioridadTarea;
        String completadaTarea;
        String lista;
        
        
        try{
            
            nombreTarea=this.miVista.getNombreTarea(this.miModelo.getListas());
            
        }catch(IllegalArgumentException e){
            this.miVista.mostrarMensajeError("El nombre de la tarea debe tener entre 1 y 10 carcateres, ambos incluidos.");
            return;
        
        
        }catch(IllegalStateException e){
            
           this.miVista.mostrarMensajeError("Ya hay una tarea con el mismo nombre guardada en la lista seleccionada");
           return;
        }
        
        
        
        try{
            descripcionTarea=this.miVista.getDescripcion();
            
        }catch(IllegalArgumentException e){
            this.miVista.mostrarMensajeError("La descripcion debe tener entre 0 y 100 carcateres, ambos incluidos.");
            return;
        }
        try{
            fechaTarea=(this.miVista.getFecha());
            
        }catch(NullPointerException e){
            
            this.miVista.mostrarMensajeError("Introduce una fecha válida");
            return;
        
        }
        
        porcentajeTarea=this.miVista.getPorcentaje();
        if(porcentajeTarea==100){
            completadaTarea="Completada";
        }else{
            completadaTarea=this.miVista.getCompletada();
        }
        
        if(this.miVista.getCompletada().contains("Completada")){
            porcentajeTarea=100;
        }
        
        prioridadTarea=this.miVista.getPrioridad();
        lista = this.miVista.getLista();
         
        this.miModelo.crearEntradaHistorial(nombreTarea, descripcionTarea, fechaTarea, porcentajeTarea, prioridadTarea, 
                                            completadaTarea, lista);
        
        this.miVista.setListaMostrar(lista);
        this.miVista.mostrarMensajeError("");
        this.miVista.limpiar();
        
        
    }
    
    
    /**
     * Maneja el evento de cuando se pulsa el boton editar. Obtiene toda la informacion de la tarea que esta
     * seleccionada en ese momento y se asegura de que haya tareas y haya una seleccionada. Una vez 
     * la encuentra en el arraylist del historial, llama a vista para que se actualicen los campos y se puedan cambiar
     * y la elimina de la lista para que no este repetida con otros datos.
     */
    public void procesaEventoEditar(){
        
        String nombre;
        EntradaHistorial entrada = null;
        String lista = this.miVista.getListaMostrar();
        int indice = 0;
        int numeroEntrada = 0;
        this.miVista.limpiar();
        this.miVista.limpiarPanelInfor();
        
        
        
        try{
            
            String tareaSeleccionada = this.miVista.getTareaSeleccionada();
            
            nombre = tareaSeleccionada.split(":")[1];
            
            for(EntradaHistorial tarea: this.miModelo.getListas().get(lista)){
                
                if(tarea.getNombreTarea().equals(nombre)){
                    
                    entrada = tarea;
                    numeroEntrada = indice;
                }
                
                indice ++;
                    
            }
            
            
        
        }catch(NullPointerException e){
            
            
            this.miVista.mostrarMensajeError("No hay ninguna tarea seleccionada");
            return;
            
        }
        
        this.miVista.setEditar(entrada.getNombreTarea(), entrada.getDescripcionTarea(), entrada.getFechaTarea(), 
                               entrada.getprioridadTarea(), entrada.getPorcentajeTarea(), entrada.getCompletadaTarea(), lista);
        
        this.miVista.eliminaSeleccion();
        this.miModelo.getListas().get(lista).remove(numeroEntrada);
        this.miVista.actualizarHistorial(lista, this.miModelo.getListas());
        
    }
    
    /**
     * Maneja el evento de pulsar el boton de eliminar. Se asegura de que haya al menos una tarea y de que haya alguna
     * seleccionada y una vez la encuentra en el arraylist del historial, la borra y llama a vista para que 
     * actualice la lista
     */
    public void procesaEventoBorrar(){
        
        String nombre;
        EntradaHistorial entrada = null;
        String lista = this.miVista.getListaMostrar();
        int indice = 0;
        int numeroEntrada = 0;
        this.miVista.limpiarPanelInfor();
        
        
        
        try{
            
            String tareaSeleccionada = this.miVista.getTareaSeleccionada();
            
            nombre = tareaSeleccionada.split(":")[1];
            
            for(EntradaHistorial tarea: this.miModelo.getListas().get(lista)){
                
                if(tarea.getNombreTarea().equals(nombre)){
                    
                    entrada = tarea;
                    numeroEntrada = indice;
                }
                
                indice ++;
                    
            }
            
            
        
        }catch(NullPointerException e){
            
            
            this.miVista.mostrarMensajeError("No hay ninguna tarea seleccionada");
            return;
            
        }
        
        this.miVista.eliminaSeleccion();
        this.miModelo.getListas().get(lista).remove(numeroEntrada);
        this.miVista.actualizarHistorial(lista, this.miModelo.getListas());
        
    }
    
    /**
     * Maneja el evento de cuando se selecciona una tarea en la lista. Se asegura de que no salte un error al borrar
     * una tarea, pues tambien se lanza este evento, y cuando se selecciona una tarea la busca en el arraylist de
     * historial y saca toda su informacion para que la vista la muestre en el panel de informacion
     */
    public void procesaEventoSeleccionar(){
        
        String tareaSeleccionada;
        String nombre;
        String lista = this.miVista.getListaMostrar();
        EntradaHistorial tareaAMostrar = null;
        
        this.miVista.limpiarPanelInfor();
        
        try{
            
            tareaSeleccionada = this.miVista.getTareaSeleccionada();
            nombre = tareaSeleccionada.split(":")[1];
            
            for(EntradaHistorial tarea: this.miModelo.getListas().get(lista)){
                
                if(tarea.getNombreTarea().equals(nombre)){
                    
                    tareaAMostrar = tarea;
                    
                }
                    
            }
            
        }catch (NullPointerException e){
            
            this.miVista.mostrarMensajeError("");
            return;
            
        }
        
        
  
        this.miVista.setInformacion(tareaAMostrar.getNombreTarea(), tareaAMostrar.getDescripcionTarea(), tareaAMostrar.getFechaTarea(),
                                    tareaAMostrar.getprioridadTarea(), tareaAMostrar.getPorcentajeTarea());
        
       
        
        
    }
    
    
    /**
     * Maneja el evento de cambiar una lista de tareas por otra, se asegura de que la lista no sea null para
     * cuando se va a mostrar y se lanza el metodo para actualizar el historial y poder obtener las listas correctamente
     */
    
    public void procesaEventoCambiaLista(){
        
        String lista = this.miVista.getListaMostrar();
        
        if(lista != null){
            
            this.miVista.actualizarHistorial(lista, this.miModelo.getListas());
            
            
        }else{
            
            return;
            
        }
        
        
    }
    
    
    /**
     * Permite manejar el evento de mostrar la vista de las listas a traes de la clase main que actua como 
     * lanzador
     */
    
    public void procesaEventoListas(){
        
        MainP2.getGestorVistas().mostrarVistaListas();
        
        
    }
    
    
    
    /**
     * Permite manejar el evento de mostrar la vista del menu a traves de la clase main 
     * que actua como lanzador
     */
    
    public void procesaEventoMenu(){
        
        MainP2.getGestorVistas().mostrarVistaMenu();
        
        
    }
    

}