package com.uva.fant11sy.daos;

import com.uva.fant11sy.db.ConnectionPool;

import com.uva.fant11sy.modelo.Club;
import com.uva.fant11sy.modelo.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * Conecta con la base de datos y obtiene los datos de un club
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class ClubDAO {
    
    private ClubDAO(){
        
        
    }
    
    
    private static final String SELECT_JUGADORES_CLUB = "SELECT j.id, j.nombre, j.posicion, j.nacionalidad, j.foto " +
                                                       "FROM Jugador j " +
                                                       "JOIN Club c ON j.id_club = c.id_club " +
                                                       "WHERE c.nombre = ?";
    
    private static final String SELECT_CLUB = "SELECT c.id_club, c.nombre, c.foto " +
                                             "FROM Club c " +
                                             "WHERE nombre = ?";
                                             
    private static final String INSERT_CLUB = "INSERT IGNORE INTO Club (id_club, nombre, foto) VALUES (?, ?, ?)";
    
    /**
     * Obtiene una lista con los jugadores de un club
     * @param nombreClub, nombre del club
     * @return lista con los jugadores
     */
    public static List<Jugador> obtenerJugadoresCLub(String nombreClub){
        
        List<Jugador> jugadores = new ArrayList<>();
        
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_JUGADORES_CLUB)) {
            
            ps.setString(1, nombreClub);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Jugador j = new Jugador();
                    j.setId(rs.getInt("id"));
                    j.setNombre(rs.getString("nombre"));
                    j.setPosicion(rs.getString("posicion"));
                    j.setNacionalidad(rs.getString("nacionalidad"));
                    j.setFoto(rs.getString("foto"));
                    jugadores.add(j);
                }
            }
        
        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
        return jugadores;
    }
    
    /**
     * Devuelve el club de la base de datos dado el nombre
     * @param nombreClub, nombre del club
     * @return el club
     */
    public static Club obtenerClub(String nombreClub){
        
        Club club = new Club();
        
        try (Connection conn = ConnectionPool.getInstance().getConnection();
         PreparedStatement ps = conn.prepareStatement(SELECT_CLUB)) {
        
            ps.setString(1, nombreClub);
            
            try (ResultSet rs = ps.executeQuery()) {
                
                if(rs.next()){
                    club.setId(rs.getInt("id_club"));
                    club.setNombre(rs.getString("nombre"));
                    club.setFoto(rs.getString("foto"));
                }
            }
        
        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
        
        return club;
    }
    
    /**
     * Guarda un club en la base de datos si no existe previamente
     * @param idClub identificador del club en la API
     * @param nombre nombre del club
     * @param foto url de la foto del escudo
     */
    public static void guardarClub(int idClub, String nombre, String foto) {
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_CLUB)) {
            ps.setInt(1, idClub);
            ps.setString(2, nombre);
            ps.setString(3, foto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
    }
}