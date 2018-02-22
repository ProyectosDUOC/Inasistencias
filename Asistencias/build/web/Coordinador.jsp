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
        <title>Menu Principal Coordinador</title>
        <%
            HttpSession sesion = request.getSession(true);
            Coordinador coor = new Coordinador();
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            
            if (user == null) {
                    response.sendRedirect("error.jsp");
            }
            else{
                int rut = user.getRutUsuario();
                coor = (new CoordinadorDAO()).buscarDatos(rut);
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
                            <p class="color-Amarillo-text"><strong>Bienvenido </strong><%=coor.getPnombre() + " " + coor.getAppaterno() + " " + coor.getApmaterno()%></p>
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
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
    </body>
</html>
