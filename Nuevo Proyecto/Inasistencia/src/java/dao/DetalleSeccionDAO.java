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
import modelo.DetalleSeccion;

/**
 *
 * @author carlos
 */
public class DetalleSeccionDAO implements GeneralDetalleSeccionDAO{

    Conectar conn;
    
    @Override
    public DetalleSeccion buscar(int idDetalleSecc) {
        
        DetalleSeccion obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            
            /*
                id_detalle_secc   INT NOT NULL AUTO_INCREMENT,
                id_seccion        INT NOT NULL,
                activo            INT NOT NULL,
                id_alumno         INT NOT NULL,
            */
            
            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM detalle_seccion WHERE id_detalle_secc =" + idDetalleSecc+ ";";

            ResultSet results = statement.executeQuery(query);

            int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                idDetSecc = results.getInt("id_detalle_secc"); 
                idSecc = results.getInt("id_seccion");
                activo = results.getInt("activo");
                idAlumno = results.getInt("id_alumno");
                
                
                if (idDetSecc == idDetalleSecc) {                   
                    obj = new DetalleSeccion(idDetalleSecc, idSecc, activo, idAlumno);
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
