package uva.tds;

/**
 * Clase de test para la clase GestorPartidaMultijugador
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestorPartidaMultijugadorTest {
    
    /**
     * Atributos de la clase GestorPartidaMultijugadorTest
     * utilizados en los distintos test e inicializados en el metodo setUp()
     */
    private GestorPartidaMultijugador gestorMultijugador;
    private String jugador1Nombre;
    private String jugador2Nombre;
    private Jugador jugador1;
    private Jugador jugador2;
    private Carta carta1;
    private Carta carta2;
    private Carta carta3;
    private Carta carta4;
    private Carta carta5;
    private Carta carta6;
    private Carta carta7;
    private Carta carta8;
    private Carta carta9;
    private Carta carta10;
    private List<Carta> cartasMesa;
    private List<Carta> cartasJugador1;
    private List<Carta> cartasJugador2;

    /**
     * Metodo setUp() que inicializa los atributos
     * utilizados en los distintos test
     */
    @BeforeEach
    void setUp() {
        
        gestorMultijugador = new GestorPartidaMultijugador("Ana");

        jugador1Nombre = "Ana";
        jugador2Nombre = "Luis";

        jugador1 = new Jugador(jugador1Nombre);
        jugador2 = new Jugador(jugador2Nombre);

        carta1 = new Carta(1);
        carta2 = new Carta(2);
        carta3 = new Carta(3);
        carta4 = new Carta(4);
        carta5 = new Carta(5);
        carta6 = new Carta(6);
        carta7 = new Carta(7);
        carta8 = new Carta(8);
        carta9 = new Carta(9);
        carta10 = new Carta(10);

        cartasMesa = new ArrayList<>(Arrays.asList(carta1, carta2, carta3, carta4));
        cartasJugador1 = new ArrayList<>(Arrays.asList(carta5, carta6, carta7));
        cartasJugador2 = new ArrayList<>(Arrays.asList(carta8, carta9, carta10));

    }

    /**
     * Test del constructor de la clase GestorPartidaMultijugador valido
     */
    @Test
    void testConstructorValido(){

        assertEquals("Ana", gestorMultijugador.getJugador1());

    }

    /**
     * Test del constructor de la clase GestorPartidaMultijugador no valido con nombre null
     */
    @Test
    void testConstructorNoValidoNull(){

        assertThrows(IllegalArgumentException.class, () -> {
            new GestorPartidaMultijugador(null);
        });
    }

    /**
     * Test del constructor de la clase GestorPartidaMultijugador no valido con nombre vacio
     */
    @Test
    void testConstructorNoValidoVacio(){

        assertThrows(IllegalArgumentException.class, () -> {
            new GestorPartidaMultijugador("");
        });
    }
    
    /**
     * Test del metodo setJugador2 valido
     */
    @Test
    void testSetJugador2Valido(){

        gestorMultijugador.setJugador2("Luis");

        assertEquals("Luis", gestorMultijugador.getJugador2());
        assertTrue(gestorMultijugador.getGestor().juegoIniciado());
        assertEquals(1, gestorMultijugador.getGestor().getTurnoActual());
        assertEquals(jugador1, gestorMultijugador.getGestor().getJugador1());
        assertEquals(jugador2, gestorMultijugador.getGestor().getJugador2());

    }

    /**
     * Test del metodo setJugador2 no valido con nombre null
     */
    @Test
    void testSetJugador2NoValidoNull(){

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.setJugador2(null);
        });
    }

    /**
     * Test del metodo setJugador2 no valido con nombre vacio
     */
    @Test
    void testSetJugador2NoValidoVacio(){

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.setJugador2("");
        });
    }

    /**
     * Test del metodo crearGestor valido
     */
    @Test
    void testCrearGestorValido(){
        
        gestorMultijugador.setJugador2("Luis");

        gestorMultijugador.crearGestor();

        assertTrue(gestorMultijugador.getGestor().juegoIniciado());
        assertEquals(1, gestorMultijugador.getGestor().getTurnoActual());
        assertEquals(jugador1, gestorMultijugador.getGestor().getJugador1());
        assertEquals(jugador2, gestorMultijugador.getGestor().getJugador2());
        
    }


    /**
     * Test del metodo getUltimCartaJugadaJugador1 valido
     */
    @Test
    void testgetUltimCartaJugadaJugador1() {
        
        gestorMultijugador.setJugador2("Luis");

        gestorMultijugador.repartirMesaLocal(cartasMesa);
        gestorMultijugador.repartirManoLocal(cartasJugador1, 1);
        
        gestorMultijugador.jugarCartaLocal(cartasJugador1.get(0));

        assertEquals(cartasJugador1.get(0), gestorMultijugador.getUltimCartaJugadaJugador1());

    }

    /**
     * Test del metodo getUltimCartaJugadaJugador2 valido
     */
    @Test
    void testgetUltimCartaJugadaJugador2() {
        
        gestorMultijugador.setJugador2("Luis");

        gestorMultijugador.repartirMesaLocal(cartasMesa);
        gestorMultijugador.repartirManoLocal(cartasJugador1, 1);
        gestorMultijugador.repartirManoLocal(cartasJugador2, 2);
        
        gestorMultijugador.jugarCartaLocal(cartasJugador1.get(0));
        gestorMultijugador.jugarCartaLocal(cartasJugador2.get(0));

        assertEquals(cartasJugador2.get(0), gestorMultijugador.getUltimCartaJugadaJugador2());

    }

    /**
     * Test del metodo parsearCarta valido
     */
    @Test
    void testparsearCartaValido() {
        gestorMultijugador.setJugador2("Luis");

        Carta cartaParseada = gestorMultijugador.parsearCarta("5-oros");

        assertEquals(new Carta(5), cartaParseada);
    }  

    /**
     * Test del metodo parsearCarta no valido con carta null
     */
    @Test
    void testparsearCartaNoValidoNull() {
        gestorMultijugador.setJugador2("Luis");

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.parsearCarta(null);
        });
    }

    /**
     * Test del metodo parsearLista valido
     */
    @Test
    void parsearListaValido() {
        gestorMultijugador.setJugador2("Luis");

        List<String> cartasStr = Arrays.asList("1-oros", "2-oros", "3-oros");
        List<Carta> cartasParseadas = gestorMultijugador.parsearLista(cartasStr);

        List<Carta> cartasEsperadas = Arrays.asList(new Carta(1), new Carta(2), new Carta(3));

        assertEquals(cartasEsperadas, cartasParseadas);
    }

    /**
     * Test del metodo parsearLista no valido con lista null
     */
    @Test
    void parsearListaNoValidoNull() {
        gestorMultijugador.setJugador2("Luis");

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.parsearLista(null);
        });
    }
    
    /**
     * Test del metodo jugarCartaLocal no valido con carta null
     */
    @Test
    void testJuegaCartaLocalNoValidoNull() {
        gestorMultijugador.setJugador2("Luis");

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.jugarCartaLocal(null);
        });
    }

    /**
     * Test del metodo jugarCartaLocal no valido carta no en mano
     */
    @Test
    void testJuegaCartaLocalNoValidoNoEnMano() {
        gestorMultijugador.setJugador2("Luis");

        gestorMultijugador.repartirMesaLocal(cartasMesa);
        gestorMultijugador.repartirManoLocal(cartasJugador1, 1);
        Carta carta = cartasJugador2.get(0);

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.jugarCartaLocal((carta));
        });
    }

    /**
     * Test del metodo capturarCartasLocal valido sin escoba
     */
    @Test
    void testCapturarCartasLocalValidoSinEscoba() {
        gestorMultijugador.setJugador2("Luis");

        gestorMultijugador.repartirMesaLocal(cartasMesa);
        gestorMultijugador.repartirManoLocal(cartasJugador1, 1);

        gestorMultijugador.jugarCartaLocal(cartasJugador1.get(1));

        gestorMultijugador.capturarCartasLocal(Arrays.asList(carta2, carta3, carta4));

        assertTrue(gestorMultijugador.getGestor().getJugador1().getCartasObtenidas().containsAll(Arrays.asList(carta2, carta3, carta4, cartasJugador1.get(1))));
        assertEquals(Arrays.asList(carta5, carta7), gestorMultijugador.getGestor().getJugador1().getMano());

    }

    /**
     * Test del metodo capturarCartasLocal valido con escoba
     */
    @Test
    void testCapturarCartasLocalValidoEscoba(){

        gestorMultijugador.setJugador2("Luis");

        gestorMultijugador.repartirMesaLocal(cartasMesa);
        gestorMultijugador.repartirManoLocal(cartasJugador1, 1);

        gestorMultijugador.jugarCartaLocal(cartasJugador1.get(0));

        gestorMultijugador.capturarCartasLocal(Arrays.asList(carta1, carta2, carta3, carta4));

        assertTrue(gestorMultijugador.getGestor().getJugador1().getCartasObtenidas().containsAll(Arrays.asList(carta1, carta2, carta3, carta4, cartasJugador1.get(0))));
        assertEquals(1, gestorMultijugador.getGestor().getJugador1().getNumeroEscobas());
        assertEquals(Arrays.asList(carta6, carta7), gestorMultijugador.getGestor().getJugador1().getMano());
    }

    /**
     * Test del metodo capturarCartasLocal no valido con lista null
     */
    @Test
    void testCapturarCartasLocalNoValidoNull() {
        gestorMultijugador.setJugador2("Luis");

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.capturarCartasLocal(null);
        });
    }

    /**
     * Test del metodo capturarCartasLocal no valido con lista vacia
     */
    @Test
    void testCapturarCartasLocalNoValidoVacio() {
        gestorMultijugador.setJugador2("Luis");
        List<Carta> listaCartas= new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.capturarCartasLocal(listaCartas);
        });
    }

    /**
     * Test del metodo capturarCartasLocal no valido cuando suma no es 15
     */
    @Test
    void testCapturarCartasLocalNoValidoSumaNo15() {
        gestorMultijugador.setJugador2("Luis");

        gestorMultijugador.repartirMesaLocal(cartasMesa);
        gestorMultijugador.repartirManoLocal(cartasJugador1, 1);

        gestorMultijugador.jugarCartaLocal(cartasJugador1.get(2));

        List<Carta> cartasACapturar= Arrays.asList(carta1,carta2);

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.capturarCartasLocal(cartasACapturar);
        });
    }

    /**
     * Test del metodo capturarCartasLocal no valido cuando no ha jugado
     */
    @Test
    void testCapturarCartasLocalNoValidoNoHaJugado() {
        gestorMultijugador.setJugador2("Luis");

        gestorMultijugador.repartirMesaLocal(cartasMesa);
        gestorMultijugador.repartirManoLocal(cartasJugador1, 1);
        List<Carta> cartasACapturar= Arrays.asList(carta1,carta2,carta3);

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.capturarCartasLocal(cartasACapturar);
        });
    }
    
    /**
     * Test del metodo repartirMesaLocal valido
     */
    @Test
    void testRepartirMesaLocalValido() {
        gestorMultijugador.setJugador2("Luis");

        gestorMultijugador.repartirMesaLocal(cartasMesa);

        assertEquals(cartasMesa, gestorMultijugador.getGestor().getMesa().getCartas());
    }

    /**
     * Test del metodo repartirMesaLocal no valido con mesa null
     */
    @Test
    void testRepartirMesaLocalNoValidoNull() {
        gestorMultijugador.setJugador2("Luis");

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.repartirMesaLocal(null);
        });
    }

    /**
     * Test del metodo repartirMesaLocal no valido con tamano incorrecto
     */
    @Test
    void testRepartirMesaLocalNoValidoTamanoIncorrecto() {
        gestorMultijugador.setJugador2("Luis");

        List<Carta> cartasInvalidas = Arrays.asList(carta1, carta2);

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.repartirMesaLocal(cartasInvalidas);
        });
    }

    /**
     * Test del metodo repartirManoLocal valido
     */
    @Test
    void testRepartirManoLocalValido() {
        gestorMultijugador.setJugador2("Luis");

        gestorMultijugador.repartirManoLocal(cartasJugador1, 1);

        assertEquals(cartasJugador1, gestorMultijugador.getGestor().getJugador1().getMano());
    }

    /**
     * Test del metodo repartirManoLocal no valido con mano null
     */
    @Test
    void testRepartirManoLocalNoValidoNull() {
        gestorMultijugador.setJugador2("Luis");

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.repartirManoLocal(null, 1);
        });
    }

    /**
     * Test del metodo repartirManoLocal no valido con tamano incorrecto
     */
    @Test
    void testRepartirManoLocalNoValidoTamanoIncorrecto() {
        gestorMultijugador.setJugador2("Luis");

        List<Carta> cartasInvalidas = Arrays.asList(carta1, carta2);

        assertThrows(IllegalArgumentException.class, () -> {
            gestorMultijugador.repartirManoLocal(cartasInvalidas, 1);
        });
    }

}