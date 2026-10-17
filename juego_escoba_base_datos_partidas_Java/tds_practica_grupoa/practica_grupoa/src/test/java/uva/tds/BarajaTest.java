package uva.tds;

/**
 * Clase de test para la clase Baraja
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;



class BarajaTest {

    /**
     * Atributos de la clase BarajaTest
     * Usados en la Fixture para pruebas comunes en test
     */
    private static List<Carta> cartas;
    private static Baraja baraja;
    private static Carta primeraBaraja;
    private static Carta ultimaBaraja;

    /**
     * Fixture para inicializar objetos comunes a varios tests
     */
    @BeforeAll
    static void setUp() {


        baraja = new Baraja();
        cartas = new ArrayList<>();
        primeraBaraja = new Carta(1);
        ultimaBaraja = new Carta(40);

        for (int i = 1; i <= 40; i++) {
            
            cartas.add(new Carta(i));
        
        }
    }

    /**
     * Test del constructor de la clase Baraja
     */
    @Test
    void testBarajaConstructorValido() {

        Baraja baraja2 = new Baraja();

        assertEquals(40, baraja2.getCartas().size());
        assertArrayEquals(cartas.toArray(), baraja2.getCartas().toArray());

    }

    /**
     * Test del metodo getCartas de la clase Baraja
     */
    @Test
    void testGetCartas() {

        Baraja barajaGet = new Baraja();

        assertNotNull(barajaGet.getCartas());
        assertArrayEquals(cartas.toArray(), barajaGet.getCartas().toArray());

    }

    /**
    * Test del metodo robarCarta de la clase Baraja limite inferior
    */
    @Test
    void testRobarCartaValidoLimiteInferior(){

        Carta cartaEsperada = cartas.get(0);
        Carta cartaRobada = baraja.robarCarta(primeraBaraja);

        assertEquals(cartaEsperada, cartaRobada);
        assertNotNull(cartaRobada);

    }

    /**
    * Test del metodo robarCarta de la clase Baraja limite superior
    */
    @Test
    void testRobarCartaValidoLimiteSuperior(){

        Carta cartaEsperada = cartas.get(39);
        Carta cartaRobada = baraja.robarCarta(ultimaBaraja);

        assertEquals(cartaEsperada, cartaRobada);
        assertNotNull(cartaRobada);

    }

    /**
     * Test del metodo robarCarta de la clase Baraja no valido carta no existente
     */
    @Test 
    void testRobarCartaNoValidoCartaNoExistente(){

        Baraja barajaRoba = new Baraja();

        Carta cartaRobada = new Carta(20);

        barajaRoba.robarCarta(cartaRobada);

        assertThrows(IllegalArgumentException.class, () -> {

            barajaRoba.robarCarta(cartaRobada);

        });
    }

    /**
    * Test del metodo robarCarta de la clase Baraja no valido baraja vacia
    */
    @Test
    void testRobarCartaNoValidoBarajaVacia(){

        Baraja barajaVacia = new Baraja();

        barajaVacia.getCartas().clear();

        assertThrows(IllegalStateException.class, () -> {
            
            barajaVacia.robarCarta(primeraBaraja);
        
        });
    }


}
