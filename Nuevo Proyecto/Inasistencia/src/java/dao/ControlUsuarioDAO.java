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
import modelo.ControlUsuario;

/**
 *
 * @author benja
 */
public class ControlUsuarioDAO implements GeneralControlUsuarioDAO{
    Conectar conn;
    
    @Override
    public ArrayList mostrarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ControlUsuario buscarDatos(String rut) {      
        ControlUsuario obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
          
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM control_usuario WHERE rut_usuario='" + rut + "';";

            ResultSet results = statement.executeQuery(query);
            
            int id, tipou, activo ;
            String usuario,clave,rut1;
             /*
            id_controlu   INT NOT NULL AUTO_INCREMENT,
            usuario       VARCHAR(30) NOT NULL,
            clave         VARCHAR(30) NOT NULL,
            rut_usuario   VARCHAR(30) NULL,
            id_tipou      INT NOT NULL,
            activo        INT NOT NULL,
        */
            while (results.next()) {
                id = results.getInt("id_controlu");
                rut1 = results.getString("rut_usuario");
                clave = results.getString("clave");
                usuario = results.getString("usuario");
                tipou = results.getInt("id_tipou");
                activo = results.getInt("activo");

                if (rut1.equals(rut)) {
                    obj = new ControlUsuario(id, usuario, clave, rut,tipou, activo);
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

    public ControlUsuario buscarDatosIdTipo(String rut, int tipo) {      
        ControlUsuario obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
          
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM control_usuario WHERE rut_usuario='" + rut + "' and id_tipou="+tipo+";";

            ResultSet results = statement.executeQuery(query);
            
            int id, tipou, activo ;
            String usuario,clave,rut1;
             /*
            id_controlu   INT NOT NULL AUTO_INCREMENT,
            usuario       VARCHAR(30) NOT NULL,
            clave         VARCHAR(30) NOT NULL,
            rut_usuario   VARCHAR(30) NULL,
            id_tipou      INT NOT NULL,
            activo        INT NOT NULL,
        */
            while (results.next()) {
                id = results.getInt("id_controlu");
                rut1 = results.getString("rut_usuario");
                clave = results.getString("clave");
                usuario = results.getString("usuario");
                tipou = results.getInt("id_tipou");
                activo = results.getInt("activo");

                if (rut1.equals(rut)) {
                    obj = new ControlUsuario(id, usuario, clave, rut,tipou, activo);
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
    public ControlUsuario buscarDatosLogin(String user) {
        ControlUsuario obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
          
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM control_usuario WHERE usuario='" + user + "';";

            ResultSet results = statement.executeQuery(query);
            
            int id, tipou, activo ;
            String usuario,clave,rut;
             /*
            id_controlu   INT NOT NULL AUTO_INCREMENT,
            usuario       VARCHAR(30) NOT NULL,
            clave         VARCHAR(30) NOT NULL,
            rut_usuario   VARCHAR(30) NULL,
            id_tipou      INT NOT NULL,
            activo        INT NOT NULL,
        */
            while (results.next()) {
                id = results.getInt("id_controlu");
                rut = results.getString("rut_usuario");
                clave = results.getString("clave");
                usuario = results.getString("usuario");
                tipou = results.getInt("id_tipou");
                activo = results.getInt("activo");

                if (usuario.equals(user)) {
                    obj = new ControlUsuario(id, usuario, clave, rut,tipou, activo);
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
    public int agregar(ControlUsuario control) {
         try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
            String agregarSQL = "INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo)"
                    + " VALUES('" + control.getUsuario()+ "','" + control.getClave()+ "','" + control.getRutUsuario()+ "'," + control.getIdTipou()+ ",1);";
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
    public int actualizarClave(ControlUsuario control) {
        int results = 0;

        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
          
            Statement statement = connection.createStatement();
            String agregarSQL = "UPDATE control_usuario SET  " +
                    " clave = '" + control.getClave() +
                    "' where id_controlu = " +control.getIdControlu(); 
            
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
