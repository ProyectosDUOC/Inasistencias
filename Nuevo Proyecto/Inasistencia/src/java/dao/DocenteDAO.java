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
import modelo.Docente;

/**
 *
 * @author carlos
 */
public class DocenteDAO implements GeneralDocenteDAO{

     Conectar conn;
    
    @Override
    public ArrayList mostrarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Docente buscarDatos(String rut) {
        Docente obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
         
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM docente WHERE rut_docente='" + rut + "';";

            ResultSet results = statement.executeQuery(query);
            
            int id, activo;
            String rut1, pnombre, snombre, appaterno, apmaterno, email;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                id = results.getInt("id_docente");
                rut1 = results.getString("rut_docente");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (rut1.equals(rut)) {                   
                    obj = new Docente(id, rut1,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public Docente buscarDatos(int idDocente) {
        
        Docente obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
           
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM docente WHERE id_docente=" + idDocente + ";";

            ResultSet results = statement.executeQuery(query);
            
            int id, activo;
            String rut1, pnombre, snombre, appaterno, apmaterno, email;
            
            while (results.next()) {
                id = results.getInt("id_docente");
                rut1 = results.getString("rut_docente");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (id == idDocente) {                   
                    obj = new Docente(id, rut1,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public Docente buscarDatosCorreo(String email) {
        Docente obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
           
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM docente WHERE email ='" + email + "';";

            ResultSet results = statement.executeQuery(query);
            
            int id, activo;
            String rut1, pnombre, snombre, appaterno, apmaterno, mail;
            
            while (results.next()) {
                id = results.getInt("id_docente");
                rut1 = results.getString("rut_docente");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                mail = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (mail.equals(mail)) {                   
                    obj = new Docente(id, rut1,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public int agregar(Docente docente) {
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
            String agregarSQL = "INSERT INTO administrador (rut_docente,pnombre,snombre,appaterno,apmaterno,email,activo)"+
                                " VALUES('"+docente.getRutDocente()+"','"+docente.getPnombre()+"','"+docente.getSnombre()+"','"+docente.getAppaterno()+"','"+docente.getApmaterno()+"','"+docente.getEmail()+"',"+docente.getActivo()+");";
            
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
    public int actualizar(Docente cordinador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
