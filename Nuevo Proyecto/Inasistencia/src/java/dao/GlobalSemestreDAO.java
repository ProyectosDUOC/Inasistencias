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
import java.util.Date;
import modelo.GlobalSemestre;

/**
 *
 * @author benja
 */
public class GlobalSemestreDAO implements GeneralGlobalSemestreDAO{
    Conectar conn;
    
    @Override
    public GlobalSemestre buscar() {
        GlobalSemestre obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM global_semestre;";
            /*
                    id_global      INT NOT NULL,
                    semestre       INT NOT NULL,
                    anio           INT NOT NULL,
                    fecha_inicio   DATE,
                    fecha_termino  DATE,
            */
            ResultSet results = statement.executeQuery(query);

            int id,semestre, anio;
            Date fecha_inicio, fecha_termino;

            while (results.next()) {
                id = results.getInt("id_global");
                semestre = results.getInt("semestre");
                anio = results.getInt("anio");
                fecha_inicio = results.getDate("fecha_inicio");
                fecha_termino = results.getDate("fecha_termino");
                
                obj = new GlobalSemestre(semestre, anio, fecha_inicio, fecha_termino);
                break;
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return obj;
    }

    @Override
    public int actualizar(GlobalSemestre globalSemestre) {
        int results = 0;

        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            /*
                    semestre       INT NOT NULL,
                    anio           INT NOT NULL,
                    fecha_inicio   DATE,
                    fecha_termino  DATE,
            */
            Statement statement = connection.createStatement();
            String agregarSQL = "UPDATE global_semestre SET"
                    + " semestre = " + globalSemestre.getSemestre()+
                    ", anio = "+globalSemestre.getAnio()+
                    ", fecha_inicio = '"+globalSemestre.getFechaInicio()+
                    "', fecha_termino = '"+globalSemestre.getFechaTermino()+"' where id_global = 1 "; 
            
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
