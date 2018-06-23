<%-- 
    Document   : justificaciones
    Created on : 06-may-2018, 1:52:15
    Author     : benja
--%>

<%@page import="dao.MotivoDAO"%>
<%@page import="dao.JustificacionDAO"%>
<%@page import="modelo.Justificacion"%>
<%@page import="dao.SubSecretariaDAO"%>
<%@page import="dao.SubdirectorDAO"%>
<%@page import="modelo.Subdirector"%>
<%@page import="modelo.SecretariaSda"%>
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

            Subdirector subdire = new Subdirector();
            SecretariaSda secreSDA = new SecretariaSda();
            Justificacion justificacion = new Justificacion();
            Inasistencia inasistencia = new Inasistencia();
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yyyy");
            ArrayList<ReporteSecretaria> arrayReporte = new ArrayList<ReporteSecretaria>();
            String nombre = "", estado = "", rut = "", fecha2 = "", rutA = "", motivo="";

            Ramo ramo = new Ramo();
            Seccion seccion = new Seccion();
            GlobalSemestre semestreActual = new GlobalSemestre();
            int estadoIna=0;
            if (user == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                semestreActual = (new GlobalSemestreDAO()).buscar();
                rut = user.getRutUsuario();
                if (estado.equals("subdirector")) {
                    subdire = (new SubdirectorDAO()).buscarDatos(rut);
                    nombre = subdire.getPnombre() + " " + subdire.getSnombre() + " " + subdire.getAppaterno() + " " + subdire.getApmaterno();
                    arrayReporte = (new ReporteSecretariaDAO()).mostrarDatosSubdirector(semestreActual.getSemestre(), semestreActual.getAnio());

                } else {
                    if (estado.equals("secretariaSDA")) {
                        secreSDA = (new SubSecretariaDAO()).buscarDatos(rut);
                        nombre = secreSDA.getPnombre() + " " + secreSDA.getAppaterno();
                        arrayReporte = (new ReporteSecretariaDAO()).mostrarDatosSubdirector(semestreActual.getSemestre(), semestreActual.getAnio());

                    } else {
                        response.sendRedirect("index.jsp");
                    }
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
                            <a href="../director.jsp" class="color-Amarillo-text"><strong><i class="Tiny material-icons prefix">home</i></strong></a>  
                            <a href="../director.jsp" class="color-Amarillo-text"><strong><i class="Tiny material-icons prefix">person</i>Bienvenido </strong><span class="white-text"><%=nombre%></span></a>
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
                    <form action="../ControladorSubdirector" method="POST" >
                        <table id="example" class="striped grey lighten-2 table table-striped table-bordered color-Azul-text" cellspacing="0"  width="100%"> 
                            <thead>
                                <tr class="amber darken-3">
                                    <th>Nombre Asignatura</th>
                                    <th>Asignatura/sección</th>  
                                    <th>Motivo</th>        
                                    <th>Rut Alumno</th>                                    
                                    <th>Fecha Inasistencia</th>
                                    <th>Acción</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if (arrayReporte.isEmpty()) { %>
                                <tr>
                                    <td></td>                                    
                                    <td></td>
                                    <td>No se han encontrados nuevos registros</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <% } else {
                                    for (ReporteSecretaria r : arrayReporte) {
                                        inasistencia = (new InasistenciaDAO()).buscar(r.getIdInasistencia());
                                        justificacion = (new JustificacionDAO()).buscarSubdirector(inasistencia.getIdInasistencia());
                                        if (justificacion.getIdMotivo() == 11 || justificacion.getIdMotivo() == 3) {
                                            seccion = (new SeccionDAO()).buscar(inasistencia.getIdSeccion());
                                            ramo = (new RamoDAO()).buscar(seccion.getCodRamo());
                                            if (inasistencia.getFechaInasistencia2() != null) {
                                                fecha2 = "  hasta  " + parseador.format(inasistencia.getFechaInasistencia2());
                                            }
                                            rutA = (new AlumnoDAO()).buscarDatosId(inasistencia.getIdAlumno()).getRutAlumno();
                                            motivo =(new MotivoDAO()).buscar(justificacion.getIdMotivo()).getNombreMotivo();
                                            estadoIna = inasistencia.getIdEstadoi();
                                %>
                                <tr>
                                    <td><%=ramo.getNombreRamo()%></td>
                                    <td><%=seccion.getCodSeccion()%></td>                                         
                                    <td><%=motivo%></td>  
                                    <td><%=rutA%></td>
                                    <td><%=parseador.format(inasistencia.getFechaInasistencia()) + fecha2%></td>
                                    <%fecha2 = ""; //para que no se repita%>
                                    <td>
                                        <%if (estadoIna == 9) {%>
                                        <button class="btn amber waves-effect waves-light" 
                                                type="submit" 
                                                name="opcion" 
                                                value="V<%=justificacion.getIdJustificacion()%>">
                                           Ver
                                        </button>
                                        <%}
                                        if (estadoIna == 10) {%>
                                        <button class="btn blue waves-effect waves-light" 
                                                type="submit" 
                                                name="opcion" 
                                                value="V<%=justificacion.getIdJustificacion()%>">
                                            Revisado
                                        </button>
                                        <%}%>
                                    </td>
                                </tr>       
                                <%}
                                            }
                                        }%>                        
                            </tbody>
                        </table> 
                    </form>
                </div>
                <a class="btn  waves-effect waves-light  red" href="../director.jsp">Volver</a>     
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
