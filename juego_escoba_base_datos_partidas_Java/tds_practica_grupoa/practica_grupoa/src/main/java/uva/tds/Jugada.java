package uva.tds;

/**
 * Clase que representa una jugada en el juego.
 * Contiene la carta jugada y las cartas capturadas en esa jugada.
 * cartaJugada: instancia de Carta que representa la carta jugada.
 * cartasCapturadas: lista de instancias de Carta que representan las cartas capturadas.
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Jugadas")
public class Jugada {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "carta_jugada_id", nullable = false)
    private Carta cartaJugada;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "Jugada_CartasCapturadas",
        joinColumns = @JoinColumn(name = "jugada_id"),
        inverseJoinColumns = @JoinColumn(name = "carta_id")
    )
    private List<Carta> cartasCapturadas;


    /**
     * Constructor de la clase Jugada.
     * Valida que las cartas no sean nulas y que la suma de los valores de las cartas jugadas y capturadas sea 15.
     * @param cartaJugada Carta que se juega.
     * @param cartasCapturadas Lista de cartas que se capturan.
     */
    public Jugada(Carta cartaJugada, List<Carta> cartasCapturadas) {
        
        esNull(cartasCapturadas);
        esNull(cartaJugada);
        
        if(cartasCapturadas.isEmpty()) {

            this.cartaJugada = cartaJugada;
            this.cartasCapturadas = cartasCapturadas;
        
        
        }else{

            validaJugada(cartaJugada, cartasCapturadas);
            
            this.cartaJugada = cartaJugada;
            this.cartasCapturadas = cartasCapturadas;

        }
        
    }

    /**
     * Constructor por defecto de la clase Jugada.
     */
    public Jugada(){


    }

    /**
     * Obtiene la carta jugada.
     * @return Carta que se juega.
     */
    public Carta getCartaJugada() {
        return cartaJugada;
    }

    /**
     * Obtiene las cartas capturadas.
     * @return Lista de cartas que se capturan.
     */
    public List<Carta> getCartasCapturadas() {
        return cartasCapturadas;
    }

    /**
     * Establece las cartas capturadas.
     * @param cartasCapturadas Lista de cartas que se capturan.
     */
    public void setCartasCapturadas(List<Carta> cartasCapturadas) {
        
        esNull(cartasCapturadas);

        validaJugada(getCartaJugada(), cartasCapturadas);
        
        this.cartasCapturadas = cartasCapturadas;
    }

    /**
     * Valida que un objeto no sea nulo.
     * @param obj Objeto a validar.
     * @throws IllegalArgumentException si el objeto es nulo.
     */
    private void esNull(Object obj) {
        
        if(obj == null){

            throw new IllegalArgumentException("El objeto no puede ser null");

        }
    }

    /**
     * Valida que la suma de los valores de la carta jugada y las cartas capturadas sea 15.
     * @param cartaJugada Carta que se juega.
     * @param cartasCapturadas Lista de cartas que se capturan.
     * @throws IllegalArgumentException si la suma no es 15.
     */
    public void validaJugada(Carta cartaJugada, List<Carta> cartasCapturadas) {

        int suma = 0;

        for (Carta carta : cartasCapturadas) {
            suma += carta.getValor();
        }

        suma += cartaJugada.getValor();

        if(suma != 15){

            throw new IllegalArgumentException("La suma de las cartas jugadas y capturadas debe ser 15");

        }
    }

    /**
     * Compara si dos jugadas son iguales basandose en la carta jugada y las cartas capturadas.
     * @param obj objeto a comparar
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Jugada other = (Jugada) obj;
        return cartaJugada.equals(other.cartaJugada) && cartasCapturadas.equals(other.cartasCapturadas);
    }

    /**
     * Genera un codigo hash basado en la carta jugada y las cartas capturadas.
     */
    @Override
    public int hashCode() {
        int result = cartaJugada.hashCode();
        result = 31 * result + cartasCapturadas.hashCode();
        return result;
    }

}
