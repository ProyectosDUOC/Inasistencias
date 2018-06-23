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
import modelo.ReporteSecretaria;

/**
 *
 * @author benja
 */
public class ReporteSecretariaDAO implements GeneralReporteSDAO{
    Conectar conn;
    private ArrayList<ReporteSecretaria> arrayReportes = new ArrayList<>();
    @Override
    public ArrayList mostrarDatos() {
        ReporteSecretaria obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM reporte_secretaria;";

            ResultSet results = statement.executeQuery(consultaSQL);
            int id, idIna, idJus, idAlum,idSecr,idDire,idlum,semestre,anio,activo;
            
      
            arrayReportes.removeAll(arrayReportes);
            while (results.next()) {
                id = results.getInt("id_reporte");                
                idIna = results.getInt("id_inasistencia");
                idJus = results.getInt("id_justificacion");
                idSecr = results.getInt("id_secretaria");
                idDire = results.getInt("id_director");
                idAlum = results.getInt("id_alumno");
                semestre = results.getInt("semestre");                
                anio = results.getInt("anio");                
                activo = results.getInt("activo");
                
                obj = new ReporteSecretaria(id, idIna, idJus, idSecr, idDire, idAlum, semestre, anio, activo);
                arrayReportes.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayReportes;
    }
    
    public ArrayList mostrarDatosSubdirector(int semeste, int ani) {
        ReporteSecretaria obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM reporte_secretaria where activo=1 and semestre="+semeste+" and anio="+ani+" order by activo desc;";

            ResultSet results = statement.executeQuery(consultaSQL);
            int id, idIna, idJus, idAlum,idSecr,idDire,idlum,semestre,anio,activo;
            
      
            arrayReportes.removeAll(arrayReportes);
            while (results.next()) {
                id = results.getInt("id_reporte");                
                idIna = results.getInt("id_inasistencia");
                idJus = results.getInt("id_justificacion");
                idSecr = results.getInt("id_secretaria");
                idDire = results.getInt("id_director");
                idAlum = results.getInt("id_alumno");
                semestre = results.getInt("semestre");                
                anio = results.getInt("anio");                
                activo = results.getInt("activo");
                
                obj = new ReporteSecretaria(id, idIna, idJus, idSecr, idDire, idAlum, semestre, anio, activo);
                arrayReportes.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayReportes;
    }
    @Override
    public ReporteSecretaria buscarDatos(int idJ) {
        ReporteSecretaria obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM reporte_secretaria where id_justificacion="+idJ+";";

            ResultSet results = statement.executeQuery(consultaSQL);
            
            int id, idIna1, idJus, idAlum,idSecr,idDire,idlum,semestre1,anio1,activo1;
            
      
            
            while (results.next()) {
                id = results.getInt("id_reporte");                
                idIna1 = results.getInt("id_inasistencia");
                idJus = results.getInt("id_justificacion");
                idSecr = results.getInt("id_secretaria");
                idDire = results.getInt("id_director");
                idAlum = results.getInt("id_alumno");
                semestre1 = results.getInt("semestre");                
                anio1 = results.getInt("anio");                
                activo1 = results.getInt("activo");
                
                if (idJ==idJus) {
                    obj = new ReporteSecretaria(id, idIna1, idJus, idSecr, idDire, idAlum, semestre1, anio1, activo1);
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
    public int agregar(ReporteSecretaria reporte) {
     try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
                  /*               
                CREATE TABLE reporte_secretaria (
                    id_reporte         INT NOT NULL AUTO_INCREMENT,
                    id_inasistencia    INT NOT NULL,
                    id_justificacion   INT NOT NULL,
                    id_secretaria      INT NOT NULL,
                    id_director        INT NOT NULL,
                    id_alumno          INT NOT NULL,
                    semestre           INT NOT NULL,
                    anio               INT NOT NULL,
                    activo             INT NOT NULL,
                    PRIMARY KEY(id_reporte)
                );           
            */
            String agregarSQL = "INSERT INTO reporte_secretaria (id_inasistencia, id_justificacion, id_secretaria, id_director, id_alumno, semestre, anio, activo)"+
                                " VALUES("+reporte.getIdInasistencia()+","
                                          +reporte.getIdJustificacion()+","
                                          +reporte.getIdSecretaria()+","
                                          +reporte.getIdDirector()+","
                                          +reporte.getIdAlumno()+","
                                          +reporte.getSemestre()+","
                                          +reporte.getAnio()+","
                                          +reporte.getActivo()+");";        
            int results = statement.executeUpdate(agregarSQL);
            connection.close();
            conn.desconectar();
            return results;
        } catch (java.lang.Exception ex) {
            return 0;
        }
    }

    @Override
    public int actualizar(int idJusti, int activo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList mostrarDatosDirector(int idDirector, int semestre, int anio) {
        ReporteSecretaria obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM reporte_secretaria where id_director="+idDirector+" and semestre="+semestre+" and anio="+anio+" and activo=1 order by id_justificacion asc, activo asc;";

            ResultSet results = statement.executeQuery(consultaSQL);
            
            int id, idIna, idJus, idAlum,idSecr,idDire,idlum,semestre1,anio1,activo1;
            
      
            arrayReportes.removeAll(arrayReportes);
            while (results.next()) {
                id = results.getInt("id_reporte");                
                idIna = results.getInt("id_inasistencia");
                idJus = results.getInt("id_justificacion");
                idSecr = results.getInt("id_secretaria");
                idDire = results.getInt("id_director");
                idAlum = results.getInt("id_alumno");
                semestre1 = results.getInt("semestre");                
                anio1 = results.getInt("anio");                
                activo1 = results.getInt("activo");
                
                obj = new ReporteSecretaria(id, idIna, idJus, idSecr, idDire, idAlum, semestre1, anio1, activo1);
                arrayReportes.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayReportes;
    }

    @Override
    public ReporteSecretaria buscarDatosReporte(int idR) {
        ReporteSecretaria obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM reporte_secretaria where id_reporte="+idR+";";

            ResultSet results = statement.executeQuery(consultaSQL);
            
            int id1, idIna1, idJus, idAlum,idSecr,idDire,idlum,semestre1,anio1,activo1;
            
      
            
            while (results.next()) {
                id1 = results.getInt("id_reporte");                
                idIna1 = results.getInt("id_inasistencia");
                idJus = results.getInt("id_justificacion");
                idSecr = results.getInt("id_secretaria");
                idDire = results.getInt("id_director");
                idAlum = results.getInt("id_alumno");
                semestre1 = results.getInt("semestre");                
                anio1 = results.getInt("anio");                
                activo1 = results.getInt("activo");
                
                if (idR==id1) {
                    obj = new ReporteSecretaria(id1, idIna1, idJus, idSecr, idDire, idAlum, semestre1, anio1, activo1);
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
    public int actualizarActivo(int idR, int activo) {
        int results = 0;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();

            String agregarSQL = "UPDATE reporte_secretaria SET  " + 
                    " activo="+activo+ 
                    " where id_reporte="+idR+";"; 
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
    public ArrayList mostrarDatosAll(int idDirector, int semestre, int anio) {
         ReporteSecretaria obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM reporte_secretaria where id_director="+idDirector+" and semestre="+semestre+" and anio="+anio+" order by id_justificacion asc, activo desc;";

            ResultSet results = statement.executeQuery(consultaSQL);
            
            int id, idIna, idJus, idAlum,idSecr,idDire,idlum,semestre1,anio1,activo1;
            
      
            arrayReportes.removeAll(arrayReportes);
            while (results.next()) {
                id = results.getInt("id_reporte");                
                idIna = results.getInt("id_inasistencia");
                idJus = results.getInt("id_justificacion");
                idSecr = results.getInt("id_secretaria");
                idDire = results.getInt("id_director");
                idAlum = results.getInt("id_alumno");
                semestre1 = results.getInt("semestre");                
                anio1 = results.getInt("anio");                
                activo1 = results.getInt("activo");
                
                obj = new ReporteSecretaria(id, idIna, idJus, idSecr, idDire, idAlum, semestre1, anio1, activo1);
                arrayReportes.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayReportes;
    }

    public ArrayList mostrarDatosAllSubdirector(int semestre, int anio) {
         ReporteSecretaria obj =null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            Statement statement = connection.createStatement();

            String consultaSQL = "SELECT * FROM reporte_secretaria where semestre="+semestre+" and anio="+anio+" order by id_justificacion asc, activo desc;";

            ResultSet results = statement.executeQuery(consultaSQL);
            
            int id, idIna, idJus, idAlum,idSecr,idDire,idlum,semestre1,anio1,activo1;
            
      
            arrayReportes.removeAll(arrayReportes);
            while (results.next()) {
                id = results.getInt("id_reporte");                
                idIna = results.getInt("id_inasistencia");
                idJus = results.getInt("id_justificacion");
                idSecr = results.getInt("id_secretaria");
                idDire = results.getInt("id_director");
                idAlum = results.getInt("id_alumno");
                semestre1 = results.getInt("semestre");                
                anio1 = results.getInt("anio");                
                activo1 = results.getInt("activo");
                
                obj = new ReporteSecretaria(id, idIna, idJus, idSecr, idDire, idAlum, semestre1, anio1, activo1);
                arrayReportes.add(obj);
            }
            connection.close();
            conn.desconectar();
        } catch (java.lang.Exception ex) {
            System.out.println("Error: " + ex);
        }
        return arrayReportes;
    }
    
}
