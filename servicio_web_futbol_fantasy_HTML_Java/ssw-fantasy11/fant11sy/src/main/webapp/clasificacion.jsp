<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link href="styles/main.css" rel="stylesheet" type ="text/css">
    <link href="styles/clasificacion.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/icono.jpeg" type="images/jpeg">
    <title>Fant11sy - Clasificación</title>
</head>
<body>

    <header>
        <a href="PrincipalServlet">
            <img src="images/logo.png" alt="logo" class="logo">
        </a>
        <h1>CLASIFICACIÓN</h1>
        
        <div class="selector-jornada">
            <select onchange="window.location.href='ClasificacionServlet?filtro=' + this.value">
                <option value="Total" ${filtroActual eq 'Total' ? 'selected' : ''}>TOTAL GLOBAL</option>
                <c:forEach var="i" begin="1" end="22">
                    <option value="${i}" ${filtroActual eq i.toString() ? 'selected' : ''}>JORNADA ${i}</option>
                </c:forEach>
            </select>
        </div>
    </header>

    <main class="contenedor">
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th style="text-align: left; padding-left: 20px;">USUARIO</th>
                    <th style="text-align: right; padding-right: 20px;">PUNTOS</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty clasificacion}">
                        <c:forEach var="fila" items="${clasificacion}">
                            <tr>
                                <td class="pos">${fila.puesto}</td>
                                <td class="nombre">
                                    <a href="EquipoUsuarioServlet?usuario=${fila.usuario}">${fila.usuario}</a>
                                </td>
                                <td class="puntos">${fila.puntos}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="3" class="sin-datos">No hay datos disponibles.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </main>

    <footer>
        <a href="EquipoServlet" class="nav-btn">MI EQUIPO</a>
        <div class="nav-btn active">CLASIFICACIÓN</div>
    </footer>

</body>
</html>