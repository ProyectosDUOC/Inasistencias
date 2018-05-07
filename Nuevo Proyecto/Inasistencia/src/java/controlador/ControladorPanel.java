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
import javax.servlet.http.HttpSession;

/**
 *
 * @author carlos
 */
public class ControladorPanel extends HttpServlet {

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
        
         HttpSession session = request.getSession(true);
        String opcion = request.getParameter("opcion");
        
        
        if (opcion.equals("Alumno")) {
            
            session.setAttribute("xCrud", "1");
            response.sendRedirect("Admin/askUser.jsp");
        }
        if (opcion.equals("Ramo")) {
            
            session.setAttribute("xCrud", "1");
            response.sendRedirect("Admin/addCurso.jsp");
        }
        if (opcion.equals("Carrera")) {
         
            session.setAttribute("xCrud", "2");
            response.sendRedirect("Admin/addCurso.jsp");
        }
        if (opcion.equals("Detalle")) {
            
            session.setAttribute("xCrud", "1");
            response.sendRedirect("Admin/addDet.jsp");
        }
        if (opcion.equals("Docente")) {
            
            session.setAttribute("xCrud", "2");
            response.sendRedirect("Admin/askUser.jsp");
        }
        if (opcion.equals("Director")) {
            
            session.setAttribute("xCrud", "3");
            response.sendRedirect("Admin/askUser.jsp");
        }
        if (opcion.equals("Seccion")) {
            
            session.setAttribute("xCrud", "1");
            response.sendRedirect("Admin/addSecc.jsp");
        }
        if (opcion.equals("Administrador")) {
            
            session.setAttribute("xCrud", "4");
            response.sendRedirect("Admin/askUser.jsp");
        }
        if (opcion.equals("Secretaria")) {
            
            session.setAttribute("xCrud", "5");
            response.sendRedirect("Admin/askUser.jsp");
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
