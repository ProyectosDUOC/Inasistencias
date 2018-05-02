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
import javax.persistence.Lob;
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
@Table(name = "justificacion_imagen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JustificacionImagen.findAll", query = "SELECT j FROM JustificacionImagen j")
    , @NamedQuery(name = "JustificacionImagen.findByIdJustificacionImg", query = "SELECT j FROM JustificacionImagen j WHERE j.idJustificacionImg = :idJustificacionImg")
    , @NamedQuery(name = "JustificacionImagen.findByIdJustificacion", query = "SELECT j FROM JustificacionImagen j WHERE j.idJustificacion = :idJustificacion")
    , @NamedQuery(name = "JustificacionImagen.findByNombreImagen", query = "SELECT j FROM JustificacionImagen j WHERE j.nombreImagen = :nombreImagen")
    , @NamedQuery(name = "JustificacionImagen.findByDescripcion", query = "SELECT j FROM JustificacionImagen j WHERE j.descripcion = :descripcion")})
public class JustificacionImagen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_justificacion_img")
    private Integer idJustificacionImg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_justificacion")
    private int idJustificacion;
    @Size(max = 30)
    @Column(name = "nombre_imagen")
    private String nombreImagen;
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;

    public JustificacionImagen() {
    }

    public JustificacionImagen(Integer idJustificacionImg) {
        this.idJustificacionImg = idJustificacionImg;
    }

    public JustificacionImagen(Integer idJustificacionImg, int idJustificacion) {
        this.idJustificacionImg = idJustificacionImg;
        this.idJustificacion = idJustificacion;
    }

    public Integer getIdJustificacionImg() {
        return idJustificacionImg;
    }

    public void setIdJustificacionImg(Integer idJustificacionImg) {
        this.idJustificacionImg = idJustificacionImg;
    }

    public int getIdJustificacion() {
        return idJustificacion;
    }

    public void setIdJustificacion(int idJustificacion) {
        this.idJustificacion = idJustificacion;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJustificacionImg != null ? idJustificacionImg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JustificacionImagen)) {
            return false;
        }
        JustificacionImagen other = (JustificacionImagen) object;
        if ((this.idJustificacionImg == null && other.idJustificacionImg != null) || (this.idJustificacionImg != null && !this.idJustificacionImg.equals(other.idJustificacionImg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.JustificacionImagen[ idJustificacionImg=" + idJustificacionImg + " ]";
    }
    
}
