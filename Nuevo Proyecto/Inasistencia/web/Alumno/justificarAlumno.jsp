<%-- 
    Document   : justificacion
    Created on : 09-may-2018, 11:20:48
    Author     : benja
--%>
<%@page import="dao.InasistenciaDAO"%>
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
            
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yyyy");
            
            GlobalSemestre semestreActual = new GlobalSemestre();

            String idSeccion = "", rut = "", rutA = "", nombreA = "", carreraA = "", correoA = "", nombre = "", estado = "", semestre = "", nombreDocente = "", nombreAsig = "", nombreCod = "", fecha="";
            String nombreDirector = "";
            int id=0;
            ArrayList<Motivo> motivos = new MotivoDAO().mostrarDatos();

            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("alumno")) {
                    
                        if (sesion.getAttribute("idIna") != null) {                          
                            id = Integer.parseInt(sesion.getAttribute("idIna").toString());                            
                            ina = (new InasistenciaDAO()).buscar(id);
                            
                            alum = (new AlumnoDAO()).buscarDatos(user.getRutUsuario());
                            nombre = alum.getPnombre() + " " + alum.getSnombre() + " " + alum.getAppaterno() + " " + alum.getApmaterno();
                            nombreA = nombre;
                            rutA= alum.getRutAlumno();
                            carrera = (new CarreraDAO()).buscar(alum.getIdCarrera());
                            carreraA = carrera.getNombreCarrera();
                            dire = (new DirectorDAO()).buscarDatos(carrera.getIdDirector());
                            nombreDirector = dire.getPnombre() + " " + dire.getAppaterno();
                            seccion = (new SeccionDAO()).buscar(ina.getIdSeccion());
                            nombreAsig = (new RamoDAO()).buscar(seccion.getCodRamo()).getNombreRamo();
                            docente = (new DocenteDAO()).buscarDatos(seccion.getIdDocente());
                            nombreDocente = docente.getPnombre() + " " + docente.getSnombre() + " " + docente.getAppaterno() + " " + docente.getApmaterno();
                            fecha = parseador.format(ina.getFechaInasistencia());
                        } else {
                             response.sendRedirect("../alumno.jsp");
                        }                   
                    }else {
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
                    <p><strong>Nombre Asignatura :</strong> <span><%=nombreAsig%></span></p>
                    <p><strong>Sección :</strong> <span><%=seccion.getCodSeccion()%></span></p>
                    <p><strong>Profesor : </strong><span><%=nombreDocente%></span></p>
                    <h4 class="color-Plomo center-align">Alumno</h4>  
                    <p><strong>Nombre Alumno :</strong> <span><%=nombreA%></span></p>
                    <p><strong>Carrera :</strong> <span><%=carreraA%></span></p>
                    <p><strong>Correo :</strong><span><%=alum.getEmail()%></span></p>
                    <h4 class="color-Plomo center-align">Director de Carrera</h4>  
                    <p><strong>Nombre Director :</strong> <span><%=nombreDirector%></span></p>
                    <br>
                </div>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo center-align">Justificación</h4>
                    <form action="../ControladorJustificarAlumno" method="post">
                        <table class="color-Plomo color-Azul-text">
                            <tr>
                                <td><p><strong>Fecha Inasistencia:</strong></td>
                                <td><p><%=fecha%></p></td>
                            </tr>
                            <tr>
                                <td><strong>Motivo:</strong></td>
                                <td class="col s12">
                                    <select name="motivo" class="color-Azul color-Amarillo-text browser-default" required="">
                                        <option value="" disabled selected>Seleccione un Motivo</option>                    
                                        <% for (Motivo mot : motivos) {
                                                if (mot.getIdMotivo() != 0) {
                                                    if(mot.getIdMotivo()!=11){
                                                        if(mot.getIdMotivo()!=3){
                                                            %>
                                                        <option value="<%=mot.getIdMotivo()%>" >
                                                            <%= mot.getNombreMotivo()%>
                                                        </option>
                                                        <%  
                                                        }
                                                    }
                                            }
                                            }
                                        %>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><strong>Glosa:</strong></td>                       
                                <td class="col s12">
                                    <textarea name="glosa" rows="10" cols="30" id="textarea1" maxlength="280" data-length="280" class="materialize-textarea" required=""></textarea>
                                    <label for="textarea1">Comente los motivos</label>
                                </td>
                            </tr>
                        </table>
                        <br>
                        <br>
                        <div>
                            <a class="white-text btn  waves-effect waves-light  red" href="../<%=estado%>.jsp">Volver</a>
                            <button class="btn amber waves-effect waves-light" type="submit" name="opcion" value="G<%=id%>">Guardar</button>
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
    </body>
</html>