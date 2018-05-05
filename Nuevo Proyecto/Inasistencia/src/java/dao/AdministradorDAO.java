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
import modelo.Administrador;


/**
 *
 * @author benja
 */
public class AdministradorDAO implements GeneralAdminDAO{
    
    Conectar conn;
    
    @Override
    public ArrayList mostrarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Administrador buscarDatos(String rut) {
        Administrador obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_administrador    INT NOT NULL AUTO_INCREMENT,
                rut_administrador   VARCHAR(30) NOT NULL,
                pnombre             VARCHAR(30),
                snombre             VARCHAR(30),
                appaterno           VARCHAR(30),
                apmaterno           VARCHAR(30),
                email               VARCHAR(50) NOT NULL,
                activo              INT,
            */
                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM administrador WHERE rut_administrador='" + rut + "';";

            ResultSet results = statement.executeQuery(query);
            
            int id, activo;
            String rut1, pnombre, snombre, appaterno, apmaterno, email;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                id = results.getInt("id_administrador");
                rut1 = results.getString("rut_administrador");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (rut1.equals(rut)) {                   
                    obj = new Administrador(id, rut1,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public Administrador buscarDatos(int id) {
         Administrador obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_administrador    INT NOT NULL AUTO_INCREMENT,
                rut_administrador   VARCHAR(30) NOT NULL,
                pnombre             VARCHAR(30),
                snombre             VARCHAR(30),
                appaterno           VARCHAR(30),
                apmaterno           VARCHAR(30),
                email               VARCHAR(50) NOT NULL,
                activo              INT,
            */
                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM administrador WHERE id_administrador=" + id + ";";

            ResultSet results = statement.executeQuery(query);
            
            int id1, activo;
            String rut, pnombre, snombre, appaterno, apmaterno, email;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                id1 = results.getInt("id_administrador");
                rut = results.getString("rut_administrador");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (id == id1) {                   
                    obj = new Administrador(id, rut,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public Administrador buscarDatosCorreo(String correo) {
         Administrador obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM administrador WHERE email='" + correo + "';";

            ResultSet results = statement.executeQuery(query);
            
            int id, activo;
            String rut1, pnombre, snombre, appaterno, apmaterno, email;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                id = results.getInt("id_administrador");
                rut1 = results.getString("rut_administrador");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (correo.equals(email)) {                   
                    obj = new Administrador(id, rut1,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public int agregar(Administrador administrador) {
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
            String agregarSQL = "INSERT INTO administrador (rut_administrador,pnombre,snombre,appaterno,apmaterno,email,activo)"+
                                " VALUES('"+administrador.getRutAdministrador()+"','"+administrador.getPnombre()+"','"+administrador.getSnombre()+"','"+administrador.getAppaterno()+"','"+administrador.getApmaterno()+"','"+administrador.getEmail()+"',"+administrador.getActivo()+");";
            /*
                id_administrador    INT NOT NULL AUTO_INCREMENT,
                rut_administrador   VARCHAR(30) NOT NULL,
                pnombre             VARCHAR(30),
                snombre             VARCHAR(30),
                appaterno           VARCHAR(30),
                apmaterno           VARCHAR(30),
                email               VARCHAR(50) NOT NULL,
                activo              INT, */
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
    public int actualizar(Administrador cordinador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
