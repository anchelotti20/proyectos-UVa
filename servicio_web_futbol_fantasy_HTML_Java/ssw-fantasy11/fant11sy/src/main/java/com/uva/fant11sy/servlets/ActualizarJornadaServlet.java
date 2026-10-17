package com.uva.fant11sy.servlets;

import com.uva.fant11sy.api.ActualizarResultadosAPI;
import com.uva.fant11sy.daos.ActuacionDAO;
import com.uva.fant11sy.daos.ClasificacionDAO;
import com.uva.fant11sy.daos.EquipoDAO;
import com.uva.fant11sy.daos.PartidoDAO;
import com.uva.fant11sy.daos.UsuarioDAO;
import com.uva.fant11sy.modelo.Equipo;
import com.uva.fant11sy.util.CalculoPuntos;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

public class ActualizarJornadaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        int jornadaJugada = PartidoDAO.obtenerJornadaActual() - 1;
        
        /**
        ActualizarResultadosAPI apiPuntos = new ActualizarResultadosAPI();
        apiPuntos.procesarJornada(LocalDate.now().toString());
        */
        
        Map<Integer, Integer> puntosJugadoresDB = ActuacionDAO.obtenerPuntosJugadoresPorJornada(jornadaJugada);
        
        if (puntosJugadoresDB != null && !puntosJugadoresDB.isEmpty()) {
           
            List<Equipo> equiposValidos = EquipoDAO.obtenerEquiposCompletos();
            
            for (Equipo equipo : equiposValidos) {
                
                int puntosTotalesEquipo = CalculoPuntos.obtenerPuntosDesdeDB(equipo.getPlantilla(), puntosJugadoresDB);
                
                
                ClasificacionDAO.guardarPuntuacionJornada(equipo.getIdUsuario(), puntosTotalesEquipo, jornadaJugada);
                
                
                UsuarioDAO.sumarPuntosUsuario(equipo.getIdUsuario(), puntosTotalesEquipo);
            }
            
            
            ClasificacionDAO.actualizarPuestosGlobales();
            
            request.setAttribute("mensaje", "¡Jornada " + jornadaJugada + " procesada con éxito! Históricos guardados y clasificaciones actualizadas.");
        } else {
            request.setAttribute("mensaje", "Atención: No se encontraron actuaciones válidas en la base de datos para la jornada " + jornadaJugada);
        }
        
       
        request.getRequestDispatcher("principal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}