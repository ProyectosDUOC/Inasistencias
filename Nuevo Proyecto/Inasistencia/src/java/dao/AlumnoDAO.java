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
public class AlumnoDAO implements GeneralAlumnoDAO {

    private ArrayList<Alumno> arrayAlumnos = new ArrayList<>();
    Conectar conn;

    @Override
    public ArrayList mostrarDatos() {
        Alumno obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM alumno where activo=1;";

            ResultSet results = statement.executeQuery(consultaSQL);

            int id, idCarrera, activo;
            String rut, pnombre, snombre, appaterno, apmaterno, email, sexo, tele, celular, jornada;

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
                jornada = results.getString("jornada");
                sexo = results.getString("sexo");
                celular = results.getString("celular");
                tele = results.getString("telefono");
                activo = results.getInt("activo");

                obj = new Alumno(id, rut, pnombre, snombre, appaterno, apmaterno, email, idCarrera, activo, sexo, tele, celular, jornada);
                arrayAlumnos.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayAlumnos;
    }
    
     public ArrayList mostrarDatosEspecificos() {
        Alumno obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = " SELECT * FROM alumno alum left JOIN control_usuario con ON alum.rut_alumno=con.rut_usuario WHERE con.usuario is null  group by alum.rut_alumno;";

            ResultSet results = statement.executeQuery(consultaSQL);

            int id, idCarrera, activo;
            String rut, pnombre, snombre, appaterno, apmaterno, email, sexo, tele, celular, jornada;

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
                jornada = results.getString("jornada");
                sexo = results.getString("sexo");
                celular = results.getString("celular");
                tele = results.getString("telefono");
                activo = results.getInt("activo");

                obj = new Alumno(id, rut, pnombre, snombre, appaterno, apmaterno, email, idCarrera, activo, sexo, tele, celular, jornada);
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
            String query = "SELECT * FROM alumno WHERE rut_alumno='" + rut + "' and activo=1;";

            ResultSet results = statement.executeQuery(query);

            int id, idCarrera, activo;
            String rut1, pnombre, snombre, appaterno, apmaterno, email, jornada, sexo, celular, tele;

            while (results.next()) {
                id = results.getInt("id_alumno");
                rut1 = results.getString("rut_alumno");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");
                idCarrera = results.getInt("id_carrera");
                jornada = results.getString("jornada");
                sexo = results.getString("sexo");
                celular = results.getString("celular");
                tele = results.getString("telefono");
                activo = results.getInt("activo");

                if (rut1.equals(rut)) {
                    obj = new Alumno(id, rut, pnombre, snombre, appaterno, apmaterno, email, idCarrera, activo, sexo, tele, celular, jornada);
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

     public Alumno buscarDatos(String rut) {
        Alumno obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM alumno WHERE rut_alumno='" + rut + "' and activo=1;";

            ResultSet results = statement.executeQuery(query);

            int id, idCarrera, activo;
            String rut1, pnombre, snombre, appaterno, apmaterno, email, jornada, sexo, celular, tele;

            while (results.next()) {
                id = results.getInt("id_alumno");
                rut1 = results.getString("rut_alumno");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");
                idCarrera = results.getInt("id_carrera");
                jornada = results.getString("jornada");
                sexo = results.getString("sexo");
                celular = results.getString("celular");
                tele = results.getString("telefono");
                activo = results.getInt("activo");

                if (rut1.equals(rut)) {
                    obj = new Alumno(id, rut, pnombre, snombre, appaterno, apmaterno, email, idCarrera, activo, sexo, tele, celular, jornada);
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
    
    public Alumno buscarONEDatosCarrea(int id_carrera) {
        Alumno obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM alumno WHERE id_carrera=" + id_carrera + " limit 1;";

            ResultSet results = statement.executeQuery(query);

            int id, idCarrera, activo;
            String rut, pnombre, snombre, appaterno, apmaterno, email, jornada, sexo, celular, tele;

            while (results.next()) {
                id = results.getInt("id_alumno");
                rut = results.getString("rut_alumno");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                jornada = results.getString("jornada");
                sexo = results.getString("sexo");
                celular = results.getString("celular");
                tele = results.getString("telefono");
                email = results.getString("email");
                idCarrera = results.getInt("id_carrera");
                activo = results.getInt("activo");

                if (idCarrera==id_carrera) {

                    obj = new Alumno(id, rut, pnombre, snombre, appaterno, apmaterno, email, idCarrera, activo, sexo, tele, celular, jornada);
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
            String agregarSQL = "INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo,sexo,telefono,celular,jornada)"
                    + " VALUES('" + alumno.getRutAlumno() + "','" + alumno.getPnombre() + "','" + alumno.getSnombre() + "','" + alumno.getAppaterno() + "','" + alumno.getApmaterno() + "','" + alumno.getEmail() + "'," + alumno.getIdCarrera() + "," + alumno.getActivo()
                    + ",'" + alumno.getSexo() + "','" + alumno.getTelefono() + "','" + alumno.getCelular() + "','" + alumno.getJornada() + "');";
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
        int results = 0;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();

            String agregarSQL = "UPDATE alumno SET  "
                    + " telefono='" + alumno.getTelefono()
                    + "', celular='" + alumno.getCelular()
                    + "', email='" + alumno.getEmail()+ "' where id_alumno="+alumno.getIdAlumno()+";";
            results = statement.executeUpdate(agregarSQL);
            connection.close();
            conn.desconectar();

        } //catching excepcion
        catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }

        return results;
    }

    @Override
    public Alumno buscarDatosId(int id) {
        Alumno obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM alumno WHERE id_alumno=" + id + ";";

            ResultSet results = statement.executeQuery(query);

            int id1, idCarrera, activo;
            String rut1, pnombre, snombre, appaterno, apmaterno, email, jornada, sexo, celular, tele;

            while (results.next()) {
                id1 = results.getInt("id_alumno");
                rut1 = results.getString("rut_alumno");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");
                idCarrera = results.getInt("id_carrera");
                activo = results.getInt("activo");
                jornada = results.getString("jornada");
                sexo = results.getString("sexo");
                celular = results.getString("celular");
                tele = results.getString("telefono");
                if (id == id1) {
                    obj = new Alumno(id, rut1, pnombre, snombre, appaterno, apmaterno, email, idCarrera, activo, sexo, tele, celular, jornada);
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
    public ArrayList buscarAlumnoR(String rut) {
        Alumno obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM alumno where rut_alumno='"+rut+"';";

            ResultSet results = statement.executeQuery(consultaSQL);

            int id, idCarrera, activo;
            String rut1, pnombre, snombre, appaterno, apmaterno, email, sexo, tele, celular, jornada;

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
                jornada = results.getString("jornada");
                sexo = results.getString("sexo");
                celular = results.getString("celular");
                tele = results.getString("telefono");
                activo = results.getInt("activo");

                obj = new Alumno(id, rut, pnombre, snombre, appaterno, apmaterno, email, idCarrera, activo, sexo, tele, celular, jornada);
                arrayAlumnos.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayAlumnos;
    }
}
