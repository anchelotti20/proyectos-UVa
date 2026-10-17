package com.uva.fant11sy.modelo;


import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton para que cada usuario tenga una unica sesion
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class GestionSesiones {
    
    
    private static GestionSesiones instancia;
    
    private Map<String, HttpSession> sesionesActivas;
    
    /**
     * Constructor de la instancia de sesiones
     */
    private GestionSesiones(){
        
        sesionesActivas = new ConcurrentHashMap<>();
        
    }
    
    /**
     * Devuelve la unica instancia de la clase, si no la habia la crea
     * @return instancia de las sesiones activas
     */
    public static synchronized GestionSesiones getInstance(){
        
        if(instancia == null){
            
            instancia = new GestionSesiones();
        }
        
        return instancia;
        
    }
    
    
    /**
     * Registra la nueva sesion del usuariom borrando la que existia si la habia
     * @param usuario, String con el nombre de usuario
     * @param nuevaSesion, sesion a guardar
     */
    public void registrarUsuario(String usuario, HttpSession nuevaSesion){
        
        HttpSession sesionAntigua = sesionesActivas.get(usuario);
        
        if(sesionAntigua != null){
            
            try{
                
                sesionAntigua.invalidate();
            
            }catch(IllegalStateException e){
                
                
            }
            
        }
        
        sesionesActivas.put(usuario, nuevaSesion);
        
    }
    
    /**
     * Elimina la sesion del usuario indicado
     * @param usuario, String con el usuario a eliminar su sesion
     */
    public void eliminarUsuario(String usuario){
        
        sesionesActivas.remove(usuario);
    }
    
    
}
