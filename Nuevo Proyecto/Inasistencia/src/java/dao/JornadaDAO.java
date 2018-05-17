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
import modelo.Jornada;

/**
 *
 * @author benja
 */
public class JornadaDAO implements GeneralJornadaDAO{

    Conectar conn;
    
    @Override
    public Jornada buscar(int id) {
        Jornada obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM jornada WHERE id_jornada="+id+";";

            ResultSet results = statement.executeQuery(query);
            
            int id_j, id_a;
            String nombre;
            
        /*
            CREATE TABLE jornada(
                id_jornada INT NOT NULL AUTO_INCREMENT,
                nombre_jornada VARCHAR(10),
                PRIMARY KEY(id_jornada)
            );
        */    
            while (results.next()) {
                id_j = results.getInt("id_jornada"); 
                nombre = results.getString("nombre_jornada");
                                
                if (id_j== id) {                   
                    obj = new Jornada(id_j, nombre);
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
