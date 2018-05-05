/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AlumnoDAO;
import dao.InasistenciaDAO;
import dao.JustificacionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Alumno;
import modelo.Inasistencia;
import modelo.Justificacion;

/**
 *
 * @author benja
 */
public class ControladorJustiS extends HttpServlet {

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
            throws ServletException, IOException, ParseException {       
        response.setContentType("text/html;charset=ISO-8859-1");
        
        HttpSession session = request.getSession(true);
        
        String fechaInasistencia = request.getParameter("fecha");
        String[] miselect;        
        String opcion = request.getParameter("opcion");
        miselect = request.getParameterValues("motivo");
        String glosa = request.getParameter("glosa");
        String motivo="", rutA="", fechaActual="", idSeccion="";
        
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fechaHoy = parseador.format(date);
        int x = 0;
        Alumno alum = new Alumno();
        Justificacion justicacion = new Justificacion();
        Inasistencia inasistencia = new Inasistencia();
        
         if (opcion.charAt(0)=='G') {
            idSeccion = opcion.substring(1);       
            try{
                rutA = session.getAttribute("rut").toString();;
                for (int i = 0; i < miselect.length; i++) {
                    motivo = miselect[i];                
                }                
                alum = (new AlumnoDAO()).buscarDatos(rutA);
                
                inasistencia = new Inasistencia(0, fechaInasistencia, Integer.parseInt(idSeccion),alum.getIdAlumno() , 0, 7);
                x = (new InasistenciaDAO()).agregar(inasistencia);
                inasistencia = (new InasistenciaDAO()).buscarIdCorreo(7);            
                justicacion = new Justificacion(0,inasistencia.getIdInasistencia(),fechaHoy,Integer.parseInt(motivo), glosa);
                inasistencia.setIdEstadoc(0);
                x=(new InasistenciaDAO()).actualizar(inasistencia);
                x = (new JustificacionDAO()).agregar(justicacion);                System.out.println("D 4444444444444 estado = "+x );
                response.sendRedirect("secretaria.jsp?mensaje=Justificacion Enviada");
            }finally{    
                response.sendRedirect("secretaria.jsp?mensaje=Error");
            }           
        }
        /*         
            CREATE TABLE justificacion (
           id_justificacion      INT NOT NULL AUTO_INCREMENT,
           id_inasistencia       INT NOT NULL,
           fecha_justificacion   DATE NOT NULL,
           id_motivo             INT NOT NULL,
           glosa                 VARCHAR(300),
           PRIMARY KEY(id_justificacion)
       );

         */
        
        
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorJustiS.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorJustiS.class.getName()).log(Level.SEVERE, null, ex);
        }
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
