/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author benja
 */
@Entity
@Table(name = "global_semestre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlobalSemestre.findAll", query = "SELECT g FROM GlobalSemestre g")
    , @NamedQuery(name = "GlobalSemestre.findBySemestre", query = "SELECT g FROM GlobalSemestre g WHERE g.semestre = :semestre")
    , @NamedQuery(name = "GlobalSemestre.findByAnio", query = "SELECT g FROM GlobalSemestre g WHERE g.anio = :anio")
    , @NamedQuery(name = "GlobalSemestre.findByFechaInicio", query = "SELECT g FROM GlobalSemestre g WHERE g.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "GlobalSemestre.findByFechaTermino", query = "SELECT g FROM GlobalSemestre g WHERE g.fechaTermino = :fechaTermino")})
public class GlobalSemestre implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_global")
    private int idGlobal;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "semestre")
    private Integer semestre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio")
    private int anio;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_termino")
    @Temporal(TemporalType.DATE)
    private Date fechaTermino;
    private String fechaIni;
    private String fechaTer;
    
    public GlobalSemestre() {
    }

    public GlobalSemestre(int idGlobal, Integer semestre, int anio, Date fechaInicio, Date fechaTermino) {
        this.idGlobal = idGlobal;
        this.semestre = semestre;
        this.anio = anio;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
    }

    public GlobalSemestre(Integer semestre, int anio, Date fechaInicio, Date fechaTermino) {
        this.semestre = semestre;
        this.anio = anio;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
    }

    public GlobalSemestre(int idGlobal, Integer semestre, int anio, String fechaIni, String fechaTer) {
        this.idGlobal = idGlobal;
        this.semestre = semestre;
        this.anio = anio;
        this.fechaIni = fechaIni;
        this.fechaTer = fechaTer;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaTer() {
        return fechaTer;
    }

    public void setFechaTer(String fechaTer) {
        this.fechaTer = fechaTer;
    }

    public GlobalSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public GlobalSemestre(Integer semestre, int anio) {
        this.semestre = semestre;
        this.anio = anio;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (semestre != null ? semestre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlobalSemestre)) {
            return false;
        }
        GlobalSemestre other = (GlobalSemestre) object;
        if ((this.semestre == null && other.semestre != null) || (this.semestre != null && !this.semestre.equals(other.semestre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.GlobalSemestre[ semestre=" + semestre + " ]";
    }

    public int getIdGlobal() {
        return idGlobal;
    }

    public void setIdGlobal(int idGlobal) {
        this.idGlobal = idGlobal;
    }
    
}
