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
import modelo.SecretariaSda;

/**
 *
 * @author benja
 */
public class SubSecretariaDAO implements GeneralSubSecretariaDAO{

     Conectar conn;
    
    @Override
    public ArrayList mostrarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SecretariaSda buscarDatos(String rut) {
        SecretariaSda obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM secretaria_sda WHERE rut_secretaria_sda='" + rut + "';";

            ResultSet results = statement.executeQuery(query);
            
            int id, activo;
            String rut1, pnombre, snombre, appaterno, apmaterno, email;
            
            while (results.next()) {
                id = results.getInt("id_secretaria_sda");
                rut1 = results.getString("rut_secretaria_sda");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (rut1.equals(rut)) {                   
                    obj = new SecretariaSda(id, rut1,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public SecretariaSda buscarDatos(int id) {
        SecretariaSda obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM secretaria_sda WHERE id_secretaria_sda=" + id + ";";

            ResultSet results = statement.executeQuery(query);
            
            int id1, activo;
            String rut, pnombre, snombre, appaterno, apmaterno, email;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                id1 = results.getInt("id_secretaria_sda");
                rut = results.getString("rut_secretaria_sda");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (id == id1) {                   
                    obj = new SecretariaSda(id, rut,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public SecretariaSda buscarDatosCorreo(String correo) {
    SecretariaSda obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM secretaria_sda WHERE email='" + correo + "';";

            ResultSet results = statement.executeQuery(query);
            
            int id, activo;
            String rut, pnombre, snombre, appaterno, apmaterno, email;
            
            while (results.next()) {
                id = results.getInt("id_secretaria_sda");
                rut = results.getString("rut_secretaria_sda");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (correo.equals(email)) {                   
                    obj = new SecretariaSda(id, rut,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public int agregar(SecretariaSda secretaria) {
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
            String agregarSQL = "INSERT INTO secretaria_sda(rut_secretaria_sda,pnombre,snombre,appaterno,apmaterno,email,activo)"
                    + " VALUES('" + secretaria.getRutSecretariaSda()+ "','" + secretaria.getPnombre() + "','" + secretaria.getSnombre() + "','" + secretaria.getAppaterno() + "','" + secretaria.getApmaterno() + "','" + secretaria.getEmail() + "'," + secretaria.getActivo()+ ");";
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
    public int actualizar(SecretariaSda secretaria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
