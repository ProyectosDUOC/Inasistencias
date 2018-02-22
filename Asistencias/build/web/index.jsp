<%-- 
    Document   : index
    Created on : 18-nov-2017, 3:34:48
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css">     
        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    </head>
    <body>
        <header class="color-Azul">
            <div class="container">
                <div class="row">                    
                    <div class="center-align">
                        <br>
                        <h5 class="white-text"><strong>Sistema de inasistencia</strong></h5>
                        <br>
                    </div>
                </div>
            </div>
        </header>       
        <br>
        <br>
        <div class="container">
            <div class="center-align">
                <p class="color-Azul-text color-Plomo"><strong>Centro de Notificaciones</strong></p>
            </div>
        </div>
        <section>
            <div class="container">
                <div class="row">
                    <div class="col s12 m4">     
                        <div class="center-align main">
                            <h5 class="color-AzulClaro-text"><strong>Ingresa acá</strong></h5>
                            <form action="Login" method="post">
                                <table>
                                    <tr>
                                        <td>Usuario:</td>
                                        <td><input type="text" name="txtUser" required=""/></td>
                                    </tr>
                                    <tr>
                                        <td>Contraseña:</td>
                                        <td><input type="password" name="txtPass" required=""/></td>
                                    </tr>
                                </table>
                                <div >
                                    <input id="entrar" type="submit" name="opcion" value="Entrar"/>                                
                                </div>                            
                            </form>
                        </div>
                        <span class="red-text"> ${param.mensaje}</span>
                    </div>
                </div>
            </div>
        </section>
        <br><br><br><br>
        <footer class="color-Azul">            
            <div class="container">
                <br>
                <p class="color-Amarillo-text center-align">Desarrollado por Estudiantes DUOC 2018</p>
                <br>
                <br>
            </div>
        </footer>
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
    </body>
</html>