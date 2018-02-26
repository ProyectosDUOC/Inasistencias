<%-- 
    Document   : Docente
    Created on : 18-11-2017, 20:44:32
    Author     : Seba
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
            ControlUsuario user = (ControlUsuario) session.getAttribute("usuario");
            Docente docente = new Docente();
            int rut = 0, contador = 0;
            String estado = "", nombre = "", rutD = "", nombreAsig = "";
            AlumnoDAO alumnos = new AlumnoDAO();
            ArrayList<Seccion> arraySecciones = null;
            ArrayList<Inasistencia> arrayInasistencia = null;

            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("Docente")) {
                    rut = user.getRutUsuario();
                    docente = (new DocenteDAO()).buscarDatos(rut);
                    nombre = docente.getPnombre() + " " + docente.getSnombre() + " " + docente.getAppaterno() + " " + docente.getApmaterno();
                    rutD = rut + "-" + docente.getDvDocente();
                    arraySecciones = (new ClasesConsultas()).buscarSeccionesRut(rut);

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
                <h4 class="color-Azul-text color-Plomo center-align">Menu Docente</h4>
                <form action="ControladorDocente" method="post" >
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
                                    <th>Sección</th>                        
                                    <th>N° no Justificados</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>                                 
                                <%for (Seccion obj : arraySecciones) {
                                        nombreAsig = (new ClasesConsultas()).buscarRamos(obj.getIdRamo()).getNombreRamo();
                                %>
                                <tr>                                    
                                    <td><%=nombreAsig%></td>
                                    <td><%=obj.getIdSeccion()%></td>
                                    <td><%
                                        contador = 0;
                                        arrayInasistencia = (new InasistenciaDAO()).buscarSeccion(obj.getIdSeccion());
                                        for (Inasistencia ina : arrayInasistencia) {
                                            if (ina.getIdEstadoi() == 2) {
                                                contador++;
                                            }
                                        }
                                        %>
                                        <%=contador%>
                                    </td>
                                    <td> 
                                        <button 
                                            class="btn indigo darken-1" 
                                            type="submit" 
                                            name="opcion" 
                                            value="s<%=obj.getIdSeccion()%>"> 
                                            ver sección 
                                        </button>
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
