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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author benja
 */
@Entity
@Table(name = "secretaria_sda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SecretariaSda.findAll", query = "SELECT s FROM SecretariaSda s")
    , @NamedQuery(name = "SecretariaSda.findByIdSecretariaSda", query = "SELECT s FROM SecretariaSda s WHERE s.idSecretariaSda = :idSecretariaSda")
    , @NamedQuery(name = "SecretariaSda.findByRutSecretariaSda", query = "SELECT s FROM SecretariaSda s WHERE s.rutSecretariaSda = :rutSecretariaSda")
    , @NamedQuery(name = "SecretariaSda.findByPnombre", query = "SELECT s FROM SecretariaSda s WHERE s.pnombre = :pnombre")
    , @NamedQuery(name = "SecretariaSda.findBySnombre", query = "SELECT s FROM SecretariaSda s WHERE s.snombre = :snombre")
    , @NamedQuery(name = "SecretariaSda.findByAppaterno", query = "SELECT s FROM SecretariaSda s WHERE s.appaterno = :appaterno")
    , @NamedQuery(name = "SecretariaSda.findByApmaterno", query = "SELECT s FROM SecretariaSda s WHERE s.apmaterno = :apmaterno")
    , @NamedQuery(name = "SecretariaSda.findByEmail", query = "SELECT s FROM SecretariaSda s WHERE s.email = :email")
    , @NamedQuery(name = "SecretariaSda.findByActivo", query = "SELECT s FROM SecretariaSda s WHERE s.activo = :activo")})
public class SecretariaSda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_secretaria_sda")
    private Integer idSecretariaSda;
    @Size(max = 10)
    @Column(name = "rut_secretaria_sda")
    private String rutSecretariaSda;
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
    @Size(max = 30)
    @Column(name = "email")
    private String email;
    @Column(name = "activo")
    private Integer activo;

    public SecretariaSda() {
    }

    public SecretariaSda(Integer idSecretariaSda, String rutSecretariaSda, String pnombre, String snombre, String appaterno, String apmaterno, String email, Integer activo) {
        this.idSecretariaSda = idSecretariaSda;
        this.rutSecretariaSda = rutSecretariaSda;
        this.pnombre = pnombre;
        this.snombre = snombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.email = email;
        this.activo = activo;
    }

    public SecretariaSda(Integer idSecretariaSda) {
        this.idSecretariaSda = idSecretariaSda;
    }

    public Integer getIdSecretariaSda() {
        return idSecretariaSda;
    }

    public void setIdSecretariaSda(Integer idSecretariaSda) {
        this.idSecretariaSda = idSecretariaSda;
    }

    public String getRutSecretariaSda() {
        return rutSecretariaSda;
    }

    public void setRutSecretariaSda(String rutSecretariaSda) {
        this.rutSecretariaSda = rutSecretariaSda;
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
        hash += (idSecretariaSda != null ? idSecretariaSda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecretariaSda)) {
            return false;
        }
        SecretariaSda other = (SecretariaSda) object;
        if ((this.idSecretariaSda == null && other.idSecretariaSda != null) || (this.idSecretariaSda != null && !this.idSecretariaSda.equals(other.idSecretariaSda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.SecretariaSda[ idSecretariaSda=" + idSecretariaSda + " ]";
    }
    
}
