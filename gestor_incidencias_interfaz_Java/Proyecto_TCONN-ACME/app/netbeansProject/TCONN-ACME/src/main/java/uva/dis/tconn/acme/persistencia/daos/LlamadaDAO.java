package uva.dis.tconn.acme.persistencia.daos;

import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

import uva.dis.tconn.acme.persistencia.accesodb.ConexionDB;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;

/**
 * DAO de la Entidad llamada, encapsula y gestiona la comunicacion con la BD
 * 
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */

public class LlamadaDAO {

    private static String[] datosJsons = {"fecha", "numero_telefono", "hora", 
                                    "duracion", "id_coordinador", 
                                    "numero_contrato", "id_llamada"};

    private LlamadaDAO() {
        // constructor privado para evitar instanciacion
    }

    private static final String INSERT_LLAMADA = 
        "INSERT INTO Llamada (fecha, numero_telefono, hora, duracion, id_coordinador) " +
        "VALUES (?::date, ?, ?::time, ?, ?) RETURNING id_llamada";

    private static final String INSERT_LLAMADA_INCORRECTA = 
        "INSERT INTO LlamadaIncorrecta (id_llamada) VALUES (?);";

    private static final String INSERT_LLAMADA_CONTRATO = 
        "INSERT INTO LlamadaContrato (id_llamada, id_contrato) VALUES (?, ?);";

    private static final String INSERT_LLAMADA_INCONSISTENTE = 
        "INSERT INTO LlamadaInconsistente (id_llamada, datos_inconsistentes) VALUES (?, ?);";

    private static final String INSERT_LLAMADA_CORRECTA = 
        "INSERT INTO LlamadaCorrecta (id_llamada) VALUES (?);";

    public static void registrarLlamadaIncorrecta(String llamadaJson) throws ErrorSistemaException{

        JsonObject json = parsearJson(llamadaJson);

        try {
            ConexionDB conexion = ConexionDB.getInstancia();
            conexion.abrirConexion();

            int idLlamadaGenerado = insertarLlamadaBase(json, conexion);

            try (PreparedStatement stmtIncorrecta = conexion.obtenerStatement(INSERT_LLAMADA_INCORRECTA)) {
                stmtIncorrecta.setInt(1, idLlamadaGenerado);
                stmtIncorrecta.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ErrorSistemaException("Error de base de datos al registrar la llamada incorrecta.", e);
        }
    }

    public static void registrarLlamadaInconsistente(String llamadaJson) throws ErrorSistemaException {
        
        JsonObject json = parsearJson(llamadaJson);
    
        try {
            ConexionDB conexion = ConexionDB.getInstancia();
            conexion.abrirConexion();
            
            int idLlamadaGenerado = insertarLlamadaBase(json, conexion);
            int idContrato = obtenerIdInternoContrato(conexion, json.getString(datosJsons[5]));


            try (PreparedStatement stmtContrato = conexion.obtenerStatement(INSERT_LLAMADA_CONTRATO)) {
                stmtContrato.setInt(1, idLlamadaGenerado);
                stmtContrato.setInt(2, idContrato);
                stmtContrato.executeUpdate();
            }

            try (PreparedStatement stmtInconsistente = conexion.obtenerStatement(INSERT_LLAMADA_INCONSISTENTE)) {
                stmtInconsistente.setInt(1, idLlamadaGenerado);
                stmtInconsistente.setString(2, json.getString("datos_inconsistentes"));
                stmtInconsistente.executeUpdate();
            }
            
        } catch (SQLException e) {
            throw new ErrorSistemaException("Error de base de datos al registrar la llamada inconsistente.", e);
        }
    }

    public static void registrarLlamadaCorrecta(String jsonLlamadaCorrecta) throws ErrorSistemaException {
        JsonObject json = parsearJson(jsonLlamadaCorrecta);
        try {
            ConexionDB conexion = ConexionDB.getInstancia();
            conexion.abrirConexion();

            int idLlamadaGenerado = insertarLlamadaBase(json, conexion);
            int idContrato = obtenerIdInternoContrato(conexion, json.getString(datosJsons[5]));

            try (PreparedStatement stmtContrato = conexion.obtenerStatement(INSERT_LLAMADA_CONTRATO)) {
                stmtContrato.setInt(1, idLlamadaGenerado);
                stmtContrato.setInt(2, idContrato);
                stmtContrato.executeUpdate();
            }

            try (PreparedStatement stmtCorrecta = conexion.obtenerStatement(INSERT_LLAMADA_CORRECTA)) {
                stmtCorrecta.setInt(1, idLlamadaGenerado);
                stmtCorrecta.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ErrorSistemaException("Error de base de datos al registrar la llamada correcta.", e);
        }
    }

    private static int insertarLlamadaBase(JsonObject json, ConexionDB conexion) throws SQLException, ErrorSistemaException{
        int idLlamadaGenerado = -1;
        try (PreparedStatement stmtLlamada = conexion.obtenerStatement(INSERT_LLAMADA)) {
            stmtLlamada.setString(1, json.getString(datosJsons[0]));
            stmtLlamada.setInt(2, json.getInt(datosJsons[1]));
            stmtLlamada.setString(3, json.getString(datosJsons[2]));
            stmtLlamada.setInt(4, json.getInt(datosJsons[3]));

            stmtLlamada.setInt(5, json.getInt(datosJsons[4]));

            try (ResultSet rs = stmtLlamada.executeQuery()) {
                if (rs.next()) {
                    idLlamadaGenerado = rs.getInt(datosJsons[6]);
                }
            }
        }
        if (idLlamadaGenerado == -1) {
            throw new ErrorSistemaException("Error en BD: No se generó el ID de la llamada.", null);
        }

        return idLlamadaGenerado;
    }

    private static JsonObject parsearJson(String llamadaJson) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        try (JsonReader reader = factory.createReader(new StringReader(llamadaJson))) {
            return reader.readObject();
        }
    }

    private static int obtenerIdInternoContrato(ConexionDB conexion, String numeroContrato) throws SQLException {
    String sql = "SELECT id_contrato FROM Contrato WHERE numero_contrato = ?";
    try (PreparedStatement pstmt = conexion.obtenerStatement(sql)) {
        pstmt.setString(1, numeroContrato);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id_contrato");
            } else {
                throw new SQLException("No se encontró el id interno para el contrato: " + numeroContrato);
            }
        }
    }
}
}