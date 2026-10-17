package uva.tds;

/**
 * Clase de test para la clase ServicioJuegoMultijugador
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ServicioJuegoMultijugadorEnAislamientoTest {
    
    /** 
     * Atributos de la clase ServicioJuegoMultijugadorTest
     * utilizados en los distintos test e inicializados en la fixture setUp()
     */
    private List<String> cartasMesa;
    private List<String> cartasManoJugador1;
    private List<String> cartasManoJugador2;
    private List<Carta> cartasMesaObj;
    private List<Carta> cartasManoJugador1Obj;
    private List<Carta> cartasManoJugador2Obj;
    private List<String> vacia;
    private Carta carta5;
    private Carta carta9;
    private Carta carta38;
    private Carta carta39;
    private Carta carta11;
    private Carta carta14;
    private Carta carta29;
    private Carta carta7;
    private Carta carta12;
    private Carta carta3;

    /** 
     * TestSubject del ServicioJuegoMultijugador
     */
    @TestSubject
    private ServicioJuegoMultijugador juegoMultijugador;
    private ServicioJuegoMultijugador juegoMultijugador2;

    /** 
     * Mock del ServicioMultijugador
     */
    @Mock
    private ServicioMultijugador servicio;


    /** 
     * Metodo setUp() que inicializa los atributos
     * utilizados en los distintos test
     */
    @BeforeEach
    void setUp() {
        
        juegoMultijugador = new ServicioJuegoMultijugador();
        juegoMultijugador2 = new ServicioJuegoMultijugador();
        servicio = EasyMock.mock(ServicioMultijugador.class);

        cartasMesa = new ArrayList<>();
        cartasMesaObj = new ArrayList<>();
        cartasManoJugador1 = new ArrayList<>();
        cartasManoJugador1Obj = new ArrayList<>();
        cartasManoJugador2 = new ArrayList<>();
        cartasManoJugador2Obj = new ArrayList<>();
        vacia = new ArrayList<>();

        carta5 = new Carta(5);
        carta9 = new Carta(9);
        carta38 = new Carta(38);
        carta39 = new Carta(39);
        carta11 = new Carta(11);
        carta14 = new Carta(14);
        carta29 = new Carta(29);
        carta7 = new Carta(7);
        carta12 = new Carta(12);
        carta3 = new Carta(3);

        cartasMesa.add("5-oros");
        cartasMesa.add("11-oros");
        cartasMesa.add("10-bastos");
        cartasMesa.add("11-bastos");

        cartasManoJugador1.add("1-copas");
        cartasManoJugador1.add("4-copas");
        cartasManoJugador1.add("11-espadas");

        cartasManoJugador2.add("7-oros");
        cartasManoJugador2.add("2-copas");
        cartasManoJugador2.add("3-oros");

        cartasMesaObj.add(carta5);
        cartasMesaObj.add(carta9);
        cartasMesaObj.add(carta38);
        cartasMesaObj.add(carta39);

        cartasManoJugador1Obj.add(carta11);
        cartasManoJugador1Obj.add(carta14);
        cartasManoJugador1Obj.add(carta29);

        cartasManoJugador2Obj.add(carta7);
        cartasManoJugador2Obj.add(carta12);
        cartasManoJugador2Obj.add(carta3);

    }

    /** 
     * Test del metodo setServicioMultijugador valido
     */
    @Test
    void testSetServicioMultijugadorValido() {

        juegoMultijugador.setServicioMultijugador(servicio);
        assertNotNull(servicio);

    }

    /** 
     * Test del metodo setServicioMultijugador no valido con null
     */
    @Test
    void testSetServicioMultijugadorNovalidoNull(){

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.setServicioMultijugador(null);
        });

    }

    /** 
     * Test del metodo getServicioMultijugador
     */
    @Tag("Cobertura")
    @Test
    void testgetServicioMultijugador() {

        juegoMultijugador2.setServicioMultijugador(servicio);

        assertEquals(servicio, juegoMultijugador2.getServicioMultijugador());

    }

    /** 
     * Test del metodo crearSala valido para sala publica
     */
    @Test
    void testCrearSalaValidoPublica() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();

        EasyMock.expect(servicio.listarSalasDisponibles()).andReturn(java.util.Arrays.asList("sala1"));
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertEquals("sala1", juegoMultijugador.listarSalasDisponibles().get(0));
        assertEquals("Ana", juegoMultijugador.getGestorMultijugador().getJugador1());

        EasyMock.verify(servicio);

    }
    
    /** 
     * Test del metodo crearSala valido para sala privada
     */
    @Test
    void testCrearSalaValidoPrivada() {

        servicio.crearSala("sala1", "Ana", true);
        EasyMock.expectLastCall();

        EasyMock.expect(servicio.listarSalasDisponibles()).andReturn(java.util.Arrays.asList("sala1"));
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", true);

        assertEquals("sala1", juegoMultijugador.listarSalasDisponibles().get(0));
        assertEquals("Ana", juegoMultijugador.getGestorMultijugador().getJugador1());

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo crearSala no valido cuando el servicio no esta establecido
     */
    @Test
    void testCrearSalaNovalidoServicioNoEstablecido() {

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.crearSala("sala1", "Ana", false);
        });

    }

    /** 
     * Test del metodo crearSala no valido cuando el id de la sala es null
     */
    @Test
    void testCrearSalaNovalidoIdSalaNull() {

        servicio.crearSala(null, "Ana", false);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.crearSala(null, "Ana", false);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo crearSala no valido cuando el creador de la sala es null
     */
    @Test
    void testCrearSalaNovalidoCreadorNull() {

        servicio.crearSala("sala1", null, false);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.crearSala("sala1", null, false);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo crearSala no valido cuando el id de la sala es vacio
     */
    @Test
    void testCrearSalaNovalidoIdSalaVacio() {

        servicio.crearSala("", "Ana", false);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.crearSala("", "Ana", false);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo crearSala no valido cuando el creador de la sala es vacio
     */
    @Test
    void testCrearSalaNovalidoCreadorVacio() {

        servicio.crearSala("sala1", "", false);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.crearSala("sala1", "", false);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo crearSala no valido cuando el id de la sala ya existe
     */
    @Test
    void testCrearSalaNoValidoIdSalaDuplicado() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.crearSala("sala1", "Luis", true);
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.crearSala("sala1", "Luis", true);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo listarSalasDisponibles valido cuando no hay salas disponibles
     */
    @Test
    void testListarSalasDisponiblesVacio() {

        EasyMock.expect(servicio.listarSalasDisponibles()).andReturn(new ArrayList<>());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        assertTrue(juegoMultijugador.listarSalasDisponibles().isEmpty());
        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo listarSalasDisponibles valido cuando hay salas disponibles
     */
    @Test
    void testListarSalasDisponiblesNoVacio() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.crearSala("sala2", "Luis", true);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.listarSalasDisponibles()).andReturn(java.util.Arrays.asList("sala1", "sala2")).times(3);
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.crearSala("sala2", "Luis", true);

        assertEquals(2, juegoMultijugador.listarSalasDisponibles().size());
        assertEquals("sala1", juegoMultijugador.listarSalasDisponibles().get(0));
        assertEquals("sala2", juegoMultijugador.listarSalasDisponibles().get(1));

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo listarSalasDisponibles no valido cuando el servicio no esta establecido
     */
    @Test
    void testListarSalasDisponiblesNoValidoServicioNoEstablecido() {

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.listarSalasDisponibles();
        });

    }

    /** 
     * Test del metodo unirseASala valido cuando la sala es publica
     */
    @Test
    void testUnirseASalaValidoPublica() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        EasyMock.replay(servicio);
        
        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertEquals("Luis", juegoMultijugador.getGestorMultijugador().getJugador2());
        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo unirseASala valido cuando la sala es privada
     */
    @Test
    void testUnirseASalaValidoPrivada() {

        servicio.crearSala("sala1", "Ana", true);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", "codigoCorrecto");
        EasyMock.expectLastCall();
        EasyMock.replay(servicio);
        
        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", true);
        juegoMultijugador.unirseASala("sala1", "Luis", "codigoCorrecto");

        assertEquals("Luis", juegoMultijugador.getGestorMultijugador().getJugador2());
        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo unirseASala no valido cuando el servicio no esta establecido
     */
    @Test
    void testUnirseASalaNoValidoServicioNoEstablecido() {

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.unirseASala("sala1", "Luis", null);
        });

    }

    /** 
     * Test del metodo unirseASala no valido cuando el id de la sala es null
     */
    @Test
    void testUnirseASalaNoValidoIdSalaNull() {
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala(null, "Luis", null);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.unirseASala(null, "Luis", null);
        });
        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo unirseASala no valido cuando el nombre del jugador es null
     */
    @Test
    void testUnirseASalaNoValidoNombreJugadorNull() {
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", null, null);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);


        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.unirseASala("sala1", null, null);
        });
        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo unirseASala no valido cuando el id de la sala es vacio
     */
    @Test
    void testUnirseASalaNoValidoIdSalaVacio() {
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("", "Luis", null);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.unirseASala("", "Luis", null);
        });
        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo unirseASala no valido cuando el nombre del jugador es vacio
     */
    @Test
    void testUnirseASalaNoValidoNombreJugadorVacio() {
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "", null);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.unirseASala("sala1", "", null);
        });
        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo unirseASala no valido cuando la sala no existe
     */
    @Test
    void testUnirseASalaNoValidoSalaNoExistente() {
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala2", "Luis", null);
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.unirseASala("sala2", "Luis", null);
        });
        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo unirseASala no valido cuando el jugador ya esta en la sala
     */
    @Test
    void testUnirseASalaNoValidoJugadorYaEnSala() {
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Ana", null);
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.unirseASala("sala1", "Ana", null);
        });
        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo unirseASala no valido cuando el codigo de acceso es incorrecto
     */
    @Test
    void testUnirseASalaNoValidoCodigoAccesoIncorrecto() {
        
        servicio.crearSala("sala1", "Ana", true);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", "codigoIncorrecto");
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", true);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.unirseASala("sala1", "Luis", "codigoIncorrecto");
        });
        
        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo unirseASala no valido cuando la sala esta llena
     */
    @Test
    void testUnirseASalaNoValidoSalaLlena() {
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Carlos", null);
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.unirseASala("sala1", "Carlos", null);
        });
        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasDeMesa valido
     */
    @Test
    void testEnviarCartasDeMesaValido() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasMesa("sala1")).andReturn(cartasMesa);
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);
        
        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);


        assertArrayEquals(cartasMesaObj.toArray(), juegoMultijugador.getGestorMultijugador().getGestor().getMesa().getCartas().toArray());
        assertArrayEquals(cartasMesa.toArray(), juegoMultijugador.getCartasMesa("sala1").toArray());

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasDeMesa no valido cuando el servicio no esta establecido
     */
    @Test
    void testEnviarCartasDeMesaNoValidoServicioNoEstablecido() {

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        });

    }

    /** 
     * Test del metodo enviarCartasDeMesa no valido cuando el id de la sala es null
     */
    @Test
    void testEnviarCartasDeMesaNoValidoIdSalaNull() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa(null, "Ana", cartasMesa);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeMesa(null, "Ana", cartasMesa);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeMesa no valido cuando el creador es null
     */
    @Test
    void testEnviarCartasDeMesaNoValidoCreadorNull() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", null, cartasMesa);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeMesa("sala1", null, cartasMesa);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeMesa no valido cuando las cartas son null
     */
    @Test
    void testEnviarCartasDeMesaNoValidoCartasNull() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", null);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", null);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasDeMesa no valido cuando el id de la sala es vacio
     */
    @Test
    void testEnviarCartasDeMesaNoValidoIdSalaVacio() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("", "Ana", cartasMesa);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeMesa("", "Ana", cartasMesa);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasDeMesa no valido cuando el creador es vacio
     */
    @Test
    void testEnviarCartasDeMesaNoValidoCreadorVacio() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "", cartasMesa);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeMesa("sala1", "", cartasMesa);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeMesa no valido cuando las cartas son vacias
     */
    @Test
    void testEnviarCartasDeMesaNoValidoCartasVacio() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", new ArrayList<>());
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        List<String> lista=new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", lista);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeMesa no valido cuando la carta tiene formato incorrecto
     */
    @Test
    void testEnviarCartasDeMesaNoValidoCartaFormatoIncorrecto(){

        List<String> cartasMesaFormatoIncorrecto = List.of("5-oros", "11-oros", "10-bastos", "bastos-11");

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesaFormatoIncorrecto);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesaFormatoIncorrecto);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeMesa no valido cuando la sala no existe
     */
    @Test
    void testEnviarCartasDeMesaNoValidoSalaNoExiste(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala2", "Ana", cartasMesa);
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartasDeMesa("sala2", "Ana", cartasMesa);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeMesa no valido cuando el creador no es el que envia las cartas
     */
    @Test
    void testEnviarCartasDeMesaNoValidoNoEsElCreador(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Luis", cartasMesa);
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartasDeMesa("sala1", "Luis", cartasMesa);
        });

        EasyMock.verify(servicio);

    }
    
    /** 
     * Test del metodo enviarCartasDeJugador valido para el jugador 1
     */
    @Test
    void testEnviarCartasDeJugadorValidoJugador1(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Ana")).andReturn(cartasManoJugador1);
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        assertArrayEquals(cartasManoJugador1Obj.toArray(), juegoMultijugador.getGestorMultijugador().getGestor().getJugador1().getMano().toArray());
        assertArrayEquals(cartasManoJugador1.toArray(), juegoMultijugador.getCartasManoJugador("sala1", "Ana").toArray());

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasDeJugador valido para el jugador 2
     */
    @Test
    void testEnviarCartasDeJugadorValidoJugador2(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Luis")).andReturn(cartasManoJugador2);
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        assertArrayEquals(cartasManoJugador2Obj.toArray(), juegoMultijugador.getGestorMultijugador().getGestor().getJugador2().getMano().toArray());
        assertArrayEquals(cartasManoJugador2.toArray(), juegoMultijugador.getCartasManoJugador("sala1", "Luis").toArray());

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando el servicio no esta establecido
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoServicioNoEstablecido() {

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        });

    }

    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando el id de la sala es null
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoIdSalaNull() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador(null, "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador(null, "Ana", "Ana", cartasManoJugador1);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando el creador es null
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoCreadorNull() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", null, "Ana", cartasManoJugador1);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador("sala1", null, "Ana", cartasManoJugador1);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando el nombre del jugador es null
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoNombreJugadorNull() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", null, cartasManoJugador1);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", null, cartasManoJugador1);
        });

        EasyMock.verify(servicio);

    }
    
    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando las cartas son null
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoCartasNull() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", null);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", null);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando el id de la sala es vacio
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoIdSalaVacio() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador("", "Ana", "Ana", cartasManoJugador1);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando el creador es vacio
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoCreadorVacio() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador("sala1", "", "Ana", cartasManoJugador1);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando el nombre del jugador es vacio
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoNombreJugadorVacio() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "", cartasManoJugador1);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "", cartasManoJugador1);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando las cartas son vacias
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoCartasVacio() {

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", new ArrayList<>());
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        List<String> listaVacia=new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", listaVacia);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando el formato de las cartas es incorrecto
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoCartaFormatoIncorrecto(){

        List<String> cartasManoFormatoIncorrecto = List.of("1-copas", "4-copas", "espadas-11");

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoFormatoIncorrecto);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoFormatoIncorrecto);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando el id de la sala no existe
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoSalaNoExiste(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala2", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador("sala2", "Ana", "Ana", cartasManoJugador1);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando el jugador no está en la sala
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoJugadorNoEnSala(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador1);
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador1);
        });

        EasyMock.verify(servicio);

    }
    
    /** 
     * Test del metodo enviarCartasDeJugador no valido cuando el creador no es el que envia las cartas
     */
    @Test
    void testEnviarCartasDeJugadorNoValidoNoEsElCreador(){
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Luis", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartasDeJugador("sala1", "Luis", "Ana", cartasManoJugador1);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartasMesa valido
     */
    @Test
    void testGetCartasMesaValido(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasMesa("sala1")).andReturn(cartasMesa);
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        assertArrayEquals(cartasMesa.toArray(), juegoMultijugador.getCartasMesa("sala1").toArray());

        EasyMock.verify(servicio);
    }
    
    /** 
     * Test del metodo getCartasMesa valido cuando no hay cartas en la mesa
     */
    @Test
    void testGetCartasMesaValidoVacia(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasMesa("sala1")).andReturn(new ArrayList<>());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertTrue(juegoMultijugador.getCartasMesa("sala1").isEmpty());

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartasMesa no valido cuando el servicio no esta establecido
     */
    @Test
    void testGetCartasMesaNoValidoServicioNoEstablecido(){

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.getCartasMesa("sala1");
        });

    }

    /** 
     * Test del metodo getCartasMesa no valido cuando el id de la sala es null
     */
    @Test
    void testGetCartasMesaNoValidoIdSalaNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasMesa(null)).andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartasMesa(null);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartasMesa no valido cuando el id de la sala es vacio
     */
    @Test
    void testGetCartasMesaNoValidoIdSalaVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasMesa("")).andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartasMesa("");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartasMesa no valido cuando la sala no existe
     */
    @Test
    void testGetCartasMesaNoValidoSalaNoExiste(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasMesa("sala2")).andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.getCartasMesa("sala2");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartasManoJugador valido para el jugador 1
     */
    @Test
    void testGetCartasManoJugadorValidoJugador1(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Ana")).andReturn(cartasManoJugador1);
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        assertArrayEquals(cartasManoJugador1.toArray(), juegoMultijugador.getCartasManoJugador("sala1", "Ana").toArray());

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartasManoJugador valido para el jugador 1 cuando no tiene cartas
     */
    @Test
    void testGetCartasManoJugadorValidoJugador1Vacia(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Ana")).andReturn(new ArrayList<>());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertTrue(juegoMultijugador.getCartasManoJugador("sala1", "Ana").isEmpty());
        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartasManoJugador valido para el jugador 2
     */
    @Test
    void testGetCartasManoJugadorValidoJugador2(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Luis")).andReturn(cartasManoJugador2);
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        assertArrayEquals(cartasManoJugador2.toArray(), juegoMultijugador.getCartasManoJugador("sala1", "Luis").toArray());

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartasManoJugador valido para el jugador 2 cuando no tiene cartas
     */
    @Test
    void testGetCartasManoJugadorValidoJugador2Vacia(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Luis")).andReturn(new ArrayList<>());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertTrue(juegoMultijugador.getCartasManoJugador("sala1", "Luis").isEmpty());

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartasManoJugador no valido cuando el servicio no esta establecido
     */
    @Test
    void testGetCartasManoJugadorNoValidoServicioNoEstablecido(){

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.getCartasManoJugador("sala1", "Ana");
        });

    }

    /** 
     * Test del metodo getCartasManoJugador no valido cuando el id de la sala es null
     */
    @Test
    void testGetCartasManoJugadorNoValidoIdSalaNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador(null, "Ana")).andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartasManoJugador(null, "Ana");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartasManoJugador no valido cuando el nombre del jugador es null
     */
    @Test
    void testGetCartasManoJugadorNoValidoNombreJugadorNull(){
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", null)).andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartasManoJugador("sala1", null);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartasManoJugador no valido cuando el id de la sala es vacio
     */
    @Test
    void testGetCartasManoJugadorNoValidoIdSalaVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("", "Ana")).andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartasManoJugador("", "Ana");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartasManoJugador no valido cuando el nombre del jugador es vacio
     */
    @Test
    void testGetCartasManoJugadorNoValidoNombreJugadorVacio(){
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "")).andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartasManoJugador("sala1", "");
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartasManoJugador no valido cuando la sala no existe
     */
    @Test
    void testGetCartasManoJugadorNoValidoSalaNoExiste(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala2", "Ana")).andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.getCartasManoJugador("sala2", "Ana");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartasManoJugador no valido cuando el jugador no esta en la sala
     */
    @Test
    void testGetCartasManoJugadorNoValidoJugadorNoEnSala(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Luis")).andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.getCartasManoJugador("sala1", "Luis");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartaJugada valido para el jugador 1
     */
    @Test
    void testEnviarCartaJugadaValidoJugador1(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartaJugadaPorJugador("sala1", "Ana")).andReturn(cartasManoJugador1.get(0));
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        cartasManoJugador1Obj.remove(0);

        assertEquals(cartasManoJugador1.get(0), juegoMultijugador.getCartaJugadaPorJugador("sala1", "Ana"));
        assertArrayEquals(cartasManoJugador1Obj.toArray(), juegoMultijugador.getGestorMultijugador().getGestor().getJugador1().getMano().toArray());

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartaJugada valido para el jugador 2
     */
    @Test
    void testEnviarCartaJugadaValidoJugador2(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Luis", cartasManoJugador2.get(0));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartaJugadaPorJugador("sala1", "Luis")).andReturn(cartasManoJugador2.get(0));
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        
        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        juegoMultijugador.enviarCartaJugada("sala1", "Luis", cartasManoJugador2.get(0));

        

        cartasManoJugador2Obj.remove(0);

        assertEquals(cartasManoJugador2.get(0), juegoMultijugador.getCartaJugadaPorJugador("sala1", "Luis"));
        assertArrayEquals(cartasManoJugador2Obj.toArray(), juegoMultijugador.getGestorMultijugador().getGestor().getJugador2().getMano().toArray());

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartaJugada no valido cuando el servicio no esta establecido
     */
    @Test
    void testEnviarCartaJugadaNoValidoServicioNoEstablecido(){

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartaJugada("sala1", "Ana", "5-oros");
        });

    }

    /** 
     * Test del metodo enviarCartaJugada no valido cuando el id de la sala es null
     */
    @Test
    void testEnviarCartaJugadaNoValidoIdSalaNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada(null, "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        String cartita= cartasManoJugador1.get(0);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartaJugada(null, "Ana", cartita);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartaJugada no valido cuando el nombre del jugador es null
     */
    @Test
    void testEnviarCartaJugadaNoValidoNombreJugadorNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", null, cartasManoJugador1.get(0));
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        String cartita= cartasManoJugador1.get(0);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartaJugada("sala1", null, cartita);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartaJugada no valido cuando la carta es null
     */
    @Test
    void testEnviarCartaJugadaNoValidoCartaNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartaJugada("sala1", "Ana", null);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartaJugada no valido cuando el id de la sala es vacio
     */
    @Test
    void testEnviarCartaJugadaNoValidoIdSalaVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        String cartita= cartasManoJugador1.get(0);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartaJugada("", "Ana", cartita);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartaJugada no valido cuando el nombre del jugador es vacio
     */
    @Test
    void testEnviarCartaJugadaNoValidoNombreJugadorVacio(){
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "", cartasManoJugador1.get(0));
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        String cartita= cartasManoJugador1.get(0);
        
        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartaJugada("sala1", "", cartita);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartaJugada no valido cuando la carta es vacia
     */
    @Test
    void testEnviarCartaJugadaNoValidoCartaVacia(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartaJugada("sala1", "Ana", "");
        });

        EasyMock.verify(servicio);
    }
    
    /** 
     * Test del metodo enviarCartaJugada no valido cuando la sala no existe
     */
    @Test
    void testEnviarCartaJugadaNoValidoSalaNoExiste(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala2", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        String cartita= cartasManoJugador1.get(0);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartaJugada("sala2", "Ana", cartita);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartaJugada no valido cuando el jugador no esta en la sala
     */
    @Test
    void testEnviarCartaJugadaNoValidoJugadorNoEnSala(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Carlos", cartasManoJugador1.get(0));
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        String cartita= cartasManoJugador1.get(0);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartaJugada("sala1", "Carlos", cartita);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartaJugada no valido cuando el jugador no tiene la carta en su mano
     */
    @Test
    void testEnviarCartaJugadaNoValidoCartaNoEnMano(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        EasyMock.replay(servicio);

         String cartita= cartasManoJugador2.get(0);
        
        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

       
        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartita);
        });

        EasyMock.verify(servicio);
    
    }
    
    /** 
     * Test del metodo enviarCartasCapturadas valido para el jugador 1
     */
    @Test
    void testEnviarCartasCapturadasValidoJugador1(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas("sala1", "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasCapturadasPorJugador("sala1", "Ana")).andReturn(List.of(cartasManoJugador1.get(0), cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);
        
        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        juegoMultijugador.enviarCartasCapturadas("sala1", "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));

        assertArrayEquals(List.of(cartasManoJugador1.get(0), cartasMesa.get(0), cartasMesa.get(1)).toArray(), juegoMultijugador.getCartasCapturadasPorJugador("sala1", "Ana").toArray());
        assertArrayEquals(List.of(cartasManoJugador1Obj.get(0), cartasMesaObj.get(0), cartasMesaObj.get(1)).toArray(), juegoMultijugador.getGestorMultijugador().getGestor().getJugador1().getCartasObtenidas().toArray());

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasCapturadas valido para el jugador 2
     */
    @Test
    void testEnviarCartasCapturadasValidoJugador2(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Luis", cartasManoJugador2.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas("sala1", "Luis", List.of(cartasMesa.get(2)));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasCapturadasPorJugador("sala1", "Luis")).andReturn(List.of(cartasManoJugador2.get(0), cartasMesa.get(2)));
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);
        
        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        juegoMultijugador.enviarCartaJugada("sala1", "Luis", cartasManoJugador2.get(0));

        juegoMultijugador.enviarCartasCapturadas("sala1", "Luis", List.of(cartasMesa.get(2)));

        assertArrayEquals(List.of(cartasManoJugador2.get(0), cartasMesa.get(2)).toArray(), juegoMultijugador.getCartasCapturadasPorJugador("sala1", "Luis").toArray());
        assertArrayEquals(List.of(cartasManoJugador2Obj.get(0), cartasMesaObj.get(2)).toArray(), juegoMultijugador.getGestorMultijugador().getGestor().getJugador2().getCartasObtenidas().toArray());

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasCapturadas no valido cuando el servicio no esta establecido
     */
    @Test
    void testEnviarCartasCapturadasNoValidoServicioNoEstablecido(){

        List<String> lista= List.of("5-oros");

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartasCapturadas("sala1", "Ana", lista);
        });

    }

    /** 
     * Test del metodo enviarCartasCapturadas no valido cuando el id de la sala es null
     */
    @Test
    void testEnviarCartasCapturadasNoValidoIdSalaNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas(null, "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        List<String> cartas= List.of(cartasMesa.get(0), cartasMesa.get(1));
        
        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasCapturadas(null, "Ana", cartas);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasCapturadas no valido cuando el nombre del jugador es null
     */
    @Test
    void testEnviarCartasCapturadasNoValidoNombreJugadorNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas("sala1", null, List.of(cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);
        

        List<String> cartas= List.of(cartasMesa.get(0), cartasMesa.get(1));
        

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasCapturadas("sala1", null, cartas);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasCapturadas no valido cuando las cartas capturadas son null
     */
    @Test
    void testEnviarCartasCapturadasNoValidoCapturadasNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasCapturadas("sala1", "Ana", null);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasCapturadas no valido cuando el id de la sala es vacio
     */
    @Test
    void testEnviarCartasCapturadasNoValidoIdSalaVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas("", "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);
        
        List<String> cartas= List.of(cartasMesa.get(0), cartasMesa.get(1));

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasCapturadas("", "Ana",cartas);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasCapturadas no valido cuando el nombre del jugador es vacio
     */
    @Test
    void testEnviarCartasCapturadasNoValidoNombreJugadorVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas("sala1", "", List.of(cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        List<String> cartas= List.of(cartasMesa.get(0), cartasMesa.get(1));

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasCapturadas("sala1", "", cartas);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasCapturadas no valido cuando la sala no existe
     */
    @Test
    void testEnviarCartasCapturadasNoValidoSalaNoExiste(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas("sala2", "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        List<String> cartas= List.of(cartasMesa.get(0), cartasMesa.get(1));


        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartasCapturadas("sala2", "Ana",cartas);
        });

        EasyMock.verify(servicio);
    }
    
    /** 
     * Test del metodo enviarCartasCapturadas no valido cuando el jugador no esta en la sala
     */
    @Test
    void testEnviarCartasCapturadasNoValidoJugadorNoEnSala(){

        List<String> lista= List.of(cartasMesa.get(0), cartasMesa.get(1));

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas("sala1", "Carlos", List.of(cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.enviarCartasCapturadas("sala1", "Carlos",lista);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo enviarCartasCapturadas no valido cuando la carta no esta en la mesa
     */
    @Test
    void testEnviarCartasCapturadasNoValidoCartaNoEnMesa(){
        
        List<String> lista= List.of("7-copas");

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        EasyMock.replay(servicio);
        
        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasCapturadas("sala1", "Ana",lista);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo enviarCartasCapturadas no valido cuando las cartas capturadas no suman 15
     */
    @Test
    void testEnviarCartasCapturadasNoValidoNoSuman15(){


        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        EasyMock.replay(servicio);
        
        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.enviarCartasCapturadas("sala1", "Ana", vacia);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartaJugadaPorJugador valido para el jugador 1
     */
    @Test
    void testGetCartaJugadaPorJugadorValidoJugador1(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartaJugadaPorJugador("sala1", "Ana")).andReturn(cartasManoJugador1.get(0));
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        assertEquals(cartasManoJugador1.get(0), juegoMultijugador.getCartaJugadaPorJugador("sala1", "Ana"));

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartaJugadaPorJugador valido para el jugador 2
     */
    @Test
    void testGetCartaJugadaPorJugadorValidoJugador2(){
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Luis", cartasManoJugador2.get(0));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartaJugadaPorJugador("sala1", "Luis")).andReturn(cartasManoJugador2.get(0));
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        juegoMultijugador.enviarCartaJugada("sala1", "Luis", cartasManoJugador2.get(0));

        assertEquals(cartasManoJugador2.get(0), juegoMultijugador.getCartaJugadaPorJugador("sala1", "Luis"));

        EasyMock.verify(servicio);
    }

    /**
     * Test del metodo getCartaJugadaPorJugador valido cuando el jugador 1 no ha jugado
     */
    @Test
    void testGetCartaJugadaPorJugadorValidoJugador1NoHaJugado(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartaJugadaPorJugador("sala1", "Ana")).andReturn(null);
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        assertNull(juegoMultijugador.getCartaJugadaPorJugador("sala1", "Ana"));
        
        EasyMock.verify(servicio);
    }

    /**
     * Test del metodo getCartaJugadaPorJugador valido cuando el jugador 2 no ha jugado
     */
    @Test
    void testGetCartaJugadaPorJugadorValidoJugador2NoHaJugado(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartaJugadaPorJugador("sala1", "Luis")).andReturn(null);
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        assertNull(juegoMultijugador.getCartaJugadaPorJugador("sala1", "Luis"));

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartaJugadaPorJugador no valido cuando el servicio no esta establecido
     */
    @Test
    void testGetCartaJugadaPorJugadorNoValidoServicioNoEstablecido(){
        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.getCartaJugadaPorJugador("sala1", "Ana");
        });


    }

    /** 
     * Test del metodo getCartaJugadaPorJugador no valido cuando el id de la sala es null
     */
    @Test
    void testGetCartaJugadaPorJugadorNoValidoIdSalaNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartaJugadaPorJugador(null, "Ana");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartaJugadaPorJugador(null, "Ana");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartaJugadaPorJugador no valido cuando el nombre del jugador es null
     */
    @Test
    void testGetCartaJugadaPorJugadorNoValidoNombreJugadorNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartaJugadaPorJugador("sala1", null);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartaJugadaPorJugador("sala1", null);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartaJugadaPorJugador no valido cuando el id de la sala es vacio
     */
    @Test
    void testGetCartaJugadaPorJugadorNoValidoIdSalaVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartaJugadaPorJugador("", "Ana");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartaJugadaPorJugador("", "Ana");
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartaJugadaPorJugador no valido cuando el nombre del jugador es vacio
     */
    @Test
    void testGetCartaJugadaPorJugadorNoValidoNombreJugadorVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartaJugadaPorJugador("sala1", "");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartaJugadaPorJugador("sala1", "");
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartaJugadaPorJugador no valido cuando la sala no existe
     */
    @Test
    void testGetCartaJugadaPorJugadorNoValidoSalaNoExiste(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartaJugadaPorJugador("sala2", "Ana");
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.getCartaJugadaPorJugador("sala2", "Ana");
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartaJugadaPorJugador no valido cuando el jugador no esta en la sala
     */
    @Test
    void testGetCartaJugadaPorJugadorNoValidoJugadorNoEnSala(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartaJugadaPorJugador("sala1", "Carlos");
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.getCartaJugadaPorJugador("sala1", "Carlos");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartasCapturadasPorJugador valido para el jugador 1
     */
    @Test
    void testGetCartasCapturadasPorJugadorValidoJugador1(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas("sala1", "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasCapturadasPorJugador("sala1", "Ana")).andReturn(List.of(cartasManoJugador1.get(0), cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        juegoMultijugador.enviarCartasCapturadas("sala1", "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));

        assertArrayEquals(List.of(cartasManoJugador1.get(0), cartasMesa.get(0), cartasMesa.get(1)).toArray(), juegoMultijugador.getCartasCapturadasPorJugador("sala1", "Ana").toArray());

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartasCapturadasPorJugador valido para el jugador 2
     */
    @Test
    void testGetCartasCapturadasPorJugadorValidoJugador2(){
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartaJugada("sala1", "Luis", cartasManoJugador2.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas("sala1", "Luis", List.of(cartasMesa.get(2)));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasCapturadasPorJugador("sala1", "Luis")).andReturn(List.of(cartasManoJugador2.get(0), cartasMesa.get(2)));
        EasyMock.replay(servicio);
        
        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        juegoMultijugador.enviarCartaJugada("sala1", "Luis", cartasManoJugador2.get(0));

        juegoMultijugador.enviarCartasCapturadas("sala1", "Luis", List.of(cartasMesa.get(2)));

        assertArrayEquals(List.of(cartasManoJugador2.get(0), cartasMesa.get(2)).toArray(), juegoMultijugador.getCartasCapturadasPorJugador("sala1", "Luis").toArray());
        
        EasyMock.verify(servicio);
    
    }

    /** 
     * Test del metodo getCartasCapturadasPorJugador valido cuando el jugador 1 no ha capturado cartas
     */
    @Test
    void testGetCartasCapturadasPorJugadorValidoJugador1Vacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasCapturadasPorJugador("sala1", "Ana")).andReturn(new ArrayList<>());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertTrue(juegoMultijugador.getCartasCapturadasPorJugador("sala1", "Ana").isEmpty());
    
        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartasCapturadasPorJugador valido cuando el jugador 2 no ha capturado cartas
     */
    @Test
    void testGetCartasCapturadasPorJugadorValidoJugador2Vacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasCapturadasPorJugador("sala1", "Luis")).andReturn(new ArrayList<>());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertTrue(juegoMultijugador.getCartasCapturadasPorJugador("sala1", "Luis").isEmpty());
    
        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartasCapturadasPorJugador no valido cuando el servicio no esta establecido
     */
    @Test
    void testGetCartasCapturadasPorJugadorNoValidoServicioNoEstablecido(){
        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.getCartasCapturadasPorJugador("sala1", "Ana");
        });
    }

    /** 
     * Test del metodo getCartasCapturadasPorJugador no valido cuando el id de la sala es null
     */
    @Test
    void testGetCartasCapturadasPorJugadorNoValidoIdSalaNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartasCapturadasPorJugador(null, "Ana");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartasCapturadasPorJugador(null, "Ana");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo getCartasCapturadasPorJugador no valido cuando el nombre del jugador es null
     */
    @Test
    void testGetCartasCapturadasPorJugadorNoValidoNombreJugadorNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartasCapturadasPorJugador("sala1", null);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartasCapturadasPorJugador("sala1", null);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartasCapturadasPorJugador no valido cuando el id de la sala es vacio
     */
    @Test
    void testGetCartasCapturadasPorJugadorNoValidoIdSalaVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartasCapturadasPorJugador("", "Ana");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartasCapturadasPorJugador("", "Ana");
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartasCapturadasPorJugador no valido cuando el nombre del jugador es vacio
     */
    @Test
    void testGetCartasCapturadasPorJugadorNoValidoNombreJugadorVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartasCapturadasPorJugador("sala1", "");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.getCartasCapturadasPorJugador("sala1", "");
        });

        EasyMock.verify(servicio);
    
    }
    
    /** 
     * Test del metodo getCartasCapturadasPorJugador no valido cuando la sala no existe
     */
    @Test
    void testGetCartasCapturadasPorJugadorNoValidoSalaNoExiste(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartasCapturadasPorJugador("sala2", "Ana");
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.getCartasCapturadasPorJugador("sala2", "Ana");
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo getCartasCapturadasPorJugador no valido cuando el jugador no esta en la sala
     */
    @Test
    void testGetCartasCapturadasPorJugadorNoValidoJugadorNoEnSala(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartasCapturadasPorJugador("sala1", "Carlos");
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.getCartasCapturadasPorJugador("sala1", "Carlos");
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo recibirCartasDeMesa valido
     */
    @Test
    void testRecibirCartasMesaValido(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasMesa("sala1")).andReturn(cartasMesa);
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador2.crearSala("sala1", "Ana", false);

        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador2.recibirCartasDeMesa("sala1");

        assertArrayEquals(cartasMesaObj.toArray(), juegoMultijugador2.getGestorMultijugador().getGestor().getMesa().getCartas().toArray());
        
        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo recibirCartasDeMesa no valido cuando el servicio no esta establecido
     */
    @Test
    void testRecibirCartasMesaNoValidoServicioNoEstablecido(){
        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.recibirCartasDeMesa("sala1");
        });
    }

    /** 
     * Test del metodo recibirCartasDeMesa no valido cuando el id de la sala es null
     */
    @Test
    void testRecibirCartasMesaNoValidoIdSalaNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.getCartasMesa(null);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);
        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador2.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador2.recibirCartasDeMesa(null);
        });

    }
    
    /** 
     * Test del metodo recibirCartasDeMesa no valido cuando el id de la sala es vacio
     */
    @Test
    void testRecibirCartasMesaNoValidoIdSalaVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.getCartasMesa("");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);
        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador2.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador2.recibirCartasDeMesa("");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo recibirCartasDeMesa no valido cuando la sala no existe
     */
    @Test
    void testRecibirCartasMesaNoValidoSalaNoExiste(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        servicio.getCartasMesa("sala2");
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);
        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador2.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador2.recibirCartasDeMesa("sala2");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo recibirCartasDeJugador valido
     */
    @Test
    void testRecibirCartasJugadorValido(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Luis")).andReturn(cartasManoJugador2);
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador2.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        juegoMultijugador2.recibirCartasDeJugador("sala1", "Luis");

        assertArrayEquals(cartasManoJugador2Obj.toArray(), juegoMultijugador2.getGestorMultijugador().getGestor().getJugador2().getMano().toArray());

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo recibirCartasDeJugador no valido cuando el servicio no esta establecido
     */
    @Test
    void testRecibirCartasJugadorNoValidoServicioNoEstablecido(){
        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.recibirCartasDeJugador("sala1", "Ana");
        });

    }

    /** 
     * Test del metodo recibirCartasDeJugador no valido cuando el id de la sala es null
     */
    @Test
    void testRecibirCartasJugadorNoValidoIdSalaNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        servicio.getCartasManoJugador(null, "Luis");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);
        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador2.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador2.recibirCartasDeJugador(null, "Luis");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo recibirCartasDeJugador no valido cuando el id de la sala es vacio
     */
    @Test
    void testRecibirCartasJugadorNoValidoIdSalaVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        servicio.getCartasManoJugador("", "Luis");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);
        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador2.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador2.recibirCartasDeJugador("", "Luis");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo recibirCartasDeJugador no valido cuando el nombre del jugador es null
     */
    @Test
    void testRecibirCartasJugadorNoValidoNombreJugadorNull(){
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        servicio.getCartasManoJugador("sala1", null);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);
        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador2.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador2.recibirCartasDeJugador("sala1", null);
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo recibirCartasDeJugador no valido cuando el nombre del jugador es vacio
     */
    @Test
    void testRecibirCartasJugadorNoValidoNombreJugadorVacio(){
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        servicio.getCartasManoJugador("sala1", "");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);
        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador2.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador2.recibirCartasDeJugador("sala1", "");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo recibirCartasDeJugador no valido cuando la sala no existe
     */
    @Test
    void testRecibirCartasJugadorNoValidoSalaNoExiste(){
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        servicio.getCartasManoJugador("sala2", "Luis");
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);
        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador2.crearSala("sala1", "Ana", false);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador2.recibirCartasDeJugador("sala2", "Luis");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo recibirCartasDeJugador no valido cuando el jugador no esta en la sala
     */
    @Test
    void testRecibirCartasJugadorNoValidoJugadorNoEnSala(){
        
        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);
        EasyMock.expectLastCall();
        servicio.getCartasManoJugador("sala1", "Carlos");
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);
        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador2.crearSala("sala1", "Ana", false);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Luis", cartasManoJugador2);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador2.recibirCartasDeJugador("sala1", "Carlos");
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo recibirJugadaDeJugador valido sin captura
     */
    @Test
    void testRecibirJugadaRivalValidoSinCaptura(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasMesa("sala1")).andReturn(cartasMesa);
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Ana")).andReturn(cartasManoJugador1);
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartaJugadaPorJugador("sala1", "Ana")).andReturn(cartasManoJugador1.get(0));
        EasyMock.expect(servicio.getCartasCapturadasPorJugador("sala1", "Ana")).andReturn(new ArrayList<>());
        EasyMock.replay(servicio);


        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.unirseASala("sala1", "Luis", null);
        
        juegoMultijugador2.crearSala("sala1", "Ana", false);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador2.recibirCartasDeMesa("sala1");

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador2.recibirCartasDeJugador("sala1", "Ana");

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        juegoMultijugador2.recibirJugadaDeJugador("sala1", "Ana");

        assertArrayEquals(List.of(carta5, carta9, carta38, carta39, cartasManoJugador1Obj.get(0)).toArray(), juegoMultijugador2.getGestorMultijugador().getGestor().getMesa().getCartas().toArray());
        assertArrayEquals(List.of(cartasManoJugador1Obj.get(1), cartasManoJugador1Obj.get(2)).toArray(), juegoMultijugador2.getGestorMultijugador().getGestor().getJugador1().getMano().toArray());

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo recibirJugadaDeJugador valido con captura
     */
    @Test
    void testRecibirJugadaRivalValidoConCaptura(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasMesa("sala1")).andReturn(cartasMesa);
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Ana")).andReturn(cartasManoJugador1);
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas("sala1", "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartaJugadaPorJugador("sala1", "Ana")).andReturn(cartasManoJugador1.get(0));
        EasyMock.expect(servicio.getCartasCapturadasPorJugador("sala1", "Ana")).andReturn(List.of(cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador2.crearSala("sala1", "Ana", false);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador2.recibirCartasDeMesa("sala1");

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador2.recibirCartasDeJugador("sala1", "Ana");

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        juegoMultijugador.enviarCartasCapturadas("sala1", "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));

        juegoMultijugador2.recibirJugadaDeJugador("sala1", "Ana");

        assertArrayEquals(List.of(cartasManoJugador1Obj.get(1), cartasManoJugador1Obj.get(2)).toArray(), juegoMultijugador2.getGestorMultijugador().getGestor().getJugador1().getMano().toArray());
        assertArrayEquals(List.of(cartasManoJugador1Obj.get(0), cartasMesaObj.get(0), cartasMesaObj.get(1)).toArray(), juegoMultijugador2.getGestorMultijugador().getGestor().getJugador1().getCartasObtenidas().toArray());

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo recibirJugadaDeJugador no valido cuando el servicio no esta establecido
     */
    @Test
    void testRecibirJugadaRivalNoValidoServicioNoEstablecido(){
        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.recibirJugadaDeJugador("sala1", "Ana");
        });
    }

    /** 
     * Test del metodo recibirJugadaDeJugador no valido cuando el id de la sala es null
     */
    @Test
    void testRecibirJugadaRivalNoValidoIdSalaNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartaJugadaPorJugador(null, "Ana");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);
        
        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.recibirJugadaDeJugador(null, "Ana");
        });

        EasyMock.verify(servicio);

        
    }

    /** 
     * Test del metodo recibirJugadaDeJugador no valido cuando el id de la sala es vacio
     */
    @Test
    void testRecibirJugadaRivalNoValidoIdSalaVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartaJugadaPorJugador("", "Ana");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.recibirJugadaDeJugador("", "Ana");
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo recibirJugadaDeJugador no valido cuando el nombre del jugador es null
     */
    @Test
    void testRecibirJugadaRivalNoValidoNombreJugadorNull(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartaJugadaPorJugador("sala1", null);
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);
        
        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.recibirJugadaDeJugador("sala1", null);
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo recibirJugadaDeJugador no valido cuando el nombre del jugador es vacio
     */
    @Test
    void testRecibirJugadaRivalNoValidoNombreJugadorVacio(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartaJugadaPorJugador("sala1", "");
        EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
        EasyMock.replay(servicio);
        
        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador.recibirJugadaDeJugador("sala1", "");
        });

        EasyMock.verify(servicio);
    }

    /** 
     * Test del metodo recibirJugadaDeJugador no valido cuando la sala no existe
     */
    @Test
    void testRecibirJugadaRivalNoValidoSalaNoExiste(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartaJugadaPorJugador("sala2", "Ana");
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.recibirJugadaDeJugador("sala2", "Ana");
        });

        EasyMock.verify(servicio);

    }

    /** 
     * Test del metodo recibirJugadaDeJugador no valido cuando el jugador no esta en la sala
     */
    @Test
    void testRecibirJugadaRivalNoValidoJugadorNoEnSala(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall();
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall();
        servicio.getCartaJugadaPorJugador("sala1", "Carlos");
        EasyMock.expectLastCall().andThrow(new IllegalStateException());
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);

        juegoMultijugador.unirseASala("sala1", "Luis", null);

        assertThrows(IllegalStateException.class, () -> {
            juegoMultijugador.recibirJugadaDeJugador("sala1", "Carlos");
        });

        EasyMock.verify(servicio);
    }

    @Test
    void testRecibirJugadaRivalNoValidoCartaNoEnManoRival(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasMesa("sala1")).andReturn(cartasMesa);
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Ana")).andReturn(cartasManoJugador1);
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartaJugadaPorJugador("sala1", "Ana")).andReturn(cartasManoJugador2.get(0));
        EasyMock.expect(servicio.getCartasCapturadasPorJugador("sala1", "Ana")).andReturn(new ArrayList<>());
        EasyMock.replay(servicio);


        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.unirseASala("sala1", "Luis", null);
        
        juegoMultijugador2.crearSala("sala1", "Ana", false);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador2.recibirCartasDeMesa("sala1");

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador2.recibirCartasDeJugador("sala1", "Ana");

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador2.recibirJugadaDeJugador("sala1", "Ana");
        });

        EasyMock.verify(servicio);
    }

    @Test
    void testRecibirJugadaRivalNoValidoCartaNoEnMesa(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasMesa("sala1")).andReturn(cartasMesa);
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Ana")).andReturn(cartasManoJugador1);
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas("sala1", "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartaJugadaPorJugador("sala1", "Ana")).andReturn(cartasManoJugador1.get(0));
        EasyMock.expect(servicio.getCartasCapturadasPorJugador("sala1", "Ana")).andReturn(List.of(cartasManoJugador1.get(0)));
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador2.crearSala("sala1", "Ana", false);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador2.recibirCartasDeMesa("sala1");

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador2.recibirCartasDeJugador("sala1", "Ana");

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        juegoMultijugador.enviarCartasCapturadas("sala1", "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador2.recibirJugadaDeJugador("sala1", "Ana");
        });

        EasyMock.verify(servicio);

    }
    
    @Test
    void testRecibirJugadaRivalNoValidoCapturaNoSuman15(){

        servicio.crearSala("sala1", "Ana", false);
        EasyMock.expectLastCall().times(2);
        servicio.unirseASala("sala1", "Luis", null);
        EasyMock.expectLastCall().times(2);
        servicio.enviarCartasDeMesa("sala1", "Ana", cartasMesa);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasMesa("sala1")).andReturn(cartasMesa);
        servicio.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartasManoJugador("sala1", "Ana")).andReturn(cartasManoJugador1);
        servicio.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));
        EasyMock.expectLastCall();
        servicio.enviarCartasCapturadas("sala1", "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));
        EasyMock.expectLastCall();
        EasyMock.expect(servicio.getCartaJugadaPorJugador("sala1", "Ana")).andReturn(cartasManoJugador1.get(0));
        EasyMock.expect(servicio.getCartasCapturadasPorJugador("sala1", "Ana")).andReturn(List.of(cartasMesa.get(0)));
        EasyMock.replay(servicio);

        juegoMultijugador.setServicioMultijugador(servicio);

        juegoMultijugador2.setServicioMultijugador(servicio);

        juegoMultijugador.crearSala("sala1", "Ana", false);
        juegoMultijugador.unirseASala("sala1", "Luis", null);

        juegoMultijugador2.crearSala("sala1", "Ana", false);
        juegoMultijugador2.unirseASala("sala1", "Luis", null);

        juegoMultijugador.enviarCartasDeMesa("sala1", "Ana", cartasMesa);

        juegoMultijugador2.recibirCartasDeMesa("sala1");

        juegoMultijugador.enviarCartasDeJugador("sala1", "Ana", "Ana", cartasManoJugador1);

        juegoMultijugador2.recibirCartasDeJugador("sala1", "Ana");

        juegoMultijugador.enviarCartaJugada("sala1", "Ana", cartasManoJugador1.get(0));

        juegoMultijugador.enviarCartasCapturadas("sala1", "Ana", List.of(cartasMesa.get(0), cartasMesa.get(1)));

        assertThrows(IllegalArgumentException.class, () -> {
            juegoMultijugador2.recibirJugadaDeJugador("sala1", "Ana");
        });
    
        EasyMock.verify(servicio);
    }

}
