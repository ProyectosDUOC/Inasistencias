<%-- 
    Document   : Justificar
    Created on : 27-11-2017, 0:33:27
    Author     : Seba
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.*"%>
<%@page import="dao.*"%>
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
        <title>Justificar Inasistencia</title>
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");

            int rutAlumno = 0;
            Alumno alu = new Alumno();
            Docente docente = new Docente();
            String nombre = "", estado = "", idInasistencia = "", nombreAsig = "", nombreDocente = "";
            ArrayList<Motivo> motivos = new ClasesConsultas().mostrarMotivos();
            Justificacion justicacion = new Justificacion();
            Inasistencia inasistencia = new Inasistencia();
            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("Alumno")) {
                    idInasistencia = sesion.getAttribute("idInasistencia").toString();
                    rutAlumno = user.getRutUsuario();
                    alu = (new AlumnoDAO()).buscarDatos(rutAlumno);
                    nombre = alu.getPnombre() + " " + alu.getSnombre() + " " + alu.getAppaterno() + " " + alu.getApmaterno();

                    inasistencia = (new InasistenciaDAO()).buscar(Integer.parseInt(idInasistencia));
                    justicacion = (new JustificacionDAO()).buscarDatos(Integer.parseInt(idInasistencia));
                    nombreAsig = (new ClasesConsultas()).buscarRamos(new ClasesConsultas().buscarSeccion(inasistencia.getIdSeccion()).getIdRamo()).getNombreRamo();
                    docente = (new DocenteDAO()).buscarDatos(new ClasesConsultas().buscarSeccion(inasistencia.getIdSeccion()).getRutDocente());
                    nombreDocente = docente.getPnombre() + " " + docente.getAppaterno();

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
                    <form action="ControladorJusti" method="post" >
                        <table class="color-Plomo color-Azul-text">
                            <tr>
                                <td><strong>Motivo:</strong></td>
                                <td class="col s12">
                                    <select name="motivo" class="color-Azul color-Amarillo-text browser-default" required="">
                                        <option value="" disabled selected>Seleccione un Motivo</option>                    
                                        <% for (Motivo mot : motivos) {
                                                if (mot.getIdMotivo() != 0) {
                                        %>
                                        <option value="<%=mot.getIdMotivo()%>" >
                                            <%= mot.getNombreMotivo()%>
                                        </option>
                                        <%      }
                                            }
                                        %>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><strong>Glosa:</strong><i class="material-icons prefix">textsms</i></td>                       
                                <td class="col s12">
                                    <textarea name="glosa" rows="10" cols="30" id="textarea1" maxlength="280" data-length="280" class="materialize-textarea" required=""></textarea>
                                    <label for="textarea1">Comente sus motivos</label>
                                </td>
                            </tr>
                        </table>
                        <br>
                        <div>
                            <input type="file" name="file" accept="image/*"/>
                        </div>
                        <br>
                        <div>
                            <a class="white-text btn  waves-effect waves-light  red" href="<%=estado%>.jsp">Volver</a>
                            <button class="btn amber waves-effect waves-light" type="submit" name="opcion" value="g<%=idInasistencia%>">Guardar</button>
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

