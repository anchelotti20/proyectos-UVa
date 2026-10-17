/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.servlets;

import com.uva.fant11sy.daos.EquipoDAO;
import com.uva.fant11sy.daos.MercadoDAO;
import com.uva.fant11sy.modelo.Equipo;
import com.uva.fant11sy.modelo.Jugador;
import com.uva.fant11sy.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.util.List;

/**
 * Controla la pagina del mercado y muestra los jugadores
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class MercadoServlet extends HttpServlet{
    
    /**
     * Obtiene los jugadores por la posicion y los muestra
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
        
        
        String posicion = request.getParameter("posicion");
        
        String jugadorViejo = request.getParameter("reemplazar");
        
        int idJugadorViejo;
        
        if(jugadorViejo != null){
            
            idJugadorViejo = Integer.parseInt(jugadorViejo);
        
        }else{
            
            idJugadorViejo = 0;
        }
        
        if (posicion == null || posicion.isEmpty()) {
            
            response.sendRedirect("EquipoServlet");
            return;
        }
        
        List<Jugador> jugadores = MercadoDAO.obtenerJugadoresPorPosicion(posicion);
        
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        Equipo miEquipo = EquipoDAO.obtenerEquipoUsuario(usuarioLogueado.getIdUsuario());
        
        
        if (miEquipo != null) {
            
            for (Jugador miJugador : miEquipo.getPlantilla()) {
                
                jugadores.removeIf(j -> j.getId() == miJugador.getId());
            }
        }
        
        int slot = Integer.parseInt(request.getParameter("slot"));
        
        request.setAttribute("slotSeleccionado", slot);
        request.setAttribute("posicionFiltro", posicion);
        request.setAttribute("jugadoresMercado", jugadores);
        request.setAttribute("idReemplazar", idJugadorViejo);
        
        request.getRequestDispatcher("mercado.jsp").forward(request, response);
        
        
    }
    
}
