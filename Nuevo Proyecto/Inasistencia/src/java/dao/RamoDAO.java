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
            String query = "SELECT * FROM ramo WHERE cod_ramo ='"+codRamo+"';";

            ResultSet results = statement.executeQuery(query);            
            String cod, nomMot;

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
    
    
    private ArrayList<Ramo> arrayRamos = new ArrayList<>();
    @Override
    public ArrayList mostrarDatos() {
        Ramo obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM ramo;";

            ResultSet results = statement.executeQuery(consultaSQL);
            String cod, nomMot;
            

            /*
                d_ramo       VARCHAR(30) NOT NULL,
                nombre_ramo   VARCHAR(100)
            
            */
            arrayRamos.removeAll(arrayRamos);
            while (results.next()) {
                cod = results.getString("id_ramo"); 
                nomMot = results.getString("nombre_ramo");
                
                obj = new Ramo(cod, nomMot);
                arrayRamos.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayRamos;
    }
    
}
