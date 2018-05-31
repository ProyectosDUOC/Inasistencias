/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conectar;
import java.util.ArrayList;
import modelo.Secretaria;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author benja
 */
public class SecretariaDAO implements GeneralSecretariaDAO{

    Conectar conn;
    
    @Override
    public ArrayList mostrarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Secretaria buscarDatos(String rut) {
        Secretaria obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_secretaria       INT NOT NULL AUTO_INCREMENT,
                rut_secretaria      VARCHAR(30) NOT NULL,
                pnombre             VARCHAR(30),
                snombre             VARCHAR(30),
                appaterno           VARCHAR(30),
                apmaterno           VARCHAR(30),
                email               VARCHAR(50) NOT NULL,
                activo              INT,
            */
                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM secretaria WHERE rut_secretaria='" + rut + "';";

            ResultSet results = statement.executeQuery(query);
            
            int id, activo;
            String rut1, pnombre, snombre, appaterno, apmaterno, email;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                id = results.getInt("id_secretaria");
                rut1 = results.getString("rut_secretaria");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (rut1.equals(rut)) {                   
                    obj = new Secretaria(id, rut1,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public Secretaria buscarDatos(int id) {
        Secretaria obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_secretaria       INT NOT NULL AUTO_INCREMENT,
                rut_secretaria      VARCHAR(30) NOT NULL,
                pnombre             VARCHAR(30),
                snombre             VARCHAR(30),
                appaterno           VARCHAR(30),
                apmaterno           VARCHAR(30),
                email               VARCHAR(50) NOT NULL,
                activo              INT,
            */
                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM secretaria WHERE id_secretaria=" + id + ";";

            ResultSet results = statement.executeQuery(query);
            
            int id1, activo;
            String rut, pnombre, snombre, appaterno, apmaterno, email;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                id1 = results.getInt("id_secretaria");
                rut = results.getString("rut_secretaria");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (id == id1) {                   
                    obj = new Secretaria(id, rut,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public Secretaria buscarDatosCorreo(String correo) {
    Secretaria obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_secretaria       INT NOT NULL AUTO_INCREMENT,
                rut_secretaria      VARCHAR(30) NOT NULL,
                pnombre             VARCHAR(30),
                snombre             VARCHAR(30),
                appaterno           VARCHAR(30),
                apmaterno           VARCHAR(30),
                email               VARCHAR(50) NOT NULL,
                activo              INT,
            */
                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM secretaria WHERE email='" + correo + "';";

            ResultSet results = statement.executeQuery(query);
            
            int id, activo;
            String rut, pnombre, snombre, appaterno, apmaterno, email;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                id = results.getInt("id_secretaria");
                rut = results.getString("rut_secretaria");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (correo.equals(email)) {                   
                    obj = new Secretaria(id, rut,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public int agregar(Secretaria secretaria) {
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
            String agregarSQL = "INSERT INTO secretaria(rut_secretaria,pnombre,snombre,appaterno,apmaterno,email,activo)"
                    + " VALUES('" + secretaria.getRutSecretaria()+ "','" + secretaria.getPnombre() + "','" + secretaria.getSnombre() + "','" + secretaria.getAppaterno() + "','" + secretaria.getApmaterno() + "','" + secretaria.getEmail() + "'," + secretaria.getActivo()+ ");";
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
    public int actualizar(Secretaria secretaria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
