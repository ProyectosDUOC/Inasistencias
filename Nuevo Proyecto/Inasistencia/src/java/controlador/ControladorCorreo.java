/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.JustificacionDAO;
import dao.RamoDAO;
import dao.SeccionDAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.Alumno;
import modelo.Director;
import modelo.Docente;
import modelo.Inasistencia;
import modelo.Justificacion;
import modelo.Seccion;
import modelo.Secretaria;
import modelo.Subdirector;

/**
 *
 * @author benja
 */
public class ControladorCorreo {

    String Username = "controlinasistencia@gmail.com";
    String PassWord = "abcd14abcd";
    String host = "smtp.gmail.com";
    String port = "587";

    DateFormat parseHora = new SimpleDateFormat("dd/MM/yyyy");

    public int enviar(String correoDestinatario, String cc, String mensaje, String asunto, int estado) {
        String Subject = asunto;
        String Mensaje = mensaje;
        String To = correoDestinatario; //correo destinatario      

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session;

        session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Username, PassWord);
            }
        }
        );

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To));  
            if (estado==1) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            }  
            
            
            message.setSubject(Subject);
            message.setContent(Mensaje, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException e) {
            return -1;
        }
        return 1;
    }

    //Predeterminado 
    public String mensajeAprobadoAlumno(Alumno alum, Inasistencia ina, Justificacion justi) {
        String mensaje;
        String fecha2 = "", nombreAsignatura = "", seccion = "";
        Seccion sec = new Seccion();

        if (ina.getFechaInasistencia2() != null) {
            fecha2 = " hasta " + parseHora.format(ina.getFechaInasistencia2());
        }
        sec = (new SeccionDAO()).buscar(ina.getIdSeccion());
        seccion = sec.getCodSeccion();
        nombreAsignatura = (new RamoDAO()).buscar(sec.getCodRamo()).getNombreRamo();

        mensaje = "<strong>Estimado Alumno  " + alum.getPnombre() + " " + alum.getAppaterno() + ":</strong> <br><br>"
                + "Junto a saludar, informo a usted sobre la resolución de la justificación de inasitencia "
                + " realizada el dia " + parseHora.format(justi.getFechaJustificacion())
                + " <br><br><strong>Detalle Justificacion</strong><br><br>"
                + "<strong>Fecha Inasistencia </strong> : " + parseHora.format(ina.getFechaInasistencia()) + fecha2
                + "<br><br><strong>Nombre Asignatura</strong> : " + nombreAsignatura
                + "<br><br><strong>Sección</strong> : " + seccion
                + "<br><br>De acuerdo a lo anterior, según la indicacion del director de carrera la solicitud se encuentra <strong>Aprobada</strong>."
                + "<br><br>Saludos Cordiales<br><strong>Sede San Bernardo</strong><br><strong>Duoc UC</strong>";
        return mensaje;
    }

    public String mensajeAprobadoDocente(Docente doce, Alumno alum, Inasistencia ina, Justificacion justi) {
        String mensaje;
        String fecha2 = "", nombreAsignatura = "", seccion = "";
        String motivo =  "", glosa="" ;
        if (ina.getFechaInasistencia2() != null) {
            fecha2 = " hasta " + parseHora.format(ina.getFechaInasistencia2());
        }
        nombreAsignatura = (new RamoDAO()).buscar((new SeccionDAO()).buscar(ina.getIdSeccion()).getCodRamo()).getNombreRamo();
        seccion = (new SeccionDAO()).buscar(ina.getIdSeccion()).getCodSeccion();
        mensaje = "<strong> Estimado Docente </strong> " + doce.getPnombre() + " " + doce.getAppaterno() + "<br><br>"
                + "De acuerdo a la justificacion recibida por el alumno(a): "+ alum.getPnombre() + " " + alum.getAppaterno() + " RUN " + alum.getRutAlumno()
                + ", informamos que se encuentra <strong>autorizada</strong> por el director de carrera<br><br>"
                + " <br><br><strong>Detalle Justificacion</strong><br><br>"
                + " <br><strong>Fecha Inasistencia </strong> : " + parseHora.format(ina.getFechaInasistencia()) + fecha2
                + " <br><strong>Nombre Asignatura </strong> : " + nombreAsignatura
                + " <br><strong>Seccion </strong> : " + seccion +"<br><br>";
        return mensaje;
    }
      public String mensajeAprobadoSubDocente(Docente doce, Alumno alum, Inasistencia ina, Justificacion justi) {
        String mensaje;
        String fecha2 = "", nombreAsignatura = "", seccion = "";
        String motivo =  "", glosa="" ;
        if (ina.getFechaInasistencia2() != null) {
            fecha2 = " hasta " + parseHora.format(ina.getFechaInasistencia2());
        }
        nombreAsignatura = (new RamoDAO()).buscar((new SeccionDAO()).buscar(ina.getIdSeccion()).getCodRamo()).getNombreRamo();
        seccion = (new SeccionDAO()).buscar(ina.getIdSeccion()).getCodSeccion();
        mensaje = "<strong> Estimado Docente </strong> " + doce.getPnombre() + " " + doce.getAppaterno() + "<br><br>"
                + "De acuerdo a la justificacion recibida por el alumno(a): "+ alum.getPnombre() + " " + alum.getAppaterno() + " RUN " + alum.getRutAlumno()
                + ", informamos que se encuentra <strong>autorizada</strong> por el Subdirector Academico<br><br>"
                + " <br><br><strong>Detalle Justificacion</strong><br><br>"
                + " <br><strong>Fecha Inasistencia </strong> : " + parseHora.format(ina.getFechaInasistencia()) + fecha2
                + " <br><strong>Nombre Asignatura </strong> : " + nombreAsignatura
                + " <br><strong>Seccion </strong> : " + seccion +"<br><br>";
        return mensaje;
    }


    public String mensajeDirector(Director dire, Alumno alum) {
        String mensaje;

        mensaje = "<strong>Estimado Director </strong> " + dire.getPnombre() + " " + dire.getAppaterno() + " <br><br><br>"
                + "Se ha solicitado una  justificacion de inasistencia del alumno(a) <strong>" + alum.getPnombre() + " " + alum.getAppaterno() + "   rut: " + alum.getRutAlumno()
                + "</strong> <br><br><br> <strong>Por favor justificar en <a href='http://www.cittsb.cl:8080/Inasistencia/'>Enlace</a></strong>";
        return mensaje;

    }
    
    public String mensajeSubdirector(Subdirector dire, Alumno alum) {
        String mensaje;

        mensaje = "<strong>Estimado Subdirector </strong> " + dire.getPnombre() + " " + dire.getAppaterno() + " <br><br><br>"
                + "Se ha solicitado una  justificacion de inasistencia del alumno <strong>" + alum.getPnombre() + " " + alum.getAppaterno() + "   rut: " + alum.getRutAlumno()
                + "</strong> <br><br><br> <strong>Por favor  en <a href='http://www.cittsb.cl:8080/Inasistencia/'>Enlace</a></strong>";
        return mensaje;

    }
     public String mensajeConfirmacionEnvio(Secretaria secre, Alumno alum) {
        String mensaje;

        mensaje = "<strong>Estimado Alumno </strong> " + alum.getPnombre() + " " + alum.getAppaterno() + " <br><br><br>"
                + "Se enviado su solicitud de justificacion de inasistencia<br>";
        return mensaje;

    }
    public String mensajeRechazoDirector(Alumno alum, Inasistencia ina, Justificacion justi) {
        String mensaje;
        String fecha2 = "", nombreAsignatura = "", seccion = "";
        String motivo =  "", glosa="" ;
        if (ina.getFechaInasistencia2() != null) {
            fecha2 = " hasta " + parseHora.format(ina.getFechaInasistencia2());
        }
        nombreAsignatura = (new RamoDAO()).buscar((new SeccionDAO()).buscar(ina.getIdSeccion()).getCodRamo()).getNombreRamo();
        seccion = (new SeccionDAO()).buscar(ina.getIdSeccion()).getCodSeccion();
        
        mensaje = "<strong>Estimado Alumno  " + alum.getPnombre() + " " + alum.getAppaterno() + ":</strong> <br><br>"
                + "Junto a saludar, informo a usted sobre la resolución de la justificación de inasitencia "
                + " realizada el dia " + parseHora.format(justi.getFechaJustificacion())
                + " <br><br><strong>Detalle Justificacion</strong><br><br>"
                + "<strong>Fecha Inasistencia </strong> : " + parseHora.format(ina.getFechaInasistencia()) + fecha2
                + "<br><br><strong>Nombre Asignatura</strong> : " + nombreAsignatura
                + "<br><br><strong>Sección</strong> : " + seccion
                + "<br><br>De acuerdo a lo anterior, según la indicacion del director de carrera la solicitud se encuentra <strong>Rechazada</strong>."
                + "<br><br>Saludos Cordiales<br><strong>Sede San Bernardo</strong><br><strong>Duoc UC</strong>";
        return mensaje;
        }
       public String mensajeRechazoSubDirector(Alumno alum, Inasistencia ina, Justificacion justi) {
        String mensaje;
        String fecha2 = "", nombreAsignatura = "", seccion = "";
        String motivo =  "", glosa="" ;
        if (ina.getFechaInasistencia2() != null) {
            fecha2 = " hasta " + parseHora.format(ina.getFechaInasistencia2());
        }
        nombreAsignatura = (new RamoDAO()).buscar((new SeccionDAO()).buscar(ina.getIdSeccion()).getCodRamo()).getNombreRamo();
        seccion = (new SeccionDAO()).buscar(ina.getIdSeccion()).getCodSeccion();
        
        mensaje = "<strong>Estimado Alumno  " + alum.getPnombre() + " " + alum.getAppaterno() + ":</strong> <br><br>"
                + "Junto a saludar, informo a usted sobre la resolución de la justificación de inasitencia "
                + " realizada el dia " + parseHora.format(justi.getFechaJustificacion())
                + " <br><br><strong>Detalle Justificacion</strong><br><br>"
                + "<strong>Fecha Inasistencia </strong> : " + parseHora.format(ina.getFechaInasistencia()) + fecha2
                + "<br><br><strong>Nombre Asignatura</strong> : " + nombreAsignatura
                + "<br><br><strong>Sección</strong> : " + seccion
                + "<br><br>De acuerdo a lo anterior, según la indicacion del Subdirector Academico la solicitud se encuentra <strong>Rechazada</strong>."
                + "<br><br>Saludos Cordiales<br><strong>Sede San Bernardo</strong><br><strong>Duoc UC</strong>";
        return mensaje;
        }
        public String mensajeAprobadaSubDirector(Alumno alum, Inasistencia ina, Justificacion justi) {
        String mensaje;
        String fecha2 = "", nombreAsignatura = "", seccion = "";
        String motivo =  "", glosa="" ;
        if (ina.getFechaInasistencia2() != null) {
            fecha2 = " hasta " + parseHora.format(ina.getFechaInasistencia2());
        }
        nombreAsignatura = (new RamoDAO()).buscar((new SeccionDAO()).buscar(ina.getIdSeccion()).getCodRamo()).getNombreRamo();
        seccion = (new SeccionDAO()).buscar(ina.getIdSeccion()).getCodSeccion();
        
        mensaje = "<strong>Estimado Alumno  " + alum.getPnombre() + " " + alum.getAppaterno() + ":</strong> <br><br>"
                + "Junto a saludar, informo a usted sobre la resolución de la justificación de inasitencia "
                + " realizada el dia " + parseHora.format(justi.getFechaJustificacion())
                + " <br><br><strong>Detalle Justificacion</strong><br><br>"
                + "<strong>Fecha Inasistencia </strong> : " + parseHora.format(ina.getFechaInasistencia()) + fecha2
                + "<br><br><strong>Nombre Asignatura</strong> : " + nombreAsignatura
                + "<br><br><strong>Sección</strong> : " + seccion
                + "<br><br>De acuerdo a lo anterior, según la indicacion del Subdirector Academico la solicitud se encuentra <strong>Aprobada</strong>."
                + "<br><br>Saludos Cordiales<br><strong>Sede San Bernardo</strong><br><strong>Duoc UC</strong>";
        return mensaje;
        }
    }

