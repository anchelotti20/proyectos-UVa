<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<!--Proyecto realizado por el grupo 12-->
<!--Pantalla que muestra todos los jugadores disponibles que se podran elegir antes de una jornada-->
<html lang="es">
<head>
    <!--Vinculamos los estilos CSS-->
    <link href="styles/main.css" rel="stylesheet" type ="text/css">

    <link href="styles/listaJugadores.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/icono.jpeg" type="images/jpeg">
    <meta charset="UTF-8">
    
    <title>Fant11sy - Mercado de ${posicionFiltro}</title>
   
</head>
<body>

<header>
    <!--Vinculacion del logo con su pagina correspondiente(inicial)-->
    <a href="PrincipalServlet"><img src="images/logo.png" alt="logo.png" class="logo"></a>
    
    <h1>MERCADO ${posicionFiltro.toUpperCase()}S</h1><!--Titulo-->
</header>

<main class="contenedor">
    <!--Creamos contenedor para poder elegir en el desplegable las posiciones de los jugadores-->
    <div class="controles-superior">
        <a href="EquipoServlet" class="btn-volver" style="text-decoration: none; font-weight: bold; color: #1e3d59;">&lt; Volver al Campo</a>
    </div>

    <div class="tabla-contenedor">
        <table>
            <thead>
                <tr><!--Filas-->
                    <th>POS</th>
                    <th>FOTO</th>
                    <th>JUGADOR</th>
                    <th>CLUB</th>
                    <th>NACIONALIDAD</th>
                    <th>ACCION</th>
                </tr>
            </thead>
            <tbody>
                
                <c:choose>
                    <c:when test="${not empty jugadoresMercado}">
                        <c:forEach var="j" items="${jugadoresMercado}">
                            <tr>
                                <td class="col-pos"><span class="tag-pos">${j.posicion.substring(0,3).toUpperCase()}</span></td>
                                <td class="col-foto">
                                    <img src="${not empty j.foto ? j.foto : 'images/silueta_jugador.png'}" class="foto-jugador" alt="foto">
                                </td>
                                <td class="col-nombre" style="font-weight: bold;">${j.nombre}</td>
                                
                                <td class="col-club" style="text-align: center;">
                                    <img src="${not empty j.fotoClub ? j.fotoClub : 'images/escudo_por_defecto.png'}" title="${j.nombreClub}" class="logo-club" style="max-height: 35px; width: auto;">
                                </td>
                                
                                <td class="col-nac">${j.nacionalidad}</td>
                                <td>
                                    <a href="FicharServlet?idJugador=${j.id}&idReemplazar=${idReemplazar}&slot=${slotSeleccionado}" class="btn-fichar" 
                                       style="background-color: #27ae60; color: white; padding: 5px 10px; text-decoration: none; border-radius: 5px; font-weight: bold;">
                                        ${idReemplazar > 0 ? 'Sustituir' : 'Fichar'}
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="5" style="text-align: center; padding: 20px;">No hay jugadores disponibles en esta posición en la base de datos.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                
            </tbody>
        </table>
        
    </div>
    
</main>

</body>
</html>
