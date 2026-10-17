package uva.tds;

import java.time.LocalDateTime;

/**
 * Clase que representa una partida del juego de la escoba.
 * idPartida: String que representa el identificador unico de la partida
 * nombreJugador1: String que representa el nombre del primer jugador
 * nombreJugador2: String que representa el nombre del segundo jugador
 * rondas: Lista de objetos Ronda que representa las rondas de la partida
 * resumenJugador1: Lista de enteros que representa el resumen de puntos del primer jugador
 * resumenJugador2: Lista de enteros que representa el resumen de puntos del segundo jugador
 * ganador: String que representa el nombre del jugador ganador
 */

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
@Table(name = "Partidas")
public class Partida {
    
    @Id
    @Column(name = "idPartida")
    private String idPartida;

    @Column(name = "jugador_1", nullable = false)
    private String nombreJugador1;

    @Column(name = "jugador_2", nullable = false)
    private String nombreJugador2;

    @Column(name = "ganador", nullable = false)
    private String ganador;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "partida_id")
    private List<Ronda> rondas;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @CollectionTable(name = "Resumen_Jugador1", joinColumns = @JoinColumn(name = "partida_id"))
    @Column(name = "resumen_jugador1")
    private List<Integer> resumenJugador1;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @CollectionTable(name = "Resumen_Jugador2", joinColumns = @JoinColumn(name = "partida_id"))
    @Column(name = "resumen_jugador2")
    private List<Integer> resumenJugador2;
    

    
    /**
    * Constructor de la clase Partida.
    * Inicializa una partida con los parametros dados y valida que no sean nulos o vacios,
    * y que las listas tengan el tamaño correcto.
    * Comprueba el ganador de la partida. 
    * @param idPartida string identificador de la partida
    * @param nombreJugador1 string nombre del primer jugador
    * @param nombreJugador2 string nombre del segundo jugador
    * @param rondas lista de rondas de la partida
    * @param resumenJugador1 lista de enteros que representa el resumen de puntos del primer jugador
    * @param resumenJugador2 lista de enteros que representa el resumen de puntos del segundo jugador
    */
    public Partida(String idPartida, String nombreJugador1, String nombreJugador2, List<Ronda> rondas, List<Integer> resumenJugador1, List<Integer> resumenJugador2) {
        
        esNull(idPartida);
        esNull(nombreJugador1);
        esNull(nombreJugador2);
        esNull(rondas);
        esNull(resumenJugador1);
        esNull(resumenJugador2);

        esVacio(idPartida);
        esVacio(nombreJugador1);
        esVacio(nombreJugador2);

        compruebaTamanio(rondas);
        compruebaTamanio(resumenJugador1);
        compruebaTamanio(resumenJugador2);

        this.idPartida = idPartida;
        this.nombreJugador1 = nombreJugador1;
        this.nombreJugador2 = nombreJugador2;
        this.fechaHora = LocalDateTime.now();
        this.rondas = rondas;
        this.resumenJugador1 = resumenJugador1;
        this.resumenJugador2 = resumenJugador2;
        this.ganador = compruebaGanador();
    }

    /**
     * Constructor por defecto de la clase Partida.
     */
    public Partida() {
        
    }

    /**
     * Comprueba si un objeto es nulo
     * @param obj objeto a comprobar
     * @throws IllegalArgumentException si el objeto es nulo
     */
    private void esNull(Object obj) {
        if(obj == null) {
            throw new IllegalArgumentException("El parámetro no puede ser null.");
        }
    }

    /**
     * Comprueba si una cadena está vacía
     * @param string cadena a comprobar
     * @throws IllegalArgumentException si la cadena está vacía
     */
    private void esVacio(String string) {
        if(string.isEmpty()) {
            throw new IllegalArgumentException("La cadena no puede estar vacía.");
        }
    }

    /**
     * Comprueba si una lista tiene el tamaño esperado de 6
     * @param lista lista a comprobar
     * @throws IllegalArgumentException si la lista no tiene el tamaño esperado
     */
    public void compruebaTamanio(List<?> lista) {
        if(lista.size() != 6) {
            throw new IllegalArgumentException("La lista no tiene el tamaño esperado de 6.");
        }
    }

    /**
     * Permite obtener el id de la partida
     * @return id de la partida
     */
    public String getIdPartida() {
        return idPartida;
    }


    /**
     * Permite obtener el nombre del primer jugador
     * @return nombre del primer jugador
     */
    public String getNombreJugador1() {
        return nombreJugador1;
    }

    /**
     * Permite establecer el nombre del primer jugador
     * @param nombreJugador1 nombre del primer jugador
     */
    public void setNombreJugador1(String nombreJugador1) {
        
        esNull(nombreJugador1);
        esVacio(nombreJugador1);

        this.nombreJugador1 = nombreJugador1;
    }


    /**
     * Permite obtener el nombre del segundo jugador
     * @return nombre del segundo jugador
     */
    public String getNombreJugador2() {
        
        
        return nombreJugador2;
    }

    /**
     * Permite establecer el nombre del segundo jugador
     * @param nombreJugador2 nombre del segundo jugador
     */
    public void setNombreJugador2(String nombreJugador2) {
        
        esNull(nombreJugador2);
        esVacio(nombreJugador2);

        this.nombreJugador2 = nombreJugador2;
    }

    /**
     * Permite obtener la fecha y hora de la partida
     * @return fecha y hora de la partida
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Permite establecer la fecha y hora de la partida
     * @param fechaHora fecha y hora de la partida
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        esNull(fechaHora);
        this.fechaHora = fechaHora;
    }

    /**
     * Permite obtener la lista de rondas
     * @return lista de rondas
     */
    public List<Ronda> getRondas() {
        return rondas;
    }

    /**
     * Permite establecer la lista de rondas
     * @param rondas lista de rondas
     */
    public void setRondas(List<Ronda> rondas) {
        esNull(rondas);
        compruebaTamanio(rondas);
        
        this.rondas = rondas;
    }

    /**
     * Permite obtener el resumen de puntos del primer jugador
     * @return lista de enteros con el resumen del primer jugador
     */
    public List<Integer> getResumenJugador1() {
        return resumenJugador1;
    }

    /**
     * Permite establecer el resumen de puntos del primer jugador
     * @param resumenJugador1 lista de enteros con el resumen del primer jugador
     */
    public void setResumenJugador1(List<Integer> resumenJugador1) {
        esNull(resumenJugador1);
        compruebaTamanio(resumenJugador1);
        
        this.resumenJugador1 = resumenJugador1;
    }


    /**
     * Permite obtener el resumen de puntos del segundo jugador
     * @return lista de enteros con el resumen del segundo jugador
     */
    public List<Integer> getResumenJugador2() {
        return resumenJugador2;
    }

    /**
     * Permite establecer el resumen de puntos del segundo jugador
     * @param resumenJugador2 lista de enteros con el resumen del segundo jugador
     */
    public void setResumenJugador2(List<Integer> resumenJugador2) {
        esNull(resumenJugador2);
        compruebaTamanio(resumenJugador2);
        
        this.resumenJugador2 = resumenJugador2;
    }

    /**
     * Permite obtener el ganador de la partida
     * @return nombre del ganador o "Empate" si hay empate
     */
    public String getGanador() {
        return ganador;
    }

    /**
     * Permite establecer el ganador de la partida
     * @param ganador nombre del ganador o "Empate" si hay empate
     */
    public void setGanador(String ganador) {
        
        
        esNull(ganador);
        esVacio(ganador);
        if(!ganador.equals(nombreJugador1) && !ganador.equals(nombreJugador2) && !ganador.equals("Empate")) {
            throw new IllegalArgumentException("El ganador debe ser uno de los jugadores o 'Empate'.");
        }
        this.ganador = ganador;
    }

    /**
     * Comprueba el ganador de la partida
     * @return nombre del ganador o "Empate" si hay empate
     */
    public String compruebaGanador(){

        if(resumenJugador1.get(5) > resumenJugador2.get(5)) {
            return nombreJugador1;
        } else if(resumenJugador2.get(5) > resumenJugador1.get(5)) {
            return nombreJugador2;
        } else {
            return "Empate";
        }

    }

    /**
     * Compara si dos partidas son iguales basandose en su id unico.
     * @param obj objeto a comparar
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Partida partida = (Partida) obj;
        return idPartida.equals(partida.idPartida);
    }
    
    /**
     * Genera un codigo hash basado en el id unico de la partida.
     */
    @Override
    public int hashCode() {
        return idPartida.hashCode();
    }

}
