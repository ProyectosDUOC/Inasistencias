/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AlumnoDAO;
import dao.DocenteDAO;
import dao.InasistenciaDAO;
import dao.SeccionDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Alumno;
import modelo.Docente;
import modelo.Inasistencia;
import modelo.Seccion;
import modelo.Secretaria;

/**
 *
 * @author benja
 */
public class ControladorSubirInasistenciaC extends HttpServlet {

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
        int x =0;
        ArrayList<Inasistencia> arrayInasistencias = new ArrayList<Inasistencia>();            
        ArrayList<Inasistencia> arrayAlumnoIna= new ArrayList<Inasistencia>();
        Alumno alum = new Alumno();
        Seccion seccion = new Seccion();
        Secretaria secre = new Secretaria();
        Docente docente = new Docente();
        String mensaje = "";        
        SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yyyy");
        
        if (opcion.equals("CONFIRMAR")) {
        
            arrayAlumnoIna = (new InasistenciaDAO()).buscarInaAlumnoIdExcel();      
            
            for (Inasistencia alumno : arrayAlumnoIna) {
                
                mensaje="";
                arrayInasistencias = (new InasistenciaDAO()).buscarInaAlumnoExcel(alumno.getIdAlumno());
                alum = (new AlumnoDAO()).buscarDatosId(alumno.getIdAlumno());
                
                mensaje = "<strong>Estimado Alumno: </strong> "+alum.getPnombre()+" "+alum.getAppaterno();
                mensaje = mensaje + "<br> Nuestro sistema registra que tienes inasistencias <br>";
                mensaje = mensaje + "<table>";
                mensaje = mensaje + "<tr>\n" +
"                  <th>Fecha</th>\n" +
"                  <th>Seccion</th> \n" +
"                  <th>Profesor</th>\n" +
"                </tr>";
                
                for (Inasistencia ina: arrayInasistencias) {                          
                    seccion = (new SeccionDAO()).buscar(ina.getIdSeccion());
                    docente = (new DocenteDAO()).buscarDatos(seccion.getIdDocente());
                    mensaje = mensaje + "<tr>";                    
                    mensaje = mensaje + "<td><strong></strong>"+parseador.format(ina.getFechaInasistencia())+"</td>"; 
                    mensaje = mensaje + "<td><strong></strong>"+seccion.getCodSeccion()+"</td>";
                    mensaje = mensaje + "<td><strong></strong>"+docente.getPnombre()+" "+docente.getAppaterno()+"</td>";                   
                    mensaje = mensaje + "</tr>";
                    ina.setIdEstadoc(1);
                    ina.setIdEstadoi(1);
                    x = (new InasistenciaDAO()).actualizar(ina);                    
                }
                mensaje = mensaje + "</table>";
                x = (new ControladorCorreo()).enviar(alum.getEmail(),"", mensaje, "Justificacar Inasistencia",0);
                if (x!=-1) {
                    System.out.println("No Enviado "+alum.getEmail());
                }else{
                    System.out.println("Enviado "+alum.getEmail());
                }
            }
             
            response.sendRedirect("Admin/subirInasistencia.jsp?mensaje=Enviado");
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
