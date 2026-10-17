package es.uva.poo.contenedor;

import java.util.ArrayList;

import es.uva.poo.trayectos.Trayecto;

/**
 * Tipo de dato que modela el comportamiento de un contenedor,
 * esta caracterizado por un codigo de dueño (3 letras) , un numero de
 * serie de digitos(6), un digito de control obtenido tras un algorimo 
 * que sigue el ESTANDAR ISO 6346
 * las unidades de peso en Kilogramos , volumen en metros 
 * cubicos
 * estado que indica si el contenedor esta o no en transito(sino esta en recogida)
 * Y otro sobre si el contenedor dispone o no de techo
 * @author marcgom
 * @author anganto
 */

public abstract class Contenedor {
	
	
	private double pesoKilogramos;
	
	private double metrosCubicos;
	
	private boolean transito;
	
	private double maximaCargaUtil;
	
	private String identificador;
	
	private ArrayList<Trayecto> trayectos;
	

	
	/**
	 * Inicializar un contenedor a partir de un "identificador completo", su peso en Kilogramos
	 * su volumen en metros cubicos, un boolean sobre si esta en transito(si no esta en transito 
	 * es que se encuentra en recogida),otro boolena para conocer si tiene o no techo, y una maxima
	 * cargaUtil
	 * @param identificador, string formato ISO 6346
	 * @param metrosCubicos, double volumen del contendor
	 * @param pesoKilogramos, double peso del contenedor
	 * @param transito, true en transito, false no
	 * @param maximaCargaUtil, doble maxima carga del contenedor
	 * @throws IllegalArgumentException identificador== null
	 * @throws IllegalArgumentException identificador incorrecto
	 * @throws IllegalArgumentException metrsoCubicos<=0
	 * @throws IllegalArgumentException pesoKilogramos<=0
	 * @throws IllegalArgumentException maximaCargaUtil<=0
	 */
	
	public Contenedor(String identificador,double pesoKilogramos,double metrosCubicos,boolean transito,double maximaCargaUtil) {
		if (identificador == null) {
			
			throw
				new IllegalArgumentException ("El identificador no puede ser null");
		
		}else if(verificacionIdentificador(identificador)==false) {
			
			throw
				new IllegalArgumentException("El identificador no es correcto");
		
		}else {
			
			this.identificador=identificador;
			
		}
		
		if(metrosCubicos <=0) {
				
			throw
				new IllegalArgumentException("El volumen no puede ser negativo ni 0");
			
		}else {
				
			this.metrosCubicos=metrosCubicos;
				
		}
		
		if(pesoKilogramos<=0) {
				
			throw
				new IllegalArgumentException("El peso no puede ser negativo ni 0");
				
		}else {
			
			this.pesoKilogramos=pesoKilogramos;
					
		}
				
		if(maximaCargaUtil<=0) {
						
			throw
				new IllegalArgumentException("La carga maxima util no puede ser negativa ni 0");
					
		}else {
			
			this.maximaCargaUtil=maximaCargaUtil;
			
		
		}
							
		this.transito=transito;
		this.trayectos = new ArrayList<>();		
				
	}
	
	

