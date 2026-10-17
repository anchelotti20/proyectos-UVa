package es.uva.poo.practica2;

import java.util.ArrayList;

import es.uva.inf.poo.maps.GPSCoordinate;
import es.uva.poo.contenedor.Contenedor;
import es.uva.poo.trayectos.Trayecto;


/**
 * Tipo de datos que modela un muelle de carga de contenedores. Los muelles se caracterizan por un identificador,
 * su posicion GPS y su estado. Cada uno tiene un número variable de plazas y un número máximo de contenedores apilables.
 * @author anganto
 * @author marcgom
 * 
 */
public class Muelle {


	private int identificador;
	
	private GPSCoordinate coordenadasGPS;
	
	private boolean operativo;
	
	private int plazas;
	
	private int cantidadApilable;
	
	private Contenedor[][] contenedores;
	
	private Puerto puerto;
	
	private boolean barco;
	
	private boolean tren;
	
	private boolean camion;
	
	
	/**
	 * Inicializar un muelle a partir de su identificador, sus coordenadas en grados decimales, su estado
	 * el numero de plazas y el número máximo de contenedores apilables. 
	 * @param identificador, entero positivo de 2 digitos
	 * @param latitud, double entre  menos 90 y 90
	 * @param longitud, double entre menos 180 y 180
	 * @param operativo, booleano
	 * @param plazas, entero positivo mayor que 0, si introduces 2, existen las plazas 0 y 1
	 * @param cantidadApilable, entero positivo mayor que 0, si introduces 2, existen el contenedor del suelo y el de encima
	 * @param barco, bool si tiene infraestructura para barcos o no
	 * @param tren, bool si tiene infraestructura para trenes o no
	 * @param camion, bool si tiene infraestructura para camiones o no
	 * @throws IllegalArgumentException identificador < 10 || identificador > 99
	 * @throws IllegalArgumentException plazas <= 0 || cantidadApilable <= 0
	 * @throws IllegalArgumentException si todos los transportes son false
	 */
	public Muelle (int identificador, double latitud, double longitud, boolean operativo, int plazas, int cantidadApilable, boolean barco, boolean tren, boolean camion ) {
		if (identificador < 10 || identificador > 99) {
			throw
				new IllegalArgumentException("El identificador tiene que ser un número de 2 dígitos");
				
		}else {
				
			this.identificador = identificador;
				
		}
		
		this.coordenadasGPS = new GPSCoordinate(latitud, longitud);
		
		if (plazas <= 0 || cantidadApilable <= 0) {
			throw
				new IllegalArgumentException("El muelle no puede tener plazas o cantidad apilable negativas ni 0");
		}else {
			
			
			this.plazas = plazas;
			this.cantidadApilable = cantidadApilable;
			
		}
		
		this.operativo = operativo;
		
		
		if(barco == false && tren == false && camion == false) {
			
			throw
				new IllegalArgumentException("El muelle tiene que tener al menos una infraestructura de transporte");
			
		}else {
			
			this.barco=barco;
			
			this.tren=tren;
			
			this.camion=camion;
			
		}
		
		
		
		
		
		
		this.contenedores = new Contenedor[plazas][cantidadApilable];
		
		this.puerto = null;
	}
		
	/**
	* Inicializar un muelle a partir de su identificador, sus coordenadas en grados, minutos y segundos, su estado,
	* el numero de plazas y el número máximo de contenedores apilables. 
	* El identificador tiene que ser un número de dos dígitos, el número de plazas y cantidad apilable no puede ser menor que 1
	* @param identificador, entero positivo de 2 digitos
	* @param latitud, string formato GGGºMM'SS"H
	* @param longitud, string formato GGGºMM'SS"H
	* @param operativo, booleano
	* @param plazas, entero positivo mayor que 0, si introduces 2, existen las plazas 0 y 1
	* @param cantidadApilable, entero positivo mayor que 0,si introduces 2, existen el contenedor del suelo y el de encima
	* @param barco, bool si tiene infraestructura para barcos o no
	* @param tren, bool si tiene infraestructura para trenes o no
	* @param camion, bool si tiene infraestructura para camiones o no
	* @throws IllegalArgumentException identificador < 10 || identificador > 99
	* @throws IllegalArgumentException plazas <= 0 || cantidadApilable <= 0
	* @throws IllegalArgumentException si todos los transportes son false
	*/
	public Muelle (int identificador, String latitud, String longitud, boolean operativo, int plazas, int cantidadApilable,boolean barco, boolean tren, boolean camion) {
			
		if (identificador < 10 || identificador > 99) {
			
			throw
				new IllegalArgumentException("El identificador tiene que ser un número de 2 dígitos");
				
		}else {
				
			this.identificador = identificador;
				
		}
			
		this.coordenadasGPS = new GPSCoordinate(latitud, longitud);
		
		if (plazas <= 0 || cantidadApilable <= 0) {
			throw
				new IllegalArgumentException("El muelle no puede tener plazas o cantidad apilable negativas ni 0");
		}else {
			
			
			this.plazas = plazas;
			this.cantidadApilable = cantidadApilable;
			
		}
		
		this.operativo = operativo;
		
		if(barco == false && tren == false && camion == false) {
			
			throw
				new IllegalArgumentException("El muelle tiene que tener al menos una infraestructura de transporte");
			
		}else {
			
			this.barco=barco;
			
			this.tren=tren;
			
			this.camion=camion;
			
		}
		
		
		this.contenedores = new Contenedor[plazas][cantidadApilable];
		
		this.puerto = null;
		
	}
	
