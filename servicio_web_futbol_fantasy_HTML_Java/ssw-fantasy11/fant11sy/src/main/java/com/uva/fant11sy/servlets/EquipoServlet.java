/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uva.fant11sy.servlets;

import com.uva.fant11sy.daos.EquipoDAO;
import com.uva.fant11sy.modelo.Equipo;
import com.uva.fant11sy.modelo.Jugador;
import com.uva.fant11sy.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controla la pagina del equipo, mostrando los jugadores segun la alineacion que haya
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class EquipoServlet extends HttpServlet {
    
    /**
     * Muestra el equipo con los jugadores, cambia la alineacion y calcula los puntos bonus
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
        
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        
        int idUsuario = usuarioLogueado.getIdUsuario();
        
        Equipo miEquipo = EquipoDAO.obtenerEquipoUsuario(idUsuario);
        
        if (miEquipo == null) {
            
            response.sendRedirect("PrincipalServlet");
            return;
        }
        
        Map<String, Jugador> porteros = new HashMap<>();
        Map<String, Jugador> defensas = new HashMap<>();
        Map<String, Jugador> mediocentros = new HashMap<>();
        Map<String, Jugador> delanteros = new HashMap<>();
        
        for (Jugador j : miEquipo.getPlantilla()) {
            
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
        
        String formacion = miEquipo.getFormacion();
        
        String[] partes = formacion.split("-");
        
        int numeroDefensas = Integer.parseInt(partes[0]);
        int numeroMedios = Integer.parseInt(partes[1]);
        int numeroDelanteros = Integer.parseInt(partes[2]);
        
        
        
        Map<String, Integer> parejasNacionalidad = new HashMap<>();
        Map<String, Integer> parejasClub = new HashMap<>();
        
        for(Jugador j: miEquipo.getPlantilla()){
            
            String nacionalidad = j.getNacionalidad();
            String club = j.getNombreClub();
            
            if(nacionalidad != null && !nacionalidad.isEmpty()){
                
                parejasNacionalidad.put(nacionalidad, parejasNacionalidad.getOrDefault(nacionalidad, 0) + 1);
            }
            
            if(club != null && !club.isEmpty()){
                
                parejasClub.put(club, parejasClub.getOrDefault(club, 0) + 1);
            }
        }
        
        List<String> detallesPais = new ArrayList<>();
        
        int bonusPais = 0;
        
        for(Map.Entry<String, Integer> entrada: parejasNacionalidad.entrySet()){
            
            int cantidad = entrada.getValue();
            int parejas = cantidad / 2;
            
            if(parejas > 0){
                
                int puntos = parejas;
                bonusPais += puntos;
                detallesPais.add(entrada.getKey().toUpperCase() + " " + cantidad + "J. +" + puntos + "P");
                
            }else {
                
                detallesPais.add(entrada.getKey().toUpperCase() + " " + cantidad + " J.");
            }
        }
        
        List<String> detallesClub = new ArrayList<>();
        
        int bonusClub = 0;
        
        for(Map.Entry<String, Integer> entrada: parejasClub.entrySet()){
            
            int cantidad = entrada.getValue();
            int parejas = cantidad / 2;
            
            if(parejas > 0){
                
                int puntos = parejas * 2;
                bonusClub += puntos;
                detallesClub.add(entrada.getKey().toUpperCase() + " " + cantidad + "J. +" + puntos + "P");
                
            }else {
                
                detallesClub.add(entrada.getKey().toUpperCase() + " " + cantidad + " J.");
            }
        }
        
        int bonus = bonusPais + bonusClub;
        
        
        request.setAttribute("detallesPais", detallesPais);
        request.setAttribute("detallesClub", detallesClub);
        request.setAttribute("totalBonus", bonus);
        request.setAttribute("equipo", miEquipo);
        request.setAttribute("formacionActual", formacion);
        request.setAttribute("porterosFichados", porteros);
        request.setAttribute("defensasFichados", defensas);
        request.setAttribute("mediosFichados", mediocentros);
        request.setAttribute("delanterosFichados", delanteros);
        request.setAttribute("numDef", numeroDefensas);
        request.setAttribute("numMed", numeroMedios);
        request.setAttribute("numDel", numeroDelanteros);
        
        request.getRequestDispatcher("equipo.jsp").forward(request, response);
    }
    
    
    
}
