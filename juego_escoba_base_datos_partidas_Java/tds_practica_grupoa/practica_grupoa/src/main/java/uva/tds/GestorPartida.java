package uva.tds;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona una partida de escoba entre dos jugadores
 * Contiene la baraja, los dos jugadores, la mesa, el turno actual y si el juego ha sido iniciado o no
 * juegoIniciado: booleano que indica si el juego ha comenzado
 * turnoActual: entero que indica el turno actual (1 o 2)
 * baraja: baraja de cartas del juego
 * jugador1: primer jugador de la partida
 * jugador2: segundo jugador de la partida
 * mesa: mesa donde se encuentran las cartas con las que los jugadores pueden hacer jugadas
 * ultimoJugador: jugador que realizó la última captura de cartas
 * @author Marcos Gomez Medina
 * @author Angel Antolin Caramazana
 * 
 */
public class GestorPartida {
    
    private Baraja baraja;
    private Jugador jugador1;
    private Jugador jugador2;
    private Carta ultimaCartaJugadaJugador1;
    private Carta ultimaCartaJugadaJugador2;
    private Jugador ultimoJugador;
    private Mesa mesa;
    private int turnoActual;
    private boolean juegoIniciado;
    private Parser parser;

    /**
     * Constructor de la clase GestorPartida
     * Inicializa una partida de escoba con la baraja, los dos jugadores, y establece el turno al primer jugador
     * inicializando el estado del juego como iniciado y las ultimas cartas jugadas a null y creando un parser
     * @param baraja baraja de cartas del juego
     * @param jugador1 primer jugador de la partida
     * @param jugador2 segundo jugador de la partida
     * @throws IllegalArgumentException si alguno de los parametros es nulo o si los jugadores son iguales
     */
    public GestorPartida(Baraja baraja, Jugador jugador1, Jugador jugador2) {
        
        
        esNull(baraja);
        esNull(jugador1);
        esNull(jugador2);

        if (jugador1.getNombre().equals(jugador2.getNombre())) {
            throw new IllegalArgumentException("Los jugadores no pueden ser iguales.");
        }

        this.baraja = baraja;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.ultimaCartaJugadaJugador1 = null;
        this.ultimaCartaJugadaJugador2 = null;
        this.turnoActual = 1;
        this.juegoIniciado = true;
        this.ultimoJugador=jugador1;
        this.parser = new Parser();
    }

