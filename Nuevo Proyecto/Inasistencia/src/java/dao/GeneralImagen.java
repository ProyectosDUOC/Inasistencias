/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.JustificacionImagen;

/**
 *
 * @author benja
 */
public interface GeneralImagen {
    public abstract JustificacionImagen buscar(int idJustificacion) ;
    public abstract int agregar(JustificacionImagen img);
}
