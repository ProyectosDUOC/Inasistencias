/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ClasesConsultas;
import dao.DocenteDAO;
import dao.ImagenDAO;
import dao.InasistenciaDAO;
import dao.JustificacionDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.Docente;
import modelo.Inasistencia;
import modelo.InasistenciaImagen;
import modelo.Seccion;

/**
 *
 * @author benja
 */
public class ControladorJusti extends HttpServlet {

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
        String opcion = request.getParameter("opcion");
        String glosa = request.getParameter("glosa");
        String[] miselect;
        miselect = request.getParameterValues("motivo");
        Inasistencia inasistencia = new Inasistencia();
        Seccion seccion = new Seccion();
        Docente docente = new Docente();
        
        //Imagen
        Part filePart ;
        InputStream fileContent = null;
        InasistenciaImagen imagen;
        boolean estado = false;
        String fileName="", idIna = "", motivo = "";
        
        if(opcion.charAt(0)=='g'){ 
            idIna = opcion.substring(1);
            for (int i = 0; i < miselect.length; i++) {
                motivo = miselect[i];
            }

            int idMotivo = Integer.parseInt(motivo);
            (new JustificacionDAO()).actualizarJustificacion(Integer.parseInt(idIna), idMotivo, glosa, 1);
            (new InasistenciaDAO()).actualizarEnviadoAlumnos(Integer.parseInt(idIna), 2);
            inasistencia = (new InasistenciaDAO()).buscar(Integer.parseInt(idIna));
            seccion = (new ClasesConsultas()).buscarSeccion(inasistencia.getIdSeccion());
            docente = (new DocenteDAO()).buscarDatos(seccion.getRutDocente());
            
            String asunto = "Inasistencia Justificada Por Alumno";
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("Estimado Docente: ");
            mensaje.append(docente.getPnombre());
            mensaje.append(" ");
            mensaje.append(docente.getAppaterno());
            mensaje.append("\n");
            mensaje.append(" Nuestro sistema acaba de tener un registro de inasistencia por el alumno ");
            mensaje.append(" RUT: ");
            mensaje.append(inasistencia.getRutAlumno());
            mensaje.append(" de la Seccion ");
            mensaje.append(seccion);
            mensaje.append("Por favor ingrese al portal web sitio www.miinasistencia.cl y verifique justificacion.\n");

            (new ControladorCorreo()).EnviarProfesor(docente.getEmail(), mensaje.toString(), asunto);
            
            
            /* Imagen
            if (request.getPart("file")!=null) {
                filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                fileContent = filePart.getInputStream();
                imagen = new InasistenciaImagen(Integer.parseInt(idIna),fileName,fileContent, glosa);
                int x = (new ImagenDAO()).agregar(imagen);
            } */
            
            sesion.setAttribute("idInasistencia",idIna);
            response.sendRedirect("VerJustificacion.jsp");
        }
         if (opcion.charAt(0) == 'j') {
            idIna = opcion.substring(1);
            (new InasistenciaDAO()).actualizarEnviadoAlumnos(Integer.parseInt(idIna), 3);
            sesion.setAttribute("idInasistencia",idIna);
            response.sendRedirect("VerJustificacion.jsp");
        }
        
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorJusti</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> " + idIna + "id motivo: "+ motivo+ "</h1>");
            out.println("</body>");
            out.println("</html>");
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

    private void actualizarJustificacion(int parseInt, int idMotivo, String glosa, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
