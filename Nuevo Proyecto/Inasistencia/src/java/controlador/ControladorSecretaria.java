/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AlumnoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Alumno;

/**
 *
 * @author benja
 */
public class ControladorSecretaria extends HttpServlet {

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
        response.setContentType("text/html;charset=ISO-8859-1");

        HttpSession sesion = request.getSession(true);
        String rut = request.getParameter("txtRut");
        String opcion = request.getParameter("opcion");
        String cel = request.getParameter("txtCel");
        String idSeccion = "", mensaje = "";
        int x = -1;
        Alumno alumno = new Alumno();
        String idAlumno="";

        if (opcion.equals("Buscar")) {
            if (rut.length() > 0) {
                Alumno alum = (new AlumnoDAO()).buscarDatos(rut);
                if (alum != null) {
                    sesion.setAttribute("idAlumno", null);
                    sesion.setAttribute("rut", rut);
                    response.sendRedirect("secretaria.jsp");
                } else {                    
                    sesion.setAttribute("idAlumno", null);
                    sesion.setAttribute("rut", null);
                    response.sendRedirect("secretaria.jsp?mensaje=Alumno no encontrado");
                }
            } else {                
                sesion.setAttribute("idAlumno", null);
                sesion.setAttribute("rut", null);
                response.sendRedirect("secretaria.jsp?mensaje=Alumno no encontrado");
            }
        }
        if (opcion.equals("Nuevo")) {
            sesion.setAttribute("rut", null);
            sesion.setAttribute("idAlumno", null);
            response.sendRedirect("secretaria.jsp");
        }
        if (opcion.charAt(0) == 'C') {  //Actualizo
            idAlumno = opcion.substring(1);  //id para actualizar
            sesion.setAttribute("idAlumno", idAlumno);
            response.sendRedirect("secretaria.jsp");
        }
        if (opcion.charAt(0) == 's') {
            idSeccion = opcion.substring(1);
            sesion.setAttribute("idSeccion", idSeccion);
            response.sendRedirect("justificarSecretaria.jsp");
        }        
        if (opcion.charAt(0) == 'x') {  //Actualizo
            idSeccion = opcion.substring(1);  //id para actualizar
            if (cel.length() > 0) {
                alumno = (new AlumnoDAO()).buscarDatosId(Integer.parseInt(idSeccion));
                alumno.setCelular(cel);
                x = (new AlumnoDAO()).actualizar(alumno);
                mensaje = "Telefono actualizado";
            } else {
                mensaje = "Ingrese telefono";
            }
            response.sendRedirect("secretaria.jsp?mensaje=" + mensaje);
        }
        
        if (opcion.charAt(0) == 'y') {  //Agrego numero
            idSeccion = opcion.substring(1);  //id del alumno para guardar
            if (cel.length() > 0) {
                mensaje = "Telefono agregado";
                alumno = (new AlumnoDAO()).buscarDatosId(Integer.parseInt(idSeccion));
                alumno.setCelular(cel);
                x = (new AlumnoDAO()).actualizar(alumno);
            } else {
                mensaje = "Ingrese telefono";
            }
            response.sendRedirect("secretaria.jsp?mensaje=" + mensaje);
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
