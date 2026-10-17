package com.uva.fant11sy.servlets;

import com.uva.fant11sy.daos.PartidoDAO;
import com.uva.fant11sy.modelo.Partido;

import java.io.IOException;

import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;



/**
 * Controla la pagina del calendario de partidos por jornadas
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class CalendarioServlet extends HttpServlet {
    
    
    /**
     * Recoge la jornada introducida en el filtro y devuelve los partidos de esa jornada
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
       
        String jParam = request.getParameter("jornada");
        
        int jornadaVer;
        
        if(jParam == null || jParam.isEmpty()){
            
            jornadaVer = PartidoDAO.obtenerJornadaActual();
            
        }else{
            
            jornadaVer = Integer.parseInt(jParam);
        }
        
        List<Partido> partidosJornada = PartidoDAO.obtenerPartidosPorJornada(jornadaVer);

       
        request.setAttribute("listaPartidos", partidosJornada);
        request.setAttribute("jornadaMostrada", jornadaVer);
        request.getRequestDispatcher("calendario.jsp").forward(request, response);
    }
}
