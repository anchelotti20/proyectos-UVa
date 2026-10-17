/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.daos;

import com.uva.fant11sy.db.ConnectionPool;
import com.uva.fant11sy.modelo.Usuario;
import com.uva.fant11sy.util.Seguridad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Conecta con la base de datos para el flujo de inicio de sesion y registro
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class UsuarioDAO {
    
    private UsuarioDAO(){
        
        
    }
    
    private static final String SELECT_USUARIO_POR_CREDENCIALES ="SELECT * FROM Usuario u WHERE u.usuario = ?";
    
    private static final String INSERT_USUARIO = "INSERT INTO Usuario (nombre, usuario, contrasena, id_liga) VALUES (?, ?, ?, 1)";
    
    private static final String INSERT_EQUIPO = "INSERT INTO Equipo (nombre, id_usuario) VALUES (?, ?)";
    
    private static final String UPDATE_LIGA = "UPDATE Liga SET numeroJugadores = numeroJugadores + 1 WHERE id_liga = 1";
    
    
    /**
     * Devuelve un usuario por sus credenciales
     * @param nombreUsuario usuario
     * @param contrasena contrasena
     * @return usuario correspondiente al login
     */
    public static Usuario login(String nombreUsuario, String contrasena){
        
        
        Usuario usuarioLogin = null;
        
        
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement pstmtUsuario = conn.prepareStatement(SELECT_USUARIO_POR_CREDENCIALES)) {
        
        
            pstmtUsuario.setString(1, nombreUsuario);
              
            try (ResultSet rs = pstmtUsuario.executeQuery()) {
            
                    if (rs.next()) {
                
                        String passwordDB = rs.getString("contrasena");
                        
                        String contrasenaEncriptada = Seguridad.encriptarContrasena(contrasena);
                        
                        if(passwordDB.equals(contrasenaEncriptada)){
                            
                            usuarioLogin=new Usuario(
                                rs.getInt("id_usuario"),
                                rs.getString("nombre"),
                                rs.getString("usuario"),
                                rs.getInt("puntos"),
                                rs.getInt("puesto"),
                                rs.getInt("id_liga")
                            );
                            
                        }
                    }
            
            }
              
        }catch (SQLException e) {
                
                throw new RuntimeException("Error crítico en la base de datos", e);
    
        }
        
        return usuarioLogin;
        
        
    }
    
    /**
     * Registra un usuario en el sistema
     * @param nombre 
     * @param usuario
     * @param contrasena
     * @return false si hubo error, true si no
     */
    public static boolean registrarUsuario(String nombre, String usuario, String contrasena){
        
        
        Connection conn = null;

        try{
            
            conn = ConnectionPool.getInstance().getConnection();
            if (conn == null) return false;
            
            
            conn.setAutoCommit(false);
            
            int idNuevoUsuario = -1;
            
            String contrasenaEncriptada = Seguridad.encriptarContrasena(contrasena);
            
            try (PreparedStatement pstmtUsuario = conn.prepareStatement(INSERT_USUARIO, Statement.RETURN_GENERATED_KEYS)) {
                pstmtUsuario.setString(1, nombre);
                pstmtUsuario.setString(2, usuario);
                pstmtUsuario.setString(3, contrasenaEncriptada);
                pstmtUsuario.executeUpdate();
       
                try (ResultSet rs = pstmtUsuario.getGeneratedKeys()) {
                    if (rs.next()) {
                        idNuevoUsuario = rs.getInt(1);
                    }
                }
            }
            
            
            if (idNuevoUsuario != -1) {
                try (PreparedStatement pstmtEquipo = conn.prepareStatement(INSERT_EQUIPO)) {
                    pstmtEquipo.setString(1, "Equipo de " + usuario);
                    pstmtEquipo.setInt(2, idNuevoUsuario);
                    pstmtEquipo.executeUpdate();
                }
                    
                try (PreparedStatement pstmtLiga = conn.prepareStatement(UPDATE_LIGA)) {
                    pstmtLiga.executeUpdate();
                }
                
                
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        
        } catch (SQLException e) {
            
            
            if (conn != null) {
                try { 
                    conn.rollback(); 
                } catch (SQLException ex) { 
                    
                    throw new RuntimeException("Error crítico en la base de datos", e);
                }
            }
            return false;
        
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
    
    public static final String UPDATE_PUNTOS_USUARIO = "UPDATE Usuario SET puntos = puntos + ? WHERE id_usuario = ?";

    /**
     * Suma los puntos obtenidos en la jornada al total del usuario
     * @param idUsuario id del usuario
     * @param puntos a sumar
     */
    public static void sumarPuntosUsuario(int idUsuario, int puntos) {
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_PUNTOS_USUARIO)) {
            
            ps.setInt(1, puntos);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error crítico en la base de datos", e);
        }
    }
    
}
