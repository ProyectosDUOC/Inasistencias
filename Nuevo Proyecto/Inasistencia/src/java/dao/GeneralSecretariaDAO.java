/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Secretaria;

/**
 *
 * @author benja
 */
public interface GeneralSecretariaDAO {
    public abstract ArrayList mostrarDatos();
    public abstract Secretaria buscarDatos(String rut) ;
    public abstract Secretaria buscarDatos(int id) ;    
    public abstract Secretaria buscarDatosCorreo(String correo) ;
    public abstract int agregar(Secretaria secretaria);
    public abstract int eliminar(String rut);
    public abstract int actualizar(Secretaria secretaria); 
}
