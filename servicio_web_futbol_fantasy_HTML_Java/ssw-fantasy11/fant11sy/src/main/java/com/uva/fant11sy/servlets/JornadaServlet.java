
/*package com.uva.fant11sy.servlets;


package com.uva.fant11sy.servlets;

import com.uva.fant11sy.api.CargarEquipos;
import com.uva.fant11sy.daos.ClasificacionDAO;
import com.uva.fant11sy.daos.EquipoDAO;
import com.uva.fant11sy.daos.UsuarioDAO;
import com.uva.fant11sy.modelo.Equipo;
import com.uva.fant11sy.modelo.Jugador;
import com.uva.fant11sy.modelo.Usuario;
import com.uva.fant11sy.util.CalculoPuntos;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;


public class JornadaServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuarioLogueado") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            
            String jornada=request.getParameter("jornada");
            
            if(jornada==null||jornada.isEmpty()){
                response.sendRedirect("principal.jsp");
                return;
            }
            
            int jornadaInt=Integer.parseInt(jornada);
            
            try{
                 Map<Integer, Integer> puntajeAPI=CargarEquipos.obtenerPuntosAPI(jornada);
                 List<Usuario> listaUsuarios = UsuarioDAO.obtenerLista();
                 
                 for(Usuario u: listaUsuarios){
                     Equipo equipo=EquipoDAO.obtenerEquipoUsuario(u.getIdUsuario());
                     int puntos=0;
                     
                     if(equipo != null && equipo.getPlantilla().size()==11){
                         puntos=CalculoPuntos.obtenerPuntos((List<Jugador>) equipo, puntajeAPI);
                     }
                     
                     ClasificacionDAO.actualizar(u.getIdUsuario(),jornada,puntos);
                     
                 }
            }catch(Exception e ){
                
            }
            
           
            
        }
    
    
    }

    
    /
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


*/