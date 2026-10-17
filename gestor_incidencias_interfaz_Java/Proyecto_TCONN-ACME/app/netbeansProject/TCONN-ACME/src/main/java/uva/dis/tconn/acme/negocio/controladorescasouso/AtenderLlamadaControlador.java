/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.controladorescasouso;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

import uva.dis.tconn.acme.negocio.modelos.Contrato;
import uva.dis.tconn.acme.negocio.modelos.Coordinador;
import uva.dis.tconn.acme.negocio.modelos.Empleado;
import uva.dis.tconn.acme.negocio.modelos.LlamadaCorrecta;
import uva.dis.tconn.acme.negocio.modelos.LlamadaInconsistente;
import uva.dis.tconn.acme.negocio.modelos.LlamadaIncorrecta;
import uva.dis.tconn.acme.persistencia.daos.ClienteDAO;
import uva.dis.tconn.acme.persistencia.daos.ContratoDAO;
import uva.dis.tconn.acme.persistencia.daos.LlamadaDAO;
import uva.dis.tconn.acme.servicioscomunes.Sesion;
import uva.dis.tconn.acme.servicioscomunes.datatypes.Direccion;
import uva.dis.tconn.acme.servicioscomunes.datatypes.IdContrato;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ClienteNoExisteException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ContratoNoValidoException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.DatosContratoIncorrectosException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;

/**
 *
 * Controlador para el caso de uso Atender Llamada
 * 
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */

public class AtenderLlamadaControlador {
    private String[] datosJsons = {"fecha", "numero_telefono", "hora", "duracion", "id_coordinador", "numero_contrato"};

    private LocalDate fecha;
    private LocalTime horaInicio;
    private int telefonoLlamada;
    private Coordinador coordinadorActual;
    private String nifCifCliente;
    private Contrato contratoAsociado;
    private int idCliente;

    public AtenderLlamadaControlador() {
        Empleado empleadoCoordinador = new Empleado(Sesion.getInstancia().getIdEmpleadoActual());
        coordinadorActual = new Coordinador(null, empleadoCoordinador);
    }

    /**
     * Registra el inicio de una llamada, asignando la fecha y hora actual, y
     * validando el número de teléfono
     * 
     * @param telefono Número de teléfono desde el que se esta recbiendo la llamada
     * 
     */
    public void registrarInicioLlamada(int telefono) {
        this.fecha = LocalDate.now();
        this.horaInicio = LocalTime.now();

        this.telefonoLlamada = telefono;
    }

    public void comprobarDatosContrato(String nContrato, int telef, String nifCif) throws ContratoNoValidoException, ErrorSistemaException{
        if(nContrato == null || nContrato.isEmpty()) {
            throw new IllegalArgumentException("El número de contrato no puede ser nulo o vacío");
        }
        if(telef == 0) {
            throw new IllegalArgumentException("El número de teléfono no puede ser cero");
        }
        if(nifCif == null || nifCif.isEmpty()) {
            throw new IllegalArgumentException("El NIF no puede ser nulo o vacío");
        }

        String contratoJson = ContratoDAO.obtenerContratoPorNumero(nContrato, nifCif);

        if (contratoJson == null) {
            
            registraLlamadaIncorrecta();
            
            throw new ContratoNoValidoException("No existe un contrato con el número indicado.");
        }
        
        this.nifCifCliente = nifCif;
        this.contratoAsociado = crearContratoDesdeJson(contratoJson);
    }

