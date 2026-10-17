package es.uva.poo.trayectos;

import java.time.LocalDate;

import es.uva.poo.practica2.Muelle;
/**
 * Implementacion de la clase Trayecto de tipo Camion que hereda de Simple, y esta de Trayecto
 * Almacena toda la informacion referente al tipo de trayecto de Camion
 * @author marcgom
 * @author anganto
 **/

public class TCamion extends Simple {
	
	
	/**
	 * Inicializa un Trayecto de tipo Camion a traves del constructor padre invocado con super()
	 * @param muelleOrigen, objeto de tipo muelle que hace referencia al muelle desde que se ha partido
	 * @param fechaInicio, objeto de tipo LocalDate, siendo la fecha de incio del trayecto
	 * @param muelleDestino, objeto de tipo muelles, que hace referencia al muelle donde se finaliza 
	 * @param fechaFin, objeto de tipo LocalDate , es la fecha de finalizacion del trayecto Camion
	 * @throws IllegalArgumentException si el muelle no es compatible co trayectos camion
	 **/
	
	public TCamion(Muelle muelleOrigen, LocalDate fechaInicio, Muelle muelleDestino, LocalDate fechaFin) {
		
		super(muelleOrigen,fechaInicio,muelleDestino,fechaFin, 200, 4.5);
		
		
		
		if(muelleOrigen.getCamion()==false||muelleDestino.getCamion()==false) {
			throw
				new IllegalArgumentException("El muelle no puede albergar trayectos de tipo camion");
		}
		
	}
	/**
	 * Metodo que calcula el precio del trayecto, sobreescribiendo el metodo abstracto de la clase 
	 * abstracta Trayceto, calculandolo a su manera con sus diferentes cuotas de precios.
	 * @return precio,valor de tipo double con el resultado del precio resultante del trayecto en camion tras 
	 * sumar el precioFijo + el numero de Km recorridos por su tarifa
	 **/

	public double getPrecio() {
		double distanciaKm,precio;
		distanciaKm= getMuelleDestino().getCoordenadaDecimal().getDistanceTo(getMuelleOrigen().getCoordenadaDecimal());
		
		precio= getCostePorTrayecto() + getCosteVariable() * distanciaKm;
		return precio;
		
	}
	

	
	
	
}
