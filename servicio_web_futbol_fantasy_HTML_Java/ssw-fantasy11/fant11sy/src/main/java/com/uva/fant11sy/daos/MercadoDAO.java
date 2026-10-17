/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.daos;

import com.uva.fant11sy.db.ConnectionPool;
import com.uva.fant11sy.modelo.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * Conecta con la base de datos para obtener jugadorespor posicion y actualizar fichajes
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class MercadoDAO {
    
    private MercadoDAO(){
        
        
        
    }
    
    
    private static final String SELECT_JUGADORES_POSICION = "SELECT j.id, j.nombre, j.posicion, j.nacionalidad, j.foto, " +
                                                           "c.nombre AS club_nombre, c.foto AS club_foto " +
                                                           "FROM Jugador j " +
                                                           "JOIN Club c ON j.id_club = c.id_club " +
                                                           "WHERE j.posicion = ? " +
                                                           "ORDER BY j.nombre ASC";

    private static final String DELETE_JUGADOR_ANTIGUO = "DELETE FROM EquipoJugador WHERE id_equipo = ? AND id_jugador = ?";
    
    private static final String INSERT_NUEVO_JUGADOR = "INSERT INTO EquipoJugador (id_equipo, id_jugador, slot) VALUES (?, ?, ?)";
    
    /**
     * Obtiene de la base de datos todos los jugadores en la posicion indicada
     * @param posicion, posicion a buscar
     * @return lista con los jugadores
     */
    public static List<Jugador> obtenerJugadoresPorPosicion(String posicion){
        
        
        List<Jugador> jugadores = new ArrayList<>();
        
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             
            PreparedStatement ps = conn.prepareStatement(SELECT_JUGADORES_POSICION)) {
            
            ps.setString(1, posicion);


            try (ResultSet rs = ps.executeQuery()) {
            
                while (rs.next()) {
                    
                    Jugador j = new Jugador();
                    j.setId(rs.getInt("id"));
                    j.setNombre(rs.getString("nombre"));
                    j.setPosicion(rs.getString("posicion"));
                    j.setNacionalidad(rs.getString("nacionalidad"));
                    j.setFoto(rs.getString("foto"));
                    j.setNombreClub(rs.getString("club_nombre"));
                    j.setFotoClub(rs.getString("club_foto"));
                    jugadores.add(j);
            
                }
        
            }
        
        } catch (SQLException e) {
           
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
        
        return jugadores;
        
    }
    
    /**
     * Ficha un jugador nuevo reemplazando al que habia
     * @param idEquipo, equipo objetivo
     * @param idJugadorViejo, id del antiguo jugador
     * @param idJugadorNuevo, id del nuevo jugador 
     */
    public static void ficharJugador(int idEquipo, int idJugadorViejo, int idJugadorNuevo, int slot){
        
        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
            conn.setAutoCommit(false); // Transaccional por seguridad si hay reemplazo
            
            if (idJugadorViejo > 0) {
                
                try (PreparedStatement psDel = conn.prepareStatement(DELETE_JUGADOR_ANTIGUO)) {
                    
                    psDel.setInt(1, idEquipo);
                    psDel.setInt(2, idJugadorViejo);
                    psDel.executeUpdate();
                }
            }
            
            try (PreparedStatement psIns = conn.prepareStatement(INSERT_NUEVO_JUGADOR)) {
                psIns.setInt(1, idEquipo);
                psIns.setInt(2, idJugadorNuevo);
                psIns.setInt(3, slot);
                psIns.executeUpdate();
            }
            
            conn.commit();
            
        
        } catch (SQLException e) {
            
           throw new RuntimeException("Error crítico en la base de datos", e);
            
        }
        
        
    }
    
}
