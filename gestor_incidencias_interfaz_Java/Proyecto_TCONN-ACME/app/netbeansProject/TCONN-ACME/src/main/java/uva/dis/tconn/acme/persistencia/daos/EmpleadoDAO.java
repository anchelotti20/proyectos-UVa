/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.persistencia.daos;

import uva.dis.tconn.acme.persistencia.accesodb.ConexionDB;
import uva.dis.tconn.acme.servicioscomunes.excepciones.CredencialesInvalidasException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.EmpleadoNoExisteException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 * DAO de la Entidad empleado, encapsula y gestiona la comunicacion con la BD
 * Implementa solo la consulta, pues el caso de uso solo es identificar un usuario, no modificarlo
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class EmpleadoDAO {
    
    private EmpleadoDAO(){
        //constructor privado para evitar instanciacion
    }
    /**
     * String de la query, saca que tipo de puesto tiene el empleado para saber que vista mostrarle
     */
    public static final String SELECT_EMPLEADO_POR_NIF = 
    "SELECT p.id_persona, p.nombre, p.apellidos, p.telefono_contacto, " +
    "       (p.direccion).nombre_via, (p.direccion).numero, (p.direccion).piso, " +
    "       (p.direccion).puerta, (p.direccion).codigo_postal, (p.direccion).localidad, " +
    "       (p.direccion).provincia, p.nif, " +
    "       e.email, e.password, e.fecha_incorporacion, e.fecha_fin_contrato, " +
    "       v.vinculo AS vinculacion, " +
    "       d.disponibilidad, " +
    "       CASE " +
    "           WHEN t.id_puesto IS NOT NULL THEN 'TECNICO' " +
    "           WHEN c.id_puesto IS NOT NULL THEN 'COORDINADOR' " +
    "           WHEN s.id_puesto IS NOT NULL THEN 'SUPERVISOR' " +
    "           ELSE 'DESCONOCIDO' " +
    "       END AS tipo_puesto " +
    "FROM Persona p " +
    "JOIN Empleado e ON p.id_persona = e.id_persona " +
    "LEFT JOIN VinculacionesConlaEmpresa v ON e.id_persona = v.id_empleado " +
    "     AND v.inicio = (SELECT MAX(inicio) FROM VinculacionesConlaEmpresa WHERE id_empleado = e.id_persona) " +
    "LEFT JOIN Disponibilidades d ON e.id_persona = d.id_empleado " +
    "     AND d.comienzo = (SELECT MAX(comienzo) FROM Disponibilidades WHERE id_empleado = e.id_persona) " +
    "JOIN Puesto pst ON e.id_persona = pst.id_empleado " +
    "     AND pst.fecha_inicio = (SELECT MAX(fecha_inicio) FROM Puesto WHERE id_empleado = e.id_persona) " +
    "LEFT JOIN Tecnico t ON pst.id_puesto = t.id_puesto " +
    "LEFT JOIN Coordinador c ON pst.id_puesto = c.id_puesto " +
    "LEFT JOIN Supervisor s ON pst.id_puesto = s.id_puesto " +
    "WHERE p.nif = ?;";
    /**
     * Obtiene el json con los datos de un empleado dado su nif
     * @param nif, string con el nif del empleado
     * @param passwordIntroducida, string con la password introducida
     * @return empleadoJsonString, JSON en formato string
     * @throws ErrorSistemaException, si ocurre algun error
     * @throws CredencialesInvalidasException, si la contrasena no coincide
     * @throws EmpleadoNoExisteException, si no devuelve nada la consulta
     */
    public static String obtenerEmpleadoPorNif(String nif, String passwordIntroducida) throws CredencialesInvalidasException, EmpleadoNoExisteException, ErrorSistemaException{
        
        String empleadoJsonString = null;
        
        try{
        
            ConexionDB conexion = ConexionDB.getInstancia();
            conexion.abrirConexion();
        
            try(
                PreparedStatement statement = conexion.obtenerStatement(SELECT_EMPLEADO_POR_NIF);){
            
                statement.setString(1, nif);
            
                ResultSet resultado = statement.executeQuery();
            
                if(resultado.next()){
                
                    String passwordBD = resultado.getString("password");
                
                    if(!passwordBD.equals(passwordIntroducida)){
                    
                        throw new CredencialesInvalidasException("Credenciales inválidas");
                    }
                
                    empleadoJsonString = crearStringJson(resultado);
                    resultado.close();
                
                }else{
                
                    throw new EmpleadoNoExisteException("Empleado no encontrado");
                
                }
            
            }
        
        }catch (SQLException e){
            
                throw new ErrorSistemaException("Error del sistema", e);
        }
        
        return empleadoJsonString;
        
    }
    
    /**
     * Crea el JsonString dado el resultado de la query a la base de datos
     * @param resultado, iterador sonbre el resultado de la query
     * @return string con el JSON
     * @throws SQLException, si ocurre algun error
     * @throws RuntimeException, si hay error a la hora de crear el JSON
     */
    private static String crearStringJson(ResultSet resultado) throws SQLException, ErrorSistemaException {
        
        
        String fechaFin = resultado.getString("fecha_fin_contrato");
        String puerta = resultado.getString("puerta");
        
        
        JsonObject empleadoJSON = Json.createObjectBuilder()
                .add("id_persona_bd", resultado.getInt("id_persona"))
                .add("nombre", resultado.getString("nombre"))
                .add("apellidos", resultado.getString("apellidos"))
                .add("telefono", resultado.getInt("telefono_contacto"))
                .add("nif", resultado.getString("nif"))
                .add("via", resultado.getString("nombre_via"))
                .add("numero", resultado.getInt("numero"))
                .add("piso", resultado.getInt("piso"))
                .add("puerta", (puerta != null) ? puerta : "")
                .add("cp", resultado.getInt("codigo_postal"))
                .add("localidad", resultado.getString("localidad"))
                .add("provincia", resultado.getString("provincia"))
                .add("email", resultado.getString("email"))
                .add("fecha_inc", resultado.getString("fecha_incorporacion"))
                .add("fecha_fin", (fechaFin != null) ? fechaFin : "")
                .add("vinculacion", resultado.getString("vinculacion"))
                .add("disponibilidad", resultado.getString("disponibilidad"))
                .add("tipo_puesto", resultado.getString("tipo_puesto"))
                .build();
        
        
        
        try (StringWriter stringWriter = new StringWriter();
             
            JsonWriter writer = Json.createWriter(stringWriter)) {
            
            
            writer.writeObject(empleadoJSON);
            return stringWriter.toString();
            
        } catch (java.io.IOException e) {
            throw new ErrorSistemaException("Error al construir el JSON del empleado", e);
        }
    }
    
    
}
