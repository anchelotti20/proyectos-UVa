package es.uva.poo.contenedor;

import java.util.ArrayList;

import es.uva.poo.trayectos.Trayecto;

/**
 * Tipo de dato que modela un contenedor estándar
 * Los contenedores estandar pueden o no tener techo, tienen paredes y tienen motor.
 * @author anganto
 * @author marcgom
 *
 */


public class Estandar extends Contenedor{

	private boolean techo;
	
	/**
	 * Inicializa un contenedor estandar dado su identificador completo, su peso, volumen y carga maxima, su estado y 
	 * si tiene o no techo
	 * @param identificador, String con formato ISO 6346
	 * @param pesoKilogramos, double en Kg
	 * @param metrosCubicos, double en m^3
	 * @param transito, true para en transito, false para no
	 * @param techo, true tiene techo, false para no
	 * @param maximaCargaUtil, double en Kg
	 */
	public Estandar(String identificador, double pesoKilogramos, double metrosCubicos, boolean transito, double maximaCargaUtil, boolean techo) {
		
		
		
		super(identificador, pesoKilogramos, metrosCubicos, transito, maximaCargaUtil);
		
		this.techo = techo;
		
		
	}
	
	/**
	 * Consulta si el contenedor tiene techo
	 * @return true si tiene, false si no
	 */
	@Override
	public boolean getTecho() {
		
		return techo;
	}
	
	
	/**
	 * Consulta si el contenedor tiene motor
	 * @return false, los estandar no tienen motor
	 */
	@Override
	public boolean getMotor() {
		
		return false;
			
		
	}
	
	/**
	 * Consulta si el contenedor tiene paredes
	 * @return true, los estandar tienen paredes
	 */
	@Override
	public boolean getParedes() {
		
		return true;
	}
	
	/**
	 * Asigna un trayecto al contenedor. Al tratarse de un contenedor estándar
	 * puede ser cualquier tipo de medio de transporte.
	 * @param trayecto, tipo de trayecto que se quiere añadir (
	 */
	@Override
	public void asignarTrayecto(Trayecto trayecto) {
		
		
		if (trayecto == null) {
			
			throw
				new IllegalArgumentException ("El trayecto no puede ser null");
	
		}
	
		ArrayList<Trayecto> trayectos = getTrayectos();
		
		trayectos.add(trayecto);
		
		
		setTrayectos(trayectos);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
