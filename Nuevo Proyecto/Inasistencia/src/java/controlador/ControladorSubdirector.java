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
import dao.SecretariaDAO;
import dao.SubSecretariaDAO;
import dao.SubdirectorComentarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Alumno;
import modelo.ControlUsuario;
import modelo.Docente;
import modelo.Inasistencia;
import modelo.Justificacion;
import modelo.ReporteSecretaria;
import modelo.Secretaria;
import modelo.SecretariaSda;
import modelo.SubdirectorComentario;

/**
 *
 * @author benja
 */
public class ControladorSubdirector extends HttpServlet {

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
        String id;
        SubdirectorComentario sub = new SubdirectorComentario();
        Inasistencia ina = new Inasistencia();
        Justificacion justi = new Justificacion();
        ControlUsuario user = new ControlUsuario();
        SecretariaSda secreSDA = new SecretariaSda();
        ReporteSecretaria reporte = new ReporteSecretaria();
        Alumno  alum = new Alumno();
        Docente doce = new Docente();
        Secretaria sec = new Secretaria();
        String mensaje ="";
        String comentario = request.getParameter("txtComentario");
        int x = 0;
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        if (opcion.charAt(0) == 'J') {
            id = opcion.substring(1);
            session.setAttribute("idJ", id);
            response.sendRedirect("Subdirector/subverJustificacion.jsp");
        }
        if (opcion.charAt(0) == 'V') {
            id = opcion.substring(1);
            session.setAttribute("idJ", id);
            response.sendRedirect("Subdirector/subverJustificacion.jsp");
        }
        if (opcion.charAt(0) == 'E') {
            id = opcion.substring(1);
            x = (new SubdirectorComentarioDAO()).eliminar(Integer.parseInt(id));
            id = session.getAttribute("idJ").toString();
            session.setAttribute("idJ", id);
            response.sendRedirect("Subdirector/subverJustificacion.jsp");
        }
        if (opcion.equals("Comentar")) {
            id = session.getAttribute("idJ").toString();
            if (comentario.length() > 0) {

                user = (ControlUsuario) session.getAttribute("usuario");
                justi = (new JustificacionDAO()).buscar(Integer.parseInt(id));
                secreSDA = (new SubSecretariaDAO()).buscarDatos(user.getRutUsuario());
                /* comentario : 
                    Integer idSubdirectorC
                    int idInasistencia, 
                    int idSecretariaSda,
                    int idSubdirector,
                    String glosa,
                    String fechaComentarios               
                 */
                sub = new SubdirectorComentario(0, justi.getIdInasistencia(), secreSDA.getIdSecretariaSda(), 0, parseador.format(date), comentario);
                x = (new SubdirectorComentarioDAO()).agregar(sub);

                session.setAttribute("idJ", id);
                response.sendRedirect("Subdirector/subverJustificacion.jsp");
            } else {
                session.setAttribute("idJ", id);
                response.sendRedirect("Subdirector/subverJustificacion.jsp?mensaje=Comente");
            }
        }
        if (opcion.equals("Revisado")) {
            id = session.getAttribute("idJ").toString();
            user = (ControlUsuario) session.getAttribute("usuario");
            justi = (new JustificacionDAO()).buscar(Integer.parseInt(id));
            secreSDA = (new SubSecretariaDAO()).buscarDatos(user.getRutUsuario());

            ina = (new InasistenciaDAO()).buscar(justi.getIdInasistencia());
            ina.setIdEstadoi(10);
            x = (new InasistenciaDAO()).actualizar(ina);

            session.setAttribute("idJ", id);
            response.sendRedirect("Subdirector/subverJustificacion.jsp");
        }
        
        if (opcion.equals("Aprobado")) {
            id = session.getAttribute("idJ").toString();
            user = (ControlUsuario) session.getAttribute("usuario");
            justi = (new JustificacionDAO()).buscar(Integer.parseInt(id));            
            ina = (new InasistenciaDAO()).buscar(justi.getIdInasistencia());
            if (user.getIdTipou()==6) {
                secreSDA = (new SubSecretariaDAO()).buscarDatos(user.getRutUsuario());
                ina.setIdEstadoi(13);
            }
            if (user.getIdTipou()==7) {
                secreSDA = (new SubSecretariaDAO()).buscarDatos(user.getRutUsuario());
                ina.setIdEstadoi(11);
            }
            reporte = (new ReporteSecretariaDAO()).buscarDatos(justi.getIdJustificacion());
            reporte.setActivo(2);
            x = (new ReporteSecretariaDAO()).actualizarActivo(reporte.getIdReporte(),2);            
            x = (new InasistenciaDAO()).actualizar(ina);
            alum =(new AlumnoDAO()).buscarDatosId(reporte.getIdAlumno());
            
            //ENVIAR CORREO
            mensaje = (new ControladorCorreo()).mensajeAprobadaSubDirector(alum, ina, justi);
            sec =(new SecretariaDAO()).buscarDatos(reporte.getIdSecretaria());
            x = (new ControladorCorreo()).enviar(alum.getEmail(),sec.getEmail(), mensaje,"Justificacion Aprobada Subdirector Academico", 1);
            //profesor
            doce = (new DocenteDAO()).buscarDatos((new SeccionDAO()).buscar(ina.getIdSeccion()).getIdDocente());
            
            mensaje =(new ControladorCorreo()).mensajeAprobadoSubDocente(doce, alum, ina, justi);
            x = (new ControladorCorreo()).enviar(alum.getEmail(),sec.getEmail(), mensaje,"Justificacion Alumno Aprobada por Subdirector Academico", 1);
            session.setAttribute("idJ", id);
            response.sendRedirect("Subdirector/subverJustificacion.jsp");
        }
         if (opcion.equals("Rechazado")) {
            id = session.getAttribute("idJ").toString();
            user = (ControlUsuario) session.getAttribute("usuario");
            justi = (new JustificacionDAO()).buscar(Integer.parseInt(id));            
            ina = (new InasistenciaDAO()).buscar(justi.getIdInasistencia());
            if (user.getIdTipou()==6) {
                secreSDA = (new SubSecretariaDAO()).buscarDatos(user.getRutUsuario());
                ina.setIdEstadoi(14);
            }
            if (user.getIdTipou()==7) {
                secreSDA = (new SubSecretariaDAO()).buscarDatos(user.getRutUsuario());
                ina.setIdEstadoi(12);
            }
            reporte = (new ReporteSecretariaDAO()).buscarDatos(justi.getIdJustificacion());
            reporte.setActivo(3);
            x = (new ReporteSecretariaDAO()).actualizarActivo(reporte.getIdReporte(),3);            
            x = (new InasistenciaDAO()).actualizar(ina);

             //ENVIAR CORREO
             
            alum =(new AlumnoDAO()).buscarDatosId(reporte.getIdAlumno());
            mensaje = (new ControladorCorreo()).mensajeRechazoSubDirector(alum, ina, justi);
            sec =(new SecretariaDAO()).buscarDatos(reporte.getIdSecretaria());
            x = (new ControladorCorreo()).enviar(alum.getEmail(),sec.getEmail(), mensaje,"Justificacion Rechazada por Subdirector Academico", 1);
            session.setAttribute("idJ", id);
            response.sendRedirect("Subdirector/subverJustificacion.jsp");
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
