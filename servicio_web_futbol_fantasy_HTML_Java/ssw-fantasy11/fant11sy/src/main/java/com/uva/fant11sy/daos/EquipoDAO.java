/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.daos;

import com.uva.fant11sy.db.ConnectionPool;
import com.uva.fant11sy.modelo.Equipo;
import com.uva.fant11sy.modelo.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Conecta con la base de datos para obtener los equipos
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class EquipoDAO {
 
    private EquipoDAO(){
        
        
    }
    
    
    private static final String SELECT_EQUIPO_USUARIO = "SELECT id_equipo, nombre, formacion FROM Equipo WHERE id_usuario = ?";
    
    private static final String SELECT_JUGADORES_EQUIPO = "SELECT j.id, j.nombre, j.posicion, j.nacionalidad, j.foto, " +
                                                         "ej.slot, c.nombre AS nombre_club " +
                                                         "FROM Jugador j " +
                                                         "JOIN EquipoJugador ej ON j.id = ej.id_jugador " +
                                                         "JOIN Club c ON j.id_club = c.id_club " +
                                                         "WHERE ej.id_equipo = ?";
    
    private static final String UPDATE_FORMACION_EQUIPO = "UPDATE Equipo SET formacion = ? WHERE id_equipo = ?";
    
    private static final String DELETE_JUGADORES_ASIGNADOS = "DELETE FROM EquipoJugador WHERE id_equipo = ?";
    
    /**
     * Obtiene un equipo con sus jugadores
     * @param idUsuario, id del usuario duenio del equipo
     * @return equipo con lista de jugadores
     */
    public static Equipo obtenerEquipoUsuario(int idUsuario){
        
        Equipo equipo = null;
        
        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
            
            try (PreparedStatement ps = conn.prepareStatement(SELECT_EQUIPO_USUARIO)) {
                
                ps.setInt(1, idUsuario);
                
                try (ResultSet rs = ps.executeQuery()) {
                    
                    if (rs.next()) {
                        equipo = new Equipo();
                        equipo.setIdEquipo(rs.getInt("id_equipo"));
                        equipo.setNombre(rs.getString("nombre"));
                        equipo.setFormacion(rs.getString("formacion"));
                        equipo.setIdUsuario(idUsuario);
                    }
                }
            }
    
            if (equipo != null) {
                    
                try (PreparedStatement ps = conn.prepareStatement(SELECT_JUGADORES_EQUIPO)) {
                        
                    ps.setInt(1, equipo.getIdEquipo());
                        
                    try (ResultSet rs = ps.executeQuery()) {
                            
                        while (rs.next()) {
                            
                            Jugador j = new Jugador();
                            
                            j.setId(rs.getInt("id"));
                            j.setNombre(rs.getString("nombre"));
                            j.setPosicion(rs.getString("posicion"));
                            j.setNacionalidad(rs.getString("nacionalidad"));
                            j.setSlot(rs.getInt("slot"));
                            j.setFoto(rs.getString("foto"));
                            j.setNombreClub(rs.getString("nombre_club"));
                            
                            equipo.getPlantilla().add(j);
                            
                        } 
                    }  
                }   
            }
            
        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
        
        return equipo;
    }
    
    /**
     * Actualiza la formacion en la DB y borra los jugadores asignados al equipo para que puedan volverse a fichar
     * @param idEquipo, id delequipo a actualizar
     * @param nuevaFormacion, nueva formacion a guardar
     */
    public static void actualizarFormacion(int idEquipo, String nuevaFormacion){
        
        
        Connection conn = null;
        
        try {
            
            conn = ConnectionPool.getInstance().getConnection();
            
            if (conn == null) {
                
                return;
            }

            conn.setAutoCommit(false);

            try (PreparedStatement psUpdate = conn.prepareStatement(UPDATE_FORMACION_EQUIPO)) {
                
                psUpdate.setString(1, nuevaFormacion);
                psUpdate.setInt(2, idEquipo);
                psUpdate.executeUpdate();
            }

            
            try (PreparedStatement psDelete = conn.prepareStatement(DELETE_JUGADORES_ASIGNADOS)) {
                
                psDelete.setInt(1, idEquipo);
                psDelete.executeUpdate();
            }

            conn.commit();
            

        } catch (SQLException e) {
            
           
            if (conn != null) {
                
                try { 
                    
                    conn.rollback(); 
                
                } catch (SQLException ex) { 
                    
                    throw new RuntimeException("Error crítico en la base de datos", e);
                }
            }
            
            throw new RuntimeException("Error crítico en la base de datos", e);
                 
        } finally {
            
            if (conn != null) {
                
                try {
                    
                    conn.setAutoCommit(true);
                    conn.close();
                
                } catch (SQLException ex) {
                    
                   throw new RuntimeException("Error crítico en la base de datos", ex);
                }
            }
        }
    }
    
    public static final String SELECT_EQUIPOS_COMPLETOS = 
        "SELECT e.id_equipo, e.nombre, e.formacion, e.id_usuario " +
        "FROM Equipo e " +
        "WHERE (SELECT COUNT(*) FROM EquipoJugador ej WHERE ej.id_equipo = e.id_equipo) = 11";

    /**
     * Obtiene todos los equipos que tienen exactamente 11 jugadores, 
     * rellenando su plantilla.
     * @return Lista de equipos completos
     */
    public static List<Equipo> obtenerEquiposCompletos() {
        List<Equipo> equipos = new ArrayList<>();
        
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_EQUIPOS_COMPLETOS);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setIdEquipo(rs.getInt("id_equipo"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setFormacion(rs.getString("formacion"));
                equipo.setIdUsuario(rs.getInt("id_usuario"));
                
                equipos.add(equipo);
            }
            
            // Llenar las plantillas de estos equipos
            for (Equipo eq : equipos) {
                try (PreparedStatement psJugadores = conn.prepareStatement(SELECT_JUGADORES_EQUIPO)) {
                    psJugadores.setInt(1, eq.getIdEquipo());
                    try (ResultSet rsJugadores = psJugadores.executeQuery()) {
                        while (rsJugadores.next()) {
                            Jugador j = new Jugador();
                            j.setId(rsJugadores.getInt("id"));
                            j.setNombre(rsJugadores.getString("nombre"));
                            j.setPosicion(rsJugadores.getString("posicion"));
                            j.setNacionalidad(rsJugadores.getString("nacionalidad"));
                            j.setSlot(rsJugadores.getInt("slot"));
                            j.setFoto(rsJugadores.getString("foto"));
                            j.setNombreClub(rsJugadores.getString("nombre_club"));
                            
                            eq.getPlantilla().add(j);
                        }
                    }
                }
            }
            
        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
        
        return equipos;
    }    
}
