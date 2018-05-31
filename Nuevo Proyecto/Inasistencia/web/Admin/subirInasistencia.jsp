<%@page import="dao.CarreraDAO"%>
<%@page import="dao.SeccionDAO"%>
<%@page import="modelo.Carrera"%>
<%@page import="modelo.Seccion"%>
<%@page import="dao.AlumnoDAO"%>
<%@page import="modelo.Alumno"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dao.InasistenciaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Inasistencia"%>
<%@page import="dao.AdministradorDAO"%>
<%@page import="modelo.Administrador"%>
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
        <link rel="stylesheet" type="text/css" href="../css/jquery.dataTables.min.css"> 
        <link rel="shortcut icon" href="../images/favicon.ico?" type="images/favicon.ico" />
        <title>Administrador | Subir Archivo Excel</title>
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            String nombre = "", estado = "", nombreA = "", rutA = "", carreraA = "", seccionA = "";
            Administrador admin = new Administrador();
            Seccion seccion = new Seccion();
            Carrera carrera = new Carrera();
            ArrayList<Inasistencia> arrayInasistencias = new ArrayList<Inasistencia>();
            SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
            Alumno alumno = new Alumno();
            int subido = 0;
            if (user == null) {
                response.sendRedirect("../index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("administrador")) {
                    String rut = user.getRutUsuario();
                    admin = (new AdministradorDAO()).buscarDatos(rut);
                    nombre = admin.getPnombre() + " " + admin.getSnombre() + " " + admin.getAppaterno() + " " + admin.getApmaterno();
                    arrayInasistencias = (new InasistenciaDAO()).inasistenciaBuscarActivo(0);
                    if (arrayInasistencias.size() > 0) {
                        subido = 1;
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
            <div class="row center-align">
                <h4 class="color-Azul-text color-Plomo center-align">Carga Inasistencias</h4>
                <br>
                <p class="red-text"><strong>Subir archivo Excel (.xlsx)</strong></p>
                <form action="../ControladorSubirInasistencia" method="post" enctype="multipart/form-data">
                    <input type="file" name="file" required=""  accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                    <input class="btn color-Azul" type="submit" value="Subir" name="opcion" />
                </form>
                <br>
                <div class="mensaje red-text"><strong>${param.mensaje}</strong></div>
                <br>                
                <a  class="white-text btn waves-effect waves-light red" href="../administrador.jsp">Volver</a>
            </div>        
        </div>     
        <div class="container">
            <%if (subido == 1) {%>
            <div class="row">
                <div class="col s12 m12 color-Azul-text">
                    <h4 class="color-Plomo center-align">Listado de inasistencias</h4>
                    <form action="../ControladorSubirInasistenciaC" method="post">
                        <center>
                            <input class="btn color-Azul" type="submit" name="opcion" value="CONFIRMAR" />
                        </center>                    
                    </form>
                    <table id="example" class="striped grey lighten-2 table table-striped table-bordered color-Azul-text" cellspacing="0"  width="100%"> 
                        <thead>
                            <tr class="amber darken-3">
                                <th>Carrera</th>
                                <th>Codigo Seccion</th>
                                <th>Nombre Alumno</th>
                                <th>Rut</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Inasistencia ina : arrayInasistencias) {
                                    alumno = (new AlumnoDAO()).buscarDatosId(ina.getIdAlumno());
                                    nombreA = alumno.getPnombre() + " " + alumno.getApmaterno();
                                    rutA = alumno.getRutAlumno();
                                    seccion = (new SeccionDAO()).buscar(ina.getIdSeccion());
                                    carrera = (new CarreraDAO()).buscar(alumno.getIdCarrera());
                                    carreraA = carrera.getNombreCarrera();
                                    seccionA = seccion.getCodSeccion();

                            %>
                            <tr>                                    
                                <td><%=carreraA%></td>
                                <td><%=seccionA%></td>
                                <td><%=nombreA%></td>
                                <td><%=rutA%></td>
                            </tr> 
                            <%}%>
                        </tbody>
                    </table>  
                </div>
            </div>
            <%}%>
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
