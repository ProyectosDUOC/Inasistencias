/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Inasistencia;

/**
 *
 * @author carlos
 */
public interface GeneralInasistenciasDAO {
    public abstract Inasistencia buscar(int idInasistencia) ;
    public abstract ArrayList inasistenciaSeccion(int idSeccion);//2.- un arreglo de una seccion (todas las inacistencias de una seccion x)
    public abstract ArrayList inasistenciaAlumno(int idAlumno);//3.- todas las inacistencias de un alumno
    public abstract ArrayList inasistenciaAlumnoSeccion(int idAlumno ,int idSeccion);//4.- todas las incistencias de un alumno de una seccion x 
    public abstract int contadorInasistencias(int idEstadoi, int idSeccion, int idAlumno);
    //5.- un contador de todas las inasistencias de un alumno de una seccion x (estadoi pueda cambiar, recuerda)
    public abstract int agregar(Inasistencia inasistencia);
    public abstract Inasistencia buscarIdCorreo(int idCorreo) ;
    
    public abstract int actualizar(Inasistencia inasistencia); 
    
    public abstract int actualizarCorreoSecretaria(int ina, int estadoC); 
   
}