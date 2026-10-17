package es.uva.poo.trayectos;

import java.time.LocalDate;

import es.uva.poo.practica2.Muelle;
/**
 * Tipo de datos que hereda de trayecto y que implementa las carcateristicas de un tipo de trayecto
 * combinado, esto un conjunto de trayectos, donde ademas de los argumentos de trayecto se necestita
 * un array de trayectos.
 * Es abstracta, cada paquete implementará sus métodos propios
 * @author marcgom
 * @author anganto
 **/
public abstract class Combinado extends Trayecto {
	
	private Simple[] trayectos;
	
	/**
	 * Inicializa un trayecto de tipo combinado, para inicializarlo llama el constructor del padre(Trayecto)
	 * Al estar formado por varios viajes simples, el muelle origen sera el primero de ellos y el de destino
	 * el ultimo muelle que haya (igual para las fechas)
	 * @param muelleOrigen, objeto muelle desde el que se inicia el trayecto
	 * @param fechaInicio, objeto LocalDate con la fecha en la que inicia el trayecto
	 * @param muelleDestino, objeto muelle en el que finaliza el trayecto
	 * @param fechaFin, objeto LocalDate con la fecha en la que finaliza el trayecto
	 * @param Simple[] trayectos, array de trayectos
	 * @throws IllegalArgumentException si trayectos == null
	 * @throws IllegalArgumentException si fechafin 1 es despues de fecha inicio 2
	 * @throws IllegalArgumentException si fechainicio 1 es despues de fecha inicio 2
	 * @throws IllegalArgumentException si fechafin 1 es igual a fecha inicio 2
	 **/
	public Combinado(Muelle muelleOrigen, LocalDate fechaInicio, Muelle muelleDestino, LocalDate fechaFin, Simple[] trayectos) {
		
		super(muelleOrigen,fechaInicio,muelleDestino,fechaFin);
		
		
		if (trayectos == null) {
			
			throw
				new IllegalArgumentException("Los trayectos no pueden ser null");
			
		}
		
		this.trayectos = trayectos;
		
		for(int i = 0; i < trayectos.length - 1; i++) {
			
			if(trayectos[i].getPuertoDestino().equals(trayectos[i + 1].getPuertoOrigen()) == false) {
				
				throw
					new IllegalArgumentException("El puerto de destino de un viaje tiene que ser el de origen del siguiente");
				
			}else if(trayectos[i].getFechaFin().isAfter(trayectos[i + 1].getFechaInicio()) == true){
				
				throw
					new IllegalArgumentException("La fecha de fin de un viaje no puede ser despues que la de inicio del siguiente");
			
			}
		
		}
	
	}
	/**
	 * Metodo que permite comprobar si todos los trayectos son del tipo correspondiente al pack
	 * Lo implementa cada pack por separado
	 * @return comprobador, valor booleano que informa sobre si esta formado por viajes del mismo pack
	 */
	public abstract boolean comprobarPack();
	
	
	/**
	 * Metodo abstracto, comprueba si hay al menos un trayecto de cada tipo de los que conforman el pack
	 * Lo implementa cada pacj por separado
	 * @return true, si si, false si no
	 */
	public abstract boolean alMenosUnoDeCada();
	
	/**
	 * Metodo que obtiene el array de trayectos Simples que forman el pack
	 * @return el array de trayectos
	 */
	public Simple[] getTrayectos() {
		
		return trayectos;
		
	}
	
	
	
	
}
