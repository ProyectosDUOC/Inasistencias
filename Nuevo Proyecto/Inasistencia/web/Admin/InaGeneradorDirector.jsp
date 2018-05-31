<%-- 
    Document   : generadorClave
    Created on : 29-may-2018, 20:08:10
    Author     : benja
--%>
<%@page import="modelo.Alumno"%>
<%@page import="dao.AlumnoDAO"%>
<%@page import="dao.ControlUsuarioDAO"%>
<%@page import="dao.DirectorDAO"%>
<%@page import="modelo.ControlUsuario"%>
<%@page import="modelo.Director"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            int x = 0;
            String mensaje = "";

            ArrayList<Director> directores = new DirectorDAO().mostrarDatosEspecificos();
            
            ArrayList<Alumno> alumnos = new AlumnoDAO().mostrarDatosEspecificos();

            if (!directores.isEmpty()) {
                mensaje = "<br> <h3> Directores </h3><br>";
                for (Director dir : directores) {
                    x = (new ControlUsuarioDAO()).agregar(new ControlUsuario(0, dir.getRutDirector(), dir.getAppaterno(), dir.getRutDirector(), 3, 1));
                    mensaje = mensaje + "<br>Rut & Usuario: " + dir.getRutDirector() + " contraseña " + dir.getAppaterno();
                }
                mensaje = mensaje + "<br> <h3> Alumnos </h3><br>";
                for (Alumno alu : alumnos) {
                    x = (new ControlUsuarioDAO()).agregar(new ControlUsuario(0, alu.getRutAlumno(), alu.getAppaterno(), alu.getRutAlumno(), 1, 1));
                    mensaje = mensaje + "<br>Rut & Usuario: " + alu.getRutAlumno() + " contraseña " + alu.getAppaterno();
                }
            } else {
                mensaje = "No hizo nada";
            }


        %>
    </head>
    <body>
        <p><%=mensaje%></p>
    </body>
</html>
