/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.modelo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase JavaBean que representa un equipo de un usuario
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class Equipo implements Serializable{
    
    private int idEquipo;
    private String nombre;
    private String formacion;
    private int idUsuario;
    private List<Jugador> plantilla;
    
    
    public Equipo(){
        
        this.idEquipo = 0;
        this.nombre = "";
        this.formacion = "4-3-3";
        this.idUsuario = 0;
        this.plantilla = new ArrayList<>();
        
        
    }
    
    /**
     * Constructor de la clase
     * @param idEquipo, id del equipo en la DB
     * @param nombre, nombre del equipo
     * @param formacion, formacion guardada
     * @param idUsuario, id del usuario duenio
     * @param plantilla, lista de jugadores
     */
    public Equipo(int idEquipo, String nombre, String formacion, int idUsuario, List<Jugador> plantilla){
        
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.formacion = formacion;
        this.idUsuario = idUsuario;
        this.plantilla = plantilla;
    }
    
    //Getters y Setters
    public int getIdEquipo() { 
        
        return idEquipo; 
    }
    
    public void setIdEquipo(int idEquipo) { 
        
        this.idEquipo = idEquipo; 
    }

    public String getNombre() { 
        
        return nombre; 
    }
    
    public void setNombre(String nombre) { 
        
        this.nombre = nombre; 
    }

    public String getFormacion() { 
        
        return formacion; 
    }
   
    public void setFormacion(String formacion) { 
        
        this.formacion = formacion; 
    }

    public int getIdUsuario() { 
        
        return idUsuario; 
    }
   
    public void setIdUsuario(int idUsuario) { 
        
        this.idUsuario = idUsuario; 
    }

    public List<Jugador> getPlantilla() { 
        
        return plantilla; 
    }
    
    public void setPlantilla(List<Jugador> plantilla) { 
        
        this.plantilla = plantilla; 
    }
}
