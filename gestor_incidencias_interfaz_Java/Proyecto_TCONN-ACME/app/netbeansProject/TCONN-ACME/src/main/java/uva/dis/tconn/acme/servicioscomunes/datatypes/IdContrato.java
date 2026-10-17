/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.datatypes;



/**
 *
 * Clase que modela el datatype de identificador par aun contrato
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class IdContrato {
    
    private String prefijo;
    private String letras;
    private int numero;
    /**
     * Construye un identificador de contrato
     * @param prefijo de tipo String
     * @param letras de tipo String
     * @param numero  de tipo int
     * @throws IllegalArgumentException si prefijo es nulo
     * @throws IllegalArgumentException si letras es nulo
     * @throws IllegalArgumentException si prefijo es vacio
     * @throws IllegalArgumentException si letras es vacio
     * @throws IllegalArgumentException si prefijo no es de longitud dos
     * @throws IllegalArgumentException si letras no es de longitud 3
     */
    public IdContrato(String prefijo,String letras,int numero){
        
        esNuloOVacio(prefijo);
        esNuloOVacio(letras);
        
        if(prefijo.length()!=2){
            throw new IllegalArgumentException("El prefijo no contiene dos caracteres.");
        }
        
        if(letras.length()!=3){
            throw new IllegalArgumentException("El conjunto de letras no contiene tres caracteres.");
        }
        
        this.prefijo=prefijo;
        this.letras=letras;
        this.numero=numero;
       
        
    }
    
    /**
     * Obtiene el prefijo
     * @return prefijo
     */
    public String getPrefijo(){
        return this.prefijo;
    }
    /**
     * Devuelve el conjunto de letras
     * @return letras de tipo String
     */
    public String getLetras(){
        return this.letras;
    }
    /**
     * Consigue devolver el numero asociado
     * @return numero
     */
    public int getNumero(){
        return this.numero;
    }
    /**
     * Metodo que lanza excecpcion si el texto es nulo o vacio
     * @param texto a comprobbar
     * @throws IllegalArgumentException si texto es nulo
     * @throws IllegalArgumentException si el texto es vacio
     */
    private void esNuloOVacio(String texto){
        if(texto==null||texto.isEmpty()){
            throw new IllegalArgumentException("Campos no pueden ser nulos o vacios.");
        }
    }

    public String toString(){
        return this.prefijo+this.letras+this.numero;
    }
    
    
}
