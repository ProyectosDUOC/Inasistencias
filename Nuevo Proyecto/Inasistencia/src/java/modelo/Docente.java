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
@Table(name = "docente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d")
    , @NamedQuery(name = "Docente.findByIdDocente", query = "SELECT d FROM Docente d WHERE d.idDocente = :idDocente")
    , @NamedQuery(name = "Docente.findByRutDocente", query = "SELECT d FROM Docente d WHERE d.rutDocente = :rutDocente")
    , @NamedQuery(name = "Docente.findByPnombre", query = "SELECT d FROM Docente d WHERE d.pnombre = :pnombre")
    , @NamedQuery(name = "Docente.findBySnombre", query = "SELECT d FROM Docente d WHERE d.snombre = :snombre")
    , @NamedQuery(name = "Docente.findByAppaterno", query = "SELECT d FROM Docente d WHERE d.appaterno = :appaterno")
    , @NamedQuery(name = "Docente.findByApmaterno", query = "SELECT d FROM Docente d WHERE d.apmaterno = :apmaterno")
    , @NamedQuery(name = "Docente.findByEmail", query = "SELECT d FROM Docente d WHERE d.email = :email")
    , @NamedQuery(name = "Docente.findByActivo", query = "SELECT d FROM Docente d WHERE d.activo = :activo")})
public class Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_docente")
    private Integer idDocente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "rut_docente")
    private String rutDocente;
    @Size(max = 30)
    @Column(name = "pnombre")
    private String pnombre;
    @Size(max = 30)
    @Column(name = "snombre")
    private String snombre;
    @Size(max = 30)
    @Column(name = "appaterno")
    private String appaterno;
    @Size(max = 30)
    @Column(name = "apmaterno")
    private String apmaterno;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo electrónico no válido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Column(name = "activo")
    private Integer activo;

    public Docente() {
    }

    public Docente(Integer idDocente, String rutDocente, String pnombre, String snombre, String appaterno, String apmaterno, String email, Integer activo) {
        this.idDocente = idDocente;
        this.rutDocente = rutDocente;
        this.pnombre = pnombre;
        this.snombre = snombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.email = email;
        this.activo = activo;
    }

    public Docente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public Docente(Integer idDocente, String rutDocente) {
        this.idDocente = idDocente;
        this.rutDocente = rutDocente;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public String getRutDocente() {
        return rutDocente;
    }

    public void setRutDocente(String rutDocente) {
        this.rutDocente = rutDocente;
    }

    public String getPnombre() {
        return pnombre;
    }

    public void setPnombre(String pnombre) {
        this.pnombre = pnombre;
    }

    public String getSnombre() {
        return snombre;
    }

    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocente != null ? idDocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.idDocente == null && other.idDocente != null) || (this.idDocente != null && !this.idDocente.equals(other.idDocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Docente[ idDocente=" + idDocente + " ]";
    }
    
}
