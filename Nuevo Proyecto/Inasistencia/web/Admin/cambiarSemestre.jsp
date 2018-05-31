<%-- 
    Document   : cambiarContra
    Created on : 23-feb-2018, 10:21:07
    Author     : benja
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.GlobalSemestre"%>
<%@page import="dao.GlobalSemestreDAO"%>
<%@page import="dao.AdministradorDAO"%>
<%@page import="dao.DirectorDAO"%>
<%@page import="dao.DocenteDAO"%>
<%@page import="dao.SecretariaDAO"%>
<%@page import="dao.CarreraDAO"%>
<%@page import="modelo.Secretaria"%>
<%@page import="modelo.Administrador"%>
<%@page import="dao.AlumnoDAO"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.Docente"%>
<%@page import="modelo.Docente"%>
<%@page import="modelo.Director"%>
<%@page import="modelo.Director"%>
<%@page import="modelo.ControlUsuario"%>
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
        <link rel="stylesheet" type="text/css" href="../css/styleLogin.css">
        <link rel="shortcut icon" href="../images/favicon.ico?" type="images/favicon.ico" />
        <title>Configuracion | cambiar clave </title>
        <%
            HttpSession sesion = request.getSession(true);
            Administrador admin = new Administrador();
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            String nombre = "", estado = "", semestre = "", anio = "", fechaInicio = "", fechaTermino = "";
            GlobalSemestre global = (new GlobalSemestreDAO()).buscar();
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yyyy");
            if (user == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("administrador")) {
                    sesion.setAttribute("respU", null);
                    sesion.setAttribute("xCrud", null);
                    String rut = user.getRutUsuario();
                    global = (new GlobalSemestreDAO()).buscar();
                    semestre = global.getSemestre().toString();
                    anio = global.getAnio() + "";
                    fechaInicio = parseador.format(global.getFechaInicio());
                    fechaTermino = parseador.format(global.getFechaTermino());

                    admin = (new AdministradorDAO()).buscarDatos(rut);
                    nombre = admin.getPnombre() + " " + admin.getSnombre() + " " + admin.getAppaterno() + " " + admin.getApmaterno();
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
                            <a href="<%=estado%>.jsp" class="color-Amarillo-text"><strong><i class="Tiny material-icons prefix">home</i></strong></a>  
                            <a href="<%=estado%>.jsp" class="color-Amarillo-text"><strong><i class="Tiny material-icons prefix">person</i>Bienvenido </strong><span class="white-text"><%=nombre%></span></a>
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
                <h4 class="color-Plomo color-Azul-text center-align" >Cambio de Semestre</h4>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo color-Azul-text center-align" >Semestre Actual</h4>  
                    <table class="col s12 table-new-contract">
                        <tr>
                            <td><strong>Semestre:</strong></td>
                            <td><strong><%=semestre%></strong></td>                                
                        </tr> 
                        <tr>
                            <td><strong>Anio:</strong></td>
                            <td><%=anio%></td>
                        </tr> 
                        <tr>
                            <td><strong>Fecha Inicio:</strong></td>
                            <td><%=fechaInicio%></td>
                        </tr> 
                        <tr>
                            <td><strong>Fecha Termino:</strong></td>
                            <td><%=fechaTermino%></td>
                        </tr>
                        <tr>
                            <td>
                                <a class="btn  waves-effect waves-light  red" href="panelControl.jsp">Volver</a>                                     
                            </td>
                        </tr>
                    </table>     
                </div>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo color-Azul-text center-align" >Cambiar Semestre</h4>  
                    <form action="../ControladorSemestre" method="post">
                        <table class="col s12 table-new-contract">
                            <tr>
                                <td><strong>Semestre:</strong></td>
                                <td class="col s12">
                                    <select name="semestre" class="color-Azul color-Amarillo-text browser-default" required="">
                                        <option value="" disabled selected>Seleccione Semestre</option>                                        
                                        <option value="1" >1</option>  
                                        <option value="2" >2</option>             
                                    </select>
                                </td>
                            </tr> 
                            <tr>
                                <td><strong>Año:</strong></td>
                                <td class="col s12">
                                    <select name="anio" class="color-Azul color-Amarillo-text browser-default" required="">
                                        <option value="" disabled selected>Seleccione Año</option>    
                                        <option value="2021" >2021</option>  
                                        <option value="2020" >2020</option>  
                                        <option value="2019" >2019</option> 
                                        <option value="2018" >2018</option>  
                                    </select>
                                </td>
                            </tr> 
                            <tr>
                                <td><strong>Fecha Inicio:</strong></td>
                                <td><p><input type="date" name="fecha1"  value="" required=""></p></td>
                            </tr> 
                            <tr>
                                <td><strong>Fecha Termino:</strong></td>
                                <td><p><input type="date" name="fecha2"  value="" required=""></p></td>
                            </tr>
                            <tr>
                                <td>
                                    <input class="btn color-Azul" type="submit" name="opcion" value="Actualizar"/> 
                                </td>
                            </tr>
                        </table>                           
                    </form>   
                    <span class="red-text"> ${param.mensaje}</span>
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
