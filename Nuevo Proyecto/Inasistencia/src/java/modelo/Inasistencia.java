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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "inasistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inasistencia.findAll", query = "SELECT i FROM Inasistencia i")
    , @NamedQuery(name = "Inasistencia.findByIdInasistencia", query = "SELECT i FROM Inasistencia i WHERE i.idInasistencia = :idInasistencia")
    , @NamedQuery(name = "Inasistencia.findByFechaInasistencia", query = "SELECT i FROM Inasistencia i WHERE i.fechaInasistencia = :fechaInasistencia")
    , @NamedQuery(name = "Inasistencia.findByIdSeccion", query = "SELECT i FROM Inasistencia i WHERE i.idSeccion = :idSeccion")
    , @NamedQuery(name = "Inasistencia.findByIdAlumno", query = "SELECT i FROM Inasistencia i WHERE i.idAlumno = :idAlumno")
    , @NamedQuery(name = "Inasistencia.findByIdEstadoi", query = "SELECT i FROM Inasistencia i WHERE i.idEstadoi = :idEstadoi")
    , @NamedQuery(name = "Inasistencia.findByIdEstadoc", query = "SELECT i FROM Inasistencia i WHERE i.idEstadoc = :idEstadoc")})
public class Inasistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_inasistencia")
    private Integer idInasistencia;
    @Column(name = "fecha_inasistencia")
    @Temporal(TemporalType.DATE)
    private Date fechaInasistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_seccion")
    private int idSeccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_alumno")
    private int idAlumno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estadoi")
    private int idEstadoi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estadoc")
    private int idEstadoc;
    
    private String fechaInaString;

    public String getFechaInaString() {
        return fechaInaString;
    }

    public void setFechaInaString(String fechaInaString) {
        this.fechaInaString = fechaInaString;
    }
    
    public Inasistencia() {
    }
    public Inasistencia(Integer idInasistencia, String fechaInaString, int idSeccion, int idAlumno, int idEstadoi, int idEstadoc) {
        this.idInasistencia = idInasistencia;
        this.fechaInaString = fechaInaString;
        this.idSeccion = idSeccion;
        this.idAlumno = idAlumno;
        this.idEstadoi = idEstadoi;
        this.idEstadoc = idEstadoc;
    }
    public Inasistencia(Integer idInasistencia, Date fechaInasistencia, int idSeccion, int idAlumno, int idEstadoi, int idEstadoc) {
        this.idInasistencia = idInasistencia;
        this.fechaInasistencia = fechaInasistencia;
        this.idSeccion = idSeccion;
        this.idAlumno = idAlumno;
        this.idEstadoi = idEstadoi;
        this.idEstadoc = idEstadoc;
    }

    public Inasistencia(Integer idInasistencia) {
        this.idInasistencia = idInasistencia;
    }

    public Inasistencia(Integer idInasistencia, int idSeccion, int idAlumno, int idEstadoi, int idEstadoc) {
        this.idInasistencia = idInasistencia;
        this.idSeccion = idSeccion;
        this.idAlumno = idAlumno;
        this.idEstadoi = idEstadoi;
        this.idEstadoc = idEstadoc;
    }

    public Integer getIdInasistencia() {
        return idInasistencia;
    }

    public void setIdInasistencia(Integer idInasistencia) {
        this.idInasistencia = idInasistencia;
    }

    public Date getFechaInasistencia() {
        return fechaInasistencia;
    }

    public void setFechaInasistencia(Date fechaInasistencia) {
        this.fechaInasistencia = fechaInasistencia;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdEstadoi() {
        return idEstadoi;
    }

    public void setIdEstadoi(int idEstadoi) {
        this.idEstadoi = idEstadoi;
    }

    public int getIdEstadoc() {
        return idEstadoc;
    }

    public void setIdEstadoc(int idEstadoc) {
        this.idEstadoc = idEstadoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInasistencia != null ? idInasistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inasistencia)) {
            return false;
        }
        Inasistencia other = (Inasistencia) object;
        if ((this.idInasistencia == null && other.idInasistencia != null) || (this.idInasistencia != null && !this.idInasistencia.equals(other.idInasistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Inasistencia[ idInasistencia=" + idInasistencia + " ]";
    }
    
}
