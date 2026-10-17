package com.uva.fant11sy.servlets;

import com.uva.fant11sy.modelo.GestionSesiones;
import com.uva.fant11sy.modelo.Usuario;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Maneja el boton de cierre de sesion de la pagina principal, quitando la sesion del usuario
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class LogoutServlet extends HttpServlet {
    
    /**
     * Recoge la llamada del boton de cerrar sesion y elimina la sesion del usuario
     * @param request, llamada
     * @param response, respuesta
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession(false);

        if (sesion != null) {
            
            Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioLogueado");
            
            if (usuarioActual != null) {
                
                GestionSesiones.getInstance().eliminarUsuario(usuarioActual.getUsuario());
            }
            
            sesion.invalidate();
        }

        response.sendRedirect("login.jsp");
    }
}