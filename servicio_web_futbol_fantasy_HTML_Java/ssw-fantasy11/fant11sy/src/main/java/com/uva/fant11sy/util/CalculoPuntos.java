package com.uva.fant11sy.util;

import com.uva.fant11sy.modelo.Actuacion;
import com.uva.fant11sy.modelo.Jugador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calcula los puntos de los jugadores y usuarios
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class CalculoPuntos {
    
    
    /**
     * Calcula los puntos de la actuación de un jugador basándose en su posición.
     * @param act, actuacion del partido
     * @param posicion, posicion del jugador
     * @return puntos, puntos que ha hecho el jugador
     */
    public static int calcularPuntosActuacion(Actuacion act, String posicion) {
        
        int puntos = 0;
        
        posicion = posicion.toUpperCase();
        
        int minutos = act.getMinutos();
       
        if (minutos >= 90) {
            
            puntos += 3;
        
        } else if (minutos >= 60) {
            
            puntos += 2;
        
        } else if (minutos >= 30) {
            
            puntos += 1;
        }
        
        
        if (posicion.contains("POR") || posicion.contains("DEF")) {
            
            puntos += act.getGoles() * 6;
        } else if (posicion.contains("MED")){
            
            puntos += act.getGoles() * 5;
        
        }else {
            
            puntos += act.getGoles() * 4;
        }
        
        
        puntos += act.getAsistencias() * 3;
        puntos -= act.getAmarillas() * 1;
        puntos -= act.getRojas() * 3;
        
       
        puntos -= act.getGolesEnPropia() * 2; 
        
        
        if (posicion.contains("POR") || posicion.contains("DEF")) {
            
            puntos -= (act.getGolesEnContra() / 2);
        }
        
        if(act.getGolesEnContra() == 0 && (posicion.contains("POR") || posicion.contains("DEF"))){
            
            puntos += 3;
            
        }
        
        return puntos;
    }
    
    
    /**
    * Calcula los puntos totales del once ideal leyendo las actuaciones ya registradas en la BD
    * y sumando los bonus por parejas de club (+2) y país (+1).
    * @param plantilla, jugadores del usuario
    * @param puntosJugadoresDB, puntaje de cada jugador
    * @return puntos del usuario esa jornada
    */
   public static int obtenerPuntosDesdeDB(List<Jugador> plantilla, Map<Integer, Integer> puntosJugadoresDB) {
       
       if (plantilla == null || plantilla.size() != 11) {
           return 0;
       }

       int puntajeBase = 0;
       int bonusPais = 0;
       int bonusClub = 0;

       Map<String, Integer> parejasNacionalidad = new HashMap<>();
       Map<String, Integer> parejasClub = new HashMap<>();

       for (Jugador j : plantilla) {
           
           int puntosJugador = puntosJugadoresDB.getOrDefault(j.getId(), 0);
           puntajeBase += puntosJugador;

           
           String nac = j.getNacionalidad();
           String club = j.getNombreClub();

           if (nac != null && !nac.isEmpty()) {
               parejasNacionalidad.put(nac, parejasNacionalidad.getOrDefault(nac, 0) + 1);
           }
           if (club != null && !club.isEmpty()) {
               parejasClub.put(club, parejasClub.getOrDefault(club, 0) + 1);
           }
       }

      
       for (Map.Entry<String, Integer> entrada : parejasNacionalidad.entrySet()) {
           int parejas = entrada.getValue() / 2;
           bonusPais += parejas; 
       }

       for (Map.Entry<String, Integer> entrada : parejasClub.entrySet()) {
           int parejas = entrada.getValue() / 2;
           bonusClub += parejas * 2; 
       }

       return puntajeBase + bonusPais + bonusClub;
   }
}