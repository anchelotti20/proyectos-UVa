package uva.tds;
/**
 * Clase que representa una baraja de cartas.
 * Cada baraja contiene 40 cartas numeradas del 1 al 40.
 * Cada carta es representada por la clase Carta.
 * cartas: lista que contiene las cartas de la baraja.
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import java.util.ArrayList;
import java.util.List;

public class Baraja {
    
    private List<Carta> cartas;

    /**
     * Constructor de la clase Baraja que inicializa la lista de cartas y rellena la baraja
     * con cartas numeradas del 1 al 40.
     */
    public Baraja(){

        this.cartas = new ArrayList<>();

        rellenarBaraja();

    }

    /**
     * Rellena la baraja con cartas numeradas del 1 al 40.
     * Cada carta es creada y añadida a la lista de cartas de la baraja.
     */
    public void rellenarBaraja(){


        for(int i = 1; i <= 40; i++){

            Carta carta = new Carta(i);
            this.cartas.add(carta);

        }

    }

    
    /**
     * Permite obtener la lista de cartas de la baraja
     * @return retorna la lista de cartas de la baraja
     */
    public List<Carta> getCartas(){
        return this.cartas;
    }

    /**
     * Permite robar una carta específica de la baraja.
     * Si la baraja está vacía o la carta no está en la baraja, se lanza una excepción.
     * @param cartaARobar La carta que se desea robar de la baraja.
     * @return La carta robada de la baraja.
     * @throws IllegalStateException Si la baraja está vacía.
     * @throws IllegalArgumentException Si la carta no está en la baraja.
     */
    public Carta robarCarta(Carta cartaARobar){

        if (this.cartas.isEmpty()) {
            throw new IllegalStateException("No hay cartas disponibles para robar.");
        }

        if (!this.cartas.contains(cartaARobar)) {
            throw new IllegalArgumentException("La carta no está en la baraja.");
        }

        Carta cartaRobada = this.cartas.get(this.cartas.indexOf(cartaARobar));

        this.cartas.remove(cartaRobada);

        return cartaRobada;


    }


}
