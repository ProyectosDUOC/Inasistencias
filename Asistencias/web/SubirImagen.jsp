<%-- 
    Document   : SubirImagen
    Created on : 22-02-2018, 1:39:52
    Author     : Seba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Subir imagen test</h1>
        <form action="CargarFoto" method="post" enctype="multipart/form-data">
            <input type="file" name="file" required=""  accept="image/*"/>
            <input type="submit" value="Subir" />
        </form>
    </body>
</html>
