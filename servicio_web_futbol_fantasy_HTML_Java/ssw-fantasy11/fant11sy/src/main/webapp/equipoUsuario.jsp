<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <link href="styles/main.css" rel="stylesheet" type="text/css">
    <link href="styles/equipoUsuario.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/icono.jpeg" type="images/jpeg">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Fant11sy - Vista de Equipo Simplificada</title>
</head>
<body>

    <header>
        <a href="PrincipalServlet">
            <img src="images/logo.png" alt="logo.png" class="logo">
        </a>
        <h1>${nombreRival}</h1>
    </header>

    <main class="contenedor">
        <div class="once-ideal">
            
            <%-- LINEA DE ATAQUE (DELANTEROS) --%>
            <div class="linea">
                <c:forEach begin="1" end="${numDel}" var="i">
                    <c:set var="slotId" value="${30 + i}" />
                    <c:set var="j" value="${delanterosFichados[slotId.toString()]}" />
                    
                    <div class="jugador">
                        <div class="foto-container">
                            <img src="${not empty j.foto ? j.foto : 'images/silueta_jugador.png'}" alt="Jugador">
                        </div>
                        <div class="nombre-jugador">${not empty j.nombre ? j.nombre : 'VACÍO'}</div>
                    </div>
                </c:forEach>
            </div>

            <%-- LINEA DEL MEDIO (MEDIOCENTROS) --%>
            <div class="linea">
                <c:forEach begin="1" end="${numMed}" var="i">
                    <c:set var="slotId" value="${20 + i}" />
                    <c:set var="j" value="${mediosFichados[slotId.toString()]}" />
                    
                    <div class="jugador">
                        <div class="foto-container">
                            <img src="${not empty j.foto ? j.foto : 'images/silueta_jugador.png'}" alt="Jugador">
                        </div>
                        <div class="nombre-jugador">${not empty j.nombre ? j.nombre : 'VACÍO'}</div>
                    </div>
                </c:forEach>
            </div>

            <%-- LINEA DE DEFENSA (DEFENSAS) --%>
            <div class="linea">
                <c:forEach begin="1" end="${numDef}" var="i">
                    <c:set var="slotId" value="${10 + i}" />
                    <c:set var="j" value="${defensasFichados[slotId.toString()]}" />
                    
                    <div class="jugador">
                        <div class="foto-container">
                            <img src="${not empty j.foto ? j.foto : 'images/silueta_jugador.png'}" alt="Jugador">
                        </div>
                        <div class="nombre-jugador">${not empty j.nombre ? j.nombre : 'VACÍO'}</div>
                    </div>
                </c:forEach>
            </div>

            <%-- LINEA DEL PORTERO --%>
            <div class="linea">
                <c:set var="j" value="${porterosFichados['1']}" />
                
                <div class="jugador">
                    <div class="foto-container">
                        <img src="${not empty j.foto ? j.foto : 'images/silueta_jugador.png'}" alt="Jugador">
                    </div>
                    <div class="nombre-jugador">${not empty j.nombre ? j.nombre : 'VACÍO'}</div>
                </div>
            </div>

        </div>
        
        <a href="ClasificacionServlet" class="btn-clasi">CLASIFICACIÓN</a>
    </main>

</body>
</html>