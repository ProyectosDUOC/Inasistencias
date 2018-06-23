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
import modelo.Subdirector;

/**
 *
 * @author benja
 */
public class SubdirectorDAO implements GeneralSubdirectorDAO{ 

    Conectar conn;
    
    @Override
    public Subdirector buscarDatos(String rut) {
        Subdirector obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM subdirector WHERE rut_subdirector ='" + rut + "';";

            ResultSet results = statement.executeQuery(query);

            int idDir, activo;
            String rutDir, pNom, sNom, aPat, aMat, mail;

            while (results.next()) {
                idDir = results.getInt("id_subdirector");
                rutDir = results.getString("rut_subdirector");
                pNom = results.getString("pnombre");
                sNom = results.getString("snombre");
                aPat = results.getString("appaterno");
                aMat = results.getString("apmaterno");
                mail = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (rutDir.equals(rut)) {                   
                    obj = new Subdirector(idDir, rutDir, pNom, sNom, aPat, aMat, mail, activo);                   
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

       public Subdirector buscarDatosSolo() {
        Subdirector obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM subdirector limit 1;";

            ResultSet results = statement.executeQuery(query);

            int idDir, activo;
            String rutDir, pNom, sNom, aPat, aMat, mail;

            while (results.next()) {
                idDir = results.getInt("id_subdirector");
                rutDir = results.getString("rut_subdirector");
                pNom = results.getString("pnombre");
                sNom = results.getString("snombre");
                aPat = results.getString("appaterno");
                aMat = results.getString("apmaterno");
                mail = results.getString("email");              
                activo = results.getInt("activo");               
                
                                   
                    obj = new Subdirector(idDir, rutDir, pNom, sNom, aPat, aMat, mail, activo);                   
                   break;
                
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return obj;
    }
    
    @Override
    public Subdirector buscarDatos(int idDirector) {
        Subdirector obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
      
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM subdirector WHERE id_subdirector=" + idDirector + ";";

            ResultSet results = statement.executeQuery(query);
            
            int idDir, activo;
            String rutDir, pNom, sNom, aPat, aMat, mail;

            while (results.next()) {
                idDir = results.getInt("id_subdirector");
                rutDir = results.getString("rut_subdirector");
                pNom = results.getString("pnombre");
                sNom = results.getString("snombre");
                aPat = results.getString("appaterno");
                aMat = results.getString("apmaterno");
                mail = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (idDir == idDirector) {                   
                    obj = new Subdirector(idDir, rutDir, pNom, sNom, aPat, aMat, mail, activo);
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
    public int agregar(Subdirector director) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Subdirector sub) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
