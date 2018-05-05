/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import modelo.Inasistencia;

/**
 *
 * @author carlos
 */
public class InasistenciaDAO implements GeneralInasistenciasDAO{

    private ArrayList<Inasistencia> arrayInasistencia = new ArrayList<>(); 
    Conectar conn;
    
    @Override
    public Inasistencia buscar(int idInasistencia) {
        
        Inasistencia obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_inasistencia      INT NOT NULL AUTO_INCREMENT,
                fecha_inasistencia   DATE,
                id_seccion           INT NOT NULL,
                id_alumno            INT NOT NULL,
                id_estadoi           INT NOT NULL,
                id_estadoc           INT NOT NULL,
            
                this.idInasistencia = idInasistencia;
                this.fechaInasistencia = fechaInasistencia;
                this.idSeccion = idSeccion;
                this.idAlumno = idAlumno;
                this.idEstadoi = idEstadoi;
                this.idEstadoc = idEstadoc;
            
                
            */
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM inasistencia WHERE id_inasistencia  =" + idInasistencia + ";";

            ResultSet results = statement.executeQuery(query);
            
            int idIna, idSec, idAl, idEstI, idEstC ;
            Date fechI;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                idIna = results.getInt("id_inasistencia"); 
                fechI = results.getDate("fecha_inasistencia");
                idSec= results.getInt("id_seccion");
                idAl = results.getInt("id_alumno");
                idEstI = results.getInt("id_estadoi");
                idEstC = results.getInt("id_estadoc");
                
                if (idIna == idInasistencia) {                   
                    obj = new Inasistencia(idInasistencia, fechI, idSec, idAl, idEstI, idEstC);
                   break;
                }
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return obj;
    }

