package uva.dis.tconn.acme.persistencia.daos;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

import uva.dis.tconn.acme.persistencia.accesodb.ConexionDB;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;

/**
 * DAO de la Entidad Cliente, encapsula y gestiona la comunicacion con la BD
 * 
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */

public class ClienteDAO {

    private ClienteDAO() {
        // constructor privado para evitar instanciacion
    }

    private static final String SELECT_DATOS_CLIENTE_PARTICULAR = 
        "SELECT p.nombre, p.apellidos, p.telefono_contacto AS telefono, " +
        "(p.direccion).nombre_via, (p.direccion).numero, (p.direccion).piso, " +
        "(p.direccion).puerta, (p.direccion).codigo_postal, " +
        "(p.direccion).localidad, (p.direccion).provincia " +
        "FROM Persona p JOIN ClienteParticular cp ON cp.id_persona = p.id_persona " +
        "WHERE p.nif = ? LIMIT 1;";

    private static final String SELECT_DATOS_CLIENTE_EMPRESARIAL =
        "SELECT ce.nombre_razon_social AS nombre, NULL AS apellidos, ce.telefono, " +
        "(ce.sede).nombre_via, (ce.sede).numero, (ce.sede).piso, " +
        "(ce.sede).puerta, (ce.sede).codigo_postal, " +
        "(ce.sede).localidad, (ce.sede).provincia " +
        "FROM ClienteEmpresarial ce " +
        "WHERE ce.cif = ? LIMIT 1;";

    public static String obtenerClientePorNifCif(String nifCif) throws ErrorSistemaException {
        String clienteJsonString = null;
        String consulta;
        char primerCaracter = nifCif.charAt(0);

        if(Character.isDigit(primerCaracter)) 
            consulta = SELECT_DATOS_CLIENTE_PARTICULAR;
        else
            consulta = SELECT_DATOS_CLIENTE_EMPRESARIAL;

        try{
            ConexionDB conexion = ConexionDB.getInstancia();
            conexion.abrirConexion();

            try(PreparedStatement statement = conexion.obtenerStatement(consulta);){
                
                statement.setString(1, nifCif);

                ResultSet resultado = statement.executeQuery();
                
                if(resultado.next()){
                    clienteJsonString = crearStringJson(resultado);
                }
                resultado.close();
            }
        } catch (SQLException e){
            throw new ErrorSistemaException("Error en el sistema", e);
        }
        return clienteJsonString;
    }

    private static String crearStringJson(ResultSet resultado) throws SQLException, ErrorSistemaException {
        
        String nombre = resultado.getString("nombre");
        String apellidos = resultado.getString("apellidos");
        String nombreCompleto = apellidos != null ? nombre + " " + apellidos : nombre;
        String puerta = resultado.getString("puerta");

        JsonObject contratoJSON = Json.createObjectBuilder()
                .add("nombre", nombreCompleto)
                .add("telefono", resultado.getString("telefono"))
                .add("nombre_via", resultado.getString("nombre_via"))
                .add("numero", resultado.getInt("numero"))
                .add("piso", resultado.getInt("piso"))
                .add("puerta", (puerta != null) ? puerta : "")
                .add("codigo_postal", resultado.getInt("codigo_postal"))
                .add("localidad", resultado.getString("localidad"))
                .add("provincia", resultado.getString("provincia"))
                .build();
        try (StringWriter stringWriter = new StringWriter();
             
            JsonWriter writer = Json.createWriter(stringWriter)) {
            
            
            writer.writeObject(contratoJSON);
            return stringWriter.toString();
            
        } catch (java.io.IOException e) {
            throw new ErrorSistemaException("Error al construir el JSON del contrato", e);
        }
    }
    
}
