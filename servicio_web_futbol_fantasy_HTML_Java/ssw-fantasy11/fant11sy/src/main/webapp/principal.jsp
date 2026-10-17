<%@page contentType= "text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<!-- Proyecto realizado por el grupo 12-->
<!--Pantalla principal, todas las pantallas tienen conexion con esta a traves de la imagen del logo-->
<html lang="es">
<head>
    <!-- Vincualacion con el estilo de CSS para el estilo -->
    <link href="styles/main.css" rel="stylesheet" type ="text/css">

    <link href="styles/principal.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/icono.jpeg" type="images/jpeg">
    <!-- Caracters que se usan -->
    <meta charset="UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Titulo de la pagina -->
    <title>Fant11sy - LIGA 2026</title>
   
</head>
<body><!-- Todo lo que se ve de la pagina -->

    <c:if test="${not empty mensaje}">
        <div style="background-color: #d4edda; color: #155724; padding: 15px; margin: 10px; border: 1px solid #c3e6cb; border-radius: 4px; text-align: center; font-weight: bold;">
            ${mensaje}
        </div>
    </c:if>
    
    <header><!-- Se trata de la cabecera de la pagina -->
        <img src="images/logo.png" alt="Logo" class="logo"><!-- Se inserta la imagen del logo -->
        <h1>LIGA 2026</h1><!-- Encabezado de la pagina -->
        <a href="LogoutServlet" class="btn-logout">CERRAR SESIÓN</a><!-- Vinculamos el boton de cerrar sesion con la pagina de login -->
    </header>
    <!-- Contenedor para almacenar los puestos de la clasifiacion-->
    <div class="contenedor"><!--Creacion de la clase contenedor-->
    
        <div class="contenedor-puesto">
            <span>${usuarioLogueado.puesto != 0 ? usuarioLogueado.puesto : "-"}º PUESTO</span><!-- Escribimos el puesto y el numero de puntos en linea -->
            <span>${usuarioLogueado.puntos} PUNTOS</span>
        </div>
        <!-- Vinculacmos el boton del equipo con la pagina que muestra el equipo -->
        <a href="EquipoServlet" class="btn-equipo">MI EQUIPO</a>
        <!-- Vinculamos el boton de calendario con la pagina que muestra el calendario -->
        <a href="CalendarioServlet" class="partidos-boton">
            <h2>PARTIDOS</h2>
            <span class="jornada-sub">JORNADA ${jornadaActual}</span><!-- Informacion sobre la jornada en la que nos encontramos -->
            
            <!-- Creacion de la tabla de partidos -->
            <table class="tabla-partidos">
                <c:forEach var="partido" items="${listaPartidos}">
                    <tr>
                        <td class="local">
                            
                            <img src="${not empty partido.fotoLocal ? partido.fotoLocal : 'images/escudo_por_defecto.png'}" 
                                        class="escudo-calendario" alt="Escudo ${partido.nombreLocal}">
                            
                            
                            ${partido.nombreLocal}
                        
                        
                        
                        </td>
                        <td class="vs">
                            
                            <c:choose>
                                
                                <c:when test="${not empty partido.ganador}">
                                    
                                    ${partido.golesLocal} - ${partido.golesVisitante}
                                    
                                </c:when>
                                    
                                <c:otherwise>
                                    
                                    - vs -
                                    
                                </c:otherwise>
                                
                            </c:choose>
                               
                        </td>
                        <td class="visitante">
                            
                            ${partido.nombreVisitante}
                            <img src="${not empty partido.fotoVisitante ? partido.fotoVisitante : 'images/escudo_por_defecto.png'}" 
                                     class="escudo-calendario" alt="Escudo ${partido.nombreVisitante}">
                        
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </a>

    </div>

</body>
</html>

