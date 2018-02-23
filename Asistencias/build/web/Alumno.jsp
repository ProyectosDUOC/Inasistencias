<%-- 
    Document   : Alumno
    Created on : 18-11-2017, 20:44:23
    Author     : Seba
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.*"%>
<%@page import="dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumno</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css">        
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css">        
        <%
            ControlUsuario user = null;
            int rutAlumno = 0;
            Alumno alu = new Alumno();
            ArrayList<Inasistencia> faltas = null;
            ClasesConsultas consultaBD = null;

            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            } else {
                user = (ControlUsuario) session.getAttribute("usuario");
                rutAlumno = user.getRutUsuario();
                alu = (new AlumnoDAO()).buscarDatos(rutAlumno);
                if (alu == null) {
                    response.sendRedirect("error.jsp");
                }
                faltas = (new InasistenciaDAO()).buscarRut(rutAlumno);
                consultaBD = new ClasesConsultas();
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
                        <br>
                        <div class="col s6 offset-s6">
                            <p class="color-Amarillo-text"><strong>Bienvenido</strong> <%=alu.getPnombre() + " " + alu.getAppaterno() + " " + alu.getApmaterno()%></p>
                        </div>
                        <br>
                    </div>
                </div>
            </div>
        </header>   
        <div class="container">
            <div class="row">
                <h4 class="color-Azul-text color-Plomo center-align">Centro de Notificaciones Duoc</h4>
                <form action="ControladorAlumno" method="post" >

                    <div class="col s12 m12">
                        <button class="btn waves-effect waves-light red right" type="submit" name="opcion" value="Salir">
                            Cerrar Sesion
                        </button>
                    </div>
                    <div class="col s12 m12" style="overflow-x:auto;">
                        <table id="example" class="striped grey lighten-2 table table-striped table-bordered" cellspacing="0"  width="100%"> 
                            <thead>
                                <tr class="amber darken-3 black-text">
                                    <th>Asignatura/sección</th>
                                    <th>Nombre Asignatura</th>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                    <th>Accion</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if (faltas.isEmpty()) {  %>
                                <tr><td>No tienes registrado inasistecias para justificar<td></tr>
                                <%   }
                                    String nombreAsig = "";
                                    for (Inasistencia falta : faltas) {
                                        nombreAsig = (new ClasesConsultas()).buscarRamos(new ClasesConsultas().buscarSeccion(falta.getIdSeccion()).getIdRamo()).getNombreRamo();
                                %>
                                <tr>  
                                    <%  if (falta.getIdEstadoi() != 0) {%>
                                    <td><%=falta.getIdSeccion()%></td>
                                    <td><%=nombreAsig%></td>
                                    <td><%=falta.getFecha()%></td>
                                    <td><%=consultaBD.buscarEstadoInasistencia(falta.getIdEstadoi()).getNombreEstadoi()%></td>
                                    <td>
                                        <% if (falta.getIdEstadoi() == 1) {%>
                                        <button 
                                            class="btn  color-Azul amber-text" 
                                            type="submit" 
                                            name="opcion" 
                                            value="j<%=falta.getIdInasistencia()%>"> 
                                            Justificar 
                                        </button>
                                        <% }%>
                                    </td>   
                                    <% } %>
                                </tr>
                                <% }%>
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
                <p class="color-Amarillo-text center-align">Carlos Orellana ★ Sebastian Orrego &#9733;  Benjamin Mora</p>
                <p class="color-Amarillo-text center-align"> &#9733; 2018 &#9733; </p>
                <br>
            </div>
        </footer>    
        <script src="js/materialize.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dataTables.js"></script>
        <script src="js/script.js"></script>
        <script>

            $(document).ready(function () {
                $('select').material_select();
            });
        </script>
    </body>
</html>
