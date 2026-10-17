package uva.tds;

/**
 * Clase de test para la clase GestorPartida
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GestorPartidaTest {

    
    /**
     * Atributos de la clase GestorPartidaTest
     * Usados en la Fixture para pruebas comunes en test
     */
    private GestorPartida gestorPartida;
    private Baraja baraja;
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
    private Carta carta14;
    private Carta carta15;
    private Carta carta16;
    private Carta carta17;
    private Carta carta27;
    private Carta carta37;
    private List<Carta> cartasJugador1;
    private List<Carta> cartasJugador2;
    private List<Carta> cartasMesa;
    
    /**
     * Fixture para inicializar objetos comunes a varios tests
     */
    @BeforeEach
    void setUp(){

        
        baraja = new Baraja();
        List<Carta> cartasBaraja = baraja.getCartas(); 
        carta1 = cartasBaraja.get(0); //As de oros
        carta2 = cartasBaraja.get(1); //2 de oros
        carta3 = cartasBaraja.get(2); //3 de oros
        carta4 = cartasBaraja.get(3); //4 de oros
        carta5 = cartasBaraja.get(4); //5 de oros
        carta6 = cartasBaraja.get(5); //6 de oros
        carta7 = cartasBaraja.get(6); //7 de oros
        carta8 = cartasBaraja.get(7); //Sota de oros
        carta9 = cartasBaraja.get(8); //Caballo de oros
        carta10 = cartasBaraja.get(9); //Rey de oros
        carta14 = cartasBaraja.get(13); //4 de copas
        carta15 = cartasBaraja.get(14); //5 de copas
        carta16 = cartasBaraja.get(15); //6 de copas
        carta17 = cartasBaraja.get(16); //7 de copas
        carta27 = cartasBaraja.get(26); //7 de espadas
        carta37 = cartasBaraja.get(36); //7 de bastos
        cartasMesa = new ArrayList<>(Arrays.asList(carta1, carta2, carta3, carta4));
        cartasJugador1 = new ArrayList<>(Arrays.asList(carta5, carta6, carta7));
        cartasJugador2 = new ArrayList<>(Arrays.asList(carta8, carta9, carta10));
        jugador1 = new Jugador("Angel");
        jugador2 = new Jugador("Marcos");
        gestorPartida = new GestorPartida(baraja, jugador1, jugador2);

    }

    /**
     * Test del constructor de la clase GestorPartida valido
     */
    @Test
    void testConstructorValido(){
        
        GestorPartida gestor = new GestorPartida(baraja, jugador1, jugador2);

        assertTrue(gestor.juegoIniciado());
        assertEquals(1, gestor.getTurnoActual());

    }

    /**
     * Test del constructor de la clase GestorPartida no valido con jugadores con el mismo nombre
     */
    @Tag("Cobertura")
    @Test
    void testConstructorNoValidoJugadoresNombreIgual(){
        
        assertThrows(IllegalArgumentException.class, () -> {
            new GestorPartida(baraja, jugador1, jugador1);
        });

        

    }



    /**
     * Test del constructor de la clase GestorPartida no valido baraja null
     */
    @Test
    void testConstructorInvalidoNullBaraja(){

        assertThrows(IllegalArgumentException.class, () -> {
            new GestorPartida(null, jugador1, jugador2);
        });
    }

    /**
     * Test del constructor de la clase GestorPartida no valido jugador1 null
     */
    @Test
    void testConstructorInvalidoNullJugador1(){

        assertThrows(IllegalArgumentException.class, () -> {
            new GestorPartida(baraja, null, jugador2);
        });

    }

    /**
     * Test del constructor de la clase GestorPartida no valido jugador2 null
     */
    @Test
    void testConstructorInvalidoNullJugador2(){

        assertThrows(IllegalArgumentException.class, () -> {
            new GestorPartida(baraja, jugador1, null);
        });

    }



    /**
     * Test del metodo getJuegoIniciado de la clase GestorPartida
     */
    @Test
    void testGetJuegoIniciado(){
        assertTrue(gestorPartida.juegoIniciado());
    }

    /**
     * Test del metodo getTurnoActual de la clase GestorPartida
     */
    @Test
    void testGetTurnoActual(){
        assertEquals(1, gestorPartida.getTurnoActual());
    }

    /**
     * Test del metodo getBaraja de la clase GestorPartida
     */
    @Test
    void testGetBaraja(){
        assertEquals(baraja, gestorPartida.getBaraja());
    }

    /** 
     * Test del metodo getJugador1 de la clase GestorPartida
     */
    @Test
    void testGetJugador1(){
        assertEquals(jugador1, gestorPartida.getJugador1());
    }

    /** 
     * Test del metodo getJugador2 de la clase GestorPartida
     */
    @Test
    void testGetJugador2(){
        assertEquals(jugador2, gestorPartida.getJugador2());
    }

    /**
     * Test del metodo repartoCartasMesa de la clase GestorPartida valido
     */
    @Test
    void testRepartoCartasMesaValido(){

        gestorPartida.repartoCartasMesa(cartasMesa);
        assertArrayEquals(cartasMesa.toArray(), gestorPartida.getMesa().getCartas().toArray());


    }

    /**
     * Test del metodo repartoCartasMesa de la clase GestorPartida no valido lista nula
     */
    @Test
    void testRepartoCartasMesaNoValidoListaNula(){

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.repartoCartasMesa(null);
        });

    }

    /**
     * Test del metodo repartoCartasMesa de la clase GestorPartida no valido lista con menos de 4 cartas
     */
    @Test
    void testRepartoCartasMesaNoValidoListaMenosDe4(){

        List<Carta> cartasMenos4 = new ArrayList<>(Arrays.asList(carta1, carta2, carta3));

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.repartoCartasMesa(cartasMenos4);
        });

    }

    /**
     * Test del metodo repartoCartasMesa de la clase GestorPartida no valido lista con mas de 4 cartas
     */
    @Test
    void testRepartoCartasMesaNoValidoListaMasDe4(){

        List<Carta> cartasMas4 = new ArrayList<>(Arrays.asList(carta1, carta2, carta3, carta4, carta5));

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.repartoCartasMesa(cartasMas4);
        });

    }

    /**
     * Test del metodo repartoCartasMesa de la clase GestorPartida no valido lista con carta repetida
     */
    @Test
    void testRepartoCartasMesaNoValidoCartaRepetida(){

        List<Carta> cartasRepetida = new ArrayList<>(Arrays.asList(carta1, carta2, carta3, carta3));

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.repartoCartasMesa(cartasRepetida);
        });

    }

    /**
     * Test del metodo repartoCartasJugador de la clase GestorPartida valido jugador 1
     */
    @Test
    void testRepartoCartasJugadorValidoJugador1(){

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);
        assertArrayEquals(cartasJugador1.toArray(), gestorPartida.getJugador1().getMano().toArray());


    }

    /**
     * Test del metodo repartoCartasJugador de la clase GestorPartida valido jugador 2
     */
    @Test
    void testRepartoCartasJugadorValidoJugador2(){

        gestorPartida.repartoCartasJugador(cartasJugador2, 2);
        assertArrayEquals(cartasJugador2.toArray(), gestorPartida.getJugador2().getMano().toArray());
    }

    /**
     * Test del metodo repartoCartasJugador de la clase GestorPartida no valido lista nula
     */
    @Test
    void testRepartoCartasJugadorNoValidoListaNula(){

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.repartoCartasJugador(null, 1);
        });

    }

    /**
     * Test del metodo repartoCartasJugador de la clase GestorPartida no valido jugador invalido
     */
    @Test
    void testRepartoCartasJugadorNoValidoJugadorInvalido(){

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.repartoCartasJugador(cartasJugador1, 3);
        });

    }

    /**
     * Test del metodo repartoCartasJugador de la clase GestorPartida no valido menos de 3 cartas
     */
    @Test
    void testRepartoCartasJugadorNoValidoMenosDe3(){

        List<Carta> cartasMenos3 = new ArrayList<>(Arrays.asList(carta1, carta2));

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.repartoCartasJugador(cartasMenos3, 1);
        });

    }

    /**
     * Test del metodo repartoCartasJugador de la clase GestorPartida no valido mas de 3 cartas
     */
    @Test
    void testRepartoCartasJugadorNoValidoMasDe3(){

        List<Carta> cartasMas3 = new ArrayList<>(Arrays.asList(carta1, carta2, carta3, carta4));

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.repartoCartasJugador(cartasMas3, 1);
        });

    }

    /**
     * Test del metodo repartoCartasJugador de la clase GestorPartida no valido carta repetida
     */
    @Test
    void testRepartoCartasJugadorNoValidoCartaRepetida(){

        List<Carta> cartasRepetida = new ArrayList<>(Arrays.asList(carta1, carta2, carta2));

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.repartoCartasJugador(cartasRepetida, 1);
        });

    }

    /**
     * Test del metodo repartoCartasJugador de la clase GestorPartida no valido carta ya robada
     */
    @Test
    void testRepartoCartasJugadorNoValidoCartaYaRobada(){

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.repartoCartasJugador(cartasJugador1, 2);
        });

    }

    /**
     * Test del metodo jugarCarta de la clase GestorPartida valido jugador 1
     */
    @Test
    void testjugarCartaValidoJugador1(){


        gestorPartida.repartoCartasMesa(cartasMesa);

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);

        gestorPartida.jugarCarta(cartasJugador1.get(0));

        assertFalse(gestorPartida.getJugador1().getMano().contains(cartasJugador1.get(0)));
        assertArrayEquals(List.of(carta1, carta2, carta3, carta4, cartasJugador1.get(0)).toArray(), gestorPartida.getMesa().getCartas().toArray());
        assertEquals(2, gestorPartida.getTurnoActual());
        assertEquals(cartasJugador1.get(0), gestorPartida.getUltimaCartaJugada(1));

    }

    /**
     * Test del metodo jugarCarta de la clase GestorPartida valido jugador 2
     */
    @Test
    void testjugarCartaValidoJugador2(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);

        gestorPartida.repartoCartasJugador(cartasJugador2, 2);

        gestorPartida.jugarCarta(cartasJugador1.get(0));

        gestorPartida.jugarCarta(cartasJugador2.get(0));

        assertFalse(gestorPartida.getJugador2().getMano().contains(cartasJugador2.get(0)));
        assertArrayEquals(List.of(carta1, carta2, carta3, carta4, cartasJugador1.get(0), cartasJugador2.get(0)).toArray(), gestorPartida.getMesa().getCartas().toArray());
        assertEquals(1, gestorPartida.getTurnoActual());
        assertEquals(cartasJugador2.get(0), gestorPartida.getUltimaCartaJugada(2));

    }

    /**
     * Test del metodo jugarCarta de la clase GestorPartida no valido carta null
     */
    @Test
    void testjugarCartaNoValidoCartaNull(){

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.jugarCarta(null);
        });

    }

    /**
     * Test del metodo jugarCarta de la clase GestorPartida no valido carta no en mano
     */
    @Test
    void testjugarCartaNoValidoCartaNoEnMano(){

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.jugarCarta(carta8);
        });

    }

    /**
     * Test del metodo jugarCarta de la clase GestorPartida no valido jugador 2 carta no en mano
     */
    @Tag("Cobertura")
    @Test
    void testjugarCartaNoValidoJugador2CartaNoEnMano(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);

        gestorPartida.repartoCartasJugador(cartasJugador2, 2);

        gestorPartida.jugarCarta(cartasJugador1.get(0));

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.jugarCarta(carta3);
        });

        
    }

    /**
     * Test del metodo getUltimaCartaJugada de la clase GestorPartida
     */
    @Test
    void testGetUltimaCartaJugadaValidoJugador1(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);

        gestorPartida.jugarCarta(cartasJugador1.get(0));

        assertEquals(cartasJugador1.get(0), gestorPartida.getUltimaCartaJugada(1));

    }

    /**
     * Test del metodo getUltimaCartaJugada de la clase GestorPartida
     */
    @Test
    void testGetUltimaCartaJugadaValidoJugador2(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);

        gestorPartida.jugarCarta(cartasJugador1.get(0));

        gestorPartida.repartoCartasJugador(cartasJugador2, 2);

        gestorPartida.jugarCarta(cartasJugador2.get(0));

        assertEquals(cartasJugador2.get(0), gestorPartida.getUltimaCartaJugada(2));

    }

    /**
     * Test del metodo getUltimaCartaJugada de la clase GestorPartida valido no ha jugado
     */
    @Test
    void testGetUltimaCartaJugadaValidoNoHaJugado(){

        assertNull(gestorPartida.getUltimaCartaJugada(1));

    }

    /**
     * Test del metodo getUltimaCartaJugada de la clase GestorPartida jugador invalido
     */
    @Test
    void testGetUltimaCartaJugadaNoValidoJugadorInvalido(){

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.getUltimaCartaJugada(3);
        });

    }

    /** 
     * Test del metodo capturarCartas de la clase GestorPartida valido jugador 1
     */
    @Test
    void testCapturarCartasValidoJugador1(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);

        gestorPartida.jugarCarta(cartasJugador1.get(2));

        gestorPartida.capturarCartas(List.of(cartasMesa.get(0), cartasMesa.get(2), cartasMesa.get(3)));

        assertFalse(gestorPartida.getJugador1().getMano().contains(cartasJugador1.get(2)));
        assertArrayEquals(List.of(carta2).toArray(), gestorPartida.getMesa().getCartas().toArray());
        assertArrayEquals(List.of(cartasJugador1.get(2), carta1, carta3, carta4).toArray(), gestorPartida.getJugador1().getCartasObtenidas().toArray());
    }

    /** 
     * Test del metodo capturarCartas de la clase GestorPartida valido escoba jugador 1
     */
    @Tag("Cobertura")
    @Test
    void testCapturarCartasValidoEscobaJugador1(){

        List<Carta> cartasMesaEscoba=new ArrayList<>(Arrays.asList(carta1, carta2, carta3, carta4));

        cartasJugador1=new ArrayList<>(Arrays.asList(carta5, carta6, carta7));

        gestorPartida.repartoCartasMesa(cartasMesaEscoba);

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);

        gestorPartida.jugarCarta(cartasJugador1.get(0));

        gestorPartida.capturarCartas(List.of(cartasMesaEscoba.get(0),cartasMesaEscoba.get(1),cartasMesaEscoba.get(2), cartasMesaEscoba.get(3)));

        assertFalse(gestorPartida.getJugador1().getMano().contains(cartasJugador1.get(0)));
        assertEquals(true,gestorPartida.getMesa().getCartas().toArray().length==0);
        assertArrayEquals(List.of(cartasJugador1.get(0), carta1, carta2,carta3, carta4).toArray(), gestorPartida.getJugador1().getCartasObtenidas().toArray());
    }

    /** 
     * Test del metodo capturarCartas de la clase GestorPartida valido jugador 2
     */
    @Test
    void testCapturarCartasValidoJugador2(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);

        gestorPartida.repartoCartasJugador(cartasJugador2, 2);

        gestorPartida.jugarCarta(cartasJugador1.get(0));

        gestorPartida.jugarCarta(cartasJugador2.get(2));

        gestorPartida.capturarCartas(List.of(cartasMesa.get(0), cartasMesa.get(3)));

        assertFalse(gestorPartida.getJugador2().getMano().contains(cartasJugador2.get(2)));
        assertArrayEquals(List.of(carta2, carta3, cartasJugador1.get(0)).toArray(), gestorPartida.getMesa().getCartas().toArray());
        assertArrayEquals(List.of(cartasJugador2.get(2), carta1, carta4).toArray(), gestorPartida.getJugador2().getCartasObtenidas().toArray());
    
    }

    /** 
     * Test del metodo capturarCartas de la clase GestorPartida no valido lista nula
     */
    @Test
    void testCapturarCartasNoValidoListaNula(){

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.capturarCartas(null);
        });

    }

    /** 
     * Test del metodo capturarCartas de la clase GestorPartida no valido lista vacia
     */
    @Test
    void testCapturarCartasNoValidoListaVacia(){

        List<Carta> listVacia=new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.capturarCartas(listVacia);
        });

    }

    /** 
     * Test del metodo capturarCartas de la clase GestorPartida no valido carta no en mano
     */
    @Test
    void testCapturarCartasNoValidoCartaNoEnMesa(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);

        gestorPartida.jugarCarta(cartasJugador1.get(0));

        List<Carta> listaCarta=List.of(carta5);

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.capturarCartas(listaCarta);
        });

    }

    /** 
     * Test del metodo capturarCartas de la clase GestorPartida no valido no suman 15
     */
    @Test
    void testCapturarCartasNoValidoNoSuman15(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        gestorPartida.repartoCartasJugador(cartasJugador1, 1);

        gestorPartida.jugarCarta(cartasJugador1.get(0));

        List<Carta> listaCartas=List.of(cartasMesa.get(0));

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.capturarCartas(listaCartas);
        });
    }

    /** 
     * Test del metodo puntuacionFinal de la clase GestorPartida puntos de escobas
     */
    @Test
    void testPuntuacionFinalEscoba(){

        jugador1.sumaEscoba();
        jugador1.sumaEscoba();
        jugador2.sumaEscoba();

        gestorPartida.repartoCartasMesa(cartasMesa);

        gestorPartida.getMesa().getCartas().clear();
        jugador1.getMano().clear();
        jugador2.getMano().clear();
        baraja.getCartas().clear();

        gestorPartida.finalizarJuego();
        gestorPartida.puntuacionFinal();

        assertEquals(2, jugador1.getPuntos());
        assertEquals(7, jugador2.getPuntos()); //6 puntos por la implementacion de 
                                                        //puntuacion que le da lo que no tiene el jugador 1
    }

    /** 
     * Test del metodo puntuacionFinal de la clase GestorPartida puntos por todos oros incluido guindis
     */
    @Test
    void testPuntuacionFinalTodosOrosIncluidoGuindis(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        jugador1.captura(carta7, new ArrayList<>(Arrays.asList(carta8)));
        jugador1.captura(carta6, new ArrayList<>(Arrays.asList(carta9)));
        jugador1.captura(carta5, new ArrayList<>(Arrays.asList(carta10)));
        jugador1.captura(carta4, new ArrayList<>(Arrays.asList(carta3, carta2, carta1, carta15)));
        gestorPartida.getMesa().getCartas().clear();
        jugador1.getMano().clear();
        jugador2.getMano().clear();
        baraja.getCartas().clear();
        gestorPartida.finalizarJuego();
        gestorPartida.puntuacionFinal();

        assertEquals(4, jugador1.getPuntos());
        assertEquals(1, jugador2.getPuntos()); //punto por mayoria de 7
    
    }

    /** 
     * Test del metodo puntuacionFinal de la clase GestorPartida puntos por mayoria de cartas
     */
    @Tag("Cobertura")
    @Test
    void testPuntuacionFinalMayoriaCartas(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        for(int i=1;i<=21;i++){
            gestorPartida.getJugador1().getCartasObtenidas().add(new Carta(i));
        }

        gestorPartida.getMesa().getCartas().clear();
        jugador1.getMano().clear();
        jugador2.getMano().clear();
        baraja.getCartas().clear();
        gestorPartida.finalizarJuego();
        gestorPartida.puntuacionFinal();

        assertEquals(5, jugador1.getPuntos());//Punto por mayoria de cartas, todos los oros, mayoria de oros y guindis
    
    }

    /** 
     * Test del metodo puntuacionFinal de la clase GestorPartida puntos por mayoria oros
     */
    @Test
    void testPuntuacionFinalMayoriaOros(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        jugador1.captura(carta5, new ArrayList<>(Arrays.asList(carta10)));
        jugador1.captura(carta6, new ArrayList<>(Arrays.asList(carta9)));
        jugador1.captura(carta7, new ArrayList<>(Arrays.asList(carta8)));
        
        gestorPartida.getMesa().getCartas().clear();
        jugador1.getMano().clear();
        jugador2.getMano().clear();
        baraja.getCartas().clear();
        gestorPartida.finalizarJuego();
        gestorPartida.puntuacionFinal();

        assertEquals(2, jugador1.getPuntos()); //punto extra por el guindis
        assertEquals(1, jugador2.getPuntos()); //punto por mayoria de 7
    }

    /** 
     * Test del metodo puntuacionFinal de la clase GestorPartida puntos por guindis
     */
    @Test
    void testPuntuacionFinalGuindis(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        jugador1.captura(carta7, new ArrayList<>(Arrays.asList(carta8)));
        
        gestorPartida.getMesa().getCartas().clear();
        jugador1.getMano().clear();
        jugador2.getMano().clear();
        baraja.getCartas().clear();
        gestorPartida.finalizarJuego();
        gestorPartida.puntuacionFinal();

        assertEquals(1, jugador1.getPuntos());
        assertEquals(2, jugador2.getPuntos());//punto por mayoria de 7 y punto por mayoria de oros


    }

    /** 
     * Test del metodo puntuacionFinal de la clase GestorPartida puntos por todos 7s
     */
    @Test
    void testPuntuacionFinalCuatro7(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        jugador1.captura(carta7, new ArrayList<>(Arrays.asList(carta8)));
        jugador1.captura(carta17, new ArrayList<>(Arrays.asList(carta3, carta5)));
        jugador1.captura(carta27, new ArrayList<>(Arrays.asList(carta2, carta6)));
        jugador1.captura(carta37, new ArrayList<>(Arrays.asList(carta4, carta14)));
        

        gestorPartida.getMesa().getCartas().clear();
        jugador1.getMano().clear();
        jugador2.getMano().clear();
        baraja.getCartas().clear();
        gestorPartida.finalizarJuego();
        gestorPartida.puntuacionFinal();

        assertEquals(5, jugador1.getPuntos()); //punto extra por mayoria de oros
        assertEquals(0, jugador2.getPuntos());

    }

    /** 
     * Test del metodo puntuacionFinal de la clase GestorPartida puntos por mayoria de 7s
     */
    @Test
    void testPuntuacionFinalMayoria7(){

        gestorPartida.repartoCartasMesa(cartasMesa);


        jugador1.captura(carta17, new ArrayList<>(Arrays.asList(carta3, carta5)));
        jugador1.captura(carta27, new ArrayList<>(Arrays.asList(carta2, carta6)));
        jugador1.captura(carta37, new ArrayList<>(Arrays.asList(carta4, carta14)));


        gestorPartida.getMesa().getCartas().clear();
        jugador1.getMano().clear();
        jugador2.getMano().clear();
        baraja.getCartas().clear();
        gestorPartida.finalizarJuego();
        gestorPartida.puntuacionFinal();

        assertEquals(1, jugador1.getPuntos());
        assertEquals(0, jugador2.getPuntos()); //punto por tener guindis
    
    }

    /** 
     * Test del metodo puntuacionFinal de la clase GestorPartida puntos por empate en 7s y oros
     */
    @Test
    void testPuntuacionFinalEmpate7yOros(){

        gestorPartida.repartoCartasMesa(cartasMesa);

        jugador1.captura(carta27, new ArrayList<>(Arrays.asList(carta2, carta6)));
        jugador1.captura(carta37, new ArrayList<>(Arrays.asList(carta4, carta14)));
        
        gestorPartida.getMesa().getCartas().clear();
        jugador1.getMano().clear();
        jugador2.getMano().clear();
        baraja.getCartas().clear();
        gestorPartida.finalizarJuego();
        gestorPartida.puntuacionFinal();

        assertEquals(0, jugador1.getPuntos()); 
        assertEquals(1, jugador2.getPuntos()); //punto por guindis y mayoria oros

    }

    /** 
     * Test del metodo puntuacionFinal de la clase GestorPartida puntos por en cartas
     */
    @Test
    void testPuntuacionFinalEmpateCartas(){

        gestorPartida.repartoCartasMesa(cartasMesa);


        gestorPartida.getMesa().getCartas().clear();
        jugador1.getMano().clear();
        jugador2.getMano().clear();
        baraja.getCartas().clear();
        gestorPartida.finalizarJuego();
        gestorPartida.puntuacionFinal();

        assertEquals(0, jugador1.getPuntos());
        assertEquals(6, jugador2.getPuntos()); //punto por mayoria oros y todos 7
    }

    /** 
     * Test del metodo puntuacionFinal de la clase GestorPartida no valido juego no finalizado
     */
    @Test
    void testPuntuacionFinalNoValidoJuegoNoFinalizado(){

        assertThrows(IllegalStateException.class, () -> {
            gestorPartida.puntuacionFinal();
        });

    }

    /** 
     * Test del metodo finalizarJuego de la clase GestorPartida
     */
    @Test
    void testJuegoFinalizado(){

        gestorPartida.repartoCartasMesa(cartasMesa);
        gestorPartida.repartoCartasJugador(cartasJugador1, 1);
        gestorPartida.repartoCartasJugador(cartasJugador2, 2);

        gestorPartida.jugarCarta(cartasJugador1.get(2));

        gestorPartida.capturarCartas(List.of(cartasMesa.get(0), cartasMesa.get(2), cartasMesa.get(3)));
        
        List<Carta> cartasMesa2=new ArrayList<>(Arrays.asList(new Carta(22),new Carta(25),carta37,carta27));
        
        gestorPartida.getMesa().setCartas(cartasMesa2);

        gestorPartida.jugarCarta(cartasJugador2.get(0));

        gestorPartida.capturarCartas(List.of(gestorPartida.getMesa().getCartas().get(0), gestorPartida.getMesa().getCartas().get(1)));
        
        jugador1.getMano().clear();
        jugador2.getMano().clear();
        baraja.getCartas().clear();

        List<Carta> cartasEsperadas=new ArrayList<>(Arrays.asList(carta8,new Carta(22),new Carta(25),carta37,carta27));
        
        gestorPartida.finalizarJuego();

        assertEquals(cartasEsperadas,jugador2.getCartasObtenidas());

        assertFalse(gestorPartida.juegoIniciado());
    }

    /** 
     * Test del metodo finalizarJuego de la clase GestorPartida no valido mano jugador1 no vacia
     */
    @Tag("Cobertura")
    @Test
    void testFinalizarJuegoNoValidoManoJugador1NoVacia(){

        gestorPartida.repartoCartasMesa(cartasMesa);
        gestorPartida.repartoCartasJugador(cartasJugador1, 1);
        gestorPartida.repartoCartasJugador(cartasJugador2, 2);

        baraja.getCartas().clear();
        jugador2.getMano().clear();

        assertThrows(IllegalStateException.class, () -> {
            gestorPartida.finalizarJuego();
        });


    }

    /** 
     * Test del metodo finalizarJuego de la clase GestorPartida no valido mano jugador2 no vacia
     */
    @Tag("Cobertura")
    @Test
    void testFinalizarJuegoNoValidoManoJugador2NoVacia(){

        gestorPartida.repartoCartasMesa(cartasMesa);
        gestorPartida.repartoCartasJugador(cartasJugador1, 1);
        gestorPartida.repartoCartasJugador(cartasJugador2, 2);

        baraja.getCartas().clear();
        jugador1.getMano().clear();

        assertThrows(IllegalStateException.class, () -> {
            gestorPartida.finalizarJuego();
        });


    }

    /** 
     * Test del metodo finalizarJuego de la clase GestorPartida no valido baraja no vacia
     */
    @Tag("Cobertura")
    @Test
    void testFinalizarJuegoNoValidoBarajaNoVacia(){

        gestorPartida.repartoCartasMesa(cartasMesa);
        gestorPartida.repartoCartasJugador(cartasJugador1, 1);
        gestorPartida.repartoCartasJugador(cartasJugador2, 2);

        jugador2.getMano().clear();
        jugador1.getMano().clear();

        assertThrows(IllegalStateException.class, () -> {
            gestorPartida.finalizarJuego();
        });


    }

    /** 
     * Test del metodo contarOros de la clase GestorPartida valido limite inferior
     */
    @Test
    void testContarOrosValidoLimiteInferior(){

        
        jugador1.captura(carta14, new ArrayList<>(Arrays.asList(carta15,carta16)));
        assertEquals(0,gestorPartida.contarOros(jugador1.getCartasObtenidas()));

    }


    /** 
     * Test del metodo contarOros de la clase GestorPartida valido limite superior
     */
    @Test
    void testContarOrosValidoLimiteSuperior(){

        jugador1.captura(carta7, new ArrayList<>(Arrays.asList(carta8)));
        jugador1.captura(carta6, new ArrayList<>(Arrays.asList(carta9)));
        jugador1.captura(carta5, new ArrayList<>(Arrays.asList(carta10)));

        

        jugador1.captura(carta4, new ArrayList<>(Arrays.asList(carta3, carta2, carta1, carta15)));
        
        assertEquals(10,gestorPartida.contarOros(jugador1.getCartasObtenidas()));
        assertEquals(0, jugador1.getPuntos()); //punto por tener el guindis

    }

    /** 
     * Test del metodo contarOros de la clase GestorPartida no valido lista cartas nula
     */
    @Test
    void testContarOrosNoValidoListaCartasNula(){

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.contarOros(null);
        });

    }

    /** 
     * Test del metodo contarSietes de la clase GestorPartida valido limite inferior
     */
    @Test
    void testContarSietesLimiteInferior(){

        jugador1.captura(carta5, new ArrayList<>(Arrays.asList(carta10)));
        assertEquals(0,gestorPartida.contarSietes(jugador1.getCartasObtenidas()));

    }

    /** 
     * Test del metodo contarSietes de la clase GestorPartida valido limite superior
     */
    @Test
    void testContarSietesLimiteSuperior(){

        jugador1.captura(carta7, new ArrayList<>(Arrays.asList(carta8)));

        jugador1.captura(carta17, new ArrayList<>(Arrays.asList(carta3, carta5)));
        jugador1.captura(carta27, new ArrayList<>(Arrays.asList(carta2, carta6)));
        jugador1.captura(carta37, new ArrayList<>(Arrays.asList(carta4, carta14)));

        assertEquals(4,gestorPartida.contarSietes(jugador1.getCartasObtenidas()));
    }

    /** 
     * Test del metodo contarSietes de la clase GestorPartida no valido lista cartas nula
     */
    @Test
    void testContarSietesNoValidoListaCartasNula(){

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.contarSietes(null);
        });

    }
    

    /** 
     * Test del metodo comprobarMesaFinal de la clase GestorPartida con mesa final valida de 10 puntos
     */
    @Test
    void testComprobarMesaFinalValido10(){

        List<Carta> mesaFinal = new ArrayList<>(Arrays.asList(carta10));

        assertTrue(gestorPartida.comprobarMesaFinal(mesaFinal));
    }

    /** 
     * Test del metodo comprobarMesaFinal de la clase GestorPartida con mesa final valida de 25 puntos
     */
    @Test
    void testComprobarMesaFinalValido25(){

        List<Carta> mesaFinal = new ArrayList<>(Arrays.asList(carta10, carta5,new Carta(20)));

        assertTrue(gestorPartida.comprobarMesaFinal(mesaFinal));
    }

    /** 
     * Test del metodo comprobarMesaFinal de la clase GestorPartida con mesa final valida de 40 puntos
     */
    @Test
    void testComprobarMesaFinalValido40(){

        List<Carta> mesaFinal = new ArrayList<>(Arrays.asList(carta10, new Carta(20), new Carta(30), new Carta(40)));
        
        assertTrue(gestorPartida.comprobarMesaFinal(mesaFinal));
    }

    /** 
     * Test del metodo comprobarMesaFinal de la clase GestorPartida con mesa final valida de 55 puntos
     */
    @Test
    void testComprobarMesaFinalValido55(){

        List<Carta> mesaFinal = new ArrayList<>(Arrays.asList(carta10, carta5, carta3, carta7, new Carta(20), new Carta(30), new Carta(40)));
        
        assertTrue(gestorPartida.comprobarMesaFinal(mesaFinal));

    }

    /** 
     * Test del metodo comprobarMesaFinal de la clase GestorPartida no valido null
     */
    @Test
    void testComprobarMesaFinalNoValidoNull(){

        assertThrows(IllegalArgumentException.class, () -> {
            gestorPartida.comprobarMesaFinal(null);
        });
    }

    /** 
     * Test del metodo comprobarMesaFinal de la clase GestorPartida no valido valor no posible
     */
    @Test
    void testComprobarMesaFinalNoValidoNoValorPosible(){

        List<Carta> mesaFinal = new ArrayList<>(Arrays.asList(carta5));

        assertThrows(IllegalStateException.class, () -> {
            gestorPartida.comprobarMesaFinal(mesaFinal);
        });
    }

    /** 
     * Test del metodo devolverUltimoJugadorQueHaCapturado de la clase GestorPartida valido
     */
    @Test
    void testDevolverUltimoJugadorQueHaCapturadoValido(){

        gestorPartida.repartoCartasMesa(cartasMesa);
        gestorPartida.repartoCartasJugador(cartasJugador1, 1);
        gestorPartida.repartoCartasJugador(cartasJugador2, 2);

        gestorPartida.jugarCarta(cartasJugador1.get(2));

        gestorPartida.capturarCartas(List.of(cartasMesa.get(0), cartasMesa.get(2), cartasMesa.get(3)));
        
        List<Carta> cartasMesa2=new ArrayList<>(Arrays.asList(new Carta(22),new Carta(25),carta37,carta27));
        
        gestorPartida.getMesa().setCartas(cartasMesa2);

        gestorPartida.jugarCarta(cartasJugador2.get(0));

        gestorPartida.capturarCartas(List.of(gestorPartida.getMesa().getCartas().get(0), gestorPartida.getMesa().getCartas().get(1)));

        assertEquals(jugador2.getNombre(),gestorPartida.devolverUltimoJugador().getNombre());


    }

}
