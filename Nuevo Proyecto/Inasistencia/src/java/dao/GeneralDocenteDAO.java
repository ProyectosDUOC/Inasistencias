/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Docente;
/**
 *
 * @author carlos
 */
public interface GeneralDocenteDAO {
    public abstract ArrayList mostrarDatos();
    public abstract Docente buscarDatos(String rut) ;
    public abstract Docente buscarDatos(int idDirector) ;    
    public abstract Docente buscarDatosCorreo(String email) ;
    public abstract int agregar(Docente director);
    public abstract int eliminar(String rut);
    public abstract int actualizar(Docente cordinador);
}
