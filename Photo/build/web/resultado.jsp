<%-- 
    Document   : resultado
    Created on : 03-05-2018, 11:58:16
    Author     : Seba
--%>

<%@page import="java.nio.file.Paths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado</title>
    </head>
    <body>
        <h1>${param.archivo}</h1>
        <img src="files/<%=request.getParameter("archivo")%>" />            
    </body>
</html>
