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
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;

/**
 * DAO de la Entidad contrato, encapsula y gestiona la comunicacion con la BD
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */

public class ContratoDAO {

    private ContratoDAO() {
        //constructor privado para evitar instanciacion
    }

    public static final String SELECT_DATOS_CONTRATO = 
        "SELECT c.id_cliente, c.numero_contrato, c.fecha_inicio, c.fecha_fin, c.activo, " +
        "(c.ubicacion).nombre_via, (c.ubicacion).numero, (c.ubicacion).piso, " +
        "(c.ubicacion).puerta, (c.ubicacion).codigo_postal, " +
        "(c.ubicacion).localidad, (c.ubicacion).provincia " +
        "FROM Contrato c JOIN Persona p ON c.id_cliente = p.id_persona " +
        "LEFT JOIN ClienteEmpresarial ce ON c.id_cliente = ce.id_persona " +
        "WHERE c.numero_contrato = ? AND (p.nif = ? OR ce.cif = ?) " +
        "AND c.activo = true AND (c.fecha_fin IS NULL OR c.fecha_fin >= CURRENT_DATE) " +
        "LIMIT 1;";

    public static String obtenerContratoPorNumero (String nContrato, String nifCif) throws ErrorSistemaException {
        
        String contratoJsonString = null;

        try{
            ConexionDB conexion = ConexionDB.getInstancia();
            conexion.abrirConexion();

            try(PreparedStatement statement = conexion.obtenerStatement(SELECT_DATOS_CONTRATO);){
                
                statement.setString(1, nContrato);
                statement.setString(2, nifCif);
                statement.setString(3, nifCif);

                ResultSet resultado = statement.executeQuery();
                
                if(resultado.next()){
                    contratoJsonString = crearStringJson(resultado);
                }
                resultado.close();
            }
        } catch (SQLException e){
            throw new ErrorSistemaException("Error en el sistema", e);
        }
        return contratoJsonString;
    }

    private static String crearStringJson(ResultSet resultado) throws SQLException, ErrorSistemaException {
        String fechaFin = resultado.getString("fecha_fin");
        String puerta = resultado.getString("puerta");

        JsonObject contratoJSON = Json.createObjectBuilder()
                .add("id_cliente", resultado.getInt("id_cliente"))
                .add("numero_contrato", resultado.getString("numero_contrato"))
                .add("fecha_inicio", resultado.getString("fecha_inicio"))
                .add("fecha_fin", (fechaFin != null) ? fechaFin : "")
                .add("activo", resultado.getBoolean("activo"))
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
