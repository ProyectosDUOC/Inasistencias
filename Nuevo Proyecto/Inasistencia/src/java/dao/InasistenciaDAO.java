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
import modelo.Inasistencia;

/**
 *
 * @author carlos
 */
public class InasistenciaDAO implements GeneralInasistenciasDAO{

    Conectar conn;
    
    @Override
    public Inasistencia buscar(int idInasistencia) {
        
        Inasistencia obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_inasistencia      INT NOT NULL AUTO_INCREMENT,
                fecha_inasistencia   DATE,
                id_seccion           INT NOT NULL,
                id_alumno            INT NOT NULL,
                id_estadoi           INT NOT NULL,
                id_estadoc           INT NOT NULL,
            
                this.idInasistencia = idInasistencia;
                this.fechaInasistencia = fechaInasistencia;
                this.idSeccion = idSeccion;
                this.idAlumno = idAlumno;
                this.idEstadoi = idEstadoi;
                this.idEstadoc = idEstadoc;
            
                
            */
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM inasistencia WHERE id_inasistencia  =" + idInasistencia + ";";

            ResultSet results = statement.executeQuery(query);
            
            int idIna, idSec, idAl, idEstI, idEstC ;
            Date fechI;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                idIna = results.getInt("id_inasistencia"); 
                fechI = results.getDate("fecha_inasistencia");
                idSec= results.getInt("id_seccion");
                idAl = results.getInt("id_alumno");
                idEstI = results.getInt("id_estadoi");
                idEstC = results.getInt("id_estadoc");
                
                if (idIna == idInasistencia) {                   
                    obj = new Inasistencia(idInasistencia, fechI, idSec, idAl, idEstI, idEstC);
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
