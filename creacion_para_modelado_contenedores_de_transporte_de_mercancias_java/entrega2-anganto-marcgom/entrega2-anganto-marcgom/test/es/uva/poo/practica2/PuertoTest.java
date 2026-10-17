package es.uva.poo.practica2;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import es.uva.inf.poo.maps.GPSCoordinate;
import es.uva.poo.contenedor.Estandar;


/**
 * Clase de pruebas para la clase Puerto
 * @author anganto
 * @author marcgom
 *
 */
public class PuertoTest {

	@Test
	public void testInicializarConLosArgumentos() {
		
		Puerto p = new Puerto("ES","CAT");
		
		
		assertEquals(p.getPais(), "ES");
		assertEquals(p.getLocalidad(), "CAT");
		assertFalse(p.getEstado());
		assertEquals(p.getMuelles().length, 0);
		
	}

	
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConCodigoDePaisTamanioIncorrecto() {
		
		Puerto p = new Puerto("ESP", "CAT");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConCodigoDePaisNoMayusculas() {
		
		Puerto p = new Puerto("eS", "CAT");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConCodigoDeLocalidadTamanioIncorrecto() {
		
		Puerto p = new Puerto("ES", "CA");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConCodigoDeLocalidadNoMayusculas() {
		
		Puerto p = new Puerto("ES", "Cat");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializaConCodigoDePaisNumerico() {
		
		Puerto p = new Puerto("22", "CAT");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializaConCodigoDeLocalidadNumerico() {
		
		Puerto p = new Puerto("ES", "333");
	}
	
	
	@Test
	public void testCambiarCodigoPaisValido() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		p.setPais("US");
		
		assertEquals(p.getPais(), "US");
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarCodigoPaisTamanioInvalido() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		p.setPais("ESP");
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarCodigoPaisMinusculas() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		p.setPais("Es");
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarCodigoPaisNumerico() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		p.setPais("22");
		
		
	}
	
	
	@Test
	public void testCambiarCodigoLocalidadValido() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		p.setLocalidad("BAR");
		
		assertEquals(p.getLocalidad(), "BAR");
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCambiarCodigoLocalidadTamanioInvalido() {
		
		Puerto p = new Puerto ("ES", "CAT");
		
		p.setLocalidad("CATA");
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCambiarCodigoLocalidadInvalido() {
		
		Puerto p = new Puerto ("ES", "CAT");
		
		p.setLocalidad("caT");
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCambiarCodigoLocalidadNumerico() {
		
		Puerto p = new Puerto ("ES", "CAT");
		
		p.setLocalidad("123");
		
	}
	
	@Test
	public void testCambiarEstadoDelPuerto() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		p.setEstado(true);
		
		assertTrue(p.getEstado());
	}
	
	
	@Test
	public void testCambiarMuellesDelPuerto() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		Muelle[] muelles = new Muelle[1];
		
		p.aniadirMuelle(m);
		
		muelles[0] = m;
		
		
		p.setMuelles(muelles);
		
		assertEquals(muelles.length, 1);
		assertNotNull(muelles[0]);
		assertEquals(muelles[0], m);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarMuellesDelPuertoNull() {
		
		Puerto p = new Puerto("ES", "CAT");
			
		Muelle[] muelles = null;
		
		p.setMuelles(muelles);
		
	}
	
	
	@Test
	public void testObtenerCodigoPais() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		assertEquals(p.getPais(), "ES");
	
	}
	
	@Test
	public void testObtenerCodigoLocalidad() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		assertEquals(p.getLocalidad(), "CAT");
	}
	
	@Test
	public void testObtenerIdentificador() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		assertEquals(p.getIdentificador(), "ES-CAT");
	}
	
	@Test
	public void testObtenerEstado() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		assertFalse(p.getEstado());
	}
	
	@Test
	public void testObtenerMuelles() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		Muelle[] muelles = new Muelle[1];
		
		p.aniadirMuelle(m);
		
		muelles[0] = m;
		
		assertArrayEquals(p.getMuelles(), muelles);
		
		
	}
	
