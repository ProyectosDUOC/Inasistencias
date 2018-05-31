/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.GlobalSemestreDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.GlobalSemestre;

/**
 *
 * @author benja
 */
public class ControladorSemestre extends HttpServlet {

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
        
        String opcion = request.getParameter("opcion");
        
        String[] anios;
        anios = request.getParameterValues("anio");
        String[] semestres;        
        semestres = request.getParameterValues("semestre");
        String anio="", semestre="";
        String fechaInicio = request.getParameter("fecha1");        
        String fechaTermino = request.getParameter("fecha2");
        GlobalSemestre global = new GlobalSemestre();
        int x=0;
        
        if (opcion.equals("Actualizar")) {
            for (int i = 0; i < anios.length; i++) {
                anio = anios[i];
            }
            for (int i = 0; i < semestres.length; i++) {
                semestre = semestres[i];
            }
            global = new GlobalSemestre(1, Integer.parseInt(semestre), Integer.parseInt(anio), fechaInicio, fechaTermino);
            x = (new GlobalSemestreDAO()).actualizar(global);
            
            response.sendRedirect("Admin/cambiarSemestre.jsp?mensaje=Se ha actualizado");
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
