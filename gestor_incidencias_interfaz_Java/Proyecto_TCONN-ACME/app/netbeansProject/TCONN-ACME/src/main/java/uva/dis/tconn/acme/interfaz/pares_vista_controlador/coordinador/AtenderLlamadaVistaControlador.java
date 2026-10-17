/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.interfaz.pares_vista_controlador.coordinador;

import uva.dis.tconn.acme.interfaz.GestorVistas;
import uva.dis.tconn.acme.negocio.controladorescasouso.AtenderLlamadaControlador;
import uva.dis.tconn.acme.servicioscomunes.datatypes.Direccion;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ClienteNoExisteException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ContratoNoValidoException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.DatosContratoIncorrectosException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;

/**
 * Controlador de la vista de atender llamadas, se encarga de procesar
 * los eventos generados en la vista y comunicarse con el controlador de dominio
 * para realizar el proceso de atender llamadas
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class AtenderLlamadaVistaControlador {
    private static final String ERRORSISTEMA = "Error del sistema";
    
    private AtenderLlamadaVista vista;
    private AtenderLlamadaControlador controlador;
    
    /**
     * Inicializa el controlador de la vista
     * @param vista, vista asociada al controlador
     */
    public AtenderLlamadaVistaControlador(AtenderLlamadaVista vista){
        
        this.vista = vista;        
        
    }
    
    /**
     * procesa el evento cuando se da al boton de recibir una llamada
     * @param telefono, el telefono desde el que se llama
     */
    public void procesaEventoRecibirLlamada(int telefono){
        
        this.controlador = new AtenderLlamadaControlador();
        controlador.registrarInicioLlamada(telefono);
        
        
    }
    
    /**
     * Procesa el evento cuando el que llama no aporta datos de contratos
     */
    public void procesaEventoSinDatos1(){
        
        try{
            
            controlador.registraLlamadaIncorrecta();
        
        }catch(ErrorSistemaException e){
            
            vista.mostrarMensajeError(ERRORSISTEMA);
            
        }
        
    }
    
    /**
     * Procesa el evento de dar al boton confirmar los datos de la llamada
     * @return true si ha ido bien, false excepcion
     */
    public boolean procesaEventoConfirmarDatosLlamada(){
        
        String nContrato = vista.getNumeroContrato();
        
        String telefonoString = vista.getTelefonoContrato();
        
        String nif = vista.getNif();
        
        int telefono;
        
        try{
            
            telefono = Integer.parseInt(telefonoString);
            
            controlador.comprobarDatosContrato(nContrato, telefono, nif);
            
            return true;
            
        }catch(NumberFormatException e){
            
            vista.mostrarMensajeError("Telefono Incorrecto");
           
            
        }catch(ContratoNoValidoException e){
            
            vista.mostrarMensajeError("El contrato no es valido");
            
            
        }catch(ErrorSistemaException e){
            
            vista.mostrarMensajeError(ERRORSISTEMA);
            
        }catch(IllegalArgumentException e){
            
            vista.mostrarMensajeError(e.getMessage());
            
        }
        
        
        
        return false;
        
    }
    
    /**
     * Procesa el evento de confirmar los datos de los clientes
     * @return true si ha ido bien, false si no
     */
    public boolean procesaEventoConfirmarDatosClientes(){
        
        String nombreRazon = vista.getNombre();
        
        String telefonoContactoString = vista.getTelefonoContacto();
        
        String nombreVia = vista.getNombreVia();
        
        String numeroString = vista.getNumeroDireccion();
        
        String pisoString = vista.getPiso();
        
        String puerta = vista.getPuerta();
        
        String codigoString = vista.getCodPostal();
        
        String localidad = vista.getLocalidad();
        
        String provincia = vista.getProvincia();
        
        try{
            
            
            int piso = Integer.parseInt(pisoString);
            int numero = Integer.parseInt(numeroString);
            int codigo = Integer.parseInt(codigoString);
            
            Direccion direccion = new Direccion(nombreVia, numero, piso, puerta, codigo, localidad, provincia);
            
            controlador.comprobarDatosCliente(nombreRazon, telefonoContactoString, direccion);
            
            
            return true;
            
        }catch(NumberFormatException e){
            
            vista.mostrarMensajeError("Formato de datos invalidos");
            
        }catch (ClienteNoExisteException | DatosContratoIncorrectosException | IllegalArgumentException e) {
            vista.mostrarMensajeError(e.getMessage());
        } catch (ErrorSistemaException ex) {
            vista.mostrarMensajeError(ERRORSISTEMA);
        }
        return false;
        
    }
    
    /**
     * Obtiene los datos inconsistentes y guarda la llamada inconsistente
     */
    public void procesaEventoGuardarInconsistencia() {
        
        String inconsistencia = vista.getInconsistencia();
        
        try {
            controlador.registraLlamadaInconsistente(inconsistencia);
        } catch (ErrorSistemaException ex) {
            
            vista.mostrarMensajeError(ERRORSISTEMA);
        }
        
        
    }
    
    /**
     * Muestra la vista sin imlementar de registro de incidencia
     */
    public void procesaEventoRegistrarIncidencia(){
        
        GestorVistas.getInstancia().mostrarVistaRegistrarIncidencia();
        
        
    }
}
