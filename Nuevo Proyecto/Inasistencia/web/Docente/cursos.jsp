<%-- 
    Document   : cursos
    Created on : 14-07-2018, 19:11:25
    Author     : benja
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dao.AlumnoDAO"%>
<%@page import="modelo.Alumno"%>
<%@page import="dao.DetalleSeccionDAO"%>
<%@page import="modelo.DetalleSeccion"%>
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
        <link href="../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="../css/styleLogin.css">        
        <link rel="stylesheet" type="text/css" href="../css/jquery.dataTables.min.css"> 
        <link rel="shortcut icon" href="../images/favicon.ico?" type="images/favicon.ico" />
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yyyy");
            Docente doce = new Docente();
            String nombre = "", estado = "", rut = "", nombreAlum="", codigoSeccion="", nombreRamo="", idSeccion="", rutAlumn="", fechaIna="";
            int semestre = 0, year = 0, encontrado = 0;
            Seccion seccion = new Seccion();
            Alumno alu = new Alumno();
            ArrayList<Inasistencia> arrayInasistencias = new ArrayList<Inasistencia>();
            
            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("docente")) {
                    rut = user.getRutUsuario();
                    doce = (new DocenteDAO()).buscarDatos(rut);
                    if (doce != null) {
                        nombre = doce.getPnombre() + " " + doce.getSnombre() + " " + doce.getAppaterno() + " " + doce.getApmaterno();
                        
                        if (session.getAttribute("idSeccion") == null) {
                            response.sendRedirect("../index.jsp"); 
                        }else{
                            idSeccion = sesion.getAttribute("idSeccion").toString();
                        
                            seccion = (new SeccionDAO()).buscar(Integer.parseInt(idSeccion));

                            arrayInasistencias = (new InasistenciaDAO()).inasistenciaSeccion(seccion.getIdSeccion());


                           //Encontrado
                            if (!arrayInasistencias.isEmpty()) {
                                encontrado = 1;
                            } else {
                                encontrado = 0;
                            }
                        }
                        
                    } else {
                        response.sendRedirect("../index.jsp");
                    }

                } else {
                    response.sendRedirect("../index.jsp");
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
        <div class="container">
            <div class="row">
                <h4 class="color-Azul-text color-Plomo center-align">Justificaciones pendientes</h4>
                <div class="col s12 m12" style="overflow-x:auto;">
                    <form action="../ControladorDocente" method="POST" >
                        <table id="example" class="striped grey lighten-2 table table-striped table-bordered color-Azul-text" cellspacing="0"  width="100%"> 
                            <thead>
                                <tr class="amber darken-3">                                                           
                                    <th>Nombre</th>
                                    <th>Rut Alumno</th>                                    
                                    <th>Fecha Inasistencia</th>
                                    <th>Acción</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if (encontrado==0) { %>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td>No se han encontrados registros</td>
                                    <td></td>
                                </tr>
                                <% } else {
                                    for (Inasistencia ina : arrayInasistencias) {  

                                      alu = (new AlumnoDAO()).buscarDatosId(ina.getIdAlumno());
                                        rutAlumn = alu.getRutAlumno();
                                        fechaIna = parseador.format(ina.getFechaInasistencia());
                                        nombreAlum =alu.getPnombre() + " " + alu.getAppaterno();
                                %>    
                                        <tr>
                                            <td><%=nombreAlum%></td>
                                            <td><%=rutAlumn%></td>
                                            <td><%=fechaIna%></td>                                    
                                            <td>
                                                <% if (ina.getIdEstadoi()==2) { %>
                                                         <button class="btn amber waves-effect waves-light" 
                                                        type="submit" 
                                                        name="opcion" 
                                                        value="J<%=ina.getIdInasistencia()%>">
                                                    Justificado
                                                </button>
                                                 <%   }                                                
                                                 if (ina.getIdEstadoi()==4) { %>
                                                         <button class="btn green waves-effect waves-light" 
                                                        type="submit" 
                                                        name="opcion" 
                                                        value="V<%=ina.getIdInasistencia()%>">
                                                    Revisado
                                                </button>
                                                 <%   }                                                
                                                %>
                                               
                                            </td>
                                        </tr> 
                                     <% }
                                    }
                                %>                        
                            </tbody>
                        </table> 
                    </form>
                </div>
                <a class="btn  waves-effect waves-light  red" href="../<%=estado%>.jsp">Volver</a>     
            </div>
        </div>     
        <footer class="color-Azul">            
            <div class="container">
                <br>
                <p class="color-Amarillo-text center-align">Desarrollado por Estudiantes DUOC San Bernardo</p>                                
                <br>
            </div>
        </footer>        
        <script src="../js/jquery.min.js"></script>
        <script src="../js/jquery.dataTables.js"></script>
        <script src="../js/script.js"></script>        
    </body>
</html>

