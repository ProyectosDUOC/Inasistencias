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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author benja
 */
@Entity
@Table(name = "telefono_alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TelefonoAlumno.findAll", query = "SELECT t FROM TelefonoAlumno t")
    , @NamedQuery(name = "TelefonoAlumno.findByIdTel", query = "SELECT t FROM TelefonoAlumno t WHERE t.idTel = :idTel")
    , @NamedQuery(name = "TelefonoAlumno.findByIdAlumno", query = "SELECT t FROM TelefonoAlumno t WHERE t.idAlumno = :idAlumno")
    , @NamedQuery(name = "TelefonoAlumno.findByTelefono", query = "SELECT t FROM TelefonoAlumno t WHERE t.telefono = :telefono")})
public class TelefonoAlumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tel")
    private Integer idTel;
    @Column(name = "id_alumno")
    private Integer idAlumno;
    @Column(name = "telefono")
    private Integer telefono;

    public TelefonoAlumno() {
    }

    public TelefonoAlumno(Integer idTel, Integer idAlumno, Integer telefono) {
        this.idTel = idTel;
        this.idAlumno = idAlumno;
        this.telefono = telefono;
    }

    public TelefonoAlumno(Integer idTel) {
        this.idTel = idTel;
    }

    public Integer getIdTel() {
        return idTel;
    }

    public void setIdTel(Integer idTel) {
        this.idTel = idTel;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTel != null ? idTel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TelefonoAlumno)) {
            return false;
        }
        TelefonoAlumno other = (TelefonoAlumno) object;
        if ((this.idTel == null && other.idTel != null) || (this.idTel != null && !this.idTel.equals(other.idTel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TelefonoAlumno[ idTel=" + idTel + " ]";
    }
    
}
