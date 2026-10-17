package com.uva.fant11sy.daos;

import com.uva.fant11sy.db.ConnectionPool;
import com.uva.fant11sy.modelo.Partido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Conecta con la base de datos para la gestion de los partidos.
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class PartidoDAO {
    
    private PartidoDAO(){}
    
    private static final String SELECT_PARTIDOS_JORNADA = "SELECT cl.nombre AS local, cv.nombre AS visitante, " +
                                                         "cl.foto AS fotoLocal, cv.foto AS fotoVisitante, " +
                                                         "p.golesLocal, p.golesVisitante, p.ganador " +
                                                         "FROM Partido p " +
                                                         "JOIN Club cl ON p.id_club_local = cl.id_club " +
                                                         "JOIN Club cv ON p.id_club_visitante = cv.id_club " +
                                                         "WHERE p.numero_jornada = ?";
    
    private static final String SELECT_JORNADA_ACTUAL_PENDIENTE = "SELECT MIN(numero_jornada) AS jornada " +
                                                                "FROM Partido " +
                                                                "WHERE ganador IS NULL OR ganador = ''";
    
    private static final String SELECT_ULTIMA_JORNADA = "SELECT MAX(numero_jornada) AS jornada FROM Partido";
    
    private static final String INSERT_PARTIDO_INICIAL = "INSERT IGNORE INTO Partido (id_partido, id_club_local, id_club_visitante, numero_jornada) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_RESULTADO_PARTIDO = "UPDATE Partido SET golesLocal = ?, golesVisitante = ?, ganador = ? WHERE id_partido = ?";

    /**
     * Obtiene la lista de partidos correspondientes a una jornada especifica.
     * @param numeroJornada, el numero de la jornada a consultar.
     * @return lista de partidos de la jornada solicitada.
     */
    public static List<Partido> obtenerPartidosPorJornada(int numeroJornada){
        List<Partido> partidosJornada = new ArrayList<>();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_PARTIDOS_JORNADA)) {
            ps.setInt(1, numeroJornada);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Partido p = new Partido();
                    p.setNombreLocal(rs.getString("local"));
                    p.setNombreVisitante(rs.getString("visitante"));
                    p.setFotoLocal(rs.getString("fotoLocal"));
                    p.setFotoVisitante(rs.getString("fotoVisitante"));
                    p.setgolesLocal(rs.getInt("golesLocal"));
                    p.setgolesVisitante(rs.getInt("golesVisitante"));
                    p.setGanador(rs.getString("ganador"));
                    partidosJornada.add(p);
                }
            }
        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
        return partidosJornada;
    }
    
    /**
     * Obtiene el numero de la jornada actual que esta pendiente de jugarse o finalizar.
     * @return entero con el numero de la jornada actual.
     */
    public static int obtenerJornadaActual(){
        int jornadaActual = 1;
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_JORNADA_ACTUAL_PENDIENTE);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next() && rs.getInt("jornada") > 0) {
                jornadaActual = rs.getInt("jornada");
            } else {
                try (PreparedStatement psUltima = conn.prepareStatement(SELECT_ULTIMA_JORNADA);
                     ResultSet rsUltima = psUltima.executeQuery()) {
                    if (rsUltima.next() && rsUltima.getInt("jornada") > 0) {
                        jornadaActual = rsUltima.getInt("jornada");
                    }
                }
            }
        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
        return jornadaActual;
    }
    
    /**
     * Inserta un partido en la base de datos durante la carga inicial del calendario.
     * Ignora la insercion si el partido ya existe en la base de datos.
     * @param idPartido, identificador del partido devuelto por la API.
     * @param idLocal, identificador del equipo local.
     * @param idVisitante, identificador del equipo visitante.
     * @param jornada, numero de la jornada en la que se disputa el encuentro.
     */
    public static void guardarPartidoInicial(int idPartido, int idLocal, int idVisitante, int jornada) {
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_PARTIDO_INICIAL)) {
            ps.setInt(1, idPartido);
            ps.setInt(2, idLocal);
            ps.setInt(3, idVisitante);
            ps.setInt(4, jornada);
            ps.executeUpdate();
        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
    }
    
    /**
     * Actualiza los goles y determina el ganador de un partido ya finalizado.
     * @param idPartido, identificador del partido a actualizar.
     * @param golesLocal, cantidad de goles anotados por el equipo local.
     * @param golesVisitante, cantidad de goles anotados por el equipo visitante.
     */
    public static void actualizarResultado(int idPartido, int golesLocal, int golesVisitante) {
        String ganador = "E";
        if (golesLocal > golesVisitante) ganador = "L";
        else if (golesVisitante > golesLocal) ganador = "V";

        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_RESULTADO_PARTIDO)) {
            ps.setInt(1, golesLocal);
            ps.setInt(2, golesVisitante);
            ps.setString(3, ganador);
            ps.setInt(4, idPartido);
            ps.executeUpdate();
        } catch (SQLException e) {
            
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
    }
}