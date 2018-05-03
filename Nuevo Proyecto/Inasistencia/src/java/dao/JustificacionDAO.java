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
import modelo.Justificacion;

/**
 *
 * @author carlos
 */
public class JustificacionDAO implements GeneralJustificacionDAO{

    Conectar conn;
    
    @Override
    public Justificacion buscar(int idCarrera) {
     
        Justificacion obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_justificacion      INT NOT NULL AUTO_INCREMENT,
                id_inasistencia       INT NOT NULL,
                fecha_justificacion   DATE NOT NULL,
                id_motivo             INT NOT NULL,
                glosa                 VARCHAR(300),
            
                this.idJustificacion = idJustificacion;
                this.idInasistencia = idInasistencia;
                this.fechaJustificacion = fechaJustificacion;
                this.idMotivo = idMotivo;
                this.glosa = glosa;
            */
            
            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM justificacion WHERE id_justificacion  =" + idJustificacion + ";";

            ResultSet results = statement.executeQuery(query);
            
            int id, idIna, idMot;
            String glos;
            Date fechaJ;

            while (results.next()) {
                id = results.getInt("id_jutificacion"); 
                idIna = results.getInt("id_inasistencia"); 
                fechaJ = results.getDate("fecha_justificacion"); 
                idMot = results.getInt("id_motivo"); 
                glos = results.getString("glosa");
                
                
                if (id == idJustificacion) {                   
                    obj = new Justificacion(idIna, idIna, fechaJ, idMot, glos);
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
