package es.uva.poo.practica2;

import java.util.ArrayList;
import java.util.List;
import es.uva.inf.poo.maps.GPSCoordinate;

/**
 *  Tipo de dato que modela un puerto de carga y descarga de contenedores. Un puerto se identifica por 2 letras
 * mayusculas referenciando al pais y tres letras mayusculas que referencian a la localidad separadas por un guion
 * Los puertos tienen muelles y un puerto estara operativo si alguno de sus muelles lo esta
 * @author anganto
 * @author marcgom
 *
 */

public class Puerto {

	private String pais;
	
	private String localidad;
	
	private boolean estado;
	
	private Muelle[] muelles;
	
	
	/**
	 * Inicializa un puerto dado su codigo de pais y de localidad. Al ser nuevo y no tener muelles su estado es 
	 * fuera de servicio
	 * @param pais, string formado por 2 letras mayusculas
	 * @param localidad, string formado por 3 letras mayusculas 
	 * @throws IllegalArgumentException pais.length() != 2 || pais.matches("[A-Z]+") == false
	 * @throws IllegalArgumentException localidad.length() != 3 || localidad.matches("[A-Z]+") == false
	 */
	public Puerto (String pais, String localidad) {
		
		if (pais.length() != 2 || pais.matches("[A-Z]+") == false) {
			
			throw
				new IllegalArgumentException ("Código de país incorrecto");
			
		}else{
			
			this.pais = pais;
			
		}
		
		if (localidad.length() != 3 || localidad.matches("[A-Z]+") == false) {
			
			
			throw
				new IllegalArgumentException ("Código de localidad incorrecto");
			
			
		}else {
			
			this.localidad = localidad;
		}
		
		this.estado = false;
		
		this.muelles = new Muelle[0];
		
	}
	
	/**
	 *Cambia el codigo de país del puerto
	 * @param pais, string formado por 2 letras mayusculas
	 * @throws IllegalArgumentException pais.length() != 2 || pais.matches("[A-Z]+") == false
	 */
	public void setPais(String pais) {
		
		
		if (pais.length() != 2 || pais.matches("[A-Z]+") == false) {
			
			throw
				new IllegalArgumentException ("Código de país incorrecto");
			
		}else{
			
			this.pais = pais;
			
		}
		
	}
	
	/**
	 * Cambia el codigo de la localidad del puerto
	 * @param localidad, string formado por 3 letras mayusculas
	 * @throws IllegalArgumentException localidad.length() != 3 || localidad.matches("[A-Z]+") == false
	 */
	public void setLocalidad(String localidad) {
		
		if (localidad.length() != 3 || localidad.matches("[A-Z]+") == false) {
			
			
			throw
				new IllegalArgumentException ("Código de localidad incorrecto");
			
			
		}else {
			
			this.localidad = localidad;
		}
		
	}
	
	/**
	 * Cambia el estado del puerto
	 * @param estado, booleano
	 */
	public void setEstado(boolean estado) {
		
		this.estado = estado;
		
	}
	
	/**
	 * Cambia la lista de muelles del puerto
	 * @param muelles, el nuevo array de objetos muelle
	 * @throws IllegalArgumentException muelles == null
	 */
	public void setMuelles(Muelle[] muelles) {
		
		if (muelles == null) {
			
			throw
				new IllegalArgumentException ("Muelles no pueden ser null");
		}else {
			
			this.muelles = muelles;
			
		}
		
		
		
	}
	
	/**
	 * Consulta el codigo de pais del puerto
	 * @return, string del codigo del pais en formato "AA"
	 */
	public String getPais() {
		
		return pais;
		
	}
	
	/**
	 * Consulta el codigo de localidad del puerto
	 * @return, string del codigo de localidad en formato "AAA"
	 */
	public String getLocalidad() {
		
		return localidad;
	}
	
	/**
	 * Consulta el identificador completo del puerto
	 * @return, string del identificador del puerto en formato "AA-AAA"
	 */
	public String getIdentificador() {
		
		
		return pais + "-" + localidad;
	}
	
	/**
	 * Consulta el estado del puerto
	 * @return, true operativo, false fuera de servicio
	 */
	public boolean getEstado() {
		
		return estado;
	}
	
	/**
	 * Consulta los muelles que contiene el puerto
	 * @return, array de objetos muelle
	 */
	public Muelle[] getMuelles() {
		
		return muelles;
		
	}
	
	/**
	 * Permite añadir un nuevo muelle al puerto, para ello copia los que ya habia y les añade el indicado
	 * @param muelle, objeto que se quiere añadir la puerto
	 * @throws IllegalArgumentException muelle == null
	 * @throws IllegalArgumentException si se intenta añadir un muelle con un identificador ya existente en el puerto
	 * @throws IllegalArgumentException si ell array se hizo mal y se ha dejado un hueco null entre 2 muelles
	 */
	public void aniadirMuelle(Muelle muelle) {
		
		if (muelle == null) {
			
			throw
				new IllegalArgumentException ("El muelle no puede ser null");
		}
		
		muelle.setPuerto(this);
		
		Muelle [] antiguos = getMuelles();
		
		Muelle [] nuevos = new Muelle[antiguos.length + 1];
		
		for (int i = 0; i < antiguos.length; i++) {
			
			if (antiguos[i] == null){
				
				throw
					new IllegalArgumentException("Hay un hueco en la lista de Muelles");
			
			
			}else if (antiguos[i].getIdentificador() == muelle.getIdentificador()) {
				
				throw
					new IllegalArgumentException("No puede haber dos muelles con el mismo identificador en un puerto");
				
				
			}else {
				
				nuevos[i] = antiguos [i];
				
			}	
		}
		
		nuevos[nuevos.length - 1] = muelle;
		
		setMuelles(nuevos);
		
		comprobarEstadoPuerto();
		
	}
	