	/**
	 * Permite consultar si el identificador es correcto, para ello analiza los diferentes casos en
	 * los que pueda ser falso, es decir, no correcto.
	 * Por un lado primero mira la longitud, la cual es importante porque comprueba directamente
	 * los digitos de NumSerie, ya que si se aniadeo quita uno mas el identificador no tendra la 
	 * longitud deseada
	 * @throws IllegalArgumentException letraEquipamiento incorrecta
	 * @param identificador string formato ISO 6346
	 * @return valor de tipo booleano
	 */
	public boolean verificacionIdentificador(String identificador) {
		
		boolean verificador=true;
		
		if(identificador.length()==11) {
			
			String codigoDuenio=identificador.substring(0,3);

			for(int i=0;i<3;i++) {
				
				char c=codigoDuenio.charAt(i);
					
				if(c< 'A'|| c>'Z') {
						
					verificador=false;
				}
			}
	
			char letraEquipamiento=identificador.charAt(3);
			
			if( letraEquipamiento!='U'&&letraEquipamiento!='J'&&letraEquipamiento!='Z') {
				verificador=false;
				throw 
					new IllegalArgumentException("La letra de equipamiento no es correcta");
			}

			int numSerie=Integer.parseInt(identificador.substring(4,10));
			
			
		
			int digitoAcomprobar= identificador.charAt(10) - '0';
			 
			
			
			if(digitoAcomprobar!=getDigitoControl(codigoDuenio,letraEquipamiento,numSerie)) {
				
				verificador=false;
			}
		
		}else {
			
			verificador=false;
		}
		
		return verificador;

	}

	
	/**
	 * Comprueba si el dígito de control del identificador introducido es correcto
	 * siguiendo el cálculo del algoritmo ISO 6346
	 * @param codigoDuenio, string de las primeras 3 letras mayúsculas del identificador
	 * @param letraEquipamiento, caracter de la letra mayúscula en la 4 posicion del identificador
	 * @param numSerie, numero de 6 digitos en las posiciones 5 a 10 del identificador
	 * @return el entero entre 0 y 9 que surge de aplicar el algoritmo de digitoControl ISO 6346
	 */
	public int getDigitoControl(String codigoDuenio,char letraEquipamiento,int numSerie) {
		
		
		if(codigoDuenio==null) {
			throw
				new IllegalArgumentException("Los parametros no pueden ser null,el codigoDuenio es null");
		}
			
		if(letraEquipamiento!='U'&& letraEquipamiento!='J'&& letraEquipamiento!='Z') {
				
			throw	
				new IllegalArgumentException("La letra de equipameinto no es correcta");
		}
			
			
		if(numSerie<100000||numSerie>999999) {
			throw
				new IllegalArgumentException("El numero de serie debe ser de 6 digitos exacto,y no negativo");
		}
			
			
		String identificadorSinDigito = codigoDuenio + letraEquipamiento + numSerie;
			
		int digitoControl;
		int valorCaracter = 0;
		int sumatorio = 0;
		char letra;
		int[] valorLetras = {10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 23, 24, 25, 26, 27, 28, 29, 30, 31,32, 34, 35, 36, 37, 38};
			
		for (int i = 0; i < identificadorSinDigito.length(); i++) {
					
			if (identificadorSinDigito.charAt(i) >= 'A' && identificadorSinDigito.charAt(i) <= 'Z' ) {
						
				letra = identificadorSinDigito.charAt(i);
						
				valorCaracter = valorLetras[letra - 'A'];
						
					
			}else if(identificadorSinDigito.charAt(i) >= '0' && identificadorSinDigito.charAt(i) <= '9'){
						
				valorCaracter = identificadorSinDigito.charAt(i) - '0';
			}else {
				
				throw
					new IllegalArgumentException("Hay un caracter que no corresponde a una letra o numero");
				
			}
					
					
			sumatorio += valorCaracter * (int) Math.pow(2.0,i);
						
		}
				
		digitoControl = sumatorio % 11;
				
		if (digitoControl ==  10) {
					
			digitoControl = 0;
					
		}
				
		return digitoControl;
		
	}
		
		
	


	/**
	 * Permite modificar el valor de un identificador de un conteneddor, pero solo lo permite
	 * si el identificador a poner es correcto, es decir sigue el estandar ISO 6346, para ello en
	 * verificacionIdentificador se revisa, el codigoDuenio, la letra de equipamiento,los digitos del
	 * numero de serie y por ultimo el digito de control
	 * @param identificador, string formato ISO 6346
	 */
	
	public void setIdentificador(String identificador) {
		if(verificacionIdentificador(identificador)==false) {
			throw
				new IllegalArgumentException("El identificador NO es correcto");
		}else {
			this.identificador=identificador;
		}
		
	}
	
	
	/**
	 * Modifica el valor del peso del contenedor en kilogramos
	 * @throws IllegalArgumentException pesoKilogramos<=0
	 * @param pesoKilogramos, double peso nuevo en Kg
	 */
	
	
	public void setTaraKg(double pesoKilogramos) {
		
		if(pesoKilogramos<=0) {
			throw 
				new IllegalArgumentException("El peso no puede tener un valor negativo ni 0");
				
		}else {
			this.pesoKilogramos=pesoKilogramos;
		}
		
	
		
	}
	
	/**
	 * Modifica el valor del peso del contenedor en pasando un valor en libras
	 * @throws IllegalArgumentException pesoLibras<=0
	 * @param pesoLibras, double nuevo peso en libras
	 */
	public void setTaraLb(double pesoLibras) {
		
		if (pesoLibras <= 0) {
			
			throw 
				new IllegalArgumentException("El peso no puede tener un valor negativo ni 0");
		
		}else {
			
			double kilogramos = pesoLibras * 0.453592;
			
			setTaraKg(kilogramos);
			
		}
		
	}
	
	
	/**
	 * Modifica el valor de las unidades de volumen en m^3
	 * @throws IllegalArgumentException metrosCubicos<=0 
	 * @param metrosCubicos, double nuevo volumen en metros cubicos
	 */
	
	public void setMetrosCubicos(double metrosCubicos) {
		
		if(metrosCubicos<=0) {
			throw 
				new IllegalArgumentException("La unidad de volumen no puede ser negativa ni 0");
			
		}else {
			this.metrosCubicos=metrosCubicos;
		}
		
		
	}
	
	/**
	 * Modifica el valor de las unidades de volumen en pies cubicos
	 * @throws IllegalArgumentException piesCubicos<=0 
	 * @param piesCubicos, double nuevo volumen en pies cubicos
	 */
	
