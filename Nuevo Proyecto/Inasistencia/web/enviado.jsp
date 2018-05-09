<%-- 
    Document   : enviado
    Created on : 05-05-2018, 21:59:46
    Author     : Seba
--%>

<%@page import="modelo.ControlUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>       
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css"> 
        <link rel="shortcut icon" href="images/favicon.ico?" type="images/favicon.ico" />
        <title>Imagen</title>
        <%
            HttpSession sesion = request.getSession(true);
            ControlUsuario user = sesion.getAttribute("usuario") == null ? null : (ControlUsuario) sesion.getAttribute("usuario");
            String estado="";
             if (session.getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            } else {
                estado = sesion.getAttribute("tipoUsuario").toString();
                if (estado.equals("director")) {
                    
                }else{
                     response.sendRedirect("index.jsp");
                }
             }

        %>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col s12">                    
                  <img src="files/${param.file}" class="img responsive-img" >
                </div>
            </div>
        </div>        
    </body>
</html>
