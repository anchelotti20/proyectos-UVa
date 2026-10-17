package uva.tds;

/**
 * Representa un jugador
 * nombre: String que representa el nombre del jugador
 * mano: lista de cartas que representa la mano del jugador
 * cartasObtenidas: lista de cartas que representa las cartas obtenidas por el jugador
 * puntos: entero que representa los puntos del jugador
 * numeroEscobas: entero que representa el número de escobas realizadas por el jugador
 * @author Marcos Gomez Medina
 * @author Angel Antolin Caramazana
 */


import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private String nombre;
    private List<Carta> mano;
    private List<Carta> cartasObtenidas;
    private int puntos;
    private int numeroEscobas;

    /**
     * Constructor de la clase Jugador
     * Inicializa un jugador con un nombre, una mano vacia, una lista de cartas obtenidas vacia,
     * 0 puntos y 0 escobas realizadas
     * @param nombre nombre del jugador
     * @throws IllegalArgumentException si el nombre es nulo o vacio
     */
    public Jugador(String nombre){

        esNull(nombre);
        if(nombre.isEmpty()){
            throw new IllegalArgumentException("el nombre no puede ser nulo o vacio.");
        
        }
        

        this.nombre=nombre;
        this.mano=new ArrayList<>();
        this.cartasObtenidas=new ArrayList<>();
        this.puntos=0;
        this.numeroEscobas=0;
            

        


    }

    /**
     * Metodo privado para comprobar si un objeto es nulo
     * @param objeto objeto a comprobar
     * @throws IllegalArgumentException si el objeto es nulo
     */
    private void esNull(Object objeto){
        if(objeto==null){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Metodo para comprobar si una carta esta en la mano del jugador
     * @param carta carta a comprobar
     * @throws IllegalArgumentException si la carta no esta en la mano del jugador
     */
    public boolean contieneCarta(Carta carta){

        esNull(carta);
        
        return mano.contains(carta);
    }

    /**
     * Permite obtener el nombre del jugador
     * @return nombre del jugador
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Permite obtener la mano del jugador
     * @return lista de cartas que representa la mano del jugador
     */
    public List<Carta> getMano(){
        return this.mano;
    }

    /**
     * Permite obtener las cartas obtenidas por el jugador
     * @return lista de cartas obtenidas por el jugador
     */
    public List<Carta> getCartasObtenidas(){
        return this.cartasObtenidas;
    }
    
    /**
     * Permite obtener los puntos del jugador
     * @return puntos del jugador
     */
    public int getPuntos(){
        return this.puntos;
    }


    /**
     * Permite obtener el numero de escobas realizadas por el jugador
     * @return numero de escobas realizadas por el jugador
     */
    public int getNumeroEscobas(){
        return this.numeroEscobas;
    }

    /**
     * Suma puntos al jugador
     * @param puntos puntos a sumar
     * @throws IllegalArgumentException si los puntos son negativos
     */
    public void sumarPuntos(int puntos){
        if(puntos < 0){
            throw new IllegalArgumentException("El puntaje no puede ser 0 o negativo.");
        }
        this.puntos+=puntos;
        
    }

    /**
     * Metodo para establecer la mano del jugador
     * @param cartas lista de cartas a establecer como mano del jugador
     * @throws IllegalArgumentException si la lista de cartas no tiene 3 cartas
     */
    public void setMano(List<Carta> cartas){

        if(cartas.size()!=3){
            throw new IllegalArgumentException("No pueden repartirse las cartas  que no sean de  3 en 3");
        }

        mano.clear();

        
        for(Carta cartita: cartas){
            mano.add(cartita);
        }   

    }

    /**
     * Metodo para jugar una carta por parte del jugador, indicando la carta que se va a jugar
     * @param cartaAJugar carta que se va a jugar
     * @throws IllegalArgumentException si la carta que se pretende jugar no se encuentra en la mano
     */
    public void juegaCartaJugador(Carta cartaAJugar){

        esNull(cartaAJugar);
        if(!contieneCarta(cartaAJugar)){
            throw new IllegalArgumentException("La carta que se pretende jugar no se encuentra en la mano.");
        }

        mano.remove(cartaAJugar);

    }

    /**
     * Metodo para que el jugador capture cartas de la mesa
     * @param cartaJugadaDeMano carta jugada por el jugador desde su mano
     * @param cartasJugarMesa lista de cartas jugadas desde la mesa
     * @throws IllegalArgumentException si la suma de las cartas no es 15, 
     * o si la carta jugada de la mano no ha sido jugada aun
     * 
     */
    public void captura(Carta cartaJugadaDeMano,List<Carta> cartasJugarMesa){

        int sumatorio=0;

        esNull(cartaJugadaDeMano);
        esNull(cartasJugarMesa);

        if(contieneCarta(cartaJugadaDeMano)) {
            throw new IllegalArgumentException("El jugador aun no ha jugado la carta de su mano.");
        }

        sumatorio += cartaJugadaDeMano.getValor();
        for(Carta cartaMesa : cartasJugarMesa){
            sumatorio += cartaMesa.getValor();
        }

        if(sumatorio != 15){
            throw new IllegalArgumentException("La suma de las cartas no es 15.");
        }

        this.cartasObtenidas.add(cartaJugadaDeMano);
        this.cartasObtenidas.addAll(cartasJugarMesa);

        mano.remove(cartaJugadaDeMano);

    }
    /**
     * Incrementa en 1 el numero de escobas realizadas por el jugador
     */
    public void sumaEscoba(){

        this.numeroEscobas+=1;
    }

    /**
     * Comprueba si el jugador tiene el punto del guindis (tener el 7 de oros)
     * @return 1 si el jugador tiene el 7 de oros, 0 en caso contrario
     */
    public int getGuindis(){

        if(this.cartasObtenidas.contains(new Carta(7))){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * Metodo para comparar dos objetos Jugador
     * @param obj objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj){

        if(this == obj){
            return true;
        }

        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        Jugador otroJugador = (Jugador) obj;

        return this.nombre.equals(otroJugador.getNombre());
    }

    /**
     * Metodo para obtener el hashcode del jugador
     * @return hashcode del jugador
     */
    @Override
    public int hashCode(){
        return nombre.hashCode();
    }
}