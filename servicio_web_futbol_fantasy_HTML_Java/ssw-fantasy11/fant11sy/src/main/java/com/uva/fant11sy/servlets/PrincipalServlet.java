package com.uva.fant11sy.servlets;

import com.uva.fant11sy.daos.PartidoDAO;
import com.uva.fant11sy.modelo.Partido;

import java.io.IOException;

import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Maneja la pagina principal y permite mostrar una previsualizacion de los partidos de la jornada actual
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */

public class PrincipalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        
        int jornadaActual = PartidoDAO.obtenerJornadaActual();
        
        
        List<Partido> listaPartidos = PartidoDAO.obtenerPartidosPorJornada(jornadaActual);
        
        
        request.setAttribute("listaPartidos", listaPartidos);
        request.setAttribute("jornadaActual", jornadaActual);
        
        
        request.getRequestDispatcher("principal.jsp").forward(request, response);
    }

}