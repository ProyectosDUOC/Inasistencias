<%-- 
    Document   : enviarCorreos
    Created on : 20-11-2017, 1:12:29
    Author     : Seba
--%>

<%@page import="dao.CoordinadorDAO"%>
<%@page import="modelo.ControlUsuario"%>
<%@page import="modelo.Coordinador"%>
<%@page import="modelo.Coordinador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css">           
        <title>Enviar Correos</title>
        <%
            HttpSession sesion = request.getSession(true);
            Coordinador coor = new Coordinador();
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            String estado = "", nombre = "";
            if (user == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("Coordinador")) {
                    int rut = user.getRutUsuario();
                    coor = (new CoordinadorDAO()).buscarDatos(rut);
                    nombre = coor.getPnombre() + " " + coor.getSnombre() + " " + coor.getAppaterno() + " " + coor.getApmaterno();
                } else {
                    response.sendRedirect("index.jsp");
                }
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
                        <div class="col s6 offset-s6">
                            <a href="<%=estado%>.jsp" class="color-Amarillo-text"><strong><i class="Tiny material-icons prefix">home</i></strong></a>  
                            <a href="Coordinador.jsp" class="color-Amarillo-text"><strong><i class="Tiny material-icons prefix">person</i>Bienvenido </strong><span class="white-text"><%=nombre%></span></a>
                            <div class="cols s6">
                                <a class="waves-effect waves-light" href="configuracion.jsp"><i class="material-icons color-Amarillo-text left">settings_applications</i><span class="white-text"><strong>Configuración</strong></span></a>&nbsp;&nbsp;&nbsp;
                                <a class="waves-effect waves-light" href="index.jsp"><i class="material-icons color-Amarillo-text left">exit_to_app</i><span class="white-text"><strong>Salir</strong></span></a>                         
                            </div>                            
                        </div>
                    </div>
                </div>
            </div>                   
        </header>  
        <div class="container">
            <h4 class="yellow darken-1 center-align">Enviar Correos</h4>
            <div class="col s12 m4 l8">
                <form method="post" action="ControladorEnviarCorreos">
                    <table border="1">
                        <tr>
                            <td>                                                          
                                <p class="red-text"><strong>Clic para enviar correos</strong></p>
                                <button class="btn-large waves-effect waves-light yellow black-text" type="submit" name="opcion" value="Enviar">Enviar</button>
                            </td>
                        <span id="mensaje red-text"><strong>${param.mensaje}</strong></span>
                        </tr>
                        <tr>
                            <td>
                                <a  class="white-text btn  waves-effect waves-light  red" href="Coordinador.jsp">Volver</a>
                            </td>
                        </tr>
                    </table>                  
                </form>
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
    </body>
</html>
