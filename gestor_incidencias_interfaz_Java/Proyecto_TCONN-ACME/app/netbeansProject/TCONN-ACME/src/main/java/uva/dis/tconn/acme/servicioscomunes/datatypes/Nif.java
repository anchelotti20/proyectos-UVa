/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.datatypes;

/**
 * Clase que modela el datatype de nif de una persona
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */

public class Nif {
    
    String textoNif;
    
    /**
     * Constructor para objeto Nif, se parte de un texto en tipo String para luego comprobar si es correcto
     * @param textoNif de tipo String
     * @throws IllegalArgumentException si el nif es nulo
     * @throws IllegalArgumentException si el nif no tiene la cantidad correcta de carcateres
     * @throws IllegalArgumentException si el ultimo carcater no es una letra maysucula
     */
    public Nif(String textoNif){
        
        if(textoNif==null){
            throw new IllegalArgumentException("El nif no puede ser nulo");
        }
        
        if(textoNif.length()!=8 && textoNif.length()!=9){
            throw new IllegalArgumentException("El nif no contiene la cantidad de caracteres correcta.");
        }
        
        int longitud=textoNif.length();
    
        char letra=textoNif.charAt(longitud-1);

        if(letra<65||letra>90){
            throw new IllegalArgumentException("El ultimo caracter no es una letra.");
        }
        
        this.textoNif=textoNif;
        
    }
    
    /**
     * Obtiene el nif en forma de String
     * @return textoNif de tipo String
     */
    public String getTextoNif(){
        return this.textoNif;
    }
    
    
    
    
    
}
