package es.uva.poo.trayectos;

import java.time.LocalDate;
import es.uva.poo.practica2.Muelle;

/**
 * Implementacion de clase Trayecto que hereda de Simple, que a su vez hereda 
 * de la clase abstracta Trayecto
 * Almacena toda la informacion de un Trayecto de tipo Tren 
 * @author marcgom
 * @author anganto
 */

public class TTren extends Simple {
	


	/**
	 * Iniializa un trayecto de tren usando herencia con la clase TrayectoSimple,
	 * se aniade el contructor de la clase padre con super, y el resto de atributos propios de la
	 * clase trayecto
	 * @param muelleOrigen, objeto de tipo muelle donde se ha iniciado el trayecto
	 * @param fechaInicio, objeto de tipo LocalDate , es la fecha cuando se inicia el trayecto
	 * @param muelleDestino, objeto de tipo muelle donde se finaliza el trayecto en tren
	 * @param fechaFin , objeto de tipo LocalDate, siendo la fecha de finalizacion del trayecto del tren
	 * @throws IllegalArgumentException si el muelle no es compatible con trayectos de tipo tren 
	 **/
	public TTren(Muelle muelleOrigen, LocalDate fechaInicio, Muelle muelleDestino, LocalDate fechaFin) {
		
		super(muelleOrigen,fechaInicio,muelleDestino,fechaFin,20, 12.5);
		
		
		
		
		if(muelleOrigen.getTren()==false||muelleDestino.getTren()==false) {
			throw
				new IllegalArgumentException("El muelle no puede albergar trayectos de tipo Tren");
		}
		
	}
	
	
	/**
	 * Sobreescritura del metodo getPrecio() de la clase abstracta trayecto,
	 * en la clase abstracta Trayecto, se tiene el metodo abstracto getPrecio(), donde
	 * cada clase hija o subclase lo calculara de una manera
	 * En este caso obtiene el precio a traves de la suma del precio o costeFijo multiplicado por
	 * la distancia de Km que se haya obtenido por el costePorKm
	 * @return precio,valor de tipo double precio, con el resultado del precio obtenido en el trayecto Tren
	 * 
	 **/
	@Override
	public double getPrecio() {
		
		double distanciaKm,precio;
		
		distanciaKm= getMuelleDestino().getCoordenadaDecimal().getDistanceTo(getMuelleOrigen().getCoordenadaDecimal());
		
		precio = getCostePorTrayecto() + getCosteVariable() *distanciaKm;
		return precio;
	}


	
	
	
	
	
}
