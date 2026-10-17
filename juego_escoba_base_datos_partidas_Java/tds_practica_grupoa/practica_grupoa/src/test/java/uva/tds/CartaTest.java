package uva.tds;

/**
 * Clase de test para la clase Carta
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CartaTest {

    private Carta carta1;

    @BeforeEach
    void setUp(){

        carta1=new Carta(1);

    }

    /**
     * Test del constructor de la clase Carta valido limite inferior
     */
    @Test
    void testConstructorValidoLimiteInferior(){
        Carta carta=new Carta(1);
        assertEquals(1,carta.getValor());

    }

    /**
     * Test del constructor de la clase Carta valido limite superior
     */
    @Test
    void testConstructorValidoLimiteSuperior(){
        Carta carta=new Carta(40);
        assertEquals(10,carta.getValor());

    }

    /**
     * Test del constructor de la clase Carta no valido debajo del limite inferior
     */
    @Test
    void testConstructorNoValidoLimiteInferior(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Carta(0);
        });
    }

    /**
     * Test del constructor de la clase Carta no valido encima del limite superior
     */
    @Test
    void testConstructorNoValidoLimiteSuperior(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Carta(41);
        });
    }
    
    /**
     * Test del metodo equals de la clase Carta con cartas distintas
     */
    @Tag("Cobertura")
    @Test
    void testCartasDistintas(){
        Carta carta2= new Carta(33);
        assertNotEquals(true,carta1.equals(carta2));
    }
    
    /**
     * Test del metodo hashCode de la clase Carta
     */
    @Tag("Cobertura")
    @Test
    void testCartaHashCode(){
        Carta carta2=new Carta(1);
        assertEquals(carta1.hashCode(),carta2.hashCode());
    }

    /**
     * Test del metodo equals de la clase Carta con cartas iguales
     */
    @Tag("Cobertura")
    @Test
    void  testCartasIguales(){
        Carta carta2=new Carta(1);

        assertEquals(true,carta1.equals(carta2));
    }
    
    /**
     * Test del metodo equals de la clase Carta con objeto nulo
     */
    @Tag("Cobertura")
    @Test
    void  testCartasObjectNulo(){
        Object object=null;

        assertNotEquals(carta1,object);
    }
    

    /**
     * Test del metodo equals de la clase Carta con clase distinta
     */
    @Tag("Cobertura")
    @Test
    void testEqualsClaseDistinta(){
        String cadena="Carta";

        assertNotEquals(true,carta1.equals(cadena));

    }
    
    
}