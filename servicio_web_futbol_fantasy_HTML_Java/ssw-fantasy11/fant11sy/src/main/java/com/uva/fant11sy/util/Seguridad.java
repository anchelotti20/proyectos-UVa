/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utiliza el algoritmo SHA-256 para encriptar las contrasenas en la base de datos
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
 public class Seguridad {
    
    /**
     * Aplica el algoritmo SHA-256 a una cadena y devuelve el hash en formato Hexadecimal
     * @param contrasena, cadena a encriptar
     * @return cadena encriptada
     */ 
    public static String encriptarContrasena(String contrasena) {
         
        
        try{
            
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contrasena.getBytes());
            
            
            StringBuilder hexadecimalString = new StringBuilder();
            
            for(byte bite: hash){
                
                String hexadecimal = Integer.toHexString(0xff & bite);
                
                if(hexadecimal.length() == 1){
                    
                    hexadecimalString.append('0');
                    
                }
                
                hexadecimalString.append(hexadecimal);
            }
            
            return hexadecimalString.toString();
        
        }catch(NoSuchAlgorithmException e){
            
            throw new RuntimeException("Error de configuración de seguridad: SHA-256 no disponible", e); 
            
        }
         
    }
     
}
