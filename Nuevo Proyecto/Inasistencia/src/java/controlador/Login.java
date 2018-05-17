/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AdministradorDAO;
import dao.AlumnoDAO;
import dao.ControlUsuarioDAO;
import dao.DetalleSeccionDAO;
import dao.DirectorDAO;
import dao.DocenteDAO;
import dao.GlobalSemestreDAO;
import dao.SeccionDAO;
import dao.SecretariaDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Administrador;
import modelo.Alumno;
import modelo.Carrera;
import modelo.ControlUsuario;
import modelo.DetalleSeccion;
import modelo.Director;
import modelo.Docente;
import modelo.GlobalSemestre;
import modelo.Seccion;
import modelo.Secretaria;

/**
 *
 * @author benja
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
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession sesion = request.getSession(true);
        String user = request.getParameter("txtUser");
        String pass = request.getParameter("txtPass");
        String correo = request.getParameter("txtCorreo");
        String opcion = request.getParameter("opcion");
        
        Alumno alum = new Alumno();
        Docente doce = new Docente();
        Secretaria secre = new Secretaria();
        Director dire = new Director();
        Administrador admin = new Administrador();
        ArrayList<Seccion> arraySecciones = new ArrayList<Seccion>();
        ArrayList<Carrera> arrayCarreras = new ArrayList<Carrera>();
        ArrayList<DetalleSeccion> arrayDetalleSeccion = new ArrayList<DetalleSeccion>();        
        GlobalSemestre semestreActual = new GlobalSemestre();
        
        if (opcion.equals("Entrar")) {
            ControlUsuario ingreso = (new ControlUsuarioDAO()).buscarDatosLogin(user);
            if (ingreso != null) {
                if (ingreso.getClave().equals(pass)) {
                    sesion.setAttribute("usuario", ingreso);                    
                    int tipousuario = ingreso.getIdTipou();
                    //global tiempo
                    semestreActual = (new GlobalSemestreDAO()).buscar();
                    sesion.setAttribute("semestreActual", semestreActual);
                    switch (tipousuario) {
                         /*
                        case 1:
                            sesion.setAttribute("tipoUsuario", "alumno");
                            response.sendRedirect("alumno.jsp");
                            break;
                           
                        case 2:
                            sesion.setAttribute("tipoUsuario", "docente");
                            doce = (new DocenteDAO()).buscarDatos(ingreso.getRutUsuario());
                            sesion.setAttribute("login",doce);
                            response.sendRedirect("docente.jsp");
                            break; */
                        case 3:
                            sesion.setAttribute("tipoUsuario", "director");
                            dire = (new DirectorDAO()).buscarDatos(ingreso.getRutUsuario());
                            sesion.setAttribute("login",dire);
                            response.sendRedirect("director.jsp");
                            break;
                            /*
                        case 4:
                            sesion.setAttribute("tipoUsuario", "administrador");
                            admin= (new AdministradorDAO()).buscarDatos(ingreso.getRutUsuario());
                            sesion.setAttribute("login",admin);
                            response.sendRedirect("administrador.jsp");                            
                            break; */
                        case 5:
                            sesion.setAttribute("tipoUsuario", "secretaria");
                            secre = (new SecretariaDAO()).buscarDatos(ingreso.getRutUsuario()); 
                            arraySecciones = (new SeccionDAO()).seccionesAnyoSemestre(semestreActual.getSemestre(), semestreActual.getAnio());
                            if (arraySecciones.isEmpty()) {
                                response.sendRedirect("index.jsp?mensaje=Sistema no disponible para este semestre");
                                break;
                            }else{
                                arrayDetalleSeccion = (new DetalleSeccionDAO()).mostrar(semestreActual.getAnio(), semestreActual.getSemestre());
                                if (arrayDetalleSeccion.isEmpty()) {
                                    response.sendRedirect("index.jsp?mensaje=No hay cursos disponible para este semestre");
                                    break;
                                }else{
                                    sesion.setAttribute("login",secre);
                                    response.sendRedirect("secretaria.jsp");
                                    break;
                                }                               
                            }                            
                        default:
                            response.sendRedirect("Error.jsp");
                            break;
                    }

                } else {
                    //clave incorrecta
                    response.sendRedirect("index.jsp?mensaje=Clave Incorrecta");
                }
            } else {
                // Usuario no existe
                response.sendRedirect("index.jsp?mensaje=Usuario No Existe");
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
                        int x = (new ControlUsuarioDAO()).actualizarClave(user1);
                        response.sendRedirect("cambiarContra.jsp?mensaje=Se ha actualizado la clave");
                    } else {
                        response.sendRedirect("cambiarContra.jsp?mensaje=La clave nueva es la misma que la anterior");
                    }
                } else {
                    response.sendRedirect("cambiarContra.jsp?mensaje=Clave anterior incorrecta");
                }
            } else {
                response.sendRedirect("cambiarContra.jsp?mensaje=Las claves nuevas no coinciden");
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
