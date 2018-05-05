<%-- 
    Document   : secretaria
    Created on : 03-may-2018, 10:36:28
    Author     : benja
--%>

<%@page import="modelo.Docente"%>
<%@page import="modelo.DetalleSeccion"%>
<%@page import="dao.DetalleSeccionDAO"%>
<%@page import="dao.DocenteDAO"%>
<%@page import="dao.RamoDAO"%>
<%@page import="dao.SeccionDAO"%>
<%@page import="modelo.Seccion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CarreraDAO"%>
<%@page import="dao.AlumnoDAO"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.GlobalSemestre"%>
<%@page import="dao.SecretariaDAO"%>
<%@page import="dao.GlobalSemestreDAO"%>
<%@page import="modelo.Secretaria"%>
<%@page import="modelo.ControlUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secretaria | local </title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css">     
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css"> 
        <link rel="shortcut icon" href="images/favicon.ico?" type="images/favicon.ico" />
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            String rut = "", rutA = "", nombreA = "", carreraA = "", correoA = "", nombre = "", estado = "", semestre = "", nombreProfe = "", nombreAsig = "", nombreCod = "";
            Secretaria secre = new Secretaria();
            Alumno alum = new Alumno();
            Docente doce = new Docente();
            ArrayList<DetalleSeccion> arrayCursos = new ArrayList<DetalleSeccion>();
            ArrayList<Seccion> arraySeccion = new ArrayList<Seccion>();
            GlobalSemestre gl = new GlobalSemestre();
            int encontrado = 2;
            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("secretaria")) {
                    rut = user.getRutUsuario(); //Usuario de secretaria
                    secre = (new SecretariaDAO()).buscarDatos(rut);

                    gl = (new GlobalSemestreDAO()).buscar();
                    semestre = "Semestre " + gl.getSemestre() + " año " + gl.getAnio();

                    nombre = secre.getPnombre() + " " + secre.getSnombre() + " " + secre.getAppaterno() + " " + secre.getApmaterno();
                    if (sesion.getAttribute("rut") != null) {
                        arraySeccion = (new SeccionDAO()).buscar
                        rutA = session.getAttribute("rut").toString();
                        alum = (new AlumnoDAO()).buscarDatos(rutA);
                        nombreA = alum.getPnombre() + " " + alum.getSnombre() + " " + alum.getAppaterno() + " " + alum.getApmaterno();
                        carreraA = (new CarreraDAO()).buscar(alum.getIdCarrera()).getNombreCarrera();
                        correoA = alum.getEmail();
                        encontrado = 1;
                        arrayCursos = (new DetalleSeccionDAO()).buscarDetalleAlumno(alum.getIdAlumno());
                        for (DetalleSeccion xx : arrayCursos) {
                            arraySeccion.add((new SeccionDAO()).buscarSemestreAnio(xx.getIdSeccion(), gl.getSemestre(), gl.getAnio()));
                        }
                        if (session.getAttribute("idSeccion") != null) {
                            sesion.setAttribute("idSeccion", null);
                        }

                    }

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
        <div class="container">
            <div class="row">
                <h4 class="color-Plomo color-Azul-text center-align" >Justificacion de inasistencia</h4>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo color-Azul-text center-align" >Ingreso de Alumno</h4> 
                    <form method="post" action="ControladorSecretaria">
                        <p><strong> Rut:</strong> <input type="text" name="txtRut" maxlength="10"/> </p>  
                        <input type="submit" name="opcion" value="Buscar" class="color-AzulClaro waves-effect waves-light btn"/>                                
                        <input type="submit" name="opcion" value="Nuevo" class="color-AzulClaro waves-effect waves-light btn"/>                                
                    </form>
                    <p> <%=semestre%> </p>
                    <span class="red-text"> ${param.mensaje}</span>
                </div>
                <% if (encontrado == 1) {%>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo color-Azul-text center-align" >Datos Alumno</h4>  
                    <p><strong> Nombre :</strong> <span><%=nombreA%></span></p>
                    <p><strong> Rut :</strong> <span><%=rutA%></span></p>
                    <p><strong> Correo :</strong> <span><%=correoA%></span></p>                    
                    <p><strong> Carrera :</strong> <span><%=carreraA%></span></p>
                </div>
                <div class="col s12 m12 color-Azul-text">
                    <h4 class="color-Plomo color-Azul-text center-align" >Cursos del Alumno</h4> 
                    <% if (arrayCursos.isEmpty()) { %>
                    <p class="text-center">No tiene registro de cursos</p> </div>
                    <%} else {
                    %>
                    <table id="example" class="striped grey lighten-2 table table-striped table-bordered color-Azul-text" cellspacing="0"  width="100%"> 
                        <thead>
                            <tr class="amber darken-3">
                                <th>Nombre Asignatura</th>
                                <th>Seccion</th>   
                                <th>Nombre Profesor</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                        <%  if (!arraySeccion.isEmpty()) {
                            for (Seccion ss : arraySeccion) {
                                nombreAsig = (new RamoDAO()).buscar(ss.getCodRamo()).getNombreRamo();
                                nombreCod = ss.getCodSeccion();
                                doce = (new DocenteDAO()).buscarDatos(ss.getIdDocente());
                                nombreProfe = doce.getPnombre() + " " + doce.getAppaterno();
                        %>
                        <tr>
                            <td><%=nombreAsig%></td>
                            <td><%=nombreCod%></td>
                            <td><%=nombreProfe%></td>
                            <td>
                                <form action="ControladorSecretaria" method="post">
                                    <button 
                                        class="btn indigo darken-1" 
                                        type="submit" 
                                        name="opcion" 
                                        value="s<%=ss.getIdSeccion()%>"> 
                                        Seleccionar 
                                    </button>
                                </form>                                  
                            </td>                                
                        </tr>                                
                        <% }
                           }
                        %>     
                        </tbody>
                    </table>  
                </div>
                <%
                    }
                    if (encontrado == 0) { %>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo color-Azul-text center-align" >Cursos</h4>  
                    <p><strong>No se registraron cursos</strong></p>  
                </div>  
                <% }
                 } %>
            </div>
        </div>                   
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>  
        <br>
        <footer class="color-Azul">            
            <div class="container">
                <br>
                <p class="color-Amarillo-text center-align">Desarrollado por Estudiantes DUOC San Bernardo</p>                                
                <br>
            </div>
        </footer> 
    </body>
</html>
