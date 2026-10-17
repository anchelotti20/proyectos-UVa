package com.uva.fant11sy.daos;

import com.uva.fant11sy.db.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Conecta con la base de datos para la gestion individual de los jugadores.
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class JugadorDAO {
    
    private JugadorDAO() {
    }

    private static final String INSERT_JUGADOR = "INSERT IGNORE INTO Jugador (id, nombre, posicion, nacionalidad, foto, id_club) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * Guarda un jugador en la base de datos si no existe previamente.
     * @param idJugador identificador del jugador en la API
     * @param nombre nombre comun del jugador
     * @param posicion demarcacion en el campo (Portero, Defensa...)
     * @param nacionalidad pais de origen
     * @param foto url de la foto del jugador
     * @param idClub identificador del club al que pertenece
     */
    public static void guardarJugador(int idJugador, String nombre, String posicion, String nacionalidad, String foto, int idClub) {
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_JUGADOR)) {
            ps.setInt(1, idJugador);
            ps.setString(2, nombre);
            ps.setString(3, posicion);
            ps.setString(4, nacionalidad);
            ps.setString(5, foto);
            ps.setInt(6, idClub);
            ps.executeUpdate();
        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
    }
}