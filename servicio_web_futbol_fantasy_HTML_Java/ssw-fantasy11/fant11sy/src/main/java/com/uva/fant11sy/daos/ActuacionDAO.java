/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.daos;

import com.uva.fant11sy.db.ConnectionPool;
import com.uva.fant11sy.modelo.Actuacion;
import com.uva.fant11sy.util.CalculoPuntos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Guarda las actuaciones de los jugadores en la BD
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class ActuacionDAO {
    
    private ActuacionDAO(){
        
        
    }
    
    
    private static final String INSERT_ACTUACIONES = "INSERT INTO ActuacionPartido (goles, minutos, asistencias, tarjetasAmarillas, tarjetasRojas, golesEnPropia, golesEnContra, puntos, id_partido, id_jugador) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SELECT_POSICION = "SELECT posicion FROM Jugador WHERE id = ?";
    
    private static final String SELECT_PUNTOS_JUGADORES = "SELECT ap.id_jugador, ap.puntos " +
                                                          "FROM ActuacionPartido ap " +
                                                          "JOIN Partido p ON ap.id_partido = p.id_partido " +
                                                          "WHERE p.numero_jornada = ?";
    
    /**
     * Guarda las actuaciones con el puntaje que ha hecho cada jugador
     * @param actuaciones, lista con las actuaciones
     */
    public static void procesarYGuardarActuaciones(List<Actuacion> actuaciones) {
        
        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
            
            for (Actuacion act : actuaciones) {
                String posicion = "";
                
                
                try (PreparedStatement psPos = conn.prepareStatement(SELECT_POSICION)) {
                    
                    psPos.setInt(1, act.getIdJugador());
                    
                    try (ResultSet rs = psPos.executeQuery()) {
                        
                        if (rs.next()) {
                            
                            posicion = rs.getString("posicion");
                        }
                    }
                }
                
                
                if (posicion.isEmpty()) continue;

                
                int puntosFinales = CalculoPuntos.calcularPuntosActuacion(act, posicion);
                act.setPuntos(puntosFinales);

               
                try (PreparedStatement psIns = conn.prepareStatement(INSERT_ACTUACIONES)) {
                    psIns.setInt(1, act.getGoles());
                    psIns.setInt(2, act.getMinutos());
                    psIns.setInt(3, act.getAsistencias());
                    psIns.setInt(4, act.getAmarillas());
                    psIns.setInt(5, act.getRojas());
                    psIns.setInt(6, act.getGolesEnPropia());
                    psIns.setInt(7, act.getGolesEnContra());
                    psIns.setInt(8, act.getPuntos());
                    psIns.setInt(9, act.getIdPartido());
                    psIns.setInt(10, act.getIdJugador());
                    psIns.executeUpdate();
                }
            }
        } catch (Exception e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
    }
    
    /**
     * Obtiene las puntuaciones de la base de datos
     * @param jornada, jornada en la que se obtienen
     * @return jugador y puntaje asociado
     */
    public static Map<Integer, Integer> obtenerPuntosJugadoresPorJornada(int jornada) {
    
        Map<Integer, Integer> puntosMap = new HashMap<>();
           
        try (Connection conn = ConnectionPool.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_PUNTOS_JUGADORES)) {
            
            ps.setInt(1, jornada);
            
            try (ResultSet rs = ps.executeQuery()) {
                
                while (rs.next()) {
                    
                    puntosMap.put(rs.getInt("id_jugador"), rs.getInt("puntos"));
                }
            }
        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
        return puntosMap;
    }

}
