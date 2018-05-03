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
import modelo.Docente;

/**
 *
 * @author carlos
 */
public class DocenteDAO implements GeneralDocenteDAO{

     Conectar conn;
    
    @Override
    public ArrayList mostrarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Docente buscarDatos(String rut) {
       Docente obj = null;
        try {
            conn = new Conectar();
            Connection connection = conn.getConnection();

            /*
                id_docente    INT NOT NULL AUTO_INCREMENT,
                rut_docente   VARCHAR(30) NOT NULL,
                pnombre       VARCHAR(30),
                snombre       VARCHAR(30),
                appaterno     VARCHAR(30),
                apmaterno     VARCHAR(30),
                email         VARCHAR(50),
                activo        INT,
            
                this.idDocente = idDocente;
                this.rutDocente = rutDocente;
                this.pnombre = pnombre;
                this.snombre = snombre;
                this.appaterno = appaterno;
                this.apmaterno = apmaterno;
                this.email = email;
                this.activo = activo;
            */
                        
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM docente WHERE rut_docente='" + rutDocente + "';";

            ResultSet results = statement.executeQuery(query);
            
            int id, activo;
            String rut, pNom;
            
            //int idDetSecc, idSecc, activo, idAlumno ;

            while (results.next()) {
                id = results.getInt("id_administrador");
                rut1 = results.getString("rut_administrador");
                pnombre = results.getString("pnombre");
                snombre = results.getString("snombre");
                appaterno = results.getString("appaterno");
                apmaterno = results.getString("apmaterno");
                email = results.getString("email");              
                activo = results.getInt("activo");               
                
                if (rut1.equals(rut)) {                   
                    obj = new Administrador(id, rut1,pnombre, snombre, appaterno, apmaterno, email, activo);
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
    public Docente buscarDatos(int idDirector) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Docente buscarDatosCorreo(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int agregar(Docente director) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Docente cordinador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
