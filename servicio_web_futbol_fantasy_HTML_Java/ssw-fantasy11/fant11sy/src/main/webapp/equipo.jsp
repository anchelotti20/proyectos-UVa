<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<!--Proyecto realizado por el grupo 12-->
<html lang="es">
<head>
    <!--Vinculacion con los estilos de CSS-->
    <link href="styles/main.css" rel="stylesheet" type ="text/css">

    <link href="styles/equipo.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/icono.jpeg" type="images/jpeg">
    
    <meta charset="UTF-8">
    
    <title>Fant11sy - Mi Equipo PRO</title>
    
</head>
<body>

    <header>
        <!--Vinculacion del logo con la pagina principal-->
        <a href="PrincipalServlet">
            <img src="images/logo.png" alt="logo.png" class="logo">
        </a>
        <h1>MI EQUIPO</h1>
        <!--Desplegable para elegir la formacion(de momento solo esta la 4-3-3)-->
        <div class="formacion-selector">
            FORMACIÓN:     
            <select id="selectorFormacion" onchange="window.location.href='CambiarFormacionServlet?nueva=' + this.value" style="padding: 5px; font-weight: bold; border-radius: 5px;">
                
                <option value="4-3-3" ${formacionActual eq '4-3-3' ? 'selected' : ''}>4-3-3</option>
                <option value="4-4-2" ${formacionActual eq '4-4-2' ? 'selected' : ''}>4-4-2</option>
                <option value="3-5-2" ${formacionActual eq '3-5-2' ? 'selected' : ''}>3-5-2</option>
                
            </select>
            
        </div>
    </header>

    <div class="contenedor">
        
        <aside class="tabla-lateral">
            <div class="bonificacion-titulo">BONIFICACIÓN</div>
    
            <div class="lista-bonus">
                <c:forEach var="detallePais" items="${detallesPais}">
                    <p>${detallePais}</p>
                </c:forEach>

                <c:forEach var="detalleClub" items="${detallesClub}">
                    <p>${detalleClub}</p>
                </c:forEach>
            </div>
            
            <div class="total-puntos">+${totalBonus} PUNTOS</div>
            
        </aside>
        <div class="campo-contenedor">
            <div class="once-ideal"><!--Clase para el once inicial-->
                
                <div class="linea">
                    
                    <c:forEach begin="1" end="${numDel}" var="i">
                        
                        <c:set var="slotId" value="${30 + i}" />
                        
                        <c:set var="j" value="${delanterosFichados[slotId.toString()]}" />

                        <c:choose>
                            <%-- Si hay un jugador guardado en este hueco exacto, lo pintamos --%>
                            <c:when test="${not empty j}">
                                <a href="MercadoServlet?posicion=Delantero&slot=${slotId}&reemplazar=${j.id}" class="jugador">
                                    <div class="foto-contenedor"><img src="${j.foto}" alt="jugador"></div>
                                    <div class="nombre-jugador">${j.nombre}</div>
                                </a>
                            </c:when>
                            <%-- Si el hueco está vacío, pintamos la silueta --%>
                            <c:otherwise>
                                <a href="MercadoServlet?posicion=Delantero&slot=${slotId}&reemplazar=0" class="jugador hueco-vacio">
                                    <div class="foto-contenedor"><img src="images/silueta_jugador.png" style="opacity:0.4;"></div>
                                    <div class="nombre-jugador" style="color: #ffdd57;">+ FICHA DEL</div>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>

                <div class="linea">
                    
                    <c:forEach begin="1" end="${numMed}" var="i">
                        
                        <c:set var="slotId" value="${20 + i}" />
                        
                        <c:set var="j" value="${mediosFichados[slotId.toString()]}" />

                        <c:choose>
                            <%-- Si hay un jugador guardado en este hueco exacto, lo pintamos --%>
                            <c:when test="${not empty j}">
                                <a href="MercadoServlet?posicion=Mediocentro&slot=${slotId}&reemplazar=${j.id}" class="jugador">
                                    <div class="foto-contenedor"><img src="${j.foto}" alt="jugador"></div>
                                    <div class="nombre-jugador">${j.nombre}</div>
                                </a>
                            </c:when>
                            <%-- Si el hueco está vacío, pintamos la silueta --%>
                            <c:otherwise>
                                <a href="MercadoServlet?posicion=Mediocentro&slot=${slotId}&reemplazar=0" class="jugador hueco-vacio">
                                    <div class="foto-contenedor"><img src="images/silueta_jugador.png" style="opacity:0.4;"></div>
                                    <div class="nombre-jugador" style="color: #ffdd57;">+ FICHA MED</div>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    
                </div>

                <div class="linea">
                    
                    <c:forEach begin="1" end="${numDef}" var="i">
                        
                        <c:set var="slotId" value="${10 + i}" />
                        
                        <c:set var="j" value="${defensasFichados[slotId.toString()]}" />

                        <c:choose>
                            <%-- Si hay un jugador guardado en este hueco exacto, lo pintamos --%>
                            <c:when test="${not empty j}">
                                <a href="MercadoServlet?posicion=Defensa&slot=${slotId}&reemplazar=${j.id}" class="jugador">
                                    <div class="foto-contenedor"><img src="${j.foto}" alt="jugador"></div>
                                    <div class="nombre-jugador">${j.nombre}</div>
                                </a>
                            </c:when>
                            <%-- Si el hueco está vacío, pintamos la silueta --%>
                            <c:otherwise>
                                <a href="MercadoServlet?posicion=Defensa&slot=${slotId}&reemplazar=0" class="jugador hueco-vacio">
                                    <div class="foto-contenedor"><img src="images/silueta_jugador.png" style="opacity:0.4;"></div>
                                    <div class="nombre-jugador" style="color: #ffdd57;">+ FICHA DEF</div>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    
                </div>

                <div class="linea">
                    <%-- Extraemos el portero del mapa usando la clave fija 1 --%>
                    <c:set var="j" value="${porterosFichados['1']}" />

                    <c:choose>
                        <%-- Si ya hay un portero guardado en el Slot 1, lo pintamos --%>
                        <c:when test="${not empty j}">
                            <a href="MercadoServlet?posicion=Portero&slot=1&reemplazar=${j.id}" class="jugador">
                                <div class="foto-contenedor">
                                    <img src="${not empty j.foto ? j.foto : 'images/silueta_jugador.png'}" alt="Portero">
                                </div>
                                <div class="nombre-jugador">${j.nombre}</div>
                            </a>
                        </c:when>

                        <%-- Si el hueco del Slot 1 está vacío, pintamos la silueta clickable --%>
                        <c:otherwise>
                            <a href="MercadoServlet?posicion=Portero&slot=1&reemplazar=0" class="jugador hueco-vacio">
                                <div class="foto-contenedor">
                                    <img src="images/silueta_jugador.png" alt="Añadir Portero" style="opacity: 0.4;">
                                </div>
                                <div class="nombre-jugador" style="color: #ffdd57;">+ FICHA POR</div>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>    
            </div>
        </div>
    </div>
    
    <footer>
        <div class="nav-btn active">MI EQUIPO</div>
        <!--Mostramos que el boton del equipo esta activo-->
        <!--Vinculamos el boton de clasificacion con la pagina que muestra esta-->
        <a href="ClasificacionServlet" class="nav-btn">CLASIFICACIÓN</a>
    </footer>

</body>
</html>
