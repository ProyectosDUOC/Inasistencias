/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.ControlUsuario;

/**
 *
 * @author benja
 */
public interface GeneralControlADAO {    
    public abstract ArrayList mostrarDatos();
    public abstract ControlUsuario buscarDatos(String rut) ;     
    public abstract ControlUsuario buscarDatosLogin(String username) ;
    public abstract int agregar(ControlUsuario control);
    public abstract int eliminar(String rut);
    public abstract int actualizar(ControlUsuario control); 
}
