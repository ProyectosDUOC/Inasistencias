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
@Table(name = "tipo_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsuario.findAll", query = "SELECT t FROM TipoUsuario t")
    , @NamedQuery(name = "TipoUsuario.findByIdTipou", query = "SELECT t FROM TipoUsuario t WHERE t.idTipou = :idTipou")
    , @NamedQuery(name = "TipoUsuario.findByNombreTipou", query = "SELECT t FROM TipoUsuario t WHERE t.nombreTipou = :nombreTipou")})
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipou")
    private Integer idTipou;
    @Size(max = 30)
    @Column(name = "nombre_tipou")
    private String nombreTipou;

    public TipoUsuario() {
    }

    public TipoUsuario(Integer idTipou, String nombreTipou) {
        this.idTipou = idTipou;
        this.nombreTipou = nombreTipou;
    }

    public TipoUsuario(Integer idTipou) {
        this.idTipou = idTipou;
    }

    public Integer getIdTipou() {
        return idTipou;
    }

    public void setIdTipou(Integer idTipou) {
        this.idTipou = idTipou;
    }

    public String getNombreTipou() {
        return nombreTipou;
    }

    public void setNombreTipou(String nombreTipou) {
        this.nombreTipou = nombreTipou;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipou != null ? idTipou.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario other = (TipoUsuario) object;
        if ((this.idTipou == null && other.idTipou != null) || (this.idTipou != null && !this.idTipou.equals(other.idTipou))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoUsuario[ idTipou=" + idTipou + " ]";
    }
    
}
