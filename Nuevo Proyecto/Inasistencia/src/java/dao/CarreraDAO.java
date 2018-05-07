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
import modelo.Carrera;

/**
 *
 * @author carlos
 */
public class CarreraDAO implements GeneralCarreraDAO{
    private ArrayList<Carrera> arrayCarreras = new ArrayList<>();
    Conectar conn;
    
    @Override
    public Carrera buscar(int idCarrera) {
        
        Carrera obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM carrera WHERE id_carrera  =" + idCarrera+ ";";

            ResultSet results = statement.executeQuery(query);
            
            int id, director;
            String codCar, nombCar;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                id = results.getInt("id_carrera"); 
                codCar = results.getString("cod_carrera");
                nombCar = results.getString("nombre_carrera");
                director = results.getInt("id_director");
                
                
                if (id == idCarrera) {                   
                    obj = new Carrera(idCarrera, codCar, nombCar, director);
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
    public ArrayList mostrarDatos() {
        Carrera obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM carrera;";

            ResultSet results = statement.executeQuery(consultaSQL);

             int id, director;
            String codCar, nombCar;
            
            //int idDetSecc, idSecc, activo, idAlumno ;
            arrayCarreras.removeAll(arrayCarreras);
            
            while (results.next()) {
               id = results.getInt("id_carrera"); 
                codCar = results.getString("cod_carrera");
                nombCar = results.getString("nombre_carrera");
                director = results.getInt("id_director");
                
                obj = new Carrera(id, codCar, nombCar, director);
                arrayCarreras.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayCarreras;
    }
    
}
