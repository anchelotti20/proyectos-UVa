/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.app;

import uva.dis.tconn.acme.interfaz.GestorVistas;


/**
 *  Clase principal de la aplicación, inicia el gestor de vistas y muestra la vista de login
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class Main {

    
    
    /**
     * Main del programa principal
     * @param args 
     */
    public static void main(String[] args){
        
        GestorVistas.getInstancia().mostrarVistaLogin();
        
    }

    
    
}
