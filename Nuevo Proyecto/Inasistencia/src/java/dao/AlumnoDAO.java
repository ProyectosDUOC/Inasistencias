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
import modelo.Alumno;

/**
 *
 * @author benja
 */
public class AlumnoDAO implements GeneralAlumnoDAO{

    private ArrayList<Alumno> arrayAlumnos = new ArrayList<>();
    Conectar conn;
    
    @Override
    public ArrayList mostrarDatos() {
        Alumno obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM alumno;";

            ResultSet results = statement.executeQuery(consultaSQL);

            int id, idCarrera, activo;
            String rut, pnombre, snombre, appaterno, apmaterno, email;

            /* id_alumno    INT NOT NULL AUTO_INCREMENT,
                rut_almuno   VARCHAR(30) NOT NULL,
                pnombre      VARCHAR(30),
                snombre      VARCHAR(30),
                appaterno    VARCHAR(30),
                apmaterno    VARCHAR(30),
                email        VARCHAR(50) NOT NULL,
                id_carrera   INT NOT NULL,
                activo       INT,*/
            arrayAlumnos.removeAll(arrayAlumnos);
            while (results.next()) {
                id = results.getInt("id_alumno");
                rut = results.getString("rut_alumno");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");
                idCarrera = results.getInt("id_carrera");                
                activo = results.getInt("activo");
                
                obj = new Alumno(id, rut, pnombre, snombre, appaterno, apmaterno, email, idCarrera, activo);
                arrayAlumnos.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayAlumnos;
    }

    @Override
    public Alumno buscarDatos(String rut) {
        Alumno obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM alumno WHERE rut_alumno='" + rut + "';";

            ResultSet results = statement.executeQuery(query);

            int id, idCarrera, activo;
            String rut1, pnombre, snombre, appaterno, apmaterno, email;

            while (results.next()) {
                id = results.getInt("id_alumno");
                rut1 = results.getString("rut_alumno");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");
                idCarrera = results.getInt("id_carrera");                
                activo = results.getInt("activo");

                if (rut1.equals(rut)) {
                    obj = new Alumno(id, rut, pnombre, snombre, appaterno, apmaterno, email, idCarrera, activo);
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

    @Override
    public Alumno buscarDatosCorreo(String correo) {
        Alumno obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM alumno WHERE email='" + correo + "';";

            ResultSet results = statement.executeQuery(query);

            int id, idCarrera, activo;
            String rut, pnombre, snombre, appaterno, apmaterno, email;

            while (results.next()) {
                id = results.getInt("id_alumno");
                rut = results.getString("rut_alumno");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");
                idCarrera = results.getInt("id_carrera");                
                activo = results.getInt("activo");

                if (email.equals(correo)) {
                    obj = new Alumno(id, rut, pnombre, snombre, appaterno, apmaterno, email, idCarrera, activo);
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

    @Override
    public int agregar(Alumno alumno) {
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
            String agregarSQL = "INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,appmaterno,email,id_carrera,activo)"
                    + " VALUES('"+alumno.getRutAlumno()+"','"+alumno.getPnombre()+"','"+alumno.getSnombre()+"','"+alumno.getAppaterno()+"','"+alumno.getApmaterno()+"','"+alumno.getEmail()+"',"+alumno.getIdCarrera()+","+alumno.getActivo()+");";
            int results = statement.executeUpdate(agregarSQL);
            connection.close();
            conn.desconectar();
            return results;
        } catch (java.lang.Exception ex) {
            return 0;
        }
    }

    @Override
    public int eliminar(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Alumno alumno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