	/**
	 * Cambia el valor del identificador del muelle, este tiene que ser un valor numérico de 2 cifras
	 * @param identificador, entero de 2 cifras
	 * @throws IllegalArgumentException identificador < 10 || identificador > 99
	 */
	public void setIdentificador(int identificador) {
		
		if (identificador < 10 || identificador > 99) {
			
			throw
				new IllegalArgumentException("El identificador tiene que ser un número de 2 dígitos");
				
		}else {
				
			this.identificador = identificador;
				
		}
				
	}
	
	/**
	 * Cambia el valor de las coordenadas GPS en grados decimales del muelle
	 * @param latitud, entero entre -90 y 90
	 * @param longitud entero entre -180 y 180
	 */
	public void setCoordenadasDecimales(double latitud, double longitud) {
		
		this.coordenadasGPS = new GPSCoordinate(latitud, longitud);
		
		
	}
	
	/**
	 * Cambia el valor de las coordenadas GPS en formato GGGºMM'SS" del muelle
	 * @param latitud string formato GGGºMM'SS"H
	 * @param longitud string formato GGGºMM'SS"H
	 */
	public void setCoordenadasGMS (String latitud, String longitud) {
		
		
		this.coordenadasGPS = new GPSCoordinate(latitud, longitud);
		
	}
	
	/**
	 * Cambia el estado del muelle y si pertenece a algún puerto comprueba el posible cambio de estado del puerto
	 * @param operativo, boolean nuevo estado
	 */
	public void setEstado (boolean operativo) {
		
		this.operativo = operativo;
		
		if (puerto != null) {
			
			puerto.comprobarEstadoPuerto();
		}
	}
	
	/**
	 * Cambia el tipo de trayecto para establecer un trayecto en barco
	 * @param barco boolean de barco
	 */
	public void setBarco(boolean barco) {
		this.barco=barco;
	}
	
	
	/**
	 * Cambia el tipo de trayecto para establecer un trayecto en tren
	 * @param tren boolean de tren
	 */
	public void setTren(boolean tren) {
		this.tren=tren;
	}
	
	
	
	/**
	 * Cambia el tipo de trayecto para establecer un trayecto en camion
	 * @param camion boolean de camion
	 */
	public void setCamion(boolean camion) {
		this.camion=camion;
	}
	

	/**
	 * Asigna a que puerto pertenece el muelle para poder manejar el estado del puerto
	 * @param puerto, nuevo puerto
	 * @throws IllegalArgumentException puerto == null
	 */
	public void setPuerto (Puerto puerto) {
		
		if (puerto == null) {
			
			throw
				new IllegalArgumentException ("El puerto no puede ser null");
			
		}else {
			
			this.puerto = puerto;
		}
	}
	
	
	/**
	 * Consulta el identificador del muelle
	 * @return un entero de dos dígitos
	 */
	public int getIdentificador() {
		
		return identificador;
	}
	
	
	/**
	 * Consulta las coordenadas en grados decimales
	 * @return Una copia del objeto que representa las coordenadas
	 */
	public GPSCoordinate getCoordenadaDecimal() {
		
		double latitud = coordenadasGPS.getLatitudeGD();
		double longitud = coordenadasGPS.getLongitudeGD();
		
		return new GPSCoordinate(latitud, longitud);
		
	}
	