    /**
     * Comprueba que un objeto no sea nulo
     * @param obj objeto a comprobar
     */
    private void esNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("El parámetro no puede ser nulo.");
        }
        
    }

    /**
     * Indica si el juego ha sido iniciado
     * @return true si el juego ha comenzado, false en caso contrario
     */
    public boolean juegoIniciado() {
        return juegoIniciado;
    }

    /**
     * Obtiene el turno actual
     * @return entero que indica el turno actual (1 o 2)
     */
    public int getTurnoActual() {
        return turnoActual;
    }

    /**
     * Permite obtener la baraja del juego
     * @return baraja del juego
     */
    public Baraja getBaraja() {
        return baraja;
    }

    /**
     * Permite obtener el primer jugador de los dos jugadores
     * @return de tipo Jugador, jugador1
     */
    public Jugador getJugador1() {
        return jugador1;
    }

    /**
     * Permite obtener el segundo jugador de los dos jugadores
     * @return de tipo Jugador, jugador2
     */
    public Jugador getJugador2() {
        return jugador2;
    }
    
    /**
     * Permite obtener la mesa del juego
     * @return mesa del juego
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * Permite obtener el parser del juego
     * @return parser del juego
     */
    public Parser getParser() {
        return parser;
    }

    /**
     * Obtiene la ultima carta jugada por el jugador 1
     * @return Carta, ultima carta jugada por el jugador 1
     */
    public Carta getUltimaCartaJugadaJugador1() {
        return ultimaCartaJugadaJugador1;
    }

    /**
     * Obtiene la ultima carta jugada por el jugador 2
     * @return Carta, ultima carta jugada por el jugador 2
     */
    public Carta getUltimaCartaJugadaJugador2() {
        return ultimaCartaJugadaJugador2;
    }

    /**
     * Reparte las cartas iniciales en la mesa
     * @param cartasMesa lista de cartas a repartir en la mesa
     */
    public void repartoCartasMesa(List<Carta> cartasMesa){

        esNull(cartasMesa);

        for(Carta carta : cartasMesa) {
            baraja.robarCarta(carta);
        }

        this.mesa = new Mesa(cartasMesa);
        

    }

    /**
     * Reparte las cartas iniciales a un jugador
     * @param cartas lista de cartas a repartir al jugador
     * @param numeroJugador numero del jugador (1 o 2)
     * @throws IllegalArgumentException si el numero de jugador no es 1 o 2
     */
    public void repartoCartasJugador(List<Carta> cartas, int numeroJugador){

        esNull(cartas);

        for(Carta carta : cartas) {
            baraja.robarCarta(carta);
        }

        if(numeroJugador == 1){
            
            jugador1.setMano(cartas);
        
        }else if(numeroJugador == 2){
            
            jugador2.setMano(cartas);
        
        }else{
            
            throw new IllegalArgumentException("El numero de jugador debe ser 1 o 2");
        }

    }

    /**
     * Permite a un jugador jugar una carta de su mano y añadirla a la mesa
     * @param cartaAJugar carta que el jugador va a jugar
     * @throws IllegalArgumentException si la carta es nula o si el jugador no tiene la carta en su mano
     */
    public void jugarCarta(Carta cartaAJugar){

        esNull(cartaAJugar);

        if(turnoActual == 1){

            if(!jugador1.contieneCarta(cartaAJugar)) {
                throw new IllegalArgumentException("El jugador 1 no tiene la carta que intenta jugar.");
            }
            jugador1.juegaCartaJugador(cartaAJugar);
            mesa.aniadirCartaMesa(cartaAJugar);
            this.ultimaCartaJugadaJugador1 = cartaAJugar;
            turnoActual = 2;
        
        }else{

            if(!jugador2.contieneCarta(cartaAJugar)) {
                throw new IllegalArgumentException("El jugador 2 no tiene la carta que intenta jugar.");
            }
            jugador2.juegaCartaJugador(cartaAJugar);
            mesa.aniadirCartaMesa(cartaAJugar);
            this.ultimaCartaJugadaJugador2 = cartaAJugar;
            turnoActual = 1;
        }


    }

    /**
     * Obtiene la ultima carta jugada por un jugador
     * @param numeroJugador numero del jugador (1 o 2)
     * @return ultima carta jugada por el jugador
     * @throws IllegalArgumentException si el numero de jugador no es 1 o 2
     */
    public Carta getUltimaCartaJugada(int numeroJugador){

        
        if(numeroJugador == 1){

            return this.ultimaCartaJugadaJugador1;

        }else if(numeroJugador == 2){

            return this.ultimaCartaJugadaJugador2;

        }else{

            throw new IllegalArgumentException("El numero de jugador debe ser 1 o 2");
        }
    
    }

    /**
     * Permite a un jugador capturar cartas de la mesa
     * @param cartasCapturar lista de cartas a capturar de la mesa
     * @throws IllegalArgumentException si la lista de cartas es nula o vacia, o si alguna carta no esta en la mesa
     */
    public void capturarCartas(List<Carta> cartasCapturar){

        esNull(cartasCapturar);
        if(cartasCapturar.isEmpty()){
            throw new IllegalArgumentException("La lista de cartas a capturar no puede estar vacia");
        }

        List<Carta> cartasAQuitar = new ArrayList<>();

        if(turnoActual == 2){

            esNull(this.ultimaCartaJugadaJugador1);

            for(Carta cartaMesa : cartasCapturar){
                mesa.contieneCarta(cartaMesa);
            }

            jugador1.captura(ultimaCartaJugadaJugador1, cartasCapturar);
            this.ultimoJugador = jugador1;
            
            cartasAQuitar.add(ultimaCartaJugadaJugador1);
            cartasAQuitar.addAll(cartasCapturar);

            mesa.quitarCartaMesa(cartasAQuitar);
            
            if(mesa.getCartas().isEmpty()){
                jugador1.sumaEscoba();
            }

            this.ultimaCartaJugadaJugador1 = null;

        }else{
            
            esNull(this.ultimaCartaJugadaJugador2);

            for(Carta cartaMesa : cartasCapturar){
                mesa.contieneCarta(cartaMesa);
            }

            jugador2.captura(ultimaCartaJugadaJugador2, cartasCapturar);
            this.ultimoJugador = jugador2;

            cartasAQuitar.add(ultimaCartaJugadaJugador2);
            cartasAQuitar.addAll(cartasCapturar);

            mesa.quitarCartaMesa(cartasAQuitar);

            if(mesa.getCartas().isEmpty()){
                jugador2.sumaEscoba();
            }

            this.ultimaCartaJugadaJugador2 = null;
        }

    }

    /**
     * Calcula la puntuacion final de la partida y la asigna a cada jugador segun las reglas del juego
     *  1 punto por cada escoba
     *  2 puntos por tener todos los oros
     *  1 punto por mayoria de oros
     *  1 punto por tener el "guindis" (7 de oros)
     *  3 puntos por tener todos los sietes
     *  1 punto por mayoria de sietes
     *  1 punto por mayoria de cartas obtenidas
     * @throws IllegalStateException si el juego no ha finalizado aun
     */
    public void puntuacionFinal(){

        if(juegoIniciado){
            throw new IllegalStateException("El juego no ha finalizado aun.");
        }
        
        int orosJugador1;
        int sietesJugador1;

        jugador1.sumarPuntos(jugador1.getNumeroEscobas());
        jugador2.sumarPuntos(jugador2.getNumeroEscobas());

        orosJugador1=contarOros(jugador1.getCartasObtenidas());
    
        if(orosJugador1 == 10){
            jugador1.sumarPuntos(3);
        }else if(orosJugador1 == 0){
            jugador2.sumarPuntos(3);
        }else if(orosJugador1 > 5){
            jugador1.sumarPuntos(1);
        }else if(orosJugador1 < 5){
            jugador2.sumarPuntos(1);
        }

        jugador1.sumarPuntos(jugador1.getGuindis());
        jugador2.sumarPuntos(jugador2.getGuindis());

        sietesJugador1=contarSietes(jugador1.getCartasObtenidas());

        if(sietesJugador1 == 4){
            jugador1.sumarPuntos(3);
        }else if(sietesJugador1 == 0){
            jugador2.sumarPuntos(3);
        }else if(sietesJugador1 > 2){
            jugador1.sumarPuntos(1);
        }else if(sietesJugador1 < 2){
            jugador2.sumarPuntos(1);
        }

        if(jugador1.getCartasObtenidas().size()>20){
            jugador1.sumarPuntos(1);
        }else if(jugador2.getCartasObtenidas().size()>20){
            jugador2.sumarPuntos(1);
        }


    }

    /**
     * Cuenta el numero de oros en una lista de cartas
     * @param listaCartas lista de cartas a comprobar
     * @return numero de oros en la lista de cartas
     * @throws IllegalArgumentException si la lista de cartas es nula
     */
    public int contarOros(List<Carta> listaCartas){

        int contadorOros=0;
        esNull(listaCartas);

        for(Carta cartita: listaCartas){
            if(cartita.getNumeroCarta()<=10){
                contadorOros++;
            
            }
        }
        
        return contadorOros;

    }


    /**
     * Cuenta el numero de sietes en una lista de cartas
     * @param listaCartas lista de cartas a comprobar
     * @return numero de sietes en la lista de cartas
     * @throws IllegalArgumentException si la lista de cartas es nula
     */
    public int contarSietes(List<Carta> listaCartas){

        int contadorSietes=0;
        esNull(listaCartas);
        
        for(Carta cartita: listaCartas){
            if(cartita.getValor()==7){
                contadorSietes++;
            }
        }

        return contadorSietes;


    }

    /**
     * Finaliza el juego, asignando las cartas restantes en la mesa al ultimo jugador que realizo una captura
     * y cambiando el estado del juego a no iniciado
     * @throws IllegalStateException si aun quedan cartas en la mano de los jugadores o en la baraja
     */
    public void finalizarJuego(){

        if(!jugador1.getMano().isEmpty() || !jugador2.getMano().isEmpty() || !baraja.getCartas().isEmpty()){
            throw new IllegalStateException("No se puede terminar el juego aun.");
        }
        
        ultimoJugador.getCartasObtenidas().addAll(mesa.getCartas());
        juegoIniciado=false;

    }

    

    /**
     * Comprueba si una lista de cartas puede formar una mesa final valida
     * Las sumas validas son 10, 25, 40 y 55
     * @param cartasFinalesMesa lista de cartas a comprobar
     * @return true si la suma de las cartas es valida, false en caso contrario
     * @throws IllegalArgumentException si la lista de cartas es nula
     * @throws IllegalStateException si la suma de las cartas no es valida
     */
    public boolean comprobarMesaFinal(List<Carta> cartasFinalesMesa){

        esNull(cartasFinalesMesa);
        int valor=0;

        for(Carta cartita: cartasFinalesMesa){
            valor+=cartita.getValor();
        }


        if(valor==10||valor==25||valor==40||valor==55){
            return true;
        }else{
            throw new IllegalStateException("No puede darse una mesa final con otra suma que no sea 10,25,40 o 55");
        }


    }

    /**
     * Obtiene el ultimo jugador que realizo una captura
     * @return ultimoJugador jugador que realizo la ultima captura
     */
    public Jugador devolverUltimoJugador(){

        
        return this.ultimoJugador;
        


    }

    



}





