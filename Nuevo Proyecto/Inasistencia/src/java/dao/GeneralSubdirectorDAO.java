/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Subdirector;

/**
 *
 * @author benja
 */
public interface GeneralSubdirectorDAO {
    public abstract Subdirector buscarDatos(String rut) ;
    public abstract Subdirector buscarDatos(int idDirector) ;  
    public abstract int agregar(Subdirector director);
    public abstract int eliminar(String rut);
    public abstract int actualizar(Subdirector sub);
}
