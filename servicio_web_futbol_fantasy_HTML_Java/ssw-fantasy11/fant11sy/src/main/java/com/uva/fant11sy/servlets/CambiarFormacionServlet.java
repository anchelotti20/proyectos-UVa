/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.servlets;

import com.uva.fant11sy.daos.EquipoDAO;
import com.uva.fant11sy.modelo.Equipo;
import com.uva.fant11sy.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Controla los cambios de formacion y actualiza la DB
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class CambiarFormacionServlet extends HttpServlet {
    
    /**
     * Registra el cambio de formacion y llama al DAO para actualizar la base de datos y devolver el control a EquipoServlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String nuevaFormacion = request.getParameter("nueva");
        
        if (nuevaFormacion == null || nuevaFormacion.isEmpty()) {
            
            response.sendRedirect("EquipoServlet");
            
            return;
        }
        
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        
        Equipo miEquipo = EquipoDAO.obtenerEquipoUsuario(usuarioLogueado.getIdUsuario());
        
        if(miEquipo != null){
            
            EquipoDAO.actualizarFormacion(miEquipo.getIdEquipo(), nuevaFormacion);
            
        }
        
        response.sendRedirect("EquipoServlet");
    }
    
}
