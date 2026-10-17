package uva.tds;

/**
 * Clase de test para la clase Jugada
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class JugadaTest {
    
    /**
     * Atributos de la clase JugadaTest
     * utilizados en los distintos test e inicializados en la fixture setUp()
     */
    private Carta cartaJugada;
    private List<Carta> cartasCapturadas;
    private Carta carta5;
    private Carta carta9;
    private Jugada jugada;

    /**
     * Metodo setUp() que inicializa los atributos
     * utilizados en los distintos test
     */
    @BeforeEach
    void setUp(){

        cartaJugada = new Carta(11);
        carta5 = new Carta(5);
        carta9 = new Carta(9);
        cartasCapturadas = new ArrayList<>();
        cartasCapturadas.add(carta5);
        cartasCapturadas.add(carta9);

        jugada = new Jugada(cartaJugada, cartasCapturadas);


    }

    /**
     * Test del constructor de la clase Jugada valido
     */
    @Test
    void testConstructorJugadaValidoCapturaNoVacia(){

        jugada = new Jugada(cartaJugada, cartasCapturadas);

        assertEquals(cartaJugada, jugada.getCartaJugada());
        assertArrayEquals(cartasCapturadas.toArray(), jugada.getCartasCapturadas().toArray());

    
    }

    /**
     * Test del constructor de la clase Jugada valido con captura vacia
     */
    @Test
    void testConstructorJugadaValidoCapturaVacia(){

        List<Carta> cartasCapturadasVacia = new ArrayList<>();

        jugada = new Jugada(cartaJugada, cartasCapturadasVacia);

        assertEquals(cartaJugada, jugada.getCartaJugada());
        assertTrue(jugada.getCartasCapturadas().isEmpty());

    
    }
    
    /**
     * Test del constructor de la clase Jugada no valido con carta jugada null
     */
    @Test
    void testConstructorJugadaCartaJugadaNull(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Jugada(null, cartasCapturadas);
        });
    }

    /**
     * Test del constructor de la clase Jugada no valido con cartas capturadas null
     */
    @Test
    void testConstructorJugadaCartasCapturadasNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Jugada(cartaJugada, null);
        });
    }

    /**
     * Test del constructor de la clase Jugada no valido con cartas capturadas que no suman 15
     */
    @Test
    void testConstructorJugadaCapturaNoSuma15(){

        List<Carta> cartasCapturadasNo15 = new ArrayList<>();
        cartasCapturadasNo15.add(new Carta(2));
        cartasCapturadasNo15.add(new Carta(3));

        assertThrows(IllegalArgumentException.class, () -> {
            new Jugada(cartaJugada, cartasCapturadasNo15);
        });


    }

    /**
     * Test del metodo setCartasCapturadas de la clase Jugada valido
     */
    @Tag("Cobertura")
    @Test
    void testSetCartasCapturadasValido(){

        jugada.setCartasCapturadas(cartasCapturadas);

        assertArrayEquals(cartasCapturadas.toArray(), jugada.getCartasCapturadas().toArray());

    }

    /**
     * Test del metodo setCartasCapturadas de la clase Jugada no valido con lista null
     */
    @Tag("Cobertura")
    @Test
    void testSetCartasCapturadasNoValidoNull(){

        assertThrows(IllegalArgumentException.class, () -> {
            jugada.setCartasCapturadas(null);
        });

    }

    /**
     * Test del metodo setCartasCapturadas de la clase Jugada no valido cuando no suma 15
     */
    @Tag("Cobertura")
    @Test
    void testSetCartasCapturadasNoValidoNoSuma15(){

        List<Carta> cartasCapturadasNo15 = new ArrayList<>();
        cartasCapturadasNo15.add(new Carta(2));
        cartasCapturadasNo15.add(new Carta(3));

        assertThrows(IllegalArgumentException.class, () -> {
            jugada.setCartasCapturadas(cartasCapturadasNo15);
        });

    }

    /**
     * Test del metodo equals de la clase Jugada con jugadas iguales
     */
    @Tag("Cobertura")
    @Test
    void testEquals(){

        Jugada jugada2 = new Jugada(cartaJugada, cartasCapturadas);

        assertEquals(jugada, jugada2);

    }

    /**
     * Test del metodo equals de la clase Jugada con cartas capturadas en orden diferente
     */
    @Tag("Cobertura")
    @Test
    void testEqualsCartasCapturadasOrdenDiferente(){

        List<Carta> cartasCapturadasOrdenDiferente = new ArrayList<>();
        cartasCapturadasOrdenDiferente.add(carta9);
        cartasCapturadasOrdenDiferente.add(carta5);

        Jugada jugada2 = new Jugada(cartaJugada, cartasCapturadasOrdenDiferente);

        assertNotEquals(jugada, jugada2);

    }

    /**
     * Test del metodo equals de la clase Jugada con jugadas distintas
     */
    @Tag("Cobertura")
    @Test
    void testNotEquals(){

        Carta cartaDiferente = new Carta(7);
        List<Carta> cartasCapturadasDiferente = new ArrayList<>();
        cartasCapturadasDiferente.add(new Carta(8));

        Jugada jugada2 = new Jugada(cartaDiferente, cartasCapturadasDiferente);

        assertNotEquals(jugada, jugada2);

    }

    /**
     * Test del metodo equals de la clase Jugada con otro tipo de objeto
     */
    @Tag("Cobertura")
    @Test
    void testNotEqualsOtroObjeto(){

        String otroObjeto = "No soy una jugada";

        assertNotEquals(jugada, otroObjeto);

    }

    /**
     * Test del metodo equals de la clase Jugada con objeto null
     */
    @Tag("Cobertura")
    @Test
    void testNotEqualsNull(){

        assertFalse(jugada.equals(null));

    }

    /**
     * Test del metodo equals de la clase Jugada con el mismo objeto
     */
    @Tag("Cobertura")
    @Test
    void testEqualsMismoObjeto(){

        assertEquals(jugada, jugada);

    }



    /**
     * Test del metodo hashCode de la clase Jugada
     */
    @Tag("Cobertura")
    @Test
    void testHashCode(){

        Jugada jugada2 = new Jugada(cartaJugada, cartasCapturadas);

        assertEquals(jugada.hashCode(), jugada2.hashCode());

    }


}
