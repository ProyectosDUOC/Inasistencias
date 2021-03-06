/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.*;
import dao.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Seba
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(true);
        String user = request.getParameter("txtUser");
        String pass = request.getParameter("txtPass");
        String correo = request.getParameter("txtCorreo");
        String opcion = request.getParameter("opcion");

        if (opcion.equals("Entrar")) {
            ControlUsuario ingreso = (new ControlUsuarioDAO()).buscarDatosLogin(user);
            if (ingreso != null) {
                if (ingreso.getClave().equals(pass)) {

                    sesion.setAttribute("usuario", ingreso);
                    int tipousuario = ingreso.getIdTipoUsuario();

                    switch (tipousuario) {
                        case 1:
                            sesion.setAttribute("tipoUsuario", "Alumno");
                            response.sendRedirect("Alumno.jsp");
                            break;
                        case 2:
                            sesion.setAttribute("tipoUsuario", "Docente");
                            response.sendRedirect("Docente.jsp");
                            break;
                        case 3:
                            sesion.setAttribute("tipoUsuario", "Director");
                            response.sendRedirect("Director.jsp");
                            break;
                        case 4:
                            sesion.setAttribute("tipoUsuario", "Coordinador");
                            response.sendRedirect("Coordinador.jsp");
                            break;
                        default:
                            response.sendRedirect("Error.jsp");
                            break;
                    }

                } else {
                    //clave incorrecta
                    response.sendRedirect("index.jsp?mensaje=clave incorrecta");
                }
            } else {
                // Usuario no existe
                response.sendRedirect("index.jsp?mensaje=usuario no existe");
            }
        }
        if (opcion.equals("Recuperar")) {
            //Buscar en todos los correo
            try {
                Alumno alu = (new AlumnoDAO()).buscarDatosCorreo(correo);
                String mensaje, asunto = "Solicitud de recuperación";
                ControlUsuario ingreso;
                int estado = -1;

                if (alu == null) {
                    Coordinador coor = (new CoordinadorDAO()).buscarDatosCorreo(correo);
                    if (coor == null) {
                        Director dire = (new DirectorDAO()).buscarDatosCorreo(correo);
                        if (dire == null) {
                            Docente doce = (new DocenteDAO()).buscarDatosCorreo(correo);
                            if (doce == null) {
                                response.sendRedirect("recuperarClave.jsp?mensaje=Usuario no existe");
                            } else {
                                ingreso = (new ControlUsuarioDAO()).buscarDatos(doce.getRutDocente());
                                mensaje = "Estimado Docente \n\nEl portal de inasistencias Duoc presenta una solicitud para recuperar su contraseña"
                                        + "\n\n   -Usuario : " + ingreso.getUsuario() + "\n   -Contraseña: " + ingreso.getClave() + "\n \n Ingrese al portal para cambiar su contraseña";
                                estado = ControladorCorreo.EnviarCorreo(doce.getEmail(), mensaje, asunto);
                            }
                        } else {
                            ingreso = (new ControlUsuarioDAO()).buscarDatos(dire.getRutDirector());
                            mensaje = "Estimado Director \n\nEl portal de inasistencias Duoc presenta una solicitud para recuperar su contraseña"
                                    + "\n\n   -Usuario : " + ingreso.getUsuario() + "\n   -Contraseña: " + ingreso.getClave() + "\n \n Ingrese al portal para cambiar su contraseña";
                            estado = ControladorCorreo.EnviarCorreo(dire.getEmail(), mensaje, asunto);
                        }
                    } else {
                        ingreso = (new ControlUsuarioDAO()).buscarDatos(coor.getRutCoordinador());
                        mensaje = "Estimado Coordinador \n\nEl portal de inasistencias Duoc presenta una solicitud para recuperar su contraseña"
                                + "\n\n   -Usuario : " + ingreso.getUsuario() + "\n   -Contraseña: " + ingreso.getClave() + "\n \n Ingrese al portal para cambiar su contraseña";
                        estado = ControladorCorreo.EnviarCorreo(coor.getEmail(), mensaje, asunto);
                    }
                } else {
                    ingreso = (new ControlUsuarioDAO()).buscarDatos(alu.getRutAlumno());
                    mensaje = "Estimado Estudiante \n\nEl portal de inasistencias Duoc presenta una solicitud para recuperar su contraseña"
                            + "\n\n   -Usuario : " + ingreso.getUsuario() + "\n   -Contraseña: " + ingreso.getClave() + "\n \n Ingrese al portal para cambiar su contraseña";
                    estado = ControladorCorreo.EnviarCorreo(alu.getEmail(), mensaje, asunto);
                }

                if (estado == 1) {
                    response.sendRedirect("recuperarClave.jsp?mensaje=Se ha enviado un mensaje al correo");
                } else {
                    response.sendRedirect("recuperarClave.jsp?mensaje=error al enviar correo");
                }

            } catch (Exception ex) {
                    response.sendRedirect("recuperarClave.jsp?mensaje=error Grave" + ex.toString());
            }

        }
        if (opcion.equals("Guardar")) {

            ControlUsuario user1 = sesion.getAttribute("usuario") == null ? new ControlUsuario() : (ControlUsuario) sesion.getAttribute("usuario");

            String passAnterior = request.getParameter("txtPassAnterior");
            String pass1 = request.getParameter("txtPassNueva1");
            String pass2 = request.getParameter("txtPassNueva2");

            if (pass1.equals(pass2)) {
                if (passAnterior.equals(user1.getClave())) {
                    if (!passAnterior.equals(pass1)) {
                        user1.setClave(pass1);
                        int x = (new ControlUsuarioDAO()).actualizar(user1);
                        response.sendRedirect("cambiarContra.jsp?mensaje=Se ha actualizado la clave");
                    } else {
                        response.sendRedirect("cambiarContra.jsp?mensaje=La clave nueva es la misma que la anterior");
                    }
                } else {
                    response.sendRedirect("cambiarContra.jsp?mensaje=Clave anterior incorrecta");
                }
            } else {
                response.sendRedirect("cambiarContra.jsp?mensaje=las claves nuevas no coinciden");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
