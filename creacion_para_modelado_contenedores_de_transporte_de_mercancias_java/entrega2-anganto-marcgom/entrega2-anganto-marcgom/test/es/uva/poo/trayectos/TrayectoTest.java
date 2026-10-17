package es.uva.poo.trayectos;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import es.uva.poo.practica2.Muelle;
import es.uva.poo.practica2.Puerto;

/**
 * Clase de pruebas para la clase Trayecto
 * @author anganto
 * @author marcgom
 *
 */
public class TrayectoTest {

	@Test
	public void testInicializarconLosArgumentos() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,false);//Barco
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getPuertoOrigen(), pOrigen);
		assertEquals(t.getMuelleOrigen(), mOrigen);
		assertEquals(t.getFechaInicio(), fInicio);
		assertEquals(t.getPuertoDestino(), pDestino);
		assertEquals(t.getMuelleDestino(), mDestino);
		assertEquals(t.getFechaFin(), fFin);
	
	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConMuelleOrigenNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
		
		mOrigen = null;
		
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConMuelleDestinoNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
		
		mDestino = null;
		
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConPuertoOrigenNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
		
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConPuertoDestinoNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
		
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConMuelleOrigenIgualMuelleDestino() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,false,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("ES", "CAT");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,false,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
		
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConFechaInicioNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = null;
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
		
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConFechaFinNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = null;
		
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConFechaInicioAnteriorActual() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2024,10, 11);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,11,10);
		
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConFechaFinAnteriorAInicio() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,10,10);
		
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConFechaFinIgualAInicio() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,false,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,false,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,11,10);
		
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected=RuntimeException.class)
	public void testInicializarConFechaInicioNoValida() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,33,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,11,10);
		
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected=RuntimeException.class)
	public void testInicializarConFechaFinNoValida() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,2,31);
		
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test
	public void testCambiarMuelleOrigen() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		Muelle nuevo = new Muelle(16,40,40,true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(nuevo);
		
		t.setMuellePuertoOrigen(nuevo);
		
		assertEquals(t.getMuelleOrigen(), nuevo);
		
	}

	
	@Test
	public void testCambiarMuelleYPuertoOrigen() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		Puerto pnuevo = new Puerto("RE", "VAR");
		
		Muelle mnuevo = new Muelle(16,40,40,true, 1, 1,true,true,false);
		
		pnuevo.aniadirMuelle(mnuevo);
		
		t.setMuellePuertoOrigen(mnuevo);
		
		assertEquals(t.getMuelleOrigen(), mnuevo);
		assertEquals(t.getPuertoOrigen(), pnuevo);
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCambiarMuelleOrigenNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		Muelle mnuevo = null;
		
		t.setMuellePuertoOrigen(mnuevo);
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarMuellePuertoOrigenNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		Muelle mnuevo = new Muelle(15,40,40,true,1,1,false,false,true);
		
		t.setMuellePuertoOrigen(mnuevo);
		
		
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarMuelleOrigenIgualDestino() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		Muelle mnuevo = new Muelle(15,40,40,true,1,1,true,true,true);
		Puerto pnuevo = new Puerto("US", "NEW");
		
		pnuevo.aniadirMuelle(mnuevo);
		
		t.setMuellePuertoOrigen(mnuevo);
		
		
	}

	@Test
	public void testCambiarMuelleDestino() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		Muelle nuevo = new Muelle(16,40,40,true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(nuevo);
		
		t.setMuellePuertoDestino(nuevo);
		
		assertEquals(t.getMuelleDestino(), nuevo);
		
	}

	
	@Test
	public void testCambiarMuelleYPuertoDestino() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		Puerto pnuevo = new Puerto("RE", "VAR");
		
		Muelle mnuevo = new Muelle(16,40,40,true, 1, 1,true,true,false);
		
		pnuevo.aniadirMuelle(mnuevo);
		
		t.setMuellePuertoDestino(mnuevo);
		
		assertEquals(t.getMuelleDestino(), mnuevo);
		assertEquals(t.getPuertoDestino(), pnuevo);
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCambiarMuelleDestinoNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		Muelle mnuevo = null;
		
		t.setMuellePuertoDestino(mnuevo);
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarMuellePuertoDestinoNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		Muelle mnuevo = new Muelle(15,40,40,true,1,1,true,true,true);
		
		t.setMuellePuertoDestino(mnuevo);
		
		
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarMuelleDestinoIgualOrigen() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		Muelle mnuevo = new Muelle(15,40,40,true,1,1,true,true,true);
		
		Puerto pnuevo = new Puerto("ES", "CAT");
		
		pnuevo.aniadirMuelle(mnuevo);
		
		t.setMuellePuertoDestino(mnuevo);
		
		
	}

	@Test
	public void testCambiarFechaInicio() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate nueva = LocalDate.of(2025, 11, 20);
		
		t.setFechaInicio(nueva);
		
		assertEquals(t.getFechaInicio(), nueva);
	
	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarFechaInicioNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate nueva = null;
		
		t.setFechaInicio(nueva);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarFechaInicioAnteriorActual() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate nueva = LocalDate.of(2024,2,10);
		
		t.setFechaInicio(nueva);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarFechaInicioDespuesFin() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate nueva = LocalDate.of(2026,2,10);
		
		t.setFechaInicio(nueva);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCambiarFechaInicioIgualFin() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate nueva = LocalDate.of(2025,12,10);
		
		t.setFechaInicio(nueva);
		
	}
	
	@Test(expected=RuntimeException.class)
	public void testCambiarFechaInicioInvalida() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate nueva = LocalDate.of(2025,11,90);
		
		t.setFechaInicio(nueva);
		
	}
	
	@Test
	public void testCambiarFechaFin() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate nueva = LocalDate.of(2026,3,1);
		
		t.setFechaFin(nueva);
		
		assertEquals(t.getFechaFin(), nueva);
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testCambiarFechaFinNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate nueva = null;
		
		t.setFechaFin(nueva);
		
		
	}

	@Test(expected= IllegalArgumentException.class)
	public void testCambiarFechaFinAnteriorInicio() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate nueva = LocalDate.of(-2025,12,10);
		
		t.setFechaFin(nueva);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testCambiarFechaFinIgualInicio() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate nueva = LocalDate.of(2025,11,10);
		
		t.setFechaFin(nueva);
		
	
	}

	@Test
	public void testObtenerPuertoOrigen() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getPuertoOrigen(), pOrigen);
		
	
	}
	
	@Test
	public void testObtenerMuelleOrigen() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getMuelleOrigen(), mOrigen);
		
	
	}
	
	@Test
	public void testObtenerFechaInicio() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getFechaInicio(), fInicio);
		
	
	}

	@Test
	public void testObtenerPuertoDestino() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getPuertoDestino(), pDestino);
		
	
	}
	
	@Test
	public void testObtenerMuelleDestino() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getMuelleDestino(), mDestino);
		
	
	}

	@Test
	public void testObtenerFechaFin() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getFechaFin(), fFin);
		
	
	}

	@Test
	public void testFinSuperiorFecha() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate otra = LocalDate.of(2024, 12, 10);
		
		assertTrue(t.fechaFinSuperior(otra));
	
	
	}
	
	@Test
	public void testFinNoSuperiorFecha() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate otra = LocalDate.of(2026, 12, 10);
		
		assertFalse(t.fechaFinSuperior(otra));
	
	
	}
	
	@Test
	public void testFinIgualFecha() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate otra = LocalDate.of(2025, 12, 10);
		
		assertFalse(t.fechaFinSuperior(otra));
	
	
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFinSuperiorFechaNull() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1 , 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		LocalDate otra = null;
		
		t.fechaFinSuperior(otra);
		
	}

	@Test
	public void testObtenerMillasMarinas() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 80, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		double distanciaKM = t.getMuelleDestino().getCoordenadaDecimal().getDistanceTo(t.getMuelleOrigen().getCoordenadaDecimal());
		
		assertNotEquals(t.getMillasMarinas(), distanciaKM);
		assertEquals(t.getMillasMarinas(), distanciaKM *0.539957, 0.00001);
		
	
	}
	
	@Test
	public void testObtenerInfo() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getInformacion(), "País Origen: ES; Localidad Origen: CAT; Fecha Inicio: 2025-11-10; País Destino: US; Localidad Destino: NEW; Fecha Fin: 2025-12-10");
		
	
	
	}


}
