/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.daos;

import com.uva.fant11sy.db.ConnectionPool;
import com.uva.fant11sy.modelo.FilaClasificacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * Conecta con la base de datos para obtener la clasificacion de la liga
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class ClasificacionDAO {
    
    private ClasificacionDAO(){
        
        
        
    }
    
    
    private static final String SELECT_CLASIFICACION_GLOBAL = "SELECT u.usuario, SUM(IFNULL(pj.puntuacionJornada, 0)) AS puntos_totales " +
                                                             "FROM Usuario u " +
                                                             "LEFT JOIN PuntuacionJornada pj ON u.id_usuario = pj.id_usuario " +
                                                             "GROUP BY u.id_usuario, u.usuario " +
                                                             "ORDER BY puntos_totales DESC";
    
    private static final String SELECT_CLASIFICACION_JORNADA = "SELECT u.usuario, pj.puntuacionJornada " +
                                                              "FROM Usuario u " +
                                                              "JOIN PuntuacionJornada pj ON u.id_usuario = pj.id_usuario " +
                                                              "WHERE pj.numero_jornada = ? " +
                                                              "ORDER BY pj.puntuacionJornada DESC";
    
    private static final String INSERT_PUNTUACION = "INSERT INTO PuntuacionJornada (puntuacionJornada, id_usuario, numero_jornada) VALUES (?, ?, ?)";
    
    private static final String SELECT_RANKING = "SELECT u.id_usuario, SUM(IFNULL(pj.puntuacionJornada, 0)) AS puntos_totales " +
                                                 "FROM Usuario u " +
                                                 "LEFT JOIN PuntuacionJornada pj ON u.id_usuario = pj.id_usuario " +
                                                 "GROUP BY u.id_usuario " +
                                                 "ORDER BY puntos_totales DESC";
    
    private static final String UPDATE_RANKING = "UPDATE Usuario SET puesto = ? WHERE id_usuario = ?";
    
    
    /**
     * Obtiene la clasificacion global de la liga
     * @return lista con la clasificacion global
     */
    public static List<FilaClasificacion> obtenerClasificacionGlobal(){
        
        List<FilaClasificacion> clasificacion = new ArrayList<>();
        
        
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_CLASIFICACION_GLOBAL);
             ResultSet rs = ps.executeQuery()) {
            
            int pos = 1;
            
            while (rs.next()) {
                
                clasificacion.add(new FilaClasificacion(rs.getString("usuario"), rs.getInt("puntos_totales"), pos++));
            
            }
        
        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        
        }
        
        return clasificacion;
    }
    
    
    public static List<FilaClasificacion> obtenerClasificacionJornada(int numeroJornada){
        
        List<FilaClasificacion> clasificacion = new ArrayList<>();
        
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_CLASIFICACION_JORNADA)) {
            
            ps.setInt(1, numeroJornada);
            
            try (ResultSet rs = ps.executeQuery()) {
                
                int pos = 1;
                
                while (rs.next()) {
                    
                    clasificacion.add(new FilaClasificacion(rs.getString("usuario"), rs.getInt("puntuacionJornada"), pos++));
                }
            }
        
        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
        
        return clasificacion;
    }
    
    /**
     * Inserta la puntuacion de la jornada de un usuario
     */
    public static void guardarPuntuacionJornada(int idUsuario, int puntos, int jornada) {
        
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_PUNTUACION)) {

            ps.setInt(1, puntos);
            ps.setInt(2, idUsuario);
            ps.setInt(3, jornada);
            ps.executeUpdate();

        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }

    }
    
    
    /**
    * Recalcula las posiciones de todos los usuarios de la base de datos basándose 
    * en el sumatorio real de todas sus jornadas y actualiza su columna 'puesto'.
    */
   public static void actualizarPuestosGlobales() {
       
      
       try (Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement psSelect = conn.prepareStatement(SELECT_RANKING);
            
               ResultSet rs = psSelect.executeQuery()) {

           
           try (PreparedStatement psUpdate = conn.prepareStatement(UPDATE_RANKING)) {
               
               int posicionRanking = 1;
               
               while (rs.next()) {
                   
                   int idUsuario = rs.getInt("id_usuario");
                   psUpdate.setInt(1, posicionRanking);
                   psUpdate.setInt(2, idUsuario);
                   psUpdate.executeUpdate();
                   posicionRanking++;
               }
           }
       
       } catch (SQLException e) {
           
           throw new RuntimeException("Error crítico en la base de datos", e);
       }

   }

}

