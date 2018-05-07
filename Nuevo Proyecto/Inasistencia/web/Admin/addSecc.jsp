<%-- 
    Document   : addSecc
    Created on : 06-05-2018, 2:41:10
    Author     : carlos
--%>

<%@page import="dao.DocenteDAO"%>
<%@page import="dao.RamoDAO"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.Seccion"%>
<%@page import="modelo.Docente"%>
<%@page import="modelo.Ramo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.AdministradorDAO"%>
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
        <link href="../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="../css/styleLogin.css">     
        <link rel="shortcut icon" href="../images/favicon.ico?" type="images/favicon.ico" />
        <title>Menu Principal Coordinador</title>
        <%
            HttpSession sesion = request.getSession(true);
            Administrador admin = new Administrador();
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            String nombre = "", estado = "", xCrud = "";
            String nomDoce = "";

            //Seccion
            ArrayList<Ramo> ramos = new ArrayList<Ramo>();
            ArrayList<Docente> docentes = new ArrayList<Docente>();

            if (user == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("administrador")) {
                    String rut = user.getRutUsuario();
                    admin = (new AdministradorDAO()).buscarDatos(rut);
                    nombre = admin.getPnombre() + " " + admin.getSnombre() + " " + admin.getAppaterno() + " " + admin.getApmaterno();
                    xCrud = sesion.getAttribute("xCrud").toString();
                    
                    ramos = (new RamoDAO()).mostrarDatos();
                    //docentes (new DocenteDAO()).mostrarDatos();
                
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
        <div class="row">
            <h4 class="color-Plomo color-Azul-text center-align" >Mi Cuenta</h4>

            <div class="col s12 m6 color-Azul-text">

                <p><strong> Semestre :</strong> <input type="text" name="txtUser" required="" maxlength="10"/> </p>
                <p><strong> Anio :</strong> <input type="text" name="txtUser" required="" maxlength="10"/> </p>

                <a  class="white-text btn  waves-effect waves-light  red" href="<%=estado%>.jsp">Volver</a>
                <!--Editar -->
                <a  class="white-text btn  waves-effect waves-light  red" href="<%=estado%>.jsp">Agregar</a>

            </div>    
            <!--Ramo -->
            <%if (xCrud.equals("1") && !ramos.isEmpty()) {%>
            <div class="col s12 m6 color-Azul-text">
                <h4 class="color-Plomo center-align">Ramo</h4>     
                <div class="input-field">                                   
                    <p><strong> Carrera :</strong> 
                        <select name="carreras" class="color-Azul color-Amarillo-text browser-default" required="">
                            <option value="" disabled selected>Seleccione Ramo</option>                    
                            <% for (Ramo ram : ramos) {

                            %>
                            <option value="<%=ram.getCodRamo()%>" >
                                <%= ram.getNombreRamo()%>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </p>
                </div>
            </div>
            <%
                }
            %>
            <!--Docente -->
            <%if (xCrud.equals("1") && !docentes.isEmpty()) {%>
            <div class="col s12 m6 color-Azul-text">
                <h4 class="color-Plomo center-align">Docente</h4>     
                <div class="input-field">                                   
                    <p><strong> Docente :</strong> 
                        <select name="carreras" class="color-Azul color-Amarillo-text browser-default" required="">
                            <option value="" disabled selected>Seleccione Docente</option>                    
                            <% for (Docente doce : docentes) {
                                    nomDoce = doce.getPnombre() + " " + doce.getAppaterno();
                            %>
                            <option value="<%=doce.getIdDocente()%>" >
                                <%= nomDoce%>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </p>
                </div>
            </div>
            <%
                }
            %>   
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
