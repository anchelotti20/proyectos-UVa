package es.uva.poo.trayectos;

import java.time.LocalDate;

import es.uva.poo.practica2.Muelle;
/**
 * Tipo de datos que modela un conjunto de tayectos de tipo camion y barco, que hereda de combinado
 * Solo pueden ser trayectos en camion o en barco
 * @author marcgom
 * @author anganto
 *
 */
public class PackCamionBarco extends Combinado {
	
	
	
	/**
	 * Inicializa un conjunto de trayectos de tipo camion y/o barco
	 * @param muelleOrigen, objeto muelle desde el que se inicia el trayecto
	 * @param fechaInicio, objeto LocalDate con la fecha en la que inicia el trayecto
	 * @param muelleDestino, objeto muelle en el que finaliza el trayecto
	 * @param fechaFin, objeto LocalDate con la fecha en la que finaliza el trayecto
	 * @param Simple[] trayectos, array de trayectos
	 * @throws IllegalArgumentException si los trayectos no son barcos o camiones
	 * @throws IllegalArgumentException si no hay un trayecto de cada tipo
	 */
	public PackCamionBarco(Muelle muelleOrigen, LocalDate fechaInicio, Muelle muelleDestino, LocalDate fechaFin, Simple[] trayectos) {
		
		super(muelleOrigen, fechaInicio, muelleDestino, fechaFin, trayectos);
		
		if(comprobarPack()==false) {
			
			throw
				new IllegalArgumentException("No todos los trayectos son barcos o camiones");
		
		}else if(alMenosUnoDeCada() == false) {
			

			throw
				new IllegalArgumentException("Para que sea Pack, tiene que haber al menos un trayecto en Camion y uno en barco");
			
			
		}
	
	}
	
	/**
	 * Metodo que obtiene los precios de un array de trayectos , que se habran comprobado en el contructor
	 * si son o no de tipo barco y camion
	 * Los barcos se descuenca un 15% es decir se paga un 85% del coste original 
	 * @return precio,el valor del precio de los trayectos que es de tipo double
	 **/
	@Override
	public double getPrecio() {
		
		double precio=0;
		Simple[] trayectos = getTrayectos();
		
		
		for(int i=0;i<trayectos.length;i++) {
			
			if(trayectos[i].getCostePorTrayecto()==0) {
				
				precio+=trayectos[i].getPrecio()*0.85;
			
			}else {
				
				precio+=trayectos[i].getPrecio();
			
			}
			
		}
		
		return precio;
	}
	
	/**
	 *Comprueba si todos los trayectos simples añadidos son de tipo camion o barco
	 *@return true si si, false si no
	 */
	@Override
	public boolean comprobarPack() {
		
		boolean comprobador=false;
		
		int contador=0;
		
		Simple[] trayectos = getTrayectos();
	
		for(int i=0;i<trayectos.length;i++) {
			
			if(trayectos[i].getCostePorTrayecto()==0||trayectos[i].getCostePorTrayecto()==200) {
				
				contador++;
			}
		}
	
		if(contador==trayectos.length) {
			comprobador=true;
		}
	
		return comprobador;
	}

	/**
	 * Comprueba si hay al menos un trayecto de cada tipo del pack
	 * @return true si lo son, false si no
	 */
	@Override
	public boolean alMenosUnoDeCada() {
		
		boolean comprobador=false;
		
		int contadorCamion = 0;
		int contadorBarco = 0;
		
		Simple[] trayectos = getTrayectos();
		
		for(int i=0;i<trayectos.length;i++) {
			
			if(trayectos[i].getCostePorTrayecto()==200){
				
				contadorCamion ++;
			
			}else {
				
				contadorBarco ++;
			}
		
		}
		
		if(contadorCamion > 0 && contadorBarco > 0) {
			
			comprobador=true;
		}
		
		return comprobador;
	}
	
	/**
	 * Devuelve la suma de los costes fijos de los trayectos que conforman el pack
	 * @return Valor entero suma de todos los costes fijos
	 */
	@Override
	public int getCostePorTrayecto() {
		
		return 4200;
		
	}

		
}
	
	
	


