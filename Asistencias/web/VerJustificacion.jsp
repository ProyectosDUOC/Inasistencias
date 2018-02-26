<%-- 
    Document   : VerJustificacion
    Created on : 27-11-2017, 13:25:03
    Author     : Seba
--%>

<%@page import="modelo.*"%>
<%@page import="dao.*"%>
<%@page import="java.util.ArrayList"%>
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
        <title>Ver Justificación</title>
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");

            Alumno alum = new Alumno();
            Docente doce = new Docente();
            Docente docente = new Docente();
            Coordinador coor = new Coordinador();
            Director dire = new Director();            
            Justificacion justicacion = new Justificacion();
            Inasistencia inasistencia = new Inasistencia();
            ClasesConsultas consultaDB = new ClasesConsultas();
            String nombre = "", rutD = "", estado = "", estado1="", idInasistencia = "", nombreAsig = "", nombreDocente = "", motivo ="", nombreAlumno="";
            int rut = 0;
            
            if (session.getAttribute("usuario") == null || sesion.getAttribute("idInasistencia") == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                estado1 = estado;
                idInasistencia = sesion.getAttribute("idInasistencia").toString();
                rut = user.getRutUsuario();
                inasistencia = (new InasistenciaDAO()).buscar(Integer.parseInt(idInasistencia));
                justicacion = (new JustificacionDAO()).buscarDatos(Integer.parseInt(idInasistencia));
                nombreAsig = (new ClasesConsultas()).buscarRamos(new ClasesConsultas().buscarSeccion(inasistencia.getIdSeccion()).getIdRamo()).getNombreRamo();
                docente = (new DocenteDAO()).buscarDatos(new ClasesConsultas().buscarSeccion(inasistencia.getIdSeccion()).getRutDocente());
                nombreDocente = docente.getPnombre() + " " + docente.getAppaterno();
                motivo = (new ClasesConsultas()).buscarMotivos(justicacion.getIdMotivo()).getNombreMotivo();
                if (estado.equals("Alumno")) {
                    alum = (new AlumnoDAO()).buscarDatos(rut);
                    nombre = alum.getPnombre() + " " + alum.getSnombre() + " " + alum.getAppaterno() + " " + alum.getApmaterno();
                }
                if (estado.equals("Docente")) {
                    doce = (new DocenteDAO()).buscarDatos(rut);
                    nombre = doce.getPnombre() + " " + doce.getSnombre() + " " + doce.getAppaterno() + " " + doce.getApmaterno();
                    estado1 = "seccionAlumnos";
                    alum = (new AlumnoDAO()).buscarDatos(inasistencia.getRutAlumno());
                    nombreAlumno = alum.getPnombre() + " " + alum.getSnombre() + " " + alum.getAppaterno() + " " + alum.getApmaterno(); 
                }
                if (estado.equals("Director")) {
                    dire = (new DirectorDAO()).buscarDatos(rut);
                    nombre = dire.getPnombre() + " " + dire.getSnombre() + " " + dire.getAppaterno() + " " + dire.getApmaterno();
                }
                if (estado.equals("Coordinador")) {
                    coor = (new CoordinadorDAO()).buscarDatos(rut);
                    nombre = coor.getPnombre() + " " + coor.getSnombre() + " " + coor.getAppaterno() + " " + coor.getApmaterno();
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
                <h4 class="color-Plomo color-Azul-text center-align"></h4>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo center-align">Detalles</h4>  
                    <p><strong>Nombre Asignatura :</strong> <span><%=nombreAsig%></span></p>
                    <p><strong>Sección :</strong> <span><%=inasistencia.getIdSeccion()%></span></p>
                    <p><strong>Profesor : </strong><span><%=nombreDocente%></span></p>
                    <p><strong>Fecha Inasistencia :</strong><input type="date" name="fecha" value="<%=inasistencia.getFecha()%>" readonly=""></p>                    
                    <br>
                </div>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo center-align">Justificación</h4>
                    <form action="ControladorJusti" method="post">
                        <table class="color-Plomo color-Azul-text">
                            <%if (estado.equals("Docente")) { %>
                                   <tr>
                                <td><strong>Nombre Alumno:</strong></td>
                                <td><%=nombreAlumno%></td>
                            </tr> 
                            <% } %>                             
                            <tr>
                                <td><strong>Motivo:</strong></td>
                                <td><%=motivo%></td>
                            </tr>
                            <tr>
                                <td><strong>Glosa:</strong> <i class="material-icons prefix">textsms</i></td>                       
                                <td><%=justicacion.getGlosa()%></td>
                            </tr>
                        </table>
                        <br>
                        <div>
                            <p>imagen por si acaso</p>
                        </div>
                        <br>
                        <div>
                            <a class="white-text btn  waves-effect waves-light  red" href="<%=estado1%>.jsp">Volver</a>
                            <%if (estado.equals("Docente") && inasistencia.getIdEstadoi()==2) { %>    
                            <button class="btn amber waves-effect waves-light" type="submit" name="opcion" value="j<%=idInasistencia%>">Justificar</button>
                            <%
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
                <p class="color-Amarillo-text center-align">Carlos Orellana ★ Sebastian Orrego &#9733;  Benjamin Mora</p>
                <p class="color-Amarillo-text center-align"> &#9733; 2018 &#9733; </p>
                <br>
            </div>
        </footer>    
    </body>
</html>
