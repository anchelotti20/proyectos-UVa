package uva.tds;

/**
 * Clase que representa una ronda del juego de la escoba.
 * Cada ronda contiene el número de ronda, las cartas en la mesa al inicio,
 * las cartas en la mano de cada jugador y las jugadas realizadas.
 * numeroRonda: número de la ronda (1-6).
 * mesaInicial: cartas en la mesa al inicio de la ronda.
 * manoJugador1: cartas en la mano del jugador 1.
 * manoJugador2: cartas en la mano del jugador 2.
 * jugadas: lista de jugadas realizadas en la ronda.
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Rondas")
public class Ronda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numeroRonda", nullable = false)
    private int numeroRonda;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "Ronda_MesaInicial",
        joinColumns = @JoinColumn(name = "ronda_id"),
        inverseJoinColumns = @JoinColumn(name = "carta_id")
    )
    private List<Carta> mesaInicial;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "Ronda_ManoJugador1",
        joinColumns = @JoinColumn(name = "ronda_id"),
        inverseJoinColumns = @JoinColumn(name = "carta_id")
    )
    private List<Carta> manoJugador1;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "Ronda_ManoJugador2",
        joinColumns = @JoinColumn(name = "ronda_id"),
        inverseJoinColumns = @JoinColumn(name = "carta_id")
    )
    private List<Carta> manoJugador2;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ronda_id")
    private List<Jugada> jugadas;

    /**
     * Constructor de la clase Ronda.
     * Inicializa una ronda con el número de ronda, las cartas en la mesa al inicio,
     * las cartas en la mano de cada jugador y las jugadas realizadas.
     * Valida que los parámetros no sean null, que el número de ronda esté entre 1 y 6,
     * y que la lista de jugadas contenga exactamente 6 jugadas.
     * @param numeroRonda entero entre 1 y 6 que representa el número de la ronda.
     * @param mesaInicial lista de cartas en la mesa al inicio de la ronda.
     * @param manoJugador1 lista de cartas en la mano del jugador 1.
     * @param manoJugador2 lista de cartas en la mano del jugador 2.
     * @param jugadas lista de jugadas realizadas en la ronda.
     * @throws IllegalArgumentException si numeroRonda no está entre 1 y 6,
     * o si jugadas no contiene exactamente 6 jugadas,
     */
    public Ronda(int numeroRonda, List<Carta> mesaInicial, List<Carta> manoJugador1, List<Carta> manoJugador2, List<Jugada> jugadas) {
        
        esNull(mesaInicial);
        esNull(manoJugador1);
        esNull(manoJugador2);
        esNull(jugadas);

        if(numeroRonda <= 0 || numeroRonda >= 7) {
            throw new IllegalArgumentException("El número de ronda debe estar entre 1 y 6.");
        }

        if(jugadas.size() != 6) {
            throw new IllegalArgumentException("La ronda debe contener exactamente 6 jugadas.");
        }

        
        
        this.numeroRonda = numeroRonda;
        this.jugadas = jugadas;
        this.mesaInicial = mesaInicial;
        this.manoJugador1 = manoJugador1;
        this.manoJugador2 = manoJugador2;
    }

    /**
     * Constructor por defecto de la clase Ronda.
     */
    public Ronda() {
        
    }

    /**
     * Valida que un objeto no sea null.
     * @param obj objeto a validar.
     * @throws IllegalArgumentException si el objeto es null.
     */
    private void esNull(Object obj) {
        if(obj == null) {
            throw new IllegalArgumentException("El parámetro no puede ser null.");
        }
    }

    /**
     * Obtiene el número de la ronda.
     * @return el número de la ronda.
     */
    public int getNumeroRonda() {
        return numeroRonda;
    }

    /**
     * Obtiene las cartas en la mesa al inicio de la ronda.
     * @return las cartas en la mesa al inicio de la ronda.
     */
    public List<Carta> getMesaInicial() {
        return mesaInicial;
    }

    /**
     * Establece las cartas en la mesa al inicio de la ronda.
     * @param mesaInicial las cartas en la mesa al inicio de la ronda.
     */
    public void setMesaInicial(List<Carta> mesaInicial) {
        
        esNull(mesaInicial);
        
        this.mesaInicial = mesaInicial;
    }

    /**
     * Obtiene las cartas en la mano del jugador 1.
     * @return las cartas en la mano del jugador 1.
     */
    public List<Carta> getManoJugador1() {
        return manoJugador1;
    }

    /**
     * Establece las cartas en la mano del jugador 1.
     * @param manoJugador1 las cartas en la mano del jugador 1.
     */
    public void setManoJugador1(List<Carta> manoJugador1) {
        
        esNull(manoJugador1);
        
        this.manoJugador1 = manoJugador1;
    }

    /**
     * Obtiene las cartas en la mano del jugador 2.
     * @return las cartas en la mano del jugador 2.
     */
    public List<Carta> getManoJugador2() {
        return manoJugador2;
    }

    /**
     * Establece las cartas en la mano del jugador 2.
     * @param manoJugador2 las cartas en la mano del jugador 2.
     */
    public void setManoJugador2(List<Carta> manoJugador2) {
        
        esNull(manoJugador2);
        
        this.manoJugador2 = manoJugador2;
    }

    /**
     * Obtiene la lista de jugadas realizadas en la ronda.
     * @return la lista de jugadas realizadas en la ronda.
     */
    public List<Jugada> getJugadas() {
        return jugadas;
    }

    /**
     * Establece la lista de jugadas realizadas en la ronda.
     * @param jugadas la lista de jugadas realizadas en la ronda.
     * @throws IllegalArgumentException si jugadas no contiene exactamente 6 jugadas.
     */
    public void setJugadas(List<Jugada> jugadas) {
        
        esNull(jugadas);
        
        if(jugadas.size() != 6) {
            throw new IllegalArgumentException("La ronda debe contener exactamente 6 jugadas.");
        }
        
        this.jugadas = jugadas;
    }

    /**
     * Compara si dos rondas son iguales basandose en sus atributos.
     * @param obj objeto a comparar
     * @return true si las rondas son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Ronda ronda = (Ronda) obj;

        if(numeroRonda != ronda.numeroRonda) return false;
        if(!mesaInicial.equals(ronda.mesaInicial)) return false;
        if(!manoJugador1.equals(ronda.manoJugador1)) return false;
        if(!manoJugador2.equals(ronda.manoJugador2)) return false;
        return jugadas.equals(ronda.jugadas);
    }

    /**
     * Genera un codigo hash basado en los atributos de la ronda.
     * @return entero que representa el codigo hash de la ronda
     */
    @Override
    public int hashCode() {
        int result = numeroRonda;
        result = 31 * result + mesaInicial.hashCode();
        result = 31 * result + manoJugador1.hashCode();
        result = 31 * result + manoJugador2.hashCode();
        result = 31 * result + jugadas.hashCode();
        return result;
    }

}
