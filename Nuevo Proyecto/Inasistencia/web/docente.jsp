<%-- 
    Document   : docente
    Created on : 14-07-2018, 19:24:21
    Author     : benja
--%>

<%@page import="modelo.Docente"%>
<%@page import="dao.InasistenciaDAO"%>
<%@page import="modelo.Inasistencia"%>
<%@page import="dao.DocenteDAO"%>
<%@page import="dao.RamoDAO"%>
<%@page import="dao.SeccionDAO"%>
<%@page import="modelo.Ramo"%>
<%@page import="modelo.GlobalSemestre"%>
<%@page import="modelo.Seccion"%>   
<%@page import="modelo.ControlUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Docente - Porta de justificaciones</title>
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
            
            Docente doce = new Docente();
            String nombre = "", estado = "", rut = "", codigoSeccion="", nombreRamo="";
            int semestre = 0, year = 0, encontrado = 0;
            GlobalSemestre gl = new GlobalSemestre();
            ArrayList<Seccion> arrayCursos = new ArrayList<Seccion>();
            
            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("docente")) {
                    rut = user.getRutUsuario();
                    doce = (new DocenteDAO()).buscarDatos(rut);
                    if (doce != null) {
                        nombre = doce.getPnombre() + " " + doce.getSnombre() + " " + doce.getAppaterno() + " " + doce.getApmaterno();
                        gl = (GlobalSemestre) sesion.getAttribute("semestreActual");
                        
                        arrayCursos = (new SeccionDAO()).mostrarDocente(doce.getIdDocente(),gl.getAnio(), gl.getSemestre());
                        
                       //Encontrado
                        if (!arrayCursos.isEmpty()) {
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
                <form action="ControladorDocente" method="post" >
                    <div class="col s12 m12">
                        <a href="index.jsp" class="btn waves-effect waves-light red left" >Cerrar Sesión</a>
                    </div>
                    <div class="col s12 m12" style="overflow-x:auto;">
                        <table id="example" class="striped grey lighten-2 table table-striped table-bordered color-Azul-text" cellspacing="0"  width="100%"> 
                            <thead>
                                <tr class="amber darken-3">
                                    <th>Nombre Asignatura</th>
                                    <th>Asignatura/sección</th>
                                    <th>n° justificaciones nuevas</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if (encontrado == 0) {  %>
                                <tr>                                    
                                    <td></td>
                                    <td>No tienes Cursos Asignados<td>
                                    <td></td>
                                </tr>                                
                                <%   }
                                    if (encontrado == 1) {
                                        for (Seccion seccion : arrayCursos) {
                                            codigoSeccion= seccion.getCodSeccion();
                                            nombreRamo = (new RamoDAO()).buscar(seccion.getCodRamo()).getNombreRamo();

                                            int x = (new InasistenciaDAO()).contadorInasistenciasDocente(seccion.getIdSeccion());
                                %>
                                <tr>
                                    <td><%=nombreRamo%></td> 
                                    <td><%=codigoSeccion%></td> 
                                    <td><%=x%></td>
                                    <td>
                                        
                                        <button class="btn amber waves-effect waves-light" 
                                                type="submit" 
                                                name="opcion" 
                                                value="S<%=seccion.getIdSeccion()%>">
                                            Ver
                                        </button>
                                    </td>
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

