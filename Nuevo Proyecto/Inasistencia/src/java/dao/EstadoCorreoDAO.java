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
import modelo.EstadoCorreo;

/**
 *
 * @author carlos
 */
public class  EstadoCorreoDAO implements GeneralEstadoCorreo{

    Conectar conn;
    
    @Override
    public EstadoCorreo buscar(int estadoc) {
        /*
            id_estadoc       INT NOT NULL,
            nombre_estadoc   VARCHAR(30)
        */
        
        EstadoCorreo obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM estado_correo WHERE id_estadoc =" + estadoc + ";";

            ResultSet results = statement.executeQuery(query);

            int id;
            String nombre;

            while (results.next()) {
                id = results.getInt("id_estadoc");    
                nombre = results.getString("nombre_estadoc");
                
                if (id == estadoc) {
                    obj = new EstadoCorreo(estadoc, nombre);
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
