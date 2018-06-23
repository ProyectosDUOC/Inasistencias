/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.SubdirectorComentario;

/**
 *
 * @author benja
 */
public interface GeneralComentario {
    
    public abstract ArrayList mostrarDatos(int idIna);
    public abstract SubdirectorComentario buscar(int idC) ;     
    public abstract int agregar(SubdirectorComentario sub);
    public abstract int eliminar(int idC);
}
