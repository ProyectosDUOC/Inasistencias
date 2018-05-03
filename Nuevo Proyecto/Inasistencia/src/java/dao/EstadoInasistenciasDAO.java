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
import modelo.EstadoInasistencia;


/**
 *
 * @author carlos
 */
public class EstadoInasistenciasDAO implements GeneralEstadoInasistenciaDAO{

     Conectar conn;
    
    @Override
    public EstadoInasistencia buscar(int id_estadoi) {
        
        EstadoInasistencia obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_estadoi       INT NOT NULL,
                nombre_estadoi   VARCHAR(30)
            
                this.idEstadoi = idEstadoi;
                this.nombreEstadoi = nombreEstadoi;
            */
            
            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM estado_inasistencia WHERE id_estadoi  =" + id_estadoi + ";";

            ResultSet results = statement.executeQuery(query);
            
            int idEstad;
            String nomEst;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                idEstad = results.getInt("id_estadoi"); 
                nomEst = results.getString("nombre_estadoi");               
                
                if (idEstad == id_estadoi) {                   
                    obj = new EstadoInasistencia(id_estadoi, nomEst);
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
