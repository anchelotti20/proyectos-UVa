package com.uva.fant11sy.servlets;

import com.uva.fant11sy.daos.EquipoDAO;
import com.uva.fant11sy.modelo.Equipo;
import com.uva.fant11sy.modelo.Jugador;
import com.uva.fant11sy.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Controla la vista simplificada del equipo de otro usuario desde la clasificación
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
@WebServlet("/EquipoUsuarioServlet")
public class EquipoUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String nombreUsuarioRival = request.getParameter("nombre");

        if (nombreUsuarioRival == null || nombreUsuarioRival.trim().isEmpty()) {
            response.sendRedirect("ClasificacionServlet");
            return;
        }

        int idUsuarioRival = 0;
        String nombreLimpio = nombreUsuarioRival.trim().toLowerCase();
        
        if (nombreLimpio.equals("angel")) {
            idUsuarioRival = 1;
        } else if (nombreLimpio.equals("marcos")) {
            idUsuarioRival = 2;
        } else if (nombreLimpio.equals("jorge")) {
            idUsuarioRival = 3;
        } else if (nombreLimpio.equals("luis")) {
            idUsuarioRival = 4;
        }

        Equipo equipoRival = null;
        if (idUsuarioRival != 0) {
            try {
                equipoRival = EquipoDAO.obtenerEquipoUsuario(idUsuarioRival);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (equipoRival == null) {
            response.sendRedirect("ClasificacionServlet");
            return;
        }

        request.setAttribute("nombreRival", equipoRival.getNombre().toUpperCase());

        Map<String, Jugador> porteros = new HashMap<>();
        Map<String, Jugador> defensas = new HashMap<>();
        Map<String, Jugador> mediocentros = new HashMap<>();
        Map<String, Jugador> delanteros = new HashMap<>();

        if (equipoRival.getPlantilla() != null) {
            for (Jugador j : equipoRival.getPlantilla()) {
                int slot = j.getSlot();
                String slotString = String.valueOf(slot);

                if (slot == 1) {
                    porteros.put(slotString, j);
                } else if (slot > 10 && slot < 20) {
                    defensas.put(slotString, j);
                } else if (slot > 20 && slot < 30) {
                    mediocentros.put(slotString, j);
                } else if (slot > 30 && slot < 40) {
                    delanteros.put(slotString, j);
                }
            }
        }

        String formacion = equipoRival.getFormacion();
        int numeroDefensas = 4;
        int numeroMedios = 3;
        int numeroDelanteros = 3;

        if (formacion != null && !formacion.isEmpty()) {
            String[] partes = formacion.split("-");
            if (partes.length == 3) {
                numeroDefensas = Integer.parseInt(partes[0]);
                numeroMedios = Integer.parseInt(partes[1]);
                numeroDelanteros = Integer.parseInt(partes[2]);
            }
        }

        request.setAttribute("porterosFichados", porteros);
        request.setAttribute("defensasFichados", defensas);
        request.setAttribute("mediosFichados", mediocentros);
        request.setAttribute("delanterosFichados", delanteros);
        request.setAttribute("numDef", numeroDefensas);
        request.setAttribute("numMed", numeroMedios);
        request.setAttribute("numDel", numeroDelanteros);

        // 9. Despachar a la vista
        request.getRequestDispatcher("equipoUsuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}