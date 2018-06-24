<%-- 
    Document   : loginAlum
    Created on : 24-06-2018, 0:47:19
    Author     : benja
--%>

<%@page import="dao.ControlUsuarioDAO"%>
<%@page import="modelo.ControlUsuario"%>
<%@page import="dao.AlumnoDAO"%>
<%@page import="modelo.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1
       <% ArrayList<Alumno> alumnos = (new AlumnoDAO()).mostrarDatosEspecificos();
       
        ControlUsuario control = new ControlUsuario();
          
        String u="";
        String e="";
        for(Alumno a: alumnos){
            e = a.getEmail();
            for (int x=0;x<e.length();x++){                
                if(e.charAt(x)=='@'){
                    break;
                }
                u = u + e.charAt(x);
            }
            u = u.toLowerCase();
            control = new ControlUsuario(0,u,u,a.getRutAlumno(), 1,1);
            (new ControlUsuarioDAO()).agregar(control);
            u="";
            %> 
            <p>Usuario : <%=control.getUsuario()%>  clave: <%=control.getClave()%> rut: <%=control.getRutUsuario()%> </p>
            <%
        }
       
       
       %>
    </body>
</html>
