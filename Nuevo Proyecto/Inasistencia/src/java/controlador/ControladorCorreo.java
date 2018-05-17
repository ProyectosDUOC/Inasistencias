/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    public int enviar(String correoDestinatario, String mensaje, String asunto) {  
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
            message.setSubject(Subject);
            message.setContent(Mensaje,"text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException e) {
            return -1;
        }
        return 1;
    } 
    
    
    //Predeterminado 
    public String mensajeAprobadoAlumno(Alumno alum, Inasistencia ina, Justificacion justi ){
        String mensaje;
        
        mensaje = "<strong>Estimado Alumno  "+alum.getPnombre()+" "+alum.getAppaterno()+" </strong> <br><br>"+
                "La justificacion de inasistencia, enviada el dia "+parseHora.format(justi.getFechaJustificacion())+
                " ha sido aprobada por el Director de carrera <br><br>";
        return mensaje;
    }
     public String mensajeAprobadoDocente(Docente doce,Alumno alum, Inasistencia ina, Justificacion justi ){
        String mensaje;
        
        mensaje = "<strong> Estimado Profesor </strong> "+doce.getPnombre()+" "+doce.getAppaterno()+"<br><br>"+
                "La justificacion de inasistencia del alumno <strong>"+alum.getPnombre()+" "+alum.getAppaterno()+"    rut "+alum.getRutAlumno()+
                "</strong> <br><br>  ha sido aprobada por el Director de carrera <br>"+
                " <br><br>  <strong>Fecha Inasistencia </strong> : "+parseHora.format(ina.getFechaInasistencia());
        return mensaje;
    }
    public String mensajeDirector(Director dire,Alumno alum){
        String mensaje;
        
        mensaje = "<strong>Estimado Director </strong> "+dire.getPnombre()+" "+dire.getAppaterno()+" <br><br><br>"+
                "Se ha solicitado una  justificacion de inasistencia del alumno <strong>"+alum.getPnombre()+" "+alum.getAppaterno()+"   rut: "+alum.getRutAlumno()+
                "</strong> <br><br><br> <strong>Por favor justificar en <a href='http://www.cittsb.cl:8080/Inasistencia/'>www.inasistenciaduoc.cl</a></strong>";
        return mensaje;
        
        
        
    }
}
