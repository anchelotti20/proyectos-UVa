/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.modelo;

/**
 * Clase JavaBean que representa un club de futbol
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class Club {
    
    
    private int id;
    private String nombre;
    private String foto;
    
    
    public Club(){
        
        this.id = 0;
        this.nombre = "";
        this.foto = "";
    }
    
    public Club (int id, String nombre, String foto){
        
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        
    }
    
    public int getId(){
        
        return this.id;
    }
    
    public void setId(int id){
        
        this.id = id;
    }
    
    public String getNombre(){
        
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        
        this.nombre = nombre;
    }
    
    public String getFoto(){
        
        return this.foto;
    }
    
    public void setFoto(String foto){
        
        this.foto = foto;
    }
}
