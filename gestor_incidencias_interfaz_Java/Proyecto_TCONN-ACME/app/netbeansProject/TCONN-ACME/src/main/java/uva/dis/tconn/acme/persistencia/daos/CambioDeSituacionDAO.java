/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.persistencia.daos;

import java.io.StringReader;
import java.io.StringWriter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonWriter;

import uva.dis.tconn.acme.persistencia.accesodb.ConexionDB;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.TecnicoNoAsignadoException;

/**
 *
 * @author aacar
 */
public class CambioDeSituacionDAO {
    
    private CambioDeSituacionDAO(){
        
        
    }
    
    
    private static final String ESTADO = "estado"; //Issue del sonar
    private static final String FECHA = "fecha"; //Issue del sonar
    
    
    public static final String SELECT_ULTIMO_CAMBIO_SITUACION = 
            "SELECT " +
                "estado, " +
                "(ubicacion).latitud AS lat, " +
                "(ubicacion).longitud AS lon, " +
                "fecha, " +
                "hora " +
            "FROM CambioDeSituacionTecnico " +
            "WHERE id_turno_tecnico = ? " +
            "ORDER BY fecha DESC, hora DESC " +
            "LIMIT 1;";
    
    public static final String INSERT_NUEVO_CAMBIO_SITUACION = 
            
            "INSERT INTO CambioDeSituacionTecnico " +
                "(estado, ubicacion, fecha, hora, id_turno_tecnico) " +
            "VALUES (?::estado_tecnico, ROW(?, ?), ?::date, ?::time, ?);";
    
    public static String obtenerUltimoCambio(int idTurno) throws ErrorSistemaException, TecnicoNoAsignadoException{
        
        
        String cambioJsonString = null;
        
        try{
            
            ConexionDB conexion = ConexionDB.getInstancia();
            conexion.abrirConexion();
            
            try(PreparedStatement statement = conexion.obtenerStatement(SELECT_ULTIMO_CAMBIO_SITUACION);){
                
                statement.setInt(1, idTurno);
                
                ResultSet resultado = statement.executeQuery();
                
                if(resultado.next()){
                    
                    cambioJsonString = crearStringJson(resultado);
                    resultado.close();   
                
                }else{
                    
                    throw new TecnicoNoAsignadoException("El tecnico no esta asignado");
                    
                }
                
            }
            
        }catch (SQLException e){
            
                throw new ErrorSistemaException("Error del sistema", e);
        }
        
        return cambioJsonString;
        
    }
    
    
    public static void registrarCambio(String nuevaSituacionJson) throws ErrorSistemaException{
        
        
        try{
            
            ConexionDB conexion = ConexionDB.getInstancia();
            conexion.abrirConexion();
            
            try(PreparedStatement statement = conexion.obtenerStatement(INSERT_NUEVO_CAMBIO_SITUACION);){
                
                
                JsonReaderFactory factory = Json.createReaderFactory(null);
        
                    try(JsonReader reader = factory.createReader(new StringReader(nuevaSituacionJson));){

                        JsonObject cambioJSON = reader.readObject();

                        String estadoString = cambioJSON.getString(ESTADO);
                        double latitud = cambioJSON.getJsonNumber("lat").doubleValue();
                        double longitud = cambioJSON.getJsonNumber("lon").doubleValue();
                        String fecha = cambioJSON.getString(FECHA);
                        String hora = cambioJSON.getString("hora");
                        int idTurno = cambioJSON.getInt("id_turno");

                       
                        statement.setString(1, estadoString); 
                        statement.setDouble(2, latitud);       
                        statement.setDouble(3, longitud);       
                        statement.setString(4, fecha);       
                        statement.setString(5, hora);        
                        statement.setInt(6, idTurno);
                        
                        

                    }
                
                statement.executeUpdate();
            }
            
        
        }catch (SQLException e){
            
                throw new ErrorSistemaException("Error del sistema", e);
        }
        
        
        
    }
    
    
    
    
    private static String crearStringJson(ResultSet resultado) throws SQLException, ErrorSistemaException{
        
        String estado; 
        double latitud;
        double longitud;
        String fecha;
        String hora;
        
        
        estado = resultado.getString(ESTADO);
        fecha = resultado.getString(FECHA);
        hora= resultado.getString("hora");
        latitud = resultado.getDouble("lat");
        longitud = resultado.getDouble("lon");
        
        JsonObject empleadoJson = Json.createObjectBuilder()
                .add(ESTADO, estado)
                .add("lat", latitud)
                .add("lon", longitud)
                .add(FECHA, fecha)
                .add("hora", hora)
                .build();
        
        try(StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);  ){
            
     
           writer.writeObject(empleadoJson);
           
        
           return stringWriter.toString();
            
        }catch (java.io.IOException e){
            
            throw new ErrorSistemaException("Error al construir el JSON", e);
            
        }
        
    }
    
 
    
  
    
}
