/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.modelo;

import java.io.Serializable;

/**
 * Clase JavaBean que representa un jugador de futbol
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class Jugador implements Serializable{
    
    private int id;
    private String nombre;
    private String posicion;
    private String nacionalidad;
    private String foto;
    private int slot;
    private String nombreClub;
    private String fotoClub;

    /**
     * Constructor por defecto
     */
    public Jugador() {
        this.id = 0;
        this.nombre = "";
        this.posicion = "";
        this.nacionalidad = "";
        this.foto = "";
        this.slot = 0;
        this.nombreClub = "";
        this.fotoClub = "";
    }
    
    /**
     * Constructor para los Servlets
     * @param id entero con la id
     * @param nombre cadena con el nombre
     * @param posicion cadena con la posicion
     * @param nacionalidad cadena con la nacionalidad
     * @param foto cadena con la foto
     * @param nombreClub cadena con el nombre del club
     * @param fotoClub cadena con la foto del club
     */
    public Jugador(int id, String nombre, String posicion, String nacionalidad, String foto, int slot, String nombreClub, String fotoClub) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        this.nacionalidad = nacionalidad;
        this.foto = foto;
        this.slot = slot;
        this.nombreClub = nombreClub;
        this.fotoClub = fotoClub;
    }

    // Getters y Setters
    public int getId() { 
        
        return id; 
    }
    
    public void setId(int id) { 
        
        this.id = id; 
    }

    public String getNombre() { 
        
        return nombre; 
    }
    
    public void setNombre(String nombre) { 
        
        this.nombre = nombre; 
    }

    public String getPosicion() { 
        
        return posicion; 
    }
    
    public void setPosicion(String posicion) { 
        
        this.posicion = posicion; 
    }

    public String getNacionalidad() { 
        
        return nacionalidad; 
    }
    
    public void setNacionalidad(String nacionalidad) { 
        
        this.nacionalidad = nacionalidad; 
    }

    public String getFoto() { 
        
        return foto; 
    }
    
    public void setFoto(String foto) { 
        
        this.foto = foto; 
    }
    
    public int getSlot() { 
        
        return slot; 
    }
    
    public void setSlot(int slot) { 
        
        this.slot = slot; 
    }
    
    public String getNombreClub() { 
        
        return nombreClub; 
    }
    
    public void setNombreClub(String nombreClub) { 
        
        this.nombreClub = nombreClub; 
    }
    
    public String getFotoClub() { 
        
        return fotoClub; 
    }
    
    public void setFotoClub(String fotoClub) { 
        
        this.fotoClub = fotoClub; 
    }
}
    
