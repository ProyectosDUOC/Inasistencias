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
@Table(name = "director")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Director.findAll", query = "SELECT d FROM Director d")
    , @NamedQuery(name = "Director.findByIdDirector", query = "SELECT d FROM Director d WHERE d.idDirector = :idDirector")
    , @NamedQuery(name = "Director.findByRutDirector", query = "SELECT d FROM Director d WHERE d.rutDirector = :rutDirector")
    , @NamedQuery(name = "Director.findByPnombre", query = "SELECT d FROM Director d WHERE d.pnombre = :pnombre")
    , @NamedQuery(name = "Director.findBySnombre", query = "SELECT d FROM Director d WHERE d.snombre = :snombre")
    , @NamedQuery(name = "Director.findByAppaterno", query = "SELECT d FROM Director d WHERE d.appaterno = :appaterno")
    , @NamedQuery(name = "Director.findByApmaterno", query = "SELECT d FROM Director d WHERE d.apmaterno = :apmaterno")
    , @NamedQuery(name = "Director.findByEmail", query = "SELECT d FROM Director d WHERE d.email = :email")
    , @NamedQuery(name = "Director.findByActivo", query = "SELECT d FROM Director d WHERE d.activo = :activo")})
public class Director implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_director")
    private Integer idDirector;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "rut_director")
    private String rutDirector;
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

    public Director() {
    }

    public Director(Integer idDirector, String rutDirector, String pnombre, String snombre, String appaterno, String apmaterno, String email, Integer activo) {
        this.idDirector = idDirector;
        this.rutDirector = rutDirector;
        this.pnombre = pnombre;
        this.snombre = snombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.email = email;
        this.activo = activo;
    }

    public Director(Integer idDirector) {
        this.idDirector = idDirector;
    }

    public Director(Integer idDirector, String rutDirector) {
        this.idDirector = idDirector;
        this.rutDirector = rutDirector;
    }

    public Integer getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Integer idDirector) {
        this.idDirector = idDirector;
    }

    public String getRutDirector() {
        return rutDirector;
    }

    public void setRutDirector(String rutDirector) {
        this.rutDirector = rutDirector;
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
        hash += (idDirector != null ? idDirector.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Director)) {
            return false;
        }
        Director other = (Director) object;
        if ((this.idDirector == null && other.idDirector != null) || (this.idDirector != null && !this.idDirector.equals(other.idDirector))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Director[ idDirector=" + idDirector + " ]";
    }
    
}
