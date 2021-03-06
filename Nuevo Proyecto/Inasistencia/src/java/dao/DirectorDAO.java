/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Director;
import conexion.Conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author carlos
 */
public class DirectorDAO implements GeneralDirectorDAO{

    private ArrayList<Director> arrayDirectores = new ArrayList<>();    
    Conectar conn;
    
    @Override
    public ArrayList mostrarDatos() {
        Director obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM director ;";

            ResultSet results = statement.executeQuery(consultaSQL);

            /*
                id_director    INT NOT NULL AUTO_INCREMENT,
                rut_director   VARCHAR(30) NOT NULL,
                pnombre        VARCHAR(30),
                snombre        VARCHAR(30),
                appaterno      VARCHAR(30),
                apmaterno      VARCHAR(30),
                email          VARCHAR(50),
                activo         INT,
            */
            
            int id, activo;
            String rut, pnom, snom, appat, apmat, mail;

            arrayDirectores.removeAll(arrayDirectores);
            while (results.next()) {
                id = results.getInt("id_director");
                rut = results.getString("rut_director");
                pnom = results.getString("pnombre");
                snom = results.getString("snombre");
                appat = results.getString("appaterno");
                apmat = results.getString("apmaterno");
                mail = results.getString("email");               
                activo = results.getInt("activo");
                
                obj = new Director(activo, rut, pnom, snom, appat, apmat, mail, activo);
                arrayDirectores.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayDirectores;
    }
    
    

    @Override
    public Director buscarDatos(String rut) {
        
        Director obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM director WHERE rut_director ='" + rut + "';";

            ResultSet results = statement.executeQuery(query);

            int idDir, activo;
            String rutDir, pNom, sNom, aPat, aMat, mail;

            while (results.next()) {
                idDir = results.getInt("id_director");
                rutDir = results.getString("rut_director");
                pNom = results.getString("pnombre");
                sNom = results.getString("snombre");
                aPat = results.getString("appaterno");
                aMat = results.getString("apmaterno");
                mail = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (rutDir.equals(rut)) {                   
                    obj = new Director(idDir, rutDir, pNom, sNom, aPat, aMat, mail, activo);                   
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
    public Director buscarDatos(int idDirector) {
        
        Director obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
      
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM director WHERE id_director=" + idDirector + ";";

            ResultSet results = statement.executeQuery(query);
            
            int idDir, activo;
            String rutDir, pNom, sNom, aPat, aMat, mail;

            while (results.next()) {
                idDir = results.getInt("id_director");
                rutDir = results.getString("rut_director");
                pNom = results.getString("pnombre");
                sNom = results.getString("snombre");
                aPat = results.getString("appaterno");
                aMat = results.getString("apmaterno");
                mail = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (idDir == idDirector) {                   
                    obj = new Director(idDir, rutDir, pNom, sNom, aPat, aMat, mail, activo);
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
    public Director buscarDatosCorreo(String email) {  
        Director obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM director WHERE email='" + email + "';";

            ResultSet results = statement.executeQuery(query);
            
             int idDir, activo;
            String rutDir, pNom, sNom, aPat, aMat, mail;

            while (results.next()) {
                idDir = results.getInt("id_director");
                rutDir = results.getString("rut_director");
                pNom = results.getString("pnombre");
                sNom = results.getString("snombre");
                aPat = results.getString("appaterno");
                aMat = results.getString("apmaterno");
                mail = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (mail.equals(email)) {                   
                    obj = new Director(idDir, rutDir, pNom, sNom, aPat, aMat, mail, activo);
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
    public int agregar(Director director) {
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
          
            String agregarSQL = "INSERT INTO director (rut_director, pnombre, snombre, appaterno, apmaterno, email, activo)"+
                                " VALUES('"+director.getRutDirector()+"','"+director.getPnombre()+"','"+director.getSnombre()+"','"+director.getAppaterno()+"' , '"+director.getApmaterno()+"','"+director.getEmail()+"',"+director.getActivo()+");";
            
            int results = statement.executeUpdate(agregarSQL);
            connection.close();
            conn.desconectar();
            return results;
        } catch (java.lang.Exception ex) {
            return 0;
        }
    }

    @Override
    public int eliminar(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Director cordinador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList mostrarDatosEspecificos(){
        Director obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = " SELECT * FROM director dire left JOIN control_usuario con ON dire.rut_director=con.rut_usuario WHERE con.usuario is null;";

            ResultSet results = statement.executeQuery(consultaSQL);

            /*
                id_director    INT NOT NULL AUTO_INCREMENT,
                rut_director   VARCHAR(30) NOT NULL,
                pnombre        VARCHAR(30),
                snombre        VARCHAR(30),
                appaterno      VARCHAR(30),
                apmaterno      VARCHAR(30),
                email          VARCHAR(50),
                activo         INT,
            */
            
            int id, activo;
            String rut, pnom, snom, appat, apmat, mail;

            arrayDirectores.removeAll(arrayDirectores);
            while (results.next()) {
                id = results.getInt("id_director");
                rut = results.getString("rut_director");
                pnom = results.getString("pnombre");
                snom = results.getString("snombre");
                appat = results.getString("appaterno");
                apmat = results.getString("apmaterno");
                mail = results.getString("email");               
                activo = results.getInt("activo");
                
                obj = new Director(activo, rut, pnom, snom, appat, apmat, mail, activo);
                arrayDirectores.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayDirectores;
    }
}
