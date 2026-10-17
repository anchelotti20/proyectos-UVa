package es.uva.poo.trayectos;

import java.time.LocalDate;

import es.uva.poo.practica2.Muelle;
import es.uva.poo.practica2.Puerto;

/**
 * Implementacion de un tipo de dato que almacena la información de los viajes que realiza un contenedor. Lo carateriza el 
 * puerto y muelle de origen, puerto y muelle destino y la fecha de inicio y fin del viaje
 * @author anganto
 * @author marcgom
 *
 */
public abstract class Trayecto {

	private Puerto puertoOrigen;
	
	private Muelle muelleOrigen;
	
	private LocalDate fechaInicio;
	
	private Puerto puertoDestino;
	
	private Muelle muelleDestino;
	
	private LocalDate fechaFin;
	
	
	/**
	 * Inicializa un trayecto a partir de sus muelles de origen y destino y de su fecha de inicio y final
	 * @param muelleOrigen, objeto muelle desde el que se inicia el trayecto
	 * @param fechaInicio, objeto LocalDate con la fecha en la que inicia el trayecto
	 * @param muelleDestino, objeto muelle en el que finaliza el trayecto
	 * @param fechaFin, objeto LocalDate con la fecha en la que finaliza el trayecto
	 * @throws IllegalArgumentException muelleOrigen == null || muelleDestino == null
	 * @throws IllegalArgumentException si el muelle de origen y el de destino son el mismo
	 * @throws IllegalArgumentException fechaInicio == null || fechaFin == null
	 * @throws IllegalArgumentException si la fecha de finalizacion es anterior a la de inicio o son la misma
	 * @throws IllegalArgumentException si alguno de los muelles no tiene puerto
	 * throws IllegalArgumentException si el muelle no es compatible con el trayecto
	 */
	public Trayecto (Muelle muelleOrigen, LocalDate fechaInicio, Muelle muelleDestino, LocalDate fechaFin) {
		
		
		
		if (muelleOrigen == null || muelleDestino == null) {
			
			throw
				new IllegalArgumentException ("Muelle no puede ser null");
		
		}else if(muelleOrigen.getPuerto() == null || muelleDestino.getPuerto() == null) {
			
			throw
				new IllegalArgumentException("Los muelles tienen que pertenecer a un puerto");
			
		}else if (muelleOrigen.equals(muelleDestino) == true) {
			
			throw
				new IllegalArgumentException("El muelle de origen no puede ser el mismo al de destino");
			
			
		}else {
			
			this.muelleDestino = muelleDestino;
			this.muelleOrigen = muelleOrigen;
		
			this.puertoDestino = muelleDestino.getPuerto();
			this.puertoOrigen = muelleOrigen.getPuerto();
		}
		
		if (fechaInicio == null || fechaFin == null) {
			
			throw
				new IllegalArgumentException("Las fechas no pueden ser null");
			
		}else if (fechaInicio.isBefore(LocalDate.now())){
			
			throw
				new IllegalArgumentException ("La fecha de inicio no puede ser anterior a la actual");
			
		}else if (fechaFin.isBefore(fechaInicio) == true || fechaFin.isEqual(fechaInicio) == true) {
			
			throw
				new IllegalArgumentException("La fecha de final de trayecto no puede ser anterior o igual a la de inicio");
			
		}else
		
			
			this.fechaFin = fechaFin;
			this.fechaInicio = fechaInicio;
			
			

			
		}
	
		
	
		
		
		
	
	/**
	 * Cambia el muelle de origen del trayecto, tambien cambia el puerto en caso de que el muelle sea de otro puerto
	 * @param muelleOrigen, nuevo muelle de origen
	 * @throws IllegalArgumentException muelleOrigen == null || muelleOrigen.muelleEquals(getMuelleDestino()) == true
	 */
	public void setMuellePuertoOrigen(Muelle muelleOrigen) {
		
		if (muelleOrigen == null) {
			
			throw
				new IllegalArgumentException ("Muelle no puede ser null");
			
		}else if (muelleOrigen.getPuerto()== null) {
			
			throw
				new IllegalArgumentException ("Muelle debe pertenecer a un Puerto");
		
		}else if(muelleOrigen.equals(getMuelleDestino()) == true){
			
			throw
				new IllegalArgumentException ("Muelle origen no puede ser igual al de destino");
		}else {
			
			this.muelleOrigen = muelleOrigen;
			this.puertoOrigen = muelleOrigen.getPuerto();
		}
		
		
	}
	
	/**
	 * Cambia el muelle de destino del trayecto, tambien cambia el puerto en caso de que el muelle sea de otro puerto
	 * @param muelleDestino, nuevo muelle de destino
	 * @throws IllegalArgumentException muelleDestino == null || muelleDestino.muelleEquals(getMuelleOrigen()) == true
	 */
	public void setMuellePuertoDestino(Muelle muelleDestino) {
		
		if (muelleDestino == null) {
			
			throw
				new IllegalArgumentException ("Muelle no puede ser null");
			
			
		}else if(muelleDestino.getPuerto() == null) {
			
			throw
				new IllegalArgumentException ("Muelle debe pertenecer a un puerto");
			
		}else if(muelleDestino.equals(getMuelleOrigen()) == true) {
				
			throw
				new IllegalArgumentException ("Muelle no puede ser igual al de origen");
		}else {
			
			this.muelleDestino = muelleDestino;
			this.puertoDestino = muelleDestino.getPuerto();
			
		}
		
		
	}
	
	
	/**
	 * Permite cambiar la fecha de inicio de un trayecto que esta asociado al viaje de un contenedor
	 * @param fechaInicio, nueva fecha de inicio
	 * @throws IllegalArgumentException si la fecha es null ,anterior a la actual o despues de la final
	 */
	
