/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Inasistencia;

/**
 *
 * @author carlos
 */
public interface GeneralInasistenciasDAO {
    public abstract Inasistencia buscar(int idInasistencia) ;
}
