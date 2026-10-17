package es.uva.poo.practica2;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;
import es.uva.poo.contenedor.*;
import es.uva.poo.trayectos.*;


/**
 * Clase de pruebas para la clase Muelle
 * @author anganto
 * @author marcgom
 *
 */
public class MuelleTest {

	@Test
	public void testInicializarMuelleConAtributosCorrectosCoordenadasDecimales() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		GPSCoordinate punto = new GPSCoordinate (40, 40);
		
		assertEquals(m.getIdentificador(), 15);
		assertEquals(m.getCoordenadaDecimal(), punto);
		assertTrue(m.getEstado());
		assertEquals(m.getPlazas(), 2);
		assertEquals(m.getCantidadApilable(), 1);
		assertEquals(m.getContenedores().length, 2);
		assertEquals(m.getContenedores()[0].length, 1);
		assertNull(m.getPuerto());
		assertTrue(m.getBarco());
		assertTrue(m.getCamion());
		assertTrue(m.getTren());
		
	}

	@Test
	public void testInicializarMuelleConAtributosCorrectosCoordenadasGradosMinutosSegundos() {
		
		Muelle m = new Muelle(15, "007º28'00\"N", "135º05'00\"E", true, 2, 1, true, true, true);
		
		GPSCoordinate punto = new GPSCoordinate ("007º28'00\"N", "135º05'00\"E");
		
	
		
		assertEquals(m.getIdentificador(), 15);
		assertEquals(m.getCoordenadaGMS().getLatitudeGMS(), punto.getLatitudeGMS());
		assertEquals(m.getCoordenadaGMS().getLongitudeGMS(), punto.getLongitudeGMS());
		assertTrue(m.getEstado());
		assertEquals(m.getPlazas(), 2);
		assertEquals(m.getCantidadApilable(), 1);
		assertEquals(m.getContenedores().length, 2);
		assertEquals(m.getContenedores()[0].length, 1);
		assertNull(m.getPuerto());
		assertTrue(m.getBarco());
		assertTrue(m.getCamion());
		assertTrue(m.getTren());
		
		
	}
	
	
	@Test
	public void testInicializarMuelleNoTodoTrueGradosMinutosSegundos() {
		
		Muelle m = new Muelle(15, "007º28'00\"N", "135º05'00\"E", true, 2, 1, false, false, true);
		
		
		assertFalse(m.getBarco());
		assertFalse(m.getTren());
		assertTrue(m.getCamion());
		
		
		
	}
	
	@Test
	public void testInicializarMuelleNoTodoFalseGradosMinutosSegundos() {
		
		Muelle m = new Muelle(15, "007º28'00\"N", "135º05'00\"E", true, 2, 1, false, true, true);
		
		
		assertFalse(m.getBarco());
		assertTrue(m.getTren());
		assertTrue(m.getCamion());
		
		
		
	}
	
	
	@Test(expected= IllegalArgumentException.class)
	public void testInicializarMuelleIdentidicadorMenorDecimales() {
		
		Muelle m = new Muelle(9, 40, 40, true, 2, 1, true, true, true);
		
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testInicializarMuelleIdentidicadorMayorDecimales() {
		
		Muelle m = new Muelle(100, 40, 40, true, 2, 1, true, true, true);
		
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testInicializarMuellePlazasNegativasDecimales() {
		
		Muelle m = new Muelle(15, 40, 40, true, 0, 1, true, true, true);
		
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testInicializarMuelleApilableNegativasDecimales() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, -2, true, true, true);
		
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testInicializarMuelleTodoFalseDecimales() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, false, false, false);
		
		
		
	}

	@Test(expected= IllegalArgumentException.class)
	public void testInicializarMuelleIdentificadorMenorGradosMinutosSegundos() {
		
		Muelle m = new Muelle(-22, "007º28'00\"N", "135º05'00\"E", true, 2, 1, true, true, true);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testInicializarMuelleIdentificadorMayorGradosMinutosSegundos() {
		
		Muelle m = new Muelle(500, "007º28'00\"N", "135º05'00\"E", true, 2, 1, true, true, true);
		
		
	}

	@Test(expected= IllegalArgumentException.class)
	public void testInicializarMuellePlazasNegativasGradosMinutosSegundos() {
		
		Muelle m = new Muelle(15, "007º28'00\"N", "135º05'00\"E", true, -5, 1, true, true, true);
		
		
	}

	@Test(expected= IllegalArgumentException.class)
	public void testInicializarMuelleApilableNegativasGradosMinutosSegundos() {
		
		Muelle m = new Muelle(15, "007º28'00\"N", "135º05'00\"E", true, 2, 0, true, true, true);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testInicializarMuelleTodoFalseGradosMinutosSegundos() {
		
		Muelle m = new Muelle(15, "007º28'00\"N", "135º05'00\"E", true, 2, 1, false, false, false);
		
		
	}

	@Test
	public void testCambiarIdentificador() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setIdentificador(50);
		
		assertEquals(m.getIdentificador(), 50);
		
		
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCambiarIdentificadorMenor() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setIdentificador(9);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCambiarIdentificadorMayor() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setIdentificador(1000);
		
	}
	
	@Test
	public void testCambiarCoordenadasDecimales() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setCoordenadasDecimales(80, 100);
		
		assertEquals(m.getCoordenadaDecimal().getLatitudeGD(), 80, 0.01);
		assertEquals(m.getCoordenadaDecimal().getLongitudeGD(), 100, 0.01);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCambiarCoordenadasDecimalesLatitudMal() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setCoordenadasDecimales(100, 100);
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCambiarCoordenadasDecimalesLongitudMal() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setCoordenadasDecimales(80, 200);
		
		
	}
	
	@Test
	public void testCambiarCoordenadasGMS() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setCoordenadasGMS("007º28'00\"N", "135º05'00\"E");
		
		assertEquals(m.getCoordenadaGMS().getLatitudeGMS(), "007º28'00\"N");
		assertEquals(m.getCoordenadaGMS().getLongitudeGMS(), "135º05'00\"E");
		
	}

	@Test (expected= IllegalArgumentException.class)
	public void testCambiarCoordenadasGMSLatitudMal() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setCoordenadasGMS("07º28'00\"N", "135º05'00\"E");
		
		
	}

	@Test 
	public void testCambiarEstadoSinPuerto() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setEstado(false);
		
		assertFalse(m.getEstado());
		
		
	}
	
	@Test 
	public void testCambiarEstadoConPuerto() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		p.aniadirMuelle(m);
		
		m.setEstado(false);
		
		assertFalse(m.getEstado());
		assertFalse(p.getEstado());
		
	}

	@Test 
	public void testCambiarPuerto() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setPuerto(p);
		
		assertEquals(m.getPuerto(), p);
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCambiarPuertoNull() {
		
		Puerto p = null;
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setPuerto(p);
		
		
	}

	@Test 
	public void testCambiarBarco() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setBarco(false);
		
		assertFalse(m.getBarco());
		
		
	}
	
	
	@Test 
	public void testCambiarTren() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setTren(false);
		
		assertFalse(m.getTren());
		
		
	}
	
	
	@Test 
	public void testCambiarCamion() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		m.setCamion(false);
		
		assertFalse(m.getCamion());
		
		
	}
	
	
	@Test
	public void testObtenerIdentificador() {
		
		Muelle m = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		assertEquals(m.getIdentificador(), 15);
		
		
	}
	
	@Test
	public void testObtenerCoordenadasDecimales() {
		
		Muelle m = new Muelle(15, 40, 80, true, 2, 1,true,true,true);
		
		assertEquals(m.getCoordenadaDecimal().getLatitudeGD(), 40, 0.01);
		assertEquals(m.getCoordenadaDecimal().getLongitudeGD(), 80, 0.01);
		
		
	}
	
	@Test
	public void testObtenerCoordenadasGMS() {
	
		
		Muelle m = new Muelle(15, "007º28'00\"N", "135º05'00\"E", true, 2, 1,true,true,true);
		
		assertEquals(m.getCoordenadaGMS().getLatitudeGMS(), "007º28'00\"N");
		assertEquals(m.getCoordenadaGMS().getLongitudeGMS(), "135º05'00\"E");
	
	
	}
	
	@Test
	public void testObtenerEstado() {
		
		Muelle m = new Muelle(15, 40, 80, true, 2, 1,true,true,true);
		
		assertTrue(m.getEstado());
		
		
	}
	
	@Test
	public void testObtenerPlazas() {
		
		Muelle m = new Muelle(15, 40, 80, true, 2, 1,true,true,true);
		
		assertEquals(m.getPlazas(), 2);
		
		
	}
	
	@Test
	public void testObtenerApilable() {
		
		Muelle m = new Muelle(15, 40, 80, true, 2, 1,true,true,true);
		
		assertEquals(m.getCantidadApilable(), 1);
		
		
	}

	@Test
	public void testObtenerContenedores() {
		
		Muelle m = new Muelle(15, 40, 80, true, 2, 1,true,true,true);
		
		assertEquals(m.getContenedores().length, 2);
		assertEquals(m.getContenedores()[0].length, 1);
		
	}

	@Test
	public void testObtenerPuerto() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m = new Muelle(15, 40, 80, true, 2, 1,true,true,true);
		
		p.aniadirMuelle(m);
		
		assertEquals(m.getPuerto(), p);
		
	}

	@Test
	public void testObtenerPlazasVacias() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		m.asignarPlaza(2, c1);
		
		assertEquals(m.getPlazasVacias(), 2);
		
	}

	@Test
	public void testObtenerPlazasVaciasTodas() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		assertEquals(m.getPlazasVacias(), 3);
		
	}
	
	@Test
	public void testObtenerPlazasVaciasNinguna() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		FlatRack c2 = new FlatRack("MSKU1234565",50,200,true,333);
		
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(1, c2);
		
		assertEquals(m.getPlazasVacias(), 0);
		
	}

	@Test
	public void testObtenerPlazasSemillenas() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(1, c2);
		
		
		assertEquals(m.getPlazasSemillenas(), 2);
		
	}
	
	@Test
	public void testObtenerPlazasSemillenasTodasVacias() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		
		assertEquals(m.getPlazasSemillenas(), 0);
		
	}

	@Test
	public void testObtenerPlazasSemillenasConPlazasLlenas() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(1, c3);
		
		
		assertEquals(m.getPlazasSemillenas(), 1);
		
	}
	
	@Test
	public void testObtenerPlazasSemillenasConPlazasSintecho() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,false);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,false);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(1, c3);
		
		
		assertEquals(m.getPlazasSemillenas(), 0);
		
	}
	
	@Test
	public void testObtenerPlazasLLenas() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,false);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(1, c3);
		
		
		assertEquals(m.getPlazasLLenas(), 1);
		
	}
	
	@Test
	public void testObtenerPlazasLLenasLLenasSinTecho() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,false);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,false);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,false);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(1, c2);
		m.asignarPlaza(2, c3);
		
		
		assertEquals(m.getPlazasLLenas(), 3);
		
	}
	
	@Test
	public void testObtenerPlazasLLenasEnterasConTecho() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		Estandar c4 = new Estandar("ZNMU9831129",120,100,true,100,true);
		
		Estandar c5 = new Estandar("NOYU9639514",120,100,true,100,true);
		
		Estandar c6 = new Estandar("VWXZ7418524",120,100,true,100,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(1, c2);
		m.asignarPlaza(2, c3);
		m.asignarPlaza(0, c4);
		m.asignarPlaza(1, c5);
		m.asignarPlaza(2, c6);
		
		assertEquals(m.getPlazasLLenas(), 3);
		
	}
	
	@Test
	public void testObtenerPlazasLLenasEnterasSinTecho() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		Estandar c4 = new Estandar("ZNMU9831129",120,100,true,100,false);
		
		Estandar c5 = new Estandar("NOYU9639514",120,100,true,100,false);
		
		Estandar c6 = new Estandar("VWXZ7418524",120,100,true,100,false);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(1, c2);
		m.asignarPlaza(2, c3);
		m.asignarPlaza(0, c4);
		m.asignarPlaza(1, c5);
		m.asignarPlaza(2, c6);
		
		assertEquals(m.getPlazasLLenas(), 3);
		
	}
	
	@Test
	public void testObtenerPlazaDelContenedor() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(1, c2);
		m.asignarPlaza(2, c3);
		
		
		assertEquals(m.plazaContenedor("UESU4858125"), 2);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testObtenerPlazaDelContenedorIdentificadorNull() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(1, c2);
		m.asignarPlaza(2, c3);
		
		
		m.plazaContenedor(null);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testObtenerPlazaDelContenedorNoEstaEnElMuelle() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
	
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(2, c3);
		
		
		m.plazaContenedor("MSKU1234565");
		
	}
	
	@Test
	public void testObtenerNivelDelContenedor() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,33,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(2, c3);
		
		
		assertEquals(m.nivelContenedor("MSKU1234565"), 1);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testObtenerNivelDelContenedorIdentificadorNull() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(2, c2);
		m.asignarPlaza(2, c3);
		
		
		m.nivelContenedor(null);
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testObtenerNivelDelContenedorNoEstaEnElMuelle() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
	
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(2, c3);
		
		
		m.nivelContenedor("MSKU1234565");
		
	}

	@Test
	public void testAsignarContenedor() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,false);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Refrigerado c2 = new Refrigerado("MSKU1234565",50,200,true,333);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(2, c3);
		
		
		assertEquals(m.getContenedores()[0][0], c1);
		assertEquals(m.getContenedores()[0][1], c2);
		assertEquals(m.getContenedores()[2][0], c3);
	}
	
	
	@Test
	public void testAsignarContenedorFlatRackEnMedioConContenedorDetras() {
		
		Muelle m = new Muelle(15, 40, 40, true, 4, 2,false,true,true);
		
		FlatRack c1 = new FlatRack("CSQU3054383",50,200,true,333);
		
		Refrigerado c2 = new Refrigerado("MSKU1234565",50,200,true,333);
		
		
		m.asignarPlaza(0, c2);
		m.asignarPlaza(1, c1);
		
		
		
		assertEquals(m.getContenedores()[0][0], c2);
		assertEquals(m.getContenedores()[1][0], c1);
		assertEquals(m.getContenedores()[2][0], c1);
	}
	
	
	@Test
	public void testAsignarContenedorFlatRackEnMedioConContenedorDelante() {
		
		Muelle m = new Muelle(15, 40, 40, true, 4, 2,true,false,true);
		
		FlatRack c1 = new FlatRack("CSQU3054383",50,200,true,333);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		
		m.asignarPlaza(2, c2);
		m.asignarPlaza(1, c1);
		
		
		
		assertEquals(m.getContenedores()[0][0], c1);
		assertEquals(m.getContenedores()[1][0], c1);
		assertEquals(m.getContenedores()[2][0], c2);
	}
	
	@Test
	public void testAsignarContenedorFlatRackAlInicio() {
		
		Muelle m = new Muelle(15, 40, 40, true, 4, 2,true,true,true);
		
		FlatRack c1 = new FlatRack("CSQU3054383",50,200,true,333);
		
		m.asignarPlaza(0, c1);
		
		assertEquals(m.getContenedores()[0][0], c1);
		assertEquals(m.getContenedores()[1][0], c1);

	}
	
	
	
	@Test
	public void testAsignarContenedorFlatRackAlFinal() {
		
		Muelle m = new Muelle(15, 40, 40, true, 4, 2,true,true,true);
		
		FlatRack c1 = new FlatRack("CSQU3054383",50,200,true,333);
		
		m.asignarPlaza(3, c1);
		
		assertEquals(m.getContenedores()[2][0], c1);
		assertEquals(m.getContenedores()[3][0], c1);

	}
	
	
	@Test
	public void testAsignarContenedorConTrayectosCompatiblesBarco() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mOrigen = new Muelle(16, 40, 40, true, 3, 2,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		Muelle mDestino = new Muelle(17, 40, 40, true, 3, 2,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
		
		
		Muelle m1= new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		TBarco t1 = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
		c1.asignarTrayecto(t1);
		
		m1.asignarPlaza(0, c1);
		
		
		assertEquals(m1.getContenedores()[0][0], c1);
		
	}
	
	@Test
	public void testAsignarContenedorConTrayectosCompatiblesTren() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mOrigen = new Muelle(16, 40, 40, true, 3, 2,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		Muelle mDestino = new Muelle(17, 40, 40, true, 3, 2,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
		
		
		Muelle m1= new Muelle(15, 40, 40, true, 3, 2,false,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		TTren t1 = new TTren(mOrigen, fInicio, mDestino, fFin);
		
		
		c1.asignarTrayecto(t1);
		
		m1.asignarPlaza(0, c1);
		
		
		assertEquals(m1.getContenedores()[0][0], c1);
		
	}
	
	@Test
	public void testAsignarContenedorConTrayectosCompatiblesCamion() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mOrigen = new Muelle(16, 40, 40, true, 3, 2,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		Muelle mDestino = new Muelle(17, 40, 40, true, 3, 2,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
		
		
		Muelle m1= new Muelle(15, 40, 40, true, 3, 2,true,false,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		TCamion t1 = new TCamion(mOrigen, fInicio, mDestino, fFin);
		
		
		c1.asignarTrayecto(t1);
		
		m1.asignarPlaza(0, c1);
		
		
		assertEquals(m1.getContenedores()[0][0], c1);
		
	}
	
	@Test
	public void testAsignarContenedorConTrayectosCompatiblesPackCamionTren() {
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Puerto pOrigen2 = new Puerto("ME", "CIU");
		
		Muelle mOrigen1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mOrigen2 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen1.aniadirMuelle(mOrigen1);
		pOrigen2.aniadirMuelle(mOrigen2);
		
		LocalDate fInicio1 = LocalDate.of(2025,12,10);
		
		LocalDate fInicio2 = LocalDate.of(2025,12,21);
		
		Puerto pDestino1 = new Puerto("ME", "CIU");
		
		Puerto pDestino2 = new Puerto("US", "NEW");
		
		Muelle mDestino1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mDestino2 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino1.aniadirMuelle(mDestino1);
		
		pDestino2.aniadirMuelle(mDestino2);
		
		LocalDate fFin1 = LocalDate.of(2025,12,20);
		
		LocalDate fFin2 = LocalDate.of(2025,12,31);
		
		TTren t1 = new TTren(mOrigen1, fInicio1, mDestino1, fFin1);
	
		TCamion t2 = new TCamion(mOrigen2, fInicio2, mDestino2, fFin2);
		
		Simple[] trayectos = {t1,t2};
		
		PackCamionTren combinado = new PackCamionTren(mOrigen1, fInicio1, mDestino2, fFin2, trayectos);
		
		Muelle m1= new Muelle(15, 40, 40, true, 3, 2, false, true,true);
		
		Muelle m2= new Muelle(15, 40, 40, true, 3, 2, false, true, false);
		
		Muelle m3= new Muelle(15, 40, 40, true, 3, 2, false, false, true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		

		c1.asignarTrayecto(combinado);
		
		m1.asignarPlaza(0, c1);
		m2.asignarPlaza(0, c1);
		m3.asignarPlaza(0, c1);
		
		assertEquals(m1.getContenedores()[0][0], c1);
		assertEquals(m2.getContenedores()[0][0], c1);
		assertEquals(m3.getContenedores()[0][0], c1);
	}
	
	@Test
	public void testAsignarContenedorConTrayectosCompatiblesPackCamionBarco() {
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Puerto pOrigen2 = new Puerto("ME", "CIU");
		
		Puerto pOrigen3 = new Puerto("US", "NEW");
		
		Muelle mOrigen1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mOrigen2 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mOrigen3 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen1.aniadirMuelle(mOrigen1);
		pOrigen2.aniadirMuelle(mOrigen2);
		pOrigen3.aniadirMuelle(mOrigen3);
		
		LocalDate fInicio1 = LocalDate.of(2025,12,10);
		
		LocalDate fInicio2 = LocalDate.of(2025,12,21);
		
		LocalDate fInicio3 = LocalDate.of(2026,1, 2);
		
		Puerto pDestino1 = new Puerto("ME", "CIU");
		
		Puerto pDestino2 = new Puerto("US", "NEW");
		
		Puerto pDestino3 = new Puerto("ER", "LOS");
		
		Muelle mDestino1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mDestino2 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mDestino3 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino1.aniadirMuelle(mDestino1);
		
		pDestino2.aniadirMuelle(mDestino2);
		
		pDestino3.aniadirMuelle(mDestino3);
		
		LocalDate fFin1 = LocalDate.of(2025,12,20);
		
		LocalDate fFin2 = LocalDate.of(2025,12,31);
		
		LocalDate fFin3 = LocalDate.of(2026,1,12);
		
		TBarco t1 = new TBarco(mOrigen1, fInicio1, mDestino1, fFin1);
	
		TCamion t2 = new TCamion(mOrigen2, fInicio2, mDestino2, fFin2);
		
		TBarco t3 = new TBarco(mOrigen3, fInicio3, mDestino3, fFin3);
		
		Simple[] trayectos = {t1,t2, t3};
		
		PackCamionBarco combinado = new PackCamionBarco(mOrigen1, fInicio1, mDestino3, fFin3, trayectos);
		
		Muelle m1= new Muelle(15, 40, 40, true, 3, 2, true, false,true);
		
		Muelle m2= new Muelle(15, 40, 40, true, 3, 2, true, false, false);
		
		Muelle m3= new Muelle(15, 40, 40, true, 3, 2, false, false, true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		

		c1.asignarTrayecto(combinado);
		
		m1.asignarPlaza(0, c1);
		m2.asignarPlaza(0, c1);
		m3.asignarPlaza(0, c1);
		
		assertEquals(m1.getContenedores()[0][0], c1);
		assertEquals(m2.getContenedores()[0][0], c1);
		assertEquals(m3.getContenedores()[0][0], c1);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAsignarContenedorNull() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = null;
		
		m.asignarPlaza(0, c1);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAsignarContenedorLugarNegativo() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		m.asignarPlaza(-5, c1);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAsignarContenedorLugarMayor() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		m.asignarPlaza(4, c1);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAsignarContenedorYaExiste() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(2, c2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAsignarContenedorLaPlazaEstaLLenaNoApila() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 1,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAsignarContenedorLaPlazaEstaLLenaPorTecho() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,false);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAsignarContenedorLaPlazaEstaLLena() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(0, c3);
	
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarContenedorFlatRackNoHay2PlazasVacias() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		FlatRack c1 = new FlatRack("CSQU3054383",50,200,true,333);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		
		m.asignarPlaza(0, c2);
		m.asignarPlaza(2, c3);
		m.asignarPlaza(1, c1);
		

	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarContenedorFlatRackNoHay2PlazasVaciasContiguasEnMedio() {
		
		Muelle m = new Muelle(15, 40, 40, true, 5, 2,true,true,true);
		
		FlatRack c1 = new FlatRack("CSQU3054383",50,200,true,333);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		
		
		
		m.asignarPlaza(3, c2);
		m.asignarPlaza(1, c3);
		m.asignarPlaza(2, c1);
		

	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarContenedorFlatRackNoHay2PlazasVaciasContiguasInicio() {
		
		Muelle m = new Muelle(15, 40, 40, true, 5, 2,true,true,true);
		
		FlatRack c1 = new FlatRack("CSQU3054383",50,200,true,333);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		m.asignarPlaza(1, c2);
		m.asignarPlaza(0, c1);
		

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarContenedorFlatRackNoHay2PlazasVaciasContiguasFinal() {
		
		Muelle m = new Muelle(15, 40, 40, true, 5, 2,true,true,true);
		
		FlatRack c1 = new FlatRack("CSQU3054383",50,200,true,333);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		m.asignarPlaza(3, c2);
		m.asignarPlaza(4, c1);
		

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarContenedorFlatRackHayUnContenedorEnLaPlaza() {
		
		Muelle m = new Muelle(15, 40, 40, true, 5, 2,true,true,true);
		
		FlatRack c1 = new FlatRack("CSQU3054383",50,200,true,333);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		m.asignarPlaza(2, c2);
		m.asignarPlaza(2, c1);
		

	}
	
	
	@Test(expected= IllegalArgumentException.class)
	public void testAsignarContenedorConTrayectosNoCompatiblesBarco() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mOrigen = new Muelle(16, 40, 40, true, 3, 2,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		Muelle mDestino = new Muelle(17, 40, 40, true, 3, 2,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
		
		
		Muelle m1= new Muelle(15, 40, 40, true, 3, 2, false,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		TBarco t1 = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
		c1.asignarTrayecto(t1);
		
		m1.asignarPlaza(0, c1);
		
	}
	
	
	@Test(expected= IllegalArgumentException.class)
	public void testAsignarContenedorConTrayectosNoCompatiblesTren() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mOrigen = new Muelle(16, 40, 40, true, 3, 2,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		Muelle mDestino = new Muelle(17, 40, 40, true, 3, 2,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
		
		
		Muelle m1= new Muelle(15, 40, 40, true, 3, 2, false,false,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		TTren t1 = new TTren(mOrigen, fInicio, mDestino, fFin);
		
		
		c1.asignarTrayecto(t1);
		
		m1.asignarPlaza(0, c1);
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testAsignarContenedorConTrayectosNoCompatiblesCamion() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mOrigen = new Muelle(16, 40, 40, true, 3, 2,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		Muelle mDestino = new Muelle(17, 40, 40, true, 3, 2,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
		
		
		Muelle m1= new Muelle(15, 40, 40, true, 3, 2, false,true,false);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		TCamion t1 = new TCamion(mOrigen, fInicio, mDestino, fFin);
		
		
		c1.asignarTrayecto(t1);
		
		m1.asignarPlaza(0, c1);
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testAsignarContenedorConTrayectosIncompatiblesPackCamionBarco() {
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Puerto pOrigen2 = new Puerto("ME", "CIU");
		
		Puerto pOrigen3 = new Puerto("US", "NEW");
		
		Muelle mOrigen1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mOrigen2 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mOrigen3 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen1.aniadirMuelle(mOrigen1);
		pOrigen2.aniadirMuelle(mOrigen2);
		pOrigen3.aniadirMuelle(mOrigen3);
		
		LocalDate fInicio1 = LocalDate.of(2025,12,10);
		
		LocalDate fInicio2 = LocalDate.of(2025,12,21);
		
		LocalDate fInicio3 = LocalDate.of(2026,1, 2);
		
		Puerto pDestino1 = new Puerto("ME", "CIU");
		
		Puerto pDestino2 = new Puerto("US", "NEW");
		
		Puerto pDestino3 = new Puerto("ER", "LOS");
		
		Muelle mDestino1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mDestino2 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mDestino3 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino1.aniadirMuelle(mDestino1);
		
		pDestino2.aniadirMuelle(mDestino2);
		
		pDestino3.aniadirMuelle(mDestino3);
		
		LocalDate fFin1 = LocalDate.of(2025,12,20);
		
		LocalDate fFin2 = LocalDate.of(2025,12,31);
		
		LocalDate fFin3 = LocalDate.of(2026,1,12);
		
		TBarco t1 = new TBarco(mOrigen1, fInicio1, mDestino1, fFin1);
	
		TCamion t2 = new TCamion(mOrigen2, fInicio2, mDestino2, fFin2);
		
		TBarco t3 = new TBarco(mOrigen3, fInicio3, mDestino3, fFin3);
		
		Simple[] trayectos = {t1,t2, t3};
		
		PackCamionBarco combinado = new PackCamionBarco(mOrigen1, fInicio1, mDestino3, fFin3, trayectos);
		
		Muelle m1= new Muelle(15, 40, 40, true, 3, 2, false, true,false);
		
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		

		c1.asignarTrayecto(combinado);
		
		m1.asignarPlaza(0, c1);
	}
	
	
	@Test(expected= IllegalArgumentException.class)
	public void testAsignarContenedorConTrayectosInCompatiblesPackCamionTren() {
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Puerto pOrigen2 = new Puerto("ME", "CIU");
		
		Muelle mOrigen1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mOrigen2 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen1.aniadirMuelle(mOrigen1);
		pOrigen2.aniadirMuelle(mOrigen2);
		
		LocalDate fInicio1 = LocalDate.of(2025,12,10);
		
		LocalDate fInicio2 = LocalDate.of(2025,12,21);
		
		Puerto pDestino1 = new Puerto("ME", "CIU");
		
		Puerto pDestino2 = new Puerto("US", "NEW");
		
		Muelle mDestino1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mDestino2 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino1.aniadirMuelle(mDestino1);
		
		pDestino2.aniadirMuelle(mDestino2);
		
		LocalDate fFin1 = LocalDate.of(2025,12,20);
		
		LocalDate fFin2 = LocalDate.of(2025,12,31);
		
		TTren t1 = new TTren(mOrigen1, fInicio1, mDestino1, fFin1);
	
		TCamion t2 = new TCamion(mOrigen2, fInicio2, mDestino2, fFin2);
		
		Simple[] trayectos = {t1,t2};
		
		PackCamionTren combinado = new PackCamionTren(mOrigen1, fInicio1, mDestino2, fFin2, trayectos);
		
		Muelle m1= new Muelle(15, 40, 40, true, 3, 2, true, false,false);
		
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		

		c1.asignarTrayecto(combinado);
		
		m1.asignarPlaza(0, c1);
	
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testAsignarRefrigeradorSinCamionNiBarco() {
				
		Muelle m1= new Muelle(15, 40, 40, true, 3, 2, false,true,false);
		
		Refrigerado c1 = new Refrigerado("CSQU3054383",50,200,true,333);
		
		
		m1.asignarPlaza(0, c1);
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testAsignarRefrigeradorSinTrenNiBarco() {
				
		Muelle m1= new Muelle(15, 40, 40, true, 3, 2, false,false,true);
		
		FlatRack c1 = new FlatRack("CSQU3054383",50,200,true,333);
		
		
		m1.asignarPlaza(0, c1);
		
	}
	
	
	@Test
	public void testEliminarContenedor() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2, true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(2, c3);
		
		m.sacarContenedor(0, 1);
		
		assertEquals(m.getContenedores()[0][0], c1);
		assertNull(m.getContenedores()[0][1]);
		assertEquals(m.getContenedores()[2][0], c3);
	}
	
	@Test
	public void testEliminarContenedorNoEstandoArribaDelTodo() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 3,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(2, c3);
		
		m.sacarContenedor(0, 1);
		
		assertEquals(m.getContenedores()[0][0], c1);
		assertNull(m.getContenedores()[0][1]);
		assertEquals(m.getContenedores()[2][0], c3);
	}
	
	
	
	@Test
	public void testEliminarContenedorFlatRack() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2, true,true,true);
		
		FlatRack c1 = new FlatRack("CSQU3054383",50,200,true,333);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(2, c2);
		
		m.sacarContenedor(0, 0);
		
		assertNull(m.getContenedores()[0][0]);
		assertNull(m.getContenedores()[1][0]);
		assertEquals(m.getContenedores()[2][0], c2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEliminarContenedorPlazaNegativa() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(2, c3);
		
		m.sacarContenedor(-4, 1);
	
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEliminarContenedorPlazaMayor() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(2, c3);
		
		m.sacarContenedor(3, 1);
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEliminarContenedorAlturaNegativa() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(2, c3);
		
		m.sacarContenedor(2, -3);
	
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEliminarContenedorAlturaMayor() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(2, c3);
		
		m.sacarContenedor(2, 2);
	
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEliminarContenedorNoHay() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(2, c3);
		
		m.sacarContenedor(1, 0);
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEliminarContenedorTieneUnoEncima() {
		
		Muelle m = new Muelle(15, 40, 40, true, 3, 2,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(2, c3);
		
		m.sacarContenedor(0, 0);
	
	}

	@Test
	public void testComprobarMuelleLLeno() {
		
		Muelle m = new Muelle(15, 40, 40, true, 1, 3,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		m.asignarPlaza(0, c3);
		
		assertTrue(m.muelleLLeno());
	
	}
	
	@Test
	public void testComprobarMuelleLLenoPorTecho() {
		
		Muelle m = new Muelle(15, 40, 40, true, 1, 3,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,false);
		
		
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		
		assertTrue(m.muelleLLeno());
	
	}

	@Test
	public void testComprobarMuelleLLenoNoLoEsta() {
		
		Muelle m = new Muelle(15, 40, 40, true, 1, 3,true,true,true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,333,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		m.asignarPlaza(0, c1);
		m.asignarPlaza(0, c2);
		
		assertFalse(m.muelleLLeno());
	
	}
	
	@Test
	public void testComprobarMuelleIgualAOtroConPuerto() {
		
		Puerto p1 = new Puerto("ES", "CAT");
		
		Puerto p2 = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 3,true,true,true);
		
		Muelle m2 = new Muelle(15, 80, 100, false, 2, 5,true,true,true);
		
		p1.aniadirMuelle(m1);
		p2.aniadirMuelle(m2);
		
		assertTrue(m1.equals(m2));
	
	}
	
	@Test
	public void testComprobarMuelleIgualAOtroSinPuerto() {
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 3,true,true,true);
		
		Muelle m2 = new Muelle(15, 40, 40, false, 2, 5,true,true,true);
		
		
		assertTrue(m1.equals(m2));
	
	}
	
	@Test
	public void testComprobarMuelleDistintoAOtroConMismoPuerto() {
		
		Puerto p1 = new Puerto("ES", "CAT");
		
		Puerto p2 = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 3,true,true,true);
		
		Muelle m2 = new Muelle(16, 80, 100, false, 2, 5,true,true,true);
		
		p1.aniadirMuelle(m1);
		p2.aniadirMuelle(m2);
		
		assertFalse(m1.equals(m2));
	
	}
	
	@Test
	public void testComprobarMuelleDistintoAOtroConDsitintoPuerto() {
		
		Puerto p1 = new Puerto("ES", "CAT");
		
		Puerto p2 = new Puerto("US", "NEW");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 3,true,true,true);
		
		Muelle m2 = new Muelle(15, 80, 100, false, 2, 5,true,true,true);
		
		p1.aniadirMuelle(m1);
		p2.aniadirMuelle(m2);
		
		assertFalse(m1.equals(m2));
	
	}
	
	@Test
	public void testComprobarMuelleDistintoAOtroConDistintoPuertoEIdentificador() {
		
		Puerto p1 = new Puerto("ES", "CAT");
		
		Puerto p2 = new Puerto("US", "NEW");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 3,true,true,true);
		
		Muelle m2 = new Muelle(16, 80, 100, false, 2, 5,true,true,true);
		
		p1.aniadirMuelle(m1);
		p2.aniadirMuelle(m2);
		
		assertFalse(m1.equals(m2));
	
	}

	@Test
	public void testComprobarMuelleDistintoAOtroConUnoSinPuerto() {
		
		Puerto p1 = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 3,true,true,true);
		
		Muelle m2 = new Muelle(16, 80, 100, false, 2, 5,true,true,true);
		
		p1.aniadirMuelle(m1);
		
		
		assertFalse(m1.equals(m2));
	
	}

	@Test
	public void testComprobarMuelleDistintoAOtroConUnoSinPuertoAlReves() {
		
		Puerto p1 = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 3,true,true,true);
		
		Muelle m2 = new Muelle(16, 80, 100, false, 2, 5,true,true,true);
		
		p1.aniadirMuelle(m2);
		
		
		assertFalse(m1.equals(m2));
	
	}
	
	@Test
	public void testComprobarMuelleIgualAOtroSinPuertoSoloIdentificadorIgual() {
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 3,true,true,true);
		
		Muelle m2 = new Muelle(15, 80, 100, false, 2, 5,true,true,true);
		
		
		assertFalse(m1.equals(m2));
	
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testComprobarMuellesIgualesNull() {
		
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 3,true,true,true);
		
		Muelle m2 = null;
		
		m1.equals(m2);
	
	}
}
