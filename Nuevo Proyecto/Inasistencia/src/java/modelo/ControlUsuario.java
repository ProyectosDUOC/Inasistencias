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
@Table(name = "control_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlUsuario.findAll", query = "SELECT c FROM ControlUsuario c")
    , @NamedQuery(name = "ControlUsuario.findByIdControlu", query = "SELECT c FROM ControlUsuario c WHERE c.idControlu = :idControlu")
    , @NamedQuery(name = "ControlUsuario.findByUsuario", query = "SELECT c FROM ControlUsuario c WHERE c.usuario = :usuario")
    , @NamedQuery(name = "ControlUsuario.findByClave", query = "SELECT c FROM ControlUsuario c WHERE c.clave = :clave")
    , @NamedQuery(name = "ControlUsuario.findByRutUsuario", query = "SELECT c FROM ControlUsuario c WHERE c.rutUsuario = :rutUsuario")
    , @NamedQuery(name = "ControlUsuario.findByIdTipou", query = "SELECT c FROM ControlUsuario c WHERE c.idTipou = :idTipou")
    , @NamedQuery(name = "ControlUsuario.findByActivo", query = "SELECT c FROM ControlUsuario c WHERE c.activo = :activo")})
public class ControlUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_controlu")
    private Integer idControlu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "clave")
    private String clave;
    @Size(max = 30)
    @Column(name = "rut_usuario")
    private String rutUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipou")
    private int idTipou;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;

    public ControlUsuario() {
    }

    public ControlUsuario(Integer idControlu) {
        this.idControlu = idControlu;
    }

    public ControlUsuario(Integer idControlu, String usuario, String clave, String rutUsuario, int idTipou, int activo) {
        this.idControlu = idControlu;
        this.usuario = usuario;
        this.clave = clave;
        this.rutUsuario = rutUsuario;
        this.idTipou = idTipou;
        this.activo = activo;
    }

    public ControlUsuario(Integer idControlu, String usuario, String clave, int idTipou, int activo) {
        this.idControlu = idControlu;
        this.usuario = usuario;
        this.clave = clave;
        this.idTipou = idTipou;
        this.activo = activo;
    }

    public Integer getIdControlu() {
        return idControlu;
    }

    public void setIdControlu(Integer idControlu) {
        this.idControlu = idControlu;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(String rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    public int getIdTipou() {
        return idTipou;
    }

    public void setIdTipou(int idTipou) {
        this.idTipou = idTipou;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idControlu != null ? idControlu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlUsuario)) {
            return false;
        }
        ControlUsuario other = (ControlUsuario) object;
        if ((this.idControlu == null && other.idControlu != null) || (this.idControlu != null && !this.idControlu.equals(other.idControlu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ControlUsuario[ idControlu=" + idControlu + " ]";
    }
    
}
