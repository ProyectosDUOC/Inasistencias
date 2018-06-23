<%-- 
    Document   : justificarSecretaria
    Created on : 04-may-2018, 11:05:51
    Author     : benja
--%>

<%@page import="dao.SubdirectorComentarioDAO"%>
<%@page import="modelo.SubdirectorComentario"%>
<%@page import="dao.SubSecretariaDAO"%>
<%@page import="dao.SubdirectorDAO"%>
<%@page import="modelo.SecretariaSda"%>
<%@page import="modelo.Subdirector"%>
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
            Subdirector subdirector = new Subdirector();
            SecretariaSda secretariaS = new SecretariaSda();
            Justificacion justi = new Justificacion();
            ReporteSecretaria reporte = new ReporteSecretaria();
            JustificacionImagen justiImg = new JustificacionImagen();
            ArrayList<SubdirectorComentario> comentarios = new ArrayList<SubdirectorComentario>();
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();

            String fechaA = "", glosa = "", rut = "", fechaJ = "", rutA = "", nombreA = "", carreraA = "", correoA = "", nombre = "", estado = "", semestre = "", nombreDocente = "", nombreAsig = "", nombreCod = "", motivo = "";
            String nombreDirector = "", fecha2 = "", jornada = "", telefono = "", secreSDA = "", color = "";
            int id = 0, activo = 0, isImg = 0, ingreso = 0, persona = 0;

            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();

                if (estado.equals("subdirector") || estado.equals("secretariaSDA")) {
                    ingreso = 1;
                    if (estado.equals("subdirector")) {
                        subdirector = (new SubdirectorDAO()).buscarDatos(user.getRutUsuario());
                        nombre = subdirector.getPnombre() + " " + subdirector.getAppaterno();
                        persona = 2;
                    } else {
                        secretariaS = (new SubSecretariaDAO()).buscarDatos(user.getRutUsuario());
                        nombre = secretariaS.getPnombre() + " " + secretariaS.getAppaterno();
                        persona = 1;

                    }

                } else {
                    response.sendRedirect("../index.jsp");
                }

                if (ingreso == 1) {

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
                        nombreDocente = docente.getPnombre() + " " + docente.getAppaterno();

                        glosa = justi.getGlosa();
                        motivo = (new MotivoDAO()).buscar(justi.getIdMotivo()).getNombreMotivo();
                        fechaA = parseador.format(ina.getFechaInasistencia());
                        sesion.setAttribute("reporte", reporte);
                        activo = reporte.getActivo();
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
                        if (justiImg != null) {
                            isImg = 1;
                        }

                        comentarios = (new SubdirectorComentarioDAO()).mostrarDatos(ina.getIdInasistencia());
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
                    <%if (activo == 1) { %>
                    <a class="white-text btn  waves-effect waves-light  red" href="subjustificaciones.jsp">Volver</a>
                    <%    }
                    %>
                    <%if (activo != 1) { %>
                    <a class="white-text btn  waves-effect waves-light  red" href="suballJustificaciones.jsp">Volver</a>
                    <%    }
                    %>
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
                    </table>
                    <br>  
                    <%if (isImg == 1) {
                    %>
                    <div> 
                        <a href="../enviado.jsp?file=<%=justiImg.getNombreImagen()%>" target="_blank" class="btn green" ><strong>Ver Archivo <i class="material-icons">folder_open</i></strong></a>
                    </div>
                    <%}%>  
                    <form action="../ControladorSubdirector" method="post" >

                        <div>
                            <%if (persona == 1) {
                                    if (reporte.getActivo() == 1) {
                                        if (ina.getIdEstadoi() == 9) { %>
                            <button class="btn waves-effect waves-light blue" type="submit" name="opcion" value="Revisado"><strong>Marcar como revisado</strong></button>
                            <%          }
                                    }
                                }
                                if (persona == 2 && ina.getIdEstadoi() == 9) { %>
                                <br><br>
                            <button class="btn waves-effect waves-light blue" type="submit" name="opcion" value="Aprobado"><strong>Aprobar</strong></button>
                            <button class="btn waves-effect waves-light blue" type="submit" name="opcion" value="Rechazado"><strong>Rechazar</strong></button>

                            <% }
                               if (ina.getIdEstadoi() == 10) { %>
                               <br>
                               <br>
                            <button class="btn waves-effect waves-light blue" type="submit" name="opcion" value="Aprobado"><strong>Aprobar</strong></button>
                            <button class="btn waves-effect waves-light blue" type="submit" name="opcion" value="Rechazado"><strong>Rechazar</strong></button>
                            <%
                                }
                                if (reporte.getActivo() == 2) {
                                    if (ina.getIdEstadoi() == 11) { %>                                            
                            <p class="btn waves-effect waves-light red"><strong>Aprobado Subdirector</strong></p>  
                            <% }
                                if (ina.getIdEstadoi() == 13) { %>                                            
                            <p class="btn waves-effect waves-light red"><strong>Aprobado Secretaria SDA</strong></p>  
                            <% }
                                }
                                if (reporte.getActivo() == 3) {
                                    if (ina.getIdEstadoi() == 12) { %>                                            
                            <p class="btn waves-effect waves-light red"><strong>Rechazado Subdirector</strong></p>  
                            <% }
                                if (ina.getIdEstadoi() == 14) { %>                                            
                            <p class="btn waves-effect waves-light red"><strong>Rechazado Secretaria SDA</strong></p>  
                            <% }
                                }

                            %>
                        </div> 
                        <div>
                            <h4 class="color-Plomo center-align">Comentarios</h4>

                            <%if (!comentarios.isEmpty()) {%>
                            <table class="color-Plomo color-Azul-text">
                                <%for (SubdirectorComentario com : comentarios) {
                                        if (com.getIdSubdirector() == 0) {
                                            color = "red";
                                            secreSDA = "Secretaria";
                                        } else {
                                            color = "blue";
                                            secreSDA = "Subdirector";
                                        }
                                %>
                                <tr>
                                    <td><p><strong class="<%=color%> white-text"><%=secreSDA%></strong></td>
                                    <td><p><%=com.getGlosa()%></p></td>                                
                                    <td><%if (persona == 1 && ina.getIdEstadoi() == 9) {%>
                                        <button class="btn red waves-effect" type="submit" name="opcion" value="E<%=com.getIdSubdirectorC()%>"><i class="material-icons">delete_forever</i></button>
                                        <% }
                                        %>
                                    </td>
                                </tr>
                                <% }
                                %>                              
                            </table>
                            <%  }
                                if (persona == 1) {
                                    if (reporte.getActivo() == 1) {
                                        if (ina.getIdEstadoi() == 9) { %>                       

                            <div class="col s12">
                                <textarea name="txtComentario" rows="10" cols="30" id="textarea1" maxlength="280" data-length="280" class="materialize-textarea"></textarea>
                                <label for="textarea1">Comente los motivos</label>
                            </div>                        
                            <button class="btn waves-effect waves-light blue" type="submit" name="opcion" value="Comentar"><strong>Comentar</strong></button>
                            <br><br>
                            <%         }
                                    }
                                }
                            %>                 
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
        <script src="../js/materialize.js"></script>
    </body>
</html>
