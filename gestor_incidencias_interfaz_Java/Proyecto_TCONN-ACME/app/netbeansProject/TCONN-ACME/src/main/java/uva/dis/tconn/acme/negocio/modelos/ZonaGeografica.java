/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.modelos;

/**
 * Modela la zona geografica
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class ZonaGeografica {
    
    private String id;
    
    /**
     * Constrcutor
     * @param id de tipo String
     * @throws IllegalArgumentException si id es nula o vacia
     */
    public ZonaGeografica (String id){
        
        if(id == null || id.isEmpty()){
            
            throw new IllegalArgumentException("La id no puede ser nula o vacia");
            
        }
        
        this.id = id;
        
    }
    
    /**
     * Obtiene el identificador de la zona 
     * @return de tipo String
     */
    public String getId(){
        
        return this.id;
        
    }
    
}
