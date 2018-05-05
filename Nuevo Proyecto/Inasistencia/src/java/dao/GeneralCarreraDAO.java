/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Carrera;

/**
 *
 * @author carlos
 */
public interface GeneralCarreraDAO {
    public abstract ArrayList mostrarDatos();
    public abstract Carrera buscar(int idCarrera) ;
}
