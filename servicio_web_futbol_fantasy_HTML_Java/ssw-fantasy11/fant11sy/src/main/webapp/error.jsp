<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <link href="styles/main.css" rel="stylesheet" type="text/css">
    <link href="styles/error.css" rel="stylesheet" type ="text/css">
    
    <link rel="icon" href="images/icono.jpeg" type="images/jpeg">
    <meta charset="UTF-8">
    <title>Error - Fant11sy</title>
    
</head>
<body>
    <header>
        <a href="PrincipalServlet">
            <img src="images/logo.png" alt="logo" class="logo">
        </a>
        <h1>¡UPS! ALGO HA SALIDO MAL</h1>
    </header>

    <main class="contenedor">
        <div class="error-container">
            <h2 style="color: red;">Se ha producido un error inesperado</h2>
            
            <p>Nuestros árbitros están revisando el VAR para solucionar el problema.</p>

            <div style="background-color: #f8d7da; color: #721c24; padding: 15px; border-radius: 5px; margin: 20px 0; font-family: monospace; text-align: left;">
                <strong>Detalle del error:</strong><br>
                <%= exception != null ? exception.getMessage() : "Error desconocido" %>
            </div>

            <a href="PrincipalServlet" class="btn-volver" style="text-decoration: none; font-weight: bold; padding: 10px 20px; background-color: #1e3d59; color: white; border-radius: 5px;">
                Volver a la Página Principal
            </a>
        </div>
    </main>
</body>
</html>
