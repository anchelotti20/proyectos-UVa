/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.servlets;

import com.uva.fant11sy.daos.EquipoDAO;
import com.uva.fant11sy.daos.MercadoDAO;
import com.uva.fant11sy.modelo.Equipo;
import com.uva.fant11sy.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Controla el sistema de fichajes y reemplazo de jugadores
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class FicharServlet extends HttpServlet{
    
    /**
     * Realiza el fichaje del nuevo jugador cambiandolo por el antiguo
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
        
        int idJugadorNuevo = Integer.parseInt(request.getParameter("idJugador"));
        int idJugadorViejo = Integer.parseInt(request.getParameter("idReemplazar"));
        int slot = Integer.parseInt(request.getParameter("slot"));
        
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        
        Equipo miEquipo = EquipoDAO.obtenerEquipoUsuario(usuarioLogueado.getIdUsuario());
        
        if(miEquipo != null){
            
            
            
            MercadoDAO.ficharJugador(miEquipo.getIdEquipo(), idJugadorViejo, idJugadorNuevo, slot);
            
        }
        
        response.sendRedirect("EquipoServlet");
    }
}