    public void comprobarDatosCliente(String nombre, String telefonoContacto, Direccion direccion) throws ErrorSistemaException, ClienteNoExisteException, DatosContratoIncorrectosException{
        if(nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede ser nulo o vacío");
        }
        if(telefonoContacto == null || telefonoContacto.isEmpty()) {
            throw new IllegalArgumentException("El teléfono de contacto no puede ser nulo o vacío");
        }
        if(direccion == null) {
            throw new IllegalArgumentException("La dirección no puede ser nula");
        }

        String clienteJson = ClienteDAO.obtenerClientePorNifCif(nifCifCliente);

        if (clienteJson == null) {
            throw new ClienteNoExisteException("No existe un cliente con el NIF/CIF indicado");
        }

        JsonObject json;
        try (JsonReader reader = Json.createReader(new StringReader(clienteJson))) {
            json = reader.readObject();
        } catch (Exception e) {
            throw new ErrorSistemaException("Error al parsear el JSON del cliente: " + e.getMessage(), e);
        }

        if(!json.getString("nombre").equalsIgnoreCase(nombre)){
                throw new DatosContratoIncorrectosException("Error: El nombre o razón social indicado es incorrecto.");
            }

            if (!json.getString("telefono").equals(telefonoContacto)) {
                throw new DatosContratoIncorrectosException("Error: El teléfono de contacto indicado es incorrecto.");
            }
            
            if (!json.getString("nombre_via").equalsIgnoreCase(direccion.getNombreDeLaVia()) || 
                json.getInt("numero") != direccion.getNumero() ||
                json.getInt("piso") != direccion.getPiso() ||
                !json.getString("puerta").equals(direccion.getPuerta()) ||
                json.getInt("codigo_postal") != direccion.getCodigoPostal() ||
                !json.getString("localidad").equalsIgnoreCase(direccion.getLocalidad()) ||
                !json.getString("provincia").equalsIgnoreCase(direccion.getProvincia())) {
            
                throw new DatosContratoIncorrectosException("Error: La dirección de ubicación no coincide.");
            }

            LlamadaCorrecta llamadaCorrecta = new LlamadaCorrecta(fecha, telefonoLlamada, horaInicio, calcularDuracionLlamada(), coordinadorActual, contratoAsociado);  
            LlamadaDAO.registrarLlamadaCorrecta(crearJsonLlamadaCorrecta(llamadaCorrecta));

    }
    
    /**
     * Registra una llamada Incorrecta en la base de datos
     * @throws ErrorSistemaException 
     */
    public void registraLlamadaIncorrecta() throws ErrorSistemaException{
        
        int duracionLlamada = calcularDuracionLlamada();
        
        LlamadaIncorrecta llamadaIncorrecta = new LlamadaIncorrecta(fecha, telefonoLlamada, horaInicio, duracionLlamada, coordinadorActual);
        
        LlamadaDAO.registrarLlamadaIncorrecta(crearJsonLlamadaIncorrecta(llamadaIncorrecta));
        
           
    }
    
    /**
     * Registra una llamada inconsistente en la base de datos
     * @throws ErrorSistemaException 
     */
    public void registraLlamadaInconsistente(String inconsistencia) throws ErrorSistemaException{
        
        int duracionLlamada = calcularDuracionLlamada();
        
        LlamadaInconsistente llamadaInconsistente = new LlamadaInconsistente(fecha, telefonoLlamada, horaInicio, duracionLlamada, coordinadorActual, contratoAsociado, inconsistencia);
        
        LlamadaDAO.registrarLlamadaInconsistente(crearJsonLlamadaInconsistente(llamadaInconsistente));
        
    }
    
    private String crearJsonLlamadaIncorrecta(LlamadaIncorrecta llamadaIncorrecta) throws ErrorSistemaException {


        JsonObject llamadaJSON = Json.createObjectBuilder()
                .add(datosJsons[0], llamadaIncorrecta.getFecha().toString())
                .add(datosJsons[1], llamadaIncorrecta.getNumeroTelefono())
                .add(datosJsons[2], llamadaIncorrecta.getHora().toString())
                .add(datosJsons[3], llamadaIncorrecta.getDuracion())
                .add(datosJsons[4], llamadaIncorrecta.getCoordinadorLlamada().getIdEmpleado().getNumero())
                .build();

        try (StringWriter stringWriter = new StringWriter();
                JsonWriter writer = Json.createWriter(stringWriter)) {
            
            writer.writeObject(llamadaJSON);
            return stringWriter.toString();
            
        } catch (IOException e) {
            throw new ErrorSistemaException("Error al construir el JSON de la llamada incorrecta", e);
        }
    }

