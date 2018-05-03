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
import modelo.Ramo;

/**
 *
 * @author benja
 */
public class RamoDAO implements GeneralRamoDAO{
    
    Conectar conn;
    
    @Override
    public Ramo buscar(String codRamo) {
        Ramo obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                cod_ramo      VARCHAR(30) NOT NULL,
                nombre_ramo   VARCHAR(100) NOT NULL,
            */
            
            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM ramo WHERE cod_ramo  ='" + codRamo + "';";

            ResultSet results = statement.executeQuery(query);
            
            String cod, nomMot;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                cod = results.getString("cod_ramo"); 
                nomMot = results.getString("nombre_ramo");
                
                
                if (cod.equals(codRamo)) {                   
                    obj = new Ramo(codRamo, nomMot);
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