	@Test
	public void testAniadirUnMuelleAlPuerto() {
		
		Puerto p = new Puerto("ES", "CAT");
			
		Muelle m = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		Muelle[] antiguo = p.getMuelles();
		
		p.aniadirMuelle(m);
		
		Muelle[] muelles = p.getMuelles();
		
		assertEquals(m.getPuerto(), p);
		assertEquals(muelles[0], m);
		assertTrue(p.getEstado());
		assertEquals(muelles.length, antiguo.length + 1);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAniadirUnMuelleNull() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m = null;
		
		p.aniadirMuelle(m);
		
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAniadirUnMuelleConMismoIdentificador() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		Muelle m2 = new Muelle(15, 45, 45, false, 2, 2, true, false, false);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAniadirUnMuelleHayUnHuecoVacio() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		Muelle m2 = new Muelle(15, 45, 45, false, 2, 2, true, false, false);
		
		p.aniadirMuelle(m1);
		
		Muelle[] muelles = p.getMuelles();
		
		muelles[0] = null;
		
		p.aniadirMuelle(m2);
		
		
		
	}
	
	
	@Test
	public void testEliminarUnMuelleValoresIntermediosArray() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, false, 1, 1, true, true, false);
		
		Muelle m2 = new Muelle(16, 40, 40, true, 1, 1, false, false, true);
		
		Muelle m3 = new Muelle(17, 40, 40, false, 1, 1, false, true, false);
		
		Muelle m4 = new Muelle(18, 40, 40, false, 1, 1, true, false, true);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		p.aniadirMuelle(m3);
		
		p.aniadirMuelle(m4);
		
		Muelle[] antes = p.getMuelles();
		
		p.eliminarMuelle(16);
		
		Muelle[] despues = p.getMuelles();
		
		assertFalse(p.getEstado());
		assertEquals(antes[0].getIdentificador(), despues[0].getIdentificador());
		assertEquals(antes[2].getIdentificador(), despues[1].getIdentificador());
		assertEquals(antes[3].getIdentificador(), despues[2].getIdentificador());
	}
	
	@Test
	public void testEliminarUnMuelleValoresInicialArray() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		Muelle m2 = new Muelle(16, 40, 40, false, 1, 1, false, false, true);
		
		Muelle m3 = new Muelle(17, 40, 40, false, 1, 1, true, false, true);
		
		Muelle m4 = new Muelle(18, 40, 40, false, 1, 1, true, false, false);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		p.aniadirMuelle(m3);
		
		p.aniadirMuelle(m4);
		
		Muelle[] antes = p.getMuelles();
		
		p.eliminarMuelle(15);
		
		Muelle[] despues = p.getMuelles();
		
		assertFalse(p.getEstado());
		assertEquals(antes[1].getIdentificador(), despues[0].getIdentificador());
		assertEquals(antes[2].getIdentificador(), despues[1].getIdentificador());
		assertEquals(antes[3].getIdentificador(), despues[2].getIdentificador());
	}
	
	@Test
	public void testEliminarUnMuelleValoresFinalArray() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, false, 1, 1, true, true, true);
		
		Muelle m2 = new Muelle(16, 40, 40, false, 1, 1, false, false, true);
		
		Muelle m3 = new Muelle(17, 40, 40, false, 1, 1, false, true, false);
		
		Muelle m4 = new Muelle(18, 40, 40, true, 1, 1, true, true, false);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		p.aniadirMuelle(m3);
		
		p.aniadirMuelle(m4);
		
		Muelle[] antes = p.getMuelles();
		
		p.eliminarMuelle(18);
		
		Muelle[] despues = p.getMuelles();
		
		assertFalse(p.getEstado());
		assertEquals(antes[0].getIdentificador(), despues[0].getIdentificador());
		assertEquals(antes[1].getIdentificador(), despues[1].getIdentificador());
		assertEquals(antes[2].getIdentificador(), despues[2].getIdentificador());
	}

	@Test
	public void testEliminarElUnicoMuelleDelArray() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		p.aniadirMuelle(m1);
		
		p.eliminarMuelle(15);
		
		Muelle[] despues = p.getMuelles();
		
		assertFalse(p.getEstado());
		assertEquals(despues.length, 0);
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testEliminarUnMuellePasandoUnIdentificadorIncorrectoMenor() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		p.aniadirMuelle(m1);
		
		p.eliminarMuelle(-500);
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testEliminarUnMuellePasandoUnIdentificadorIncorrectoMayor() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		p.aniadirMuelle(m1);
		
		p.eliminarMuelle(1000);
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testEliminarUnMuelleQueNoExisteEnElPuerto() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		p.aniadirMuelle(m1);
		
		p.eliminarMuelle(20);
		
	}
	

	@Test
	public void testComprobarPuertoCompleto() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		Estandar c = new Estandar("CSQU3054383",50,200,true,333, true);
		
		m1.asignarPlaza(0, c);
		
		p.aniadirMuelle(m1);
		
		assertTrue(p.puertoCompleto());
		
		
	}
	
	@Test
	public void testComprobarPuertoConHueco() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 2, 1, true, true, true);
		
		Estandar c = new Estandar("CSQU3054383",50,200,true,333, true);
		
		m1.asignarPlaza(0, c);
		
		p.aniadirMuelle(m1);
		
		assertFalse(p.puertoCompleto());
		
		
	}
	
	@Test
	public void testComprobarPuertoConCompletoPorFaltaTechoContenedor() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 2, 2, true, true, true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200, true,333, true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333, false);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,333, true);
		
		m1.asignarPlaza(0, c1);
		
		m1.asignarPlaza(0, c3);
		
		m1.asignarPlaza(1, c2);
		
		p.aniadirMuelle(m1);
		
		assertTrue(p.puertoCompleto());
		
		
	}
	
	
	@Test
	public void testObtenerListaDeMuellesOperativos() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		Muelle m2 = new Muelle(16, 40, 40, false, 1, 1, false, false, true);
		
		Muelle m3 = new Muelle(17, 40, 40, false, 1, 1, true, true, true);
		
		Muelle m4 = new Muelle(18, 40, 40, true, 1, 1, false, true, false);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		p.aniadirMuelle(m3);
		
		p.aniadirMuelle(m4);
		
		List<Muelle> operativos = p.muellesOperativos();
		
		assertFalse(operativos.isEmpty());
		assertTrue(operativos.contains(m1));
		assertTrue(operativos.contains(m4));
		assertEquals(operativos.size(), 2);
		
		
	}
	
	@Test
	public void testObtenerListaDeMuellesOperativosNoLosHay() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, false, 1, 1, true, true, false);
		
		Muelle m2 = new Muelle(16, 40, 40, false, 1, 1, true, true, true);
		
		Muelle m3 = new Muelle(17, 40, 40, false, 1, 1, true, true, true);
		
		Muelle m4 = new Muelle(18, 40, 40, false, 1, 1, true, true, false);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		p.aniadirMuelle(m3);
		
		p.aniadirMuelle(m4);
		
		List<Muelle> operativos = p.muellesOperativos();
		
		assertTrue(operativos.isEmpty());
	}

	@Test
	public void testObtenerListaDeMuellesOperativosTodosLoSon() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		Muelle m2 = new Muelle(16, 40, 40, true, 1, 1, false, false, true);
		
		Muelle m3 = new Muelle(17, 40, 40, true, 1, 1, false, false, true);
		
		Muelle m4 = new Muelle(18, 40, 40, true, 1, 1, false, false, true);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		p.aniadirMuelle(m3);
		
		p.aniadirMuelle(m4);
		
		List<Muelle> operativos = p.muellesOperativos();
		
		assertFalse(operativos.isEmpty());
		assertTrue(operativos.contains(m1));
		assertTrue(operativos.contains(m2));
		assertTrue(operativos.contains(m3));
		assertTrue(operativos.contains(m4));
		assertEquals(operativos.size(), 4);
	}
	
	@Test
	public void testObtenerMuellesConEspacio() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 1, true, true, true);
		
		Muelle m2 = new Muelle(16, 40, 40, true, 2, 1, true, false, true);
		
		Muelle m3 = new Muelle(17, 40, 40, true, 1, 1, false, false, true);
		
		Muelle m4 = new Muelle(18, 40, 40, true, 2, 1, false, true, false);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,456,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,200,true);
		
		Estandar c4 = new Estandar("BICU1234565",50,200,true,100,true);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		p.aniadirMuelle(m3);
		
		p.aniadirMuelle(m4);
		
		m1.asignarPlaza(0, c1);
		
		m2.asignarPlaza(1, c2);
		
		m3.asignarPlaza(0, c3);
	
		m4.asignarPlaza(0, c4);
	
		List<Muelle> conEspacio = p.muellesConEspacio();
		
		
		assertFalse(conEspacio.isEmpty());
		assertTrue(conEspacio.contains(m2));
		assertTrue(conEspacio.contains(m4));
		assertEquals(conEspacio.size(), 2);
		
	}
	
	@Test
	public void testObtenerMuellesConEspacioTodosLLenos() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 1, 1, true, true, false);
		
		Muelle m2 = new Muelle(16, 40, 40, true, 1, 1, true, true, true);
		
		Muelle m3 = new Muelle(17, 40, 40, true, 1, 1, false, false, true);
		
		Muelle m4 = new Muelle(18, 40, 40, true, 1, 1, false, true, false);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,456,true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,200,true);
		
		Estandar c4 = new Estandar("BICU1234565",50,200,true,100,true);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		p.aniadirMuelle(m3);
		
		p.aniadirMuelle(m4);
		
		m1.asignarPlaza(0, c1);
		
		m2.asignarPlaza(0, c2);
		
		m3.asignarPlaza(0, c3);
	
		m4.asignarPlaza(0, c4);
	
		List<Muelle> conEspacio = p.muellesConEspacio();
		
		
		assertTrue(conEspacio.isEmpty());
	}

	@Test
	public void testObtenerMuellesConEspacioTodosLoSon() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 2, 1,true, true, true);
		
		Muelle m2 = new Muelle(16, 40, 40, true, 2, 1,true,false,true);
		
		Muelle m3 = new Muelle(17, 40, 40, true, 2, 1,true,true, false);
		
		Muelle m4 = new Muelle(18, 40, 40, true, 2, 1, false, false, true);
		
		Estandar c1 = new Estandar("CSQU3054383",50,200,true,456, true);
		
		Estandar c2 = new Estandar("MSKU1234565",50,200,true,333,true);
		
		Estandar c3 = new Estandar("UESU4858125",50,200,true,200,true);
		
		Estandar c4 = new Estandar("BICU1234565",50,200,true,100,true);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		p.aniadirMuelle(m3);
		
		p.aniadirMuelle(m4);
		
		m1.asignarPlaza(0, c1);
		
		m2.asignarPlaza(1, c2);
		
		m3.asignarPlaza(0, c3);
	
		m4.asignarPlaza(1, c4);
	
		List<Muelle> conEspacio = p.muellesConEspacio();
		
		
		assertFalse(conEspacio.isEmpty());
		assertTrue(conEspacio.contains(m1));
		assertTrue(conEspacio.contains(m2));
		assertTrue(conEspacio.contains(m3));
		assertTrue(conEspacio.contains(m4));
		assertEquals(conEspacio.size(), 4);
	}

	
	@Test
	public void testObtenerMuellesConDistaciaInferiorAlPuntoDado(){
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle laguna = new Muelle(15, 42.0095500, -4.5240600, true, 1, 1, true, true, true);
		
		Muelle palencia = new Muelle(16, 41.5696300, -4.7200200, true, 1, 1,true, true, true);
		
		Muelle rumania = new Muelle(17, 46.0000000, 25.0000000, true, 1, 1, true, true, true);
		
		Muelle brasil = new Muelle(18, -10.0000000, -55.0000000, true, 1, 1, false, false, true);
		
		GPSCoordinate valladolid = new GPSCoordinate (41.6551800, -4.7237200);
		
		p.aniadirMuelle(laguna);
		
		p.aniadirMuelle(palencia);
		
		p.aniadirMuelle(rumania);
		
		p.aniadirMuelle(brasil);
		
		List<Muelle> inferiores = p.muellesDistanciaInferior(500, valladolid);
		
		assertFalse(inferiores.isEmpty());
		assertTrue(inferiores.contains(laguna));
		assertTrue(inferiores.contains(palencia));
		assertEquals(inferiores.size(), 2);
		
		
		
	}
	
	
	@Test
	public void testObtenerMuellesConDistaciaInferiorAlDadoNingunoLoEs(){
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle peru = new Muelle(15, 10.0000000, -76.0000000, true, 1, 1, true, true, true);
		
		Muelle madagascar = new Muelle(16, -20.0000000, 47.0000000, true, 1, 1, false, false, true);
		
		Muelle rumania = new Muelle(17, 46.0000000, 25.0000000, true, 1, 1, true, true, false);
		
		Muelle brasil = new Muelle(18, -10.0000000, -55.0000000, true, 1, 1, true, true, false);
		
		GPSCoordinate valladolid = new GPSCoordinate (41.6551800, -4.7237200);
		
		p.aniadirMuelle(peru);
		
		p.aniadirMuelle(madagascar);
		
		p.aniadirMuelle(rumania);
		
		p.aniadirMuelle(brasil);
		
		List<Muelle> inferiores = p.muellesDistanciaInferior(500, valladolid);
		
		assertTrue(inferiores.isEmpty());
		
		
		
		
	}

	@Test
	public void testObtenerMuellesConDistaciaInferiorAlPuntoTodosLosSon(){
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle laguna = new Muelle(15, 42.0095500, -4.5240600, true, 1, 1, true, true, true);
		
		Muelle palencia = new Muelle(16, 41.5696300, -4.7200200, true, 1, 1, true, false, true);
		
		Muelle zamora = new Muelle(17, 41.5063300, -5.7445600, true, 1, 1, false, true, false);
		
		Muelle burgos = new Muelle(18, 42.3502200, -3.6752700, true, 1, 1, false, true, false);
		
		GPSCoordinate valladolid = new GPSCoordinate (41.6551800, -4.7237200);
		
		p.aniadirMuelle(laguna);
		
		p.aniadirMuelle(palencia);
		
		p.aniadirMuelle(zamora);
		
		p.aniadirMuelle(burgos);
		
		List<Muelle> inferiores = p.muellesDistanciaInferior(500, valladolid);
		
		assertFalse(inferiores.isEmpty());
		assertTrue(inferiores.contains(laguna));
		assertTrue(inferiores.contains(palencia));
		assertTrue(inferiores.contains(zamora));
		assertTrue(inferiores.contains(burgos));
		assertEquals(inferiores.size(), 4);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testObtenerMuellesConDistaciaInferiorAlPuntoDistanciaNegativa(){
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle laguna = new Muelle(15, 42.0095500, -4.5240600, true, 1, 1, true, true, true);
		
		Muelle palencia = new Muelle(16, 41.5696300, -4.7200200, true, 1, 1, false, false, true);
		
		Muelle zamora = new Muelle(17, 41.5063300, -5.7445600, true, 1, 1, false, false, true);
		
		Muelle burgos = new Muelle(18, 42.3502200, -3.6752700, true, 1, 1, false, false, true);
		
		GPSCoordinate valladolid = new GPSCoordinate (41.6551800, -4.7237200);
		
		p.aniadirMuelle(laguna);
		
		p.aniadirMuelle(palencia);
		
		p.aniadirMuelle(zamora);
		
		p.aniadirMuelle(burgos);
		
		List<Muelle> inferiores = p.muellesDistanciaInferior(-1000, valladolid);
		
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testObtenerMuellesConDistaciaInferiorAlPuntoSiendoPuntoNull(){
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle laguna = new Muelle(15, 42.0095500, -4.5240600, true, 1, 1, false, false, true);
		
		Muelle palencia = new Muelle(16, 41.5696300, -4.7200200, true, 1, 1, true, false, false);
		
		Muelle zamora = new Muelle(17, 41.5063300, -5.7445600, true, 1, 1, false, true, false);
		
		Muelle burgos = new Muelle(18, 42.3502200, -3.6752700, true, 1, 1, true, false, false);
		
		GPSCoordinate valladolid = null;
		
		p.aniadirMuelle(laguna);
		
		p.aniadirMuelle(palencia);
		
		p.aniadirMuelle(zamora);
		
		p.aniadirMuelle(burgos);
		
		List<Muelle> inferiores = p.muellesDistanciaInferior(500, valladolid);
		
		
	}
	
	@Test
	public void testComprobarEstadoPuertoTodosTrue() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 2, 1, true, false, false);
		
		Muelle m2 = new Muelle(16, 40, 40, true, 2, 1, false, true, false);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		p.comprobarEstadoPuerto();
		
		assertTrue(p.getEstado());
		
		
	}
	
	@Test
	public void testComprobarEstadoPuertoTodosFalse() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, false, 2, 1, false, true, false);
		
		Muelle m2 = new Muelle(16, 40, 40, false, 2, 1, true, false, false);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		p.comprobarEstadoPuerto();
		
		assertFalse(p.getEstado());
		
		
	}
	
	@Test
	public void testComprobarEstadoPuertoTantoTrueComoFalse() {
		
		Puerto p = new Puerto("ES", "CAT");
		
		Muelle m1 = new Muelle(15, 40, 40, true, 2, 1, true, false, false);
		
		Muelle m2 = new Muelle(16, 40, 40, false, 2, 1, false, true, false);
		
		p.aniadirMuelle(m1);
		
		p.aniadirMuelle(m2);
		
		p.comprobarEstadoPuerto();
		
		assertTrue(p.getEstado());
		
		
	}
	
	@Test
	public void testComprobarDosPuertosSonIguales() {
		
		Puerto p1 = new Puerto("ES", "CAT");
		
		Puerto p2 = new Puerto("ES", "CAT");
		
		assertTrue(p1.equals(p2));
		
		
	}
	
	@Test
	public void testComprobarDosPuertosIgualesMismoPaisPeroDistintaLocalidad() {
		
		Puerto p1 = new Puerto("ES", "CAT");
		
		Puerto p2 = new Puerto("ES", "PAL");
		
		assertFalse(p1.equals(p2));
		
		
	}
	
	@Test
	public void testComprobarDosPuertosIgualesMismaLocalidadPeroDistintoPais() {
		
		Puerto p1 = new Puerto("US", "CAT");
		
		Puerto p2 = new Puerto("ES", "CAT");
		
		assertFalse(p1.equals(p2));
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testComprobarDosPuertosIgualesPuertoPasadoNull() {
		
		Puerto p1 = new Puerto("US", "CAT");
		
		Puerto p2 = null;
		
		p1.equals(p2);
		
		
	}
	
	
}
