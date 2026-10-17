/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.persistencia.daos;

import java.util.List;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import uva.dis.tconn.acme.negocio.modelos.enums.EstadoIncidencia;
import uva.dis.tconn.acme.persistencia.accesodb.ConexionDB;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;


/**
 * DAO de la entidad Incidencia, se encarga de encapsular la comunicacion la BD
 * Solo implementa el proceso de consulta
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class IncidenciaDAO {
    /**
     * Constructor privado para evitar la instanciacion
     */
    private IncidenciaDAO(){
        
    }
    
    /**
     * String de la query o consulta  para filtrar por fechas, mas adelante se aniade el el orden ascendente y filtro
     */
    public static final String SELECT_INCIDENCIAS_SEGUN_FECHA = 
        "SELECT i.codigo, i.descripcion, i.criticidad, i.estado, llama.hora " +
        "FROM Incidencia i " +
        "JOIN LlamadaCorrecta llamac ON i.id_llamada_correcta = llamac.id_llamada " + 
        "JOIN Llamada llama ON llamac.id_llamada = llama.id_llamada " +
        "WHERE i.fecha = ? ";
        
       
    /**
     * Permite sacar una lista de tipo inciidencias a traves de la consulta y filtro, ademas de ordenar en orden ascendente
     * @param fecha de tipo LocalDate 
     * @param filtro estado de las incidenias a filtrar, si es null todas las incidencias 
     * @return lista de tipo Incidencias ordenadas segun la hora y con la fecha 
     * @throws ErrorSistemaException si ocurre error con la consulta sql
     */
    public static List<String> obtenerIncidenciasSegunFecha(LocalDate fecha,List<EstadoIncidencia> filtro) throws ErrorSistemaException{
        
        List<String> listaIncidencias = null;
        
        StringBuilder consulta = new StringBuilder(SELECT_INCIDENCIAS_SEGUN_FECHA);

        if (filtro != null) {
            for(int i = 0; i < filtro.size(); i++){
                if(i == 0){
                    consulta.append("AND (");
                }
                switch (filtro.get(i)) {
                    case ESCALADA_A_ESPECIALISTA:
                        consulta.append("i.estado='escaladaAEspecialista'");
                        break;
                    case PARCIALMENTE_RESUELTA:
                        consulta.append("i.estado='parcialmenteResuelta'");
                        break;
                    case RESUELTA_IN_SITU:
                        consulta.append("i.estado='resueltaInSitu'");
                        break;
                    case RESUELTA_REMOTAMENTE:
                        consulta.append("i.estado='resueltaRemotamente'");
                        break;
                    default:
                        consulta.append("i.estado='sinResolver'");
                        break;
                }
                if(i == filtro.size()-1){
                    consulta.append(") ");
                }else{
                    consulta.append(" OR ");
                }
            }
        }
        
        consulta.append(" ORDER by llama.hora ASC");
        
        try{
            ConexionDB conexion= ConexionDB.getInstancia();
            conexion.abrirConexion();
            
            try(
                PreparedStatement statement = conexion.obtenerStatement(consulta.toString());){
                
                statement.setDate(1,java.sql.Date.valueOf(fecha));//LocalDate no funciona hay que convertirlo a Date
                
                ResultSet resultado;
                resultado = statement.executeQuery();
                
                listaIncidencias=crearStringJson(resultado);
                resultado.close();
                
                }
            
        } catch(SQLException e){
            throw new ErrorSistemaException("Error en el sistema",e);
        }
        return listaIncidencias;
        
    }
    /**
     * Permite crear una ncidenciasJSON e ir aniadidendo a la lista de incidencias
     * @param resultado de tipo ResultSet
     * @return lista
     * @throws IOException si no se puede construir la lista
     * @throws SQLException
     * @throws ErrorSistemaException no se ha podido crear la lista
     */
    private static List<String> crearStringJson(ResultSet resultado) throws SQLException, ErrorSistemaException{
        
        List<String> lista= new ArrayList<>();
        
        while(resultado.next()){
            
                JsonObject incidenciaJSON = Json.createObjectBuilder()
                        .add("codigo",resultado.getString("codigo"))
                        .add("descripcion",resultado.getString("descripcion"))
                        .add("criticidad",resultado.getString("criticidad"))
                        .add("estado",resultado.getString("estado"))
                        .add("hora",resultado.getString("hora"))
                        .build();


            try(StringWriter stringWriter = new StringWriter();

                JsonWriter writer = Json.createWriter(stringWriter)){

                writer.writeObject(incidenciaJSON);
                lista.add(stringWriter.toString());

            } catch(java.io.IOException e){
                throw new ErrorSistemaException("Error al construir la lista de incidencias JSON",e);
            }
        

        }
        return lista;
    
   
    }
}
    