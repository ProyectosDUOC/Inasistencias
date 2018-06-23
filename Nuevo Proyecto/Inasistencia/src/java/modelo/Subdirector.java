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
@Table(name = "subdirector")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subdirector.findAll", query = "SELECT s FROM Subdirector s")
    , @NamedQuery(name = "Subdirector.findByIdSubdirector", query = "SELECT s FROM Subdirector s WHERE s.idSubdirector = :idSubdirector")
    , @NamedQuery(name = "Subdirector.findByRutSubdirector", query = "SELECT s FROM Subdirector s WHERE s.rutSubdirector = :rutSubdirector")
    , @NamedQuery(name = "Subdirector.findByPnombre", query = "SELECT s FROM Subdirector s WHERE s.pnombre = :pnombre")
    , @NamedQuery(name = "Subdirector.findBySnombre", query = "SELECT s FROM Subdirector s WHERE s.snombre = :snombre")
    , @NamedQuery(name = "Subdirector.findByAppaterno", query = "SELECT s FROM Subdirector s WHERE s.appaterno = :appaterno")
    , @NamedQuery(name = "Subdirector.findByApmaterno", query = "SELECT s FROM Subdirector s WHERE s.apmaterno = :apmaterno")
    , @NamedQuery(name = "Subdirector.findByEmail", query = "SELECT s FROM Subdirector s WHERE s.email = :email")
    , @NamedQuery(name = "Subdirector.findByActivo", query = "SELECT s FROM Subdirector s WHERE s.activo = :activo")})
public class Subdirector implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_subdirector")
    private Integer idSubdirector;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "rut_subdirector")
    private String rutSubdirector;
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

    public Subdirector() {
    }

    public Subdirector(Integer idSubdirector, String rutSubdirector, String pnombre, String snombre, String appaterno, String apmaterno, String email, Integer activo) {
        this.idSubdirector = idSubdirector;
        this.rutSubdirector = rutSubdirector;
        this.pnombre = pnombre;
        this.snombre = snombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.email = email;
        this.activo = activo;
    }

    public Subdirector(Integer idSubdirector) {
        this.idSubdirector = idSubdirector;
    }

    public Subdirector(Integer idSubdirector, String rutSubdirector) {
        this.idSubdirector = idSubdirector;
        this.rutSubdirector = rutSubdirector;
    }

    public Integer getIdSubdirector() {
        return idSubdirector;
    }

    public void setIdSubdirector(Integer idSubdirector) {
        this.idSubdirector = idSubdirector;
    }

    public String getRutSubdirector() {
        return rutSubdirector;
    }

    public void setRutSubdirector(String rutSubdirector) {
        this.rutSubdirector = rutSubdirector;
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
        hash += (idSubdirector != null ? idSubdirector.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subdirector)) {
            return false;
        }
        Subdirector other = (Subdirector) object;
        if ((this.idSubdirector == null && other.idSubdirector != null) || (this.idSubdirector != null && !this.idSubdirector.equals(other.idSubdirector))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Subdirector[ idSubdirector=" + idSubdirector + " ]";
    }
    
}
