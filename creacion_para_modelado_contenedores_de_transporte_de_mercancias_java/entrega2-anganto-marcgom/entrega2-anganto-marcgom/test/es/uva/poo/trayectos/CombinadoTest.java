package es.uva.poo.trayectos;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import es.uva.poo.practica2.Muelle;
import es.uva.poo.practica2.Puerto;

public class CombinadoTest {

	
	@Test
	public void testIncializarCombinadoConLosArgumentosCorrectos() {
		
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
		
		assertEquals(combinado.getMuelleOrigen(), mOrigen1);
		assertEquals(combinado.getMuelleDestino(), mDestino2);
		assertEquals(combinado.getFechaInicio(), fInicio1);
		assertEquals(combinado.getFechaFin(), fFin2);
		assertEquals(combinado.getTrayectos().length, 2);
		
	}
	
	
	@Test(expected= IllegalArgumentException.class)
	public void testIncializarCombinadoConTrayectosNull() {
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Puerto pOrigen2 = new Puerto("ME", "CIU");
		
		Muelle mOrigen1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		pOrigen1.aniadirMuelle(mOrigen1);
		
		LocalDate fInicio1 = LocalDate.of(2025,12,10);
		
		Puerto pDestino2 = new Puerto("US", "NEW");
		
		
		Muelle mDestino2 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		
		pDestino2.aniadirMuelle(mDestino2);
		
		LocalDate fFin2 = LocalDate.of(2025,12,31);
		
		Simple[] trayectos = null;
		
		PackCamionTren combinado = new PackCamionTren(mOrigen1, fInicio1, mDestino2, fFin2, trayectos);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testIncializarCombinadoConPuertosDestinoOrigenDistintos() {
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Puerto pOrigen2 = new Puerto("ER", "LOS");
		
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
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testIncializarCombinadoConFechaFinal1PosteriorFechaInicio2() {
		
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
		
		LocalDate fFin1 = LocalDate.of(2025,12,23);
		
		LocalDate fFin2 = LocalDate.of(2025,12,31);
		
		TTren t1 = new TTren(mOrigen1, fInicio1, mDestino1, fFin1);
	
		TCamion t2 = new TCamion(mOrigen2, fInicio2, mDestino2, fFin2);
		
		Simple[] trayectos = {t1,t2};
		
		PackCamionTren combinado = new PackCamionTren(mOrigen1, fInicio1, mDestino2, fFin2, trayectos);
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIncializarCombinadoConSoloTrayectosDeUnTipoTren() {
		
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
	
		TTren t2 = new TTren(mOrigen2, fInicio2, mDestino2, fFin2);
		
		Simple[] trayectos = {t1,t2};
		
		PackCamionTren combinado = new PackCamionTren(mOrigen1, fInicio1, mDestino2, fFin2, trayectos);
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIncializarCombinadoConSoloTrayectosDeUnTipoCamion() {
		
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
		
		TCamion t1 = new TCamion(mOrigen1, fInicio1, mDestino1, fFin1);
	
		TCamion t2 = new TCamion(mOrigen2, fInicio2, mDestino2, fFin2);
		
		Simple[] trayectos = {t1,t2};
		
		PackCamionTren combinado = new PackCamionTren(mOrigen1, fInicio1, mDestino2, fFin2, trayectos);
		
		
	}
	
	@Test
	public void testObtenerCosteFijoTotalTrayecto() {
		
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
		
		assertEquals(combinado.getCostePorTrayecto(), 220);
		
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testPackCamionTrenConTrayectosEnBarco() {
		
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
	
		TBarco t2 = new TBarco(mOrigen2, fInicio2, mDestino2, fFin2);
		
		Simple[] trayectos = {t1,t2};
		
		PackCamionTren combinado = new PackCamionTren(mOrigen1, fInicio1, mDestino2, fFin2, trayectos);
		
		
		
		
	}
	
	@Test
	public void testObtenerPrecioCamionTren() {
		
		Puerto pOrigen1 = new Puerto("ES", "CAT");
		
		Puerto pOrigen2 = new Puerto("ME", "CIU");
		
		Muelle mOrigen1 = new Muelle(15, 40, 40, true, 1, 1,true,true,true);
		
		Muelle mOrigen2 = new Muelle(15, 80, 80, true, 1, 1,true,true,true);
		
		pOrigen1.aniadirMuelle(mOrigen1);
		pOrigen2.aniadirMuelle(mOrigen2);
		
		LocalDate fInicio1 = LocalDate.of(2025,12,10);
		
		LocalDate fInicio2 = LocalDate.of(2025,12,21);
		
		Puerto pDestino1 = new Puerto("ME", "CIU");
		
		Puerto pDestino2 = new Puerto("US", "NEW");
		
		Muelle mDestino1 = new Muelle(15, 80, 80, true, 1, 1,true,true,true);
		
		Muelle mDestino2 = new Muelle(15, 85, 85, true, 1, 1,true,true,true);
		
		pDestino1.aniadirMuelle(mDestino1);
		
		pDestino2.aniadirMuelle(mDestino2);
		
		LocalDate fFin1 = LocalDate.of(2025,12,20);
		
		LocalDate fFin2 = LocalDate.of(2025,12,31);
		
		TTren t1 = new TTren(mOrigen1, fInicio1, mDestino1, fFin1);
	
		TCamion t2 = new TCamion(mOrigen2, fInicio2, mDestino2, fFin2);
		
		Simple[] trayectos = {t1,t2};
		
		PackCamionTren combinado = new PackCamionTren(mOrigen1, fInicio1, mDestino2, fFin2, trayectos);
		
		assertEquals(combinado.getPrecio(), 53198.01803, 0.01);
		
	}
	
}