	/**
	 * Consulta las coordenadas en grados, minutos y segundos
	 * @return Una copia del objeto que representa las coordenadas
	 */
	public GPSCoordinate getCoordenadaGMS() {
		
		String latitud = coordenadasGPS.getLatitudeGMS();
		String longitud = coordenadasGPS.getLongitudeGMS();
		
		return new GPSCoordinate(latitud, longitud);
	}
	
	/**
	 * Consulta el estado del muelle
	 * @return valor True para operativo, False para fuera de servicio
	 */
	public boolean getEstado() {
		
		return operativo;
		
	}
	
	
	/**
	 * Consulta el tipo de transporte del contenedor, y es de tipo barco
	 * @return valor True si es un transporte de tipo barco, false en caso contrario
	 */
	
	public boolean getBarco() {
		
		return barco;
	}
	
	
	/**
	 * Consulta el tipo de transporte del contenedor, y es de tipo barco
	 * @return valor True si es un transporte de tipo barco, false en caso contrario
	 */
	
	public boolean getTren() {
		
		return tren;
	}
	
	
	
	
	/**
	 * Consulta el tipo de transporte del contenedor, y es de tipo barco
	 * @return valor True si es un transporte de tipo barco, false en caso contrario
	 */
	
	public boolean getCamion() {
		
		return camion;
	}
	
	
	/**
	 * Consulta el número de plazas que tiene el muelle
	 * @return un valor entero mayor que 0
	 */
	public int getPlazas() {
		
		return plazas;
	}
	
	/**
	 * Consulta la cantidad máxima de contenedores que se pueden apilar
	 * @return un valor entero mayor que 0
	 */
	public int getCantidadApilable() {
		
		return cantidadApilable;
	}
	
	/**
	 * Consulta la matriz de contenedores contenidos en el muelle
	 * @return matriz de objetos contenedor que contiene el muelle
	 */
	public Contenedor[][] getContenedores(){
			
		
		return contenedores; 
		
		
	}
	
	/**
	 * Consulta a que puerto pertenece el muelle
	 * @return objeto puerto que contiene al muelle
	 */
	public Puerto getPuerto() {
		
		return puerto;
	}
	 
	/**
	 * Consulta el numero de plazas vacías en un muelle
	 * @return numero entero positivo mayor o igual que 0
	 */
	public int getPlazasVacias () {
		
		int plazasVacias = 0;
		
		Contenedor[][] plazas = getContenedores();
		
		for(int i = 0; i < getPlazas(); i++) {
			
				
			if (plazas[i][0] == null) {
					
				plazasVacias ++;
								
			}
				
				
		}
			
		return plazasVacias;
			
	}
		
	/**
	 * Consultar el numero de plazas semillenas	
	 * @return numero entero mayor o igual que 0
	 */
	public int getPlazasSemillenas () {
		
		int plazasSemillenas = 0;
		
		int contenedoresPlaza = 0;
		
		Contenedor[][] plazas = getContenedores();
		
		Contenedor ultimoContenedor = null;
		
		for(int i = 0; i < getPlazas(); i++) {
			
			
			for (int j = 0; j < getCantidadApilable(); j++) {
				
				if (plazas[i][j] != null) {
					
					contenedoresPlaza ++;
					
					ultimoContenedor = plazas[i][j];
					
				}
				
			}
			
			if (contenedoresPlaza != 0) {
				
				if (contenedoresPlaza != plazas[0].length && ultimoContenedor.getTecho() == true) {
					
					plazasSemillenas ++;	
					
				}
				
			}
			
			contenedoresPlaza = 0;
			ultimoContenedor = null;
		}
		
		return plazasSemillenas;
		
	}
		
	/**
	 * Consultar el numero de plazas llenas (considera llenas que esten completamente apiladas o que el de más arriba no tenga techo)
	 * @return numero entero mayor o igual que 0
	 */
	public int getPlazasLLenas() {
		
		int plazasLLenas = 0;
		
		int contenedoresPlaza = 0;
		
		Contenedor [][] plazas = getContenedores();
		
		Contenedor ultimoContenedor = null;
		
		for(int i = 0; i < getPlazas(); i++) {
			
			for (int j = 0; j < getCantidadApilable(); j++) {
				
				if (plazas[i][j] != null) {
					
					contenedoresPlaza ++;
					
					ultimoContenedor = plazas[i][j];
					
				}
				
			}
			
			if(contenedoresPlaza > 0) {
				
				if (contenedoresPlaza == plazas[0].length) {
					
					plazasLLenas ++;
					
				}else if(ultimoContenedor.getTecho() == false) {
						
					plazasLLenas ++;
				
					
				}
				
			}
			
			
			contenedoresPlaza = 0;
			ultimoContenedor  = null;
		}
		
		return plazasLLenas;
		
	}
	
