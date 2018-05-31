/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.InasistenciaDAO;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Inasistencia;
import dao.AlumnoDAO;
import dao.SeccionDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import modelo.Seccion;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
/**
 *
 * @author benja
 */

@WebServlet(name = "ControlSubirInasistencia", urlPatterns = {"/ControlSubirInasistencia"})
@MultipartConfig
public class ControladorSubirInasistencia extends HttpServlet {

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
        
            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();
            
            //Get the workbook instance for XLS file 
            XSSFWorkbook workbook = new XSSFWorkbook (fileContent);

            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            int idA=0;
            String seccion, rutA = "", fechaA="";
            Date fecha;
            Inasistencia falta = new Inasistencia();            
            Inasistencia inaEncontrado = new Inasistencia();
            Seccion sec = new Seccion();
            SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
            ArrayList<Inasistencia> arrayInasistencia = new ArrayList<Inasistencia>();
            int x=0;
            for(Row fila : sheet )
            {                
              /*  if(fila.getCell(0).getCellTypeEnum()==CellType.STRING)
                    rutA = fila.getCell(0).getStringCellValue();
                else
                    continue; */
                if(fila.getCell(1)==null){
                       break;
                }
                
                
                if (fila.getCell(0).getCellTypeEnum()==CellType.STRING) {
                    rutA = fila.getCell(0).getStringCellValue();
                } else{
                    if (fila.getCell(0).getCellTypeEnum()==CellType.NUMERIC) {
                        rutA = (int)fila.getCell(0).getNumericCellValue() + "";
                    }   
                }
               
                try { fecha = fila.getCell(3).getDateCellValue(); } catch (Exception ex) { break; }
                
                fechaA = parseador.format(fecha);
                
                idA = (new AlumnoDAO()).buscarDatos(rutA).getIdAlumno();
                
                seccion = fila.getCell(2).getStringCellValue();
                sec = (new SeccionDAO()).buscarCod(seccion);
                
               // 0 auto_increment, fecha Valida, fecha no, seccion, idAlumno, 0 subido, 0 Enviado 0 veces
               
               inaEncontrado = (new InasistenciaDAO()).buscarExcel(fechaA,sec.getIdSeccion(), idA); 
               falta=new Inasistencia(0, fechaA, fechaA, sec.getIdSeccion(), idA, 0, 0);
               if (inaEncontrado != null ) {
                    System.out.println("ENCONTRADO...");                      
                }else{ 
                    x = (new InasistenciaDAO()).agregarOnly(falta);                    
                    System.out.println("NUEVO... "+x);     
               }                 
            }        
            response.sendRedirect("Admin/subirInasistencia.jsp");
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
