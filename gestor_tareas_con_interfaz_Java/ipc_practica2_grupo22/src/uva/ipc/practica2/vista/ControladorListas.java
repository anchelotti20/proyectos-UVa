/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.ipc.practica2.vista;


import java.util.Date;
import uva.ipc.practica2.MainP2;
import uva.ipc.practica2.modelo.EntradaHistorial;
import uva.ipc.practica2.modelo.ModeloP2;

/**
 * Se encarga de controlar y manejar los eventos de la ventana del gestor de listas
 * @author Ángel Antolín
 * @author Marcos Gómez
 */
public class ControladorListas {
    
    private VistaListas miVista;
    private ModeloP2 miModelo;
    
    
    /**
     * Inicializa el controlador con la vista y el modelo comun
     * @param vista, objeto con la vista de la ventana 
     */
    public ControladorListas (VistaListas vista){
        
        
        this.miVista = vista;
        this.miModelo = MainP2.getListas();
        
        
    }
    
    /**
     * Llamado al crear la vista para que cada vez que esta se muestre los cambios realizados aparezcan,
     * el textarea no este vacío y los combobox tengan las nuevas listas creadas
     */
    public void inicializar(){
        
        this.miVista.actualizarVentanaTareas("IPC", this.miModelo.getListas());
        this.miVista.actualizarCombobox(this.miModelo.getNombres());
        
    }
    
    /**
     * Maneja el evento cuando se cambia la lista escogida en el ComboBox.
     * Usa la vista para obtener la nueva seleccion y en caso de que no sea null
     * actualiza las tareas totales y pendientes de la lista escogida
     */
    public void procesaEventoEscogerLista(){
        
        String lista = miVista.getLista();
        
        if(lista != null){
                
            miVista.actualizarVentanaTareas(lista, this.miModelo.getListas());
            miVista.actualizarVentanaPendientes(lista, this.miModelo.getListas());
            
        
        
        }else{
            
            miVista.limpiarVentanaTareas();
        
        }
    }
    
    /**
     * Maneja el evento cuando se quiere crear una nueva lista. Recoge de la vista el nombre y controla
     * que sea válido. Finalmente crea la lista y la añade a la lista de listas elegibles
     */
    public void procesaEventoGuardar(){
        
        String nombre; 
        
        try{
            
            nombre = this.miVista.getNombre(this.miModelo.getListas());
        
        }catch (IllegalArgumentException e){
            
            this.miVista.mostrarMensajeError("Introduce un nombre para la lista");
            return;
            
        }catch (IllegalStateException e){
            
            this.miVista.mostrarMensajeError("Ya existe una lista con ese nombre");
            return;
            
        }
        
        this.miModelo.crearLista(nombre);
        this.miVista.actualizarCombobox(this.miModelo.getNombres());
        this.miVista.mostrarMensajeError("");
        this.miVista.limpiar();
        
        
        
    }
    
    /**
     * Maneja el evento cuando se selecciona una tarea pendiente.
     * Obtiene los datos de la tarea seleccionada, llama al modelo para que calcule la diferencia de dias
     * y muestra los datos
     */
    public void procesaEventoSeleccionar(){
        
        String tareaSeleccionada;
        String nombre;
        String lista = this.miVista.getLista();
        Date fechaTarea;
        Date fechaActual = new Date();
        EntradaHistorial tareaAMostrar = null;
        int dias;
        
        
        this.miVista.limpiarPanelPendientes();
        
        try{
            
            tareaSeleccionada = this.miVista.getTareaSeleccionada();
            nombre = tareaSeleccionada.split(":")[1];
            
            for(EntradaHistorial tarea: this.miModelo.getListas().get(lista)){
                
                if(tarea.getNombreTarea().equals(nombre)){
                    
                    tareaAMostrar = tarea;
                    
                }
                
            }
            
        }catch(NullPointerException e){
            
            this.miVista.mostrarMensajeError("");
            return;
            
            
        }
        
        fechaTarea = tareaAMostrar.getFechaTarea();
        
        dias = this.miModelo.calcularDiferenciaFechas(fechaTarea, fechaActual);
        
        this.miVista.setInformacionPendientes(nombre, dias);
               
    }
    
    /**
     * Maneja el evento cuando se da a completar una tarea pendiente.
     * Obtine que tarea estaba seleccionada, pone su porcentaje al 100 y la elimina de
     * las tareas pendientes.
     */
    public void procesaEventoCompletar(){
        
        String nombre;
        String lista = this.miVista.getLista();
        EntradaHistorial tareaCompletada = null;
        String tareaSeleccionada;
        
        try{
            
            tareaSeleccionada = this.miVista.getTareaSeleccionada();
            
            nombre = tareaSeleccionada.split(":")[1];
            
            for(EntradaHistorial tarea: this.miModelo.getListas().get(lista)){
                
                if(tarea.getNombreTarea().equals(nombre)){
                    
                    tareaCompletada = tarea;
                    
                }
                
                
            }
            
            
        }catch (NullPointerException e){
            
            this.miVista.mostrarMensajeErrorCompletar("No hay ninguna tarea seleccionada");
            return;
            
            
        }
        
        
        tareaCompletada.setPorcentajeTarea(100);
        tareaCompletada.setCompletadaTarea("Completada");
        this.miVista.actualizarVentanaPendientes(lista, this.miModelo.getListas());
        this.miVista.actualizarVentanaTareas(lista, this.miModelo.getListas());
        this.miVista.mostrarMensajeErrorCompletar("");
        
    }
    
    
    /**
     * Maneja el evento cuando se cambia a la ventana del gestor de tareas.
     * Usa el gestor de vistas y cambia a la nueva ventana
     */
    public void procesaEventoTareas(){
        
        MainP2.getGestorVistas().mostrarVistaTareas();
        
        
    }
    
    /**
     * Maneja el evento cuando se cambia a la ventana del menu.
     * Usa el gestor de vistas y cambia a la nueva ventana
     */
    public void procesaEventoMenu(){
        
        MainP2.getGestorVistas().mostrarVistaMenu();
        
        
    }
}
