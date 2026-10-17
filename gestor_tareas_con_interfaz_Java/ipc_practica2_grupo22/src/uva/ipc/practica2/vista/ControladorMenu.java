/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.ipc.practica2.vista;


import uva.ipc.practica2.MainP2;

/**
 * Se encarga de controlar y manejar los eventos de la ventana del menú
 * @author Ángel Antolín
 * @author Marcos Gómez
 */
public class ControladorMenu {
    
    
    private VistaMenu miVista;
    
    
    /**
     * Inicializa el controlador con la vista, no usa el modelo
     * @param vista 
     */
    public ControladorMenu(VistaMenu vista){
        
        this.miVista = vista;
        
    }
    
    /**
     * Maneja el evento cuando se cambia a la ventana del gestor de tareas.
     * Usa el gestor de vistas y cambia a la nueva ventana
     */
    public void procesaEventoTareas(){
        
        MainP2.getGestorVistas().mostrarVistaTareas();
        
        
       
    }
    
    /**
     * Maneja el evento cuando se cambia a la ventana del gestor de listas.
     * Usa el gestor de vistas y cambia a la nueva ventana
     */
    public void procesaEventoListas(){
        
        
        MainP2.getGestorVistas().mostrarVistaListas();
        
    }
    
}
