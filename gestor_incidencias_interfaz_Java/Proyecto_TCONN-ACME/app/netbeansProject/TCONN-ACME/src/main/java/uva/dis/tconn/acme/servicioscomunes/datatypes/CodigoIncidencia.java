/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.datatypes;

/**
 *
 * DataType que modela el codigo de una incidencia
 * Esta formado por un string que controla una serie de caracteres que deben ser obligatoriamente disgitos
 * luego por dos enteros, uno respresnta el anio y otro un numero
 * @author Marcos Gomez 
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class CodigoIncidencia {
    
    private String prefijo;
    private int anio;
    private int numero;
    /**
     * Constructor del codigo de incidencia
     * @param prefijo string para controlar que este formado por EXACTAMENTE 3 digitos
     * @param anio de tipo int
     * @param numero de tipo int
     * @throws IllegalArgumentException si prefijo no tiene longitud 3
     * @throws IllegalArgumentException si no esta formado por digitos
     * @throws IllegalArgumentException si anio es menor que cero, no se contempla anios antes de cristo
     * 
     */
    public CodigoIncidencia(String prefijo,int anio,int numero){
        
        if(prefijo.length()!=3){
            throw new IllegalArgumentException("El prefijo debe contener 3 digitos");
        }else{
            for(int i=0;i<3;i++){
                if(prefijo.charAt(i)<48 || prefijo.charAt(i)>57){
                    throw new IllegalArgumentException("El prefijo deben ser digitos.");
                }
            }
        }
        
        this.prefijo=prefijo;
        
        if(anio<0){
            throw new IllegalArgumentException("Anio no puede ser negativo.");
        }
        this.anio=anio;
        this.numero=numero;
    }
    /**
     * Getter para obtener el prefijo
     * @return String con el prefijo, se garantiza que son 3 digitos
     */
    public String getPrefijo(){
        return this.prefijo;
    }
    /**
     * Devuleve el anio 
     * @return anio de tipo int
     */
    public int getAnio(){
        return this.anio;
    }
    /**
     * Se encarga de obtener el numero del codigo de la incidencia
     * @return 
     */
    public int getNumero(){
        return this.numero;
    }
    
}
