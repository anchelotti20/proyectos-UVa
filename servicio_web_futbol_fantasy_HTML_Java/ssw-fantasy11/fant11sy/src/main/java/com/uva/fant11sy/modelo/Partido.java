package com.uva.fant11sy.modelo;

import java.io.Serializable;

/**
 * Clase JavaBean que representa un enfrentamiento entre dos clubes
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class Partido implements Serializable {
    
    private int idPartido;
    private String ganador;
    private int golesLocal;
    private int golesVisitante;
    private int jornada;
    private String nombreLocal;
    private String nombreVisitante;
    private String fotoLocal;
    private String fotoVisitante;
    

    public Partido() {
        
        this.ganador = "";
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.jornada = 0;
        this.nombreLocal = "";
        this.nombreVisitante = "";
        this.fotoLocal = "";
        this.fotoVisitante = "";
        
    }

    // Constructor rápido para el Servlet
    public Partido(int idPartido, String ganador, int golesLocal, int golesVisitante, int jornada, String nombreLocal, String nombreVisitante, String fotoLocal, String fotoVisitante) {
        
        this.idPartido = idPartido;
        this.ganador = ganador;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.jornada = jornada;
        this.nombreLocal = nombreLocal;
        this.nombreVisitante = nombreVisitante;
        this.fotoLocal = fotoLocal;
        this.fotoVisitante = fotoVisitante;
    }

    public int idPartido() {
        return idPartido;
    }

    public void setidPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
    
    public int getgolesLocal() {
        return golesLocal;
    }

    public void setgolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getgolesVisitante() {
        return golesVisitante;
    }

    public void setgolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
    
    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }
    
    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getNombreVisitante() {
        return nombreVisitante;
    }

    public void setNombreVisitante(String nombreVisitante) {
        this.nombreVisitante = nombreVisitante;
    }
    
    public String getFotoLocal() {
        return fotoLocal;
    }

    public void setFotoLocal(String fotoLocal) {
        this.fotoLocal = fotoLocal;
    }
    
    public String getFotoVisitante() {
        return fotoVisitante;
    }

    public void setFotoVisitante(String fotoVisitante) {
        this.fotoVisitante = fotoVisitante;
    }
}