<%-- 
    Document   : Coordinador
    Created on : 18-11-2017, 20:45:00
    Author     : Seba
--%>

<%@page import="dao.CoordinadorDAO"%>
<%@page import="modelo.Coordinador"%>
<%@page import="modelo.ControlUsuario"%>
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
        <title>Menu Principal Coordinador</title>
        <%
            HttpSession sesion = request.getSession(true);
            Coordinador coor = new Coordinador();
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            String nombre = " ", estado = " ";
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
                <h4 class="color-Plomo color-Azul-text center-align" >Menu Coordinador</h4>
                <form action="MenuCoordinador" method="post" >
                    <table border="1">
                        <tr>
                            <td>
                                <button class="btn-large waves-effect waves-light color-AzulClaro" type="submit" name="opcion" value="Subir Inasistencias">Subir Inasistencias</button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button class="btn-large waves-effect waves-light color-AzulClaro" type="submit" name="opcion" value="Enviar Correos">Enviar Correos</button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button class="btn-large color-AzulClaro" type="submit" name="opcion" value="Ver datos">Ver datos</button> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button class="btn-large waves-effect  red waves-light " type="submit" name="opcion" value="Salir">Salir</button>
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