	public void setPiesCubicos(double piesCubicos) {
		
		if(piesCubicos<=0) {
			throw 
				new IllegalArgumentException("La unidad de volumen no puede ser negativa ni 0");
			
		}else {
			
			double metrosCubicos = piesCubicos * 0.0283168;
			
			setMetrosCubicos(metrosCubicos);
		}
		
	}
	
	/**
	 * Permite obtener un "identificador completo" 
	 * @return string Identificador
	 */
	
	public String getIdentificador() {
		
			return identificador;
		}
		
	/**
	 * Permite modificar si el contenedor se encuentra en transito o no(en recogida)
	 * @param transito, nuevo valor de transito
	 */
	public void setTransito(boolean transito) {
		this.transito=transito;
	}
	
	/**
	 * Permite modificar el valor de la carga maxima util
	 * @throws IllegalArgumentException maximaCargaUtil<=0
	 * @param maximaCargaUtil, double nueva carga util en Kg
	 */
	public void setMaximaCargaUtil(double maximaCargaUtil) {
		if(maximaCargaUtil<=0) {
			throw 
				new IllegalArgumentException("La carga util maxima no puede ser negativa ni 0");
		}
		this.maximaCargaUtil=maximaCargaUtil;
	}
	
	
	/**
	 * Modifica el arreglo o arrayy de los trayectos,verificando que no sena null
	 * @throws IllegalArgumentException trayectos== null
	 * @param trayectos, nuevo arraylist de trayectos
	 */
	
	public void setTrayectos(ArrayList<Trayecto> trayectos) {
		
		if(trayectos == null) {
			
			throw
				new IllegalArgumentException("Los trayectos no pueden ser null");
		}
		
		this.trayectos = trayectos;
		
	}
	
	
	/**
	 * Permite consultar el peso del contendor en Kilogramos
	 * @return double pesoKilogramos
	 */
	

	public double getTaraKg() {
		return pesoKilogramos;
	}
	
	
	

	
	/**
	 * Permite consultar el peso del contenedor en libras sabiendo su peso en Kg
	 * @return double pesoLibras
	 */
	
	public double getTaraLb() {
		return getTaraKg()*2.20462;
	}
	
	
	
	
	
	/**
	 * Consulta el valor de las unidades de volumnes en m^3
	 * @return metrosCubicos
	 */
	
	public double getMetrosCubicos() {
		return metrosCubicos;
	}
	
	
	/**
	 * Permite consultar el valor de las unidades de volumen piesCubicos a partir de las unidades m^3
	 * @return piesCubicos
	 */
	
	public double getPiesCubicos() {
		return getMetrosCubicos()*35.3147;
	}
	
	
	
	
	
	
	/**
	 * Consulta si el contenedor se encuentra en transito o no
	 * @return boolean transito
	 */
	public boolean getTransito() {
		return transito;
	}
	
	
	
	/**
	 * Consulta si el contenedor tiene o no techo
	 * Depende del tipo de contenedor
	 * @return true tiene, false no
	 */
	
	public abstract boolean getTecho();
	
	
	
	/**
	 * Conuslta el valor la carga util maxima de un contendor
	 * @return double de la cargaMaximaUtil
	 */
	public double getMaximaCargaUtil() {
		return maximaCargaUtil;
	}
	
	
	/**
	 * Permite obtener un array de trayetos 
	 * @return array de trayectos
	 */
	
	public ArrayList<Trayecto> getTrayectos() {
		
		return trayectos;
	}
	
	
	/**
	 * Consulta si el contenedor tiene motor o no
	 * Depende del tipo de contenedor
	 * @return true tiene, false no tiene
	 */
	public abstract boolean getMotor();
	
	/**
	 * Consulta si el contenedor tiene paredes o no
	 * Depende del tipo de contenedor
	 * @return ture tiene, false no tiene
	 */
	public abstract boolean getParedes();
	
	/**
	 * Obtiene el precio total de los trayectos que ha realizado el contenedor.
	 * @return double precio del trayecto del contenedor
	 */
	
	public double getPrecioContenedor() {
		
		double costeTotal = 0;
		
		ArrayList<Trayecto> trayectos = getTrayectos();
		
		for (int i = 0; i < trayectos.size(); i ++) {
			
			
			costeTotal += trayectos.get(i).getPrecio();
			
		}
		
		return costeTotal;
		
	}
	
	
	/**
	 * Permite asignar un trayecto a un contenedor. Cada tipo de contenedor puede tener distintos
	 * tipos de trayectos por lo que cada contenedor implementa su propio método.
	 * @param trayecto, trayecto que se quiere añadir al contenedor
	 */
	
	public abstract void asignarTrayecto(Trayecto trayecto);
	
	
}