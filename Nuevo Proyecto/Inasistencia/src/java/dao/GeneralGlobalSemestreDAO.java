/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.GlobalSemestre;

/**
 *
 * @author benja
 */
public interface GeneralGlobalSemestreDAO {
    public abstract GlobalSemestre buscar();
    public abstract int actualizar(GlobalSemestre globalSemestre);
     
}
