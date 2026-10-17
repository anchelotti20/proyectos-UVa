package com.uva.fant11sy.servlets;

import com.uva.fant11sy.daos.ClasificacionDAO;

import com.uva.fant11sy.modelo.FilaClasificacion;

import java.io.IOException;

import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;


/**
 * Controla la pagina de clasificacion obteniendo cual mostrar
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class ClasificacionServlet extends HttpServlet {
    
    
    /**
     * LLama al DAO y ejecuta el metodo para obtener la clasificacion global o de la jornada
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
        
        
        String filtro = request.getParameter("filtro");
        
        if (filtro == null || filtro.isEmpty()){
            
            filtro = "Total";
        }

        List<FilaClasificacion> clasificacion;
        
        if(filtro.equals("Total")){
            
            clasificacion = ClasificacionDAO.obtenerClasificacionGlobal();
        
        }else{
            
            int jornada = Integer.parseInt(filtro);
            clasificacion = ClasificacionDAO.obtenerClasificacionJornada(jornada);
        }

        

        request.setAttribute("clasificacion", clasificacion);
        request.setAttribute("filtroActual", filtro);
        request.getRequestDispatcher("clasificacion.jsp").forward(request, response);
    }
}