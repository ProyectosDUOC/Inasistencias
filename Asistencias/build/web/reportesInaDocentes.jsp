<%-- 
    Document   : reportesInaDocentes
    Created on : 25-feb-2018, 0:26:05
    Author     : el Carlos ;v 
--%>

<%@page import="dao.DirectorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.*" %>

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
        <title>Inasistencias por docente</title>
        <%
            HttpSession sesion = request.getSession(true);
            Director dire = new Director();
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            String nombre = " ", estado = " ";
            if (user == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("Director")) {
                        int rut = user.getRutUsuario();
                dire = (new DirectorDAO()).buscarDatos(rut);
                nombre = dire.getPnombre() + " "+ dire.getSnombre()+" "+ dire.getAppaterno() + " " + dire.getApmaterno();
                    }else{
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
        <%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="sql"   uri="http://java.sun.com/jsp/jstl/sql" %>
        <div class="container">
            <form name="reporte" action="ControladorPDF">
                <h4 class="yellow darken-1 center-align">Reporte Inasistencias en pdf</h4>       
                <button class="btn-large waves-effect waves-light  indigo darken-3 center-align" type="submit" name="btnver" value="Visualizar PDF" >
                    Crear PDF<i class="material-icons right">picture_as_pdf
                    </i>
                </button>
                <a  class="white-text btn-large  waves-effect waves-light  red" href="Director.jsp">Volver</a>
            </form>
            <sql:setDataSource
                driver="com.mysql.jdbc.Driver"
                url="jdbc:mysql://localhost:3306/instituto"
                user="root"
                password=""/>      

            <sql:query var="consulta">
                SELECT *
                FROM inasistencia
                JOIN seccion using(id_seccion) 
                JOIN docente using(rut_docente)
                JOIN justificacion using(id_inasistencia)
                JOIN motivo using(id_motivo)
                JOIN estado_inasistencia using(id_estadoi)
                where id_estadoi = 3
                order by rut_docente;
            </sql:query>
            <div style="overflow-x:auto;">
                <table id="example" class="striped grey lighten-2 table table-striped table-bordered" cellspacing="0"  width="100%">
                    <thead>
                        <tr class="amber darken-3">                    
                            <th>Rut Docente</th> 
                            <th>Nombre Docente</th>
                            <th>Seccion</th>
                            <th>Rut alumno</th>
                            <th>Fecha inasistencia</th>
                            <th>Motivo</th>
                            <th>Dscripcion del Motivo</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="row" items="${consulta.rows}">
                            <tr>
                                <td>${row.rut_docente} - 
                                    ${row.dv_docente}</td>
                                <td>
                                    ${row.pnombre}
                                    ${row.snombre}
                                    ${row.appaterno}
                                    ${row.apmaterno}
                                </td>
                                <td>
                                    ${row.id_seccion}
                                </td>
                                <td>${row.rut_alumno}</td>
                                <td>
                                    ${row.fecha} 
                                </td>
                                <td>
                                    ${row.nombre_motivo} 
                                </td>
                                <td>
                                    ${row.glosa}
                                </td>
                                <td>${row.nombre_estadoi}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>  
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
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dataTables.js"></script>
        <script src="js/script.js"></script>
    </body>
</html>
