package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import modelo.*;
import dao.*;

public final class Alumno_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>Alumno</title>\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1.0\"/>\r\n");
      out.write("        <!-- CSS  -->\r\n");
      out.write("        <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\r\n");
      out.write("        <link href=\"css/materialize.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen,projection\"/>\r\n");
      out.write("        <link href=\"css/style1.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen,projection\"/>\r\n");
      out.write("\r\n");
      out.write("        ");

            ControlUsuario user = (ControlUsuario) session.getAttribute("usuario");
            if (user == null) response.sendRedirect("error.jsp");
            int rutAlumno = user.getRutUsuario();
            Alumno alu = (new AlumnoDAO()).buscarDatos(rutAlumno);
            if (alu == null) response.sendRedirect("error.jsp");
            ArrayList<Inasistencia> faltas = (new InasistenciaDAO()).buscarRut(rutAlumno);
            ClasesConsultas consultaBD = new ClasesConsultas();
        
      out.write("\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            <h1 class=\"yellow darken-1\">Menu Alumno</h1>\r\n");
      out.write("            <form action=\"ControladorAlumno\" method=\"post\" >\r\n");
      out.write("                <button class=\"btn waves-effect waves-light red right\" type=\"submit\" name=\"opcion\" value=\"Salir\">\r\n");
      out.write("                    Cerrar Sesion\r\n");
      out.write("                </button>\r\n");
      out.write("                <h3 class=\"black-text\">Datos Alumno</h3>               \r\n");
      out.write("                <ul>\r\n");
      out.write("                    <li class=\"amber darken-3 black-text\">Nombre: ");
      out.print(alu.getPnombre() + " " + alu.getAppaterno() + " " + alu.getApmaterno());
      out.write("</li>\r\n");
      out.write("                    <li class=\"amber darken-3 black-text\">Rut: ");
      out.print(rutAlumno + "-" + alu.getDvAlumno());
      out.write(" </li>\r\n");
      out.write("                </ul>\r\n");
      out.write("                <table class=\" grey lighten-2\">\r\n");
      out.write("                    <tr class=\"amber darken-3\">\r\n");
      out.write("                        <th>Ramo</th>\r\n");
      out.write("                        <th>Fecha</th>\r\n");
      out.write("                        <th>Estado</th>\r\n");
      out.write("                        <th>Accion</th>\r\n");
      out.write("                    </tr>\r\n");
      out.write("                    ");
 if (faltas.isEmpty()) {  
      out.write("\r\n");
      out.write("                    <tr><td>No tienes registrado inasistecias para justificar<td></tr>\r\n");
      out.write("                     ");
   }
                    
      out.write("\r\n");
      out.write("                    ");
 for (Inasistencia falta : faltas) {   
      out.write("\r\n");
      out.write("                    <tr>  \r\n");
      out.write("                        ");
  if (falta.getIdEstadoi() != 0) {
      out.write("\r\n");
      out.write("                        <td>");
      out.print(falta.getIdSeccion());
      out.write("</td>\r\n");
      out.write("                        <td>");
      out.print(falta.getFecha());
      out.write("</td>\r\n");
      out.write("                        <td>");
      out.print(consultaBD.buscarEstadoInasistencia(falta.getIdEstadoi()).getNombreEstadoi());
      out.write("</td>\r\n");
      out.write("                        <td>\r\n");
      out.write("                            ");
 if (falta.getIdEstadoi() == 1) {
      out.write("\r\n");
      out.write("                            <button \r\n");
      out.write("                                class=\"btn waves-effect waves-light indigo darken-3\" \r\n");
      out.write("                                type=\"submit\" \r\n");
      out.write("                                name=\"opcion\" \r\n");
      out.write("                                value=\"j");
      out.print(falta.getIdInasistencia());
      out.write("\"> \r\n");
      out.write("                                Justificar \r\n");
      out.write("                            </button>\r\n");
      out.write("                            ");
 }
      out.write("\r\n");
      out.write("                        </td>   \r\n");
      out.write("                        ");
 } 
      out.write("\r\n");
      out.write("                    </tr>\r\n");
      out.write("                    ");
 }
      out.write("\r\n");
      out.write("                </table>    \r\n");
      out.write("                <button class=\"btn waves-effect waves-light red \" type=\"submit\" name=\"opcion\" value=\"Salir\">\r\n");
      out.write("                    Cerrar Sesion\r\n");
      out.write("                </button>\r\n");
      out.write("            </form>                \r\n");
      out.write("        </div>       \r\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-2.1.1.min.js\"></script>\r\n");
      out.write("        <script src=\"js/materialize.js\"></script>\r\n");
      out.write("        <script src=\"js/init.js\"></script>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
