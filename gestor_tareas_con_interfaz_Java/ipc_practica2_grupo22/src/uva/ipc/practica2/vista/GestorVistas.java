/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.ipc.practica2.vista;

import javax.swing.JFrame;

/**
 * Controla el cambio de ventanas
 * @author Ángel Antolín
 * @author Marcos Gómez
 */
public class GestorVistas {
    
    private JFrame vistaActual;
    
    /**
     * Cambia la vista actual a la ventana del menu
     */
    public void mostrarVistaMenu() {
        if (vistaActual != null) {
            vistaActual.setVisible(false);
            vistaActual.dispose();
        }
        vistaActual = new VistaMenu();
        vistaActual.setVisible(true);
    }
    /**
     * Cambia la vista actual a la ventana del gestor de tareas
     */
    public void mostrarVistaTareas() {
        if (vistaActual != null) {
            vistaActual.setVisible(false);
            vistaActual.dispose();
        }
        vistaActual = new VistaTareas();
        vistaActual.setVisible(true);
    }
    
    /**
     * Cambia la vista actual a la ventana del gestor de listas
     */
    public void mostrarVistaListas() {
        if (vistaActual != null) {
            vistaActual.setVisible(false);
            vistaActual.dispose();
        }
        vistaActual = new VistaListas();
        vistaActual.setVisible(true);
    }
}
