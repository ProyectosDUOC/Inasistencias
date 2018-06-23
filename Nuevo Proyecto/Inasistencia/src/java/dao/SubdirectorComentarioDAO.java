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
import java.util.Date;
import modelo.SubdirectorComentario;

/**
 *
 * @author benja
 */
public class SubdirectorComentarioDAO implements GeneralComentario {

    Conectar conn;
    private ArrayList<SubdirectorComentario> arrayComentarios = new ArrayList<>();

    @Override
    public ArrayList mostrarDatos(int idIna) {
        SubdirectorComentario obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM subdirector_comentario where id_inasistencia="+idIna+";";

            ResultSet results = statement.executeQuery(consultaSQL);

            int idC, idI, idS, idSub;
            String glosa;
            Date fecha;
            
            /* 
             CREATE TABLE IF NOT EXISTS `subdirector_comentario` (
            `id_subdirector_c` int(11) NOT NULL AUTO_INCREMENT,
            `id_inasistencia` int(11) NOT NULL,
            `id_secretaria_sda` int(11) NOT NULL,
            `id_subdirector` int(11) NOT NULL,    
            `fecha_comentario` date NOT NULL,    
            `glosa` varchar(300) NOT NULL,
            PRIMARY KEY (`id_subdirector_c`)
          ) AUTO_INCREMENT=1 ;
             */
            arrayComentarios.removeAll(arrayComentarios);
            while (results.next()) {
                idC = results.getInt("id_subdirector_c");
                idI = results.getInt("id_inasistencia");
                idS = results.getInt("id_secretaria_sda");
                idSub = results.getInt("id_subdirector");
                fecha = results.getDate("fecha_comentario");
                glosa = results.getString("glosa");

                obj = new SubdirectorComentario(idC, idI, idS, idSub, fecha, glosa);
                arrayComentarios.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayComentarios;
    }

    @Override
    public SubdirectorComentario buscar(int idC) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int agregar(SubdirectorComentario sub) {
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
            String agregarSQL = "INSERT INTO subdirector_comentario(id_inasistencia,id_secretaria_sda,id_subdirector,fecha_comentario,glosa)"
                    + " VALUES(" +sub.getIdInasistencia()+","+sub.getIdSecretariaSda()+","+sub.getIdSubdirector()+",'" + sub.getFechaComentarios()
                    + "','" + sub.getGlosa() + "');";
            int results = statement.executeUpdate(agregarSQL);
            connection.close();
            conn.desconectar();
            return results;
        } catch (java.lang.Exception ex) {
            return 0;
        }
    }

    @Override
    public int eliminar(int idC) {
        int results = 0;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();

            String agregarSQL = "DELETE FROM subdirector_comentario WHERE id_subdirector_c="+idC+";"; 
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
