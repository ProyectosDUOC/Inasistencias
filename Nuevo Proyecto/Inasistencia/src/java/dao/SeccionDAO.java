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
import modelo.Seccion;

/**
 *
 * @author carlos
 */
public class SeccionDAO implements GeneralSeccionDAO{

    Conectar conn;
    
    @Override
    public Seccion buscar(int idSeccion) {
        
        Seccion obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
            id_seccion    INT NOT NULL AUTO_INCREMENT,
            cod_seccion   VARCHAR(30),
            id_docente    INT NOT NULL,
            semestre      INT,
            anio          INT,
            
            this.idSeccion = idSeccion;
            this.codSeccion = codSeccion;
            this.idDocente = idDocente;
            this.semestre = semestre;
            this.anio = anio;
            */
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM seccion WHERE id_seccion  =" + idSeccion + ";";

            ResultSet results = statement.executeQuery(query);
            
            int idSecc, idDoc, semes, anyo;
            String codSecc;
            

            while (results.next()) {
                idSecc = results.getInt("id_seccion"); 
                codSecc = results.getString("cod_seccion");
                idDoc = results.getInt("id_docente");
                semes= results.getInt("semestre");
                anyo= results.getInt("anio");
                
                
                if (idSecc == idSeccion) {                   
                    obj = new Seccion(idSeccion, codSecc, idDoc, semes, anyo);
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
