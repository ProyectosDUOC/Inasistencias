<%-- 
    Document   : recuperarClave
    Created on : 23-feb-2018, 12:18:01
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Recuperar Clave | Inasistencias Duoc</title>           
        <!-- CSS  -->        
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css"> 
        <link rel="shortcut icon" href="images/favicon.ico?" type="images/favicon.ico" />
    </head>
    <body>
        <header class="color-Azul">
            <div class="container">
                <div class="row">                    
                    <div class="center-align">
                        <br>
                        <h5 class="white-text"><strong>Control de Inasistencias</strong></h5>
                        <br>
                    </div>
                </div>
            </div>
        </header>   
        <div class="container">
            <div class="center-align">
                <p class="color-Plomo-text color-Plomo">hola</p>
            </div>
        </div>
        <section>
            <div class="container">
                <div class="row">
                    <div class="col s12 m6">     
                        <div class="center-align main">
                            <h5 class="color-AzulClaro-text"><strong>Ingresa acá</strong></h5>
                            <form action="Login" method="post">
                                <table>
                                    <tr>
                                        <td>Correo Electronico:</td>
                                        <td><input type="email" name="txtCorreo" required=""/></td>                                       
                                    </tr>                                   
                                </table>
                                <div >
                                    <input type="submit" name="opcion" value="Recuperar" class="color-Azul-text"/>                                
                                </div>                            
                            </form>
                            <br>
                            <a href="index.jsp" class="red-text left-align"><strong>Volver</strong></a>
                            <br>
                        </div>
                        <span class="red-text"> ${param.mensaje}</span>
                    </div>
                </div>
            </div>
        </section>
        <br>
        <footer class="color-Azul">            
            <div class="container">
                <br>
                <p class="color-Amarillo-text center-align">Desarrollado por Estudiantes DUOC San Bernardo</p>
                <p class="color-Amarillo-text center-align">Carlos Orellana ★ Sebastian Orrego &#9733;  Benjamin Mora</p>
                <p class="color-Amarillo-text center-align"> &#9733; 2018 &#9733; </p>
                <br>
            </div>
        </footer>       
    </body>
</html>
