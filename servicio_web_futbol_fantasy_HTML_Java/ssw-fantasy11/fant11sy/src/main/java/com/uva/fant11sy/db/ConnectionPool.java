package com.uva.fant11sy.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.naming.InitialContext;


/**
 * Maneja la conexion con la Base de Datos
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class ConnectionPool {
    
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    private ConnectionPool(){
        
        try{
            
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/fantasy11");
            
            
        }catch(Exception e){
            
            e.printStackTrace();
        }
        
    }
    
    
    public static ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        
        return pool;

    }

    public Connection getConnection() {

        try {

            return dataSource.getConnection();

        }catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }

    }
    
    public void freeConnection(Connection c) {
        try {

            c.close();

        }catch (SQLException sqle) {

            sqle.printStackTrace();

        }

    }
    
}
