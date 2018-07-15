/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.InasistenciaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Inasistencia;

/**
 *
 * @author benja
 */
public class ControladorDocente extends HttpServlet {

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
        try {
            HttpSession session = request.getSession(true);    
            String idSeccion = "";
            Inasistencia ina = new Inasistencia();
            String opcion = request.getParameter("opcion");
            if (opcion.charAt(0) == 'S') {
                idSeccion = opcion.substring(1);
                
                session.setAttribute("idSeccion",idSeccion);
                response.sendRedirect("Docente/cursos.jsp");  
            }
            //Ver
            if (opcion.charAt(0) == 'V') {
                
                idSeccion = opcion.substring(1);                
                session.setAttribute("idInasistencia",idSeccion); 
                session.setAttribute("Vista","V");
                response.sendRedirect("Docente/verJustificacion.jsp");  
            }  
            //justificar
            if (opcion.charAt(0) == 'J') {
                
                idSeccion = opcion.substring(1);
                session.setAttribute("idInasistencia",idSeccion);     
                response.sendRedirect("Docente/verJustificacion.jsp");  
            }
            //Revisado
            if (opcion.charAt(0) == 'R') {
                
                idSeccion = opcion.substring(1);
                ina = (new InasistenciaDAO()).buscar(Integer.parseInt(idSeccion));
                ina.setIdEstadoi(4);
                int x = (new InasistenciaDAO()).actualizar(ina);
                System.out.println("que pasa con x" + x);
                session.setAttribute("idInasistencia",idSeccion);       
                response.sendRedirect("Docente/verJustificacion.jsp");  
            }
            
        }catch(Exception ex){
               response.sendRedirect("index.jsp");  
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
