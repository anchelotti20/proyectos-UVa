<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <link href="styles/main.css" rel="stylesheet" type="text/css">
    <link href="styles/calendario.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/icono.jpeg" type="images/jpeg">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fant11sy - Calendario interactivo</title>
</head>
<body>

    <header>
        <a href="PrincipalServlet">
            <img src="images/logo.png" alt="logo.png" class="logo">
        </a>
    
        <h1>CALENDARIO</h1>
    
        <div class="selector-jornada">
            <label style="font-weight: bold;">IR A </label>
            <select id="jornadaSelect" onchange="window.location.href='CalendarioServlet?jornada=' + this.value">
                <c:forEach var="i" begin="1" end="22">
                    <option value="${i}" ${jornadaMostrada == i ? 'selected' : ''}>Jornada ${i}</option>
                </c:forEach>
            </select>
        </div>
    </header>

    <main class="contenedor">
        <div class="jornada-titulo">JORNADA ${jornadaMostrada}</div>

        <div class="lista-partidos">
            <c:choose>
                <c:when test="${not empty listaPartidos}">
                    <c:forEach var="p" items="${listaPartidos}">
                        <div class="partido-fila">
                            
                            <a href="ClubServlet?nombre=${p.nombreLocal}" class="btn-equipo-lista local">
                                <img src="${not empty p.fotoLocal ? p.fotoLocal : 'images/escudo_por_defecto.png'}" 
                                        class="escudo-calendario" alt="Escudo ${p.nombreLocal}">
                                
                                <span>${p.nombreLocal}</span>
                            
                            
                            </a>
                            
                            <div class="resultado-marcador">
                                
                                <c:choose>
                                    
                                    <c:when test="${not empty p.ganador}">
                                        <span class="goles">${p.golesLocal}</span>
                                        <span class="vs-separador">-</span>
                                        <span class="goles">${p.golesVisitante}</span>
                                    </c:when>
                                        
                                    <c:otherwise>
                                        <span class="goles">-</span>
                                        <span class="vs-separador">VS</span>
                                        <span class="goles">-</span>   
                                    </c:otherwise>
                                    
                                </c:choose>
                                
                            </div>
                            
                            <a href="ClubServlet?nombre=${p.nombreVisitante}" class="btn-equipo-lista visitante">
                                <span>${p.nombreVisitante}</span>
                                
                                <img src="${not empty p.fotoVisitante ? p.fotoVisitante : 'images/escudo_por_defecto.png'}" 
                                     class="escudo-calendario" alt="Escudo ${p.nombreVisitante}">
                            
                            </a>
                        
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No hay partidos programados para esta jornada.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </main>

</body>
</html>