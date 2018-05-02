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
@Table(name = "detalle_seccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSeccion.findAll", query = "SELECT d FROM DetalleSeccion d")
    , @NamedQuery(name = "DetalleSeccion.findByIdDetalleSecc", query = "SELECT d FROM DetalleSeccion d WHERE d.idDetalleSecc = :idDetalleSecc")
    , @NamedQuery(name = "DetalleSeccion.findByIdSeccion", query = "SELECT d FROM DetalleSeccion d WHERE d.idSeccion = :idSeccion")
    , @NamedQuery(name = "DetalleSeccion.findByActivo", query = "SELECT d FROM DetalleSeccion d WHERE d.activo = :activo")
    , @NamedQuery(name = "DetalleSeccion.findByIdAlumno", query = "SELECT d FROM DetalleSeccion d WHERE d.idAlumno = :idAlumno")})
public class DetalleSeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_secc")
    private Integer idDetalleSecc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_seccion")
    private int idSeccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_alumno")
    private int idAlumno;

    public DetalleSeccion() {
    }

    public DetalleSeccion(Integer idDetalleSecc) {
        this.idDetalleSecc = idDetalleSecc;
    }

    public DetalleSeccion(Integer idDetalleSecc, int idSeccion, int activo, int idAlumno) {
        this.idDetalleSecc = idDetalleSecc;
        this.idSeccion = idSeccion;
        this.activo = activo;
        this.idAlumno = idAlumno;
    }

    public Integer getIdDetalleSecc() {
        return idDetalleSecc;
    }

    public void setIdDetalleSecc(Integer idDetalleSecc) {
        this.idDetalleSecc = idDetalleSecc;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleSecc != null ? idDetalleSecc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleSeccion)) {
            return false;
        }
        DetalleSeccion other = (DetalleSeccion) object;
        if ((this.idDetalleSecc == null && other.idDetalleSecc != null) || (this.idDetalleSecc != null && !this.idDetalleSecc.equals(other.idDetalleSecc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleSeccion[ idDetalleSecc=" + idDetalleSecc + " ]";
    }
    
}
