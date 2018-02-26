<%-- 
    Document   : reportesSemetrales
    Created on : 20-nov-2017, 12:28:37
    Author     : benja
--%>

<%@page import="dao.DirectorDAO"%>
<%@page import="modelo.ControlUsuario"%>
<%@page import="modelo.Director"%>
<%@page import="modelo.Director"%>
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
        <link rel="shortcut icon" href="images/favicon.ico?" type="images/favicon.ico" />
        <title>Resportes semestrales</title>
        <%
            HttpSession sesion = request.getSession(true);
            Director dire = new Director();
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            String nombre = "", estado = "";
            String semestre = "", anio ="" ;
            if (user == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("Director")) {
                    int rut = user.getRutUsuario();
                    dire = (new DirectorDAO()).buscarDatos(rut);
                    nombre = dire.getPnombre() + " " + dire.getSnombre() + " " + dire.getAppaterno() + " " + dire.getApmaterno();
                    semestre =sesion.getAttribute("semestre").toString();
                    anio = sesion.getAttribute("anio").toString();
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
        <%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="sql"   uri="http://java.sun.com/jstl/sql" %>      
        <sql:setDataSource
            driver="com.mysql.jdbc.Driver"
            url="jdbc:mysql://localhost:3306/instituto"
            user="root"
            password=""/>

        <c:set var="filtro" value="where anio= ${param.a} and semestre= ${param.s} "/>

        <sql:query var="consulta">               
            SELECT 
            nombre_motivo , count(id_motivo) AS contador, id_motivo FROM instituto.justificacion INNER join instituto.motivo using(id_motivo) 
            where id_inasistencia in (select 
            id_inasistencia
            from 
            instituto.alumno join instituto.detalle_seccion using(rut_alumno) join instituto.inasistencia using(rut_alumno) 
            ${filtro}
            group by id_inasistencia) and id_estadoj = 1
            group by id_motivo;
        </sql:query>
        <sql:query var="Motivos">               
            SELECT 
            *
            FROM motivo;
        </sql:query>
        <sql:query var="NoS">               
            SELECT 
            nombre_motivo, 0 as Valor
            FROM instituto.motivo where nombre_motivo <> ' ' and id_motivo not in ( Select id_motivo from instituto.justificacion where id_inasistencia in ( select 
            id_inasistencia
            from 
            instituto.alumno join instituto.detalle_seccion using(rut_alumno) join instituto.inasistencia using(rut_alumno) 
            ${filtro}  
            group by id_inasistencia)     group by id_motivo);

        </sql:query>   
        <div class="container">
            <div class="row">
                <h4 class="color-Azul-text color-Plomo center-align">Reporte semestre <%=semestre%> del <%=anio%> </h4>
                <div class="col s12 m12" style="overflow-x:auto;">
                    <table id="example" class="striped grey lighten-2 table table-striped table-bordered color-Azul-text" cellspacing="0"  width="100%"> 
                    <thead class="amber darken-3">
                    <tr>      
                        <th>Motivo</th> 
                        <th>Cantiadad</th>
                    </tr>
                </thead>
                <tbody>     
                    <c:forEach var="mot" items="${Motivos.rows}">                   
                        <c:forEach var="row" items="${consulta.rows}">
                            <c:if test = "${row.nombre_motivo == mot.nombre_motivo}">
                                <tr>
                                    <td> ${row.nombre_motivo}</td>
                                    <td class="blue-text"> ${row.contador}</td>
                                </tr>
                            </c:if>                              
                        </c:forEach>
                    </c:forEach>   
                    <c:forEach var="no" items="${NoS.rows}">                   
                        <tr>
                            <td> ${no.nombre_motivo}</td>
                            <td class="blue-text"> ${no.valor}</td>
                        </tr>
                    </c:forEach>  
                </tbody>    
                    </table>   
                </div>
            </div>   
                <a  class="white-text btn  waves-effect waves-light  red" href="reportesSemestre.jsp">Volver</a>
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
