package uva.tds;

/**
 * Clase de test para la clase Parser
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ParserTest {

    /**
     * Atributos de la clase ParserTest
     * utilizados en los distintos test e inicializados en el metodo setUp()
     */
    private Baraja baraja;
    private Carta carta5;
    private Carta carta7;
    private Carta carta9;
    private Carta carta38;
    private Carta carta39;
        

    
    private JSONObject jsonObject;

    private Parser parser;

    /**
     * Metodo setUp() que inicializa los atributos
     * utilizados en los distintos test
     */
    @BeforeEach
    void setUp(){

        baraja = new Baraja();
        List<Carta> cartasBaraja = baraja.getCartas();
        carta5 = cartasBaraja.get(4);
        carta7=cartasBaraja.get(6);
        carta9=cartasBaraja.get(8);
        carta38=cartasBaraja.get(37);
        carta39=cartasBaraja.get(38);


        parser = new Parser();

        try {
    
            InputStream is = getClass().getClassLoader().getResourceAsStream("partida_escoba1.json");

    
            if (is == null) {
                throw new FileNotFoundException("El archivo 'partida_escoba1.json' no se encuentra en src/test/resources");
            }
            String jsonString = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            this.jsonObject = new JSONObject(jsonString);

            } catch (Exception e) {
    
                e.printStackTrace();
    
                throw new RuntimeException("Fallo fatal leyendo el JSON: " + e.getMessage());
            }
    
    }


    /** 
     * Test del metodo parsearCartasDesdeJSON de la clase JSONParser con JSON valido
     */
    @Test
    void testParsearCartasDesdeJSONValido(){

        List<Carta> cartasEsperadas = new ArrayList<>(Arrays.asList(carta5, carta9, carta38, carta39));

        JSONArray mesaInicial = jsonObject.getJSONObject("partida").getJSONArray("rondas").getJSONObject(0).getJSONObject("inicio").getJSONArray("mesa_inicial");

        
        List<Carta> cartasParseadas = parser.parsearCartasDesdeJSON(mesaInicial);

        assertArrayEquals(cartasEsperadas.toArray(), cartasParseadas.toArray());
    }

    /** 
     * Test del metodo parsearCartasDesdeJSON de la clase JSONParser con JSON no valido null
     */
    @Test
    void testParsearCartasDesdeJSONNoValidoNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parsearCartasDesdeJSON(null);
        });
    }

    /** 
     * Test del metodo parseaCartaJSON de la clase JSONParser con JSON valido
     */
    @Test
    void testParseaCartaJSONValido(){

        String cartaJSON = jsonObject.getJSONObject("partida").getJSONArray("rondas").getJSONObject(0).getJSONArray("jugadas").getJSONObject(1).getString("juega");

        Carta cartaParseada = parser.parseaCartaJSON(cartaJSON);

        assertEquals(carta7, cartaParseada);

    }

    /** 
     * Test del metodo parseaCartaJSON de la clase GestorPartida con JSON no valido null
     */
    @Test
    void testParseaCartaJSONNoValidoNull(){

        assertThrows(IllegalArgumentException.class, () -> {
            parser.parseaCartaJSON(null);
        });
    }

    /** 
     * Test del metodo parsea de la clase Parser con lista valida
     */
    @Test
    void testParsearValido(){

        List<String> cartas= new ArrayList<>(Arrays.asList("5-oros","11-oros"));
        List<Carta> cartasParseadas=new ArrayList<>(Arrays.asList(carta5,carta9));

        assertEquals(cartasParseadas,parser.parsea(cartas));

    }

    /** 
     * Test del metodo parsea de la clase Parser con lista no valida nula
     */
    @Test
    void testParsearNoValidoListaNula(){

        List<String> cartas=null;

        assertThrows(IllegalArgumentException.class,()->{
            parser.parsea(cartas);
        });

    }

    /** 
     * Test del metodo parseaCartaJSON de la clase Parser con carta JSON de palo copas
     */
    @Tag("Cobertura")
    @Test
    void testParseaCartaJSONCopas(){

        String cartaJSON="2-copas";

        Carta cartaParseada=parser.parseaCartaJSON(cartaJSON);

        assertEquals(new Carta(12),cartaParseada);

    }

    /** 
     * Test del metodo parseaCartaJSON de la clase Parser con carta JSON de palo espadas
     */
    @Tag("Cobertura")
    @Test
    void testParseaCartaJSONEspadas(){
        
        String cartaJSON="2-espadas";

        Carta cartaParseada=parser.parseaCartaJSON(cartaJSON);

        assertEquals(new Carta(22),cartaParseada);

    }
    /** 
     * Test del metodo parseaCartaJSON de la clase Parser con carta JSON de palo bastos
     */
    @Tag("Cobertura")
    @Test
    void testParseaCartaJSONBastos(){
        
        String cartaJSON="2-bastos";

        Carta cartaParseada=parser.parseaCartaJSON(cartaJSON);

        assertEquals(new Carta(32),cartaParseada);

    }

    /** 
     * Test del metodo parseaCartaJSON de la clase Parser con carta rey copas
     */
    @Tag("Cobertura")
    @Test
    void testParseaCartaJSONCopasRey(){

        String cartaJSON="12-copas";

        Carta cartaParseada=parser.parseaCartaJSON(cartaJSON);

        assertEquals(new Carta(20),cartaParseada);

    }

    /** 
     * Test del metodo parseaCartaJSON de la clase Parser con carta rey espadas
     */
    @Tag("Cobertura")
    @Test
    void testParseaCartaJSONEspadasRey(){
        
        String cartaJSON="12-espadas";

        Carta cartaParseada=parser.parseaCartaJSON(cartaJSON);

        assertEquals(new Carta(30),cartaParseada);

    }

    /** 
     * Test del metodo parseaCartaJSON de la clase Parser con carta rey bastos
     */
    @Tag("Cobertura")
    @Test
    void testParseaCartaJSONBastosRey(){
        
        String cartaJSON="12-bastos";

        Carta cartaParseada=parser.parseaCartaJSON(cartaJSON);

        assertEquals(new Carta(40),cartaParseada);

    }

    /** 
     * Test del metodo parseaCartaJSON de la clase Parser con carta JSON que no existe
     */
    @Tag("Cobertura")
    @Test
    void testParseaCartaPaloNoExiste(){
        
        String cartaJSON="12-gnochis";

        assertThrows(IllegalArgumentException.class,()->{
            parser.parseaCartaJSON(cartaJSON);
        });


    }

    
}
