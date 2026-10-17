package uva.tds;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ServicioPartidaEnAislamientoTest {
    
    /**
     * Atributo de la clase GestorPartidaTest
     */
    @TestSubject
    private ServicioPartida servicioPartida;

    /**
     * Repositorio mockeado para las pruebas
     */
    @Mock
    private IRepositorioPartida repositorio;

    // Atributos auxiliares
    private Partida partida;
    private Partida partidaOtra;
    private List<Integer> resumenJugador1;
    private List<Integer> resumenJugador2;
    private List<Ronda> rondas;
    
    // Listas de datos
    private List<Partida> listaPartidasJugador1;
    private List<Partida> listaPartidasJugador2;
    private List<Partida> listaPartidasFecha;
    private List<Partida> listaPartidas10; // Contendrá las 55 partidas
    private List<Carta> cartas = new ArrayList<>();

    // Nombres
    private String nombreJugador1;
    private String nombreJugador2;

    @BeforeEach
    void setUp(){
        
        repositorio=EasyMock.mock(IRepositorioPartida.class);
        servicioPartida=new ServicioPartida();
        resumenJugador1=new ArrayList<>(Arrays.asList(0, 13, 3, 1, 0, 0));
        resumenJugador2=new ArrayList<>(Arrays.asList(1, 27, 7, 3, 1, 5));

        configurarRondasYJugadas();

        nombreJugador1 = "Ana";
        nombreJugador2 = "Luis";

        partida = new Partida("partida", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        partidaOtra=new Partida("OtraPartida", nombreJugador2,nombreJugador1, rondas , resumenJugador1, resumenJugador2);

        generarPartidasTop10();

        listaPartidasJugador1=new ArrayList<>(List.of(partida, partidaOtra));
        
        listaPartidasFecha=new ArrayList<>(List.of(partida, partidaOtra));

        listaPartidasJugador2=new ArrayList<>(List.of(partidaOtra));

    }

    /**
     * Método auxiliar para generar las 55 partidas del Top 10 sin repetir código.
     */
    private void generarPartidasTop10() {
        listaPartidas10 = new ArrayList<>();
        
        // Mapa de Rival -> Cantidad de victorias necesarias
        Map<String, Integer> historial = new LinkedHashMap<>();
        historial.put("Marcos", 10);
        historial.put("Angel", 9);
        historial.put("Manuel", 8);
        historial.put("Marisa", 7);
        historial.put("Fernando", 6);
        historial.put("Alex", 5);
        historial.put("Carla", 4);
        historial.put("Luisa", 3);
        historial.put("Luis", 2);
        historial.put("Ana", 1);

        int idCounter = 1;
        for (Map.Entry<String, Integer> entry : historial.entrySet()) {
            String rival = entry.getKey();
            int cantidad = entry.getValue();
            
            for (int i = 0; i < cantidad; i++) {
                listaPartidas10.add(new Partida(
                    "partida_" + idCounter++, 
                    "Latasa", 
                    rival, 
                    rondas, resumenJugador1, resumenJugador2
                ));
            }
        }
    }

    /**
     * Metodo auxiliar para configurar las rondas y jugadas utilizadas en los tests
     */
    private void configurarRondasYJugadas() {
        
        rondas = new ArrayList<>();
        List<Carta> vacio = new ArrayList<>();

        for(int i = 1; i <= 40; i++) {
            cartas.add(new Carta(i));
        }

        // -- RONDA 1 --
        List<Carta> mesa1 = new ArrayList<>(Arrays.asList(new Carta(5), new Carta(9), new Carta(38), new Carta(39))); // ids originales: 4, 8, 37, 38 -> cartas: 5, 9, sota bastos, caballo bastos
        List<Carta> manoJ1R1 = new ArrayList<>(Arrays.asList(new Carta(11), new Carta(14), new Carta(29)));
        List<Carta> manoJ2R1 = new ArrayList<>(Arrays.asList(new Carta(7), new Carta(12), new Carta(3)));
        
        List<Jugada> jugadasR1 = new ArrayList<>();
        jugadasR1.add(new Jugada(manoJ1R1.get(0), List.of(mesa1.get(0), mesa1.get(1))));
        jugadasR1.add(new Jugada(manoJ2R1.get(0), List.of(mesa1.get(2))));
        jugadasR1.add(new Jugada(manoJ1R1.get(2), vacio));
        jugadasR1.add(new Jugada(manoJ2R1.get(1), vacio));
        jugadasR1.add(new Jugada(manoJ1R1.get(1), List.of(manoJ1R1.get(2), manoJ2R1.get(1)))); // Nota: lógica original mantenida aunque extraña
        jugadasR1.add(new Jugada(manoJ2R1.get(2), vacio));
        
        rondas.add(new Ronda(1, mesa1, manoJ1R1, manoJ2R1, jugadasR1));

        // -- RONDA 2 --
        List<Carta> mesa2 = new ArrayList<>(Arrays.asList(new Carta(39), new Carta(3))); 
        List<Carta> manoJ1R2 = new ArrayList<>(Arrays.asList(new Carta(22), new Carta(25), new Carta(28)));
        List<Carta> manoJ2R2 = new ArrayList<>(Arrays.asList(new Carta(24), new Carta(6), new Carta(36)));

        List<Jugada> jugadasR2 = new ArrayList<>();
        jugadasR2.add(new Jugada(manoJ1R2.get(2), vacio));
        jugadasR2.add(new Jugada(manoJ2R2.get(0), List.of(mesa2.get(1), manoJ1R2.get(2))));
        jugadasR2.add(new Jugada(manoJ1R2.get(1), vacio));
        jugadasR2.add(new Jugada(manoJ2R2.get(1), List.of(mesa2.get(0))));
        jugadasR2.add(new Jugada(manoJ1R2.get(0), vacio));
        jugadasR2.add(new Jugada(manoJ2R2.get(2), vacio));

        rondas.add(new Ronda(2, mesa2, manoJ1R2, manoJ2R2, jugadasR2));

        // -- RONDA 3 --
        List<Carta> mesa3 = new ArrayList<>(Arrays.asList(new Carta(25), new Carta(22), new Carta(36)));
        List<Carta> manoJ1R3 = new ArrayList<>(Arrays.asList(new Carta(21), new Carta(26), new Carta(34)));
        List<Carta> manoJ2R3 = new ArrayList<>(Arrays.asList(new Carta(35), new Carta(15), new Carta(33)));

        List<Jugada> jugadasR3 = new ArrayList<>();
        jugadasR3.add(new Jugada(manoJ1R3.get(2), List.of(mesa3.get(0), mesa3.get(2))));
        jugadasR3.add(new Jugada(manoJ2R3.get(0), vacio));
        jugadasR3.add(new Jugada(manoJ1R3.get(1), vacio));
        jugadasR3.add(new Jugada(manoJ2R3.get(1), vacio));
        jugadasR3.add(new Jugada(manoJ1R3.get(0), vacio));
        jugadasR3.add(new Jugada(manoJ2R3.get(2), List.of(manoJ1R3.get(1), manoJ2R3.get(1), manoJ1R3.get(0))));

        rondas.add(new Ronda(3, mesa3, manoJ1R3, manoJ2R3, jugadasR3));

        // -- RONDA 4 --
        List<Carta> mesa4 = new ArrayList<>(Arrays.asList(new Carta(22), new Carta(35)));
        List<Carta> manoJ1R4 = new ArrayList<>(Arrays.asList(new Carta(13), new Carta(20), new Carta(32)));
        List<Carta> manoJ2R4 = new ArrayList<>(Arrays.asList(new Carta(19), new Carta(4), new Carta(1)));

        List<Jugada> jugadasR4 = new ArrayList<>();
        jugadasR4.add(new Jugada(manoJ1R4.get(1), List.of(mesa4.get(1))));
        jugadasR4.add(new Jugada(manoJ2R4.get(0), vacio));
        jugadasR4.add(new Jugada(manoJ1R4.get(2), vacio));
        jugadasR4.add(new Jugada(manoJ2R4.get(1), List.of(mesa4.get(0), manoJ2R4.get(0))));
        jugadasR4.add(new Jugada(manoJ1R4.get(0), vacio));
        jugadasR4.add(new Jugada(manoJ2R4.get(2), vacio));

        rondas.add(new Ronda(4, mesa4, manoJ1R4, manoJ2R4, jugadasR4));

        // -- RONDA 5 --
        List<Carta> mesa5 = new ArrayList<>(Arrays.asList(new Carta(32), new Carta(13), new Carta(1)));
        List<Carta> manoJ1R5 = new ArrayList<>(Arrays.asList(new Carta(16), new Carta(17), new Carta(2)));
        List<Carta> manoJ2R5 = new ArrayList<>(Arrays.asList(new Carta(27), new Carta(8), new Carta(31)));

        List<Jugada> jugadasR5 = new ArrayList<>();
        jugadasR5.add(new Jugada(manoJ1R5.get(2), vacio));
        jugadasR5.add(new Jugada(manoJ2R5.get(0), List.of(mesa5.get(0), mesa5.get(1), mesa5.get(2), manoJ1R5.get(2))));
        jugadasR5.add(new Jugada(manoJ1R5.get(0), vacio));
        jugadasR5.add(new Jugada(manoJ2R5.get(1), vacio));
        jugadasR5.add(new Jugada(manoJ1R5.get(1), List.of(manoJ2R5.get(1))));
        jugadasR5.add(new Jugada(manoJ2R5.get(2), vacio));

        rondas.add(new Ronda(5, mesa5, manoJ1R5, manoJ2R5, jugadasR5));

        // -- RONDA 6 --
        List<Carta> mesa6 = new ArrayList<>(Arrays.asList(new Carta(16), new Carta(31)));
        List<Carta> manoJ1R6 = new ArrayList<>(Arrays.asList(new Carta(23), new Carta(30), new Carta(10)));
        List<Carta> manoJ2R6 = new ArrayList<>(Arrays.asList(new Carta(18), new Carta(40), new Carta(37)));

        List<Jugada> jugadasR6 = new ArrayList<>();
        jugadasR6.add(new Jugada(manoJ1R6.get(1), vacio));
        jugadasR6.add(new Jugada(manoJ2R6.get(0), List.of(mesa6.get(0), mesa6.get(1))));
        jugadasR6.add(new Jugada(manoJ1R6.get(2), vacio));
        jugadasR6.add(new Jugada(manoJ2R6.get(1), vacio));
        jugadasR6.add(new Jugada(manoJ1R6.get(0), vacio));
        jugadasR6.add(new Jugada(manoJ2R6.get(2), vacio));

        rondas.add(new Ronda(6, mesa6, manoJ1R6, manoJ2R6, jugadasR6));


    }

    /** 
     * Test del metodo cargarPartida de la clase GestorPartida valido correcto
     */
    @Test
    void testCargarPartidaCorrecta(){

        EasyMock.expect(repositorio.cargar("prueba")).andReturn(partida);

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        assertEquals(partida,servicioPartida.cargarPartida("prueba"));

        EasyMock.verify(repositorio);
        

    }

    /** 
     * Test del metodo cargarPartida de la clase GestorPartida no valido no existe identificador
     */
    @Test
    void testCargarPartidaNoValidoNoExisteIdentificador(){

        EasyMock.expect(repositorio.cargar("otroNombre")).andThrow(new IllegalStateException());

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        assertThrows(IllegalStateException.class,()->{

            servicioPartida.cargarPartida("otroNombre");
        });

        EasyMock.verify(repositorio);


    }

    /**
     * Test del metodo cargarPartida de la clase GestorPartida no valido identificador nulo
     */
    @Test
    void testCargarPartidaNoValidaIdentifcadorNulo(){

        
        EasyMock.expect(repositorio.cargar(null)).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);
        

        servicioPartida.setRepositorioPartida(repositorio);

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.cargarPartida(null);
        });

        EasyMock.verify(repositorio);

        
    }

    /**
     * Test del metodo cargarPartida de la clase GestorPartida no valido identificador vacio
     */
    @Test
    void testCargarPartidaNoValidaIdentificadorVacio(){

        EasyMock.expect(repositorio.cargar("")).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.cargarPartida("");
        });

        EasyMock.verify(repositorio);

    }


    /**
     * Test del metodo guardarPartida de la clase GestorPartida valido
     */
    @Test
    void testGuardarPartidaValido(){

        Partida partida2=new Partida("otra", nombreJugador1,nombreJugador2, rondas , resumenJugador1, resumenJugador2);

        repositorio.guardar(partida2);
        EasyMock.expectLastCall();
        EasyMock.expect(repositorio.cargar(partida2.getIdPartida())).andReturn(partida2);
        
        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        servicioPartida.guardarPartida(partida2);

        assertEquals(partida2,servicioPartida.cargarPartida(partida2.getIdPartida()));
        EasyMock.verify(repositorio);


    }


    /**
     * Test del metodo guardarPartida de la clase GestorPartida no valido partida nula
     */
    @Test
    void testGuardarPartidaNoValidoPartidaNulo(){

        partida=null;

        repositorio.guardar(partida);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.guardarPartida(partida);
        });

        EasyMock.verify(repositorio);

    }

    /**
     * Test del metodo guardarPartida de la clase GestorPartida no valido partida con mismo ID
     */
    @Test
    void testGuardarPartidaNoValidoPartidaConMismoID(){

        Partida partida2=new Partida("Prueba", nombreJugador1,nombreJugador2, rondas , resumenJugador1, resumenJugador2);


        repositorio.guardar(partida2);
        EasyMock.expectLastCall().andThrow(new IllegalStateException());

        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);

        assertThrows(IllegalStateException.class,()->{

            servicioPartida.guardarPartida(partida2);
        });

        EasyMock.verify(repositorio);

    }

    /**
     * Test del metodo actualizarPartida de la clase GestorPartida valido
     */
    @Test
    void testActualizarPartidaValido(){

        Partida partida2=new Partida("Prueba", nombreJugador2,nombreJugador1, rondas , resumenJugador1, resumenJugador2);
    
        repositorio.actualizar(partida2);
        EasyMock.expectLastCall();

        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);

        servicioPartida.actualizarPartida(partida2);

        EasyMock.verify(repositorio);

    }
    
    /**
     * Test del metodo actualizarPartida de la clase GestorPartida no valido partida nula
     */
    @Test
    void testActualizarPartidaNoValidoPartidaNula(){

        partida=null;

        repositorio.actualizar(partida);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.actualizarPartida(partida);
        });

        EasyMock.verify(repositorio);

    }


    /**
     * Test del metodo actualizarPartida de la clase GestorPartida no valido partida no existe ID
     */
    @Test
    void testActualizarPartidaNoValidoPartidaNoExisteID(){

        Partida partida2=new Partida("otroID", nombreJugador1,nombreJugador2, rondas , resumenJugador1, resumenJugador2);

        repositorio.actualizar(partida2);
        EasyMock.expectLastCall().andThrow(new IllegalStateException());

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        assertThrows(IllegalStateException.class,()->{
            
            servicioPartida.actualizarPartida(partida2);
        });

        EasyMock.verify(repositorio);

    }

    /**
     * Test del metodo eliminarPartida de la clase GestorPartida valido
     */
    @Test
    void testEliminarPartidaValido(){

        repositorio.eliminar(partida.getIdPartida());
        EasyMock.expectLastCall();

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        servicioPartida.eliminarPartida(partida.getIdPartida());

        EasyMock.verify(repositorio);


    }

    /**
     * Test del metodo eliminarPartida de la clase GestorPartida no valido ID nulo
     */
    @Test
    void testEliminarPartidaNoValidoIDNulo(){

        repositorio.eliminar(null);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.eliminarPartida(null);

        });

        EasyMock.verify(repositorio);

    }

    /**
     * Test del metodo eliminarPartida de la clase GestorPartida no valido ID vacio
     */
    @Test
    void testEliminarPartidaNoValidoIDVacio(){

        repositorio.eliminar("");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.eliminarPartida("");

        });

        EasyMock.verify(repositorio);

    }

    /**
     * Test del metodo eliminarPartida de la clase GestorPartida no valido no existe ID
     */
    @Test
    void testEliminarPartidaNoValidoNoExisteID(){


        repositorio.eliminar("esteIdNoExiste");
        EasyMock.expectLastCall().andThrow(new IllegalStateException());

        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);


        assertThrows(IllegalStateException.class,()->{

            servicioPartida.eliminarPartida("esteIdNoExiste");

        });

        EasyMock.verify(repositorio);

    }

    /** 
     * Test del metodo obtenerPartidasJugador de la clase GestorPartida valido
     */
    @Test
    void testObtenerPartidasJugador(){

        EasyMock.expect(repositorio.obtenerPartidasPorJugador(nombreJugador1)).andReturn(listaPartidasJugador1);

        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);


        assertEquals(listaPartidasJugador1,servicioPartida.obtenerPartidasJugador(nombreJugador1));

        EasyMock.verify(repositorio);

    }

    /** 
     * Test del metodo obtenerPartidasJugador de la clase GestorPartida valido jugador 2
     */
    @Tag("Cobertura")
    @Test
    void testObtenerPartidasJugador2(){

        

        assertEquals(0,servicioPartida.obtenerPartidasJugador(listaPartidasJugador1, "Pedro"));


    }

    /** 
     * Test del metodo obtenerPartidasJugador de la clase GestorPartida no valido nombre jugador nulo
     */
    @Test
    void testObtenerPartidasJugadorNoValidoNombreJugadorNulo(){

        EasyMock.expect(repositorio.obtenerPartidasPorJugador(null)).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerPartidasJugador(null);

        });

        EasyMock.verify(repositorio);

    }

    /** 
     * Test del metodo obtenerPartidasJugador de la clase GestorPartida no valido nombre jugador vacio
     */
    @Test
    void testObtenerPartidasJugadorNoValidoNombreJugadorVacio(){

        EasyMock.expect(repositorio.obtenerPartidasPorJugador("")).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerPartidasJugador("");

        });

        EasyMock.verify(repositorio);

    }

    /** 
     * Test del metodo obtenerPartidasFecha de la clase GestorPartida valido
     */
    @Test
    void testObtenerPartidasFechaValido(){

        EasyMock.expect(repositorio.obtenerPartidasPorFecha("2025-04-04", "2005-06-12")).andReturn(listaPartidasFecha);

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        assertEquals(listaPartidasFecha,servicioPartida.obtenerPartidasFecha("2025-04-04", "2005-06-12"));

        EasyMock.verify(repositorio);

    }

    /** 
     * Test del metodo obtenerPartidasFecha de la clase GestorPartida no valido repositorio nulo
     */
    @Test
    void testObtenerPartidasFechaNoValidoFechaInicialNull(){

    
        EasyMock.expect(repositorio.obtenerPartidasPorFecha(null, "2005-06-12")).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerPartidasFecha(null, "2005-06-12");

        });

        EasyMock.verify(repositorio);


    }

    /** 
     * Test del metodo obtenerPartidasFecha de la clase GestorPartida no valido fecha final nulo
     */
    @Test
    void testObtenerPartidasFechaNoValidoFechaFinalNulo(){

        EasyMock.expect(repositorio.obtenerPartidasPorFecha("2025-04-04", null)).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerPartidasFecha("2025-04-04", null);

        });

        EasyMock.verify(repositorio);

    }


    /** 
     * Test del metodo obtenerPartidasFecha de la clase GestorPartida no valido fecha formato no valido
     */
    @Test
    void testObtenerPartidasFechaNoValidoFormatoNoValido(){

        EasyMock.expect(repositorio.obtenerPartidasPorFecha("25-4-04", "10-2-3")).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerPartidasFecha("25-4-04", "10-2-3");

        });

        EasyMock.verify(repositorio);

    }

    /** 
     * Test del metodo setRepositorio de la clase GestorPartida valido
     */
    @Test
    void testSetRepositorioValido(){


        servicioPartida.setRepositorioPartida(repositorio);
        assertNotNull(repositorio);

    }

    /** 
     * Test del metodo setRepositorio de la clase GestorPartida no valido repositorio nulo
     */
    @Test
    void testSetRepositorioNoValidoNulo(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.setRepositorioPartida(null);

        });

    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida valido
     */
    @Test
    void testValidaPartidaValida(){

        assertTrue(servicioPartida.validarPartida(partida));

    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida no valido partida nula
     */
    @Test
    void testValidaPartidaNoValidaNula(){
        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.validarPartida(null);

        });
    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida no valido partida mal formada
     */
    @Test
    void testValidaPartidaNoValidaEstaMal(){
        Partida partidaInvalida=new Partida("Prueba2", nombreJugador1,nombreJugador2, rondas , resumenJugador2, resumenJugador1);
        assertFalse(servicioPartida.validarPartida(partidaInvalida));

    }

    /** 
     * Test del metodo obtenerEstadisticasEntreFechas de la clase GestorPartida valido
     */
    @Test
    void testObtenerEstadisticasEntreFechas(){

        EasyMock.expect(repositorio.obtenerPartidasPorFecha("2025-04-04", "2005-06-12")).andReturn(listaPartidasFecha).times(4);

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        assertEquals(listaPartidasFecha.size(),servicioPartida.obtenerEstadisticasEntreFechas("2025-04-04", "2005-06-12")[0]);
        assertEquals(2, servicioPartida.obtenerEstadisticasEntreFechas("2025-04-04", "2005-06-12")[1]);
        
        EasyMock.verify(repositorio);

    }

    /** 
     * Test del metodo obtenerEstadisticasEntreFechas de la clase GestorPartida no valido repositorio nulo
     */
    @Tag("Cobertura")
    @Test
    void testObtenerEstadisticasEntreFechasNoValidoRepositorioNulo(){

        assertThrows(IllegalStateException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2025-04-04", "2005-06-12");

        });

    }

    /** 
     * Test del metodo obtenerEstadisticasEntreFechas de la clase GestorPartida no valido fecha inicial nula
     */
    @Test
    void testObtenerEstadisticasNoValidoFechaInicialNull(){


        EasyMock.expect(repositorio.obtenerPartidasPorFecha(null, "2005-06-12")).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas(null,"2005-06-12");

        });

        EasyMock.verify(repositorio);


    }

    /** 
     * Test del metodo obtenerEstadisticasEntreFechas de la clase GestorPartida no valido fecha inicial vacia
     */
    @Test
    void testObtenerEstadisticasNoValidoFechaFinalNull(){

        EasyMock.expect(repositorio.obtenerPartidasPorFecha("2005-06-12",null)).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);
        
        servicioPartida.setRepositorioPartida(repositorio);

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2005-06-12",null);
        });

        EasyMock.verify(repositorio);

    }

    /** 
     * Test del metodo obtenerEstadisticasEntreFechas de la clase GestorPartida no valido fecha final vacia
     */
    @Test
    void testObtenerEstadisticasNoValidoFechaFinalVacia(){


        EasyMock.expect(repositorio.obtenerPartidasPorFecha("2005-06-12","")).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);
        
        servicioPartida.setRepositorioPartida(repositorio);

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2005-06-12","");
        });

        EasyMock.verify(repositorio);

    }

    /** 
     * Test del metodo obtenerEstadisticasEntreFechas de la clase GestorPartida no valido fecha inicial vacia
     */
    @Test
    void testObtenerEstadisticasNoValidoFechaInicialVacio(){

        EasyMock.expect(repositorio.obtenerPartidasPorFecha("","2005-06-12")).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);
        
        servicioPartida.setRepositorioPartida(repositorio);

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("","2005-06-12");
        });

        EasyMock.verify(repositorio);
    }


    /** 
     * Test del metodo obtenerEstadisticasJugador de la clase GestorPartida valido
     */
    @Test
    void testObtenerEstadisticasJugador(){


        EasyMock.expect(repositorio.obtenerPartidasPorJugador("Luis")).andReturn(listaPartidasJugador1).times(12);

        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);

        assertEquals(2,servicioPartida.obtenerEstadisticasJugador("Luis")[0]);
        assertEquals(1,servicioPartida.obtenerEstadisticasJugador("Luis")[1]);
        assertEquals(1,servicioPartida.obtenerEstadisticasJugador("Luis")[2]);
        assertEquals(1,servicioPartida.obtenerEstadisticasJugador("Luis")[3]);


        EasyMock.verify(repositorio);

    }


    /** 
     * Test del metodo obtenerEstadisticasJugador de la clase GestorPartida valido jugador 2
     */
    @Tag("Cobertura")
    @Test
    void testObtenerEstadisticasJugador2(){


        EasyMock.expect(repositorio.obtenerPartidasPorJugador("Luis")).andReturn(listaPartidasJugador2).times(12);

        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);

        assertEquals(1,servicioPartida.obtenerEstadisticasJugador("Luis")[0]);
        assertEquals(0,servicioPartida.obtenerEstadisticasJugador("Luis")[1]);
        assertEquals(1,servicioPartida.obtenerEstadisticasJugador("Luis")[2]);
        assertEquals(0,servicioPartida.obtenerEstadisticasJugador("Luis")[3]);


        EasyMock.verify(repositorio);

    }

    /** 
     * Test del metodo obtenerEstadisticasJugador de la clase GestorPartida no valido repositorio nulo
     */
    @Tag("Cobertura")
    @Test
    void testObtenerEstadisticasJugadorNoValidoRepositorioNulo(){

        assertThrows(IllegalStateException.class,()->{

            servicioPartida.obtenerEstadisticasJugador(nombreJugador1);

        });


    }

    /** 
     * Test del metodo obtenerEstadisticasJugador de la clase GestorPartida no valido jugador nulo
     */
    @Test
    void testObtenerEstadisticasJugadorNoValidoNulo(){

        EasyMock.expect(repositorio.obtenerPartidasPorJugador(null)).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasJugador(null);

        });

        EasyMock.verify(repositorio);


    }

    /** 
     * Test del metodo obtenerEstadisticasJugador de la clase GestorPartida no valido jugador vacio
     */
    @Test
    void testObtenerEstadisticasJugadorNovalidoVacio(){

        EasyMock.expect(repositorio.obtenerPartidasPorJugador("")).andThrow(new IllegalArgumentException());

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasJugador("");
        });

        EasyMock.verify(repositorio);
    

    }


    /** 
     * Test del metodo obtenerTop10 de la clase GestorPartida valido
     */
    @Test
    void testObtenerTop10(){

        
        EasyMock.expect(repositorio.obtenerPartidasPorFecha("2000-01-01", "2100-11-26")).andReturn(listaPartidas10).times(2);
        
        EasyMock.replay(repositorio);


        servicioPartida.setRepositorioPartida(repositorio);
        List<String> top=new ArrayList<>(Arrays.asList("Marcos - 10 victorias","Angel - 9 victorias","Manuel - 8 victorias","Marisa - 7 victorias","Fernando - 6 victorias","Alex - 5 victorias","Carla - 4 victorias","Luisa - 3 victorias","Luis - 2 victorias","Ana - 1 victorias"));

        assertEquals(10,servicioPartida.obtenerTop10().size());
        assertArrayEquals(top.toArray(),servicioPartida.obtenerTop10().toArray());
        
        EasyMock.verify(repositorio);

    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida no valido cartas obtenidas distintas jugador 1
     */
    @Tag("Cobertura")
    @Test
    void testValidarPartidaNoValidaCartasObtenidasDistintasJugador1(){
        
        resumenJugador1.clear();
    
        resumenJugador1.add(0); //n escobas
        resumenJugador1.add(12); //n cartas
        resumenJugador1.add(3); //n oros
        resumenJugador1.add(1); //n sietes
        resumenJugador1.add(0); //puntos guindis
        resumenJugador1.add(0); //puntos juego
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertFalse(servicioPartida.validarPartida(partidaJugadorCartasDist));
    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida no valido oros obtenidos distintas jugador 1
     */
    @Tag("Cobertura")
    @Test
    void testValidarPartidaNoValidaCartasOrosObtenidasDistintasJugador1(){
        
        resumenJugador1.clear();
    
        resumenJugador1.add(0); //n escobas
        resumenJugador1.add(13); //n cartas
        resumenJugador1.add(2); //n oros
        resumenJugador1.add(1); //n sietes
        resumenJugador1.add(0); //puntos guindis
        resumenJugador1.add(0); //puntos juego
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertFalse(servicioPartida.validarPartida(partidaJugadorCartasDist));
    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida no valido sietes obtenidos distintas jugador 1
     */
    @Tag("Cobertura")
    @Test
    void testValidarPartidaNoValidaCartasSietesObtenidasDistintasJugador1(){
        
        resumenJugador1.clear();
    
        resumenJugador1.add(0); //n escobas
        resumenJugador1.add(13); //n cartas
        resumenJugador1.add(3); //n oros
        resumenJugador1.add(2); //n sietes
        resumenJugador1.add(0); //puntos guindis
        resumenJugador1.add(0); //puntos juego

        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertFalse(servicioPartida.validarPartida(partidaJugadorCartasDist));
    
    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida no valido guindis no lo tiene jugador 1
     */
    @Tag("Cobertura")
    @Test
    void testValidarPartidaNoValidaGuindisNoLoTieneJugador1(){
        
        resumenJugador1.clear();

        resumenJugador1.add(0); //n escobas
        resumenJugador1.add(13); //n cartas
        resumenJugador1.add(3); //n oros
        resumenJugador1.add(1); //n sietes
        resumenJugador1.add(1); //puntos guindis
        resumenJugador1.add(0); //puntos juego
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertFalse(servicioPartida.validarPartida(partidaJugadorCartasDist));
       
    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida no valido jugador 1 puntos
     */
    @Tag("Cobertura")
    @Test
    void testValidarPartidaNoValidaJugador1Puntos(){
        
        resumenJugador1.clear();
    
        resumenJugador1.add(0); //n escobas
        resumenJugador1.add(13); //n cartas
        resumenJugador1.add(3); //n oros
        resumenJugador1.add(1); //n sietes
        resumenJugador1.add(0); //puntos guindis
        resumenJugador1.add(1); //puntos juego
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertFalse(servicioPartida.validarPartida(partidaJugadorCartasDist));
       
    }

    /**
     * Test del metodo validarPartida de la clase GestorPartida no valido escobas distintas jugador 2
     */
    @Tag("Cobertura")
    @Test
    void testValidarPartidaNoValidaEscobasDistintasJugador2(){


        resumenJugador2.clear();
    
        resumenJugador2.add(2); //n escobas
        resumenJugador2.add(27); //n cartas
        resumenJugador2.add(7); //n oros
        resumenJugador2.add(3); //n sietes
        resumenJugador2.add(1); //puntos guindis
        resumenJugador2.add(5); //puntos juego
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertFalse(servicioPartida.validarPartida(partidaJugadorCartasDist));
    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida no valido cartas obtenidas distintas jugador 2
     */
    @Tag("Cobertura")
    @Test
    void testValidarPartidaNoValidaCartasObtenidasDistintasJugador2(){
        
        resumenJugador2.clear();
    
        resumenJugador2.add(1); //n escobas
        resumenJugador2.add(29); //n cartas
        resumenJugador2.add(7); //n oros
        resumenJugador2.add(3); //n sietes
        resumenJugador2.add(1); //puntos guindis
        resumenJugador2.add(5); //puntos juego
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertFalse(servicioPartida.validarPartida(partidaJugadorCartasDist));
       
    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida no valido oros obtenidos distintas jugador 2
     */
    @Tag("Cobertura")
    @Test
    void testValidarPartidaNoValidaCartasOrosObtenidasDistintasJugador2(){
        
        resumenJugador2.clear();
    
        resumenJugador2.add(1); //n escobas
        resumenJugador2.add(27); //n cartas
        resumenJugador2.add(4); //n oros
        resumenJugador2.add(3); //n sietes
        resumenJugador2.add(1); //puntos guindis
        resumenJugador2.add(5); //puntos juego
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertFalse(servicioPartida.validarPartida(partidaJugadorCartasDist));
       
    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida no valido oros obtenidos distintas jugador 2
     */
    @Tag("Cobertura")
    @Test
    void testValidarPartidaNoValidaCartasSietesObtenidasDistintasJugador2(){
        
        resumenJugador2.clear();
    
        resumenJugador2.add(1); //n escobas
        resumenJugador2.add(27); //n cartas
        resumenJugador2.add(7); //n oros
        resumenJugador2.add(5); //n sietes
        resumenJugador2.add(1); //puntos guindis
        resumenJugador2.add(5); //puntos juego
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertFalse(servicioPartida.validarPartida(partidaJugadorCartasDist));
       
    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida no valido guindis no lo tiene jugador 2
     */
    @Tag("Cobertura")
    @Test
    void testValidarPartidaNoValidaGuindisNoLoTieneJugador2(){
        
        resumenJugador2.clear();
    
        resumenJugador2.add(1); //n escobas
        resumenJugador2.add(27); //n cartas
        resumenJugador2.add(7); //n oros
        resumenJugador2.add(3); //n sietes
        resumenJugador2.add(0); //puntos guindis
        resumenJugador2.add(5); //puntos juego
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertFalse(servicioPartida.validarPartida(partidaJugadorCartasDist));
       
    }

    /** 
     * Test del metodo validarPartida de la clase GestorPartida no valido jugador 2 puntos
     */
    @Tag("Cobertura")
    @Test
    void testValidarPartidaNoValidaJugador2Puntos(){
        
        resumenJugador2.clear();
    
        resumenJugador2.add(1); //n escobas
        resumenJugador2.add(27); //n cartas
        resumenJugador2.add(7); //n oros
        resumenJugador2.add(3); //n sietes
        resumenJugador2.add(1); //puntos guindis
        resumenJugador2.add(4); //puntos juego
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        assertFalse(servicioPartida.validarPartida(partidaJugadorCartasDist));
       
    }

    @Tag("Cobertura")
    @Test
    void testObtenerEstadisticasJugador1Guindis(){
        // Configuración inicial
        nombreJugador1 = "Ana";
        nombreJugador2 = "Luis";
        rondas = new ArrayList<>();
        resumenJugador1 = new ArrayList<>();
        resumenJugador2 = new ArrayList<>();
        List<Carta> vacio = new ArrayList<>();

        // -- RONDA 1 --
        // IDs calculados de cartas.get(i) -> i + 1
        List<Carta> m1 = Arrays.asList(new Carta(27), new Carta(38), new Carta(27), new Carta(37));
        List<Carta> j1r1 = Arrays.asList(new Carta(7), new Carta(38), new Carta(18));
        List<Carta> j2r1 = Arrays.asList(new Carta(2), new Carta(33), new Carta(24));

        List<Jugada> jugR1 = new ArrayList<>();
        jugR1.add(new Jugada(j1r1.get(0), List.of(m1.get(1))));
        jugR1.add(new Jugada(j2r1.get(0), vacio));
        jugR1.add(new Jugada(j1r1.get(1), List.of(m1.get(2))));
        jugR1.add(new Jugada(j2r1.get(1), vacio));
        jugR1.add(new Jugada(j1r1.get(2), List.of(m1.get(3))));
        jugR1.add(new Jugada(j2r1.get(2), vacio));

        rondas.add(new Ronda(1, m1, j1r1, j2r1, jugR1));

        // -- RONDA 2 --
        List<Carta> m2 = Arrays.asList(new Carta(17), new Carta(2), new Carta(33), new Carta(24));
        List<Carta> j1r2 = Arrays.asList(new Carta(8), new Carta(11), new Carta(20));
        List<Carta> j2r2 = Arrays.asList(new Carta(36), new Carta(14), new Carta(15));

        List<Jugada> jugR2 = new ArrayList<>();
        jugR2.add(new Jugada(j1r2.get(0), List.of(m2.get(0))));
        jugR2.add(new Jugada(j2r2.get(0), List.of(m2.get(1), m2.get(2), m2.get(3))));
        jugR2.add(new Jugada(j1r2.get(1), vacio));
        jugR2.add(new Jugada(j2r2.get(1), vacio));
        jugR2.add(new Jugada(j1r2.get(2), List.of(j1r2.get(1), j2r2.get(1))));
        jugR2.add(new Jugada(j2r2.get(2), vacio));

        rondas.add(new Ronda(2, m2, j1r2, j2r2, jugR2));

        // -- RONDA 3 --
        List<Carta> m3 = Arrays.asList(new Carta(15));
        List<Carta> j1r3 = Arrays.asList(new Carta(12), new Carta(39), new Carta(29));
        List<Carta> j2r3 = Arrays.asList(new Carta(30), new Carta(25), new Carta(21));

        List<Jugada> jugR3 = new ArrayList<>();
        jugR3.add(new Jugada(j1r3.get(2), vacio));
        jugR3.add(new Jugada(j2r3.get(2), List.of(m3.get(0), j1r3.get(2))));
        jugR3.add(new Jugada(j1r3.get(1), vacio));
        jugR3.add(new Jugada(j2r3.get(0), vacio));
        jugR3.add(new Jugada(j1r3.get(0), vacio));
        jugR3.add(new Jugada(j2r3.get(1), List.of(j2r3.get(0))));

        rondas.add(new Ronda(3, m3, j1r3, j2r3, jugR3));

        // -- RONDA 4 --
        List<Carta> m4 = Arrays.asList(new Carta(39), new Carta(12));
        List<Carta> j1r4 = Arrays.asList(new Carta(13), new Carta(16), new Carta(9));
        List<Carta> j2r4 = Arrays.asList(new Carta(32), new Carta(22), new Carta(10));

        List<Jugada> jugR4 = new ArrayList<>();
        jugR4.add(new Jugada(j1r4.get(1), List.of(m4.get(0))));
        jugR4.add(new Jugada(j2r4.get(0), vacio));
        jugR4.add(new Jugada(j1r4.get(0), vacio));
        jugR4.add(new Jugada(j2r4.get(1), vacio));
        jugR4.add(new Jugada(j1r4.get(2), List.of(m4.get(1), j2r4.get(0), j2r4.get(1))));
        jugR4.add(new Jugada(j2r4.get(2), vacio));

        rondas.add(new Ronda(4, m4, j1r4, j2r4, jugR4));

        // -- RONDA 5 --
        List<Carta> m5 = Arrays.asList(new Carta(13), new Carta(20));
        List<Carta> j1r5 = Arrays.asList(new Carta(29), new Carta(35), new Carta(40));
        List<Carta> j2r5 = Arrays.asList(new Carta(34), new Carta(26), new Carta(4));

        List<Jugada> jugR5 = new ArrayList<>();
        jugR5.add(new Jugada(j1r5.get(1), List.of(m5.get(1))));
        jugR5.add(new Jugada(j2r5.get(1), vacio));
        jugR5.add(new Jugada(j1r5.get(0), List.of(j2r5.get(1))));
        jugR5.add(new Jugada(j2r5.get(0), vacio));
        jugR5.add(new Jugada(j1r5.get(2), vacio));
        jugR5.add(new Jugada(j2r5.get(2), vacio));

        rondas.add(new Ronda(5, m5, j1r5, j2r5, jugR5));

        // -- RONDA 6 --
        List<Carta> m6 = Arrays.asList(new Carta(13), new Carta(34), new Carta(40), new Carta(4));
        List<Carta> j1r6 = Arrays.asList(new Carta(31), new Carta(5), new Carta(1));
        List<Carta> j2r6 = Arrays.asList(new Carta(3), new Carta(6), new Carta(23));

        List<Jugada> jugR6 = new ArrayList<>();
        jugR6.add(new Jugada(j1r6.get(0), List.of(m6.get(2), m6.get(1))));
        jugR6.add(new Jugada(j2r6.get(2), vacio));
        jugR6.add(new Jugada(j1r6.get(1), List.of(m6.get(0), m6.get(3), j2r6.get(2))));
        jugR6.add(new Jugada(j2r6.get(0), vacio));
        jugR6.add(new Jugada(j1r6.get(2), vacio));
        jugR6.add(new Jugada(j2r6.get(1), vacio));

        rondas.add(new Ronda(6, m6, j1r6, j2r6, jugR6));

        // Configuración de Resúmenes y Partida
        resumenJugador1.addAll(Arrays.asList(2, 31, 9, 4, 1, 8)); // 1 punto de guindis en índice 4
        resumenJugador2.addAll(Arrays.asList(2, 9, 1, 0, 0, 2));

        Partida partidaJson3 = new Partida(nombreJugador1, nombreJugador1, nombreJugador2, rondas, resumenJugador1, resumenJugador2);
        List<Partida> listaPartidasConGuindisJugador1 = new ArrayList<>(List.of(partidaJson3));

        // Mock y Verificación
        EasyMock.expect(repositorio.obtenerPartidasPorJugador(nombreJugador1)).andReturn(listaPartidasConGuindisJugador1).times(12);
        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        assertEquals(1, servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[0]);
        assertEquals(1, servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[1]);
        assertEquals(0, servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[2]);
        assertEquals(1, servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[3]);

        EasyMock.verify(repositorio);
    }

    /** 
     * Test del metodo obtenerEstadisticasJugador de la clase GestorPartida valido jugador 2 sin guindis
     */
    @Tag("Cobertura")
    @Test
    void testObtenerEstadisticasJugador2NoTieneGuindisGuindis(){

        List<Partida> listaTest=new ArrayList<>();

        partidaOtra=new Partida("OtraPartida", nombreJugador2,nombreJugador1, rondas , resumenJugador2, resumenJugador1);

        listaTest.add(partidaOtra);

        EasyMock.expect(repositorio.obtenerPartidasPorJugador(nombreJugador1)).andReturn(listaTest).times(12);

        EasyMock.replay(repositorio);

        servicioPartida.setRepositorioPartida(repositorio);

        assertEquals(1,servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[0]);
        assertEquals(0,servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[1]);
        assertEquals(1,servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[2]);
        assertEquals(0,servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[3]);


        EasyMock.verify(repositorio);


    }
}
