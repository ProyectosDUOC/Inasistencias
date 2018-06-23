/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.SecretariaSda;

/**
 *
 * @author benja
 */
public interface GeneralSubSecretariaDAO {
    public abstract ArrayList mostrarDatos();
    public abstract SecretariaSda buscarDatos(String rut) ;
    public abstract SecretariaSda buscarDatos(int id) ;    
    public abstract SecretariaSda buscarDatosCorreo(String correo) ;
    public abstract int agregar(SecretariaSda secretaria);
    public abstract int eliminar(String rut);
    public abstract int actualizar(SecretariaSda secretaria); 
}
