<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<!--Proyecto realizado por el grupo 12-->
<!--Muestra la pantalla de un equipo, para no repetirnos-->
<!--De momento se muestran unos pocos jugadores, en las siguientes iteraciones se insertaran todos los jugadores de un equipo-->
<html lang="es">
<head>
    <!--Vinculacion con los estilos de CSS-->
    <link href="styles/main.css" rel="stylesheet" type ="text/css">

    <link href="styles/clubes.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/icono.jpeg" type="images/jpeg">
    
    <meta charset="UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Fant11sy - Plantilla</title>
    
</head>
<body>

    <header>
        <!--Vinculamos con la pagina principal-->
        <a href="PrincipalServlet"><img src="images/logo.png" alt="logo.png" class="logo"></a>
    
        <h1>${nombreClub}</h1><!--Nombre del equipo-->
        
        <div class="escudo-equipo">
            <img src="${not empty escudoClub ? escudoClub : 'images/escudo_por_defecto.png'}" alt="Escudo de ${nombreClub}">
        </div>
    </header>

    <div class="nav-contenedor">
        <!--Vinuclacion con el calendario a traves del boton de hacia atras "<" -->
        <a href="CalendarioServlet" class="btn-volver">&lt;</a>
    </div>

    <main class="contenedor">
        <table class="tabla-jugadores">
            <thead>
                <tr><!--En cada fila tenemos posicion,foto,jugador,equipo,puntos y nacionalidad-->
                    <th>POS</th>
                    <th>FOTO</th>
                    <th>JUGADOR</th>
                    <th>CLUB</th>
                    <th>NACIONALIDAD</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty listaJugadores}">
                        <c:forEach var="j" items="${listaJugadores}">
                            <tr>
                                <td><span class="pos">${j.posicion}</span></td>
                                <td>
                                    <div class="foto-cuadrada">
                                        <img src="${not empty j.foto ? j.foto : 'images/silueta_jugador.png'}" alt="jugador">
                                    </div>
                                </td>
                                <td style="font-weight: bold;">${j.nombre}</td>
                                <td>${nombreClub}</td>
                                <td class="nacionalidad">${j.nacionalidad}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="5" style="text-align: center; padding: 20px;">
                                No se encontraron jugadores cargados para este club.
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </main>

</body>
</html>
