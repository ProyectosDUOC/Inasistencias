/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AdministradorDAO;
import dao.AlumnoDAO;
import dao.ControlUsuarioDAO;
import dao.DirectorDAO;
import dao.DocenteDAO;
import dao.SecretariaDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Administrador;
import modelo.Alumno;
import modelo.ControlUsuario;
import modelo.Director;
import modelo.Docente;
import modelo.Secretaria;

/**
 *
 * @author benja
 */
public class ControladorAdministrador extends HttpServlet {

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
        String rut = request.getParameter("txtRut");
        String pnom = request.getParameter("txtPnombre");
        String snom = request.getParameter("txtSnombre");
        String apat = request.getParameter("txtApaterno");
        String amat = request.getParameter("txtAmaterno");
        String mail = request.getParameter("txtEmail");
        
        String usuario = request.getParameter("txtUsuario");        
        String contra = request.getParameter("txtContra");
           
        String xCrud = "";
        
        
        Alumno alu = new Alumno();
        Docente doce = new Docente();
        Director dire = new Director();
        Administrador admin = new Administrador();
        Secretaria secr = new Secretaria();
        
        String carrera= "";
        int x=0;
        String[] miselect;
        miselect = request.getParameterValues("carrera");
        
        

        if (opcion.equals("buscar")) {
            xCrud = sesion.getAttribute("xCrud").toString();
            sesion.setAttribute("rutU",rut);
            if (xCrud.equals("1")) {
                alu = (new AlumnoDAO()).buscarDatos(rut);
                if (alu == null) {
                    
                    response.sendRedirect("Admin/addUser.jsp");  
                }
                else
                {
                    sesion.setAttribute("respU",alu);
                    response.sendRedirect("Admin/addUser.jsp");
                }
                
            }
            if (xCrud.equals("2")) {
                doce = (new DocenteDAO()).buscarDatos(rut);
                if (doce == null) {
                   
                    response.sendRedirect("Admin/addUser.jsp");
                }
                else
                {
                    sesion.setAttribute("respU",doce);
                    response.sendRedirect("Admin/addUser.jsp");
                }
            }
            if (xCrud.equals("3")) {
                dire = (new DirectorDAO()).buscarDatos(rut);
                if (doce == null) {
                    response.sendRedirect("Admin/addUser.jsp");
                }
                else
                {
                    sesion.setAttribute("respU",dire);
                    response.sendRedirect("Admin/addUser.jsp");
                }
            }
            if (xCrud.equals("4")) {
                admin = (new AdministradorDAO()).buscarDatos(rut);
                if (doce == null) {
                    response.sendRedirect("Admin/addUser.jsp");
                }
                else
                {
                    sesion.setAttribute("respU",admin);
                    response.sendRedirect("Admin/addUser.jsp");
                }
            }
            if (xCrud.equals("5")) {
                secr = (new SecretariaDAO()).buscarDatos(rut);
                if (doce == null) {
                    response.sendRedirect("Admin/addUser.jsp");
                }
                else
                {
                    sesion.setAttribute("respU",secr);
                    response.sendRedirect("Admin/addUser.jsp");
                }
            }
        }

        
        if (opcion.equals("Agregar")) {
            xCrud = sesion.getAttribute("xCrud").toString();
            if (xCrud.equals("1")) {
                for (int i = 0; i < miselect.length; i++) {
                    carrera = miselect[i];
                }                
                alu = new Alumno(0, rut, pnom, snom, apat, amat, mail, Integer.parseInt(carrera) , 1);         
                x = (new AlumnoDAO()).agregar(alu);                
                if (x != 0) {
                    response.sendRedirect("index.jsp?mensaje=Agregado"+x);
                }
            }
            if (xCrud.equals("2")) {
                doce = new Docente(0, rut, pnom, snom, apat, amat, mail, 1);
                x = (new DocenteDAO()).agregar(doce);
                if (x != 0) {
                    response.sendRedirect("index.jsp?mensaje=Agregado"+x);
                }
            }
            if (xCrud.equals("3")) {
                //dire = (new DirectorDAO()).buscarDatos(rut);
                
                dire = new Director(0, rut, pnom, snom, apat, amat, mail, 1);
                x= (new DirectorDAO()).agregar(dire);
                if (x != 0) {
                    response.sendRedirect("index.jsp?mensaje=Agregado"+x);
                }
            }
            if (xCrud.equals("4")) {
                //admin = (new AdministradorDAO()).buscarDatos(rut);
                admin = new Administrador(0, rut, pnom, snom, apat, amat, mail, 1);
                x = (new AdministradorDAO()).agregar(admin);
                if (x != 0) {
                    response.sendRedirect("index.jsp?mensaje=Agregado"+x);
                }
            }
            if (xCrud.equals("5")) {
                //secr = (new SecretariaDAO()).buscarDatos(rut);
                secr = new Secretaria(0, rut, pnom, snom, apat, amat, mail, 1);
                x = (new SecretariaDAO()).agregar(secr);                
                x = (new ControlUsuarioDAO()).agregar(new ControlUsuario(0, usuario,contra, rut, 5, 1));
                if (x != 0) {
                    response.sendRedirect("Admin/panelControl.jsp?mensaje=Secretaria Ingresada");
                }
            }
        }
          
        if (opcion.equals("Actualizar")) {
            xCrud = sesion.getAttribute("xCrud").toString();
            if (xCrud.equals("1")) {
                for (int i = 0; i < miselect.length; i++) {
                    carrera = miselect[i];
                }                
                alu = new Alumno(0, rut, pnom, snom, apat, amat, mail, Integer.parseInt(carrera) , 1);         
                x = (new AlumnoDAO()).actualizar(alu);                
                if (x != 0) {
                    response.sendRedirect("index.jsp?mensaje=Actualizado "+x);
                }
            }
            if (xCrud.equals("2")) {
                doce = new Docente(0, rut, pnom, snom, apat, amat, mail, 1);
                x = (new DocenteDAO()).actualizar(doce);
                if (x != 0) {
                    response.sendRedirect("index.jsp?mensaje=Actualizado "+x);
                }
            }
            if (xCrud.equals("3")) {                
                dire = new Director(0, rut, pnom, snom, apat, amat, mail, 1);
                x= (new DirectorDAO()).actualizar(dire);
                if (x != 0) {
                    response.sendRedirect("index.jsp?mensaje=Actualizado "+x);
                }
            }
            if (xCrud.equals("4")) {
                admin = new Administrador(0, carrera, pnom, snom, apat, amat, mail, 1);
                x = (new AdministradorDAO()).actualizar(admin);
                if (x != 0) {
                    response.sendRedirect("index.jsp?mensaje=Actualizado "+x);
                }
            }
            if (xCrud.equals("5")) {
                secr = new Secretaria(0, rut, pnom, snom, apat, amat, mail, 1);
                x = (new SecretariaDAO()).actualizar(secr);                
                if (x != 0) {
                    response.sendRedirect("index.jsp?mensaje=Actualizado "+x);
                }
            }
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
