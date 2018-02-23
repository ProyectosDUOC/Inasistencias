<%-- 
    Document   : index
    Created on : 18-nov-2017, 3:34:48
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
         <%
            HttpSession sesion = request.getSession(true);            
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");

            if (user != null) {
                sesion.invalidate();
            } 
        %>
    </head>
    <body>
        <header class="color-Azul">
            <div class="container">
                <div class="row">                    
                    <div class="center-align">
                        <br>
                        <h5 class="white-text"><strong>Sistema de inasistencias</strong></h5>
                        <br>
                    </div>
                </div>
            </div>
        </header>   
        <div class="container">
            <div class="center-align">
                <p class="color-Plomo-text color-Plomo">-------</p>
            </div>
        </div>
        <section>
            <div class="container">
                <div class="row">
                    <div class="col s12 m4">     
                        <div class="center-align main">
                            <h5 class="color-AzulClaro-text"><strong>Ingresa acá</strong></h5>
                            <form action="Login" method="post">
                                <table>
                                    <tr>
                                        <td>Usuario:</td>
                                        <td><input type="text" name="txtUser" required=""/></td>
                                    </tr>
                                    <tr>
                                        <td>Contraseña:</td>
                                        <td><input type="password" name="txtPass" required=""/></td>
                                    </tr>                                    
                                </table>
                                <div >
                                    <input type="submit" name="opcion" value="Entrar" class="color-Azul-text"/>                                
                                </div>                            
                            </form>
                            <br>
                            <a href="recuperarClave.jsp" class="color-Azul-text right">¿Olvidaste tu contraseña? </a>
                            <br>
                        </div>
                        <span class="red-text"> ${param.mensaje}</span>
                    </div>
                </div>
            </div>
        </section>
        <br>
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