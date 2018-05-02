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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author benja
 */
@Entity
@Table(name = "justificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Justificacion.findAll", query = "SELECT j FROM Justificacion j")
    , @NamedQuery(name = "Justificacion.findByIdJustificacion", query = "SELECT j FROM Justificacion j WHERE j.idJustificacion = :idJustificacion")
    , @NamedQuery(name = "Justificacion.findByIdInasistencia", query = "SELECT j FROM Justificacion j WHERE j.idInasistencia = :idInasistencia")
    , @NamedQuery(name = "Justificacion.findByFechaJustificacion", query = "SELECT j FROM Justificacion j WHERE j.fechaJustificacion = :fechaJustificacion")
    , @NamedQuery(name = "Justificacion.findByIdMotivo", query = "SELECT j FROM Justificacion j WHERE j.idMotivo = :idMotivo")
    , @NamedQuery(name = "Justificacion.findByGlosa", query = "SELECT j FROM Justificacion j WHERE j.glosa = :glosa")})
public class Justificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_justificacion")
    private Integer idJustificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_inasistencia")
    private int idInasistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_justificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaJustificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_motivo")
    private int idMotivo;
    @Size(max = 300)
    @Column(name = "glosa")
    private String glosa;

    public Justificacion() {
    }

    public Justificacion(Integer idJustificacion, int idInasistencia, Date fechaJustificacion, int idMotivo, String glosa) {
        this.idJustificacion = idJustificacion;
        this.idInasistencia = idInasistencia;
        this.fechaJustificacion = fechaJustificacion;
        this.idMotivo = idMotivo;
        this.glosa = glosa;
    }

    public Justificacion(Integer idJustificacion) {
        this.idJustificacion = idJustificacion;
    }

    public Justificacion(Integer idJustificacion, int idInasistencia, Date fechaJustificacion, int idMotivo) {
        this.idJustificacion = idJustificacion;
        this.idInasistencia = idInasistencia;
        this.fechaJustificacion = fechaJustificacion;
        this.idMotivo = idMotivo;
    }

    public Integer getIdJustificacion() {
        return idJustificacion;
    }

    public void setIdJustificacion(Integer idJustificacion) {
        this.idJustificacion = idJustificacion;
    }

    public int getIdInasistencia() {
        return idInasistencia;
    }

    public void setIdInasistencia(int idInasistencia) {
        this.idInasistencia = idInasistencia;
    }

    public Date getFechaJustificacion() {
        return fechaJustificacion;
    }

    public void setFechaJustificacion(Date fechaJustificacion) {
        this.fechaJustificacion = fechaJustificacion;
    }

    public int getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJustificacion != null ? idJustificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Justificacion)) {
            return false;
        }
        Justificacion other = (Justificacion) object;
        if ((this.idJustificacion == null && other.idJustificacion != null) || (this.idJustificacion != null && !this.idJustificacion.equals(other.idJustificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Justificacion[ idJustificacion=" + idJustificacion + " ]";
    }
    
}
