<%-- 
    Document   : director
    Created on : 03-05-2018, 23:46:30
    Author     : carlos
--%>

<%@page import="dao.DirectorDAO"%>
<%@page import="modelo.ControlUsuario"%>
<%@page import="modelo.Director"%>
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
        <link rel="shortcut icon" href="images/favicon.ico?" type="images/favicon.ico" />
        <title>Menu Director</title>
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            Director dire = new Director();

            String nombre = "", estado = "", rut = "";
            if (user == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("director")) {
                    rut = user.getRutUsuario();
                    dire = (new DirectorDAO()).buscarDatos(rut);
                    nombre = dire.getPnombre() + " " + dire.getSnombre() + " " + dire.getAppaterno() + " " + dire.getApmaterno();
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
                            <a href="<%=estado%>.jsp" class="color-Amarillo-text"><strong><i class="Tiny material-icons prefix">person</i>Bienvenido </strong><span class="white-text"><%=nombre%></span></a>
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
            <div class="row">            
                <h4 class="color-Plomo color-Azul-text center-align" >Menu Director de Carrera</h4>                  
                <table border="1" class="center-align">   
                    <tr>
                        <td>
                            <a class="btn-large waves-effect waves-light color-AzulClaro" href="">
                                Inasistencias Justificadas
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a class="btn-large waves-effect waves-light color-AzulClaro" href="">
                                Checkeado por docente 
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a class="btn-large waves-effect waves-light color-AzulClaro" href="">
                                Reporte por Semestre
                            </a>
                        </td>
                    </tr>
                    <a class="btn-large waves-effect waves-light color-AzulClaro" href="mensajeEntrante.jsp">
                        Justificar
                    </a>
                    <tr>
                        <td>
                            <a class="btn-large waves-effect waves-light color-AzulClaro" href="index.jsp">
                                Salir
                            </a>
                        </td>
                    </tr> 
                </table>
            </div>
        </div>
        <footer class="color-Azul">            
            <div class="container">
                <br>
                <p class="color-Amarillo-text center-align">Desarrollado por Estudiantes DUOC San Bernardo</p>                                
                <br>
            </div>
        </footer>   
    </body>
</html>