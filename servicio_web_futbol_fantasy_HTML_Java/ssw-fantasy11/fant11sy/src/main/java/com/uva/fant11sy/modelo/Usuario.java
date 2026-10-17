package com.uva.fant11sy.modelo;

import java.io.Serializable;

/**
 * Clase JavaBean que representa a un Usuario del sistema.
 * Sirve para transportar los datos desde el Servlet hasta el JSP.
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class Usuario implements Serializable {
    
    private int idUsuario;
    private String nombre;
    private String usuario;
    private int puntos;
    private int puesto;
    private int idLiga;

    
    public Usuario() {
        
        this.nombre = "";
        this.usuario = "";
        this.puntos = 0;
        this.puesto = 0;
        
    }

    
    public Usuario(int idUsuario, String nombre, String usuario, int puntos, int puesto, int idLiga) {
        
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.puntos = puntos;
        this.puesto = puesto;
        this.idLiga = idLiga;
    }
    
    public int getIdUsuario() { 
        return idUsuario; 
    }
    
    public void setIdUsuario(int idUsuario) { 
        
        this.idUsuario = idUsuario; 
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }
    
    public int getIdLiga() { 
        
        return idLiga; 
    }
    public void setIdLiga(int idLiga) { 
        
        this.idLiga = idLiga; 
    }
}