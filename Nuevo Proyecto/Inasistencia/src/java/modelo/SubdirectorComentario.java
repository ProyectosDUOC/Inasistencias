/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author benja
 */
@Entity
@Table(name = "subdirector_comentario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubdirectorComentario.findAll", query = "SELECT s FROM SubdirectorComentario s")
    , @NamedQuery(name = "SubdirectorComentario.findByIdSubdirectorC", query = "SELECT s FROM SubdirectorComentario s WHERE s.idSubdirectorC = :idSubdirectorC")
    , @NamedQuery(name = "SubdirectorComentario.findByIdInasistencia", query = "SELECT s FROM SubdirectorComentario s WHERE s.idInasistencia = :idInasistencia")
    , @NamedQuery(name = "SubdirectorComentario.findByIdSecretariaSda", query = "SELECT s FROM SubdirectorComentario s WHERE s.idSecretariaSda = :idSecretariaSda")
    , @NamedQuery(name = "SubdirectorComentario.findByIdSubdirector", query = "SELECT s FROM SubdirectorComentario s WHERE s.idSubdirector = :idSubdirector")
    , @NamedQuery(name = "SubdirectorComentario.findByFechaComentario", query = "SELECT s FROM SubdirectorComentario s WHERE s.fechaComentario = :fechaComentario")
    , @NamedQuery(name = "SubdirectorComentario.findByGlosa", query = "SELECT s FROM SubdirectorComentario s WHERE s.glosa = :glosa")})
public class SubdirectorComentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_subdirector_c")
    private Integer idSubdirectorC;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_inasistencia")
    private int idInasistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_secretaria_sda")
    private int idSecretariaSda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_subdirector")
    private int idSubdirector;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_comentario")
    @Temporal(TemporalType.DATE)
    private Date fechaComentario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "glosa")
    private String glosa;
    private String fechaComentarios;
    
    public SubdirectorComentario() {
    }
    

    public SubdirectorComentario(Integer idSubdirectorC) {
        this.idSubdirectorC = idSubdirectorC;
    }

    public SubdirectorComentario(Integer idSubdirectorC, int idInasistencia, int idSecretariaSda, int idSubdirector, String fechaComentarios, String glosa) {
        this.idSubdirectorC = idSubdirectorC;
        this.idInasistencia = idInasistencia;
        this.idSecretariaSda = idSecretariaSda;
        this.idSubdirector = idSubdirector;
        this.glosa = glosa;
        this.fechaComentarios = fechaComentarios;
    }

    public SubdirectorComentario(Integer idSubdirectorC, int idInasistencia, int idSecretariaSda, int idSubdirector, Date fechaComentario, String glosa) {
        this.idSubdirectorC = idSubdirectorC;
        this.idInasistencia = idInasistencia;
        this.idSecretariaSda = idSecretariaSda;
        this.idSubdirector = idSubdirector;
        this.fechaComentario = fechaComentario;
        this.glosa = glosa;
    }

    public String getFechaComentarios() {
        return fechaComentarios;
    }

    public void setFechaComentarios(String fechaComentarios) {
        this.fechaComentarios = fechaComentarios;
    }

    public Integer getIdSubdirectorC() {
        return idSubdirectorC;
    }

    public void setIdSubdirectorC(Integer idSubdirectorC) {
        this.idSubdirectorC = idSubdirectorC;
    }

    public int getIdInasistencia() {
        return idInasistencia;
    }

    public void setIdInasistencia(int idInasistencia) {
        this.idInasistencia = idInasistencia;
    }

    public int getIdSecretariaSda() {
        return idSecretariaSda;
    }

    public void setIdSecretariaSda(int idSecretariaSda) {
        this.idSecretariaSda = idSecretariaSda;
    }

    public int getIdSubdirector() {
        return idSubdirector;
    }

    public void setIdSubdirector(int idSubdirector) {
        this.idSubdirector = idSubdirector;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubdirectorC != null ? idSubdirectorC.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubdirectorComentario)) {
            return false;
        }
        SubdirectorComentario other = (SubdirectorComentario) object;
        if ((this.idSubdirectorC == null && other.idSubdirectorC != null) || (this.idSubdirectorC != null && !this.idSubdirectorC.equals(other.idSubdirectorC))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.SubdirectorComentario[ idSubdirectorC=" + idSubdirectorC + " ]";
    }
    
}
