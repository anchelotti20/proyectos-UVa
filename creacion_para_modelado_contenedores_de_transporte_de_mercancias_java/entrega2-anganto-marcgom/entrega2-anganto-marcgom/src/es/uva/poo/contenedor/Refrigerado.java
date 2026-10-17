package es.uva.poo.contenedor;

import java.util.ArrayList;

import es.uva.poo.trayectos.Trayecto;

/**
 * Tipo de dato que modela un contenedor refrigerador
 * Los contenedores refrigeradores siempre tienen techo, tienen paredes y motor
 * @author anganto
 * @author marcgom
 *
 */
public class Refrigerado extends Contenedor{

	
	
	/**
	 * Inicializa un contenedor refrigerado dado su identificador completo, su peso, volumen y carga maxima y su estado
	 * @param identificador, String con formato ISO 6346
	 * @param pesoKilogramos, double en Kg
	 * @param metrosCubicos, double en m^3
	 * @param transito, true para en transito, false para no
	 * @param maximaCargaUtil, double en Kg
	 */
	public Refrigerado(String identificador, double pesoKilogramos, double metrosCubicos, boolean transito, double maximaCargaUtil) {
		
		super(identificador, pesoKilogramos, metrosCubicos, transito, maximaCargaUtil);
		
	}
	
	
	/**
	 * Consulta si el contenedor tiene paredes
	 * @return true, los refrigerados siempre tienen paredes
	 */
	@Override
	public boolean getParedes() {
		
		return true;
	}
	
	/**
	 * Consulta si el contenedor tiene techo
	 * @return true, los refrigerados siempre tienen techo
	 */
	@Override
	public boolean getTecho() {
		
		return true;
		
	}
	
	
	/**
	 * Consulta si el contenedor tiene motor
	 * @return true, los refrigerados tienen motor
	 */
	@Override
	public boolean getMotor() {
		
		return true;
			
		
	}


	
	/**
	 * Asigna un trayecto al contenedor. Al tratarse de un contenedor refrigerado
	 * solo puede ir en camión o barco que son a los que puede ir enchufado el motor
	 * @param trayecto, tipo de trayecto que se quiere añadir 
	 * @throws IllegalArgumentException si el trayecto es null
	 * @throws IllegalArgumentException si el trayecto es de tipo tren
	 * @throws IllegalArgumentException si el trayecto es el pack camion tren pues al menos un viaje sera en tren 
	 */
	@Override
	public void asignarTrayecto(Trayecto trayecto) {
		
		
		if (trayecto == null) {
			
			throw
				new IllegalArgumentException ("El trayecto no puede ser null");
	
		}else if(trayecto.getCostePorTrayecto()==20) {
				
				throw
					new IllegalArgumentException("El trayecto de Refrigerado no puede ser tren");
				
			
		}else if(trayecto.getCostePorTrayecto() == 220) {
			
			throw
				new IllegalArgumentException("El trayecto de Refrigerado no puede ser PackCamionTren");
			
			
		}
	
		ArrayList<Trayecto> trayectos = getTrayectos();
		
		trayectos.add(trayecto);
		
		setTrayectos(trayectos);
	}

	
	
	
	
	
}
