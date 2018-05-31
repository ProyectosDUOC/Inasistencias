<%-- 
    Document   : index
    Created on : 02-may-2018, 12:50:09
    Author     : benja
--%>

<%@page import="modelo.ControlUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Portal | Inasistencias Duoc</title>           
        <!-- CSS  -->        
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css">          
        <link rel="shortcut icon" href="images/favicon.ico?" type="images/favicon.ico" />
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");

            if (user != null) {
                sesion.invalidate();
            }
        %>
    </head>
    <body class="text-center">
        <header class="color-Azul">
            <div class="container">
                <div class="row">                    
                    <div class="center-align">
                        <br>
                        <h5 class="white-text"><strong>NOSOTROS</strong></h5>
                        <br>
                    </div>
                </div>
            </div>
        </header>   
        <div class="container">
            <div class="center-align">
                <p class="color-Plomo">Desarollodo por Estudiantes Duoc San Bernardo 2018</p>

                <div class="col s12 m4">     
                    <div>
                        <h5 class="color-AzulClaro-text"><strong>Desarrolladores</strong></h5>    
                        <p><strong>Carlos Orellana</strong></p>
                        <p><strong>Sebastian Orrego</strong></p>
                        <p><strong>Benjamin Mora</strong></p>                          
                        <br>                        
                        <h5 class="color-AzulClaro-text"><strong>Profesor Cristian Garcia</strong></h5> 
                        <br>                   
                    </div>
                    <div>
                        <a class="white-text btn  waves-effect waves-light  red" href="index.jsp">Volver</a>
                    </div>   
                </div>
            </div>
        </div>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <footer class="color-Azul">            
            <div class="container">
                <br>
                <br>
                <br>
                <br>
            </div>
        </footer>
    </body>
</html>