	public void setFechaInicio(LocalDate fechaInicio) {
		
		if(fechaInicio == null) {
			
			throw
				new IllegalArgumentException("La fecha no puede ser null");
		
		}else if(fechaInicio.isBefore(LocalDate.now())) {
			
			throw
				new IllegalArgumentException("La fecha de inicio no puede ser anterior a la actual");
		
		}else if (fechaInicio.isAfter(getFechaFin()) == true || fechaInicio.isEqual(getFechaFin()) == true){
				
				throw
					new IllegalArgumentException("La fecha no puede ser después o igual a la final");
				
		}else {
				
			this.fechaInicio = fechaInicio;
		}
		
		
		
	}
	
	
	/**
	 * Permite modificar la fecha de finalizacion de un trayecto asociado a un contendor
	 * @param fechaFin, nueva fecha de fin
	 * @throws IllegalArgumentException si la fecha es null ,anterior a la inicial 
	 */
	
	public void setFechaFin(LocalDate fechaFin) {
		
		if(fechaFin == null ) {
			
			throw
				new IllegalArgumentException("La fecha no puede ser null");
			
		}else if (fechaFin.isBefore(getFechaInicio()) == true || fechaFin.isEqual(getFechaInicio()) == true){
				
				throw
					new IllegalArgumentException("La fecha no puede ser antes o igual a la inicial");
				
		}else {
				
			this.fechaFin = fechaFin;
		}
		
		
		
	}
	
	/**
	 * Consulta un objeto de tipo puerto , siendo el puerto de origen del trayecto
	 * @return devuleve un objeto puerto que es el  puerto origen
	 */

	public Puerto getPuertoOrigen() {
		
		
		return puertoOrigen;
	}
	
	/**
	 * Consulta el objeto de tipo puerto, que es el puerto destino del trayecto
	 * @return devuleve un objeto puerto que es el  puerto destino
	 */

	public Puerto getPuertoDestino() {
		
		return puertoDestino;
	}
	
	/**
	 * Consulta el objeto muelle desde el que se parte 
	 * @return muelle origen
	 */
	
	public Muelle getMuelleOrigen() {
		
		return muelleOrigen;
	}
	
	/**
	 * Consulta el objeto muelle el cual es el destino del trayecto
	 * @return muelle destino
	 */
	public Muelle getMuelleDestino() {
		
		return muelleDestino;
		
	}
	/**
	 * Consulta a objeto de tipo LocalDate sobre que fecha de inicio es el trayecto
	 * @return fecha de inicio de trayecto
	 */
	
	public LocalDate getFechaInicio() {
		
		return fechaInicio;
		
	}
	/**
	 * Consulta sobre fecha de finalizacion sobre el trayecto que se realiza
	 * @return fecha de finaliacion de trayecto
	 */
	public LocalDate getFechaFin() {
		
		return fechaFin;
		
	}
	
	/**
	 * Metodo que permite conocer si la fecha final del trayecto es superior a otra 
	 * @param otra, que es una fecha de LocalDate tambien
	 * @return superior, siendo este un valor boolean que permite saber si la fechaFin es superior o no
	 * @throws IllegalArgumentException si la fecha es null
	 */
	public boolean fechaFinSuperior (LocalDate otra) {
		
		boolean superior = false;
		
		if (otra == null) {
			
			throw
				new IllegalArgumentException("La fecha no puede ser null");
			
			
		}else if (fechaFin.isAfter(otra) == true) {
			
			superior = true;
			
		}
				
		return superior;
	}
	
	/**
	 * Obtencion de la distancia en millas marinas de un trayecto
	 * Obtenemos en trayecto muelle destino y muelle origen, obtenemos su coordenadas decimales
	 * que la clase GPSCoordinate devuelve en km y pasamos a millas marinas.
	 * getDistanceTo garantiza un resultado >=0.
	 * @return distancia del trayecto en millas marinas
	 */
	
	public double getMillasMarinas() {
		
		double distanciaKm = getMuelleDestino().getCoordenadaDecimal().getDistanceTo(getMuelleOrigen().getCoordenadaDecimal());
		double distanciaMillas=distanciaKm*0.539957;
		return distanciaMillas;
		
		
	}
	
	/**METODO ABTSRACTO QUE LAS SUBCLASES: TBARCO, TCAMION Y TTren calcularan a su manera
	 * Consulta que obtiene el precio de un trayecto a partir de un trayecto, compara sus fechas
	 * de finalizacion e inicio, despues el cliente pasa como parametro el precio por dia y coste 
	 * de las Millas Marinas
	 * @return devuelve el precio total de un trayecto.
	 */
	public abstract double getPrecio();
	
	
	/**
	 * Metodo que devuelve la informacion completa de un trayecto, 
	 * su identificador completo,(localidad y pais) fecha de inicio
	 * y el puerto destino con su identificador(localidad y pais) con su fecha de finalizacion
	 * @return un string con toda la informacion del trayecto 
	 */
	public String getInformacion() {
		
		
		return "País Origen: " + getPuertoOrigen().getPais() + "; Localidad Origen: " + getPuertoOrigen().getLocalidad() + "; Fecha Inicio: " + getFechaInicio().toString() + "; País Destino: " + getPuertoDestino().getPais() + "; Localidad Destino: " + getPuertoDestino().getLocalidad() + "; Fecha Fin: " + getFechaFin().toString();
		
		
		
	}
	
	/**
	 * Metodo abstracto para obbtener los diferentes precios POR TRAYECTO de cada uno de los trayectos,
	 * permitiendonos diferenciar estos por sus costes de trayecto (o costes fijos)
	 * @return costePorTrayecto siendo este de tipo entero
	 */
	public abstract int getCostePorTrayecto();

	
	
}