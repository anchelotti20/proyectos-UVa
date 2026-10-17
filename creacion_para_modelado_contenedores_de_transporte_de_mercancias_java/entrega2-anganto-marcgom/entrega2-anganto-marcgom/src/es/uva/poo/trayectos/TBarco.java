package es.uva.poo.trayectos;

import java.time.LocalDate;

import es.uva.poo.practica2.Muelle;

/**
 * Implementacion del tipo de dato que almacena la informacion de Tipo TrayectoBarco
 * Almacena toda la infromacion correspondiente al trayecto de un barco
 * @author marcgom
 * @author anganto
 **/
public class TBarco extends Simple {
	
	

	
	/**
	 * Inicializa un Trayecto de tipo Barco a traves del constructor padre invocado con super()
	 * @param muelleOrigen, objeto de tipo muelle que hace referencia al muelle desde que se ha partido
	 * @param fechaInicio, objeto de tipo LocalDate, siendo la fecha de incio del trayecto
	 * @param muelleDestino, objeto de tipo muelles, que hace referencia al muelle donde se finaliza 
	 * @param fechaFin, objeto de tipo LocalDate , es la fecha de finalizacion del trayecto Barco
	 * @throws IllegalArgumentException si el muelle no puede albergar un barco
	 **/
	
	public TBarco(Muelle muelleOrigen, LocalDate fechaInicio, Muelle muelleDestino, LocalDate fechaFin) {
		
		super(muelleOrigen,fechaInicio,muelleDestino,fechaFin, 0, 4000);
		
		
		if(muelleOrigen.getBarco()==false||muelleDestino.getBarco()==false) {
			throw
				new IllegalArgumentException("El muelle no puede albergar trayectos de tipo barco");
		}
		
		
	}

	
	/**
	 * Metodo que obtiene el precio de un traycteo de un trayceto en Barco, sobreescribiendo el metodo
	 * abstracto de Trayecto y Simple
	 * @return precio, valor de tipo double que es el resultado del resultado de la duracion del trayecto
	 * por el costePorDia 
	 **/
	@Override
	public double getPrecio(){
		long diasDuracionTrayecto;
		double precio;
		
		diasDuracionTrayecto= getFechaFin().toEpochDay() - getFechaInicio().toEpochDay();
		
		precio = diasDuracionTrayecto * getCosteVariable();
		
		return precio;
		
	}
	
	
	

}
