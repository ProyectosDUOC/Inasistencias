/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Administrador;

/**
 *
 * @author benja
 */
public interface GeneralAdminDAO {
    public abstract ArrayList mostrarDatos();
    public abstract Administrador buscarDatos(String rut) ;
    public abstract Administrador buscarDatos(int id) ;    
    public abstract Administrador buscarDatosCorreo(String correo) ;
    public abstract int agregar(Administrador administrador);
    public abstract int eliminar(String rut);
    public abstract int actualizar(Administrador cordinador); 
}
