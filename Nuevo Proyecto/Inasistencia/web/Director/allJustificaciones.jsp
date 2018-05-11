<%-- 
    Document   : justificaciones
    Created on : 06-may-2018, 1:52:15
    Author     : benja
--%>

<%@page import="dao.AlumnoDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dao.SeccionDAO"%>
<%@page import="modelo.Seccion"%>
<%@page import="modelo.Ramo"%>
<%@page import="dao.RamoDAO"%>
<%@page import="modelo.Inasistencia"%>
<%@page import="dao.InasistenciaDAO"%>
<%@page import="dao.GlobalSemestreDAO"%>
<%@page import="modelo.GlobalSemestre"%>
<%@page import="dao.ReporteSecretariaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.ReporteSecretaria"%>
<%@page import="dao.DirectorDAO"%>
<%@page import="modelo.Director"%>
<%@page import="modelo.ControlUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>

        <link rel="stylesheet" type="text/css" href="../css/jquery.dataTables.min.css"> 
        <link rel="stylesheet" type="text/css" href="../css/styleLogin.css">  
        <link rel="shortcut icon" href="../images/favicon.ico?" type="images/favicon.ico" />
        <title>Menu Director</title>
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            Director dire = new Director();
            ReporteSecretaria reportes = new ReporteSecretaria();
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yyyy");
            ArrayList<ReporteSecretaria> arrayReportes = new ArrayList<ReporteSecretaria>();
            String nombre = "", estado = "", rut = "", fecha2="";
            Inasistencia inasistencia = new Inasistencia();
            Ramo ramo = new Ramo();
            Seccion seccion = new Seccion();
            GlobalSemestre semestreActual = new GlobalSemestre();
            if (user == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("director")) {
                    rut = user.getRutUsuario();
                    dire = (new DirectorDAO()).buscarDatos(rut);

                    semestreActual = (new GlobalSemestreDAO()).buscar();
                    System.out.println(semestreActual.getAnio() + " " + semestreActual.getSemestre());
                    nombre = dire.getPnombre() + " " + dire.getSnombre() + " " + dire.getAppaterno() + " " + dire.getApmaterno();
                    arrayReportes = (new ReporteSecretariaDAO()).mostrarDatosAll(dire.getIdDirector(), semestreActual.getSemestre(), semestreActual.getAnio());
                    sesion.setAttribute("reporte", null);
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
                <h4 class="color-Azul-text color-Plomo center-align">Todas las Justificaciones</h4>
                <div class="col s12 m12" style="overflow-x:auto;">
                    <form action="../ControladorJustificacion" method="POST" >
                        <table id="example" class="striped grey lighten-2 table table-striped table-bordered color-Azul-text" cellspacing="0"  width="100%"> 
                            <thead>
                                <tr class="amber darken-3">
                                    <th>Nombre Asignatura</th>
                                    <th>Asignatura/sección</th>
                                    <th>Rut Alumno</th>
                                    <th>Fecha Inasistencia</th>
                                    <th>Acción</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if (arrayReportes.isEmpty()) { %>
                                <tr>
                                    <td></td>                                    
                                    <td></td>
                                    <td>No se han encontrados nuevos registros</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <% } else {
                                    for (ReporteSecretaria r : arrayReportes) {
                                        inasistencia = (new InasistenciaDAO()).buscar(r.getIdInasistencia());
                                        seccion = (new SeccionDAO()).buscar(inasistencia.getIdSeccion());
                                        ramo = (new RamoDAO()).buscar(seccion.getCodRamo());

                                        if(inasistencia.getFechaInasistencia2()!=null){
                                            fecha2="  hasta  "+ parseador.format(inasistencia.getFechaInasistencia2());
                                        }
                                %>
                                <tr>
                                    <td><%=ramo.getNombreRamo()%></td>
                                    <td><%=seccion.getCodSeccion()%></td>                                    
                                    <td><%=(new AlumnoDAO()).buscarDatosId(r.getIdAlumno()).getRutAlumno()%></td>
                                    <td><%=parseador.format(inasistencia.getFechaInasistencia())+fecha2%></td>
                                    <%fecha2="";%>
                                    <td>
                                    <%if(r.getActivo()==1){%>
                                        <button class="btn amber waves-effect waves-light" 
                                                type="submit" 
                                                name="opcion" 
                                                value="V<%=r.getIdJustificacion()%>">
                                            Pendiente
                                        </button>
                                    <%}%>
                                    <%if(r.getActivo()==2){%>
                                        <button class="btn green waves-effect waves-light" 
                                                type="submit" 
                                                name="opcion" 
                                                value="V<%=r.getIdJustificacion()%>">
                                            Aprobado
                                        </button>
                                    <%}%>
                                    <%if(r.getActivo()==3){%>
                                        <button class="btn red waves-effect waves-light" 
                                                type="submit" 
                                                name="opcion" 
                                                value="V<%=r.getIdJustificacion()%>">
                                            Rechazado
                                        </button>
                                    <%}%>
                                    </td>
                                </tr>       
                                <%     }

                                }%>                        
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
