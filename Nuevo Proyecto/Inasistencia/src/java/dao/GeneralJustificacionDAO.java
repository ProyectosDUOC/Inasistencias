/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Justificacion;

/**
 *
 * @author carlos
 */
public interface GeneralJustificacionDAO {
    public abstract Justificacion buscar(int idJustificacion) ;
    public abstract int agregar(Justificacion justificacion);
    public abstract Justificacion buscarEspecifica(Justificacion jus) ;
}
