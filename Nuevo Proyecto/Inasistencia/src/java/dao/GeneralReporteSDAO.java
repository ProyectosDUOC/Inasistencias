/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.ReporteSecretaria;

/**
 *
 * @author benja
 */
public interface GeneralReporteSDAO {
    public abstract ArrayList mostrarDatos();    
    public abstract ArrayList mostrarDatosDirector(int idDirector, int semestre , int anio);
    public abstract ReporteSecretaria buscarDatos(int idJ) ; 
    public abstract int agregar(ReporteSecretaria reporte);
    public abstract int actualizar(int idJusti, int activo);
}
