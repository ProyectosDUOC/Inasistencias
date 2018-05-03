/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.ArrayList;
import modelo.Director;
/**
 *
 * @author carlos
 */
public interface GeneralDirectorDAO {
    public abstract ArrayList mostrarDatos();
    public abstract Director buscarDatos(String rut) ;
    public abstract Director buscarDatos(int idDirector) ;    
    public abstract Director buscarDatosCorreo(String email) ;
    public abstract int agregar(Director director);
    public abstract int eliminar(String rut);
    public abstract int actualizar(Director cordinador);
}
