/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AlumnoDAO;
import dao.DocenteDAO;
import dao.InasistenciaDAO;
import dao.JustificacionDAO;
import dao.ReporteSecretariaDAO;
import dao.SeccionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Alumno;
import modelo.Docente;
import modelo.Inasistencia;
import modelo.Justificacion;
import modelo.ReporteSecretaria;
import modelo.Seccion;

/**
 *
 * @author benja
 */
public class ControladorVerJustifi extends HttpServlet {

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
        
        ReporteSecretaria reporte= new ReporteSecretaria();   
        Inasistencia inasistencia = new Inasistencia();    
        Seccion seccion = new Seccion();
        Docente doce = new Docente();
        Alumno alum = new Alumno();
        Justificacion justi = new Justificacion();
        String opcion = request.getParameter("opcion");
        String mensaje="", asunto="";
        int x;
        if (opcion.equals("Aprobar")) {
            reporte = (ReporteSecretaria)sesion.getAttribute("reporte");
            System.out.println("1************");
            x= (new ReporteSecretariaDAO()).actualizarActivo(reporte.getIdReporte(),2);
             System.out.println("2************");
            inasistencia=(new InasistenciaDAO()).buscar(reporte.getIdInasistencia());
             System.out.println("3************");
            inasistencia.setIdEstadoi(6);
            (new InasistenciaDAO()).actualizar(inasistencia);
             System.out.println("4************");
            seccion = (new SeccionDAO()).buscar(inasistencia.getIdSeccion());
             System.out.println("5************");
            doce =(new DocenteDAO()).buscarDatos(seccion.getIdDocente());
             System.out.println("6************");
            //JUSTI
            alum = (new AlumnoDAO()).buscarDatosId(inasistencia.getIdAlumno());
             System.out.println("7************");
            justi = (new JustificacionDAO()).buscar(reporte.getIdJustificacion());
             System.out.println("8************");
            //Enviar Correo Electronico
            mensaje = (new ControladorCorreo()).mensajeAprobadoAlumno(alum,inasistencia,justi);
             System.out.println("9************");
            asunto = "Justificacion Aprobada";    
            
            x = (new ControladorCorreo()).enviar(alum.getEmail(), mensaje, asunto);
             System.out.println("10 ************");
            
            
            response.sendRedirect("Director/justificaciones.jsp");
        }
        if (opcion.equals("Rechazar")) {
            reporte = (ReporteSecretaria)sesion.getAttribute("reporte");  
             System.out.println("AAAAAAAAA");
            x= (new ReporteSecretariaDAO()).actualizarActivo(reporte.getIdReporte(),3);
             System.out.println("bbbbbbbbbb");
            inasistencia=(new InasistenciaDAO()).buscar(reporte.getIdInasistencia());
             System.out.println("CCCCCCCCCCCCCCCCCCCC");
            inasistencia.setIdEstadoi(7);
            (new InasistenciaDAO()).actualizar(inasistencia);
             System.out.println("DDDDDDDDDDDDD");
            response.sendRedirect("Director/justificaciones.jsp");
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
