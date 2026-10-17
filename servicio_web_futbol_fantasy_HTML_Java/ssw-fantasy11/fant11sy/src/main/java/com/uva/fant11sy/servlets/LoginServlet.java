/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.uva.fant11sy.servlets;

import com.uva.fant11sy.daos.UsuarioDAO;
import com.uva.fant11sy.modelo.GestionSesiones;
import com.uva.fant11sy.modelo.Usuario;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Servlet que controla el inicio de sesion
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class LoginServlet extends HttpServlet {
    
    
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
        
        
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena"); 

        
        if (esNuloOVacio(usuario) || esNuloOVacio(contrasena)) {
            
            request.setAttribute("mensajeError", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return; 
        }

        Usuario usuarioActual = UsuarioDAO.login(usuario, contrasena);
        
        
        if(usuarioActual != null){
            
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("usuarioLogueado", usuarioActual);
            
            GestionSesiones.getInstance().registrarUsuario(usuarioActual.getUsuario(), sesion);
            
            response.sendRedirect("PrincipalServlet");
            return;
            
        
        } else {
           
            request.setAttribute("mensajeError", "Usuario o contraseña incorrectos");
        }
            
        request.getRequestDispatcher("login.jsp").forward(request, response);
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