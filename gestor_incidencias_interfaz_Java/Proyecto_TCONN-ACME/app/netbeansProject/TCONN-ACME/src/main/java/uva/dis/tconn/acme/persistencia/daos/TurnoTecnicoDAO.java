/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.persistencia.daos;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import uva.dis.tconn.acme.persistencia.accesodb.ConexionDB;
import uva.dis.tconn.acme.servicioscomunes.datatypes.IdEmpleado;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.TecnicoNoEnTurnoException;

/**
 *
 * @author aacar
 */
public class TurnoTecnicoDAO {
    
    private TurnoTecnicoDAO (){
        
        
    }
    
    public static final String SELECT_TURNO_ACTUAL_TECNICO = 
            "SELECT " + 
                "t.id_turno, " + 
                "t.fecha_inicio, " + 
                "t.hora_inicio, " + 
                "t.fecha_fin, " + 
                "t.hora_fin, " + 
                "tec.nivel_tecnico, " + 
                "p.fecha_inicio AS fecha_inicio_puesto, " + 
                "b.id_base, " + 
                "b.id_zona, " + 
                "(b.ubicacion).latitud AS lat, " + 
                "(b.ubicacion).longitud AS lon " + 
            "FROM TurnoTecnico t " + 
            "JOIN Tecnico tec ON t.id_tecnico = tec.id_puesto " + 
            "JOIN Puesto p ON tec.id_puesto = p.id_puesto " +
            "JOIN Base b ON t.id_base = b.id_base " + 
            "WHERE t.id_tecnico = ? " + 
              "AND CURRENT_TIMESTAMP BETWEEN (t.fecha_inicio + t.hora_inicio) AND (t.fecha_fin + t.hora_fin);";
    
    public static String obtenerTurnoActual(IdEmpleado idTecnico) throws ErrorSistemaException, TecnicoNoEnTurnoException{
        
        
        String turnoJsonString = "";
        
        try{
            
            ConexionDB conexion = ConexionDB.getInstancia();
            conexion.abrirConexion();
            
            try(PreparedStatement statement = conexion.obtenerStatement(SELECT_TURNO_ACTUAL_TECNICO);){
                
                int idTecnicoDB = idTecnico.getNumero();
                
                statement.setInt(1, idTecnicoDB);
                
                ResultSet resultado = statement.executeQuery();
                
                if(resultado.next()){
                    
                    turnoJsonString = crearStringJson(resultado);
                    resultado.close();
                    
                    
                }else{
                    
                    throw new TecnicoNoEnTurnoException("El tecnico no se encuentra en turno");
                    
                }
                
                
            }
            
        }catch (SQLException e){
            
                throw new ErrorSistemaException("Error del sistema", e);
        }
        
        return turnoJsonString;
        
        
    }
    
    
    private static String crearStringJson(ResultSet resultado) throws SQLException, ErrorSistemaException{
        
        int idTurno; 
        String fechaInicio;
        String horaInicio;
        String fechaFin;
        String horaFin;
        String nivel;
        String fechaInicioPuesto;
        int idBase;
        String idZona;
        double latitud;
        double longitud;
        
        
        idTurno = resultado.getInt("id_turno");
        fechaInicio = resultado.getString("fecha_inicio");
        horaInicio = resultado.getString("hora_inicio");
        fechaFin = resultado.getString("fecha_fin");
        horaFin = resultado.getString("hora_fin");
        nivel = resultado.getString("nivel_tecnico");
        fechaInicioPuesto = resultado.getString("fecha_inicio_puesto");
        idBase = resultado.getInt("id_base");
        idZona = resultado.getString("id_zona");
        latitud = resultado.getDouble("lat");
        longitud = resultado.getDouble("lon");
        
        JsonObject empleadoJson = Json.createObjectBuilder()
                .add("id_turno", idTurno)
                .add("fecha_inicio", fechaInicio)
                .add("hora_inicio", horaInicio)
                .add("fecha_fin", fechaFin)
                .add("hora_fin", horaFin)
                .add("nivel_tecnico", nivel)
                .add("fecha_inicio_puesto", fechaInicioPuesto)
                .add("id_base", idBase)
                .add("id_zona", idZona)
                .add("lat", latitud)
                .add("lon", longitud)
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
