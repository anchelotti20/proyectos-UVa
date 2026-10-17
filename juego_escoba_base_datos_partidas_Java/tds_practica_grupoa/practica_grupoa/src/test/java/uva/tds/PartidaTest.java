package uva.tds;

/**
 * Clase de test para la clase Partida
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PartidaTest {
    
    /**
     * Atributos de la clase PartidaTest
     * utilizados en los distintos test e inicializados en la fixture setUp()
     */
    private List<Ronda> rondas;
    private Ronda ronda1;
    private Ronda ronda2;
    private Ronda ronda3;
    private Ronda ronda4;
    private Ronda ronda5;
    private Ronda ronda6;
    private String nombreJugador1;
    private String nombreJugador2;
    private List<Carta> vacio;
    private List<Integer> resumenJugador1;
    private List<Integer> resumenJugador2;
    private List<Carta> cartas;
    private List<Carta> mesaInicial1;
    private List<Carta> mesaInicial2;
    private List<Carta> mesaInicial3;
    private List<Carta> mesaInicial4;
    private List<Carta> mesaInicial5;
    private List<Carta> mesaInicial6;
    private List<Carta> manoJugador1Ronda1;
    private List<Carta> manoJugador2Ronda1;
    private List<Carta> manoJugador1Ronda2;
    private List<Carta> manoJugador2Ronda2;
    private List<Carta> manoJugador1Ronda3;
    private List<Carta> manoJugador2Ronda3;
    private List<Carta> manoJugador1Ronda4;
    private List<Carta> manoJugador2Ronda4;
    private List<Carta> manoJugador1Ronda5;
    private List<Carta> manoJugador2Ronda5;
    private List<Carta> manoJugador1Ronda6;
    private List<Carta> manoJugador2Ronda6;
    private List<Jugada> jugadasRonda1;
    private List<Jugada> jugadasRonda2;
    private List<Jugada> jugadasRonda3;
    private List<Jugada> jugadasRonda4;
    private List<Jugada> jugadasRonda5;
    private List<Jugada> jugadasRonda6;
    private Partida partida;
    
    /**
     * Metodo setUp() que inicializa los atributos
     * utilizados en los distintos test
     */
    @BeforeEach
    void setUp() {
        
        nombreJugador1 = "Ana";
        nombreJugador2 = "Luis";

        rondas = new ArrayList<>();
        cartas = new ArrayList<>();
        vacio = new ArrayList<>();
        resumenJugador1 = new ArrayList<>();
        resumenJugador2 = new ArrayList<>();
        mesaInicial1 = new ArrayList<>();
        mesaInicial2 = new ArrayList<>();
        mesaInicial3 = new ArrayList<>();
        mesaInicial4 = new ArrayList<>();
        mesaInicial5 = new ArrayList<>();
        mesaInicial6 = new ArrayList<>();
        manoJugador1Ronda1 = new ArrayList<>();
        manoJugador2Ronda1 = new ArrayList<>();
        manoJugador1Ronda2 = new ArrayList<>();
        manoJugador2Ronda2 = new ArrayList<>();
        manoJugador1Ronda3 = new ArrayList<>();
        manoJugador2Ronda3 = new ArrayList<>();
        manoJugador1Ronda4 = new ArrayList<>();
        manoJugador2Ronda4 = new ArrayList<>();
        manoJugador1Ronda5 = new ArrayList<>();
        manoJugador2Ronda5 = new ArrayList<>();
        manoJugador1Ronda6 = new ArrayList<>();
        manoJugador2Ronda6 = new ArrayList<>();
        jugadasRonda1 = new ArrayList<>();
        jugadasRonda2 = new ArrayList<>();
        jugadasRonda3 = new ArrayList<>();
        jugadasRonda4 = new ArrayList<>();
        jugadasRonda5 = new ArrayList<>();
        jugadasRonda6 = new ArrayList<>();

        for(int i = 1; i <= 40; i++) {
            cartas.add(new Carta(i));
        }

        mesaInicial1.add(cartas.get(4)); //5 de oros
        mesaInicial1.add(cartas.get(8)); //9 de oros
        mesaInicial1.add(cartas.get(37)); //sota de bastos
        mesaInicial1.add(cartas.get(38)); //caballo de bastos

        manoJugador1Ronda1.add(cartas.get(10)); //as de copas
        manoJugador1Ronda1.add(cartas.get(13)); //4 de copas
        manoJugador1Ronda1.add(cartas.get(28)); //caballo de espadas
        manoJugador2Ronda1.add(cartas.get(6)); //7 de oros
        manoJugador2Ronda1.add(cartas.get(11)); //2 de copas
        manoJugador2Ronda1.add(cartas.get(2)); //3 de oros

        mesaInicial2.add(cartas.get(38)); //caballo de bastos
        mesaInicial2.add(cartas.get(2)); //3 de oros

        manoJugador1Ronda2.add(cartas.get(21)); //2 de espadas
        manoJugador1Ronda2.add(cartas.get(24)); //5 de espadas
        manoJugador1Ronda2.add(cartas.get(27)); //8 de espadas
        manoJugador2Ronda2.add(cartas.get(23)); //4 de espadas
        manoJugador2Ronda2.add(cartas.get(5)); //6 de oros
        manoJugador2Ronda2.add(cartas.get(35)); //6 de bastos

        mesaInicial3.add(cartas.get(24)); //5 de espadas
        mesaInicial3.add(cartas.get(21)); //2 de espadas
        mesaInicial3.add(cartas.get(35)); //6 de bastos

        manoJugador1Ronda3.add(cartas.get(20)); //as de espadas
        manoJugador1Ronda3.add(cartas.get(25)); //6 de espadas
        manoJugador1Ronda3.add(cartas.get(33)); //4 de bastos
        manoJugador2Ronda3.add(cartas.get(34)); //5 de bastos
        manoJugador2Ronda3.add(cartas.get(14)); //5 de copas
        manoJugador2Ronda3.add(cartas.get(32)); //3 de bastos

        mesaInicial4.add(cartas.get(21)); //2 de espadas
        mesaInicial4.add(cartas.get(34)); //5 de bastos

        manoJugador1Ronda4.add(cartas.get(12)); //3 de copas
        manoJugador1Ronda4.add(cartas.get(19)); //rey de copas
        manoJugador1Ronda4.add(cartas.get(31)); //2 de bastos
        manoJugador2Ronda4.add(cartas.get(18)); //cabballo de copas
        manoJugador2Ronda4.add(cartas.get(3)); //4 de oros
        manoJugador2Ronda4.add(cartas.get(0)); //as de oros

        mesaInicial5.add(cartas.get(31)); //2 de bastos
        mesaInicial5.add(cartas.get(12)); //3 de copas
        mesaInicial5.add(cartas.get(0)); //as de oros

        manoJugador1Ronda5.add(cartas.get(15)); //6 de copas
        manoJugador1Ronda5.add(cartas.get(16)); //7 de copas
        manoJugador1Ronda5.add(cartas.get(1)); //2 de oros
        manoJugador2Ronda5.add(cartas.get(26)); //7 de espadas
        manoJugador2Ronda5.add(cartas.get(7)); //sota de oros
        manoJugador2Ronda5.add(cartas.get(30)); //as de bastos

        mesaInicial6.add(cartas.get(15)); //6 de copas
        mesaInicial6.add(cartas.get(30)); //as de bastos

        manoJugador1Ronda6.add(cartas.get(22)); //3 de espadas
        manoJugador1Ronda6.add(cartas.get(29)); //rey de espadas
        manoJugador1Ronda6.add(cartas.get(9)); //rey de oros
        manoJugador2Ronda6.add(cartas.get(17)); //sota de copas
        manoJugador2Ronda6.add(cartas.get(39)); //rey de bastos
        manoJugador2Ronda6.add(cartas.get(36)); //7 de bastos

        jugadasRonda1.add(new Jugada(manoJugador1Ronda1.get(0), List.of(mesaInicial1.get(0), mesaInicial1.get(1))));
        jugadasRonda1.add(new Jugada(manoJugador2Ronda1.get(0), List.of(mesaInicial1.get(2))));
        jugadasRonda1.add(new Jugada(manoJugador1Ronda1.get(2), vacio));
        jugadasRonda1.add(new Jugada(manoJugador2Ronda1.get(1), vacio));
        jugadasRonda1.add(new Jugada(manoJugador1Ronda1.get(1), List.of(manoJugador1Ronda1.get(2), manoJugador2Ronda1.get(1))));
        jugadasRonda1.add(new Jugada(manoJugador2Ronda1.get(2), vacio));

        jugadasRonda2.add(new Jugada(manoJugador1Ronda2.get(2), vacio));
        jugadasRonda2.add(new Jugada(manoJugador2Ronda2.get(0), List.of(mesaInicial2.get(1), manoJugador1Ronda2.get(2))));
        jugadasRonda2.add(new Jugada(manoJugador1Ronda2.get(1), vacio));
        jugadasRonda2.add(new Jugada(manoJugador2Ronda2.get(1), List.of(mesaInicial2.get(0))));
        jugadasRonda2.add(new Jugada(manoJugador1Ronda2.get(0), vacio));
        jugadasRonda2.add(new Jugada(manoJugador2Ronda2.get(2), vacio));

        jugadasRonda3.add(new Jugada(manoJugador1Ronda3.get(2), List.of(mesaInicial3.get(0), mesaInicial3.get(2))));
        jugadasRonda3.add(new Jugada(manoJugador2Ronda3.get(0), vacio));
        jugadasRonda3.add(new Jugada(manoJugador1Ronda3.get(1), vacio));
        jugadasRonda3.add(new Jugada(manoJugador2Ronda3.get(1), vacio));
        jugadasRonda3.add(new Jugada(manoJugador1Ronda3.get(0), vacio));
        jugadasRonda3.add(new Jugada(manoJugador2Ronda3.get(2), List.of(manoJugador1Ronda3.get(1), manoJugador2Ronda3.get(1), manoJugador1Ronda3.get(0))));

        jugadasRonda4.add(new Jugada(manoJugador1Ronda4.get(1), List.of(mesaInicial4.get(1))));
        jugadasRonda4.add(new Jugada(manoJugador2Ronda4.get(0), vacio));
        jugadasRonda4.add(new Jugada(manoJugador1Ronda4.get(2), vacio));
        jugadasRonda4.add(new Jugada(manoJugador2Ronda4.get(1), List.of(mesaInicial4.get(0), manoJugador2Ronda4.get(0))));
        jugadasRonda4.add(new Jugada(manoJugador1Ronda4.get(0), vacio));
        jugadasRonda4.add(new Jugada(manoJugador2Ronda4.get(2), vacio));

        jugadasRonda5.add(new Jugada(manoJugador1Ronda5.get(2), vacio));
        jugadasRonda5.add(new Jugada(manoJugador2Ronda5.get(0), List.of(mesaInicial5.get(0), mesaInicial5.get(1), mesaInicial5.get(2), manoJugador1Ronda5.get(2))));
        jugadasRonda5.add(new Jugada(manoJugador1Ronda5.get(0), vacio));
        jugadasRonda5.add(new Jugada(manoJugador2Ronda5.get(1), vacio));
        jugadasRonda5.add(new Jugada(manoJugador1Ronda5.get(1), List.of(manoJugador2Ronda5.get(1))));
        jugadasRonda5.add(new Jugada(manoJugador2Ronda5.get(2), vacio));

        jugadasRonda6.add(new Jugada(manoJugador1Ronda6.get(1), vacio));
        jugadasRonda6.add(new Jugada(manoJugador2Ronda6.get(0), List.of(mesaInicial6.get(0), mesaInicial6.get(1))));
        jugadasRonda6.add(new Jugada(manoJugador1Ronda6.get(2), vacio));
        jugadasRonda6.add(new Jugada(manoJugador2Ronda6.get(1), vacio));
        jugadasRonda6.add(new Jugada(manoJugador1Ronda6.get(0), vacio));
        jugadasRonda6.add(new Jugada(manoJugador2Ronda6.get(2), vacio));
        
        ronda1 = new Ronda(1, mesaInicial1, manoJugador1Ronda1, manoJugador2Ronda1, jugadasRonda1);
        ronda2 = new Ronda(2, mesaInicial2, manoJugador1Ronda2, manoJugador2Ronda2, jugadasRonda2);
        ronda3 = new Ronda(3, mesaInicial3, manoJugador1Ronda3, manoJugador2Ronda3, jugadasRonda3);
        ronda4 = new Ronda(4, mesaInicial4, manoJugador1Ronda4, manoJugador2Ronda4, jugadasRonda4);
        ronda5 = new Ronda(5, mesaInicial5, manoJugador1Ronda5, manoJugador2Ronda5, jugadasRonda5);
        ronda6 = new Ronda(6, mesaInicial6, manoJugador1Ronda6, manoJugador2Ronda6, jugadasRonda6);

        rondas.add(ronda1);
        rondas.add(ronda2);
        rondas.add(ronda3);
        rondas.add(ronda4);
        rondas.add(ronda5);
        rondas.add(ronda6);

        resumenJugador1.add(0); //n escobas
        resumenJugador1.add(13); //n cartas
        resumenJugador1.add(3); //n oros
        resumenJugador1.add(1); //n sietes
        resumenJugador1.add(0); //puntos guindis
        resumenJugador1.add(0); //puntos juego
        
        resumenJugador2.add(1); //n escobas
        resumenJugador2.add(27); //n cartas
        resumenJugador2.add(7); //n oros
        resumenJugador2.add(3); //n sietes
        resumenJugador2.add(1); //puntos guindis
        resumenJugador2.add(5); //puntos juego

        partida = new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        

    }


    /**     
     * Test del constructor de la clase Partida valido  
     */
    @Test
    void testConstructorPartidaValido() {
        
        partida = new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);

        assertEquals("partida_1", partida.getIdPartida());
        assertEquals(nombreJugador1, partida.getNombreJugador1());
        assertEquals(nombreJugador2, partida.getNombreJugador2());
        assertEquals(6, partida.getRondas().size());
        assertArrayEquals(rondas.toArray(), partida.getRondas().toArray());
        assertArrayEquals(resumenJugador1.toArray(), partida.getResumenJugador1().toArray());
        assertArrayEquals(resumenJugador2.toArray(), partida.getResumenJugador2().toArray());
    }

    /**     
    * Test del constructor de la clase Partida no valido con distintos parametros invalidos
    */
    @Test
    void testConstructorPartidaNoValidoIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida(null, nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        });
    }

    /**     
    * Test del constructor de la clase Partida no valido con id vacio
    */
    @Test
    void testConstructorPartidaNoValidoIdVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida("", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        });
    }

    /**     
    * Test del constructor de la clase Partida no valido con nombre jugador 1 null
    */
    @Test
    void testConstructorPartidaNoValidoNombreJugador1Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida("partida_1", null, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        });
    }

    /**     
    * Test del constructor de la clase Partida no valido con nombre jugador 2 null
    */
    @Test
    void testConstructorPartidaNoValidoNombreJugador2Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida("partida_1", nombreJugador1, null, rondas, resumenJugador1, resumenJugador2);
        });
    }

    /**     
    * Test del constructor de la clase Partida no valido con nombre jugador 1 vacio
    */
    @Test
    void testConstructorPartidaNoValidoNombreJugador1Vacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida("partida_1", "", nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        });
    }

    /**     
    * Test del constructor de la clase Partida no valido con nombre jugador 2 vacio
    */
    @Test
    void testConstructorPartidaNoValidoNombreJugador2Vacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida("partida_1", nombreJugador1, "", rondas, resumenJugador1, resumenJugador2);
        });
    }

    /**     
    * Test del constructor de la clase Partida no valido con rondas null
    */
    @Test
    void testConstructorPartidaNoValidoRondasNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida("partida_1", nombreJugador1, nombreJugador2, null, resumenJugador1, resumenJugador2);
        });
    }

    /**     
    * Test del constructor de la clase Partida no valido con resumen jugador 1 null
    */
    @Test
    void testConstructorPartidaNoValidoResumenJugador1Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, null, resumenJugador2);
        });
    }

    /**     
    * Test del constructor de la clase Partida no valido con resumen jugador 2 null
    */
    @Test
    void testConstructorPartidaNoValidoResumenJugador2Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, resumenJugador1, null);
        });
    }

    /**     
    * Test del constructor de la clase Partida no valido con rondas incompletas
    */
    @Test
    void testConstructorPartidaNoValidoRondasIncompletas() {
        rondas.remove(5);
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        });
    }

    /**     
    * Test del constructor de la clase Partida no valido con resumen jugador 1 incompleto
    */
    @Test
    void testConstructorPartidaNoValidoResumenJugador1Incompleto() {
        resumenJugador1.remove(5);
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        });
    }

    /**     
    * Test del constructor de la clase Partida no valido con resumen jugador 2 incompleto
    */
    @Test
    void testConstructorPartidaNoValidoResumenJugador2Incompleto() {
        resumenJugador2.remove(5);
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        });
    }

    /**
     * Test del constructor por defecto de la clase Partida
     */
    @Tag("Cobertura")
    @Test
    void constructorPorDefecto() {
        Partida partidaDefecto = new Partida();
        assertNull(partidaDefecto.getIdPartida());
        assertNull(partidaDefecto.getNombreJugador1());
        assertNull(partidaDefecto.getNombreJugador2());
        assertNull(partidaDefecto.getRondas());
        assertNull(partidaDefecto.getResumenJugador1());
        assertNull(partidaDefecto.getResumenJugador2());
    }

    /**
     * Test del metodo setNombreJugador1 de la clase Partida valido
     */
    @Tag("Cobertura")
    @Test
    void setNombreJugador1Valido() {
        
        partida.setNombreJugador1("Carlos");
        assertEquals("Carlos", partida.getNombreJugador1());
    }

    /**
     * Test del metodo setNombreJugador1 de la clase Partida no valido con nombre null
     */
    @Tag("Cobertura")
    @Test
    void setNombreJugador1NoValidoNull() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setNombreJugador1(null);
        });
    }

    /**
     * Test del metodo setNombreJugador1 de la clase Partida no valido con nombre vacio
     */
    @Tag("Cobertura")
    @Test
    void setNombreJugador1NoValidoVacio() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setNombreJugador1("");
        });
    }

    /**
     * Test del metodo setNombreJugador2 de la clase Partida valido
     */
    @Tag("Cobertura")
    @Test
    void setNombreJugador2Valido() {
        
        partida.setNombreJugador2("Carlos");
        assertEquals("Carlos", partida.getNombreJugador2());
    }

    /**
     * Test del metodo setNombreJugador2 de la clase Partida no valido con nombre null
     */
    @Tag("Cobertura")
    @Test
    void setNombreJugador2NoValidoNull() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setNombreJugador2(null);
        });
    }

    /**
     * Test del metodo setNombreJugador2 de la clase Partida no valido con nombre vacio
     */
    @Tag("Cobertura")
    @Test
    void setNombreJugador2NoValidoVacio() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setNombreJugador2("");
        });
    }

    /**
     * Test del metodo setFechaHora de la clase Partida valido
     */
    @Tag("Cobertura")
    @Test
    void setFechaHoraValido() {
        
        partida.setFechaHora(java.time.LocalDateTime.now());
        assertEquals(java.time.LocalDateTime.now().getDayOfMonth(), partida.getFechaHora().getDayOfMonth());
    }

    /**
     * Test del metodo setFechaHora de la clase Partida no valido con fecha null
     */
    @Tag("Cobertura")
    @Test
    void setFechaHoraNoValidoNull() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setFechaHora(null);
        });
    }

    /**
     * Test del metodo setRondas de la clase Partida valido
     */
    @Tag("Cobertura")
    @Test
    void setRondasValido() {
        
        partida.setRondas(rondas);
        assertEquals(6, partida.getRondas().size());
    }

    /**
     * Test del metodo setRondas de la clase Partida no valido con rondas null
     */
    @Tag("Cobertura")
    @Test
    void setRondasNoValidoNull() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setRondas(null);
        });
    }

    /**
     * Test del metodo setRondas de la clase Partida no valido con rondas incompletas
     */
    @Tag("Cobertura")
    @Test
    void setRondasNoValidoIncompleto() {
        rondas.remove(5);
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setRondas(rondas);
        });
    }

    /**
     * Test del metodo setResumenJugador1 de la clase Partida valido
     */
    @Tag("Cobertura")
    @Test
    void setResumenJugador1Valido() {
        
        partida.setResumenJugador1(resumenJugador1);
        assertArrayEquals(resumenJugador1.toArray(), partida.getResumenJugador1().toArray());
    }

    /**
     * Test del metodo setResumenJugador1 de la clase Partida no valido con resumen null
     */
    @Tag("Cobertura")
    @Test
    void setResumenJugador1NoValidoNull() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setResumenJugador1(null);
        });
    }

    /**
     * Test del metodo setResumenJugador1 de la clase Partida no valido con resumen incompleto
     */
    @Tag("Cobertura")
    @Test
    void setResumenJugador1NoValidoIncompleto() {
        resumenJugador1.remove(5);
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setResumenJugador1(resumenJugador1);
        });
    }

    /**
     * Test del metodo setResumenJugador2 de la clase Partida valido
     */
    @Tag("Cobertura")
    @Test
    void setResumenJugador2Valido() {
        
        partida.setResumenJugador2(resumenJugador2);
        assertArrayEquals(resumenJugador2.toArray(), partida.getResumenJugador2().toArray());
    }

    /**
     * Test del metodo setResumenJugador2 de la clase Partida no valido con resumen null
     */
    @Tag("Cobertura")
    @Test
    void setResumenJugador2NoValidoNull() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setResumenJugador2(null);
        });
    }

    /**
     * Test del metodo setResumenJugador2 de la clase Partida no valido con resumen incompleto
     */
    @Tag("Cobertura")
    @Test
    void setResumenJugador2NoValidoIncompleto() {
        resumenJugador2.remove(5);
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setResumenJugador2(resumenJugador2);
        });
    }



    
    /** 
     * Test del metodo getGanador de la clase Partida
     */
    @Tag("Cobertura")
    @Test
    void testGetGanador(){

        assertEquals(nombreJugador2, new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2).getGanador());

    }

    /** 
    * Test del metodo compruebaGanador de la clase Partida ganador jugador 1
    */
    @Tag("Cobertura")
    @Test
    void testCompruebaGanadorJugador1(){

        resumenJugador1.set(5,5);
        resumenJugador2.set(5,0);
        
        assertEquals(nombreJugador1,new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2).compruebaGanador());

    }

    /** 
    * Test del metodo compruebaGanador de la clase Partida ganador jugador 2
    */
    @Tag("Cobertura")
    @Test
    void testCompruebaGanadorJugador2(){

    
        assertEquals(nombreJugador2,new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2).compruebaGanador());

    }

    /** 
    * Test del metodo compruebaGanador de la clase Partida empate
    */
    @Tag("Cobertura")
    @Test
    void testCompruebaGanadorEmpate(){

        resumenJugador1.set(5,2);
        resumenJugador2.set(5,2);
        
        assertEquals("Empate",new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2).compruebaGanador());

    }

    /**
     * Test del metodo setGanador de la clase Partida con distintos valores validos e invalidos
     */
    @Tag("Cobertura")
    @Test
    void setGanadorValidoJugador1() {
        
        partida.setGanador("Ana");
        assertEquals("Ana", partida.getGanador());
    }

    /**
     * Test del metodo setGanador de la clase Partida con distintos valores validos e invalidos
     */
    @Tag("Cobertura")
    @Test
    void setGanadorValidoJugador2() {
        
        partida.setGanador("Luis");
        assertEquals("Luis", partida.getGanador());
    }

    /**
     * Test del metodo setGanador de la clase Partida con distintos valores validos e invalidos
     */
    @Tag("Cobertura")
    @Test
    void setGanadorValidoEmpate() {
        
        partida.setGanador("Empate");
        assertEquals("Empate", partida.getGanador());
    }

    /**
     * Test del metodo setGanador de la clase Partida con distintos valores validos e invalidos
     */
    @Tag("Cobertura")
    @Test
    void setGanadorNoValidoNull() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setGanador(null);
        });
    }

    /**
     * Test del metodo setGanador de la clase Partida con distintos valores validos e invalidos
     */
    @Tag("Cobertura")
    @Test
    void setGanadorNoValidoVacio() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setGanador("");
        });
    }
    
    /**
     * Test del metodo setGanador de la clase Partida con distintos valores validos e invalidos
     */
    @Tag("Cobertura")
    @Test
    void setGanadorNoValidoInexistente() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            partida.setGanador("Pedro");
        });
    }

    
    /**
     * Test del metodo equals de la clase Partida
     */
    @Tag("Cobertura")
    @Test
    void testEquals() {
        Partida otraPartida = new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertEquals(partida, otraPartida);
    }

    /**
     * Test del metodo equals de la clase Partida con el mismo objeto
     */
    @Tag("Cobertura")
    @Test
    void testEqualsSameObject() {
        assertEquals(partida, partida);
    }

    /**
     * Test del metodo equals de la clase Partida con objetos diferentes
     */
    @Tag("Cobertura")
    @Test
    void testNotEquals() {
        Partida otraPartida = new Partida("partida_2", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertEquals(false, partida.equals(otraPartida));
    }

    /**
     * Test del metodo equals de la clase Partida con objeto de clase diferente
     */
    @Tag("Cobertura")
    @Test
    void testNotEqualsDifferentClass() {
        String otraClase = "No es una partida";
        assertEquals(false, partida.equals(otraClase));
    }

    /**
     * Test del metodo equals de la clase Partida con objeto null
     */
    @Tag("Cobertura")
    @Test
    void testNotEqualsNull() {
        assertEquals(false, partida.equals(null));
    }

    /**
     * Test del metodo hashCode de la clase Partida
     */
    @Tag("Cobertura")
    @Test
    void testHashCode() {
        Partida otraPartida = new Partida("partida_1", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertEquals(partida.hashCode(), otraPartida.hashCode());
    }



}
