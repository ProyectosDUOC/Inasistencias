/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.TipoUsuario;
import conexion.Conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author carlos
 */
public class TipoUsuarioDAO implements GeneralTipoUsuarioDAO{

    Conectar conn;
    
    @Override
    public TipoUsuario buscar(int idCarrera) {
        
        TipoUsuario obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_tipou       INT NOT NULL,
                nombre_tipou   VARCHAR(30)
            
                this.idTipou = idTipou;
                this.nombreTipou = nombreTipou;
            
            */
            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM tipo_usuario WHERE id_tipou  =" + idTipou + ";";

            ResultSet results = statement.executeQuery(query);
            
            int idTipU;
            String nomTipU;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                idTipU = results.getInt("id_tipou"); 
                nomTipU = results.getString("nombre_tipou");
                
                
                if (idTipU == idTipou) {                   
                    obj = new TipoUsuario(idTipU, nomTipU);
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
