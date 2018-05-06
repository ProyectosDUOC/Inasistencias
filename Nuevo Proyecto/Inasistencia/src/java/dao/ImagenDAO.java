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
import modelo.JustificacionImagen;

/**
 *
 * @author benja
 */
public class ImagenDAO implements GeneralImagen{
    Conectar conn;
    
    @Override
    public JustificacionImagen buscar(int idJustificacion) {
    
        JustificacionImagen obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM justificacion_imagen WHERE id_justificacion =" + idJustificacion+ ";";

            ResultSet results = statement.executeQuery(query);
            
            int id_img, id_j;
            String nombre, desc;
            
             /*
        CREATE TABLE justificacion_imagen (
            id_justificacion_img   INT NOT NULL AUTO_INCREMENT,
            id_justificacion       INT NOT NULL,
            nombre_imagen          VARCHAR(50),
            descripcion            VARCHAR(100),
            PRIMARY KEY(id_justificacion_img)
        );
    
    */   
            
            while (results.next()) {
                id_img = results.getInt("id_justuficacion_img"); 
                id_j = results.getInt("id_justificacion");
                nombre = results.getString("nombre_imagen");
                desc = results.getString("descripcion");
                                
                if (id_j== idJustificacion) {                   
                    obj = new JustificacionImagen(id_img, idJustificacion, nombre, desc);
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
    public int agregar(JustificacionImagen img) {
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
            String agregarSQL = "INSERT INTO justificacion_imagen (id_justificacion,nombre_imagen,descripcion)"
                    + " VALUES("+img.getIdJustificacion()+",'"+img.getNombreImagen()+"','"+img.getDescripcion()+"');";
            int results = statement.executeUpdate(agregarSQL);
            connection.close();
            conn.desconectar();
            return results;
        } catch (java.lang.Exception ex) {
            return 0;
        }
    }
    
}
