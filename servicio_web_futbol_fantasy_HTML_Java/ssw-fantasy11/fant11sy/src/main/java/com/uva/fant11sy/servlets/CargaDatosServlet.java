package com.uva.fant11sy.servlets;

import com.uva.fant11sy.api.CargarEquipos;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CargaDatosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Ejecutamos la lógica de carga
        CargarEquipos cargador = new CargarEquipos();
        cargador.cargarDatos();
        
        // Enviamos el mensaje de éxito
        request.setAttribute("mensaje", "Datos volcados correctamente desde SportsMonks");
        
        // Redirigimos a principal.jsp
        request.getRequestDispatcher("principal.jsp").forward(request, response);
    }
}