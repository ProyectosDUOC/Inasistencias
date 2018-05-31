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
@Table(name = "ramo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ramo.findAll", query = "SELECT r FROM Ramo r")
    , @NamedQuery(name = "Ramo.findByCodRamo", query = "SELECT r FROM Ramo r WHERE r.codRamo = :codRamo")
    , @NamedQuery(name = "Ramo.findByNombreRamo", query = "SELECT r FROM Ramo r WHERE r.nombreRamo = :nombreRamo")})
public class Ramo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "cod_ramo")
    private String codRamo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_ramo")
    private String nombreRamo;
    
    private String codCarrare;
    private String sigla;
    
    public Ramo() {
    }

    public Ramo(String codRamo, String nombreRamo, String codCarrare, String sigla) {
        this.codRamo = codRamo;
        this.nombreRamo = nombreRamo;
        this.codCarrare = codCarrare;
        this.sigla = sigla;
    }

    public Ramo(String codRamo) {
        this.codRamo = codRamo;
    }

    public String getCodCarrare() {
        return codCarrare;
    }

    public void setCodCarrare(String codCarrare) {
        this.codCarrare = codCarrare;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    
    public String getCodRamo() {
        return codRamo;
    }

    public void setCodRamo(String codRamo) {
        this.codRamo = codRamo;
    }

    public String getNombreRamo() {
        return nombreRamo;
    }

    public void setNombreRamo(String nombreRamo) {
        this.nombreRamo = nombreRamo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codRamo != null ? codRamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ramo)) {
            return false;
        }
        Ramo other = (Ramo) object;
        if ((this.codRamo == null && other.codRamo != null) || (this.codRamo != null && !this.codRamo.equals(other.codRamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Ramo[ codRamo=" + codRamo + " ]";
    }
    
}
