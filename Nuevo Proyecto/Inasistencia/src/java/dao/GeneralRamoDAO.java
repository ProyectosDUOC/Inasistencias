/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Ramo;
/**
 *
 * @author benja
 */
public interface GeneralRamoDAO {    
    public abstract Ramo buscar(String codRamo) ;    
    public abstract ArrayList mostrarDatos();
}
