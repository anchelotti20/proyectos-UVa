package uva.tds;

import java.util.List;

/**
 * Clase que gestiona una partida multijugador.
 * Contiene el gestor de la partida (logica de la partida) y los nombres de los jugadores.
 * gestor: instancia de GestorPartida que maneja la logica de la partida.
 * jugador1: nombre del primer jugador.
 * jugador2: nombre del segundo jugador.
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

public class GestorPartidaMultijugador {
    
    private GestorPartida gestor;
    private String jugador1;
    private String jugador2;
    
    

    /**
     * Constructor de la clase GestorPartidaMultijugador.
     * Inicializa el nombre del primer jugador y valida que no sea nulo o vacío.
     * @param jugador1 String con el nombre del primer jugador.
     */
    public GestorPartidaMultijugador(String jugador1) {
        
        esNull(jugador1);
        isEmpty(jugador1);
        
        this.jugador1 = jugador1;
        
    }

    /**
     * Valida que un objeto no sea nulo.
     * @param obj Objeto a validar.
     * @throws IllegalArgumentException si el objeto es nulo.
     */
    private void esNull(Object obj){
        if(obj == null){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Valida que una cadena no esté vacía.
     * @param str string a validar.
     * @throws IllegalArgumentException si la cadena está vacía.
     */
    public void isEmpty(String str){
        if(str.isEmpty()){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Obtiene el nombre del primer jugador.
     * @return String con el nombre del primer jugador.
     */
    public String getJugador1() {
        return jugador1;
    }

    /**
     * Obtiene el nombre del segundo jugador.
     * @return String con el nombre del segundo jugador.
     */
    public String getJugador2() {
        return jugador2;
    }

    /**
     * Obtiene el gestor de la partida.
     * @return GestorPartida que maneja la logica de la partida.
     */
    public GestorPartida getGestor() {
        return gestor;
    }

    /**
     * Obtiene la última carta jugada por el primer jugador.
     * @return Carta última carta jugada por el primer jugador.
     */
    public Carta getUltimCartaJugadaJugador1() {
        return gestor.getUltimaCartaJugadaJugador1();
    }

    /**
     * Obtiene la última carta jugada por el segundo jugador.
     * @return Carta última carta jugada por el segundo jugador.
     */
    public Carta getUltimCartaJugadaJugador2() {
        return gestor.getUltimaCartaJugadaJugador2();
    }

    /**
     * Establece el nombre del segundo jugador y valida que no sea nulo o vacío.
     * Crea el gestor de la partida una vez establecido el segundo jugador.
     * @param jugador2 String con el nombre del segundo jugador.
     */
    public void setJugador2(String jugador2) {
        esNull(jugador2);
        isEmpty(jugador2);
        this.jugador2 = jugador2;
        crearGestor();
    }

    /**
     * Crea el gestor de la partida con los dos jugadores y una nueva baraja.
     * Inicializa las instancias de Jugador y Baraja necesarias para el gestor.
     */
    public void crearGestor() {
        

        Jugador jugadorObj1 = new Jugador(this.jugador1);
        Jugador jugadorObj2 = new Jugador(this.jugador2);
        Baraja baraja = new Baraja();

        this.gestor = new GestorPartida(baraja, jugadorObj1, jugadorObj2);
        

    }

    /**
     * Parsea una carta desde su representación en String a un objeto Carta.
     * @param cartaStr String con la representación de la carta en formato JSON.
     * @return Carta parseada.
     */
    public Carta parsearCarta(String cartaStr) {
        esNull(cartaStr);
        return gestor.getParser().parseaCartaJSON(cartaStr);
    }

    /**
     * Parsea una lista de cartas desde su representación en String a una lista de objetos Carta.
     * @param cartasStr Lista de String con la representación de las cartas en formato JSON.
     * @return Lista de Carta parseadas.
     */
    public List<Carta> parsearLista(List<String> cartasStr) {
        esNull(cartasStr);
        return gestor.getParser().parsea(cartasStr);
    }
    
    /**
     * Verifica si una carta está en la mano del jugador actual.
     * @param carta Carta a verificar.
     * @return true si la carta está en la mano del jugador actual, false en caso contrario.
     */
    private boolean cartaEnMano(Carta carta) {
        esNull(carta);
        
        int turno = gestor.getTurnoActual();
        Jugador jugadorActual;

        if(turno == 1) {
            
            jugadorActual = gestor.getJugador1();

        } else {
            jugadorActual = gestor.getJugador2();
        }

        return jugadorActual.contieneCarta(carta);
    }

    /**
     * Verifica si la suma de las cartas capturadas y la última carta jugada es 15.
     * @param cartasCapturadas Lista de cartas capturadas.
     * @return true si la suma es 15, false en caso contrario.
     */
    private boolean cartaJugadaYSuma15(List<Carta> cartasCapturadas) {
        
        int turno = gestor.getTurnoActual();
        Carta ultimaCartaJugada;
        int suma = 0;

        if(turno == 2) {
            
            ultimaCartaJugada = gestor.getUltimaCartaJugadaJugador1();

        } else {
            ultimaCartaJugada = gestor.getUltimaCartaJugadaJugador2();
        }

        esNull(ultimaCartaJugada);

        for(Carta carta : cartasCapturadas) {
            esNull(carta);
            suma += carta.getValor();
        }
        suma += ultimaCartaJugada.getValor();

        if(suma != 15) {
            throw new IllegalArgumentException("La suma de las cartas capturadas y la carta jugada no es 15");
        }
        return true;

        
    }

    /**
     * Juega una carta localmente.
     * @param carta Carta a jugar.
     * @throws IllegalArgumentException si la carta no está en la mano del jugador.
     */
    public void jugarCartaLocal(Carta carta) {
        
        if(!cartaEnMano(carta)) {
            throw new IllegalArgumentException("La carta no está en la mano del jugador");
        }
        
        gestor.jugarCarta(carta);
    }

    /**
     * Captura cartas localmente.
     * @param cartasCapturadas Lista de cartas a capturar.
     * @throws IllegalArgumentException si la lista de cartas está vacía o si la suma
     * de las cartas capturadas y la carta jugada no es 15.
     */
    public void capturarCartasLocal(List<Carta> cartasCapturadas) {
        
        esNull(cartasCapturadas);
        if(cartasCapturadas.isEmpty()) {
            throw new IllegalArgumentException("La lista de cartas capturadas no puede estar vacía");
        }
        
        cartaJugadaYSuma15(cartasCapturadas);

        gestor.capturarCartas(cartasCapturadas);
    
    }

    /**
     * Reparte las cartas de la mesa localmente.
     * @param cartasMesa Lista de cartas para la mesa.
     * @throws IllegalArgumentException si la lista de cartas no tiene 4 cartas o es nula.
     */
    public void repartirMesaLocal(List<Carta> cartasMesa) {
        
        esNull(cartasMesa);
        if(cartasMesa.size() != 4) {
            throw new IllegalArgumentException("La mesa debe tener 4 cartas");
        }
        
        gestor.repartoCartasMesa(cartasMesa);
    
    }

    /**
     * Reparte la mano de un jugador localmente.
     * @param manoJugador Lista de cartas para la mano del jugador.
     * @param numeroJugador Número del jugador (1 o 2).
     * @throws IllegalArgumentException si la lista de cartas no tiene 3 cartas, es nula.
     */
    public void repartirManoLocal(List<Carta> manoJugador, int numeroJugador) {
        
        esNull(manoJugador);
        if(manoJugador.size() != 3) {
            throw new IllegalArgumentException("La mano debe tener 3 cartas");
        }
        
        gestor.repartoCartasJugador(manoJugador, numeroJugador);
    }

}
