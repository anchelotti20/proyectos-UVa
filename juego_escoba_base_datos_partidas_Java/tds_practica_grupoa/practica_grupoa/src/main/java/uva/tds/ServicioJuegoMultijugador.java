package uva.tds;

/**
 * Clase que representa el servicio del juego multijugador.
 * Permite crear salas, unirse a ellas y gestionar la comunicación
 * entre los jugadores durante la partida.
 * servicioMultijugador: instancia del servicio multijugador.
 * gestorPartidaMultijugador: gestor de la partida multijugador.
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import java.util.List;

public class ServicioJuegoMultijugador {
    
    private ServicioMultijugador servicioMultijugador;
    private GestorPartidaMultijugador gestorPartidaMultijugador;

    /**
     * Establece el servicio multijugador a utilizar.
     * @param servicioMultijugador instancia del servicio multijugador.
     * @throws IllegalArgumentException si el servicio multijugador es null.
     */
    public void setServicioMultijugador(ServicioMultijugador servicioMultijugador) {
        
        if(servicioMultijugador == null){
            throw new IllegalArgumentException("El servicio multijugador no puede ser nulo");
        }

        this.servicioMultijugador = servicioMultijugador;
    }

    /**
     * Valida que un objeto no sea null.
     * @param obj objeto a validar.
     * @throws IllegalStateException si el objeto es null.
     */
    private void esNull(Object obj) {
        if (obj == null) {
            throw new IllegalStateException("El objeto no puede ser nulo");
        }

    }

    /**
     * Obtiene el servicio multijugador.
     * @return instancia del servicio multijugador.
     */
    public ServicioMultijugador getServicioMultijugador() {
        return this.servicioMultijugador;
    }

    /**
     * Obtiene el gestor de la partida multijugador.
     * @return instancia del gestor de la partida multijugador.
     */
    public GestorPartidaMultijugador getGestorMultijugador() {
        return this.gestorPartidaMultijugador;
    }

    /**
     * Crea una sala de juego multijugador.
     * Localmente, inicializa el gestor de la partida multijugador con el creador de la sala.
     * @param idSala string que representa el identificador de la sala.
     * @param creador string que representa el nombre del creador
     * @param esPrivada booleano que indica si la sala es privada o pública.
     * @throws Exception si ocurre un error al crear la sala en el servicio multijugador
     */
    public void crearSala(String idSala, String creador, boolean esPrivada) {
        
        
        esNull(servicioMultijugador);

        servicioMultijugador.crearSala(idSala, creador, esPrivada);
        gestorPartidaMultijugador = new GestorPartidaMultijugador(creador);
        
    }

    /**
     * Lista las salas de juego disponibles en el servicio multijugador.
     * @return lista de identificadores de salas disponibles.
     */
    public List<String> listarSalasDisponibles() {
        
        
        esNull(servicioMultijugador);
        
        
        return servicioMultijugador.listarSalasDisponibles();
    }

    /**
     * Permite a un jugador unirse a una sala de juego multijugador.
     * Localmente, establece el nombre del jugador 2 en el gestor de la partida multijugador.
     * @param idSala string identificador de la sala a la que se quiere unir el jugador.
     * @param nombreJugador string nombre del jugador que se une a la sala.
     * @param codigoAcceso string código de acceso para unirse a la sala (si es privada).
     * @throws Exception si ocurre un error al unirse a la sala en el servicio multijugador.
     */
    public void unirseASala(String idSala, String nombreJugador, String codigoAcceso) {

        esNull(servicioMultijugador);

      
        servicioMultijugador.unirseASala(idSala, nombreJugador, codigoAcceso);
        gestorPartidaMultijugador.setJugador2(nombreJugador);
        
        
    }

    /**
     * Envía las cartas de la mesa al servicio multijugador.
     * Localmente, actualiza el estado del gestor de la partida multijugador con las cartas enviadas.
     * @param idSala string identificador de la sala.
     * @param creador string identificador del creador de la sala.
     * @param cartas lista de cartas a enviar.
     * @throws Exception si ocurre un error al enviar las cartas al servicio multijugador.
     */
    public void enviarCartasDeMesa(String idSala, String creador, List<String> cartas){

        esNull(servicioMultijugador);

        servicioMultijugador.enviarCartasDeMesa(idSala, creador, cartas);
        gestorPartidaMultijugador.getGestor().repartoCartasMesa(gestorPartidaMultijugador.getGestor().getParser().parsea(cartas));


    }

    /**
     * Envía las cartas de un jugador al servicio multijugador.
     * Localmente, actualiza el estado del gestor de la partida multijugador con las cartas enviadas.
     * @param idSala string identificador de la sala.
     * @param creador string identificador del creador de la sala.
     * @param nombreJugador string nombre del jugador al que se le envía las cartas.
     * @param cartas lista de cartas a enviar.
     * @throws Exception si ocurre un error al enviar las cartas al servicio multijugador.
     */
    public void enviarCartasDeJugador(String idSala, String creador, String nombreJugador, List<String> cartas){

        esNull(servicioMultijugador);
            servicioMultijugador.enviarCartasDeJugador(idSala, creador, nombreJugador, cartas);
            
        if(nombreJugador.equals(gestorPartidaMultijugador.getJugador1())){
                
            gestorPartidaMultijugador.getGestor().repartoCartasJugador(gestorPartidaMultijugador.getGestor().getParser().parsea(cartas), 1);
            
        } else {
                
            gestorPartidaMultijugador.getGestor().repartoCartasJugador(gestorPartidaMultijugador.getGestor().getParser().parsea(cartas), 2);
        }

        
    }

    /**
     * Obtiene las cartas en la mesa desde el servicio multijugador.
     * @param idSala string identificador de la sala.
     * @return lista de cartas en la mesa.
     * @throws Exception si ocurre un error al obtener las cartas desde el servicio multijugador.
     */
    public List<String> getCartasMesa(String idSala){

        esNull(servicioMultijugador);

       

        return servicioMultijugador.getCartasMesa(idSala);

      
        

    }

    /**
     * Obtiene las cartas en la mano de un jugador desde el servicio multijugador.
     * @param idSala string identificador de la sala.
     * @param nombreJugador string nombre del jugador.
     * @return lista de cartas en la mano del jugador.
     * @throws Exception si ocurre un error al obtener las cartas desde el servicio multijugador.
     */
    public List<String> getCartasManoJugador(String idSala,String nombreJugador){

        esNull(servicioMultijugador);

        return servicioMultijugador.getCartasManoJugador(idSala, nombreJugador);

       

    }

    /**
     * Envía la carta jugada por un jugador al servicio multijugador.
     * Localmente, actualiza el estado del gestor de la partida multijugador con la carta jugada
     * comprueba antes de mandarla que la jugada sea válida.
     * @param idSala string identificador de la sala.
     * @param nombreJugador string nombre del jugador que juega la carta.
     * @param carta string carta jugada.
     * @throws Exception si ocurre un error al enviar la carta jugada al servicio multijugador.
     */
    public void enviarCartaJugada(String idSala, String nombreJugador, String carta){

        esNull(servicioMultijugador);

        esNull(gestorPartidaMultijugador.getJugador2());

        

        gestorPartidaMultijugador.jugarCartaLocal(gestorPartidaMultijugador.parsearCarta(carta));

        servicioMultijugador.enviarCartaJugada(idSala, nombreJugador, carta);

        

    }

    /**
     * Envía las cartas capturadas por un jugador al servicio multijugador.
     * Localmente, actualiza el estado del gestor de la partida multijugador con las cartas capturadas.
     * @param idSala string identificador de la sala.
     * @param nombreJugador string nombre del jugador que captura las cartas.
     * @param cartas lista de cartas capturadas.
     * @throws Exception si ocurre un error al enviar las cartas capturadas al servicio multijugador.
     */
    public void enviarCartasCapturadas(String idSala, String nombreJugador, List<String> cartas){

        esNull(servicioMultijugador);

        esNull(gestorPartidaMultijugador.getJugador2());

       

        gestorPartidaMultijugador.capturarCartasLocal(gestorPartidaMultijugador.parsearLista(cartas));
        servicioMultijugador.enviarCartasCapturadas(idSala, nombreJugador, cartas);

       

    }

    /**
     * Obtiene la carta jugada por un jugador desde el servicio multijugador.
     * @param idSala string identificador de la sala.
     * @param nombreJugador string nombre del jugador.
     * @return carta jugada por el jugador.
     * @throws Exception si ocurre un error al obtener la carta jugada desde el servicio multijugador.
     */
    public String getCartaJugadaPorJugador(String idSala, String nombreJugador){

        esNull(servicioMultijugador);

        
        return servicioMultijugador.getCartaJugadaPorJugador(idSala, nombreJugador);


    }

    /**
     * Obtiene las cartas capturadas por un jugador desde el servicio multijugador.
     * @param idSala string identificador de la sala.
     * @param nombreJugador string nombre del jugador.
     * @return lista de cartas capturadas por el jugador.
     * @throws Exception si ocurre un error al obtener las cartas capturadas desde el servicio multijugador.
     */
    public List<String> getCartasCapturadasPorJugador(String idSala, String nombreJugador){

        esNull(servicioMultijugador);

        

        return servicioMultijugador.getCartasCapturadasPorJugador(idSala, nombreJugador);

        

    }

    /**
     * Obtiene las cartas en la mesa desde el servicio multijugador y actualiza el estado local.
     * @param idSala string identificador de la sala.
     * @throws Exception si ocurre un error al obtener las cartas desde el servicio multijugador.
     */
    public void recibirCartasDeMesa (String idSala){


        esNull(servicioMultijugador);

    
        List<String> cartasMesa = servicioMultijugador.getCartasMesa(idSala);
        gestorPartidaMultijugador.repartirMesaLocal(gestorPartidaMultijugador.parsearLista(cartasMesa));

        

    }

    /**
     * Obtiene las cartas en la mano de un jugador desde el servicio multijugador y actualiza el estado local.
     * @param idSala string identificador de la sala.
     * @param nombreJugador string nombre del jugador.
     * @throws Exception si ocurre un error al obtener las cartas desde el servicio multijugador.
     */
    public void recibirCartasDeJugador (String idSala, String nombreJugador){

        esNull(servicioMultijugador);

        

        List<String> cartasJugador = servicioMultijugador.getCartasManoJugador(idSala, nombreJugador);
            
        if(nombreJugador.equals(gestorPartidaMultijugador.getJugador1())){
                
            gestorPartidaMultijugador.repartirManoLocal(gestorPartidaMultijugador.parsearLista(cartasJugador), 1);
            
        } else {
                
            gestorPartidaMultijugador.repartirManoLocal(gestorPartidaMultijugador.parsearLista(cartasJugador), 2);
        }
        

    }

    /**
     * Obtiene la carta jugada por un jugador desde el servicio multijugador y actualiza el estado local.
     * @param idSala string identificador de la sala.
     * @param nombreJugador string nombre del jugador.
     * @throws Exception si ocurre un error al obtener la carta jugada desde el servicio multijugador.
     */
    public void recibirJugadaDeJugador (String idSala, String nombreJugador){

        esNull(servicioMultijugador);

        esNull(gestorPartidaMultijugador.getJugador2());

        

        String cartaJugada = servicioMultijugador.getCartaJugadaPorJugador(idSala, nombreJugador);
        List<String> cartasCapturadas = servicioMultijugador.getCartasCapturadasPorJugador(idSala, nombreJugador);

        gestorPartidaMultijugador.jugarCartaLocal(gestorPartidaMultijugador.parsearCarta(cartaJugada));
            
        if(!cartasCapturadas.isEmpty()){
                
            gestorPartidaMultijugador.capturarCartasLocal(gestorPartidaMultijugador.parsearLista(cartasCapturadas));
        }

        
    }
}