	/**
	 * Devuelve la plaza en la que se encuentra el contenedor cuyo codigo es el parametro
	 * @param identificador, string que une los atributos que forman el identidicador de un contenedor
	 * @return entero del numero de la plaza del contenedor
	 * @throws IllegalArgumentException si el contenedor no está en el muelle
	 * @throws IllegalArgumentException identificador == null
	 */
	public int plazaContenedor (String identificador) {
		
		if (identificador == null) {
			
			throw
				new IllegalArgumentException ("El identificador no puede ser null");
		}
		
		int plaza = 0;
		
		Contenedor [][] plazas = getContenedores();
		
		for (int i = 0; i < getPlazas(); i++) {
			
			for (int j = 0; j < getCantidadApilable(); j++) {
				
				if (plazas[i][j] != null) {
					
					if (plazas[i][j].getIdentificador().equals(identificador)) {
						
						plaza = i;
						return plaza;
					}
				}
			}
		}
		
		throw
			new IllegalArgumentException ("El contenedor no está en el muelle");	
	}
	
	
	/**
	 * Devuelve el nivel de la plaza que se encuentra el contenedor cuyo codigo es el parametro
	 * @param identificador, string que une los atributos que forman el identidicador de un contenedor
	 * @return entero con la altura a la que se encuentra el contenedor
	 * @throws IllegalArgumentException si el contenedor no está en el muelle
	 * @throws IllegalArgumentException identificador == null
	 */
	public int nivelContenedor (String identificador) {
		
		if (identificador == null) {
			
			throw
				new IllegalArgumentException ("El identificador no puede ser null");
		}
		
		int nivel = 0;
		
		Contenedor [][] plazas = getContenedores();
		
		for (int i = 0; i < getPlazas(); i++) {
			
			for (int j = 0; j < getCantidadApilable(); j++) {
				
				if(plazas[i][j] != null) {
					
					if (plazas[i][j].getIdentificador().equals(identificador)) {
						
						nivel = j;
						return nivel;
					}
				}
			}
		}
		
		throw
			new IllegalArgumentException ("El contenedor no está en el muelle");	
	}
		
	
	/**
	 * Asigna el contenedor dado a la plaza dada, tiene en cuenta si esta vacia o si en el que se le va a apilar tiene techo.
	 * Si es un FlatRack, comprueba que haya 2 plazas vacias y que esten contiguas para poder asignarlo
	 * @param lugar, numero entero mayor igual que 0 y menor igual al numero de plazas del muelle.
	 * @param contenedor, objeto contenedor que quiere ser asignado a la plaza
	 * @throws IllegalArgumentException contenedor == null
	 * @throws IllegalArgumentException lugar > getPlazas() || lugar < 0
	 * @throws IllegalArgumentException si no hay hueco en la plaza en la que se solicita la asignacion
	 * @throws IllegalArgumentException si ya hay un contenedor con el mismo identificador en el muelle
	 * @throws IllegalArgumentException si hay menos de 2 plazas vacias y se intenta añadir un FlatRack
	 * @throws IllegalArgumentException si el contenedor no tiene trayectos compatibles con la infraestructura del muelle
	 * @throws IllegalArgumentException si se intenta añadir un FlatRack y no hay transporte en barco o en tren
	 * @throws IllegalArgumentException si se intenta añadir un refrigerado y no hay transporte en camion o barco
	 * @throws IllegalArgumentException si se intenta añadir un FlatRack y las plazas vacias no son contiguas
	 */
	public void asignarPlaza (int lugar, Contenedor contenedor) {
		
		if (contenedor == null) {
			
			throw 
				new IllegalArgumentException ("El contenedor no puede ser null");
		
		}else if (lugar > getPlazas() - 1 || lugar < 0) {
			
			throw
				new IllegalArgumentException ("El número de plaza es mayor al del muelle o negativo");
			
		}
		
		if(contenedor.getParedes() == false && this.getPlazasVacias() < 2) {
			
			throw
				new IllegalArgumentException ("No hay espacio para un contenedor FlatRack");
			
		}
		
		ArrayList<Trayecto> viajesContenedor = contenedor.getTrayectos();
		boolean aceptarSusTrayectos = false;
		
		if (viajesContenedor.size() != 0) {
			
			for(int i = 0; i < viajesContenedor.size(); i++) {
				
				if(viajesContenedor.get(i).getCostePorTrayecto() == 200 && this.getCamion() == true) {
					
					aceptarSusTrayectos = true;
					
				}else if(viajesContenedor.get(i).getCostePorTrayecto() == 20 && this.getTren() == true) {
					
					
					aceptarSusTrayectos = true;
				
				}else if(viajesContenedor.get(i).getCostePorTrayecto() == 0 && this.getBarco() == true) {
					
					
					aceptarSusTrayectos = true;
				
				}else if(viajesContenedor.get(i).getCostePorTrayecto() == 4200 && (this.getCamion() == true || this.getBarco() == true)){
					
					aceptarSusTrayectos = true;
				
				}else if(viajesContenedor.get(i).getCostePorTrayecto() == 220 && (this.getCamion() == true || this.getTren() == true)){
					
					aceptarSusTrayectos = true;
				
				}
				
			}
			
			if (aceptarSusTrayectos == false) {
				
				throw
					new IllegalArgumentException ("Los trayectos del contenedor no son compatibles con la infraestructura del muelle");
				
			}
			
			
			
		}else if (contenedor.getParedes() == false && this.getBarco() == false && this.getTren() == false) {
			
				throw
					new IllegalArgumentException ("Para meter un FlatRack es necesario tener transporte en barco o en tren");
			
		}else if(contenedor.getMotor() == true && this.getCamion() == false && this.getBarco() == false) {
			
			throw
				new IllegalArgumentException ("Para meter un Refrigerado es necesario tener transporte en camion o barco");
			
		}
		
		
		Contenedor [][] plazas = getContenedores();
		
		boolean contenedorColocado = false;
		
		for (int i = 0; i < getPlazas(); i++) {
			
			for (int j = 0; j < getCantidadApilable(); j ++) {
				
				if (plazas[i][j] != null) {
					
					if(plazas[i][j].getIdentificador().equals(contenedor.getIdentificador())) {
				
						throw
							new IllegalArgumentException ("El contenedor ya se encuentra en el muelle");
					}
				}
				
			}
			
		}
		
		if (contenedor.getParedes() == false) {
			
			if(plazas[lugar][0] == null) {
				
				if(flatRackValido(lugar, contenedor, plazas) == false) {
					
					throw
						new IllegalArgumentException ("No hay 2 plazas contiguas vacias para meter el FlatRack");
					
					
				}
				
			}else {
				
				throw
					new IllegalArgumentException ("Hay un contenedor en la plaza donde intentas meter el FlatRack");
				
			}
				
			
		}else {
			
			
			if (plazas[lugar][0] == null) {
			
				plazas[lugar][0] = contenedor;

			
			}else if (plazas[lugar].length == 1 ){
			
				throw
					new IllegalArgumentException ("La plaza está llena");
		
			}else {
			
				for (int j = 1; j < plazas[lugar].length; j++) {
			
					if (plazas[lugar][j] == null && contenedorColocado == false) {
					
						if(plazas[lugar][j-1].getTecho() == true) {
						
							plazas[lugar][j] = contenedor;
						
							contenedorColocado = true;
					
						}else {
					
							throw
								new IllegalArgumentException ("La plaza está llena");
						}
					}
			
				}
				if (contenedorColocado == false) {
				
					throw
						new IllegalArgumentException ("La plaza está llena");
				
				}
		
			}
	
		
		
		}
	}
	/**
	 * Desapila el contenedor que se encuntre en la plaza y altura proporcionadas. Solo se puede sacar un contenedor
	 * si no tiene otro encima. Si se trata de un FlatRack, lo elimina de ambas plazas que ocupa
	 * @param plaza, entero mayor o igual a 0
	 * @param altura, entero mayor o igual a 0
	 * @throws IllegalArgumentException plaza > getPlazas() - 1 || plaza < 0
	 * @throws IllegalArgumentException altura > getCantidadApilable() - 1 || altura < 0
	 * @throws IllegalArgumentException plazas [plaza][altura] == null
	 * @throws IllegalArgumentException si se intenta quitar un contenedor que tiene otro encima
	 */
	public void sacarContenedor (int plaza, int altura) {
		
		if (plaza > getPlazas() - 1 || plaza < 0) {
			
			throw
				new IllegalArgumentException("El numero de plaza es mayor a las del muelle o negativo");
		
		}else if (altura > getCantidadApilable() - 1 || altura < 0) {
			
			
		throw 
			new IllegalArgumentException ("La altura no puede ser negativa");
			
			
		}
		
	
		Contenedor [][] plazas = getContenedores();
		Contenedor contenedorEnLaPlaza = plazas[plaza][altura];
		
		if (contenedorEnLaPlaza == null) {
			
			throw
				new IllegalArgumentException("No hay ningun contenedor en la posición indicada");
		
		}
		
		if(contenedorEnLaPlaza.getParedes() == false) {
			
			
			for(int i = 0; i < getPlazas(); i ++) {
				
				if(plazas[i][altura].getIdentificador().equals(contenedorEnLaPlaza.getIdentificador())) {
					
					plazas[i][altura] =null;
					
					
				}
				
				
			}
				
		}else {
			
			
				
			if (altura == getCantidadApilable() - 1) {
				
				plazas[plaza][altura] = null;
			
			}else if (plazas[plaza][altura + 1] != null) {
				
				throw
					new IllegalArgumentException("Este contenedor tiene otro contenedor encima");
				
			}else {
				
				plazas[plaza][altura] = null;
				
			}
			
			
		}
		
	}
	
	
	/**
	 * Comprueba si un muelle esta completo.
	 * @return false si tiene hueco, true si no
	 */
	public boolean muelleLLeno() {
		
		boolean completo = false;
		int plazasLLenas = getPlazasLLenas();
		
		if (plazasLLenas == getPlazas()) {
			
			completo = true;
			
		}
		
		return completo;
	}
	
	
	/**
	 * Comprueba si habiendo 2 plazas vacias en el muelle, estan una contigua a la otra para poder introducir 
	 * un contenedor FlatRack.
	 * @param lugar, entero positivo plaza donde se le quiere colocar
	 * @param contenedor, contenedor que se quiere colocar
	 * @param plazas, matriz de plazas del muelle
	 * @return false si no se le ha podido colocar, true si se ha colocado
	 */
	public boolean flatRackValido(int lugar, Contenedor contenedor, Contenedor[][] plazas) {
		
		boolean valido = true;
		
		if (lugar == 0) {
			
			if(plazas[lugar + 1][0] == null) {
				
				plazas[lugar][0] = contenedor;
				plazas[lugar + 1][0] = contenedor;
				
				
			}else {
				
				valido = false;
				
			}
			
			
		}else if(lugar == this.getPlazas() - 1) {
			
			if(plazas[lugar - 1][0] == null) {
			
				plazas[lugar][0] = contenedor;
				plazas[lugar - 1][0] = contenedor;
				
				
			}else {
				
				
				valido = false;
				
				
			}
		
		}else {
			
			if(plazas[lugar - 1][0] == null || plazas[lugar + 1][0] == null) {
				
				if (plazas[lugar - 1][0] == null) {
					
					plazas[lugar][0] = contenedor;
					plazas[lugar - 1][0] = contenedor;
					
				}else {
					
					plazas[lugar][0] = contenedor;
					plazas[lugar + 1][0] = contenedor;
					
					
				}
				
			
			
			}else {
				
				valido = false;
				
			}
			
		}
		
		return valido;
		
		
	}
		
		
	
	
	/**
	 * Comprueba si dos muelles son el mismo, considerando dos muelles iguales aquellos que esten en el mismo puerto
	 * y tengan el mismo identificador, si no tienen puerto comprueba sus coordenadas y su identificador
	 * @param otro, muelle a comparar
	 * @return true, si son iguales, false si no
	 */
	@Override
	public boolean equals(Object otro) {
		
		boolean iguales = false;
		
		if (((Muelle)otro) == null) {
			
			throw
				new IllegalArgumentException("El muelle no puede ser null");
		
		} 
		
		if(((Muelle)otro).getPuerto() == null || getPuerto() == null) {
			
			if(getIdentificador() == ((Muelle)otro).getIdentificador() && getCoordenadaDecimal().equals(((Muelle)otro).getCoordenadaDecimal()) == true) {
				
				
				iguales = true;
			}
			
		}else {
			
			if (getIdentificador() == ((Muelle)otro).getIdentificador() && getPuerto().equals(((Muelle)otro).getPuerto()) == true) {
				
				iguales = true;
			
			}
			
		}
		
		return iguales;
		
		
	}

}
