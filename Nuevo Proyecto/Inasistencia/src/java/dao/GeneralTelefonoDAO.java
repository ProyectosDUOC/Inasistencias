/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.TelefonoAlumno;

/**
 *
 * @author benja
 */
public interface GeneralTelefonoDAO {
    public abstract ArrayList mostrarDatos();    
    public abstract TelefonoAlumno buscarDatosAlum(int id) ;   
    public abstract TelefonoAlumno buscarDatos(int id) ;    
    public abstract int agregar(TelefonoAlumno tel);    
    public abstract int actualizar(int id, int num); 
}
