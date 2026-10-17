package com.uva.fant11sy.util;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    
    private static Properties properties = new Properties();
    
    static {
        cargarPropiedades();
    }
    
    private static void cargarPropiedades() {
        String[] rutas = {
            "config.properties",
            "/config.properties",
            "META-INF/config.properties",
            "/META-INF/config.properties"
        };
        
        boolean cargado = false;
        
        for (String ruta : rutas) {
            try {
                InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(ruta);
                
                if (input == null) {
                    input = Config.class.getResourceAsStream(ruta);
                }
                
                if (input != null) {
                    properties.load(input);
                    System.out.println("FANT11SY: Archivo config.properties encontrado y cargado desde: " + ruta);
                    cargado = true;
                    input.close();
                    break;
                }
            } catch (Exception ex) {
                System.err.println("FANT11SY: Fallo al intentar leer la ruta: " + ruta);
            }
        }
        
        if (!cargado) {
            System.err.println("ERROR CRÍTICO FANT11SY: No se ha encontrado el archivo config.properties en ninguna ruta del Classpath.");
        }
    }
    
    public static String getApiToken() {
        String token = System.getenv("SPORTMONKS_TOKEN");
        if (token == null || token.isEmpty()) {
            token = properties.getProperty("api.token");
        }
        
        if (token == null) {
            return ""; 
        }
        return token.trim();
    }
}