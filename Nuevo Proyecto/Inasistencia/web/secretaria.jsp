<%-- 
    Document   : secretaria
    Created on : 03-may-2018, 10:36:28
    Author     : benja
--%>

<%@page import="dao.JornadaDAO"%>
<%@page import="dao.TelefonoDAO"%>
<%@page import="modelo.TelefonoAlumno"%>
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

            Secretaria secre = new Secretaria();
            Alumno alum = new Alumno();
            Docente doce = new Docente();

            GlobalSemestre semestreActual = new GlobalSemestre();
            //variables de secretaria
            String estado = "", rut = "", nombre = "", semestre = "", numero = "";
            TelefonoAlumno tel = new TelefonoAlumno();
            //variables alumno
            String rutA = "", nombreA = "", carreraA = "", correoA = "", jornada="";
            ArrayList<DetalleSeccion> arrayDetalleSeccionAlumno = new ArrayList<DetalleSeccion>();
            ArrayList<Seccion> arraySeccionesAlumno = new ArrayList<Seccion>();
            int alumnoEncontrado = 0; //0 no encontrado, 1 se encontro, 2 no tiene ramos
            int cursosEncontrado = 0;

            //Variables de cursos
            String nombreAsig = "", nombreCod = "", nombreProfe = "";

            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("secretaria")) {
                    rut = user.getRutUsuario(); //Usuario de secretaria
                    secre = (new SecretariaDAO()).buscarDatos(rut);
                    semestreActual = (GlobalSemestre) session.getAttribute("semestreActual");
                    semestre = "Semestre " + semestreActual.getSemestre() + " año " + semestreActual.getAnio();
                    nombre = secre.getPnombre() + " " + secre.getSnombre() + " " + secre.getAppaterno() + " " + secre.getApmaterno();

                    // si encuentra a un alumno con el rut
                    if (sesion.getAttribute("rut") != null) {
                        rutA = session.getAttribute("rut").toString();
                        alum = (new AlumnoDAO()).buscarDatos(rutA);
                        nombreA = alum.getPnombre() + " " + alum.getSnombre() + " " + alum.getAppaterno() + " " + alum.getApmaterno();
                        carreraA = (new CarreraDAO()).buscar(alum.getIdCarrera()).getNombreCarrera();
                        correoA = alum.getEmail();
                        alumnoEncontrado = 1;
                        jornada = (new JornadaDAO()).buscar(alum.getActivo()).getNombreJornada();
                        tel = (new TelefonoDAO()).buscarDatosAlum(alum.getIdAlumno());
                        if (tel != null) {
                            numero = tel.getTelefono().toString();
                        } else {
                            numero = "";
                        }

                        //todos los detalle cursos que esta
                        arrayDetalleSeccionAlumno = (new DetalleSeccionDAO()).mostrarAlumno(alum.getIdAlumno(), semestreActual.getAnio(), semestreActual.getSemestre());
                        if (arrayDetalleSeccionAlumno.isEmpty()) {
                            //en caso que no tenga cursos
                            cursosEncontrado = 0;
                        } else {
                            arraySeccionesAlumno = (new SeccionDAO()).mostrarAlumno(alum.getIdAlumno(), semestreActual.getAnio(), semestreActual.getSemestre());
                            if (arraySeccionesAlumno.isEmpty()) {
                                cursosEncontrado = 0;
                            } else {
                                cursosEncontrado = 1;
                            }
                        }
                    } else {
                        alumnoEncontrado = 0;
                    }

                } else {
                    response.sendRedirect("index.jsp");
                }
            }
        %>   
        <script>
            function validarRut(string) {//solo letras y numeros
                var out = '';
                //Se añaden las letras validas
                var filtro = '1234567890k';//Caracteres validos

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
                <h4 class="color-Plomo color-Azul-text center-align" >Justificacion de inasistencia</h4>

                <form method="post" action="ControladorSecretaria">
                    <div class="col s12 m6 color-Azul-text">
                        <h4 class="color-Plomo color-Azul-text center-align" >Ingreso de Alumno</h4> 

                        <p><strong> Rut:</strong> <input type="text" name="txtRut" maxlength="10" placeholder="19000222 (Sin digito verificador y puntos)" onkeyup="this.value = validarRut(this.value)"/> </p>  
                        <input type="submit" name="opcion" value="Buscar" class="color-AzulClaro waves-effect waves-light btn"/>                                
                        <input type="submit" name="opcion" value="Nuevo" class="color-AzulClaro waves-effect waves-light btn"/>                                
                        <p> <%=semestre%> </p>
                        <span class="red-text"> ${param.mensaje}</span>
                    </div>


                    <% if (alumnoEncontrado == 1) {%>
                    <div class="col s12 m6 color-Azul-text">
                        <h4 class="color-Plomo color-Azul-text center-align" >Datos Alumno</h4>  
                        <p><strong> Nombre :</strong> <span><%=nombreA%></span></p>
                        <p><strong> Rut :</strong> <span><%=rutA%></span></p>
                        <p><strong> Correo :</strong> <span><%=correoA%></span></p>                    
                        <p><strong> Carrera :</strong> <span><%=carreraA%></span></p>    
                        <p><strong> Jornada :</strong> <span><%=jornada%></span></p>  
                        <p><strong> Telefono :</strong>
                            <input type="number" maxlength="9" name="txtTel" value="<%=numero%>" placeholder="Número de celular o casa (229993862)" oninput="if(this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" /><i>(Máximo 8 dígitos)</i>
                        </p>
                        <%if (numero.length() > 0) {%>
                        <button 
                            class="amber waves-effect waves-light btn" 
                            type="submit" 
                            name="opcion" 
                            value="x<%=tel.getIdTel()%>"> 
                            Actualizar 
                        </button>
                        <% } else {%>
                        <button 
                            class="red waves-effect waves-light btn" 
                            type="submit" 
                            name="opcion" 
                            value="y<%=alum.getIdAlumno()%>"> 
                            Agregar
                        </button>
                        <% }%>

                    </div>
                    <div class="col s12 m12 color-Azul-text">
                        <h4 class="color-Plomo color-Azul-text center-align" >Cursos del Alumno</h4> 
                        <% if (cursosEncontrado == 0) { %>
                        <p class="text-center">No tiene registro de cursos</p> 
                        <% } else { %>
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
                                <% for (Seccion sec : arraySeccionesAlumno) {
                                        nombreAsig = (new RamoDAO()).buscar(sec.getCodRamo()).getNombreRamo();
                                        nombreCod = sec.getCodSeccion();
                                        doce = (new DocenteDAO()).buscarDatos(sec.getIdDocente());
                                        nombreProfe = doce.getPnombre() + " " + doce.getAppaterno();
                                %>
                                <tr>
                                    <td><%=nombreAsig%></td>
                                    <td><%=nombreCod%></td>
                                    <td><%=nombreProfe%></td>
                                    <td>
                                        <button 
                                            class="btn indigo darken-1" 
                                            type="submit" 
                                            name="opcion" 
                                            value="s<%=sec.getIdSeccion()%>"> 
                                            Seleccionar 
                                        </button>
                                    </td>                                
                                </tr>                                
                                <%}%>     
                            </tbody>
                        </table> 
                        <% }
                            }%>                    
                    </div>
                </form> 
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
