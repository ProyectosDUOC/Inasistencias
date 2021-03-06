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
import dao.ReporteSecretariaDAO;
import dao.SecretariaDAO;
import dao.SubdirectorDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
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
import modelo.ReporteSecretaria;
import modelo.Secretaria;
import modelo.Subdirector;

/**
 *
 * @author benja
 */
@MultipartConfig
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
        ControlUsuario user = session.getAttribute("usuario") == null ? new ControlUsuario() : (ControlUsuario) session.getAttribute("usuario");

        String fechaInasistencia = request.getParameter("fecha");
        String fechaInasistencia2 = "";
        String[] miselect;
        String opcion = request.getParameter("opcion");
        miselect = request.getParameterValues("motivo");
        String glosa = request.getParameter("glosa");
        String motivo = "", rutA = "", fechaActual = "", idSeccion = "", descripcion="", mensaje="", correo="";

        
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fechaHoy = parseador.format(date);
        int x = 0, estadoIna=3;
        Alumno alum = new Alumno();
        Secretaria secre = new Secretaria();
        Justificacion justificacion = new Justificacion();
        Inasistencia inasistencia = new Inasistencia();
        JustificacionImagen img = new JustificacionImagen();
        ReporteSecretaria report = new ReporteSecretaria();
        GlobalSemestre semestreActual = new GlobalSemestre();
        Carrera carrera = new Carrera();
        Part filePart = request.getPart("file"); //img
        String dias = request.getParameter("grupo1");
        Director dire = new Director();
        Subdirector subd = new Subdirector();
        
        if (opcion.charAt(0) == 'G') {
            idSeccion = opcion.substring(1);

            rutA = session.getAttribute("rut").toString();
            for (int i = 0; i < miselect.length; i++) {
                motivo = miselect[i];
            }
            alum = (new AlumnoDAO()).buscarDatos(rutA);
            
            if (Integer.parseInt(motivo)==3 || Integer.parseInt(motivo)==11) {
                estadoIna=9;
            }
            
            if (dias.equals("1")) { //un solo dia
                inasistencia = new Inasistencia(0, fechaInasistencia,fechaInasistencia, Integer.parseInt(idSeccion), alum.getIdAlumno(), estadoIna, 7);
                x = (new InasistenciaDAO()).agregarOnly(inasistencia);
            }
            if (dias.equals("2")) { //mas de un dia
                
                fechaInasistencia2 = request.getParameter("fecha2");
                inasistencia = new Inasistencia(0, fechaInasistencia,fechaInasistencia2, Integer.parseInt(idSeccion), alum.getIdAlumno(), estadoIna, 7);
                x = (new InasistenciaDAO()).agregar(inasistencia);
                                
            }
            
            inasistencia = (new InasistenciaDAO()).buscarIdCorreo(7);
            justificacion = new Justificacion(0, inasistencia.getIdInasistencia(), fechaHoy, Integer.parseInt(motivo), glosa);
            x = (new InasistenciaDAO()).actualizarCorreoSecretaria(inasistencia.getIdInasistencia(), 0);
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
           
            semestreActual = (GlobalSemestre) session.getAttribute("semestreActual");
            secre = (new SecretariaDAO()).buscarDatos(user.getRutUsuario());
            carrera = (new CarreraDAO()).buscar(alum.getIdCarrera());
            
            report = new ReporteSecretaria(0,justificacion.getIdInasistencia(),justificacion.getIdJustificacion(),secre.getIdSecretaria(),carrera.getIdDirector(), alum.getIdAlumno(), semestreActual.getSemestre(), semestreActual.getAnio(), 1);
            x = (new ReporteSecretariaDAO()).agregar(report);
            
            if (Integer.parseInt(motivo)==3 || Integer.parseInt(motivo)==11) {
                subd = (new SubdirectorDAO()).buscarDatosSolo();
                mensaje= (new ControladorCorreo()).mensajeSubdirector(subd, alum);
                correo=subd.getEmail();
            }else{
                dire = (new DirectorDAO()).buscarDatos(carrera.getIdDirector());
                mensaje= (new ControladorCorreo()).mensajeDirector(dire, alum);
                correo = dire.getEmail();
            }
            
          
            //Correo
            x =(new ControladorCorreo()).enviar(correo,secre.getEmail(), mensaje, "Justificar Inasistencia",1);
            //Correo alumno
            mensaje = (new ControladorCorreo()).mensajeConfirmacionEnvio(secre, alum);
            x = (new ControladorCorreo()).enviar(alum.getEmail(),secre.getEmail(),mensaje,"Informe de solicitud enviada",1);
            
            session.setAttribute("rut", null);
            response.sendRedirect("secretaria.jsp?mensaje=Se ha enviado exitosamente");
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
