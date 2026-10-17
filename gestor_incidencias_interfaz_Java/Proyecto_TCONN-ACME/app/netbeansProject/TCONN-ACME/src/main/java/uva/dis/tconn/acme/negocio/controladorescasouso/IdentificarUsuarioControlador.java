/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.controladorescasouso;

import java.io.StringReader;


import java.time.LocalDate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;


import uva.dis.tconn.acme.negocio.modelos.Coordinador;
import uva.dis.tconn.acme.servicioscomunes.datatypes.DatosFechaTrabajo;

import uva.dis.tconn.acme.negocio.modelos.Empleado;
import uva.dis.tconn.acme.negocio.modelos.Persona;
import uva.dis.tconn.acme.negocio.modelos.Puesto;
import uva.dis.tconn.acme.negocio.modelos.Supervisor;
import uva.dis.tconn.acme.negocio.modelos.Tecnico;
import uva.dis.tconn.acme.negocio.modelos.enums.Disponibilidad;
import uva.dis.tconn.acme.negocio.modelos.enums.Vinculacion;
import uva.dis.tconn.acme.servicioscomunes.Sesion;
import uva.dis.tconn.acme.servicioscomunes.excepciones.CredencialesInvalidasException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.EmpleadoInactivoException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.EmpleadoNoExisteException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;
import uva.dis.tconn.acme.persistencia.daos.EmpleadoDAO;
import uva.dis.tconn.acme.servicioscomunes.datatypes.Direccion;
import uva.dis.tconn.acme.servicioscomunes.datatypes.IdEmpleado;
import uva.dis.tconn.acme.servicioscomunes.datatypes.Nif;

/**
 * Controlador del caso de uso identificar usuario
 * Comprueba los datos introducidos y recupera con ayuda del DAO el empleado correspondiente
 * Maneja los casos no validos con excepciones personalizadas del dominio
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class IdentificarUsuarioControlador {
    
    
    /**
     * Recibe los datos introducidos por el usuario en la vista y comprueba si es un usuario valido
     * @param nif, String con el nif
     * @param password, String con la password
     * @return tipo de rol del empleado identificado para mostrar la vista correspondiente
     * @throws EmpleadoNoExisteException, si el nif no corresponde a un empleado
     * @throws ErrorSistemaException, si hay algun error interno
     * @throws CredencialesInvalidasException, si la password no coincide con el nif
     * @throws EmpleadoInactivoException, si el empleado no esta activo
     */
    public Puesto identificarUsuario(String nif, String password) throws EmpleadoNoExisteException, ErrorSistemaException, CredencialesInvalidasException, EmpleadoInactivoException{

        esNuloOVacio(nif);
        esNuloOVacio(password);
        
        String empleadoJSON = "";
        
        empleadoJSON = EmpleadoDAO.obtenerEmpleadoPorNif(nif, password);
          
        Empleado empleadoLogin = desdeJSON(empleadoJSON);
        
       
        if(!empleadoLogin.estaEnActivo()){
            
            throw new EmpleadoInactivoException("El usuario no es valido porque no esta activo");
        }
        
       
        
        Sesion.setIdEmpleadoActual(empleadoLogin.getIdentificadorEmpleado());
        return empleadoLogin.getPuesto();
        
        
    }
    
    /**
     * Comprueba si un texto es nulo o vacio
     * @param texto, String con el texto
     * @throws IllegalArgumentException si es nulo
     */
    private void esNuloOVacio (String texto){
        
        if (texto == null || texto.isEmpty()){
            
            throw new IllegalArgumentException("Campo de login nulo o vacio");
        }
        
    }
    

    /**
     * Devuelve un empleado a partir de un json
     * @param json, string con el JSON
     * @return objeto empleado al que corresponde el json
     */
    private Empleado desdeJSON(String json) {
        
        JsonReaderFactory factory = Json.createReaderFactory(null);
        
        try (JsonReader reader = factory.createReader(new StringReader(json))) {
            
            JsonObject empleadoJSON = reader.readObject();
            
            
            int idPersonaBD = empleadoJSON.getInt("id_persona_bd");
            String nombre = empleadoJSON.getString("nombre");
            String apellidos = empleadoJSON.getString("apellidos");
            int telefono = empleadoJSON.getInt("telefono");
            
            IdEmpleado idEmpleado = new IdEmpleado("EMP", idPersonaBD);
            
            Nif nif = new Nif(empleadoJSON.getString("nif").trim());
            
            Direccion direccion = 
                new Direccion(
                    empleadoJSON.getString("via"),
                    empleadoJSON.getInt("numero"),
                    empleadoJSON.getInt("piso"),
                    empleadoJSON.getString("puerta"),
                    empleadoJSON.getInt("cp"),
                    empleadoJSON.getString("localidad"),
                    empleadoJSON.getString("provincia")
                );
            
        
            LocalDate fechaInc = LocalDate.parse(empleadoJSON.getString("fecha_inc"));
            String fechaFinStr = empleadoJSON.getString("fecha_fin");
            LocalDate fechaFin = (fechaFinStr.isEmpty()) ? null : LocalDate.parse(fechaFinStr);
           
            Vinculacion vinc = Vinculacion.valueOf(empleadoJSON.getString("vinculacion"));
            Disponibilidad disp = Disponibilidad.valueOf(empleadoJSON.getString("disponibilidad"));
            
            
            String tipoPuesto = empleadoJSON.getString("tipo_puesto");
            Puesto puesto = null;
            
            
            if ("TECNICO".equals(tipoPuesto)) {
                puesto = new Tecnico(); 
            } else if ("COORDINADOR".equals(tipoPuesto)) {
                puesto = new Coordinador();
            } else {
                puesto = new Supervisor();
            }
            
            Persona persona=null;
            persona = new Persona(nombre,apellidos,telefono,direccion,nif);
            
            DatosFechaTrabajo fechas=null;
            fechas=new DatosFechaTrabajo(fechaInc,fechaFin);
            
            return new Empleado(
                persona,
                puesto, 
                empleadoJSON.getString("email"),
                fechas,
                idEmpleado, 
                disp, 
                vinc
            );
        }
    }
}
