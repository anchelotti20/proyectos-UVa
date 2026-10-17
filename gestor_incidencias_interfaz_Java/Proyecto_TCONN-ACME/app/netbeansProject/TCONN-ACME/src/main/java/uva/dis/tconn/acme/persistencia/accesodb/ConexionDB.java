    package uva.dis.tconn.acme.persistencia.accesodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;

/**
 * Crea la conexion a la BD y devuelve la instancia cada vez que se necesite, siempre la misma (singleton).
 * @author Angel Antolin 
 * @author Elisa Martinez 
 * @author Marcos Gomez
 */
public class ConexionDB {
    
    private static ConexionDB instanciaDB;
    
    private Connection conexion;
    private String url;
    private String usuario;
    private String password;
    
    /**
     * Incializa la Instancia de la BD, leyendo del archivo de configuracion y usando las variables
     * de entorno configuradas para acceder.
     * @throws RuntimeException si no encuentra el archivo, si hay error al leerlo o si no encuentra el driver
     */
    private ConexionDB() throws ErrorSistemaException {
        Properties propiedad = new Properties();
        String envUrl;
        String envUsuario;
        String envpassword;
        
        try (FileInputStream leer = new FileInputStream("../../db/configdb.env")) {
            propiedad.load(leer);
            
            envUrl = propiedad.getProperty("url").replace("$","");
            envUsuario = propiedad.getProperty("user").replace("$", "");
            envpassword = propiedad.getProperty("password").replace("$", "");
            
            this.url = System.getenv(envUrl);
            this.usuario = System.getenv(envUsuario);
            this.password = System.getenv(envpassword);
            
                
        } catch (FileNotFoundException e) {
            throw new ErrorSistemaException("configdb.env no ha sido encontrado en la ruta app/db/", e);
        } catch (IOException e) {
            throw new ErrorSistemaException("Error al leer configdb.env", e);
        }
        
    }
        
    /**
     * Obtiene la instancia de la DB o la crea si esta no existe
     * @return InstanciaDB, la instancia de la DB
     */
    public static synchronized ConexionDB getInstancia() throws ErrorSistemaException {
        if (instanciaDB == null) {
            instanciaDB = new ConexionDB();
        }
        return instanciaDB;
    }
    
    /**
     * Abre la conexion con la base de datos, solo lo hace en caso de que no se haya hecho ya
     * @throws SQLException, si hay algun error 
     */
    public void abrirConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(url, usuario, password);
        }
    }
    
    /**
     * Cierra la conexion con la base de datos, solo lo hace en caso de que haya una abierta
     * @throws SQLException, si hay algun error
     */
    public void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }
    
    /**
     * Prepara la query para la base de datos
     * @param query, string con la query a enviar
     * @return la query en el lenguaje que entiende la base de datos
     * @throws SQLException, si hay algun error
     */
    public PreparedStatement obtenerStatement(String query) throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            abrirConexion();
        }
        return conexion.prepareStatement(query);
    }

} 