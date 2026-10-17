/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.servlets;

import com.uva.fant11sy.daos.ClubDAO;
import com.uva.fant11sy.modelo.Club;
import com.uva.fant11sy.modelo.Jugador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * Controla la pagina que muestra el listado de jugadores de un club
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class ClubServlet extends HttpServlet {
    
    /**
     * Muestra la lista con los jugadores del club
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String nombreClub = request.getParameter("nombre");
        if(nombreClub == null || nombreClub.isEmpty()){
            
            response.sendRedirect("PrincipalServlet");
            return;
        }
        
        List<Jugador> jugadoresClub = ClubDAO.obtenerJugadoresCLub(nombreClub);
        Club club = ClubDAO.obtenerClub(nombreClub);
        
 
        request.setAttribute("nombreClub", nombreClub);
        request.setAttribute("listaJugadores", jugadoresClub);
        request.setAttribute("escudoClub", club.getFoto());
        request.getRequestDispatcher("clubes.jsp").forward(request, response);
    }
}
