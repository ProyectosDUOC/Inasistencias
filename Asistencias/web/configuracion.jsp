<%-- 
    Document   : configuracion
    Created on : 23-feb-2018, 16:44:49
    Author     : benja
--%>

<%@page import="dao.ClasesConsultas"%>
<%@page import="dao.DirectorDAO"%>
<%@page import="dao.DocenteDAO"%>
<%@page import="dao.AlumnoDAO"%>
<%@page import="modelo.Docente"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.Director"%>
<%@page import="dao.CoordinadorDAO"%>
<%@page import="modelo.ControlUsuario"%>
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
        <title>Configuracion | </title>
        <%
            HttpSession sesion = request.getSession(true);

            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            Coordinador coor = new Coordinador();
            Director dire = new Director();
            Docente doce = new Docente();
            Alumno alum = new Alumno();
            String nombre = " ", correo = " ", usuario = " ", clave = " ", carrera = " ", rutD = " ";
            String estado = " ";

            if (user == null) {
                response.sendRedirect("error.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                usuario = user.getUsuario();
                clave = user.getClave();
                int rut = user.getRutUsuario();
                if (estado.equals("Alumno")) {
                    alum = (new AlumnoDAO()).buscarDatos(rut);
                    nombre = alum.getPnombre() + " " + alum.getSnombre() + " " + alum.getAppaterno() + " " + alum.getApmaterno();
                    rutD = rut + "-" + alum.getDvAlumno();
                    correo = alum.getEmail();
                    carrera = (new ClasesConsultas()).buscarCarrera(alum.getIdCarrera()).getNombreCarrera();
                }
                if (estado.equals("Docente")) {
                    doce = (new DocenteDAO()).buscarDatos(rut);
                    nombre = doce.getPnombre() + " " + doce.getSnombre() + " " + doce.getAppaterno() + " " + doce.getApmaterno();
                    rutD = rut + "-" + doce.getDvDocente();
                    correo = doce.getEmail();
                }
                if (estado.equals("Director")) {
                    dire = (new DirectorDAO()).buscarDatos(rut);
                    nombre = dire.getPnombre() + " " + dire.getSnombre() + " " + dire.getAppaterno() + " " + dire.getApmaterno();
                    rutD = rut + "-" + dire.getDvDirector();
                    correo = dire.getEmail();
                }
                if (estado.equals("Coordinador")) {
                    coor = (new CoordinadorDAO()).buscarDatos(rut);
                    nombre = coor.getPnombre() + " " + coor.getSnombre() + " " + coor.getAppaterno() + " " + coor.getApmaterno();
                    rutD = rut + "-" + coor.getDvCoordinador();
                    correo = coor.getEmail();
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
                <h4 class="color-Plomo color-Azul-text center-align" >Mi Cuenta</h4>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo color-Azul-text center-align" >Datos Personales</h4>  
                    <p><strong> Nombre :</strong> <span><%=nombre%></span></p>
                    <p><strong> Rut :</strong> <span><%=rutD%></span></p>
                    <p><strong> Correo :</strong> <span><%=correo%></span></p>
                    <%if (estado.equals("Alumno")) {%>
                    <p><strong> Carrera :</strong> <span><%=carrera%></span></p>
                    <%
                        }
                    %>
                    <a  class="white-text btn  waves-effect waves-light  red" href="<%=estado%>.jsp">Volver</a>
                </div>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo center-align">Usuario</h4>     
                    <div class="input-field">                                
                        <p><strong> Usuario :</strong> <span><%=usuario%></span></p>
                        <a class="btn amber" href="cambiarContra.jsp">Cambiar Contraseña</a>
                    </div>
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
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
    </body>
</html>
