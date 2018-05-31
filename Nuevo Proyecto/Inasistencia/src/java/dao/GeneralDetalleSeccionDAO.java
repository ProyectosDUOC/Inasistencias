/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.DetalleSeccion;

/**
 *
 * @author carlos
 */
public interface GeneralDetalleSeccionDAO {
   public abstract DetalleSeccion buscar(int idDetalleSecc) ;//busca por un id_detalle especifico
   public abstract ArrayList mostrarDatosSeccion(int idSeccion);//busca por un curso X de seccion = entrega todos los alumnos del curso
   public abstract ArrayList buscarDetalleAlumno(String rut) ;//pregunta por todas las secciones que esta el alumno
   public abstract ArrayList mostrar(int year, int semestre);
   public abstract ArrayList mostrarAlumno(int idAlumno,int year, int semestre);
}

//select * from detalle_seccion where id_seccion = elidseccion;