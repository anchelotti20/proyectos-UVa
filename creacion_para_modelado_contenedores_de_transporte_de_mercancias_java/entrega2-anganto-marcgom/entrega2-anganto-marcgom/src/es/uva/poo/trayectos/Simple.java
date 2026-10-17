package es.uva.poo.trayectos;

import java.time.LocalDate;

import es.uva.poo.practica2.Muelle;
/**
 * Implementacion de un tipo de datos que hereda de la clase abstracta Trayecto
 * Contiene la informacion de un SOLO trayecto. Se trata de una clase abstracta, pues 
 * los métodos los implementara cada clase de trayecto propio
 * @author marcgom
 * @author anganto
 */
public abstract class Simple extends Trayecto {
	
	private int costePorTrayecto;
	private double costeVariable;
	
	
	/**
	 * 
	 * Inicializa un Trayecto de tipo Simple  a traves del constructor padre invocado con super()
	 * @param muelleOrigen, objeto de tipo muelle que hace referencia al muelle desde que se ha partido
	 * @param fechaInicio, objeto de tipo LocalDate, siendo la fecha de incio del trayecto
	 * @param muelleDestino, objeto de tipo muelles, que hace referencia al muelle donde se finaliza 
	 * @param fechaFin, objeto de tipo LocalDate , es la fecha de finalizacion del trayecto 
	 * @param costeVariable, de tipo double y el coste variabke del trayecto.
	 *  @param costePorTrayecto, de tipo entero y el coste fijo del trayecto.
	 **/
	public Simple(Muelle muelleOrigen, LocalDate fechaInicio, Muelle muelleDestino, LocalDate fechaFin, int costePorTrayecto, double costeVariable) {
		
		
		super(muelleOrigen,fechaInicio,muelleDestino,fechaFin);
		
		this.costePorTrayecto = costePorTrayecto;
		
		this.costeVariable = costeVariable;
	
	}
	
	/**
	 * Devuelve el coste fijo del trayecto
	 * @return valor entero con el coste del trayecto
	 */
	public int getCostePorTrayecto() {
		
		return costePorTrayecto;
		
	}
	
	/**
	 * Devuelve el valor variable del trayecto
	 * @return valor dobule con el coste del trayecto
	 */
	public double getCosteVariable() {
		
		return costeVariable;
		
	}
	
	/**
	 * Metodo abstracto, cada viaje simple calcula su precio total
	 * de una forma distinta
	 * @return double con el valor del trayecto
	 */
	public abstract double getPrecio();
	
	

}
