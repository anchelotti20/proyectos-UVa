package com.uva.fant11sy.servlets;

import com.uva.fant11sy.daos.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * Servlet que maneja el registro de un nuevo usuario
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class RegistroServlet extends HttpServlet {
    
    
    /**
     * Recoge los datos del formulario de registro, crea el usuario aniadiendolo a la liga y le asocia un equipo
     * @param request lo que recibe
     * @param response respuesta al jsp
     * @throws ServletException 
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String nombre = request.getParameter("nombre");
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena"); 

        
        if (esNuloOVacio(nombre) || esNuloOVacio(usuario) || esNuloOVacio(contrasena)) {
            
            request.setAttribute("mensajeError", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return; 
        }

        
        boolean registroExito = UsuarioDAO.registrarUsuario(nombre, usuario, contrasena);
        
        if(registroExito){
            
            request.setAttribute("mensajeExito", "¡Registro completado!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            
        }else{
            
            request.setAttribute("mensajeError", "El nombre de usuario ya está en uso o hubo un problema en el servidor.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
        
    }
    
    
    /**
     * Comprueba que los atributos esten correctos
     * @param texto texto a comprobar
     * @return true si esta bien, false si no
     */
    private boolean esNuloOVacio(String texto){
        
        boolean comprobar = false;
        
        if (texto == null || texto.isEmpty()){
            
            comprobar = true;
        }
        
        return comprobar;
    }
}
