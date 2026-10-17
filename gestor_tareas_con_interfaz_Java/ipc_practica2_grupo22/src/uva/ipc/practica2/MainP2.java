/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package uva.ipc.practica2;


import uva.ipc.practica2.vista.GestorVistas;
import uva.ipc.practica2.modelo.ModeloP2;

/**
 * Clase encargada de iniciar el programa creando el gestor de vistas y el modelo comun de todas
 * @author Ángel Antolín
 * @author Marcos Gómez
 */
public class MainP2 {
    
    private static GestorVistas gestor;
    private static ModeloP2 listas;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        gestor = new GestorVistas();
        listas = new ModeloP2();
        
        gestor.mostrarVistaMenu();
       
    }
    
    /**
     * Devuelver el gestor de vistas
     * @return gestor, objeto gestor de vistas
     */
    public static GestorVistas getGestorVistas() {
        return gestor;
    }
    
    
    /**
     * Devuelve el modelo comun
     * @return listas, objeto modelo
     */
    public static ModeloP2 getListas() {
        return listas;
    }
    
    
}
