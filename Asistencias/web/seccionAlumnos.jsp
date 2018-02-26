<%-- 
    Document   : seccionAlumnos
    Created on : 26-feb-2018, 15:47:32
    Author     : benja
--%>
<%@page import="modelo.Motivo"%>
<%@page import="dao.JustificacionDAO"%>
<%@page import="modelo.Seccion"%>
<%@page import="dao.DocenteDAO"%>
<%@page import="modelo.Docente"%>
<%@page import="dao.InasistenciaDAO"%>
<%@page import="dao.AlumnoDAO"%>
<%@page import="modelo.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ClasesConsultas"%>
<%@page import="modelo.Inasistencia"%>
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
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css">   
        <title>Docente</title>
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");

            Docente docente = new Docente();
            int rut = 0, contador = 0;
            String estado = "", nombre = "", rutD = "", idSeccion="", nombreAsig = "";
            Alumno alumno = new Alumno();
            ArrayList<Inasistencia> arrayInasistencia = new ArrayList<Inasistencia>();
            Seccion seccion = new Seccion();
            if (session.getAttribute("usuario") == null || sesion.getAttribute("idSeccion")==null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("Docente")) {                    
                    idSeccion = sesion.getAttribute("idSeccion").toString();
                    rut = user.getRutUsuario();
                    docente = (new DocenteDAO()).buscarDatos(rut);
                    nombre = docente.getPnombre() + " " + docente.getSnombre() + " " + docente.getAppaterno() + " " + docente.getApmaterno();
                    rutD = rut + "-" + docente.getDvDocente();
                    arrayInasistencia = (new InasistenciaDAO()).buscarSeccionOrdenFecha(idSeccion);
                    seccion = (new ClasesConsultas()).buscarSeccion(idSeccion);
                    nombreAsig = (new ClasesConsultas()).buscarRamos(seccion.getIdRamo()).getNombreRamo();
                              
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
                <h4 class="color-Azul-text color-Plomo center-align"><%=nombreAsig+" - "+idSeccion%></h4>
                <form action="ControladorSeccionAlumnos" method="post" >
                    <div class="col s12 m12">
                        <a class="btn waves-effect waves-light red" href="<%=estado%>.jsp">Atras</a>
                    </div>
                    <div class="col s12 m12" style="overflow-x:auto;">
                        <table id="example" class="striped grey lighten-2 table table-striped table-bordered color-Azul-text" cellspacing="0"  width="100%"> 
                            <thead>
                                <tr class="amber darken-3">
                                    <th>Rut Alumno</th>
                                    <th>Nombre Alumno</th>                        
                                    <th>Fecha Inasistencia</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>                                 
                                <%for (Inasistencia obj : arrayInasistencia) {
                                    alumno = (new AlumnoDAO()).buscarDatos(obj.getRutAlumno());
                                %>
                                <tr>                                    
                                    <td><%=alumno.getRutAlumno()%>-<%=alumno.getDvAlumno()%></td>
                                    <td><%=alumno.getPnombre()%> <%=alumno.getAppaterno()%></td>
                                    <td><input type="date" name="fecha" value="<%=obj.getFecha()%>" readonly=""></td>
                                    <td>
                                        <%if (obj.getIdEstadoi()==2) { %>
                                        <button 
                                            class="btn amber darken-1" 
                                            type="submit" 
                                            name="opcion" 
                                            value="j<%=obj.getIdInasistencia()%>"> 
                                            Justificar
                                        </button>
                                        <%}%>
                                        <%if (obj.getIdEstadoi()==3) { %>
                                        <button 
                                            class="btn color-Azul darken-1" 
                                            type="submit" 
                                            name="opcion" 
                                            value="v<%=obj.getIdInasistencia()%>"> 
                                            ver
                                        </button>
                                        <%}%>
                                        <%if (obj.getIdEstadoi()==1) { %>
                                        No ha justificado
                                        <%}%>
                                    </td>
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
    </body>
</html>
