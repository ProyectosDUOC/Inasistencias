/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AlumnoDAO;
import dao.CarreraDAO;
import dao.DirectorDAO;
import dao.ImagenDAO;
import dao.InasistenciaDAO;
import dao.JustificacionDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.Alumno;
import modelo.Carrera;
import modelo.ControlUsuario;
import modelo.Director;
import modelo.GlobalSemestre;
import modelo.Inasistencia;
import modelo.Justificacion;
import modelo.JustificacionImagen;
import modelo.Subdirector;

/**
 *
 * @author benja
 */
@MultipartConfig
public class ControladorJustificarAlumno extends HttpServlet {

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
        ControlUsuario user = session.getAttribute("usuario") == null ? new ControlUsuario() : (ControlUsuario) session.getAttribute("usuario");

        String fechaInasistencia = request.getParameter("fecha");
        String[] miselect;
        String opcion = request.getParameter("opcion");
        miselect = request.getParameterValues("motivo");
        String glosa = request.getParameter("glosa");
        String motivo = "", rutA = "", fechaActual = "", idSeccion = "", descripcion="", mensaje="", correo="", idIna="";

        
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fechaHoy = parseador.format(date);
        int x = 0, estadoIna=2;
        Alumno alum = new Alumno();
        //Secretaria secre = new Secretaria();
        Justificacion justificacion = new Justificacion();
        Inasistencia inasistencia = new Inasistencia();
        JustificacionImagen img = new JustificacionImagen();
        //ReporteSecretaria report = new ReporteSecretaria();
        GlobalSemestre semestreActual = new GlobalSemestre();
        Carrera carrera = new Carrera();
        Part filePart = request.getPart("file"); //img
        //String dias = request.getParameter("grupo1");
        Director dire = new Director();
        Subdirector subd = new Subdirector();
        
        if (opcion.charAt(0) == 'G') {
            idIna = opcion.substring(1);
            inasistencia = (new InasistenciaDAO()).buscar(Integer.parseInt(idIna));
            alum = (new AlumnoDAO()).buscarDatosId(inasistencia.getIdAlumno());
            
            rutA = alum.getRutAlumno();
            for (int i = 0; i < miselect.length; i++) {
                motivo = miselect[i];
            }
            alum = (new AlumnoDAO()).buscarDatos(rutA);
                        
            inasistencia.setIdEstadoi(2);

            //inasistencia = new Inasistencia(0, fechaInasistencia,fechaInasistencia, Integer.parseInt(idSeccion), alum.getIdAlumno(), estadoIna, 7);
            x = (new InasistenciaDAO()).actualizar(inasistencia);
            
            
            justificacion = new Justificacion(0, inasistencia.getIdInasistencia(), fechaHoy, Integer.parseInt(motivo), glosa);
           
            x = (new JustificacionDAO()).agregar(justificacion);
            justificacion = (new JustificacionDAO()).buscarEspecifica(justificacion);
            
            if (filePart.getSize()>0) {
                File file = File.createTempFile("foto-", ".jpg");
                File file2 = new File(System.getenv("UPLOADS"), file.getName());
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file2.toPath());
                }            
                String nombreFile = file2.getName();
                descripcion="idIna:"+inasistencia.getIdInasistencia()+" rut:"+rutA+" fecha:"+fechaInasistencia;
                img = new JustificacionImagen(0, justificacion.getIdJustificacion(), nombreFile, descripcion);
                x = (new ImagenDAO()).agregar(img);
            }            
           
         
            carrera = (new CarreraDAO()).buscar(alum.getIdCarrera());
            dire = (new DirectorDAO()).buscarDatos(carrera.getIdDirector());
            mensaje= (new ControladorCorreo()).mensajeDirector(dire, alum);
            correo = dire.getEmail();
            
            //Correo
            x =(new ControladorCorreo()).enviar(correo,"", mensaje, "Justificar Inasistencia",0);
            //Correo alumno
            mensaje = (new ControladorCorreo()).mensajeConfirmacionEnvioAlumno(alum);
            x = (new ControladorCorreo()).enviar(alum.getEmail(),"",mensaje,"Informe de solicitud enviada",0);
            
            session.setAttribute("idIna", null);
            response.sendRedirect("alumno.jsp");
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
