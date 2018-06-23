<%-- 
    Document   : panelControl
    Created on : 06-05-2018, 0:27:58
    Author     : carlos
--%>
<%@page import="dao.AdministradorDAO"%>
<%@page import="modelo.ControlUsuario"%>
<%@page import="modelo.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="../css/styleLogin.css">     
        <link rel="shortcut icon" href="../images/favicon.ico?" type="images/favicon.ico" />
        <title>Menu Principal Coordinador</title>
        <%
            HttpSession sesion = request.getSession(true);
            Administrador admin = new Administrador();
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            String nombre = "", estado = "";
            if (user == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("administrador")) {
                    sesion.setAttribute("respU",null);
                    sesion.setAttribute("xCrud",null);
                    String rut = user.getRutUsuario();
                    admin = (new AdministradorDAO()).buscarDatos(rut);
                    nombre = admin.getPnombre() + " " + admin.getSnombre() + " " + admin.getAppaterno() + " " + admin.getApmaterno();
                } else {
                    response.sendRedirect("../index.jsp");
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
                            <a href="../<%=estado%>.jsp" class="color-Amarillo-text"><strong><i class="Tiny material-icons prefix">home</i></strong></a>  
                            <a href="../<%=estado%>.jsp" class="color-Amarillo-text"><strong><i class="Tiny material-icons prefix">person</i>Bienvenido </strong><span class="white-text"><%=nombre%></span></a>
                            <div class="cols s6">
                                <a class="waves-effect waves-light" href="../configuracion.jsp"><i class="material-icons color-Amarillo-text left">settings_applications</i><span class="white-text"><strong>Configuración</strong></span></a>&nbsp;&nbsp;&nbsp;
                                <a class="waves-effect waves-light" href="../index.jsp"><i class="material-icons color-Amarillo-text left">exit_to_app</i><span class="white-text"><strong>Salir</strong></span></a>                         
                            </div>                            
                        </div>
                    </div>
                </div>
            </div>                   
        </header>  
        <div class="container">
            <div class="row">
                <h4 class="color-Plomo color-Azul-text center-align" >Menu Control</h4>
                <form method="POST" action="../ControladorPanel">   
                    <div class="col s12 m6 color-Azul-text">
                        <h4 class="color-Plomo color-Azul-text center-align" >Usuarios</h4>  
                 <!--       <button type="submit" value="Alumno" name="opcion" class="btn waves-effect waves-light color-AzulClaro" >Alumno</button>
                        <button type="submit" value="Docente" name="opcion"  class="btn waves-effect waves-light color-AzulClaro" >Docente</button>
                        <button type="submit" value="Director" name="opcion"  class="btn waves-effect waves-light color-AzulClaro">Director</button>
                        <br><br>    --> 
                        <button type="submit" value="Secretaria" name="opcion"  class="btn waves-effect waves-light color-AzulClaro" >Secretaria</button>
                        <!--<button type="submit" value="Administrador" name="opcion"  class="btn waves-effect waves-light color-AzulClaro" >Administrador</button> -->
                       
                        <span class="red-text"> ${param.mensaje}</span>
                        <br><br> <br> <br> <br>
                        <a  class="white-text btn  waves-effect waves-light  red" href="../<%=estado%>.jsp">Volver</a>
                    </div>
                    <!--
                    <div class="col s12 m6 color-Azul-text">
                        <h4 class="color-Plomo center-align">Registro</h4>     
                        <div class="input-field">                                
                            <button type="submit" value="Detalle" name="opcion"  class="btn waves-effect waves-light color-AzulClaro" >Detalle de Sección</button>
                            <button type="submit" value="Seccion" name="opcion"  class="btn waves-effect waves-light color-AzulClaro" >Seccion</button>
                            <br> <br>
                            <button type="submit" value="Carrera" name="opcion"  class="btn waves-effect waves-light color-AzulClaro" >Carrera</button>
                            <button type="submit" value="Ramo" name="opcion"  class="btn waves-effect waves-light color-AzulClaro" >Ramo</button>
                        </div>
                    </div> -->
                     <div class="col s12 m6 color-Azul-text">
                        <h4 class="color-Plomo center-align">Datos Globales</h4>     
                        <div class="input-field">                                
                            <button type="submit" value="Semestre" name="opcion"  class="btn waves-effect waves-light color-AzulClaro" >Cambiar Semestre y Año</button>
                            
                        </div>
                    </div>
                </form>
            </div>
        </div>           
                    <br> <br> <br>
        <footer class="color-Azul">            
            <div class="container">
                <br>
                <p class="color-Amarillo-text center-align">Desarrollado por Estudiantes DUOC San Bernardo</p>                                
                <br>
            </div>
        </footer> 
    </body>
</html>
