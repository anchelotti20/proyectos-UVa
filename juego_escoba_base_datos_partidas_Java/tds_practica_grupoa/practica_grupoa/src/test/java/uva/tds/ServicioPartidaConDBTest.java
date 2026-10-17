package uva.tds;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServicioPartidaConDBTest {
    
    
    private ServicioPartida servicioPartida;

    private static IRepositorioPartida repositorio;


    private static final String CONFIG_FILE = "hibernate-test.cfg.xml";

    // Atributos auxiliares
    private Partida partida;
    private Partida partidaOtra;
    private List<Integer> resumenJugador1;
    private List<Integer> resumenJugador2;

    
    // Listas de datos
    private List<Partida> listaPartidasJugador1;
    private List<Partida> listaPartidasFecha;
    private List<Partida> listaPartidas10; // Contendrá las 55 partidas
    private Map<Integer, Carta> mapaCartas;

    // Nombres
    private String nombreJugador1;
    private String nombreJugador2;


    @BeforeAll
    void setUpAll(){

        repositorio = new RepositorioPartida(CONFIG_FILE);
        
    }

    @BeforeEach
    void setUp() throws Exception {
        
        Session session = HibernateUtil.getSessionFactory(CONFIG_FILE).openSession();

        try{

            session.beginTransaction();

            session.createNativeQuery("DELETE FROM Resumen_Jugador1").executeUpdate();
            session.createNativeQuery("DELETE FROM Resumen_Jugador2").executeUpdate();
            session.createNativeQuery("DELETE FROM Jugada_CartasCapturadas").executeUpdate();
            session.createNativeQuery("DELETE FROM Ronda_MesaInicial").executeUpdate();
            session.createNativeQuery("DELETE FROM Ronda_ManoJugador1").executeUpdate();
            session.createNativeQuery("DELETE FROM Ronda_ManoJugador2").executeUpdate();
            session.createQuery("DELETE FROM Jugada").executeUpdate();
            session.createQuery("DELETE FROM Ronda").executeUpdate(); 
            session.createQuery("DELETE FROM Partida").executeUpdate();
            session.createQuery("DELETE FROM Carta").executeUpdate();

            session.getTransaction().commit();
        }catch(Exception e){

            e.printStackTrace();
            session.getTransaction().rollback();
        
        } finally {
            
            session.close();
        }


        servicioPartida=new ServicioPartida();
        servicioPartida.setRepositorioPartida(repositorio);
        resumenJugador1=new ArrayList<>(Arrays.asList(0, 13, 3, 1, 0, 0));
        resumenJugador2=new ArrayList<>(Arrays.asList(1, 27, 7, 3, 1, 5));
        mapaCartas=new HashMap<>();
        nombreJugador1 = "Ana";
        nombreJugador2 = "Luis";

        
        partida = new Partida("partida", nombreJugador1, nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);

        

      
        partidaOtra=new Partida("OtraPartida", nombreJugador2,nombreJugador1, crearNuevasRondas(), resumenJugador1, resumenJugador2);

        generarPartidasTop10();

        listaPartidasJugador1=new ArrayList<>(List.of(partida, partidaOtra));
        
        listaPartidasFecha=new ArrayList<>(List.of(partida, partidaOtra));


    }

    @AfterAll
    void tearDownAll(){
        HibernateUtil.shutdown();
    }


    /**
     * Metodo auxiliar para obtener una carta por ID desde el mapa de cartas
     * @param id El identificador de la carta
     * @return La carta correspondiente al ID
     */
    private Carta getCartaPorId(int id) {
        
        if(!mapaCartas.containsKey(id)){

            mapaCartas.put(id, new Carta(id));

        }
        return mapaCartas.get(id);
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
                listaPartidas10.add(new Partida("partida_" + idCounter++,"Latasa", rival, crearNuevasRondas(), resumenJugador1, resumenJugador2));
            }
        }
    }

    /**
     * Metodo auxiliar para crear nuevas rondas para las partidas de test
     * @return Lista de rondas configuradas
     */
    private List<Ronda> crearNuevasRondas() {
        
        List<Ronda> rondas = new ArrayList<>();
        List<Carta> vacio = new ArrayList<>();


        // -- RONDA 1 --
        List<Carta> mesa1 = new ArrayList<>(Arrays.asList(getCartaPorId(5), getCartaPorId(9), getCartaPorId(38), getCartaPorId(39))); // ids originales: 4, 8, 37, 38 -> cartas: 5, 9, sota bastos, caballo bastos
        List<Carta> manoJ1R1 = new ArrayList<>(Arrays.asList(getCartaPorId(11), getCartaPorId(14), getCartaPorId(29)));
        List<Carta> manoJ2R1 = new ArrayList<>(Arrays.asList(getCartaPorId(7), getCartaPorId(12), getCartaPorId(3)));
        
        List<Jugada> jugadasR1 = new ArrayList<>();
        jugadasR1.add(new Jugada(manoJ1R1.get(0), List.of(mesa1.get(0), mesa1.get(1))));
        jugadasR1.add(new Jugada(manoJ2R1.get(0), List.of(mesa1.get(2))));
        jugadasR1.add(new Jugada(manoJ1R1.get(2), vacio));
        jugadasR1.add(new Jugada(manoJ2R1.get(1), vacio));
        jugadasR1.add(new Jugada(manoJ1R1.get(1), List.of(manoJ1R1.get(2), manoJ2R1.get(1)))); // Nota: lógica original mantenida aunque extraña
        jugadasR1.add(new Jugada(manoJ2R1.get(2), vacio));
        
        rondas.add(new Ronda(1, mesa1, manoJ1R1, manoJ2R1, jugadasR1));

        // -- RONDA 2 --
        List<Carta> mesa2 = new ArrayList<>(Arrays.asList(getCartaPorId(39), getCartaPorId(3))); 
        List<Carta> manoJ1R2 = new ArrayList<>(Arrays.asList(getCartaPorId(22), getCartaPorId(25), getCartaPorId(28)));
        List<Carta> manoJ2R2 = new ArrayList<>(Arrays.asList(getCartaPorId(24), getCartaPorId(6), getCartaPorId(36)));

        List<Jugada> jugadasR2 = new ArrayList<>();
        jugadasR2.add(new Jugada(manoJ1R2.get(2), vacio));
        jugadasR2.add(new Jugada(manoJ2R2.get(0), List.of(mesa2.get(1), manoJ1R2.get(2))));
        jugadasR2.add(new Jugada(manoJ1R2.get(1), vacio));
        jugadasR2.add(new Jugada(manoJ2R2.get(1), List.of(mesa2.get(0))));
        jugadasR2.add(new Jugada(manoJ1R2.get(0), vacio));
        jugadasR2.add(new Jugada(manoJ2R2.get(2), vacio));

        rondas.add(new Ronda(2, mesa2, manoJ1R2, manoJ2R2, jugadasR2));

        // -- RONDA 3 --
        List<Carta> mesa3 = new ArrayList<>(Arrays.asList(getCartaPorId(25), getCartaPorId(22), getCartaPorId(36)));
        List<Carta> manoJ1R3 = new ArrayList<>(Arrays.asList(getCartaPorId(21), getCartaPorId(26), getCartaPorId(34)));
        List<Carta> manoJ2R3 = new ArrayList<>(Arrays.asList(getCartaPorId(35), getCartaPorId(15), getCartaPorId(33)));

        List<Jugada> jugadasR3 = new ArrayList<>();
        jugadasR3.add(new Jugada(manoJ1R3.get(2), List.of(mesa3.get(0), mesa3.get(2))));
        jugadasR3.add(new Jugada(manoJ2R3.get(0), vacio));
        jugadasR3.add(new Jugada(manoJ1R3.get(1), vacio));
        jugadasR3.add(new Jugada(manoJ2R3.get(1), vacio));
        jugadasR3.add(new Jugada(manoJ1R3.get(0), vacio));
        jugadasR3.add(new Jugada(manoJ2R3.get(2), List.of(manoJ1R3.get(1), manoJ2R3.get(1), manoJ1R3.get(0))));

        rondas.add(new Ronda(3, mesa3, manoJ1R3, manoJ2R3, jugadasR3));

        // -- RONDA 4 --
        List<Carta> mesa4 = new ArrayList<>(Arrays.asList(getCartaPorId(22), getCartaPorId(35)));
        List<Carta> manoJ1R4 = new ArrayList<>(Arrays.asList(getCartaPorId(13), getCartaPorId(20), getCartaPorId(32)));
        List<Carta> manoJ2R4 = new ArrayList<>(Arrays.asList(getCartaPorId(19), getCartaPorId(4), getCartaPorId(1)));

        List<Jugada> jugadasR4 = new ArrayList<>();
        jugadasR4.add(new Jugada(manoJ1R4.get(1), List.of(mesa4.get(1))));
        jugadasR4.add(new Jugada(manoJ2R4.get(0), vacio));
        jugadasR4.add(new Jugada(manoJ1R4.get(2), vacio));
        jugadasR4.add(new Jugada(manoJ2R4.get(1), List.of(mesa4.get(0), manoJ2R4.get(0))));
        jugadasR4.add(new Jugada(manoJ1R4.get(0), vacio));
        jugadasR4.add(new Jugada(manoJ2R4.get(2), vacio));

        rondas.add(new Ronda(4, mesa4, manoJ1R4, manoJ2R4, jugadasR4));

        // -- RONDA 5 --
        List<Carta> mesa5 = new ArrayList<>(Arrays.asList(getCartaPorId(32), getCartaPorId(13), getCartaPorId(1)));
        List<Carta> manoJ1R5 = new ArrayList<>(Arrays.asList(getCartaPorId(16), getCartaPorId(17), getCartaPorId(2)));
        List<Carta> manoJ2R5 = new ArrayList<>(Arrays.asList(getCartaPorId(27), getCartaPorId(8), getCartaPorId(31)));

        List<Jugada> jugadasR5 = new ArrayList<>();
        jugadasR5.add(new Jugada(manoJ1R5.get(2), vacio));
        jugadasR5.add(new Jugada(manoJ2R5.get(0), List.of(mesa5.get(0), mesa5.get(1), mesa5.get(2), manoJ1R5.get(2))));
        jugadasR5.add(new Jugada(manoJ1R5.get(0), vacio));
        jugadasR5.add(new Jugada(manoJ2R5.get(1), vacio));
        jugadasR5.add(new Jugada(manoJ1R5.get(1), List.of(manoJ2R5.get(1))));
        jugadasR5.add(new Jugada(manoJ2R5.get(2), vacio));

        rondas.add(new Ronda(5, mesa5, manoJ1R5, manoJ2R5, jugadasR5));

        // -- RONDA 6 --
        List<Carta> mesa6 = new ArrayList<>(Arrays.asList(getCartaPorId(16), getCartaPorId(31)));
        List<Carta> manoJ1R6 = new ArrayList<>(Arrays.asList(getCartaPorId(23), getCartaPorId(30), getCartaPorId(10)));
        List<Carta> manoJ2R6 = new ArrayList<>(Arrays.asList(getCartaPorId(18), getCartaPorId(40), getCartaPorId(37)));

        List<Jugada> jugadasR6 = new ArrayList<>();
        jugadasR6.add(new Jugada(manoJ1R6.get(1), vacio));
        jugadasR6.add(new Jugada(manoJ2R6.get(0), List.of(mesa6.get(0), mesa6.get(1))));
        jugadasR6.add(new Jugada(manoJ1R6.get(2), vacio));
        jugadasR6.add(new Jugada(manoJ2R6.get(1), vacio));
        jugadasR6.add(new Jugada(manoJ1R6.get(0), vacio));
        jugadasR6.add(new Jugada(manoJ2R6.get(2), vacio));

        rondas.add(new Ronda(6, mesa6, manoJ1R6, manoJ2R6, jugadasR6));

        return rondas;
    }

    /** 
     * Test del metodo cargarPartida de la clase GestorPartida valido correcto
     */
    @Test
    void testCargarPartidaCorrecta(){

        servicioPartida.guardarPartida(partida);

        assertEquals(partida,servicioPartida.cargarPartida("partida"));

    }

    /** 
     * Test del metodo cargarPartida de la clase GestorPartida no valido no existe identificador
     */
    @Test
    void testCargarPartidaNoValidoNoExisteIdentificador(){

        servicioPartida.guardarPartida(partida);
        assertThrows(IllegalStateException.class,()->{

            servicioPartida.cargarPartida("otroNombre");
        });

    }

    /**
     * Test del metodo cargarPartida de la clase GestorPartida no valido identificador nulo
     */
    @Test
    void testCargarPartidaNoValidaIdentifcadorNulo(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.cargarPartida(null);
        });

    }

    /**
     * Test del metodo cargarPartida de la clase GestorPartida no valido identificador vacio
     */
    @Test
    void testCargarPartidaNoValidaIdentificadorVacio(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.cargarPartida("");
        });


    }


    /**
     * Test del metodo guardarPartida de la clase GestorPartida valido
     */
    @Test
    void testGuardarPartidaValido(){


        servicioPartida.guardarPartida(partidaOtra);

        assertEquals(partidaOtra,servicioPartida.cargarPartida(partidaOtra.getIdPartida()));
    


    }


    /**
     * Test del metodo guardarPartida de la clase GestorPartida no valido partida nula
     */
    @Test
    void testGuardarPartidaNoValidoPartidaNulo(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.guardarPartida(null);
        });


    }

    /**
     * Test del metodo guardarPartida de la clase GestorPartida no valido partida con mismo ID
     */
    @Test
    void testGuardarPartidaNoValidoPartidaConMismoID(){

        Partida partida2 = new Partida("partida", nombreJugador1,nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);

        servicioPartida.guardarPartida(partida);

        assertThrows(IllegalStateException.class,()->{

            servicioPartida.guardarPartida(partida2);
        });


    }

    /**
     * Test del metodo actualizarPartida de la clase GestorPartida valido
     */
    @Test
    void testActualizarPartidaValido(){

        Partida partida2 = new Partida("partida", nombreJugador2,nombreJugador1, crearNuevasRondas(), resumenJugador1, resumenJugador2);
    
        servicioPartida.guardarPartida(partida);

        servicioPartida.actualizarPartida(partida2);


        Partida partidaCargada = servicioPartida.cargarPartida(partida2.getIdPartida());

        assertEquals(partida2.getNombreJugador1(), partidaCargada.getNombreJugador1());

    }
    
    /**
     * Test del metodo actualizarPartida de la clase GestorPartida no valido partida nula
     */
    @Test
    void testActualizarPartidaNoValidoPartidaNula(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.actualizarPartida(null);
        });


    }


    /**
     * Test del metodo actualizarPartida de la clase GestorPartida no valido partida no existe ID
     */
    @Test
    void testActualizarPartidaNoValidoPartidaNoExisteID(){

        Partida partida2=new Partida("otroID", nombreJugador1,nombreJugador2, crearNuevasRondas() , resumenJugador1, resumenJugador2);

        

        servicioPartida.guardarPartida(partida);

        assertThrows(IllegalStateException.class,()->{
            
            servicioPartida.actualizarPartida(partida2);
        });

        

    }

    /**
     * Test del metodo eliminarPartida de la clase GestorPartida valido
     */
    @Test
    void testEliminarPartidaValido(){

        servicioPartida.guardarPartida(partida);

        

        servicioPartida.eliminarPartida(partida.getIdPartida());

        String idPartida=partida.getIdPartida();

        
        assertThrows(IllegalStateException.class,()->{

            servicioPartida.cargarPartida(idPartida);

        });

    }

    /**
     * Test del metodo eliminarPartida de la clase GestorPartida no valido ID nulo
     */
    @Test
    void testEliminarPartidaNoValidoIDNulo(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.eliminarPartida(null);

        });


    }

    /**
     * Test del metodo eliminarPartida de la clase GestorPartida no valido ID vacio
     */
    @Test
    void testEliminarPartidaNoValidoIDVacio(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.eliminarPartida("");

        });


    }

    /**
     * Test del metodo eliminarPartida de la clase GestorPartida no valido no existe ID
     */
    @Test
    void testEliminarPartidaNoValidoNoExisteID(){

        servicioPartida.guardarPartida(partida);


        assertThrows(IllegalStateException.class,()->{

            servicioPartida.eliminarPartida("esteIdNoExiste");

        });

    }

    /** 
     * Test del metodo obtenerPartidasJugador de la clase GestorPartida valido
     */
    @Test
    void testObtenerPartidasJugador(){

        
        servicioPartida.guardarPartida(partida);
        servicioPartida.guardarPartida(partidaOtra);
        
        
        
        
        assertEquals(listaPartidasJugador1.size(), servicioPartida.obtenerPartidasJugador(nombreJugador1).size());
        assertTrue(servicioPartida.obtenerPartidasJugador(nombreJugador1).containsAll(listaPartidasJugador1));


    }

    /** 
     * Test del metodo obtenerPartidasJugador de la clase GestorPartida valido jugador 2
     */
    @Tag("Cobertura")
    @Test
    void testObtenerPartidasJugador2(){

        servicioPartida.guardarPartida(partida);
        servicioPartida.guardarPartida(partidaOtra);
        

        assertEquals(0,servicioPartida.obtenerPartidasJugador(listaPartidasJugador1, "Pedro"));


    }

    /** 
     * Test del metodo obtenerPartidasJugador de la clase GestorPartida no valido nombre jugador nulo
     */
    @Test
    void testObtenerPartidasJugadorNoValidoNombreJugadorNulo(){
        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerPartidasJugador(null);

        });

        

    }

    /** 
     * Test del metodo obtenerPartidasJugador de la clase GestorPartida no valido nombre jugador vacio
     */
    @Test
    void testObtenerPartidasJugadorNoValidoNombreJugadorVacio(){


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerPartidasJugador("");

        });


    }

    /** 
     * Test del metodo obtenerPartidasFecha de la clase GestorPartida valido
     */
    @Test
    void testObtenerPartidasFechaValido(){

        
        servicioPartida.guardarPartida(partida);
        servicioPartida.guardarPartida(partidaOtra);
        

        assertEquals(listaPartidasFecha.size(), servicioPartida.obtenerPartidasFecha("2025-04-04", "2026-06-12").size());
        assertTrue(servicioPartida.obtenerPartidasFecha("2025-04-04", "2026-06-12").containsAll(listaPartidasFecha));

        

    }

    /** 
     * Test del metodo obtenerPartidasFecha de la clase GestorPartida no valido repositorio nulo
     */
    @Test
    void testObtenerPartidasFechaNoValidoFechaInicialNull(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerPartidasFecha(null, "2005-06-12");

        });

       


    }

    /** 
     * Test del metodo obtenerPartidasFecha de la clase GestorPartida no valido fecha final nulo
     */
    @Test
    void testObtenerPartidasFechaNoValidoFechaFinalNulo(){


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerPartidasFecha("2025-04-04", null);

        });


    }


    /** 
     * Test del metodo obtenerPartidasFecha de la clase GestorPartida no valido fecha formato no valido
     */
    @Test
    void testObtenerPartidasFechaNoValidoFormatoNoValido(){


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerPartidasFecha("25-4-04", "10-2-3");

        });


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
        Partida partidaInvalida=new Partida("Prueba2", nombreJugador1,nombreJugador2, crearNuevasRondas() , resumenJugador2, resumenJugador1);
        assertFalse(servicioPartida.validarPartida(partidaInvalida));

    }

    /** 
     * Test del metodo obtenerEstadisticasEntreFechas de la clase GestorPartida valido
     */
    @Test
    void testObtenerEstadisticasEntreFechas(){

        servicioPartida.guardarPartida(partida);

        servicioPartida.guardarPartida(partidaOtra);

        assertEquals(listaPartidasFecha.size(),servicioPartida.obtenerEstadisticasEntreFechas("2025-04-04", "2026-06-12")[0]);
        assertEquals(2, servicioPartida.obtenerEstadisticasEntreFechas("2025-04-04", "2026-06-12")[1]);
        
    }

    /** 
     * Test del metodo obtenerEstadisticasEntreFechas de la clase GestorPartida no valido fecha inicial nula
     */
    @Test
    void testObtenerEstadisticasNoValidoFechaInicialNull(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas(null,"2005-06-12");

        });


    }

    /** 
     * Test del metodo obtenerEstadisticasEntreFechas de la clase GestorPartida no valido fecha inicial vacia
     */
    @Test
    void testObtenerEstadisticasNoValidoFechaFinalNull(){

        
        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2005-06-12",null);
        });


    }

    /** 
     * Test del metodo obtenerEstadisticasEntreFechas de la clase GestorPartida no valido fecha final vacia
     */
    @Test
    void testObtenerEstadisticasNoValidoFechaFinalVacia(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2005-06-12","");
        });


    }

    /** 
     * Test del metodo obtenerEstadisticasEntreFechas de la clase GestorPartida no valido fecha inicial vacia
     */
    @Test
    void testObtenerEstadisticasNoValidoFechaInicialVacio(){


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("","2005-06-12");
        });

    }

    @Test
    void testObtenerEstadisticasNoValidoFormatoNoValidoFaltaUnaParte(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2025-04","2005-06-12");
        });
    }

    @Test
    void testObtenerEstadisticasNoValidoFormatoNoValidoAniooMal(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("202-4-04","2005-06-12");
        });
    }

    @Test
    void testObtenerEstadisticasNoValidoFormatoNoValidoMesMal(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2025-4-04","2005-06-12");
        });
    }

    @Test
    void testObtenerEstadisticasNoValidoFormatoNoValidoDiaMal(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2025-04-4","2005-06-12");
        });
    }

    @Test
    void testObtenerEstadisticasNoValidoFormatoNoValidoSeparadoresMal(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2025/04/04","2005-06-12");
        });
    }

    @Test
    void testObtenerEstadisticasNoValidoFormatoNoValidoNoNumeros(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("ABCD-04-04","2005-06-12");
        });
    }

    @Test
    void testObtenerEstadisticasNoValidoFormatoNoValidoMesInvalidoLimiteInferior(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2025-00-04","2005-06-12");
        });
    }

    @Test
    void testObtenerEstadisticasNoValidoFormatoNoValidoMesInvalidoLimiteSuperior(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2025-13-04","2005-06-12");
        });
    }

    @Test
    void testObtenerEstadisticasNoValidoFormatoNoValidoDiaInvalidoLimiteInferior(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2025-04-00","2005-06-12");
        });
    }

    @Test
    void testObtenerEstadisticasNoValidoFormatoNoValidoDiaInvalidoLimiteSuperior(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasEntreFechas("2025-04-32","2005-06-12");
        });
    }


    /** 
     * Test del metodo obtenerEstadisticasJugador de la clase GestorPartida valido
     */
    @Test
    void testObtenerEstadisticasJugador(){


        servicioPartida.guardarPartida(partida);
        servicioPartida.guardarPartida(partidaOtra);


        assertEquals(2,servicioPartida.obtenerEstadisticasJugador("Luis")[0]);
        assertEquals(1,servicioPartida.obtenerEstadisticasJugador("Luis")[1]);
        assertEquals(1,servicioPartida.obtenerEstadisticasJugador("Luis")[2]);
        assertEquals(1,servicioPartida.obtenerEstadisticasJugador("Luis")[3]);


    }


    /** 
     * Test del metodo obtenerEstadisticasJugador de la clase GestorPartida valido jugador 2
     */
    @Tag("Cobertura")
    @Test
    void testObtenerEstadisticasJugador2(){

        servicioPartida.guardarPartida(partidaOtra);

        
        assertEquals(1,servicioPartida.obtenerEstadisticasJugador("Luis")[0]);
        assertEquals(0,servicioPartida.obtenerEstadisticasJugador("Luis")[1]);
        assertEquals(1,servicioPartida.obtenerEstadisticasJugador("Luis")[2]);
        assertEquals(0,servicioPartida.obtenerEstadisticasJugador("Luis")[3]);



    }


    /** 
     * Test del metodo obtenerEstadisticasJugador de la clase GestorPartida no valido jugador nulo
     */
    @Test
    void testObtenerEstadisticasJugadorNoValidoNulo(){

        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasJugador(null);

        });



    }

    /** 
     * Test del metodo obtenerEstadisticasJugador de la clase GestorPartida no valido jugador vacio
     */
    @Test
    void testObtenerEstadisticasJugadorNovalidoVacio(){


        assertThrows(IllegalArgumentException.class,()->{

            servicioPartida.obtenerEstadisticasJugador("");
        });

    }


    /** 
     * Test del metodo obtenerTop10 de la clase GestorPartida valido
     */
    @Test
    void testObtenerTop10(){

        for(Partida p : listaPartidas10){
            servicioPartida.guardarPartida(p);
        }

        List<String> top=new ArrayList<>(Arrays.asList("Marcos - 10 victorias","Angel - 9 victorias","Manuel - 8 victorias","Marisa - 7 victorias","Fernando - 6 victorias","Alex - 5 victorias","Carla - 4 victorias","Luisa - 3 victorias","Luis - 2 victorias","Ana - 1 victorias"));

        assertEquals(10,servicioPartida.obtenerTop10().size());
        assertTrue(servicioPartida.obtenerTop10().containsAll(top));
        

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
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);
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
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);
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

        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);
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
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);
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
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);
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
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);
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
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);
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
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);
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
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);
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
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);
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
        
        Partida partidaJugadorCartasDist = new Partida("otra", nombreJugador1, nombreJugador2, crearNuevasRondas(), resumenJugador1, resumenJugador2);
        assertFalse(servicioPartida.validarPartida(partidaJugadorCartasDist));
       
    }

    @Tag("Cobertura")
    @Test
    void testObtenerEstadisticasJugador1Guindis(){
        // Configuración inicial
        nombreJugador1 = "Ana";
        nombreJugador2 = "Luis";
        List<Ronda> rondas = new ArrayList<>();
        resumenJugador1 = new ArrayList<>();
        resumenJugador2 = new ArrayList<>();
        List<Carta> vacio = new ArrayList<>();

        // -- RONDA 1 --
        // IDs calculados de cartas.get(i) -> i + 1
        List<Carta> m1 = Arrays.asList(getCartaPorId(27), getCartaPorId(38), getCartaPorId(27), getCartaPorId(37));
        List<Carta> j1r1 = Arrays.asList(getCartaPorId(7), getCartaPorId(38), getCartaPorId(18));
        List<Carta> j2r1 = Arrays.asList(getCartaPorId(2), getCartaPorId(33), getCartaPorId(24));

        List<Jugada> jugR1 = new ArrayList<>();
        jugR1.add(new Jugada(j1r1.get(0), List.of(m1.get(1))));
        jugR1.add(new Jugada(j2r1.get(0), vacio));
        jugR1.add(new Jugada(j1r1.get(1), List.of(m1.get(2))));
        jugR1.add(new Jugada(j2r1.get(1), vacio));
        jugR1.add(new Jugada(j1r1.get(2), List.of(m1.get(3))));
        jugR1.add(new Jugada(j2r1.get(2), vacio));

        rondas.add(new Ronda(1, m1, j1r1, j2r1, jugR1));

        // -- RONDA 2 --
        List<Carta> m2 = Arrays.asList(getCartaPorId(17), getCartaPorId(2), getCartaPorId(33), getCartaPorId(24));
        List<Carta> j1r2 = Arrays.asList(getCartaPorId(8), getCartaPorId(11), getCartaPorId(20));
        List<Carta> j2r2 = Arrays.asList(getCartaPorId(36), getCartaPorId(14), getCartaPorId(15));

        List<Jugada> jugR2 = new ArrayList<>();
        jugR2.add(new Jugada(j1r2.get(0), List.of(m2.get(0))));
        jugR2.add(new Jugada(j2r2.get(0), List.of(m2.get(1), m2.get(2), m2.get(3))));
        jugR2.add(new Jugada(j1r2.get(1), vacio));
        jugR2.add(new Jugada(j2r2.get(1), vacio));
        jugR2.add(new Jugada(j1r2.get(2), List.of(j1r2.get(1), j2r2.get(1))));
        jugR2.add(new Jugada(j2r2.get(2), vacio));

        rondas.add(new Ronda(2, m2, j1r2, j2r2, jugR2));

        // -- RONDA 3 --
        List<Carta> m3 = Arrays.asList(getCartaPorId(15));
        List<Carta> j1r3 = Arrays.asList(getCartaPorId(12), getCartaPorId(39), getCartaPorId(29));
        List<Carta> j2r3 = Arrays.asList(getCartaPorId(30), getCartaPorId(25), getCartaPorId(21));

        List<Jugada> jugR3 = new ArrayList<>();
        jugR3.add(new Jugada(j1r3.get(2), vacio));
        jugR3.add(new Jugada(j2r3.get(2), List.of(m3.get(0), j1r3.get(2))));
        jugR3.add(new Jugada(j1r3.get(1), vacio));
        jugR3.add(new Jugada(j2r3.get(0), vacio));
        jugR3.add(new Jugada(j1r3.get(0), vacio));
        jugR3.add(new Jugada(j2r3.get(1), List.of(j2r3.get(0))));

        rondas.add(new Ronda(3, m3, j1r3, j2r3, jugR3));

        // -- RONDA 4 --
        List<Carta> m4 = Arrays.asList(getCartaPorId(39), getCartaPorId(12));
        List<Carta> j1r4 = Arrays.asList(getCartaPorId(13), getCartaPorId(16), getCartaPorId(9));
        List<Carta> j2r4 = Arrays.asList(getCartaPorId(32), getCartaPorId(22), getCartaPorId(10));

        List<Jugada> jugR4 = new ArrayList<>();
        jugR4.add(new Jugada(j1r4.get(1), List.of(m4.get(0))));
        jugR4.add(new Jugada(j2r4.get(0), vacio));
        jugR4.add(new Jugada(j1r4.get(0), vacio));
        jugR4.add(new Jugada(j2r4.get(1), vacio));
        jugR4.add(new Jugada(j1r4.get(2), List.of(m4.get(1), j2r4.get(0), j2r4.get(1))));
        jugR4.add(new Jugada(j2r4.get(2), vacio));

        rondas.add(new Ronda(4, m4, j1r4, j2r4, jugR4));

        // -- RONDA 5 --
        List<Carta> m5 = Arrays.asList(getCartaPorId(13), getCartaPorId(20));
        List<Carta> j1r5 = Arrays.asList(getCartaPorId(29), getCartaPorId(35), getCartaPorId(40));
        List<Carta> j2r5 = Arrays.asList(getCartaPorId(34), getCartaPorId(26), getCartaPorId(4));

        List<Jugada> jugR5 = new ArrayList<>();
        jugR5.add(new Jugada(j1r5.get(1), List.of(m5.get(1))));
        jugR5.add(new Jugada(j2r5.get(1), vacio));
        jugR5.add(new Jugada(j1r5.get(0), List.of(j2r5.get(1))));
        jugR5.add(new Jugada(j2r5.get(0), vacio));
        jugR5.add(new Jugada(j1r5.get(2), vacio));
        jugR5.add(new Jugada(j2r5.get(2), vacio));

        rondas.add(new Ronda(5, m5, j1r5, j2r5, jugR5));

        // -- RONDA 6 --
        List<Carta> m6 = Arrays.asList(getCartaPorId(13), getCartaPorId(34), getCartaPorId(40), getCartaPorId(4));
        List<Carta> j1r6 = Arrays.asList(getCartaPorId(31), getCartaPorId(5), getCartaPorId(1));
        List<Carta> j2r6 = Arrays.asList(getCartaPorId(3), getCartaPorId(6), getCartaPorId(23));

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
        
        servicioPartida.guardarPartida(partidaJson3);

        assertEquals(1, servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[0]);
        assertEquals(1, servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[1]);
        assertEquals(0, servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[2]);
        assertEquals(1, servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[3]);

    }

    /** 
     * Test del metodo obtenerEstadisticasJugador de la clase GestorPartida valido jugador 2 sin guindis
     */
    @Tag("Cobertura")
    @Test
    void testObtenerEstadisticasJugador2NoTieneGuindisGuindis(){

        partidaOtra=new Partida("OtraPartida", nombreJugador2,nombreJugador1, crearNuevasRondas() , resumenJugador2, resumenJugador1);

        servicioPartida.guardarPartida(partidaOtra);

        assertEquals(1,servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[0]);
        assertEquals(0,servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[1]);
        assertEquals(1,servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[2]);
        assertEquals(0,servicioPartida.obtenerEstadisticasJugador(nombreJugador1)[3]);



    }
}
