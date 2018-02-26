<%-- 
    Document   : reportesPDFsemestre
    Created on : 26-nov-2017, 22:51:49
    Author     : benja
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
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css"> 
        <title>Reportes por Año y Semestre</title>

        <%
            HttpSession sesion = request.getSession(true);
            Director dire = new Director();
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            String nombre = " ", estado = " ";
            if (user == null) {
                response.sendRedirect("error.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                int rut = user.getRutUsuario();
                dire = (new DirectorDAO()).buscarDatos(rut);
                nombre = dire.getPnombre() + " " + dire.getSnombre() + " " + dire.getAppaterno() + " " + dire.getApmaterno();
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
                <h4 class="color-Plomo color-Azul-text center-align">Reporte por año y semestre especificos</h4>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo center-align"></h4>     
                    <form action="ControladorDirector" method="post">
                        <table class=" color-Plomo color-Azul-text">
                            <tr>
                                <td>Semestre:</td>
                                <td class="col s12">
                                    <select class="browser-default" name="semestre" required="required">
                                        <option value="" disabled selected>Seleccione Semestre</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                    </select> 
                                </td>
                            </tr>
                            <tr>
                                <td >Año:</td>
                                <td class="col s12">
                                    <select class="browser-default" name="anio" required="required">
                                        <option value="" disabled selected>Seleccione un año</option>
                                        <option value="2017">2017</option>
                                        <option value="2016">2016</option>
                                        <option value="2015">2015</option>
                                        <option value="2014">2014</option>
                                        <option value="2013">2013</option>
                                    </select> 
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a  class="white-text btn-large waves-effect waves-light red" href="<%=estado%>.jsp">Volver</a>
                                </td>
                                <td>
                                    <button class="btn-large waves-effect waves-light btn amber" type="submit" name="opcion" value="Guardar">
                                        Guardar
                                    </button> 
                                </td> 
                            </tr>
                        </table>
                    </form>
                </div>          
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
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
        <script>
            $(document).ready(function () {
                $('select').material_select();
            });
        </script>  
    </body>
</html>
