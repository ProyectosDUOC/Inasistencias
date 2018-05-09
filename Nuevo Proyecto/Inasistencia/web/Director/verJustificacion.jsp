<%-- 
    Document   : justificarSecretaria
    Created on : 04-may-2018, 11:05:51
    Author     : benja
--%>

<%@page import="dao.ImagenDAO"%>
<%@page import="dao.JustificacionDAO"%>
<%@page import="dao.InasistenciaDAO"%>
<%@page import="dao.ReporteSecretariaDAO"%>
<%@page import="modelo.JustificacionImagen"%>
<%@page import="modelo.ReporteSecretaria"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="modelo.GlobalSemestre"%>
<%@page import="modelo.Carrera"%>
<%@page import="dao.DirectorDAO"%>
<%@page import="modelo.Director"%>
<%@page import="dao.CarreraDAO"%>
<%@page import="dao.SecretariaDAO"%>
<%@page import="dao.DocenteDAO"%>
<%@page import="dao.RamoDAO"%>
<%@page import="dao.SeccionDAO"%>
<%@page import="modelo.Seccion"%>
<%@page import="modelo.Secretaria"%>
<%@page import="dao.AlumnoDAO"%>
<%@page import="modelo.Inasistencia"%>
<%@page import="modelo.Justificacion"%>
<%@page import="dao.MotivoDAO"%>
<%@page import="modelo.Motivo"%>
<%@page import="modelo.Motivo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Docente"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.ControlUsuario"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="../css/styleLogin.css">         
        <link rel="stylesheet" type="text/css" href="../css/lightbox.css">
        <link rel="shortcut icon" href="../images/favicon.ico?" type="images/favicon.ico" />
        <title>Justificar Inasistencia</title>
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");

            Alumno alum = new Alumno();
            Docente docente = new Docente();
            Secretaria secre = new Secretaria();
            Seccion seccion = new Seccion();
            Director dire = new Director();
            Carrera carrera = new Carrera();
            Inasistencia ina = new Inasistencia();
            Justificacion justi = new Justificacion();
            ReporteSecretaria reporte = new ReporteSecretaria();
            JustificacionImagen justiImg = new JustificacionImagen();

            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();

            String fechaA = "", glosa = "", rut = "", rutA = "", nombreA = "", carreraA = "", correoA = "", nombre = "", estado = "", semestre = "", nombreDocente = "", nombreAsig = "", nombreCod = "", motivo = "";
            String nombreDirector = "";
            int id = 0;

            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("director")) {
                    dire = (new DirectorDAO()).buscarDatos(user.getRutUsuario());
                    nombre = dire.getPnombre() + " " + dire.getSnombre() + " " + dire.getAppaterno() + " " + dire.getApmaterno();

                    if (session.getAttribute("idJ") != null) {
                        id = Integer.parseInt(sesion.getAttribute("idJ").toString());

                        reporte = (new ReporteSecretariaDAO()).buscarDatos(id);
                        alum = (new AlumnoDAO()).buscarDatosId(reporte.getIdAlumno());
                        ina = (new InasistenciaDAO()).buscar(reporte.getIdInasistencia());
                        justi = (new JustificacionDAO()).buscar(reporte.getIdJustificacion());
                        seccion = (new SeccionDAO()).buscar(ina.getIdSeccion());
                        carrera = (new CarreraDAO()).buscar(alum.getIdCarrera());
                        carreraA = carrera.getNombreCarrera();
                       justiImg = (new ImagenDAO()).buscar(justi.getIdJustificacion());
                        dire = (new DirectorDAO()).buscarDatos(carrera.getIdDirector());
                        nombreDirector = dire.getPnombre() + " " + dire.getAppaterno();

                        nombreAsig = (new RamoDAO()).buscar(seccion.getCodRamo()).getNombreRamo();
                        docente = (new DocenteDAO()).buscarDatos(seccion.getIdDocente());

                        nombreA = alum.getPnombre() + " " + alum.getSnombre() + " " + alum.getAppaterno() + " " + alum.getApmaterno();
                        nombreDocente = docente.getPnombre() + " " + docente.getSnombre() + " " + docente.getAppaterno() + " " + docente.getApmaterno();

                        glosa = justi.getGlosa();
                        motivo = (new MotivoDAO()).buscar(justi.getIdMotivo()).getNombreMotivo();
                        fechaA = parseador.format(ina.getFechaInasistencia());
                        sesion.setAttribute("reporte", reporte);
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
                                <a class="waves-effect waves-light" href="../configuracion.jsp"><i class="material-icons color-Amarillo-text left">settings_applications</i><span class="white-text"><strong>Configuraci�n</strong></span></a>&nbsp;&nbsp;&nbsp;
                                <a class="waves-effect waves-light" href="../index.jsp"><i class="material-icons color-Amarillo-text left">exit_to_app</i><span class="white-text"><strong>Salir</strong></span></a>                         
                            </div>                            
                        </div>
                    </div>
                </div>
            </div>                   
        </header>
        <div class="container">
            <div class="row">
                <h4 class="color-Plomo color-Azul-text center-align"></h4>
                <div class="col s12 m6 color-Azul-text">                    
                    <h4 class="color-Plomo center-align">Asignatura</h4>  
                    <p><strong>Nombre Asignatura :</strong> <span><%=nombreAsig%></span></p>
                    <p><strong>Secci�n :</strong> <span><%=seccion.getCodRamo()%></span></p>
                    <p><strong>Profesor : </strong><span><%=nombreDocente%></span></p>
                    <h4 class="color-Plomo center-align">Alumno</h4>  
                    <p><strong>Nombre Alumno :</strong> <span><%=nombreA%></span></p>
                    <p><strong>Carrera :</strong> <span><%=carreraA%></span></p>
                    <p><strong>Correo :</strong><span><%=alum.getEmail()%></span></p>
                    <h4 class="color-Plomo center-align">Director de Carrera</h4>  
                    <p><strong>Nombre Director :</strong> <span><%=nombreDirector%></span></p>
                    <br>
                    <a class="white-text btn  waves-effect waves-light  red" href="justificaciones.jsp">Volver</a>

                </div>

                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo center-align">Justificaci�n</h4>
                    <form method="post" action="../ControladorVerJustifi">
                        <table class="color-Plomo color-Azul-text">
                            <tr>
                                <td><p><strong>Fecha Inasistencia:</strong></td>
                                <td><p><%=fechaA%></p></td>
                            </tr>
                            <tr>
                                <td><p><strong>Fecha Justificacion:</strong></td>
                                <td><p><%=fechaA%></p></td>
                            </tr>
                            <tr>
                                <td><strong>Motivo:</strong></td>
                                <td><p><%=motivo%></p></td>
                            </tr>
                            <tr>
                                <td><strong>Glosa:</strong></td>                       
                                <td><p><%=glosa%></p></td>
                            </tr>
                        </table>
                        <br>                        
                        <div> 
                            <a href="../enviado.jsp?file=<%=justiImg.getNombreImagen()%>" target="_blank" class="btn green" ><strong>Ver Archivo <i class="material-icons">folder_open</i></strong></a>
                        </div>
                        <!--<div class="gallery">                       
                            <a href="files/" data-lightbox="mygallery" alt="Galer" data-title="Justificacion">
                            <img src="files/" alt="Justificacion alumno" class="circle" width="100" height="100"></a>
                        </div> -->    
                        <br>
                        <div>

                            <button class="btn waves-effect waves-light blue" type="submit" name="opcion" value="Rechazar"><strong>Rechazar</strong></button>
                            <button class="btn waves-effect waves-light blue" type="submit" name="opcion" value="Aprobar"><strong>Aprobar</strong></button>
                        </div> 
                    </form>
                </div>
            </div>
        </div>         
        <footer class="color-Azul">            
            <div class="container">
                <br>
                <p class="color-Amarillo-text center-align">Desarrollado por Estudiantes DUOC San Bernardo</p>                                
                <br>
            </div>
        </footer>  

        <script src="../js/lightbox-plus-jquery.min.js" type="text/javascript"></script>
    </body>
</html>