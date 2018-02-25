<%-- 
    Document   : reporteInasistencias
    Created on : 19-11-2017, 22:03:06
    Author     : carlos
--%>

<%@page import="dao.DirectorDAO"%>
<%@page import="modelo.Director"%>
<%@page import="dao.DocenteDAO"%>
<%@page import="modelo.Docente"%>
<%@page import="dao.CoordinadorDAO"%>
<%@page import="modelo.Coordinador"%>
<%@page import="modelo.Coordinador"%>
<%@page import="modelo.ControlUsuario"%>
<%@page import="dao.ClasesConsultas" %>
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
        <title>Inasistencia por alumno</title>          
        <%
            HttpSession sesion = request.getSession(true);

            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            Coordinador coor = new Coordinador();
            Director dire = new Director();
            Docente doce = new Docente();            
            String nombre = " ", correo = " ", usuario = " ", clave = " ", rutD = " ";
            String estado = " ";

            if (user == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                usuario = user.getUsuario();
                clave = user.getClave();
                int rut = user.getRutUsuario();
                
                if (estado.equals("Docente") || estado.equals("Alumno")) {
                    response.sendRedirect("index.jsp");              
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
                <h4 class="yellow darken-1 center-align">Reporte Inasistencias Justificado</h4>
            </div>
        </div>  
        <%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="sql"   uri="http://java.sun.com/jstl/sql" %>
        <sql:setDataSource
            driver="com.mysql.jdbc.Driver"
            url="jdbc:mysql://localhost:3306/instituto"
            user="root"
            password=""/>      

        <sql:query var="reportes">
            select  * from justificacion JOIN inasistencia using(id_inasistencia) JOIN alumno using (rut_alumno)join seccion using(id_seccion) join ramo using(id_ramo) join motivo USING(id_motivo) where id_estadoj = 1;
        </sql:query>
        <div class="container">
            <div class="col s12 m6">
                <a  class="white-text btn  waves-effect waves-light  red" href="${sessionScope.tipoUsuario}.jsp">Volver</a>
                <div style="overflow-x:auto;">
                    <table id="example" class="striped grey lighten-2 table table-striped table-bordered" cellspacing="0"  width="100%">
                        <thead>
                            <tr class="amber darken-3 black-text">
                                <th>Rut Alumno</th> 
                                <th>Nombre Alumno</th>
                                <th>Fecha Inasistencia</th>
                                <th>Asignatura</th>
                                <th>Seccion</th>
                                <th>Motivo</th>
                                <th>Descripcion del Motivo</th>
                            </tr>
                        </thead>
                        <tbody>                        
                            <c:forEach var="row" items="${reportes.rows}">
                                <tr>
                                    <td>${row.rut_alumno}-${row.dv_alumno}</td>
                                    <td>
                                        ${row.pnombre}
                                        ${row.snombre}
                                        ${row.appaterno}
                                        ${row.apmaterno}
                                    </td>
                                    <td>
                                        <input type="date" name="fecha" value="${row.fecha}" readonly="">
                                    </td>   
                                    <td>
                                        ${row.nombre_ramo}
                                    </td>
                                    <td>
                                        ${row.id_seccion}
                                    </td>
                                    <td>
                                        ${row.nombre_motivo} 
                                    </td>
                                    <td>
                                        ${row.glosa}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>    
        <br>
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
