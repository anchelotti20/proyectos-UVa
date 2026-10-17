/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.modelo;


/**
 * Clase JavaBean que modela la actuacion en un partido de un jugador
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class Actuacion {
    
    private int idPartido;
    private int idJugador;
    private int minutos;
    private int goles;
    private int asistencias;
    private int amarillas;
    private int rojas;
    private int golesEnPropia;
    private int golesEnContra;
    private int puntos;

    public Actuacion() {
        this.minutos = 0;
        this.goles = 0;
        this.asistencias = 0;
        this.amarillas = 0;
        this.rojas = 0;
        this.golesEnPropia = 0;
        this.golesEnContra = 0;
        this.puntos = 0;
    }
    
    
    public Actuacion(int idPartido, int idJugador, int minutos, int goles, int asistencias, int amarillas, int rojas, int golesEnPropia, int golesEnContra, int puntos){
        
        this.idPartido = idPartido;
        this.idJugador = idJugador;
        this.minutos = minutos;
        this.goles = goles;
        this.asistencias = asistencias;
        this.amarillas = amarillas;
        this.rojas = rojas;
        this.golesEnPropia = golesEnPropia;
        this.golesEnContra = golesEnContra;
        this.puntos = puntos;
        
    }

    
    public int getIdPartido() { 
        
        return idPartido; 
    }
    
    public void setIdPartido(int idPartido) { 
        
        this.idPartido = idPartido; 
    }

    public int getIdJugador() { 
        
        return idJugador; 
    }
    
    public void setIdJugador(int idJugador) { 
        
        this.idJugador = idJugador; 
    }

    public int getMinutos() { 
        
        return minutos; 
    }
    
    public void setMinutos(int minutos) { 
        
        this.minutos = minutos; 
    }

    public int getGoles() { 
        
        return goles; 
    }
    
    public void setGoles(int goles) { 
        
        this.goles = goles; 
    }

    public int getAsistencias() { 
        
        return asistencias; 
    }
    
    public void setAsistencias(int asistencias) { 
        
        this.asistencias = asistencias; 
    }

    public int getAmarillas() { 
        
        return amarillas; 
    }
    
    public void setAmarillas(int amarillas) { 
        
        this.amarillas = amarillas; 
    }

    public int getRojas() { 
        
        return rojas; 
    }
    
    public void setRojas(int rojas) { 
        
        this.rojas = rojas; 
    }
    
    public int getGolesEnPropia() { 
        
        return golesEnPropia; 
    }
    
    public void setGolesEnPropia(int golesEnPropia) { 
        
        this.golesEnPropia = golesEnPropia; 
    }
    
    public int getGolesEnContra() { 
        
        return golesEnContra; 
    }
    
    public void setGolesEnContra(int golesEnContra) { 
        
        this.golesEnContra = golesEnContra; 
    }
    
    public int getPuntos() { 
        
        return puntos; 
    }
    
    public void setPuntos(int puntos) { 
        
        this.puntos = puntos; 
    }
}