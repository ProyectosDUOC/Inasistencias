<%-- 
    Document   : configuracion
    Created on : 23-feb-2018, 16:44:49
    Author     : benja
--%>

<%@page import="dao.DirectorDAO"%>
<%@page import="dao.AdministradorDAO"%>
<%@page import="dao.CarreraDAO"%>
<%@page import="modelo.Secretaria"%>
<%@page import="dao.AlumnoDAO"%>
<%@page import="modelo.Docente"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.Director"%>
<%@page import="dao.SecretariaDAO"%>
<%@page import="modelo.ControlUsuario"%>
<%@page import="modelo.Administrador"%>
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
        <title>Configuracion | </title>
        <%
            HttpSession sesion = request.getSession(true);

            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            Secretaria secre = new Secretaria();
            Director dire = new Director();
            Docente doce = new Docente();
            Alumno alum = new Alumno();
            Administrador admin = new Administrador();
            String nombre = "", correo = "", usuario = "", clave = "", carrera = "", rut = "";
            String estado = "";

            if (user == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                usuario = user.getUsuario();
                clave = user.getClave();
                rut = user.getRutUsuario();
                if (estado.equals("alumno")) {
                    alum = (new AlumnoDAO()).buscarDatos(rut);
                    nombre = alum.getPnombre() + " " + alum.getSnombre() + " " + alum.getAppaterno() + " " + alum.getApmaterno();
                    correo = alum.getEmail();
                    carrera = (new CarreraDAO()).buscar(alum.getIdCarrera()).getNombreCarrera();
                }
                if (estado.equals("docente")) {
                  //  doce = (new DocenteDAO()).buscarDatos(rut);
                    nombre = doce.getPnombre() + " " + doce.getSnombre() + " " + doce.getAppaterno() + " " + doce.getApmaterno();
                    correo = doce.getEmail();
                }
                if (estado.equals("director")) {
                    dire = (new DirectorDAO()).buscarDatos(rut);
                    nombre = dire.getPnombre() + " " + dire.getSnombre() + " " + dire.getAppaterno() + " " + dire.getApmaterno();                   
                    correo = dire.getEmail();
                }
                if (estado.equals("administrador")) {
                    admin = (new AdministradorDAO()).buscarDatos(rut);
                    nombre = admin.getPnombre() + " " + admin.getSnombre() + " " + admin.getAppaterno() + " " + admin.getApmaterno();               
                    correo = admin.getEmail();
                }
                if (estado.equals("secretaria")) {
                    secre = (new SecretariaDAO()).buscarDatos(rut);
                    nombre = secre.getPnombre() + " " + secre.getSnombre() + " " + secre.getAppaterno() + " " + secre.getApmaterno();               
                    correo = secre.getEmail();
                    sesion.setAttribute("rut",null );
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
                <h4 class="color-Plomo color-Azul-text center-align" >Mi Cuenta</h4>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo color-Azul-text center-align" >Datos Personales</h4>  
                    <p><strong> Nombre :</strong> <span><%=nombre%></span></p>
                    <p><strong> Rut :</strong> <span><%=rut%></span></p>
                    <p><strong> Correo :</strong> <span><%=correo%></span></p>                    
                    <%if (estado.equals("alumno")) {%>
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
                <br>
            </div>
        </footer> 
    </body>
</html>
