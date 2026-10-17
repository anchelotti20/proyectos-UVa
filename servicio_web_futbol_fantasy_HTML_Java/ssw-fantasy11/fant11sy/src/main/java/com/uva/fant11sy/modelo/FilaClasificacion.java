package com.uva.fant11sy.modelo;

import java.io.Serializable;

/**
 * Clase que representa una fila en la tabla de clasificación.
 * Se utiliza para mostrar el ranking por jornadas en la pantalla principal.
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */

public class FilaClasificacion implements Serializable {
    
    private String usuario;
    private int puntos;
    private int puesto;

    /**
     * Constructor por defecto
     */
    public FilaClasificacion() {
        this.usuario = "";
        this.puntos = 0;
        this.puesto = 0;
    }

    /**
     * Constructor completo para facilitar la creación desde el Servlet
     * @param usuario usuario del usuario
     * @param puntos Puntos obtenidos en la jornada
     * @param puesto Posición en el ranking
     */
    public FilaClasificacion(String usuario, int puntos, int puesto) {
        this.usuario = usuario;
        this.puntos = puntos;
        this.puesto = puesto;
    }

    // Getters y Setters
    
    public String getusuario() {
        return usuario;
    }

    public void setusuario(String usuario) {
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
}