	/**
	 * Elimina el muelle cuyo identificador sea el proporcionado.
	 * @param identificador, entero de dos dígitos
	 * @throws IllegalArgumentException identificador < 10 || identificador > 99
	 * @throws IllegalArgumentException si no hay ningún muelle con ese identificador
	 */
	public void eliminarMuelle (int identificador) {
		
		boolean eliminado = false;
		
		if (identificador < 10 || identificador > 99) {
			
			throw 
				new IllegalArgumentException ("El identificador tiene que ser un número de dos dígitos");
			
		}
		
		Muelle [] muelles = getMuelles();
		Muelle [] nuevos = new Muelle[muelles.length - 1];
		
		
		for (int i = 0; i < muelles.length; i++) {
			
			if (muelles[i].getIdentificador() == identificador) {
				
				muelles[i] = null;
				eliminado = true;
			}	
		}
		
		if (eliminado == false) {
			
			throw
				new IllegalArgumentException ("No existe ningun muelle con tal identificador");
		
		}else {
			
			if (nuevos.length != 0) {
				
				for (int i = 0; i < nuevos.length; i ++) {
					
					if (i == 0) {
						
						if (muelles[i] != null) {
							
							nuevos[i] = muelles [i];
						
						}else {
							
							nuevos[i] = muelles[i+1];
						}
					
					}else {
						
						if (muelles[i] != null && nuevos[i-1].getIdentificador() != muelles[i].getIdentificador()) {
							
							nuevos[i] = muelles [i];
						
						}else {
							
							nuevos[i] = muelles[i+1];
						}	
					}
				}		
			}
			
			setMuelles(nuevos);
			comprobarEstadoPuerto();
			
		}
		
		
		
	
	
	}
	
	/**
	 * Consulta si un puerto esta completo, si alguno de sus muelles no lo esta, el puerto tampoco
	 * @return, true si esta completo, false si no
	 */
	public boolean puertoCompleto(){
		
		boolean completo = true;
		
		Muelle [] muelles = getMuelles();
		
		for (int i = 0; i < muelles.length; i++) {
			
			if (muelles[i].muelleLLeno() == false) {
				
				completo = false;
				
			}
			
		}
		
		return completo;
		
	}
	
	/**
	 * Lista en un mismo array los muelles que esten operativos del puerto
	 * @return, lista de objetos muelle cuyo estado es operativo (operativo = true)
	 */
	public List<Muelle> muellesOperativos() {
		
		Muelle [] muelles = getMuelles();
		
		List<Muelle> operativos = new ArrayList<>();
		
		for (int i = 0; i < muelles.length; i++) {
			
			if (muelles[i].getEstado() == true) {
				
				operativos.add(muelles[i]);
				
			}
			
		}
		
		return operativos;
		
		
	}
	
	/**
	 * Lista en un mismo array aquellos muelles del puerto que tengan espacios para contenedores
	 * @return, lista de objetos muelle con espacio libre (muelleLLenos() = false)
	 */
	public List<Muelle> muellesConEspacio() {
		
		Muelle [] muelles = getMuelles();
		
		List<Muelle> conEspacio = new ArrayList<>();
		
		for (int i = 0; i < muelles.length; i++) {
			
			if (muelles[i].muelleLLeno() == false) {
				
				conEspacio.add(muelles[i]);
				
			}
			
		}
		
		return conEspacio;
		
	}
	
	
	/**
	 * Lista los muelles que esten a una distancia inferior a la introducida del punto GPS introducido
	 * @param distancia, double positivo en km
	 * @param punto, coordenadas de un punto
	 * @return, lista de objetos muelle que cumplen la condicion de estar a menos distancia del punto que la dada
	 * @throws IllegalArgumentException distancia < 0
	 * @throws IllegalArgumentException punto == null
	 */
	public List<Muelle> muellesDistanciaInferior(double distancia, GPSCoordinate punto) {
		
		if (distancia < 0) {
			
			throw
				new IllegalArgumentException ("La distancia no puede ser negativa");
		
		}else if (punto == null) {
			
			throw
				new IllegalArgumentException ("El punto no puede ser null");
		}
		
		Muelle [] muelles = getMuelles();
		
		List<Muelle> distanciaInferior = new ArrayList<>();
		
		GPSCoordinate posicion;
		
		for (int i = 0; i < muelles.length; i++) {
			
			posicion = muelles[i].getCoordenadaDecimal();
			
			if (posicion.getDistanceTo(punto) < distancia) {
				
				distanciaInferior.add(muelles[i]);
			
			}
		}
		return distanciaInferior;
	}

	/**
	 * Actualiza el estado de un puerto cada vez que se cambia el estado de alguno de sus muelles o se elimina
	 */
	public void comprobarEstadoPuerto () {
		
		boolean estado = false;
		
		Muelle [] muelles = getMuelles();
		
		if (muelles.length != 0) {
			
			for (int i = 0; i < muelles.length; i++) {
			
				if (muelles[i].getEstado() == true) {
				
					estado = true;
				}
			
			
			}
		
		}
		setEstado(estado);
		
		
	}

	/**
	 * Comprueba si dos puertos son iguales, considerando iguales aquellos con el mismo identificador
	 * @param otro, puerto a comparar
	 * @return true si son iguales, false si no
	 */
	@Override
	public boolean equals (Object otro) {
		
		boolean iguales = false;
		
		if (((Puerto)otro) == null) {
			
			throw
				new IllegalArgumentException("El puerto no puede ser null");
			
		}else if (getIdentificador().equals(((Puerto) otro).getIdentificador())){
			
			
			iguales = true;
			
		}
		
		return iguales;
	}

}