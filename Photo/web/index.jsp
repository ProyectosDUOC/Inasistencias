<%-- 
    Document   : index
    Created on : 03-05-2018, 2:17:17
    Author     : Seba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subir imagen</title>
    </head>
    <body>
        <h1>Subir imagen test</h1>
        <form action="CargarFoto" method="post" enctype="multipart/form-data">
            <input type="file" name="file" required=""  accept="image/*"/>
            <input type="submit" value="Subir" />
        </form>
        <p><%=System.getenv("UPLOADS")%></p>
        <p><%=System.getProperty("java.io.tmpdir")%></p>
    </body>
</html>
