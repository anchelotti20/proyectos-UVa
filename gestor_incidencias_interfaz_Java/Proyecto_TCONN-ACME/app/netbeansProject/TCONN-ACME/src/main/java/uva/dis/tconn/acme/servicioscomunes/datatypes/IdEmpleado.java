/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.servicioscomunes.datatypes;

/**
 * Modela el DataType de identificador de empleado
 * @author Marcos Gomez
 * @author Angel Antolin 
 * @author Elisa Martinez
 */

public class IdEmpleado {
    
    private String texto;
    private int numero;
    /**
     * Cosntruye el identificador de empleado
     * @param texto se utiliza para poder comprobar si se cumplen las normas idnicadas
     * @param numero numero de identificadcion
     * @throws IllegalArgumentException si el texto es nulo o vacio, si no tiene la longitud correcta y si no son letras
     */
    public IdEmpleado(String texto,int numero){
        if(texto==null||texto.isBlank()){
            throw new IllegalArgumentException("El conjunto de letras no puede ser nulo o vacio");
        }
        
        if(texto.length()!=3){
            throw new IllegalArgumentException("El conjunto de letras debe ser de longitud 3");
        }
        
        for(int i=0;i<3;i++){
            if(texto.charAt(i)<65 || texto.charAt(i)>90){
                throw new IllegalArgumentException("Alguno de los caracteres no son letras.");
            }
        }
        
        this.texto=texto;
        this.numero=numero;
        
    }
    
    /**
     * Obtiene el conjunto de letras que conforman el identificador
     * @return conjunto de letras(MAYUSCULAS)
     */
    public String getLetras(){
        return this.texto;
    }
    /**
     * Permite devolver el numero 
     * @return numero
     */
    public int getNumero(){
        return this.numero;
    }
    
}
