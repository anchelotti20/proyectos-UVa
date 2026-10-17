package uva.tds;

/**
 * Clase de test para la clase Jugador
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

 class JugadorTest {

    /** 
     * Atributos de la clase JugadorTest
     * Usados en la Fixture para pruebas comunes en test
     */
    private Jugador jugador;
    private Carta carta1;
    private Carta carta2;
    private Carta carta3;
    private Carta carta6;
    private Carta carta7;
    private List<Carta> mano;
    private List<Carta> cartasMesa;

    /** 
     * Fixture para inicializar objetos comunes a varios tests
     */
    @BeforeEach
     void setUp(){
        jugador = new Jugador("Fernando");
        carta1 = new Carta(1);
        carta2 = new Carta(2);
        carta3 = new Carta(3);
        carta6 = new Carta(6);
        carta7 = new Carta(7);
        mano = new ArrayList<>();
        mano.add(carta1);
        mano.add(carta2);
        mano.add(carta3);
        cartasMesa = new ArrayList<>();
        cartasMesa.add(carta6);
        cartasMesa.add(carta7);
    }

    /** 
     * Test del constructor de la clase Jugador valido
     */
    @Test
    void testJugadorValido(){
        assertEquals("Fernando",jugador.getNombre());

    }

    /** 
     * Test del constructor de la clase Jugador no valido nombre nulo
     */
    @Test 
    void testJugadorNoValidoNombreNulo(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Jugador(null);
        });
    }

    /** 
     * Test del constructor de la clase Jugador no valido nombre vacio
     */
    @Test 
    void testJugadorNoValidoNombreVacio(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Jugador("");
        });
    }

    /** 
     * Test del metodo getNombre de la clase Jugador
     */
    @Test
    void testGetNombre(){

        assertEquals("Fernando", jugador.getNombre());

    }

    /** 
     * Test del metodo getMano de la clase Jugador
     */
    @Test
    void testGetMano(){

        jugador.setMano(mano);

        assertEquals(3, jugador.getMano().size());

    }

    /** 
     * Test del metodo getCartasObtenidas de la clase Jugador
     */
    @Test
    void testGetCartasObtenidas(){
        jugador.setMano(mano);
        jugador.juegaCartaJugador(carta2);
        jugador.captura(carta2, cartasMesa);

        assertEquals(3, jugador.getCartasObtenidas().size());

    }

    /** 
     * Test del metodo getPuntos de la clase Jugador
     */
    @Test
    void testGetPuntos(){

        assertEquals(0, jugador.getPuntos());

    }

    /** 
     * Test del metodo getNumeroEscobas de la clase Jugador
     */
    @Test
    void testGetNumeroEscobas(){

        assertEquals(0, jugador.getNumeroEscobas());

    }

    /** 
     * Test del metodo setMano de la clase Jugador no valido menos de 3 cartas
     */
    @Test
    void testSetManoNoValidoMenos3(){

        List<Carta> manoInvalida = new ArrayList<>();
        manoInvalida.add(carta1);
        manoInvalida.add(carta2);

        assertThrows(IllegalArgumentException.class, () -> {
            jugador.setMano(manoInvalida);
        });

    }

    /** 
     * Test del metodo setMano de la clase Jugador no valido mas de 3 cartas
     */
    @Test
    void testSetManoNoValidoMas3(){

        List<Carta> manoInvalida = new ArrayList<>();
        manoInvalida.add(carta1);
        manoInvalida.add(carta2);
        manoInvalida.add(carta3);
        manoInvalida.add(new Carta(4));

        assertThrows(IllegalArgumentException.class, () -> {
            jugador.setMano(manoInvalida);
        });

    }

    /** 
     * Test del metodo juegaCartaJugador de la clase Jugador
     */
    @Test
    void testJuegaCartaValido(){

        jugador.setMano(mano);
        jugador.juegaCartaJugador(carta1);
        assertEquals(2, jugador.getMano().size());

    }
    
    /** 
     * Test del metodo juegaCartaJugador de la clase Jugador no valido carta nula
     */
    @Test
    void testJuegaCartaNoValidoCartaNula(){

        assertThrows(IllegalArgumentException.class, () -> {
            jugador.juegaCartaJugador(null);
        });
        

    }

    /** 
     * Test del metodo juegaCartaJugador de la clase Jugador no valido carta no en mano
     */
    @Test
    void testJuegaCartaNoValidoCartaNoEnMano(){
        
        jugador.setMano(mano);
        Carta carta = new Carta(4);

        assertThrows(IllegalArgumentException.class, () -> {
            jugador.juegaCartaJugador(carta);
        });

    }

    /** 
     * Test del metodo captura de la clase Jugador
     */
    @Test
    void testCapturaValido(){
        jugador.setMano(mano);
        jugador.juegaCartaJugador(carta2);
        jugador.captura(carta2, cartasMesa);
        assertEquals(3, jugador.getCartasObtenidas().size());
        assertEquals(2, jugador.getMano().size());
    }

    /** 
     * Test del metodo captura de la clase Jugador no valido carta nula
     */
    @Test
    void testCapturaNoValidoCartaNula(){
        

        assertThrows(IllegalArgumentException.class, () -> {
            jugador.captura(null, cartasMesa);
        });

    }
    
    /** 
     * Test del metodo captura de la clase Jugador no valido cartas mesa nulas
     */
    @Test
    void testCapturaNoValidoCartasNulas(){
        

        assertThrows(IllegalArgumentException.class, () -> {
            jugador.captura(carta1, null);
        });

    }

    /** 
     * Test del metodo captura de la clase Jugador no valido no suma 15
     */
    @Test
    void testCapturaNoValidoNoSuman15(){

        jugador.setMano(mano);

        assertThrows(IllegalArgumentException.class, () -> {
            jugador.captura(carta1, cartasMesa);
        });
    }

    /** 
     * Test del metodo sumaEscoba de la clase Jugador
     */
    @Test
    void testSumaEscoba(){

        jugador.sumaEscoba();
        assertEquals(1, jugador.getNumeroEscobas());

    }

    /** 
     * Test del metodo sumarPuntos de la clase Jugador
     */
    @Test
    void testSumarPuntosValido(){

        jugador.sumarPuntos(1);
        assertEquals(1, jugador.getPuntos());

    }

    /** 
     * Test del metodo sumarPuntos de la clase Jugador no valido limite inferior
     */
    @Test
    void testSumarPuntosNoValidoLimiteInferior(){

        assertThrows(IllegalArgumentException.class,()->{
            jugador.sumarPuntos(-1);
        });

    }

    /**
     * Test del metodo getGuindis de la clase Jugador cuando tiene guindis
     */
    @Test
    void testGetGuindisValidoLoTiene(){


        jugador.setMano(mano);

        jugador.juegaCartaJugador(carta2);

        jugador.captura(carta2, cartasMesa); 

        assertEquals(1, jugador.getGuindis());

    }

    /**
     * Test del metodo getGuindis de la clase Jugador cuando no tiene guindis
     */
    @Test
    void testGetGuindisValidoNoLoTiene(){

        Carta carta9 = new Carta(9);
        jugador.setMano(Arrays.asList(carta6, carta7, carta9));

        jugador.juegaCartaJugador(carta9);

        jugador.captura(carta9, mano);

        assertEquals(0, jugador.getGuindis());
    }

    /**
     * Test del metodo equals de la clase Jugador cuando son iguales
     */
    @Tag("Cobertura")
    @Test
    void testJugadoresIguales(){

        Jugador jugador2=new Jugador("Fernando");
        assertEquals(jugador,jugador2);
    }

    /**
     * Test del metodo hashCode de la clase Jugador
     */
    @Tag("Cobertura")
    @Test
    void testJugadorHash(){

        Jugador jugador2=new Jugador("Fernando");
        assertEquals(jugador.hashCode(),jugador2.hashCode());
    
    }

    /**
     * Test del metodo equals de la clase Jugador cuando no son iguales
     */
    @Tag("Cobertura")
    @Test
    void testJugadorEqualsNulo(){
        Jugador jugador2=null;
        assertNotEquals(jugador,jugador2);
    }

    /**
     * Test del metodo equals de la clase Jugador cuando son distintos objetos
     */
    @Tag("Cobertura")
    @Test
    void testJugadorEqualsOtroObjeto(){
        String jugador2="cadena";
        assertNotEquals(jugador,jugador2);
    }

}
