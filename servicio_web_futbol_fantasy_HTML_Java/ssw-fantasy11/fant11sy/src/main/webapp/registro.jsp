<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <link href="styles/main.css" rel="stylesheet" type ="text/css">
        <link href="styles/registro.css" rel="stylesheet" type="text/css">
        <link rel="icon" href="images/icono.jpeg" type="image/jpeg">
        
        <title>Registro en Fant11sy</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        
    </head>
    <body>
        <div class="contenedor">

        <img src="images/logo.png" alt="logo.png" class="logo">

        <h2>REGISTRO</h2>
        <h3>¡Crea una cuenta Fant11sy y juega con tus amigos!</h3>
        
        
        <c:if test = "${not empty mensajeError}">
            
            <p style="color: red; text-align: center; font-weight: bold;">${mensajeError}</p>
        </c:if>
        
        
        <form action="RegistroServlet" method="POST" class="formulario">
               
            <div class="fila">
                <label>Nombre:</label>
                <input type="text" name="nombre" placeholder="Juan Miguel" required>
            </div>
            
            <div class="fila">
                <label>Usuario</label>
                <input type="text" name="usuario" placeholder="CalipoLima" required>
            </div>

            <div class="fila">
                <label>Contraseña(no olvidar!)</label>

                <div class="password-container">
                    <input type="password" name="contrasena" id="password" required>
                </div>
            </div>

            <div class="btn">
                <button type="submit" class="button" style="cursor: pointer">Crear Usuario</button>
            </div>

    </form>
    </body>
</html>
