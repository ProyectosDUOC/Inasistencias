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
import modelo.TelefonoAlumno;

/**
 *
 * @author benja
 */
public class TelefonoDAO implements GeneralTelefonoDAO {

    Conectar conn;
    
    @Override
    public ArrayList mostrarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public TelefonoAlumno buscarDatos(int id) {
        TelefonoAlumno obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM telefono_alumno WHERE id_tel="+id+";";

            ResultSet results = statement.executeQuery(query);
            
            int id1, idA, telefono;
            while (results.next()) {
                id1 = results.getInt("id_tel");
                idA = results.getInt("id_alumno");                 
                telefono = results.getInt("telefono"); 
                
                if (id1 == id) {                   
                    obj = new TelefonoAlumno(id1, idA, telefono);
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
    public int agregar(TelefonoAlumno tel) {
         try {
             
            /*
               CREATE TABLE telefono_alumno(
                    id_tel              INT NOT NULL AUTO_INCREMENT,
                    id_alumno           INT,
                    telefono            INT,
                    PRIMARY KEY(id_tel)
                );
            
            */
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
            String agregarSQL = "INSERT INTO telefono_alumno (id_alumno,telefono)"
                    + " VALUES("+tel.getIdAlumno()+","+tel.getTelefono()+");";
            int results = statement.executeUpdate(agregarSQL);
            connection.close();
            conn.desconectar();
            return results;
        } catch (java.lang.Exception ex) {
            return 0;
        }    
    }

    @Override
    public int actualizar(int id, int num) {
         int results = 0;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();

            /*
               CREATE TABLE telefono_alumno(
                    id_tel              INT NOT NULL AUTO_INCREMENT,
                    id_alumno           INT,
                    telefono            INT,
                    PRIMARY KEY(id_tel)
                );
            
            */
            String agregarSQL = "UPDATE telefono_alumno SET  " + 
                    " telefono="+num+
                    " where id_tel="+id+";"; 
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
    public TelefonoAlumno buscarDatosAlum(int id) {
        TelefonoAlumno obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM telefono_alumno WHERE id_alumno="+id+";";

            ResultSet results = statement.executeQuery(query);
            
            int id1, idA, telefono;
            

            while (results.next()) {
                id1 = results.getInt("id_tel");
                idA = results.getInt("id_alumno");                 
                telefono = results.getInt("telefono"); 
                
                if (idA == id) {                   
                    obj = new TelefonoAlumno(id1, idA, telefono);
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
    
}
