/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Seccion;

/**
 *
 * @author carlos
 */
public interface GeneralSeccionDAO {
    public abstract Seccion buscar(int idSeccion) ;
    public abstract ArrayList seccionesDocente(int idDocente);//2.- arreglo con el id de un docente(todas sus secciones)
    public abstract ArrayList seccionesAnyoSemestre(int semestre, int anio);//3.- todas las secciones que ocurren en un semestre y año especifico
    public abstract ArrayList seccionesAnuales(int anio);//4.- todas las secciones de un año especifico   
    public abstract ArrayList seccionesAlumnoAnyoSemestre(int idAlumno,int semestre, int anio);
}
    