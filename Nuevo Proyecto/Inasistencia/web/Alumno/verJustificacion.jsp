<%-- 
    Document   : justificarSecretaria
    Created on : 04-may-2018, 11:05:51
    Author     : benja
--%>

<%@page import="dao.ImagenDAO"%>
<%@page import="dao.MotivoDAO"%>
<%@page import="dao.DocenteDAO"%>
<%@page import="dao.RamoDAO"%>
<%@page import="dao.DirectorDAO"%>
<%@page import="dao.CarreraDAO"%>
<%@page import="dao.SeccionDAO"%>
<%@page import="dao.AlumnoDAO"%>
<%@page import="dao.SecretariaDAO"%>
<%@page import="dao.JustificacionDAO"%>
<%@page import="dao.InasistenciaDAO"%>
<%@page import="dao.InasistenciaDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.Carrera"%>
<%@page import="modelo.JustificacionImagen"%>
<%@page import="modelo.Justificacion"%>
<%@page import="modelo.Inasistencia"%>
<%@page import="modelo.Director"%>
<%@page import="modelo.Seccion"%>
<%@page import="modelo.Secretaria"%>
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
        <link rel="shortcut icon" href="../images/favicon.ico" type="images/favicon.ico" />
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
            JustificacionImagen justiImg = new JustificacionImagen();

            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();

            String fechaA = "", glosa = "", rut = "", fechaJ = "", rutA = "", nombreA = "", carreraA = "", correoA = "", nombre = "", estado = "", semestre = "", nombreDocente = "", nombreAsig = "", nombreCod = "", motivo = "";
            String nombreDirector = "", fecha2 = "", jornada = "", telefono = "", nombreS = "", fechaJusti = "";
            int id = 0, isImg = 0;

            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("alumno")) {
                    rut = user.getRutUsuario();
                    alum = (new AlumnoDAO()).buscarDatos(rut);
                    if (sesion.getAttribute("idInasistencia") != null) {
                        ina = (new InasistenciaDAO()).buscar(Integer.parseInt(sesion.getAttribute("idInasistencia").toString()));
                        justi = (new JustificacionDAO()).buscarSubdirector(ina.getIdInasistencia());
                        
                        alum = (new AlumnoDAO()).buscarDatosId(ina.getIdAlumno());
                        seccion = (new SeccionDAO()).buscar(ina.getIdSeccion());
                        carrera = (new CarreraDAO()).buscar(alum.getIdCarrera());
                        carreraA = carrera.getNombreCarrera();
                        dire = (new DirectorDAO()).buscarDatos(carrera.getIdDirector());
                        nombreDirector = dire.getPnombre() + " " + dire.getAppaterno();

                        nombreAsig = (new RamoDAO()).buscar(seccion.getCodRamo()).getNombreRamo();
                        docente = (new DocenteDAO()).buscarDatos(seccion.getIdDocente());

                        nombreA = alum.getPnombre() + " " + alum.getSnombre() + " " + alum.getAppaterno() + " " + alum.getApmaterno();
                        nombreDocente = docente.getPnombre() + " " + docente.getAppaterno();

                        glosa = justi.getGlosa();
                        motivo = (new MotivoDAO()).buscar(justi.getIdMotivo()).getNombreMotivo();
                        fechaA = parseador.format(ina.getFechaInasistencia());
                        telefono = alum.getCelular();
                        jornada = alum.getJornada();
                        if (jornada.equals("D")) {
                            jornada = "Diurno";
                        } else {
                            jornada = "Vespertino";
                        }
                        fechaJ = justi.getFechaJustificacion().toString();
                        if (ina.getFechaInasistencia2() != null) {
                            fecha2 = parseador.format(ina.getFechaInasistencia2());
                        }
                        if((new ImagenDAO()).buscar(justi.getIdJustificacion())!=null){
                            justiImg = (new ImagenDAO()).buscar(justi.getIdJustificacion());
                            if (justiImg != null) {
                                isImg = 1;
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
                <h4 class="color-Plomo color-Azul-text center-align"></h4>
                <div class="col s12 m6 color-Azul-text">                    
                    <h4 class="color-Plomo center-align">Asignatura</h4>  
                    <p><strong>Nombre Asignatura : </strong> <span><%=nombreAsig%></span></p>
                    <p><strong>Sección : </strong> <span><%=seccion.getCodSeccion()%></span></p>
                    <p><strong>Profesor : </strong><span><%=nombreDocente%></span></p>
                    <h4 class="color-Plomo center-align">Alumno</h4>  
                    <p><strong>Nombre Alumno : </strong> <span><%=nombreA%></span></p>
                    <p><strong>Carrera : </strong> <span><%=carreraA%></span></p>
                    <p><strong>Correo : </strong><span><%=alum.getEmail()%></span></p>
                    <p><strong>Jornada : </strong><span><%=jornada%></span></p>
                    <p><strong>Celular : </strong><span><%=telefono%></span></p>
                    <h4 class="color-Plomo center-align">Director de Carrera</h4>  
                    <p><strong>Nombre Director :</strong> <span><%=nombreDirector%></span></p>
                    <br>
                    <a class="white-text btn  waves-effect waves-light  red" href="../alumno.jsp">Volver</a>

                </div>

                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo center-align">Justificación</h4>
                    <table class="color-Plomo color-Azul-text">
                        <tr>
                            <td><p><strong>Fecha Inasistencia:</strong></td>
                            <td><p><%=fechaA%></p></td>
                        </tr>
                        <%if (!fecha2.isEmpty()) {%>
                        <tr>
                            <td><p><strong>Hasta </strong></td>
                            <td><p><%=fecha2%></p></td>
                        </tr>     
                        <%    }
                        %>

                        <tr>
                            <td><strong>Motivo:</strong></td>
                            <td><p><%=motivo%></p></td>
                        </tr>
                        <tr>
                            <td><strong>Glosa:</strong></td>                       
                            <td><p><%=glosa%></p></td>
                        </tr>
                        <tr>
                            <td><strong>Estado actual:</strong> </td>
                            <td>
                                <%if (ina.getIdEstadoi() == 1) {%>
                                <button class="btn amber waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="">
                                    Pendiente
                                </button>
                                <%}%>
                                <%if (ina.getIdEstadoi() == 2) {%>
                                <button class="btn green waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="">
                                    Ver justificación
                                </button>
                                <%}%>
                                <%if (ina.getIdEstadoi() == 3) {%>
                                <button class="btn blue darken-1 waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="">
                                    Enviado por Secretaria
                                </button>
                                <%}%>
                                <%if (ina.getIdEstadoi() == 4) {%>
                                <button class="btn green waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="">
                                    Aprobado por Docente
                                </button>
                                <%}%>
                                <%if (ina.getIdEstadoi() == 5) {%>
                                <button class="btn red waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="">
                                    No Aprobado por Docente
                                </button>
                                <%}%>
                                <%if (ina.getIdEstadoi() == 6) {%>
                                <button class="btn green waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="">
                                    Aprobado por Director
                                </button>
                                <%}%>
                                <%if (ina.getIdEstadoi() == 7) {%>
                                <button class="btn red waves-effect waves-light" 
                                        type="submit" 
                                        name="opcion" 
                                        value="">
                                    No Aprobado por Director
                                </button>
                                <%}%>
                            </td>   
                        </tr>
                    </table>
                    <br>  
                    <%if (isImg
                                == 1) {
                    %>
                    <div> 
                        <a href="../enviado.jsp?file=<%=justiImg.getNombreImagen()%>" target="_blank" class="btn green" ><strong>Ver Archivo <i class="material-icons">folder_open</i></strong></a>
                    </div>
                    <%}%> 
                    <br>
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
    </body>
</html>
