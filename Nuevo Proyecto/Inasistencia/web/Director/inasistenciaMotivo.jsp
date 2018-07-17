<%-- 
    Document   : inasistenciaMotivo
    Created on : 17-07-2018, 13:11:30
    Author     : benja
--%>

<%@page import="dao.MotivoDAO"%>
<%@page import="modelo.Motivo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.SubdirectorDAO"%>
<%@page import="dao.SubSecretariaDAO"%>
<%@page import="modelo.SecretariaSda"%>
<%@page import="modelo.Subdirector"%>
<%@page import="modelo.GlobalSemestre"%>
<%@page import="dao.DirectorDAO"%>
<%@page import="modelo.ControlUsuario"%>
<%@page import="modelo.Director"%>
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
        <title>Menu Director</title>
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            Director dire = new Director();
            Subdirector subdire = new Subdirector();
            SecretariaSda secreSDA = new SecretariaSda();
            GlobalSemestre semestreActual = new GlobalSemestre();
            String nombre = "", estado = "", rut = "", link="", texto="";
            
            
            ArrayList<Motivo> arrayMotivo = new ArrayList<Motivo>();
            
            if (user == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                rut = user.getRutUsuario();
                semestreActual = (GlobalSemestre) session.getAttribute("semestreActual");
                if (estado.equals("director")) {
                    dire = (new DirectorDAO()).buscarDatos(rut);
                    nombre = dire.getPnombre() + " " + dire.getSnombre() + " " + dire.getAppaterno() + " " + dire.getApmaterno();
                    link="director";
                    texto="Director de carrera";
                    arrayMotivo = (new MotivoDAO()).mostrarDatos();
                }else{
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
                            <a href="<%=link%>.jsp" class="color-Amarillo-text"><strong><i class="Tiny material-icons prefix">home</i></strong></a>  
                            <a href="<%=link%>.jsp" class="color-Amarillo-text"><strong><i class="Tiny material-icons prefix">person</i>Bienvenido </strong><span class="white-text"><%=nombre%></span></a>
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
                                    <th>Motivo</th>
                                    <th>Cantidad</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Motivo motivo : arrayMotivo) {
                                        if(motivo.getIdMotivo()==0) continue; %>
                                    <tr>
                                    <td><%=motivo.getNombreMotivo()%></td>
                                    <td>2</td>
                                    <td>
                                        <button class="btn red waves-effect waves-light" 
                                                type="submit" 
                                                name="opcion" 
                                                value="V">Rechazado
                                        </button>
                                    </td>
                                </tr>                                        
                              <%  }
                                %>
                                
                                        
                                                 
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