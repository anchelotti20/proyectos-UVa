package uva.tds;

/**
 * Clase de test para la clase Ronda
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RondaTest {
    
    /**
     * Atributos de la clase RondaTest
     * utilizados en los distintos test e inicializados en la fixture setUp()
     */
    private List<Carta> mesaInicial;
    private List<Carta> manoJugador1;
    private List<Carta> manoJugador2;
    private List<Jugada> jugadas;
    private List<Carta> vacia;
    private Jugada jugada1;
    private Jugada jugada2;
    private Jugada jugada3;
    private Jugada jugada4;
    private Jugada jugada5;
    private Jugada jugada6;
    private Carta carta3;
    private Carta carta5;
    private Carta carta7;
    private Carta carta9;
    private Carta carta11;
    private Carta carta12;
    private Carta carta14;
    private Carta carta29;
    private Carta carta38;
    private Carta carta39;

    /**
     * Metodo setUp() que inicializa los atributos
     * utilizados en los distintos test
     */
    @BeforeEach
    void setUp() {
        
        mesaInicial = new ArrayList<>();
        manoJugador1 = new ArrayList<>();
        manoJugador2 = new ArrayList<>();
        jugadas = new ArrayList<>();
        vacia = new ArrayList<>();

        carta3 = new Carta(3);
        carta5 = new Carta(5);
        carta7 = new Carta(7);
        carta9 = new Carta(9);
        carta11 = new Carta(11);
        carta12 = new Carta(12);
        carta14 = new Carta(14);
        carta29 = new Carta(29);
        carta38 = new Carta(38);
        carta39 = new Carta(39);

        mesaInicial.add(carta5);
        mesaInicial.add(carta9);
        mesaInicial.add(carta38);
        mesaInicial.add(carta39);

        manoJugador1.add(carta11);
        manoJugador1.add(carta14);
        manoJugador1.add(carta29);

        manoJugador2.add(carta7);
        manoJugador2.add(carta12);
        manoJugador2.add(carta3);

        jugada1 = new Jugada(carta11, List.of(carta5, carta9));
        jugada2 = new Jugada(carta7, List.of(carta38));
        jugada3 = new Jugada(carta29, vacia);
        jugada4 = new Jugada(carta12, vacia);
        jugada5 = new Jugada(carta14, List.of(carta29, carta12));
        jugada6 = new Jugada(carta3, vacia);

        jugadas.add(jugada1);
        jugadas.add(jugada2);
        jugadas.add(jugada3);
        jugadas.add(jugada4);
        jugadas.add(jugada5);
        jugadas.add(jugada6);

    }

    /** 
     * Test del constructor de la clase Ronda valido limite inferior
     */
    @Test
    void testConstructorRondaValidoLimiteInferior(){

        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);

        assertEquals(1, ronda.getNumeroRonda());
        assertEquals(6, ronda.getJugadas().size());
        assertArrayEquals(mesaInicial.toArray(), ronda.getMesaInicial().toArray());
        assertArrayEquals(manoJugador1.toArray(), ronda.getManoJugador1().toArray());
        assertArrayEquals(manoJugador2.toArray(), ronda.getManoJugador2().toArray());
        assertArrayEquals(jugadas.toArray(), ronda.getJugadas().toArray());


    }

    /** 
     * Test del constructor de la clase Ronda valido limite superior
     */
    @Test
    void testConstructorRondaValidoLimiteSuperior(){

        Ronda ronda = new Ronda(6, mesaInicial, manoJugador1, manoJugador2, jugadas);

        assertEquals(6, ronda.getNumeroRonda());
        assertArrayEquals(mesaInicial.toArray(), ronda.getMesaInicial().toArray());
        assertArrayEquals(manoJugador1.toArray(), ronda.getManoJugador1().toArray());
        assertArrayEquals(manoJugador2.toArray(), ronda.getManoJugador2().toArray());
        assertArrayEquals(jugadas.toArray(), ronda.getJugadas().toArray());

    }

    /** 
     * Test del constructor de la clase Ronda no valido numeroRonda menor al limite inferior
     */
    @Test
    void testConstructorRondaInvalidoNumeroRondaMenorLimiteInferior(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Ronda(0, mesaInicial, manoJugador1, manoJugador2, jugadas);
        });
    }
    
    /** 
     * Test del constructor de la clase Ronda no valido numeroRonda mayor al limite superior
     */
    @Test
    void testConstructorRondaInvalidoNumeroRondaMayorLimiteSuperior(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Ronda(7, mesaInicial, manoJugador1, manoJugador2, jugadas);
        });
    }

    /** 
     * Test del constructor de la clase Ronda no valido mesaInicial null
     */
    @Test
    void testConstructorRondaInvalidoMesaInicialNull(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Ronda(1, null, manoJugador1, manoJugador2, jugadas);
        });
    }

    /** 
     * Test del constructor de la clase Ronda no valido manoJugador1 null
     */
    @Test
    void testConstructorRondaInvalidoManoJugador1Null(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Ronda(1, mesaInicial, null, manoJugador2, jugadas);
        });
    }

    /** 
     * Test del constructor de la clase Ronda no valido manoJugador2 null
     */
    @Test
    void testConstructorRondaInvalidoManoJugador2Null(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Ronda(1, mesaInicial, manoJugador1, null, jugadas);
        });
    }

    /** 
     * Test del constructor de la clase Ronda no valido jugadas null
     */
    @Test
    void testConstructorRondaInvalidoJugadasNull(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Ronda(1, mesaInicial, manoJugador1, manoJugador2, null);
        });
    }
    
    /** 
     * Test del constructor de la clase Ronda no valido jugadas incompletas
     */
    @Test
    void testConstructorRondaNoValidoJugadasIncompletas() {
        jugadas.remove(5);
        assertThrows(IllegalArgumentException.class, () -> {
            new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        });
    }

    @Tag("Cobertura")
    @Test
    void testSetMesaInicialValido() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        List<Carta> nuevaMesa = new ArrayList<>();
        nuevaMesa.add(carta3);
        nuevaMesa.add(carta5);
        nuevaMesa.add(carta7);
        nuevaMesa.add(carta11);
        ronda.setMesaInicial(nuevaMesa);
        assertArrayEquals(nuevaMesa.toArray(), ronda.getMesaInicial().toArray());
    }

    @Tag("Cobertura")
    @Test
    void testSetMesaInicialInvalidoNull() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        assertThrows(IllegalArgumentException.class, () -> {
            ronda.setMesaInicial(null);
        });
    }

    @Tag("Cobertura")
    @Test
    void testSetManoJugador1Valido() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        List<Carta> nuevaMano = new ArrayList<>();
        nuevaMano.add(carta7);
        nuevaMano.add(carta9);
        nuevaMano.add(carta12);
        ronda.setManoJugador1(nuevaMano);
        assertArrayEquals(nuevaMano.toArray(), ronda.getManoJugador1().toArray());
    }

    @Tag("Cobertura")
    @Test
    void testSetManoJugador1InvalidoNull() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        assertThrows(IllegalArgumentException.class, () -> {
            ronda.setManoJugador1(null);
        });
    }

    @Tag("Cobertura")
    @Test
    void testSetManoJugador2Valido() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        List<Carta> nuevaMano = new ArrayList<>();
        nuevaMano.add(carta11);
        nuevaMano.add(carta12);
        nuevaMano.add(carta3);
        ronda.setManoJugador2(nuevaMano);
        assertArrayEquals(nuevaMano.toArray(), ronda.getManoJugador2().toArray());
    }

    @Tag("Cobertura")
    @Test
    void testSetManoJugador2InvalidoNull() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        assertThrows(IllegalArgumentException.class, () -> {
            ronda.setManoJugador2(null);
        });
    }

    @Tag("Cobertura")
    @Test
    void testSetJugadasValido() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        List<Jugada> nuevasJugadas = new ArrayList<>();
        nuevasJugadas.add(jugada6);
        nuevasJugadas.add(jugada5);
        nuevasJugadas.add(jugada4);
        nuevasJugadas.add(jugada3);
        nuevasJugadas.add(jugada2);
        nuevasJugadas.add(jugada1);
        ronda.setJugadas(nuevasJugadas);
        assertArrayEquals(nuevasJugadas.toArray(), ronda.getJugadas().toArray());
    }

    @Tag("Cobertura")
    @Test
    void testSetJugadasInvalidoNull() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        assertThrows(IllegalArgumentException.class, () -> {
            ronda.setJugadas(null);
        });
    }

    /**
     * Test del metodo setJugadas de la clase Ronda no valido con jugadas incompletas
     */
    @Tag("Cobertura")
    @Test
    void testSetJugadasInvalidoIncompleto() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        List<Jugada> nuevasJugadas = new ArrayList<>();
        nuevasJugadas.add(jugada1);
        nuevasJugadas.add(jugada2);
        nuevasJugadas.add(jugada3);
        assertThrows(IllegalArgumentException.class, () -> {
            ronda.setJugadas(nuevasJugadas);
        });
    }

    /**
     * Test del metodo equals de la clase Ronda con el mismo objeto
     */
    @Tag("Cobertura")
    @Test
    void testEqualsMismoObjeto() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        assertEquals(ronda, ronda);
    }

    /**
     * Test del metodo equals de la clase Ronda con objeto null
     */
    @Tag("Cobertura")
    @Test
    void testEqualsObjetoNull() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        assertEquals(false, ronda.equals(null));
    }

    /**
     * Test del metodo equals de la clase Ronda con distinta clase
     */
    @Tag("Cobertura")
    @Test
    void testEqualsDistintaClase() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        String otroObjeto = "No es una Ronda";
        assertEquals(false, ronda.equals(otroObjeto));
    }

    /**
     * Test del metodo equals de la clase Ronda con mismo contenido
     */
    @Tag("Cobertura")
    @Test
    void testEqualsMismoContenido() {
        Ronda ronda1 = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        Ronda ronda2 = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        assertEquals(ronda1, ronda2);
    }

    /**
     * Test del metodo equals de la clase Ronda con distinto contenido
     */
    @Tag("Cobertura")
    @Test
    void testEqualsDistintoContenido() {
        Ronda ronda1 = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        Ronda ronda2 = new Ronda(2, mesaInicial, manoJugador1, manoJugador2, jugadas);
        assertEquals(false, ronda1.equals(ronda2));
    }

    /**
     * Test del metodo equals de la clase Ronda con distinta mesa inicial
     */
    @Tag("Cobertura")
    @Test
    void testEqualsDistintaMEsaInicial() {
        Ronda ronda1 = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        List<Carta> otraMesa = new ArrayList<>();
        otraMesa.add(carta3);
        otraMesa.add(carta5);
        otraMesa.add(carta7);
        otraMesa.add(carta11);
        Ronda ronda2 = new Ronda(1, otraMesa, manoJugador1, manoJugador2, jugadas);
        assertEquals(false, ronda1.equals(ronda2));
    }

    /**
     * Test del metodo equals de la clase Ronda con distinta mano jugador 1
     */
    @Tag("Cobertura")
    @Test
    void testEqualsDistintaManoJugador1() {
        Ronda ronda1 = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        List<Carta> otraMano1 = new ArrayList<>();
        otraMano1.add(carta7);
        otraMano1.add(carta9);
        otraMano1.add(carta12);
        Ronda ronda2 = new Ronda(1, mesaInicial, otraMano1, manoJugador2, jugadas);
        assertEquals(false, ronda1.equals(ronda2));
    }

    /**
     * Test del metodo equals de la clase Ronda con distinta mano jugador 2
     */
    @Tag("Cobertura")
    @Test
    void testEqualsDistintaManoJugador2() {
        Ronda ronda1 = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        List<Carta> otraMano2 = new ArrayList<>();
        otraMano2.add(carta11);
        otraMano2.add(carta12);
        otraMano2.add(carta3);
        Ronda ronda2 = new Ronda(1, mesaInicial, manoJugador1, otraMano2, jugadas);
        assertEquals(false, ronda1.equals(ronda2));
    }

    /**
     * Test del metodo hashCode de la clase Ronda consistente
     */
    @Tag("Cobertura")
    @Test
    void testHashCodeConsistente() {
        Ronda ronda = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        int hash1 = ronda.hashCode();
        int hash2 = ronda.hashCode();
        assertEquals(hash1, hash2);
    }

    /**
     * Test del metodo hashCode de la clase Ronda igual para objetos iguales
     */
    @Tag("Cobertura")
    @Test
    void testHashCodeIgualParaObjetosIguales() {
        Ronda ronda1 = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        Ronda ronda2 = new Ronda(1, mesaInicial, manoJugador1, manoJugador2, jugadas);
        assertEquals(ronda1.hashCode(), ronda2.hashCode());
    }
}