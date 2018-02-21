/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.InasistenciaImagen;

/**
 *
 * @author benja
 */
public class ImagenDAO {

    private ArrayList<InasistenciaImagen> arrayImg = new ArrayList<>();

    public ArrayList mostrarDatos() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");
            Statement statement = connection.createStatement();
            String consultaSQL = "SELECT * FROM inasistencia_imagen;";
            ResultSet results = statement.executeQuery(consultaSQL);

            /* CREATE TABLE inasistencia_imagen(
                id_inasistencia INT NOT NULL,
                nombre_imagen VARCHAR(30),
                imagen MEDIUMBLOB,
                descripcion VARCHAR(30),
                PRIMARY KEY(id_inasistencia));
             */
            arrayImg.removeAll(arrayImg);
            while (results.next()) {
                InasistenciaImagen imagen = new InasistenciaImagen();
                imagen.setIdInasistencia(results.getInt("id_inasistecia"));
                imagen.setNombreImagen(results.getString("nombre_imagen"));
                imagen.setImagen(results.getBytes("imagen"));
                imagen.setDescripcion(results.getString("descripcion"));

                arrayImg.add(imagen);
            }
            connection.close();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayImg;
    }

    public int agregar(InasistenciaImagen imagen) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");
            Statement statement = connection.createStatement();
            /* CREATE TABLE inasistencia_imagen(
                id_inasistencia INT NOT NULL,
                nombre_imagen VARCHAR(30),
                imagen MEDIUMBLOB,
                descripcion VARCHAR(30),
                PRIMARY KEY(id_inasistencia));
             */
            String agregarSQL = "INSERT INTO inasistencia_imagen VALUES("
                    + imagen.getIdInasistencia()
                    + ",'" + imagen.getNombreImagen()
                    + "','" + imagen.getArchivoImagen()
                    + "','" + imagen.getDescripcion() + "');";
            int results = statement.executeUpdate(agregarSQL);
            connection.close();
            return results;
        } catch (java.lang.Exception ex) {
            return 0;
        }
    }

    public InasistenciaImagen buscar(int idInasistencia) {
        InasistenciaImagen obj = new InasistenciaImagen();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM inasistencia_imagen WHERE id_inasistencia = " + idInasistencia + ";";

            ResultSet results = statement.executeQuery(query);
            /* CREATE TABLE inasistencia_imagen(
                id_inasistencia INT NOT NULL,
                nombre_imagen VARCHAR(30),
                imagen MEDIUMBLOB,
                descripcion VARCHAR(30),
                PRIMARY KEY(id_inasistencia));
             */
            while (results.next()) {
                obj.setIdInasistencia(results.getInt("id_inasistencia"));
                obj.setNombreImagen(results.getString("nombre_imagen"));
                obj.setImagen(results.getBytes("imagen"));
                obj.setDescripcion(results.getString("descripcion"));
                break;
            }
            connection.close();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return obj;
    }

}
