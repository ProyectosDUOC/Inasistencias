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
import modelo.Seccion;

/**
 *
 * @author carlos
 */
public class SeccionDAO implements GeneralSeccionDAO{

    private ArrayList<Seccion> arraySeccion = new ArrayList<>(); 
    Conectar conn;
    
    @Override
    public Seccion buscar(int idSeccion) {
        
        Seccion obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_seccion    INT NOT NULL AUTO_INCREMENT,
                cod_seccion   VARCHAR(30),
                cod_ramo      VARCHAR(30),
                id_docente    INT NOT NULL,
                semestre      INT,
                anio          INT,
            
            this.idSeccion = idSeccion;
            this.codSeccion = codSeccion;
            this.idDocente = idDocente;
            this.semestre = semestre;
            this.anio = anio;
            */
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM seccion WHERE id_seccion="+idSeccion+";";

            ResultSet results = statement.executeQuery(query);
            
            int idSecc, idDoc, semes, anyo;
            String codSecc, codRamo;
            

            while (results.next()) {
                idSecc = results.getInt("id_seccion"); 
                codSecc = results.getString("cod_seccion");
                idDoc = results.getInt("id_docente");
                semes= results.getInt("semestre");
                anyo= results.getInt("anio");
                codRamo = results.getString("cod_ramo");
                                
                if (idSecc == idSeccion) {                   
                    obj = new Seccion(idSeccion, codSecc, codRamo, idDoc, semes, anyo);
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
    public ArrayList seccionesDocente(int idDocente) {
        Seccion obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM seccion WHERE id_docente = "+ idDocente +";";

            ResultSet results = statement.executeQuery(consultaSQL);
          
            int idSec,idDoce, semes, anyo;
            String codSec, codRamo;

            arraySeccion.removeAll(arraySeccion);
            
            while (results.next()) {
                idSec = results.getInt("id_seccion");
                codSec = results.getString("cod_seccion");                
                idDoce = results.getInt("id_docente");
                semes = results.getInt("semestre");
                codRamo = results.getString("cod_ramo");
                anyo = results.getInt("anio");
                obj = new Seccion(idSec, codSec, codRamo, idDocente, semes, anyo);
                arraySeccion.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arraySeccion;
    }

    public ArrayList mostrarDocente(int idDocente, int anio, int semestre) {
        Seccion obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM seccion WHERE id_docente = "+ idDocente +" and anio="+anio+" and semestre="+semestre+";";

            ResultSet results = statement.executeQuery(consultaSQL);
          
            int idSec,idDoce, semes, anyo;
            String codSec, codRamo;

            arraySeccion.removeAll(arraySeccion);
            
            while (results.next()) {
                idSec = results.getInt("id_seccion");
                codSec = results.getString("cod_seccion");                
                idDoce = results.getInt("id_docente");                
                codRamo = results.getString("cod_ramo");
                semes = results.getInt("semestre");
                anyo = results.getInt("anio");
                obj = new Seccion(idSec, codSec, codRamo, idDocente, semes, anyo);
                arraySeccion.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arraySeccion;
    }
    
    @Override
    public ArrayList seccionesAnyoSemestre(int semestre, int anio) {
        Seccion obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM seccion WHERE semestre = "+ semestre + " and  anio ="+ anio +";";

            ResultSet results = statement.executeQuery(consultaSQL);
          
            int idSec,idDoce, semes, anyo;
            String codSec, codRamo;

            arraySeccion.removeAll(arraySeccion);
            
            while (results.next()) {
                idSec = results.getInt("id_seccion");
                codSec = results.getString("cod_seccion");                
                idDoce = results.getInt("id_docente");
                semes = results.getInt("semestre");
                codRamo = results.getString("cod_ramo");
                anyo = results.getInt("anio");
                obj = new Seccion(idSec, codSec, codRamo, idDoce, semes, anyo);
                arraySeccion.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arraySeccion;
    }

    @Override
    public ArrayList seccionesAnuales(int anio) {
        Seccion obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM seccion WHERE anio = "+ anio +";";

            ResultSet results = statement.executeQuery(consultaSQL);
          
            int idSec,idDoce, semes, anyo;
            String codSec, codRamo;

            arraySeccion.removeAll(arraySeccion);
            
            while (results.next()) {
                idSec = results.getInt("id_seccion");
                codSec = results.getString("cod_seccion");                
                idDoce = results.getInt("id_docente");
                semes = results.getInt("semestre");
                anyo = results.getInt("anio");                
                codRamo = results.getString("cod_ramo");
                
                obj = new Seccion(idSec, codSec, codRamo, idDoce, semes, anio);
                arraySeccion.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arraySeccion;
    }

    @Override
    public Seccion buscarSemestreAnio(int idSeccion, int semestre, int anio) {
          Seccion obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_seccion    INT NOT NULL AUTO_INCREMENT,
                cod_seccion   VARCHAR(30),
                cod_ramo      VARCHAR(30),
                id_docente    INT NOT NULL,
                semestre      INT,
                anio          INT,
            
            this.idSeccion = idSeccion;
            this.codSeccion = codSeccion;
            this.idDocente = idDocente;
            this.semestre = semestre;
            this.anio = anio;
            */
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM seccion WHERE id_seccion=" + idSeccion + " and anio="+anio+" and semestre="+semestre+";";

            ResultSet results = statement.executeQuery(query);
            
            int idSecc, idDoc, semes, anyo;
            String codSecc, codRamo;
            

            while (results.next()) {
                idSecc = results.getInt("id_seccion"); 
                codSecc = results.getString("cod_seccion");
                idDoc = results.getInt("id_docente");
                semes= results.getInt("semestre");
                anyo= results.getInt("anio");
                codRamo = results.getString("cod_ramo");
                                
                if (idSecc == idSeccion) {                   
                    obj = new Seccion(idSeccion, codSecc, codRamo, idDoc, semes, anyo);
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
    public ArrayList mostrarAlumno(int idAlumno, int year, int semestre) {
        Seccion obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM seccion as sec JOIN detalle_seccion as deta ON sec.id_seccion=deta.id_seccion  WHERE sec.anio="+year+" and sec.semestre="+semestre+" and deta.id_alumno="+idAlumno+";";

            ResultSet results = statement.executeQuery(consultaSQL);
          
            int idSec,idDoce, semes, anyo;
            String codSec, codRamo;
            arraySeccion.removeAll(arraySeccion);
            
            while (results.next()) {
                idSec = results.getInt("id_seccion");
                codSec = results.getString("cod_seccion");                
                idDoce = results.getInt("id_docente");
                semes = results.getInt("semestre");
                anyo = results.getInt("anio");
                codRamo = results.getString("cod_ramo");
                
                obj = new Seccion(idSec, codSec, codRamo, idDoce, semestre, anyo);
                arraySeccion.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arraySeccion;
    }

    public Seccion buscarCod(String code) {
          Seccion obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM seccion WHERE cod_seccion='" + code + "';";

            ResultSet results = statement.executeQuery(query);
            
            int idSecc, idDoc, semes, anyo;
            String codSecc, codRamo;
            

            while (results.next()) {
                idSecc = results.getInt("id_seccion"); 
                codSecc = results.getString("cod_seccion");
                idDoc = results.getInt("id_docente");
                semes= results.getInt("semestre");
                anyo= results.getInt("anio");
                codRamo = results.getString("cod_ramo");
                                
                if (codSecc.equals(code)) {                   
                    obj = new Seccion(idSecc, codSecc, codRamo, idDoc, semes, anyo);
                   break;
                }
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return obj; }
    
}
