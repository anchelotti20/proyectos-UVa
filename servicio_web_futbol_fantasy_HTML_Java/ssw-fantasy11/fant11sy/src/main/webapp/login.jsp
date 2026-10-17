<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<!--Proyecto realizado por el grupo 12 -->
<!--Pantalla que serivra para acceder a los servicios de la aplicacion-->
<html lang="es">
<head>
    <!--Vinculacion con los estilos de  CSS-->
    <link href="styles/main.css" rel="stylesheet" type ="text/css">

    <link href="styles/login.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/icono.jpeg" type="images/jpeg">
 
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Acceder - Fant11sy</title>

</head>

<body>

    <div class="contenedor">

        <img src="images/logo.png" alt="logo.png" class="logo">

        <h2>ACCEDER</h2><!--Escribimmos acceder para dar a entender que el usuario debe introducir sus credenciales-->
        <!--Se trata de un formulario que el usuario debe rellenar-->
        <!-- Ahora mismo da igual lo que se introduzca que se pasara a la siguiente pagina web-->
        <form action="LoginServlet" method="POST" class="formulario">

            <div class="fila">
                <label>Usuario:</label><!--Etiqueta de usuario-->
                <input type="text" name="usuario" required><!--Indicamos que esperamos texto-->
            </div>

            <div class="fila">
                <label>Contraseña:</label><!--Etiqueta de password-->

                <div class="password-container">
                    <input type="password" id="password" name="contrasena" required>
                </div>
            </div>

            <div class="btn">
                <button type="submit" class="button">Aceptar</button><!--Vinculamos el boton de acpetar para acceder a la pagina principal-->
            </div>
            
            <c:if test="${not empty mensajeError}">
                
                <div class="mensaje-error" style="color: red; text-align: center; margin-bottom: 15px;">
                    ${mensajeError}
                </div>
            </c:if>
        
        </form>
            
        <label class="centrado">¿Eres nuevo?</label>
            <div class="btn">
                
                <a href="registro.jsp" class="button">Registrarse</a>
            
            </div>
            
            
            <c:if test="${not empty mensajeExito}">
                <div class="mensaje-exito" style="color: green; text-align: center; margin-bottom: 15px;">
                    ${mensajeExito}
                </div>
            </c:if>
        
    </div>

</body>
</html>
