package es.uva.poo.trayectos;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import es.uva.poo.practica2.Muelle;
import es.uva.poo.practica2.Puerto;

public class SimpleTest {

	@Test
	public void testIncializarTrayectoBarco() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
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
		assertEquals(t.getCostePorTrayecto(), 0);
		assertEquals(t.getCosteVariable(), 4000, 0);
		
		
		
	}
	
	@Test
	public void testIncializarTrayectoTren() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TTren t = new TTren(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getPuertoOrigen(), pOrigen);
		assertEquals(t.getMuelleOrigen(), mOrigen);
		assertEquals(t.getFechaInicio(), fInicio);
		assertEquals(t.getPuertoDestino(), pDestino);
		assertEquals(t.getMuelleDestino(), mDestino);
		assertEquals(t.getFechaFin(), fFin);
		assertEquals(t.getCostePorTrayecto(), 20);
		assertEquals(t.getCosteVariable(), 12.5, 0);
		
	}
	
	
	@Test
	public void testIncializarTrayectoCamion() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TCamion t = new TCamion(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getPuertoOrigen(), pOrigen);
		assertEquals(t.getMuelleOrigen(), mOrigen);
		assertEquals(t.getFechaInicio(), fInicio);
		assertEquals(t.getPuertoDestino(), pDestino);
		assertEquals(t.getMuelleDestino(), mDestino);
		assertEquals(t.getFechaFin(), fFin);
		assertEquals(t.getCostePorTrayecto(), 200);
		assertEquals(t.getCosteVariable(), 4.5, 0);
		
	}
	
	
	@Test(expected= IllegalArgumentException.class)
	public void testIncializarTrayectoBarcoPuertoOrigenNoAceptaBarcos() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testIncializarTrayectoBarcoPuertoOrigenYDestinoNoAceptaBarcos() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testIncializarTrayectoBarcoPuertoDestinoNoAceptaBarcos() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testIncializarTrayectoBarcoPuertoOrigenNoAceptaTrenes() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TTren t = new TTren(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testIncializarTrayectoBarcoPuertoOrigenYDestinoNoAceptaTrenes() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,false,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,false,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TTren t = new TTren(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testIncializarTrayectoBarcoPuertoDestinoNoAceptaTrenes() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TTren t = new TTren(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testIncializarTrayectoBarcoPuertoOrigenNoAceptaCamiones() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TCamion t = new TCamion(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testIncializarTrayectoBarcoPuertoOrigenYDestinoNoAceptaCamion() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TCamion t = new TCamion(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testIncializarTrayectoBarcoPuertoDestinoNoAceptaCamiones() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,false,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TCamion t = new TCamion(mOrigen, fInicio, mDestino, fFin);
		
		
	}
	
	@Test
	public void testGetPrecioBarco() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,11,20);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getPrecio(), 40000, 0.00001);
	}
	
	
	@Test
	public void testGetPrecioTren() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 80, 80, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,11,20);
	
		TTren t = new TTren(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getPrecio(), 59485.2303, 0.01);
	}
	
	@Test
	public void testGetPrecioCamion() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 80, 80, true, 1, 1,true,true,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,11,20);
	
		TCamion t = new TCamion(mOrigen, fInicio, mDestino, fFin);
		
		assertEquals(t.getPrecio(), 21607.48291, 0.01);
	}
	
	@Test
	public void testGetCosteTrayectoBarco() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
	
		assertEquals(t.getCostePorTrayecto(), 0);
	}
	
	@Test
	public void testGetCosteTrayectoTren() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TTren t = new TTren(mOrigen, fInicio, mDestino, fFin);
	
		assertEquals(t.getCostePorTrayecto(), 20);
	}
	
	@Test
	public void testGetCosteTrayectoCamion() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TCamion t = new TCamion(mOrigen, fInicio, mDestino, fFin);
	
		assertEquals(t.getCostePorTrayecto(), 200);
	}

	@Test
	public void testGetCosteVariableBarco() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TBarco t = new TBarco(mOrigen, fInicio, mDestino, fFin);
	
		assertEquals(t.getCosteVariable(), 4000, 0);
	}
	
	@Test
	public void testGetCosteVariableTren() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,true,false);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TTren t = new TTren(mOrigen, fInicio, mDestino, fFin);
	
		assertEquals(t.getCosteVariable(), 12.5, 0);
	}
	
	@Test
	public void testGetCosteVariableCamion() {
		
		Puerto pOrigen = new Puerto("ES", "CAT");
		
		Muelle mOrigen = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pOrigen.aniadirMuelle(mOrigen);
		
		LocalDate fInicio = LocalDate.of(2025,11,10);
		
		Puerto pDestino = new Puerto("US", "NEW");
		
		Muelle mDestino = new Muelle(15, 40, 40, true, 1, 1,true,false,true);
		
		pDestino.aniadirMuelle(mDestino);
		
		LocalDate fFin = LocalDate.of(2025,12,10);
	
		TCamion t = new TCamion(mOrigen, fInicio, mDestino, fFin);
	
		assertEquals(t.getCosteVariable(), 4.5, 0);
	}
	
	
}
