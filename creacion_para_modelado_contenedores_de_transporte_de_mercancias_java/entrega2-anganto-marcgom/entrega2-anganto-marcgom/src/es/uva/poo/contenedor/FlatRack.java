package es.uva.poo.contenedor;

import java.util.ArrayList;

import es.uva.poo.trayectos.Trayecto;
/**
 * Tipo de datos que model el comportamiento de un FlatRack heredando de contenedor,
 * siendo este un contenedor sin techo, y sin paredes, solo se puede transportar en barco y tren
 * Ocupa dos plazas
 * @author marku
 * @author anganto
 */
public class FlatRack extends Contenedor{

	
	
	/**
	 * Inicializa un contenedor de tipo FlatRack dado su identificador, su peso en Kg, su volumen en m^3
	 * si esta en transito o no y su maxima carga util en Kg
	 * @param identificador, String con formato ISO 6346
	 * @param pesoKilogramos, double en Kg
	 * @param metrosCubicos, double en m^3
	 * @param transito, true para en transito, false para no
	 * @param maximaCargaUtil, double en Kg
	 */
	public FlatRack(String identificador, double pesoKilogramos, double metrosCubicos, boolean transito, double maximaCargaUtil) {
		super(identificador, pesoKilogramos, metrosCubicos, transito, maximaCargaUtil);
	
	}
	
	
	/**
	 * Consulta si el contenedor tiene paredes
	 * @return false, los FlatRack no tienen paredes
	 */
	@Override
	public boolean getParedes() {
		
		return false;
	}
	
	
	/**
	 * Consulta si el contenedor tiene techo
	 * @return false, los FlatRack no tienen techo
	 */
	@Override
	public boolean getTecho() {
		
		return false;
		
	}
	
	/**
	 * Consulta si el contenedor tiene motor
	 * @return false, los FlatRack no tienen motor
	 */
	@Override
	public boolean getMotor() {
		
		return false;
			
		
	}
	
	/**
	 * Asigna un trayecto al contenedor. Al tratarse de un contenedor FlatRack
	 * solo puede ir en tren o barco 
	 * @param trayecto, tipo de trayecto que se quiere añadir 
	 * @throws IllegalArgumentException si el trayecto es null
	 * @throws IllegalArgumentException si el trayecto es de tipo camion 
	 * @throws IllegalArgumentException si el trayecto es cualquiera de los packs, pues al menos un viaje
	 * se hara en camion 
	 */
	@Override
	public void asignarTrayecto(Trayecto trayecto) {
		
		
		if (trayecto == null) {
			
			throw
				new IllegalArgumentException ("El trayecto no puede ser null");
	
		}else if(trayecto.getCostePorTrayecto()==200) {
				
				throw
					new IllegalArgumentException("El trayecto no puede ser de tipo camion");
			
		}else if(trayecto.getCostePorTrayecto() == 220) {
			
			throw
				new IllegalArgumentException("El trayecto no puede ser PackCamionTren");
			
		}else if(trayecto.getCostePorTrayecto() == 4200) {
			
			throw
				new IllegalArgumentException("El trayecto no puede ser PackCamionBarco");
			
		}
	
		ArrayList<Trayecto> trayectos = getTrayectos();
		
		trayectos.add(trayecto);
		
		
		setTrayectos(trayectos);
	}
	
	
	
}
