package es.uva.poo.trayectos;

import java.time.LocalDate;

import es.uva.poo.practica2.Muelle;
/**
 * Tipo de datos que hereda de la clase Combinado y que implementa el comportamiento de una clase de
 * trayectos de tipo camion y tren , siendo solo estos de manera exclusiva
 * @author marcgom
 * @author anganto
 */
public class PackCamionTren extends Combinado {
	
	
	
	
	/**
	 * Inicializa un conjunto de trayectos de tipo camion o tren 
	 * @param muelleOrigen, objeto muelle desde el que se inicia el trayecto
	 * @param fechaInicio, objeto LocalDate con la fecha en la que inicia el trayecto
	 * @param muelleDestino, objeto muelle en el que finaliza el trayecto
	 * @param fechaFin, objeto LocalDate con la fecha en la que finaliza el trayecto
	 * @param Simple[] trayectos, array de trayectos
	 * @throws IllegalArgumentException si los trayectos no son de tipo camion o tren
	 * @throws IllegalArgumentException si los trayectos no son barcos o camiones
	 */

	public PackCamionTren(Muelle muelleOrigen, LocalDate fechaInicio, Muelle muelleDestino, LocalDate fechaFin, Simple[] trayectos) {
		
		super(muelleOrigen, fechaInicio, muelleDestino, fechaFin, trayectos);
		
		if(comprobarPack()==false) {
			
			throw
				new IllegalArgumentException("No todos los tracyectos son trenes o camion");
		
		}else if(alMenosUnoDeCada() == false) {
			

			throw
				new IllegalArgumentException("Para que sea Pack, tiene que haber al menos un trayecto en Camion y uno en barco");
			
			
		}
		
	
	}
	
	/**
	 * Permite obtener el precio del pack camion tren quue se habra revisado que sean trayectos de 
	 * ese tipo en el constructor y el precio se basa en los kilometros recorridos por trayecto
	 * @return precio,de tipo double para un array de trayectos de camion o tren 
	 */
	@Override
	public double getPrecio(){
		
		double precio=0;
		
		double distanciaKm;
		
		Simple[] trayectos = getTrayectos();
		
		for(int i=0;i<trayectos.length;i++) {
			
			distanciaKm = trayectos[i].getMuelleDestino().getCoordenadaDecimal().getDistanceTo(trayectos[i].getMuelleOrigen().getCoordenadaDecimal());
			
			precio+=distanciaKm*10;
		}
		
		return precio;
		
	}
	
	/**
	 * Comprueba si todos los trayectos añadidos al pack son del tipo camion o tren
	 * @return true si lo son, false si no
	 */
	@Override
	public boolean comprobarPack() {
		
		boolean comprobador=false;
		
		int contador=0;
		
		Simple[] trayectos = getTrayectos();
		
		for(int i=0;i<trayectos.length;i++) {
			
			if(trayectos[i].getCostePorTrayecto()==20||trayectos[i].getCostePorTrayecto()==200) {
				
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
		int contadorTren = 0;
		
		Simple[] trayectos = getTrayectos();
		
		for(int i=0;i<trayectos.length;i++) {
			
			if(trayectos[i].getCostePorTrayecto()==20){
				
				contadorTren ++;
			
			}else {
				
				contadorCamion ++;
			}
		
		}
		
		if(contadorTren > 0 && contadorCamion > 0) {
			
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
		
		return 220;
		
	}
}
