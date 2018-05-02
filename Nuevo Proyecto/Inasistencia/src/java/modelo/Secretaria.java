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
@Table(name = "secretaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Secretaria.findAll", query = "SELECT s FROM Secretaria s")
    , @NamedQuery(name = "Secretaria.findByIdSecretaria", query = "SELECT s FROM Secretaria s WHERE s.idSecretaria = :idSecretaria")
    , @NamedQuery(name = "Secretaria.findByRutSecretaria", query = "SELECT s FROM Secretaria s WHERE s.rutSecretaria = :rutSecretaria")
    , @NamedQuery(name = "Secretaria.findByPnombre", query = "SELECT s FROM Secretaria s WHERE s.pnombre = :pnombre")
    , @NamedQuery(name = "Secretaria.findBySnombre", query = "SELECT s FROM Secretaria s WHERE s.snombre = :snombre")
    , @NamedQuery(name = "Secretaria.findByAppaterno", query = "SELECT s FROM Secretaria s WHERE s.appaterno = :appaterno")
    , @NamedQuery(name = "Secretaria.findByApmaterno", query = "SELECT s FROM Secretaria s WHERE s.apmaterno = :apmaterno")
    , @NamedQuery(name = "Secretaria.findByEmail", query = "SELECT s FROM Secretaria s WHERE s.email = :email")
    , @NamedQuery(name = "Secretaria.findByActivo", query = "SELECT s FROM Secretaria s WHERE s.activo = :activo")})
public class Secretaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_secretaria")
    private Integer idSecretaria;
    @Size(max = 30)
    @Column(name = "rut_secretaria")
    private String rutSecretaria;
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

    public Secretaria() {
    }

    public Secretaria(Integer idSecretaria, String rutSecretaria, String pnombre, String snombre, String appaterno, String apmaterno, String email, Integer activo) {
        this.idSecretaria = idSecretaria;
        this.rutSecretaria = rutSecretaria;
        this.pnombre = pnombre;
        this.snombre = snombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.email = email;
        this.activo = activo;
    }

    public Secretaria(Integer idSecretaria) {
        this.idSecretaria = idSecretaria;
    }

    public Integer getIdSecretaria() {
        return idSecretaria;
    }

    public void setIdSecretaria(Integer idSecretaria) {
        this.idSecretaria = idSecretaria;
    }

    public String getRutSecretaria() {
        return rutSecretaria;
    }

    public void setRutSecretaria(String rutSecretaria) {
        this.rutSecretaria = rutSecretaria;
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
        hash += (idSecretaria != null ? idSecretaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Secretaria)) {
            return false;
        }
        Secretaria other = (Secretaria) object;
        if ((this.idSecretaria == null && other.idSecretaria != null) || (this.idSecretaria != null && !this.idSecretaria.equals(other.idSecretaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Secretaria[ idSecretaria=" + idSecretaria + " ]";
    }
    
}
