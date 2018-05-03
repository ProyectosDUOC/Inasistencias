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
import modelo.Motivo;

/**
 *
 * @author carlos
 */
public class MotivoDAO implements GeneralMotivoDAO{
    
    Conectar conn;
    
    @Override
    public Motivo buscar(int idMotivo) {
        
        Motivo obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_motivo       INT NOT NULL,
                nombre_motivo   VARCHAR(30)
            
                this.idMotivo = idMotivo;
                this.nombreMotivo = nombreMotivo;
            
            */
            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM motivo WHERE id_motivo  =" + idMotivo + ";";

            ResultSet results = statement.executeQuery(query);
            
            int idMot;
            String nomMot;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                idMot = results.getInt("id_motivo"); 
                nomMot = results.getString("nombre_motivo");
                
                
                if (idMot == idMotivo) {                   
                    obj = new Motivo(idMotivo, nomMot);
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
     private ArrayList<Motivo> arrayMotivos = new ArrayList<>();
    @Override
    public ArrayList mostrarDatos() {
        Motivo obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM motivo;";

            ResultSet results = statement.executeQuery(consultaSQL);

            int idMot;
            String nomMot;
            

            /*
                id_motivo       INT NOT NULL,
                nombre_motivo   VARCHAR(30)
            
                this.idMotivo = idMotivo;
                this.nombreMotivo = nombreMotivo;
            
            */
            arrayMotivos.removeAll(arrayMotivos);
            while (results.next()) {
                idMot = results.getInt("id_motivo"); 
                nomMot = results.getString("nombre_motivo");
                
                obj = new Motivo(idMot, nomMot);
                arrayMotivos.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayMotivos;
    }
    
}
