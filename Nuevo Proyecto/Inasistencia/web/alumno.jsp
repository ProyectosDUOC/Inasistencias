<%-- 
    Document   : alumno
    Created on : 03-may-2018, 18:57:13
    Author     : benja
--%>

<%@page import="dao.InasistenciaDAO"%>
<%@page import="modelo.Inasistencia"%>
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
            int semestre = 0, year = 0, encontrado = 0;
            GlobalSemestre gl = new GlobalSemestre();
            ArrayList<Inasistencia> arrayInasistencia = new ArrayList<Inasistencia>();
            Seccion seccion = new Seccion();
            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("alumno")) {
                    rut = user.getRutUsuario();
                    alu = (new AlumnoDAO()).buscarDatos(rut);
                    if (alu != null) {
                        System.out.println("1");
                        nombre = alu.getPnombre() + " " + alu.getSnombre() + " " + alu.getAppaterno() + " " + alu.getApmaterno();
                        gl = (GlobalSemestre)sesion.getAttribute("semestreActual");
                        System.out.println("2" + nombre);
                        arrayInasistencia = (new InasistenciaDAO()).inasistenciaAlumnoActual(alu.getIdAlumno(), gl.getSemestre(), gl.getAnio());
                        System.out.println("4 = " + arrayInasistencia.isEmpty());
                        if (!arrayInasistencia.isEmpty()) {
                            encontrado = 1;
                        } else {
                            encontrado = 0;
                        }
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
                                    <th>Fecha Inasistencia</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if (encontrado == 0) {  %>
                                <tr>                                    
                                    <td></td>
                                    <td>No tienes inasistencias registrados<td>
                                    <td></td>
                                    <td></td>
                                </tr>                                
                                <%   } else {
                                    if (encontrado == 1) {
                                        for (Inasistencia ina : arrayInasistencia) {
                                            seccion = (new SeccionDAO()).buscar(ina.getIdSeccion());
                                %>
                            <tr>
                            <td><%=(new RamoDAO()).buscar(seccion.getCodRamo()).getNombreRamo()%></td> 
                            <td><%=seccion.getCodRamo()%></td> 
                            <td><%=ina.getFechaInasistencia()%></td>
                            <td>
                                <%if (ina.getIdEstadoi() == 1) {%>
                                <button class="btn amber waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="I<%=ina.getIdInasistencia()%>">
                                    Pendiente
                                </button>
                                <%}%>
                                <%if (ina.getIdEstadoi() == 2) {%>
                                <button class="btn green waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="v<%=ina.getIdInasistencia()%>">
                                    Ver justificación
                                </button>
                                <%}%>
                                <%if (ina.getIdEstadoi() == 3) {%>
                                <button class="btn blue darken-1 waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="v<%=ina.getIdInasistencia()%>">
                                    Enviado por Secretaria
                                </button>
                                <%}%>
                                <%if (ina.getIdEstadoi() == 4) {%>
                                <button class="btn green waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="v<%=ina.getIdInasistencia()%>">
                                    Aprobado por Docente
                                </button>
                                <%}%>
                                <%if (ina.getIdEstadoi() == 5) {%>
                                <button class="btn red waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="v<%=ina.getIdInasistencia()%>">
                                    No Aprobado por Docente
                                </button>
                                <%}%>
                                <%if (ina.getIdEstadoi() == 6) {%>
                                <button class="btn green waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="v<%=ina.getIdInasistencia()%>">
                                    Aprobado por Director
                                </button>
                                <%}%>
                                <%if (ina.getIdEstadoi() == 7) {%>
                                <button class="btn red waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="v<%=ina.getIdInasistencia()%>">
                                    No Aprobado por Director
                                </button>
                                <%}%>
                            </td>
                            <%
                                        }
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
