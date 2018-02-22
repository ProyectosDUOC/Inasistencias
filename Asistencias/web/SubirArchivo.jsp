<%-- 
    Document   : SubirArchivo
    Created on : 19-11-2017, 21:44:45
    Author     : Seba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style1.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css">         
        <title>Coordinador | Subir Archivo Excel</title>
    </head>
    <body>
        <header class="color-Azul">
            <div class="container">
                <div class="row">                    
                    <div class="center-align">
                        <br>
                        <h5 class="white-text"><strong>Sistema de inasistencias</strong></h5>
                        
                        <div class="col s6 offset-s6">
                            <p class="color-Amarillo-text"><strong>Bienvenido </strong><%=coor.getPnombre() + " " + coor.getAppaterno() + " " + coor.getApmaterno()%></p>
                        </div>
                    </div>
                </div>
            </div>
        </header>  
        <div class="container">
            <div class="row">
                <h1 class="yellow darken-1 center-align">Carga Inasistencias</h1>
                <p class="red-text">Subir archivo Excel (.xlsx) </p>
                <form action="CargarExcel" method="post" enctype="multipart/form-data">
                    <input type="file" name="file" required=""  accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                    <input type="submit" value="Subir" />
                </form>
                <br>
                <div class="mensaje">${param.mensaje}</div>
                <br>                
                <a  class="white-text btn waves-effect waves-light red" href="Coordinador.jsp">Volver</a>
          
            </div>        
        </div>                
        <footer class="color-Azul">            
            <div class="container">
                <br>
                <p class="color-Amarillo-text center-align">Desarrollado por Estudiantes DUOC San Bernardo</p>
                <p class="color-Amarillo-text center-align">Carlos Orellana ★ Sebastian Orrego &#9733;  Benjamin Mora</p>
                <p class="color-Amarillo-text center-align"> &#9733; 2018 &#9733; </p>
                <br>
            </div>
        </footer>
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
    </body>
</html>