    @Override
    public ArrayList inasistenciaSeccion(int idSeccion) {
       //2.- un arreglo de una seccion (todas las inacistencias de una seccion x)
       Inasistencia obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM inasistencia WHERE id_seccion = "+ idSeccion +";";

            ResultSet results = statement.executeQuery(consultaSQL);
          
            /*
            id_inasistencia      INT NOT NULL AUTO_INCREMENT,
            fecha_inasistencia   DATE,
            id_seccion           INT NOT NULL,
            id_alumno            INT NOT NULL,
            id_estadoi           INT NOT NULL,
            id_estadoc           INT NOT NULL,
            
            this.idInasistencia = idInasistencia;
            this.fechaInasistencia = fechaInasistencia;
            this.idSeccion = idSeccion;
            this.idAlumno = idAlumno;
            this.idEstadoi = idEstadoi;
            this.idEstadoc = idEstadoc;
            */
            int idIna, idSec, idAlu, idEstI,idEstC;
            Date fechIna;

            arrayInasistencia.removeAll(arrayInasistencia);
            
            while (results.next()) {
                idIna = results.getInt("id_inasistencia");
                fechIna = results.getDate("fecha_inasistencia");                
                idSec= results.getInt("id_seccion");
                idAlu = results.getInt("id_alumno");
                idEstI = results.getInt("id_estadoi");
                idEstC = results.getInt("id_estadoc");

                obj = new Inasistencia(idEstC, fechIna, idSeccion, idAlu, idEstI, idEstC);
                arrayInasistencia.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayInasistencia;
    }

    @Override
    public ArrayList inasistenciaAlumno(int idAlumno) {
        //3.- todas las inacistencias de un alumno
        Inasistencia obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM inasistencia WHERE id_alumno = "+ idAlumno +";";

            ResultSet results = statement.executeQuery(consultaSQL);
          
           
            int idIna, idSec, idAlu, idEstI,idEstC;
            Date fechIna;

            arrayInasistencia.removeAll(arrayInasistencia);
            
            while (results.next()) {
                idIna = results.getInt("id_inasistencia");
                fechIna = results.getDate("fecha_inasistencia");                
                idSec= results.getInt("id_seccion");
                idAlu = results.getInt("id_alumno");
                idEstI = results.getInt("id_estadoi");
                idEstC = results.getInt("id_estadoc");

                obj = new Inasistencia(idEstC, fechIna, idSec, idAlu, idEstI, idEstC);
                arrayInasistencia.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayInasistencia;
        
        
    }

    @Override
    public ArrayList inasistenciaAlumnoSeccion(int idAlumno ,int idSeccion) {
        //4.- todas las incistencias de un alumno de una seccion x 
        Inasistencia obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM inasistencia WHERE id_seccion = "+ idSeccion +" and id_alumno"+ idAlumno +" ;";

            ResultSet results = statement.executeQuery(consultaSQL);
            
            int idIna, idSec, idAlu, idEstI,idEstC;
            Date fechIna;

            arrayInasistencia.removeAll(arrayInasistencia);
            
            while (results.next()) {
                idIna = results.getInt("id_inasistencia");
                fechIna = results.getDate("fecha_inasistencia");                
                idSec= results.getInt("id_seccion");
                idAlu = results.getInt("id_alumno");
                idEstI = results.getInt("id_estadoi");
                idEstC = results.getInt("id_estadoc");

                obj = new Inasistencia(idEstC, fechIna, idSeccion, idAlu, idEstI, idEstC);
                arrayInasistencia.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayInasistencia;
        
        
    }

    @Override
    public int contadorInasistencias(int idEstadoi, int idSeccion, int idAlumno) {
         //5.- un contador de todas las inasistencias de un alumno de una seccion x (estadoi pueda cambiar, recuerda)
        int contador = 0;
         
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM inasistencia WHERE id_estadoi = "+ idEstadoi +" and  id_seccion = "+idSeccion+ "and id_alumno = "+idAlumno +";";

            ResultSet results = statement.executeQuery(consultaSQL);
            
            while (results.next()) {
                contador++;
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return contador;
         
    }

    @Override
    public int agregar(Inasistencia inasistencia) {
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
            String agregarSQL = "INSERT INTO inasistencia(fecha_inasistencia,id_seccion,id_alumno,id_estadoi,id_estadoc)"
                    + " VALUES('"+inasistencia.getFechaInaString()+"',"+inasistencia.getIdSeccion()+","+inasistencia.getIdAlumno()+","+inasistencia.getIdEstadoi()+","+inasistencia.getIdEstadoc()+");";
            int results = statement.executeUpdate(agregarSQL);
            connection.close();
            conn.desconectar();
            return results;
        } catch (java.lang.Exception ex) {
            return 0;
        }
    }
        /*
           CREATE TABLE inasistencia (
           id_inasistencia      INT NOT NULL AUTO_INCREMENT,
           fecha_inasistencia   DATE, 
           id_seccion           INT NOT NULL,
           id_alumno            INT NOT NULL,
           id_estadoi           INT NOT NULL,
           id_estadoc           INT NOT NULL,
           PRIMARY KEY(id_inasistencia)
       );

           */
    @Override
    public Inasistencia buscarIdCorreo(int idCorreo) {
         Inasistencia obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_inasistencia      INT NOT NULL AUTO_INCREMENT,
                fecha_inasistencia   DATE,
                id_seccion           INT NOT NULL,
                id_alumno            INT NOT NULL,
                id_estadoi           INT NOT NULL,
                id_estadoc           INT NOT NULL,
            
                this.idInasistencia = idInasistencia;
                this.fechaInasistencia = fechaInasistencia;
                this.idSeccion = idSeccion;
                this.idAlumno = idAlumno;
                this.idEstadoi = idEstadoi;
                this.idEstadoc = idEstadoc;
            */
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM inasistencia WHERE id_estadoc="+idCorreo+";";

            ResultSet results = statement.executeQuery(query);
            
            int idIna, idSec, idAl, idEstI, idEstC ;
            Date fechI;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                idIna = results.getInt("id_inasistencia"); 
                fechI = results.getDate("fecha_inasistencia");
                idSec= results.getInt("id_seccion");
                idAl = results.getInt("id_alumno");
                idEstI = results.getInt("id_estadoi");
                idEstC = results.getInt("id_estadoc");
                
                if (idEstC == idCorreo) {                   
                    obj = new Inasistencia(idIna, fechI, idSec, idAl, idEstI, idEstC);
                   break;
                }
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return obj;
    }

    @Override
    public int actualizar(Inasistencia inasistencia) {
        int results = 0;

        try {

            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();

            String agregarSQL = "UPDATE inasistencia SET  " + 
                    ", fecha_inasistencia='" +inasistencia.getFechaInaString()+ 
                    "', id_seccion="+ inasistencia.getIdSeccion()  +
                    ", id_alumno=" + inasistencia.getIdAlumno()+
                    ", id_estadoi=" + inasistencia.getIdEstadoi() +
                    ", id_estadoc="+ inasistencia.getIdEstadoc()+
                    " where id_inasistencia = " + inasistencia.getIdInasistencia() + ";"; 
            results = statement.executeUpdate(agregarSQL);
            connection.close();
            conn.desconectar();

        } //catching excepcion
        catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }

        return results;
    }

    @Override
    public int actualizarCorreoSecretaria(int idIna,int estadoC) {
        int results = 0;

        try {

            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();

            String agregarSQL = "UPDATE inasistencia SET "
                    + "id_estadoc="+estadoC+" where id_inasistencia ="+idIna+";"; 
            results = statement.executeUpdate(agregarSQL);
            connection.close();
            conn.desconectar();

        } //catching excepcion
        catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }

        return results;
    }
    
}
