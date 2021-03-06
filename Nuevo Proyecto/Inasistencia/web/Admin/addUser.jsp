<%-- 
    Document   : addUser
    Created on : 06-05-2018, 0:27:21
    Author     : carlos
--%>

<%@page import="dao.ControlUsuarioDAO"%>
<%@page import="modelo.Docente"%>
<%@page import="modelo.Secretaria"%>
<%@page import="modelo.Director"%>
<%@page import="modelo.Alumno"%>
<%@page import="dao.CarreraDAO"%>
<%@page import="modelo.Carrera"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.AdministradorDAO"%>
<%@page import="modelo.ControlUsuario"%>
<%@page import="modelo.Administrador"%>
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
        <title>Menu Principal Coordinador</title>
        <%
            HttpSession sesion = request.getSession(true);
            Administrador admin = new Administrador();
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            String nombre = "", estado = "", xCrud = "";
            int valido = 0, button = 0;
            String mensaje = "";
            String rutU = "", pnombreU = "", snombreU = "", appaternoU = "", apmaternoU = "", correoU = "", activoU = "", carreraU = "";

            Alumno alum = new Alumno();
            Director dire = new Director();
            Secretaria secre = new Secretaria();
            Docente doce = new Docente();
            Administrador administrador = new Administrador();
            ControlUsuario controlUsuario = new ControlUsuario();

            ArrayList<Carrera> carreras = new ArrayList<Carrera>();

            if (user == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("administrador")) {
                    String rut = user.getRutUsuario();
                    admin = (new AdministradorDAO()).buscarDatos(rut);
                    nombre = admin.getPnombre() + " " + admin.getSnombre() + " " + admin.getAppaterno() + " " + admin.getApmaterno();
                    xCrud = sesion.getAttribute("xCrud").toString();

                    rutU = sesion.getAttribute("rutU").toString();

                    if (xCrud.equals("1")) {
                        mensaje = "Alumno";
                        carreras = (new CarreraDAO()).mostrarDatos();

                        if (session.getAttribute("respU") != null) {
                            alum = (Alumno) session.getAttribute("respU");

                            pnombreU = alum.getPnombre();
                            snombreU = alum.getSnombre();
                            appaternoU = alum.getAppaterno();
                            apmaternoU = alum.getApmaterno();
                            correoU = alum.getEmail();
                            activoU = alum.getActivo().toString();
                            carreraU = (new CarreraDAO()).buscar(alum.getIdCarrera()).getNombreCarrera();
                            valido = 1;
                            button = 1;

                            controlUsuario = (new ControlUsuarioDAO()).buscarDatosIdTipo(rut, 1);
                        }
                    }
                    if (xCrud.equals("2")) {
                        mensaje = "Docente";
                        if (session.getAttribute("respU") != null) {
                            doce = (Docente) session.getAttribute("respU");

                            pnombreU = doce.getPnombre();
                            snombreU = doce.getSnombre();
                            appaternoU = doce.getAppaterno();
                            apmaternoU = doce.getApmaterno();
                            correoU = doce.getEmail();
                            activoU = doce.getActivo().toString();
                            valido = 0;
                            button = 1;

                            controlUsuario = (new ControlUsuarioDAO()).buscarDatosIdTipo(rut, 2);
                        }
                    }
                    if (xCrud.equals("3")) {
                        mensaje = "Director";
                        if (session.getAttribute("respU") != null) {
                            dire = (Director) session.getAttribute("respU");

                            pnombreU = dire.getPnombre();
                            snombreU = dire.getSnombre();
                            appaternoU = dire.getAppaterno();
                            apmaternoU = dire.getApmaterno();
                            correoU = dire.getEmail();
                            activoU = dire.getActivo().toString();
                            valido = 0;
                            button = 1;

                            controlUsuario = (new ControlUsuarioDAO()).buscarDatosIdTipo(rut, 3);
                        }
                    }
                    if (xCrud.equals("4")) {
                        mensaje = "Administrador";
                        if (session.getAttribute("respU") != null) {
                            admin = (Administrador) session.getAttribute("respU");

                            pnombreU = admin.getPnombre();
                            snombreU = admin.getSnombre();
                            appaternoU = admin.getAppaterno();
                            apmaternoU = admin.getApmaterno();
                            correoU = admin.getEmail();
                            activoU = admin.getActivo().toString();
                            valido = 0;
                            button = 1;

                            controlUsuario = (new ControlUsuarioDAO()).buscarDatosIdTipo(rut, 6);
                        }
                    }
                    if (xCrud.equals("5")) {
                        mensaje = "Secretaria";
                        if (session.getAttribute("respU") != null) {
                            secre = (Secretaria) session.getAttribute("respU");

                            pnombreU = secre.getPnombre();
                            snombreU = secre.getSnombre();
                            appaternoU = secre.getAppaterno();
                            apmaternoU = secre.getApmaterno();
                            correoU = secre.getEmail();
                            activoU = secre.getActivo().toString();
                            valido = 0;
                            button = 1;

                            controlUsuario = (new ControlUsuarioDAO()).buscarDatosIdTipo(secre.getRutSecretaria(), 5);

                        }
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
                <form method="POST" action="../ControladorAdministrador">  

                    <h4 class="color-Plomo color-Azul-text center-align" >Agregar <%=mensaje%></h4>

                    <div class="col s12 m6 color-Azul-text">
                        <h4 class="color-Plomo color-Azul-text center-align" >Datos Personales</h4>
                        <table class="col s12 table-new-contract">
                            <tr>
                                <td><strong>RUT:</strong></td>
                                <td><input type="text" name="txtRut" readonly="" value="<%=rutU%>" maxlength="30"/></td>
                            </tr> 
                            <tr>
                                <td><strong>Primer Nombre:</strong></td>
                                <td><input type="text" name="txtPnombre" required="" value="<%=pnombreU%>" maxlength="30"/></td>
                            </tr> 
                            <tr>
                                <td><strong>Segundo Nombre:</strong></td>
                                <td><input type="text" name="txtSnombre" required="" value="<%=snombreU%>" maxlength="30"/></td>
                            </tr> 
                            <tr>
                                <td><strong>Apellido Paterno:</strong></td>
                                <td><input type="text" name="txtApaterno" required="" value="<%=appaternoU%>" maxlength="30"/></td>
                            </tr>
                            <tr>
                                <td><strong>Apellido Materno:</strong></td>
                                <td><input type="text" name="txtAmaterno" required="" value="<%=apmaternoU%>" maxlength="30"/></td>
                            </tr> 
                            <tr>
                                <td><strong>Correo Eléctronico:</strong></td>
                                <td><input type="email" name="txtEmail" required="" value="<%=correoU%>" maxlength="30"/></td>
                            </tr> 
                        </table>
                    </div>
                    <%if (xCrud.equals("1") && !carreras.isEmpty() && valido == 0) {%>
                    <div class="col s12 m6 color-Azul-text">
                        <h4 class="color-Plomo center-align">Carrera</h4>     
                        <div class="input-field">    
                            <p><strong> Carrera :</strong> 
                                <select name="carrera" class="color-Azul color-Amarillo-text browser-default" required="">
                                    <option value="" disabled selected>Seleccione Carrera</option>                    
                                    <% for (Carrera car : carreras) {

                                    %>
                                    <option value="<%=car.getIdCarrera()%>" >
                                        <%= car.getNombreCarrera()%>
                                    </option>
                                    <%
                                        }
                                    %>
                                </select>
                            </p>
                        </div>
                    </div>
                    <%
                    } else {
                        if (valido == 1) {%>
                    <div class="col s12 m6 color-Azul-text">
                        <h4 class="color-Plomo center-align">Carrera</h4>     
                        <div class="input-field">         
                            <p><strong> Carrera :</strong> 

                                <input type="text" name="carrera" value="<%=carreraU%>" class="color-Azul color-Amarillo-text" readonly=""/>
                            </p>
                        </div>
                    </div>   
                    <% }
                        }
                    %>                    
                    <div class="col s12 m6 color-Azul-text">
                        <h4 class="color-Plomo center-align">Usuario</h4>     
                        <div class="input-field">                                
                            <%if (controlUsuario.getRutUsuario() != null) {%>
                            <p><strong> Usuario : <%=controlUsuario.getUsuario()%></strong></p>
                            <p><strong> Clave : <%=controlUsuario.getClave()%></strong></p>
                            <%} else {%>
                            <table>
                                <tr>
                                    <td><strong>Usuario :</strong></td>
                                    <td><input type="text" name="txtUsuario" required="" value="" maxlength="30"/></td>
                                </tr> 
                                <tr>
                                    <td><strong>Contraseña:</strong></td>
                                    <td><input type="text" name="txtContra" required="" value="" maxlength="30"/></td>
                                </tr> 
                            </table>              
                            <% }%> 
                        </div>

                        <br><br>
                    </div>
                    <a class="white-text btn  waves-effect waves-light  red" href="askUser.jsp">Volver</a>
                    <% if (button == 0) { %>
                    <button  name="opcion" type="submit" value="Agregar" class="white-text btn  waves-effect waves-light  red">Agregar</button>
                    <%  } else { %>
                    <!-- <button  name="opcion" type="submit" value="Actualizar" class="black-text btn  waves-effect waves-light  yellow">Actualizar</button> -->
                    <%    }
                    %>  
                </form>
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
