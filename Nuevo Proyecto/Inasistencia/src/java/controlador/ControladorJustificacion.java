/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author benja
 */
public class ControladorJustificacion extends HttpServlet {

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
        String id = "";
        
        String opcion = request.getParameter("opcion");
        
        //Director
        if (opcion.charAt(0) == 'J') {
            id = opcion.substring(1);           
            
            session.setAttribute("idJ", id);
            response.sendRedirect("Director/verJustificacion.jsp");
        }
        if (opcion.charAt(0) == 'V') {
            id = opcion.substring(1);           
            
            session.setAttribute("idJ", id);
            response.sendRedirect("Director/verJustificacion.jsp");
        }
        
        //Alumno
        
        //Pendiente
         if (opcion.charAt(0) == 'P') {
            id = opcion.substring(1);           
            
            session.setAttribute("idJ", id);
            response.sendRedirect("Director/verJustificacion.jsp");
        }
        //Ver el estado
          if (opcion.charAt(0) == 'v') {
            id = opcion.substring(1);           
            
            session.setAttribute("idJ", id);
            response.sendRedirect("Director/verJustificacion.jsp");
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
