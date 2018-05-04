<%-- 
    Document   : alumno
    Created on : 03-may-2018, 18:57:13
    Author     : benja
--%>

<%@page import="dao.DocenteDAO"%>
<%@page import="dao.RamoDAO"%>
<%@page import="dao.SeccionDAO"%>
<%@page import="modelo.Ramo"%>
<%@page import="modelo.GlobalSemestre"%>
<%@page import="modelo.Seccion"%>   
<%@page import="dao.AlumnoDAO"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.ControlUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumno</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css">        
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css"> 
        <link rel="shortcut icon" href="images/favicon.ico?" type="images/favicon.ico" />
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            Alumno alu = new Alumno();
            String nombre = "", estado = "", rut = "";
            int semestre = 0, year = 0;
            ArrayList<Seccion> arrayCursos = new ArrayList<Seccion>();
            Seccion seccion = new Seccion();
            Ramo ramo = new Ramo();
            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("alumno")) {
                    rut = user.getRutUsuario();
                    alu = (new AlumnoDAO()).buscarDatos(rut);
                    if (alu != null) {
                        nombre = alu.getPnombre() + " " + alu.getSnombre() + " " + alu.getAppaterno() + " " + alu.getApmaterno();
                        semestre = (new GlobalSemestre()).getSemestre();
                        year = (new GlobalSemestre()).getAnio();
                        arrayCursos = (new SeccionDAO()).seccionesAlumnoAnyoSemestre(alu.getIdAlumno(), semestre, year);
                    } else {
                        response.sendRedirect("index.jsp");
                    }

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
                <h4 class="color-Azul-text color-Plomo center-align">Centro de Notificaciones Duoc</h4>
                <form action="index.jsp" method="post" >
                    <div class="col s12 m12">
                        <button class="btn waves-effect waves-light red left" type="submit" name="opcion" value="Salir">
                            Cerrar Sesion
                        </button>
                    </div>
                    <div class="col s12 m12" style="overflow-x:auto;">
                        <table id="example" class="striped grey lighten-2 table table-striped table-bordered color-Azul-text" cellspacing="0"  width="100%"> 
                            <thead>
                                <tr class="amber darken-3">
                                    <th>Nombre Asignatura</th>
                                    <th>Asignatura/sección</th>                                    
                                    <th>Fecha</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if (arrayCursos.isEmpty()) {  %>
                                <tr><td>No tienes cursos registrados<td></tr>
                                <%   } else {
                                    for (Seccion xx : arrayCursos) {%>
                            <td><%=(new RamoDAO()).buscar(xx.getCodRamo()).getNombreRamo()%></td> 
                            <td><%=xx.getCodRamo()%></td> 
                            <td><%=(new DocenteDAO()).buscarDatos(xx.getIdDocente())%></td>
                            <td>0</td>
                            <button 
                                class="btn  color-Azul amber-text" 
                                type="submit" 
                                name="opcion" 
                                value="j<%=xx.getIdSeccion()%>"> 
                                Justificar 
                            </button>
                            <%
                                    }
                                }
                            %>
                            </tbody>
                        </table>  
                    </div>
                </form> 
            </div>
        </div>  
        <footer class="color-Azul">            
            <div class="container">
                <br>
                <p class="color-Amarillo-text center-align">Desarrollado por Estudiantes DUOC San Bernardo</p>                                
                <br>
            </div>
        </footer>        
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dataTables.js"></script>
        <script src="js/script.js"></script>        
    </body>
</html>
