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
    <body>
        <header class="color-Azul">
            <div class="container">
                <div class="row">                    
                    <div class="center-align">
                        <br>
                        <h5 class="white-text"><strong>Sistema de Inasistencias</strong></h5>
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
            <p><%=System.getenv("UPLOADS")%></p>
            <div class="container">
                <div class="row">
                    <div class="col s12 m4">     
                        <div class="center-align main">
                            <h5 class="color-AzulClaro-text"><strong>Ingresa acá</strong></h5>
                            <form action="Login" method="post">
                                <table>
                                    <tr>
                                        <td>Usuario:</td>
                                        <td><input type="text" name="txtUser" required="" maxlength="10"/></td>
                                    </tr>
                                    <tr>
                                        <td>Contraseña:</td>
                                        <td><input type="password" name="txtPass" required="" maxlength="10"/></td>
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
                    <div class="col s12 m6">     
                        <div>
                            <h5 class="color-AzulClaro-text"><strong>Datos</strong></h5>
                            <p> <strong>Administrador:</strong> 4002   1234 </p>
                            <p> <strong>Secretaria:</strong>     1001   1234   ok </p>
                            <p> <strong>Docente:</strong>       2002   1234 </p>
                            <p> <strong>Director:</strong>       3000   1234 </p>
                            <p> <strong>Alumno:</strong>         10-30     1234 </p>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <br>
            <br>
        </section>
        <br>
        <footer class="color-Azul">            
            <div class="container">
                <br>
                <p class="color-Amarillo-text center-align">Desarrollado por Estudiantes DUOC San Bernardo  <a href="acerca.jsp" class="color-Plomo-text">ver</a></p>                                
                <br>
            </div>
        </footer>
    </body>
</html>
