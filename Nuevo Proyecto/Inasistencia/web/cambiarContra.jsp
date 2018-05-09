<%-- 
    Document   : cambiarContra
    Created on : 23-feb-2018, 10:21:07
    Author     : benja
--%>

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
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css">
        <link rel="shortcut icon" href="images/favicon.ico?" type="images/favicon.ico" />
        <title>Configuracion | cambiar clave </title>
        <%
            HttpSession sesion = request.getSession(true);

            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            Administrador admin = new Administrador();
            Director dire = new Director();
            Docente doce = new Docente();
            Alumno alum = new Alumno();
            Secretaria secre = new Secretaria();

            String nombre = "", correo = "", usuario = "", clave = " ", carrera = "", rut = "";
            String estado = "";

            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                usuario = user.getUsuario();
                clave = user.getClave();
                rut = user.getRutUsuario();
                if (estado.equals("alumno")) {
                    alum = (new AlumnoDAO()).buscarDatos(rut);
                    nombre = alum.getPnombre() + " " + alum.getSnombre() + " " + alum.getAppaterno() + " " + alum.getApmaterno();
                    correo = alum.getEmail();
                    carrera = (new CarreraDAO()).buscar(alum.getIdCarrera()).getNombreCarrera();
                }
                if (estado.equals("docente")) {
                    doce = (new DocenteDAO()).buscarDatos(rut);
                    nombre = doce.getPnombre() + " " + doce.getSnombre() + " " + doce.getAppaterno() + " " + doce.getApmaterno();
                    correo = doce.getEmail();
                }
                if (estado.equals("director")) {
                    dire = (new DirectorDAO()).buscarDatos(rut);
                    nombre = dire.getPnombre() + " " + dire.getSnombre() + " " + dire.getAppaterno() + " " + dire.getApmaterno();
                    correo = dire.getEmail();
                }
                if (estado.equals("administrador")) {
                    admin = (new AdministradorDAO()).buscarDatos(rut);
                    nombre = admin.getPnombre() + " " + admin.getSnombre() + " " + admin.getAppaterno() + " " + admin.getApmaterno();
                    correo = admin.getEmail();
                }
                if (estado.equals("secretaria")) {
                    secre = (new SecretariaDAO()).buscarDatos(rut);
                    nombre = secre.getPnombre() + " " + secre.getSnombre() + " " + secre.getAppaterno() + " " + secre.getApmaterno();
                    correo = secre.getEmail();
                    sesion.setAttribute("rut", null);
                }
            }
        %>
        <script>

            function NumText(string) {//solo letras y numeros
                var out = '';
                //Se añaden las letras validas
                var filtro = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890';//Caracteres validos

                for (var i = 0; i < string.length; i++)
                    if (filtro.indexOf(string.charAt(i)) != -1)
                        out += string.charAt(i);
                return out;
            }

        </script>
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
                <h4 class="color-Plomo color-Azul-text center-align" >Mi Cuenta</h4>
                <div class="col s12 m6 color-Azul-text">
                    <h4 class="color-Plomo color-Azul-text center-align" >Cambiar Contraseña</h4>  
                    <form action="Login" method="post">
                        <table class="col s12 table-new-contract">
                            <tr>
                                <td><strong>Contraseña Anterior:</strong></td>
                                <td><input type="password" name="txtPassAnterior" onkeyup="this.value = NumText(this.value)" required="" maxlength="10"/></td>                                
                            </tr> 
                            <tr>
                                <td><strong>Contraseña Nueva:</strong></td>
                                <td><input type="password" name="txtPassNueva1" onkeyup="this.value = NumText(this.value)" required=""  maxlength="10" /></td>
                            </tr> 
                            <tr>
                                <td><strong>Contraseña Nueva:</strong></td>
                                <td><input type="password" name="txtPassNueva2" onkeyup="this.value = NumText(this.value)" required=""  maxlength="10"/></td>
                            </tr> 
                            <tr>
                                <td>
                                    <a class="btn  waves-effect waves-light  red" href="configuracion.jsp">Volver</a>                                     
                                </td>
                                <td>
                                    <input class="btn color-Azul" type="submit" name="opcion" value="Guardar"/> 
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
