/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.persistencia.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import uva.dis.tconn.acme.persistencia.accesodb.ConexionDB;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;

/**
 *
 * @author aacar
 */
public class ZonaGeograficaDAO {
    
    
    private ZonaGeograficaDAO(){
        
    }
    
    
    public static final String COUNT_TECNICOS_DISPONIBLES = 
            "SELECT COUNT(t.id_turno) AS total_disponibles " +
            "FROM TurnoTecnico t " +
            "JOIN Base b ON t.id_base = b.id_base " +
            "WHERE b.id_zona = ? " +
            "  AND CURRENT_TIMESTAMP BETWEEN (t.fecha_inicio + t.hora_inicio) AND (t.fecha_fin + t.hora_fin) " +
            "  AND 'disponible'::estado_tecnico = ( " +
            "      SELECT estado " +
            "      FROM CambioDeSituacionTecnico c " +
            "      WHERE c.id_turno_tecnico = t.id_turno " +
            "      ORDER BY c.fecha DESC, c.hora DESC " +
            "      LIMIT 1 " +
            "  );";
    
    
    public static int contarTecnicosDisponibles(String idZona) throws ErrorSistemaException{
        
        int tecnicosDisponibles = 0;
        
        try{
            
            ConexionDB conexion = ConexionDB.getInstancia();
            conexion.abrirConexion();
            
            
            try(PreparedStatement statement = conexion.obtenerStatement(COUNT_TECNICOS_DISPONIBLES);){
        
                
                statement.setString(1, idZona);
                
                 ResultSet resultado = statement.executeQuery();
                
                if(resultado.next()){
                    
                    tecnicosDisponibles = resultado.getInt("total_disponibles");
                    resultado.close();   
                
                }
                
                
                
            }
            
        }catch (SQLException e){
            
                throw new ErrorSistemaException("Error del sistema", e);
        }
        
        
        return tecnicosDisponibles;
        
    }
    
    
    

}
