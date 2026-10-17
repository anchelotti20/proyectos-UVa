package es.uva.poo.contenedor;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import es.uva.poo.practica2.Muelle;
import es.uva.poo.practica2.Puerto;
import es.uva.poo.trayectos.*;


/**
 * Clase de pruebas para la clase Contenedor
 * @author anganto
 * @author marcgom
 *
 */
public class ContenedorTest {

	@Test
	public void testInicializarConLosArgumentosEstandar() {
		
		Estandar c= new Estandar("CSQU3054383",50,200,true,333,true);
		
		assertEquals(c.getIdentificador(),"CSQU3054383");
		assertEquals(c.getTaraKg(),50.00,0.0001);
		assertEquals(c.getMetrosCubicos(),200.00,0.0001);
		assertTrue(c.getTransito());
		assertTrue(c.getTecho());
		assertEquals(c.getMaximaCargaUtil(),333.000,0.0001);
	}
	
	@Test
	public void testInicializarConLosArgumentosRefrigerado() {
		
		Refrigerado c= new Refrigerado("CSQU3054383",50,200,true,333);
		
		assertEquals(c.getIdentificador(),"CSQU3054383");
		assertEquals(c.getTaraKg(),50.00,0.0001);
		assertEquals(c.getMetrosCubicos(),200.00,0.0001);
		assertTrue(c.getTransito());
		assertEquals(c.getMaximaCargaUtil(),333.000,0.0001);
	}
	
	@Test
	public void testInicializarConLosArgumentosFlatRack() {
		
		FlatRack c= new FlatRack("CSQU3054383",50,200,true,333);
		
		assertEquals(c.getIdentificador(),"CSQU3054383");
		assertEquals(c.getTaraKg(),50.00,0.0001);
		assertEquals(c.getMetrosCubicos(),200.00,0.0001);
		assertTrue(c.getTransito());
		assertEquals(c.getMaximaCargaUtil(),333.000,0.0001);
	}
	
	
	@Test
	public void testVerificadorIdentificadorCorrecto1() {
		
		Estandar c = new Estandar("MSKU1234565",120,100,true,100,true);
		
		assertTrue(c.verificacionIdentificador("MSKU1234565"));
		
	}
	
	
	
	
	@Test
	public void testVerificadorIdentificadorCorrecto2() {
		
		Estandar c = new Estandar("UESU4858125",120,100,true,100,true);
		
		assertTrue(c.verificacionIdentificador("UESU4858125"));
		
	}
	
	@Test
	public void testVerificadorIdentificadorCorrecto3() {
		
		Estandar c = new Estandar("BICU1234565",120,100,true,100,true);
		
		assertTrue(c.verificacionIdentificador("BICU1234565"));
		
	}
	
	
	
	
	@Test
	public void testVerificadorIdentificadorCorrecto4() {
		
		Estandar c = new Estandar("PLSU8723107",120,100,true,100,true);
		
		assertTrue(c.verificacionIdentificador("PLSU8723107"));
		
	}
	
	
	
	@Test
	public void testVerificadorIdentificadorCorrecto5LetraJ() {
		
		Estandar c = new Estandar("MSKJ1234568",120,100,true,100,true);
		
		assertTrue(c.verificacionIdentificador("MSKJ1234568"));
		
	}
	
	
	
	
	@Test
	public void testVerificadorIdentificadorCorrecto6LetraZ() {
		
		Estandar c = new Estandar("PLSZ8723100",120,100,true,100,true);
		
		assertTrue(c.verificacionIdentificador("PLSZ8723100"));
		
	}
	
	
	
	
	
