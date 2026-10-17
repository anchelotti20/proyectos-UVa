package uva.tds;

/**
 * Clase de test para la clase Mesa
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

class MesaTest {
    
    /**
     * Atributos de la clase MesaTest
     * Usados en la Fixture para pruebas comunes en test
     */
    private  Carta carta1;
    private  Carta carta2;
    private  Carta carta3;
    private  Carta carta4;
    private List<Carta> cartasMesaInicial;
    private List<Carta> cartasMesa;
    private Mesa mesa;

    /**
     * Fixture para inicializar objetos comunes a varios tests
     */
    @BeforeEach
    void setUp() {
        carta1 = new Carta(1);
        carta2 = new Carta(2);
        carta3 = new Carta(3);
        carta4 = new Carta(4);
        cartasMesaInicial = new ArrayList<>();
        cartasMesaInicial.add(carta1);
        cartasMesaInicial.add(carta2);
        cartasMesaInicial.add(carta3);
        cartasMesaInicial.add(carta4);
        mesa = new Mesa(cartasMesaInicial);

        cartasMesa = new ArrayList<>();
        cartasMesa.add(carta1);
        cartasMesa.add(carta2);
        cartasMesa.add(carta3);
    }

    /** 
     * Test del constructor de la clase Mesa valido
     */
    @Test
    void testConstructorMesaValido() {
    
        assertArrayEquals(cartasMesaInicial.toArray(), mesa.getCartas().toArray());
        assertEquals(4, mesa.getCartas().size());

    }

    /** 
     * Test del constructor de la clase Mesa no valido con lista nula
     */
    @Test
    void testConstructorMesaNoValidoNull() {

        assertThrows(IllegalArgumentException.class, () -> new Mesa(null));
    }

    /** 
     * Test del constructor de la clase Mesa no valido con lista vacia
     */
    @Test
    void testConstructorMesaNoValidoVacio() {

        List<Carta> cartasVacias = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Mesa(cartasVacias));
    
    }


    /** 
     * Test del constructor de la clase Mesa no valido con menos de 4 cartas
     */
    @Test
    void testConstructorMesaNoValidoMenosDe4() {
        
        assertThrows(IllegalArgumentException.class, () -> new Mesa(cartasMesa));
        
    }

    /** 
     * Test del constructor de la clase Mesa no valido con mas de 4 cartas
     */
    @Test
    void testConstructorMesaNoValidoMasDe4() {

        List<Carta> cartasMasDe4 = new ArrayList<>(cartasMesaInicial);
        cartasMasDe4.add(new Carta(5));

        assertThrows(IllegalArgumentException.class, () -> new Mesa(cartasMasDe4));

    }

    /** 
     * Test del metodo getCartas de la clase Mesa
     */
    @Test
    void testGetCartasValido() {
        
        mesa.setCartas(cartasMesa);

        assertArrayEquals(cartasMesa.toArray(), mesa.getCartas().toArray());
        assertEquals(3, mesa.getCartas().size());

    }

    /** 
     * Test del metodo setCartas de la clase Mesa valido
     */
    @Test
    void testSetCartasValido() {

        List<Carta> nuevasCartas = new ArrayList<>();
        nuevasCartas.add(carta1);
        nuevasCartas.add(carta4);
        mesa.setCartas(nuevasCartas);

        assertArrayEquals(nuevasCartas.toArray(), mesa.getCartas().toArray());
        assertEquals(2, mesa.getCartas().size());

    }

    /** 
     * Test del metodo setCartas de la clase Mesa no valido con lista nula
     */
    @Test
    void testSetCartasNoValidoNull() {

        List<Carta> nuevasCartas = null;
        assertThrows(IllegalArgumentException.class, () -> mesa.setCartas(nuevasCartas));

    }

    /** 
     * Test del metodo setCartas de la clase Mesa no valido con lista vacia
     */
    @Test
    void testSetCartasNoValidoVacio() {

        List<Carta> nuevasCartas = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> mesa.setCartas(nuevasCartas));

    }

    /** 
     * Test del metodo quitarCartaMesa de la clase Mesa valido
     */
    @Test
    void testQuitarCartaMesaValido(){

        List<Carta> cartaAQuitar = new ArrayList<>();
        cartaAQuitar.add(carta4);
        
        mesa.quitarCartaMesa(cartaAQuitar);
        
        assertEquals(3, mesa.getCartas().size());

    }

    /**
     * Test del metodo quitarCartaMesa de la clase Mesa no valido carta no existe
     */
    @Test
    void testQuitarCartaMesaNoValidoNoExiste(){

        List<Carta> cartaAQuitar = new ArrayList<>();
        cartaAQuitar.add(new Carta(5));

        assertThrows(IllegalArgumentException.class, () -> mesa.quitarCartaMesa(cartaAQuitar));
        

    }

    /**
     * Test del metodo quitarCartaMesa de la clase Mesa no valido carta nula
     */
    @Test
    void testQuitarCartaMesaNoValidoNull(){

        assertThrows(IllegalArgumentException.class, () -> mesa.quitarCartaMesa(null));
        

    }

    /**
     * Test del metodo aniadirCartaMesa de la clase Mesa no valido carta nula
     */
    @Test
    void aniadirCartaMesaNoValidoNulo(){
        assertThrows(IllegalArgumentException.class, () -> mesa.aniadirCartaMesa(null));
    }

    /**
     * Test del metodo aniadirCartaMesa de la clase Mesa valido
     */
    @Test
    void aniadirCartaMesaValida(){

        Carta cartAniadir=new Carta(5);
        mesa.aniadirCartaMesa(cartAniadir);
        assertEquals(5,mesa.getCartas().size());

    }

    /**
     * Test del metodo contieneCarta de la clase Mesa no valido carta no esta en mesa
     */
    @Tag("Cobertura")
    @Test
    void testcontieneCartaNoValidoNoEstaEnMesa(){

        Carta cartaNoEsta=new Carta(10);
        assertThrows(IllegalArgumentException.class, () -> mesa.contieneCarta(cartaNoEsta));

    }


}