    private Contrato crearContratoDesdeJson(String contratoJson) {
        try (JsonReader reader = Json.createReader(new StringReader(contratoJson))) {
            JsonObject json = reader.readObject();

            //IdContrato
            String idContratoStr = json.getString("numero_contrato");
            IdContrato idContrato = new IdContrato(idContratoStr.substring(0, 2), 
                                    idContratoStr.substring(2,5), 
                                    Integer.parseInt(idContratoStr.substring(5))); 

            // Fechas
            LocalDate fechaInicio = LocalDate.parse(json.getString("fecha_inicio"));
            
            String fechaFinStr = json.getString("fecha_fin", "");
            LocalDate fechaFin = null;
            if (!fechaFinStr.isEmpty()) {
                fechaFin = LocalDate.parse(fechaFinStr); 
            }

            //Estado
            boolean activo = json.getBoolean("activo");

            //Ubicacion
            String nombreVia = json.getString("nombre_via");
            int numero = json.getInt("numero");
            
            int piso = json.getInt("piso"); 
            String puerta = json.getString("puerta");
            
            int cp = json.getInt("codigo_postal");
            String localidad = json.getString("localidad");
            String provincia = json.getString("provincia");
            
            Direccion ubicacion = new Direccion(nombreVia, numero, piso, puerta, cp, localidad, provincia);

            this.idCliente = json.getInt("id_cliente");
            return new Contrato(idContrato, fechaInicio, fechaFin, ubicacion, activo);

        } catch (Exception e) {
            throw new IllegalArgumentException("Error al parsear el JSON del contrato: " + e.getMessage(), e);
        }
    }

   private String crearJsonLlamadaInconsistente(LlamadaInconsistente llamada) throws ErrorSistemaException {
    
        JsonObject llamadaJSON = Json.createObjectBuilder()
            .add(datosJsons[0], llamada.getFecha().toString())
            .add(datosJsons[1], llamada.getNumeroTelefono())
            .add(datosJsons[2], llamada.getHora().toString())
            .add(datosJsons[3], llamada.getDuracion())
            .add(datosJsons[4], llamada.getCoordinadorLlamada().getIdEmpleado().getNumero())
            .add(datosJsons[5], llamada.getContrato().getIdContrato().toString())
            .add("datos_inconsistentes", llamada.getDatosInconsistentes())
            .build();

            try (java.io.StringWriter stringWriter = new java.io.StringWriter();
            javax.json.JsonWriter writer = javax.json.Json.createWriter(stringWriter)) {
            
            writer.writeObject(llamadaJSON);
            return stringWriter.toString();
            
        } catch (IOException e) {
            throw new ErrorSistemaException("Error al construir el JSON de la llamada inconsistente", e);
        }
    }

    private int calcularDuracionLlamada() {
        return (int) ChronoUnit.MINUTES.between(horaInicio, LocalTime.now());
    }

    private String crearJsonLlamadaCorrecta(LlamadaCorrecta llamadaCorrecta) throws ErrorSistemaException {
        JsonObject json = Json.createObjectBuilder()
            .add(datosJsons[0], llamadaCorrecta.getFecha().toString())
            .add(datosJsons[1], llamadaCorrecta.getNumeroTelefono())
            .add(datosJsons[2], llamadaCorrecta.getHora().toString())
            .add(datosJsons[3], llamadaCorrecta.getDuracion())
            .add("id_cliente", this.idCliente)
            .add(datosJsons[4], llamadaCorrecta.getCoordinadorLlamada().getIdEmpleado().getNumero())
            .add(datosJsons[5], llamadaCorrecta.getContrato().getIdContrato().toString())
            .build();

        try (StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter)) {
            writer.writeObject(json);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new ErrorSistemaException("Error al construir la lista de incidencias JSON",e);
        }
    }
}