	@Test
	public void testVerificadorIdentificadorCorrecto7() {
		
		Estandar c = new Estandar("ADFJ1237891",120,100,true,100,true);
		
		assertTrue(c.verificacionIdentificador("ADFJ1237891"));
		
	}
	
	
	
	
	@Test
	public void testVerificadorIdentificadorCorrecto8() {
		
		Estandar c = new Estandar("GHNZ7891232",120,100,true,100,true);
		
		assertTrue(c.verificacionIdentificador("GHNZ7891232"));
		
	}
	
	
	
	
	@Test
	public void testVerificadorIdentificadorCorrecto9() {
		
		Estandar c = new Estandar("ORTU4567890",120,100,true,100,true);
		
		assertTrue(c.verificacionIdentificador("ORTU4567890"));
		
	}
	
	
	
	
	@Test
	public void testVerificadorIdentificadorCorrecto10() {
		
		Estandar c = new Estandar("VWXZ7418524",120,100,true,100,true);
		
		assertTrue(c.verificacionIdentificador("VWXZ7418524"));
		
	}
	
	
	
	@Test
	public void testVerificadorIdentificadorCorrecto11() {
		
		Estandar c = new Estandar("NOYU9639514",120,100,true,100,true);
		
		assertTrue(c.verificacionIdentificador("NOYU9639514"));
		
	}
	
	
	@Test
	public void testVerificadorIdentificadorCorrecto12() {
		
		Estandar c = new Estandar("ZNMU9831129",120,100,true,100,true);
		
		assertTrue(c.verificacionIdentificador("ZNMU9831129"));
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarContenedorConDigitoControlIncorrecto() {
		
		Estandar c = new Estandar("CSQU3054389",50,200,true,333,true);
		
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarContenedorConNumerosEnLugarDeLetras() {
		
		Estandar c = new Estandar("9SQU3054389",50,200,true,333,true);
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarContenedorConLetrasMinusculas() {
		
		Estandar c = new Estandar("csqU3054389",50,200,true,333,true);
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarContenedorIdentificadorNull() {
		
		Estandar c = new Estandar(null,50,200,true,333,true);
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarContenedorMetrosCubicosIgualCero() {
		
		Estandar c = new Estandar("CSQU3054383",50,0,true,333,true);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarContenedorMetrosCubicosNegativo() {
		
		Estandar c = new Estandar("CSQU3054383",50,-100,true,333,true);
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarContenedorPesoKilogramosEsIgualCero() {
		
		Estandar c = new Estandar("CSQU3054383",0,100,true,333,true);
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarContenedorPesoKilogramosNegativo() {
		
		Estandar c = new Estandar("CSQU3054383",-120,100,true,333,true);
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarContenedorMaximaCargaUtilCero() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,0,true);
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarContenedorMaximaCargaUtilNegativa() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,-20,true);
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testVerificadorIdentificadorMasLongitud() {
		
		Estandar c = new Estandar("CSQU305438323",120,100,true,10,true);
		
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testVerificadorIdentificadorLetrasEquipamientoErroneas1() {
		
		Estandar c = new Estandar("CSQM3054383",120,100,true,100,true);
		
		
	}
	
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testVerificadorIdentificadorLetrasEquipamientoErroneas2() {
		
		Estandar c = new Estandar("CSQZ3054383",120,100,true,80,true);
		
		c.verificacionIdentificador("CSQZ3054383");
		
	}
	
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testVerificadorIdentificadorLetrasEquipamientoInorrectas() {
		
		Estandar c = new Estandar("CSQJ3054383",120,100,true,80,true);
		
		
		
	}
	
	@Test
	public void testGetIdentificador() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		assertEquals(c.getIdentificador(),"CSQU3054383");
		
		
		
	}
	
	
	
	@Test
	public void testSetIdentificador() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setIdentificador("BICU1234565");
		assertEquals(c.getIdentificador(),"BICU1234565");
		
		
		
	}
	
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetIdentificadorIncorrecto() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setIdentificador("BICU1234564");
		assertEquals(c.getIdentificador(),"BICU1234564");
		
		
		
	}
	
	
	
	
	@Test
	public void testGetTaraKgCorrecto() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
		assertEquals(c.getTaraKg(),120.00,0.0001);
		
		
		
	}
	
	
	
	
	
	
	
	@Test
	public void testSetTaraKgCorrecto() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setTaraKg(500);
		assertEquals(c.getTaraKg(),500.00,0.0001);
		
		
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetTaraKgPesoNegativo() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setTaraKg(-500);
		
		
		
		
	}
	
	
	
	
	@Test
	public void testGetTaraLb() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
		assertEquals(c.getTaraLb(),264.555,0.1);
		
		
		
		
	}
	
	
	
	
	@Test
	public void testSetMetrosCubicos() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setMetrosCubicos(333);
		assertEquals(c.getMetrosCubicos(),333,0.1);
		
		
		
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetMetrosCubicosNegativos() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setMetrosCubicos(-333);
		
		
		
		
	}
	
	
	
	
	@Test
	public void testGetPiesCubicosCorrecto() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
		assertEquals(c.getPiesCubicos(),3531.467,0.1);
		
		
	}
	
	
	
	
	@Test
	public void testSetTransito() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setTransito(false);
		assertFalse(c.getTransito());
		
		
	}
	
	
	
	
	@Test
	public void testGetTransito() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
		assertTrue(c.getTransito());
		
		
	}
	
	
	
	@Test
	public void testGetTechoEstandar() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,false);
		
		assertFalse(c.getTecho());
	}
	
	@Test
	public void testGetTechoRefrigerado() {
		
		Refrigerado c = new Refrigerado("CSQU3054383",120,100,true,80);
		
		assertTrue(c.getTecho());
	}
	
	@Test
	public void testGetTechoFlatRack() {
		
		FlatRack c = new FlatRack("CSQU3054383",120,100,true,80);
		
		assertFalse(c.getTecho());
	}
	
	@Test
	public void testGetMotorEstandar() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
		assertFalse(c.getMotor());
	}
	
	@Test
	public void testGetMotorRefrigerado() {
		
		Refrigerado c = new Refrigerado("CSQU3054383",120,100,true,80);
		
		assertTrue(c.getMotor());
	}
	
	@Test
	public void testGetMotorFlatRack() {
		
		FlatRack c = new FlatRack("CSQU3054383",120,100,true,80);
		
		assertFalse(c.getMotor());
	}
	
	@Test
	public void testGetParedesEstandar() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,false);
		
		assertTrue(c.getParedes());
	}
	
	@Test
	public void testGetParedesRefrigerado() {
		
		Refrigerado c = new Refrigerado("CSQU3054383",120,100,true,80);
		
		assertTrue(c.getParedes());
	}
	
	@Test
	public void testGetParedesFlatRack() {
		
		FlatRack c = new FlatRack("CSQU3054383",120,100,true,80);
		
		assertFalse(c.getParedes());
	}
	
	@Test
	public void testCargaMaximaUtil() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setMaximaCargaUtil(900);
		assertEquals(c.getMaximaCargaUtil(),900,0.01);
		
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCargaMaximaUtilNegativa() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setMaximaCargaUtil(-900);
		
		
		
	}
	
	
	@Test
	public void testObtenerPrecio() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(19, 50, 20, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
																	
		LocalDate fFin = LocalDate.of(2025,11,20);
	
		TBarco t1 = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		c.asignarTrayecto(t1);
		
		assertEquals(c.getPrecioContenedor(), 40000, 0.1);
		
		
		
	}
	
	@Test
	public void testObtenerPrecioConTrayectosSimplesYCombinados() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(19, 50, 20, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
																	
		LocalDate fFin = LocalDate.of(2025,11,20);
	
		TBarco t1 = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Puerto pOrigen2 = new Puerto("ME", "CIU");
		
		Puerto pOrigen3 = new Puerto("US", "NEW");
		
		Muelle mOrigen1 = new Muelle(15, 20, 20, true, 1, 1,true,true,true);
		
		Muelle mOrigen2 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mOrigen3 = new Muelle(15, 80, 80, true, 1, 1,true,true,true);
		
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
		
		Muelle mDestino2 = new Muelle(15, 80, 80, true, 1, 1,true,true,true);
		
		Muelle mDestino3 = new Muelle(15, 85, 85, true, 1, 1,true,true,true);
		
		pDestino1.aniadirMuelle(mDestino1);
		
		pDestino2.aniadirMuelle(mDestino2);
		
		pDestino3.aniadirMuelle(mDestino3);
		
		LocalDate fFin1 = LocalDate.of(2025,12,20);
		
		LocalDate fFin2 = LocalDate.of(2025,12,31);
		
		LocalDate fFin3 = LocalDate.of(2026,1,12);
		
		TBarco t2 = new TBarco(mOrigen1, fInicio1, mDestino1, fFin1);
	
		TCamion t3 = new TCamion(mOrigen2, fInicio2, mDestino2, fFin2);
		
		TBarco t4 = new TBarco(mOrigen3, fInicio3, mDestino3, fFin3);
		
		Simple[] trayectos = {t2,t3, t4};
		
		PackCamionBarco combinado = new PackCamionBarco(mOrigen1, fInicio1, mDestino3, fFin3, trayectos);
		
		c.asignarTrayecto(t1);
		c.asignarTrayecto(combinado);
		
		
		assertEquals(c.getPrecioContenedor(), 129607.4828, 0.1);
		
		
		
	}
	
	@Test
	public void testSetTrayectosArraySimple() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t1 = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
		ArrayList<Trayecto> trayectos = new ArrayList<>();
		
		trayectos.add(t1);
		
		c.setTrayectos(trayectos);
		
		assertEquals(c.getTrayectos(),trayectos);
			
	}
	
	@Test
	public void testSetTrayectosArrayCombinado() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
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
		
		Simple[] trayectos1 = {t1,t2};
		
		PackCamionTren combinado = new PackCamionTren(mOrigen1, fInicio1, mDestino2, fFin2, trayectos1);
		
		
		ArrayList<Trayecto> trayectos2 = new ArrayList<>();
		
		trayectos2.add(t1);
		
		c.setTrayectos(trayectos2);
		
		assertEquals(c.getTrayectos(),trayectos2);
			
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetTrayectosArrayNull() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
		
		ArrayList<Trayecto> trayectos = null;
		
		
		
		c.setTrayectos(trayectos);
		
	

	}
	
	
	
	@Test
	public void testAsignarTrayectoEstandar() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Puerto pOrigen2 = new Puerto("ES", "BAR");
		
		Muelle mOrigen1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mOrigen2 = new Muelle(16, 80, 100, true, 3, 2,true,true,true);
		
		pOrigen1.aniadirMuelle(mOrigen1);
		pOrigen2.aniadirMuelle(mOrigen2);
		
		LocalDate fInicio1 = LocalDate.of(2025,11,10);
		
		LocalDate fInicio2 = LocalDate.of(2025,12,10);
		
		Puerto pDestino1 = new Puerto("US", "NEW");
		
		Puerto pDestino2 = new Puerto("US", "WAS");
		
		Muelle mDestino1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mDestino2 = new Muelle(50, 20, 50, true, 2, 5,true,true,true);
		
		pDestino1.aniadirMuelle(mDestino1);
		
		pDestino2.aniadirMuelle(mDestino2);
		
		LocalDate fFin1 = LocalDate.of(2025,12,10);
		
		LocalDate fFin2 = LocalDate.of(2026,03,25);
		
		TBarco t1 = new TBarco(mOrigen1, fInicio1, mDestino1, fFin1);
		
		TTren t2 = new TTren(mOrigen2, fInicio2, mDestino2, fFin2);
		
		c.asignarTrayecto(t1);
		
		c.asignarTrayecto(t2);
		
		ArrayList<Trayecto> trayectosTrasAsignacion= c.getTrayectos();
		
		assertEquals(trayectosTrasAsignacion.get(0), t1);
		assertEquals(trayectosTrasAsignacion.get(1), t2);
		assertEquals(trayectosTrasAsignacion.size(), 2);
		

		
	}
	
	@Test
	public void testAsignarTrayectoRefrigerado() {
		
		Refrigerado c = new Refrigerado("CSQU3054383",120,100,true,80);
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Puerto pOrigen2 = new Puerto("ES", "BAR");
		
		Muelle mOrigen1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mOrigen2 = new Muelle(16, 80, 100, true, 3, 2,true,true,true);
		
		pOrigen1.aniadirMuelle(mOrigen1);
		pOrigen2.aniadirMuelle(mOrigen2);
		
		LocalDate fInicio1 = LocalDate.of(2025,11,10);
		
		LocalDate fInicio2 = LocalDate.of(2025,12,10);
		
		Puerto pDestino1 = new Puerto("US", "NEW");
		
		Puerto pDestino2 = new Puerto("US", "WAS");
		
		Muelle mDestino1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mDestino2 = new Muelle(50, 20, 50, true, 2, 5,true,true,true);
		
		pDestino1.aniadirMuelle(mDestino1);
		
		pDestino2.aniadirMuelle(mDestino2);
		
		LocalDate fFin1 = LocalDate.of(2025,12,10);
		
		LocalDate fFin2 = LocalDate.of(2026,03,25);
		
		TBarco t1 = new TBarco(mOrigen1, fInicio1, mDestino1, fFin1);
		
		TCamion t2 = new TCamion(mOrigen2, fInicio2, mDestino2, fFin2);

		c.asignarTrayecto(t1);
		
		c.asignarTrayecto(t2);
		
		ArrayList<Trayecto> trayectosTrasAsignacion= c.getTrayectos();
		
		assertEquals(trayectosTrasAsignacion.get(0), t1);
		assertEquals(trayectosTrasAsignacion.get(1), t2);
		assertEquals(trayectosTrasAsignacion.size(), 2);
		

		
	}
	
	@Test
	public void testAsignarTrayectoFlatRack() {
		
		FlatRack c = new FlatRack("CSQU3054383",120,100,true,80);
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Puerto pOrigen2 = new Puerto("ES", "BAR");
		
		Muelle mOrigen1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mOrigen2 = new Muelle(16, 80, 100, true, 3, 2,true,true,true);
		
		pOrigen1.aniadirMuelle(mOrigen1);
		pOrigen2.aniadirMuelle(mOrigen2);
		
		LocalDate fInicio1 = LocalDate.of(2025,11,10);
		
		LocalDate fInicio2 = LocalDate.of(2025,12,10);
		
		Puerto pDestino1 = new Puerto("US", "NEW");
		
		Puerto pDestino2 = new Puerto("US", "WAS");
		
		Muelle mDestino1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mDestino2 = new Muelle(50, 20, 50, true, 2, 5,true,true,true);
		
		pDestino1.aniadirMuelle(mDestino1);
		
		pDestino2.aniadirMuelle(mDestino2);
		
		LocalDate fFin1 = LocalDate.of(2025,12,10);
		
		LocalDate fFin2 = LocalDate.of(2026,03,25);
		
		TBarco t1 = new TBarco(mOrigen1, fInicio1, mDestino1, fFin1);
		
		TTren t2 = new TTren(mOrigen2, fInicio2, mDestino2, fFin2);
		
		c.asignarTrayecto(t1);
		
		c.asignarTrayecto(t2);
		
		ArrayList<Trayecto> trayectosTrasAsignacion= c.getTrayectos();
		
		assertEquals(trayectosTrasAsignacion.get(0), t1);
		assertEquals(trayectosTrasAsignacion.get(1), t2);
		assertEquals(trayectosTrasAsignacion.size(),  2);
		

		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarTrayectoNULLEstandar() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
		TTren t1 = null;
		
		
		c.asignarTrayecto(t1);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarTrayectoNULLRefrigerado() {
		
		Refrigerado c = new Refrigerado("CSQU3054383",120,100,true,80);
		
		TBarco t1 = null;
		
		
		c.asignarTrayecto(t1);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarTrayectoNULLFlatRack() {
		
		FlatRack c = new FlatRack("CSQU3054383",120,100,true,80);
		
		TBarco t1 = null;
		
		
		c.asignarTrayecto(t1);
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarTrayectoTrenRefrigerado() {
		
		Refrigerado c = new Refrigerado("CSQU3054383",120,100,true,80);
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Muelle mOrigen1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen1.aniadirMuelle(mOrigen1);
		
		LocalDate fInicio1 = LocalDate.of(2025,11,10);
		
		Puerto pDestino1 = new Puerto("US", "NEW");
		
		Muelle mDestino1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino1.aniadirMuelle(mDestino1);

		LocalDate fFin1 = LocalDate.of(2025,12,10);
		
		TTren t1 = new TTren(mOrigen1, fInicio1, mDestino1, fFin1);
		
		c.asignarTrayecto(t1);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarTrayectoTrenRefrigeradoPackNoPermitido() {
		
		Refrigerado c = new Refrigerado("CSQU3054383",120,100,true,80);
		
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
		
		c.asignarTrayecto(combinado);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarTrayectoCamionFlatRack() {
		
		FlatRack c = new FlatRack("CSQU3054383",120,100,true,80);
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Muelle mOrigen1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen1.aniadirMuelle(mOrigen1);
		
		LocalDate fInicio1 = LocalDate.of(2025,11,10);
		
		Puerto pDestino1 = new Puerto("US", "NEW");
		
		Muelle mDestino1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino1.aniadirMuelle(mDestino1);

		LocalDate fFin1 = LocalDate.of(2025,12,10);
		
		TCamion t1 = new TCamion(mOrigen1, fInicio1, mDestino1, fFin1);
		
		c.asignarTrayecto(t1);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarTrayectoPackCamionTrenFlatRack() {
		
		FlatRack c = new FlatRack("CSQU3054383",120,100,true,80);
		
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
		
		c.asignarTrayecto(combinado);
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarTrayectoPackCamionBarcoFlatRack() {
		
		FlatRack c = new FlatRack("CSQU3054383",120,100,true,80);
		
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
		
		c.asignarTrayecto(combinado);
		
	}
	
	@Test
	public void testCambiarPiesCubicos() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setPiesCubicos(50);
		assertEquals(c.getMetrosCubicos(),1.41584,0.00001);
		
		
	}
	
	
	
	
	
	
	@Test
	public void testCambiarPiesCubicos2() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setPiesCubicos(979.5);
		assertEquals(c.getMetrosCubicos(),27.7336351,0.01);
		
		
	}
	
	
	
	
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarPiesCubicosNegativo() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setPiesCubicos(-50);
		
		
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarPiesCubicosCero() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setPiesCubicos(0);
		
		
		
	}
	
	
	
	
	@Test
	public void testCambiarPesoLibraCorrecto() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setTaraLb(50);
		assertEquals(c.getTaraKg(),22.6796,0.00001);
		
		
	}
	
	
	
	@Test
	public void testCambiarPesoLibraCorrecto2() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setTaraLb(777);
		assertEquals(c.getTaraKg(),352.441,0.001);
		
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarPesoLibrasNegativo() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setTaraLb(-777);
		
		
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarPesoLibrasCero() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		c.setTaraLb(0);
		
		
		
	}
	
	
	
	
	@Test
	public void testGetDigitoControlCorrecto() {
		
		Estandar c = new Estandar("CSQU3054383",120,100,true,80,true);
		
		assertEquals(c.getDigitoControl("CSQ",'U',305438),3);
		
		
		
	}
	
	
	
	
	@Test
	public void testGetDigitoControlCorrecto2() {
		
		Estandar c = new Estandar("MSKU1234565",120,100,true,80,true);
		
		assertEquals(c.getDigitoControl("MSK",'U',123456),5);
		
		
		
	}
	
	
	
	@Test
	public void testGetDigitoControlCorrecto3() {
		
		Estandar c = new Estandar("BICU1234565",120,100,true,80,true);
		
		assertEquals(c.getDigitoControl("BIC",'U',123456),5);
		
		
		
	}
	
	
	@Test
	public void testGetDigitoControlCorrecto4() {
		
		Estandar c = new Estandar("PLSU8723107",120,100,true,80,true);
		
		assertEquals(c.getDigitoControl("PLS",'U',872310),7);
		
		
		
	}
	
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDigitoControLCodigoDuenioNull() {
		
		Estandar c = new Estandar("PLSU8723107",120,100,true,80,true);
		
		c.getDigitoControl(null,'U',872310);
		
		
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDigitoControLetraEquipamientoIncorrecta() {
		
		Estandar c = new Estandar("PLSU8723107",120,100,true,80,true);
		
		c.getDigitoControl("PLS",'M',872310);
		

	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDigitoControLetraEquipamientoIncorrectaMinuscula() {
		
		Estandar c = new Estandar("PLSU8723107",120,100,true,80,true);
		
		c.getDigitoControl("PLS",'n',872310);
		

	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDigitoControLetraEquipamientoIncorrectaCarcaterRaro() {
		
		Estandar c = new Estandar("PLSU8723107",120,100,true,80,true);
		
		c.getDigitoControl("PLS",'@',872310);
		

	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDigitoControNumeroSerieIncorrectoNegativo() {
		
		Estandar c = new Estandar("PLSU8723107",120,100,true,80,true);
		
		c.getDigitoControl("PLS",'U',-954120);
		

	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDigitoControNumeroSerieIncorrectoNegativo5Digitos() {
		
		Estandar c = new Estandar("PLSU8723107",120,100,true,80,true);
		
		c.getDigitoControl("PLS",'U',-95412);
		

	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDigitoControNumeroSerieIncorrectoMas6Digitos() {
		
		Estandar c = new Estandar("PLSU8723107",120,100,true,80,true);
		
		c.getDigitoControl("PLS",'U',777777777);
		

	}
	
	@Test
	public void testGetDigitoControlSiendoEste10() {
		
		Estandar c = new Estandar("CSQU3050080",120,100,true,80,true);
		
		assertEquals(c.getDigitoControl("CSQ", 'U', 305008),0);
		

	}
	
	
	@Test
	public void testGetDigitoControlLetraJ() {
		
		Estandar c = new Estandar("CSQJ3054386",120,100,true,80,true);
		
		assertEquals(c.getDigitoControl("CSQ", 'J', 305438),6);
		

	}
	
	@Test
	public void testGetDigitoControlLetraZ() {
		
		Estandar c = new Estandar("MSKZ1234569",120,100,true,80,true);
		
		assertEquals(c.getDigitoControl("MSK", 'Z', 123456),9);
		

	}
	
	@Test
	public void testGetDigitoControlLetrasLimite() {
		
		Estandar c = new Estandar("AAAZ1234560",120,100,true,80,true);
		
		assertEquals(c.getDigitoControl("AAA", 'Z', 123456),0);
		

	}

	@Test(expected= IllegalArgumentException.class)
	public void testGetDigitoControlCaracterIncorrectoEnElDuenio() {
		
		Estandar c = new Estandar("AAAZ1234560",120,100,true,80,true);
		
		c.getDigitoControl("AA-", 'Z', 123456);
		

	}
	@Test(expected= IllegalArgumentException.class)
	public void testGetDigitoControlCaracterIncorrectoEnLaLetraEquipamiento() {
		
		Estandar c = new Estandar("AAAZ1234560",120,100,true,80,true);
		
		c.getDigitoControl("AAA", '-', 123456);
		

	}



}