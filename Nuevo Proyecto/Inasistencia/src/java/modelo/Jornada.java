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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author benja
 */
@Entity
@Table(name = "jornada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jornada.findAll", query = "SELECT j FROM Jornada j")
    , @NamedQuery(name = "Jornada.findByIdJornada", query = "SELECT j FROM Jornada j WHERE j.idJornada = :idJornada")
    , @NamedQuery(name = "Jornada.findByIdAlumno", query = "SELECT j FROM Jornada j WHERE j.idAlumno = :idAlumno")
    , @NamedQuery(name = "Jornada.findByNombreJornada", query = "SELECT j FROM Jornada j WHERE j.nombreJornada = :nombreJornada")})
public class Jornada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_jornada")
    private Integer idJornada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_alumno")
    private int idAlumno;
    @Size(max = 10)
    @Column(name = "nombre_jornada")
    private String nombreJornada;

    public Jornada() {
    }

    public Jornada(Integer idJornada, int idAlumno, String nombreJornada) {
        this.idJornada = idJornada;
        this.idAlumno = idAlumno;
        this.nombreJornada = nombreJornada;
    }

    public Jornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Jornada(Integer idJornada, int idAlumno) {
        this.idJornada = idJornada;
        this.idAlumno = idAlumno;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreJornada() {
        return nombreJornada;
    }

    public void setNombreJornada(String nombreJornada) {
        this.nombreJornada = nombreJornada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJornada != null ? idJornada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jornada)) {
            return false;
        }
        Jornada other = (Jornada) object;
        if ((this.idJornada == null && other.idJornada != null) || (this.idJornada != null && !this.idJornada.equals(other.idJornada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Jornada[ idJornada=" + idJornada + " ]";
    }
    
}
