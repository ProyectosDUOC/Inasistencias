/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author benja
 */
@Entity
@Table(name = "reporte_secretaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteSecretaria.findAll", query = "SELECT r FROM ReporteSecretaria r")
    , @NamedQuery(name = "ReporteSecretaria.findByIdReporte", query = "SELECT r FROM ReporteSecretaria r WHERE r.idReporte = :idReporte")
    , @NamedQuery(name = "ReporteSecretaria.findByIdInasistencia", query = "SELECT r FROM ReporteSecretaria r WHERE r.idInasistencia = :idInasistencia")
    , @NamedQuery(name = "ReporteSecretaria.findByIdJustificacion", query = "SELECT r FROM ReporteSecretaria r WHERE r.idJustificacion = :idJustificacion")
    , @NamedQuery(name = "ReporteSecretaria.findByIdSecretaria", query = "SELECT r FROM ReporteSecretaria r WHERE r.idSecretaria = :idSecretaria")
    , @NamedQuery(name = "ReporteSecretaria.findByIdDirector", query = "SELECT r FROM ReporteSecretaria r WHERE r.idDirector = :idDirector")
    , @NamedQuery(name = "ReporteSecretaria.findByIdAlumno", query = "SELECT r FROM ReporteSecretaria r WHERE r.idAlumno = :idAlumno")
    , @NamedQuery(name = "ReporteSecretaria.findBySemestre", query = "SELECT r FROM ReporteSecretaria r WHERE r.semestre = :semestre")
    , @NamedQuery(name = "ReporteSecretaria.findByAnio", query = "SELECT r FROM ReporteSecretaria r WHERE r.anio = :anio")
    , @NamedQuery(name = "ReporteSecretaria.findByActivo", query = "SELECT r FROM ReporteSecretaria r WHERE r.activo = :activo")})
public class ReporteSecretaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reporte")
    private Integer idReporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_inasistencia")
    private int idInasistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_justificacion")
    private int idJustificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_secretaria")
    private int idSecretaria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_director")
    private int idDirector;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_alumno")
    private int idAlumno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semestre")
    private int semestre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio")
    private int anio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;

    public ReporteSecretaria() {
    }

    public ReporteSecretaria(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public ReporteSecretaria(Integer idReporte, int idInasistencia, int idJustificacion, int idSecretaria, int idDirector, int idAlumno, int semestre, int anio, int activo) {
        this.idReporte = idReporte;
        this.idInasistencia = idInasistencia;
        this.idJustificacion = idJustificacion;
        this.idSecretaria = idSecretaria;
        this.idDirector = idDirector;
        this.idAlumno = idAlumno;
        this.semestre = semestre;
        this.anio = anio;
        this.activo = activo;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public int getIdInasistencia() {
        return idInasistencia;
    }

    public void setIdInasistencia(int idInasistencia) {
        this.idInasistencia = idInasistencia;
    }

    public int getIdJustificacion() {
        return idJustificacion;
    }

    public void setIdJustificacion(int idJustificacion) {
        this.idJustificacion = idJustificacion;
    }

    public int getIdSecretaria() {
        return idSecretaria;
    }

    public void setIdSecretaria(int idSecretaria) {
        this.idSecretaria = idSecretaria;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReporte != null ? idReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteSecretaria)) {
            return false;
        }
        ReporteSecretaria other = (ReporteSecretaria) object;
        if ((this.idReporte == null && other.idReporte != null) || (this.idReporte != null && !this.idReporte.equals(other.idReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ReporteSecretaria[ idReporte=" + idReporte + " ]";
    }
    
}
