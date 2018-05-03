/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.EstadoInasistencia;

/**
 *
 * @author carlos
 */
public interface GeneralEstadoInasistenciaDAO {
    public abstract EstadoInasistencia buscar(int id_estadoi) ;
}
