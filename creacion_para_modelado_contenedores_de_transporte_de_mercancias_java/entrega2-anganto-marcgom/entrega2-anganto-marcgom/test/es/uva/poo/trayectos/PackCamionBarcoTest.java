package es.uva.poo.trayectos;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import es.uva.poo.practica2.Muelle;
import es.uva.poo.practica2.Puerto;

public class PackCamionBarcoTest {

	@Test
	public void testIncializarCombinadoConLosArgumentosCorrectos() {
		
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
		
		assertEquals(combinado.getMuelleOrigen(), mOrigen1);
		assertEquals(combinado.getMuelleDestino(), mDestino3);
		assertEquals(combinado.getFechaInicio(), fInicio1);
		assertEquals(combinado.getFechaFin(), fFin3);
		assertEquals(combinado.getTrayectos().length, 3);
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testIncializarPackCamionBarcoConTrenes() {
		
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
	
		TTren t2 = new TTren(mOrigen2, fInicio2, mDestino2, fFin2);
		
		TBarco t3 = new TBarco(mOrigen3, fInicio3, mDestino3, fFin3);
		
		Simple[] trayectos = {t1,t2, t3};
		
		PackCamionBarco combinado = new PackCamionBarco(mOrigen1, fInicio1, mDestino3, fFin3, trayectos);
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIncializarCombinadoConSoloUnTipoCamion() {
		
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
		
		TCamion t1 = new TCamion(mOrigen1, fInicio1, mDestino1, fFin1);
	
		TCamion t2 = new TCamion(mOrigen2, fInicio2, mDestino2, fFin2);
		
		TCamion t3 = new TCamion(mOrigen3, fInicio3, mDestino3, fFin3);
		
		Simple[] trayectos = {t1,t2, t3};
		
		PackCamionBarco combinado = new PackCamionBarco(mOrigen1, fInicio1, mDestino3, fFin3, trayectos);
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIncializarCombinadoConSoloUnTipoBarco() {
		
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
	
		TBarco t2 = new TBarco(mOrigen2, fInicio2, mDestino2, fFin2);
		
		TBarco t3 = new TBarco(mOrigen3, fInicio3, mDestino3, fFin3);
		
		Simple[] trayectos = {t1,t2, t3};
		
		PackCamionBarco combinado = new PackCamionBarco(mOrigen1, fInicio1, mDestino3, fFin3, trayectos);
		
		
	}
	
	@Test
	public void testCalcularPrecioPack() {
		
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
		
		TBarco t1 = new TBarco(mOrigen1, fInicio1, mDestino1, fFin1);
	
		TCamion t2 = new TCamion(mOrigen2, fInicio2, mDestino2, fFin2);
		
		TBarco t3 = new TBarco(mOrigen3, fInicio3, mDestino3, fFin3);
		
		Simple[] trayectos = {t1,t2, t3};
		
		PackCamionBarco combinado = new PackCamionBarco(mOrigen1, fInicio1, mDestino3, fFin3, trayectos);
		
		assertEquals(combinado.getPrecio(),89607.48289, 0.01);
		
		
	}
	

}
