/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.interfaz.pares_vista_controlador.empleado;

import uva.dis.tconn.acme.interfaz.GestorVistas;
import uva.dis.tconn.acme.negocio.controladorescasouso.IdentificarUsuarioControlador;
import uva.dis.tconn.acme.negocio.modelos.Puesto;
import uva.dis.tconn.acme.servicioscomunes.excepciones.CredencialesInvalidasException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.EmpleadoInactivoException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.EmpleadoNoExisteException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;

/**
 * Controlador de la vista de identificación de usuario, se encarga de procesar los eventos generados en la vista y comunicarse con el controlador de dominio para realizar el proceso de login
 * @author Angel Antolin
 * @author Elisa Martinez
 * @author Marcos Gomez
 */
public class IdentificarUsuarioVistaControlador {

    private final IdentificarUsuarioVista vista;

    /**
     * Constructor del controlador, recibe la vista asociada para poder interactuar con ella
     * @param vista La vista de identificación de usuario asociada a este controlador
     */
    public IdentificarUsuarioVistaControlador(IdentificarUsuarioVista vista) {
        this.vista = vista;
    }
    
    /**
     * Procesa el evento de acceso del usuario
     */
    public void procesaEventoAcceder(){
            
        String nif = vista.getNIF();
        String password = vista.getPassword();
        
        if(!esVacio(nif) && !esVacio(password)){
            
            try{

                IdentificarUsuarioControlador crtlDominio = new IdentificarUsuarioControlador();

                Puesto puesto = crtlDominio.identificarUsuario(vista.getNIF(),vista.getPassword());
                
                String rolIdentificador = puesto.getRolIdentificador();
                
                switch(rolIdentificador){
                    
                    case "TECNICO":
                        
                        GestorVistas.getInstancia().mostrarVistaCambioDisponibilidad();
                        break;
                    
                    case "COORDINADOR":
                        GestorVistas.getInstancia().mostrarVistaAtenderLlamada();
                        vista.mostrarMensajeLogin("Login correcto con rol: " + rolIdentificador);
                        break;
                    
                    case "SUPERVISOR":
                        GestorVistas.getInstancia().mostrarVistaConsultarIncidencias();
                        vista.mostrarMensajeLogin("Login correcto con rol: " + rolIdentificador);
                        break;
                    
                    default:
                        
                        vista.mostrarMensajeError("Rol desconocido");
                        break;
                }
                

            } catch (EmpleadoNoExisteException e){

                vista.mostrarMensajeError("Usuario incorrecto");

            } catch (CredencialesInvalidasException e){

                vista.mostrarMensajeError("Contraseña incorrecta");

            }catch (EmpleadoInactivoException e){

                vista.mostrarMensajeError("Empleado no activo");

            } catch (ErrorSistemaException e) {
                
                vista.mostrarMensajeError("Error en el sistema");
                
            } 
        
        }else{
            
            vista.mostrarMensajeError("Credenciales vacías");
            
        }
    }
    
    
    private boolean esVacio (String texto){
        
        return texto.isEmpty();
        
    }
    
}
