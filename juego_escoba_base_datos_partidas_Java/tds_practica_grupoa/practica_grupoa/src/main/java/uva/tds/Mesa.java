package uva.tds;

/**
 * Representa una mesa de juego
 * cartasMesa: lista de cartas que representa las cartas en la mesa
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */
import java.util.List;

public class Mesa {
    
    private List<Carta> cartasMesa;

    /**
     * Constructor de la clase Mesa
     * Inicializa una mesa con una lista de cartas dada
     * @param cartasMesa lista de cartas para inicializar la mesa
     * @throws IllegalArgumentException si la lista de cartas es nula, vacia o no tiene 4 cartas
     */
    public Mesa(List<Carta> cartasMesa) {
        
        esNull(cartasMesa);
        isEmpty(cartasMesa);

        if(cartasMesa.size() != 4) {
            throw new IllegalArgumentException("La lista de cartas no tiene 4 cartas");
        }

        this.cartasMesa = cartasMesa;
    }

    /**
     * Comprueba si una lista de cartas es nula
     * @param cartasMesa lista de cartas a comprobar si es nula
     * @throws IllegalArgumentException si la lista de cartas es nula
     */
    private void esNull(Object cartasMesa) {
        if(cartasMesa == null) {
            throw new IllegalArgumentException("La lista de cartas no puede ser nula");
        }
    }

    /**
     * Comprueba si una lista de cartas esta vacia
     * @param cartasMesa lista de cartas a comprobar si esta vacia
     * @throws IllegalArgumentException si la lista de cartas esta vacia
     */
    public void isEmpty(List<Carta> cartasMesa) {
        if(cartasMesa.isEmpty()) {
            throw new IllegalArgumentException("La lista de cartas no puede estar vacia");
        }
    }

    /**
     * Permite obtener las cartas de la mesa
     * @return lista de cartas de la mesa
     */
    public List<Carta> getCartas() {
        
        return cartasMesa;
    }
    
    /**
     * Permite establecer las cartas de la mesa
     * @param nuevasCartas lista de cartas para establecer en la mesa
     * @throws IllegalArgumentException si la lista de cartas es nula o vacia
     */
    public void setCartas(List<Carta> nuevasCartas){

        esNull(nuevasCartas);
        isEmpty(nuevasCartas);

        this.cartasMesa = nuevasCartas;
        
    }

    /**
     * Permite eliminar cartas de la mesa
     * @param cartasAEliminar lista de cartas a eliminar de la mesa
     * @throws IllegalArgumentException si la lista de cartas es nula o si alguna carta no esta en la mesa
     */
    public void quitarCartaMesa(List<Carta> cartasAEliminar) {

        esNull(cartasAEliminar);

        for(Carta carta : cartasAEliminar) {
            if(!cartasMesa.contains(carta)) {
                throw new IllegalArgumentException("La carta no esta en la mesa");
            }
        }
        

        cartasMesa.removeAll(cartasAEliminar);

    }

    /**
     * Permite añadir una carta a la mesa
     * @param carta la carta a añadir a la mesa
     * @throws IllegalArgumentException si la carta es nula
     */
    public void aniadirCartaMesa(Carta carta){
        esNull(carta);
        cartasMesa.add(carta);
    }

    /**
     * Permite comprobar si una carta esta en la mesa
     * @param carta la carta a comprobar
     * @throws IllegalArgumentException si la carta no esta en la mesa
     */
    public void contieneCarta(Carta carta){

        esNull(carta);

        if(!cartasMesa.contains(carta)){
            throw new IllegalArgumentException("La carta no esta en la mano del jugador.");
        }

    }

}
