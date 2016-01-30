/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoservers;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stand
 */
@Entity
@Table(name = "publicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicacion.findAll", query = "SELECT p FROM Publicacion p"),
    @NamedQuery(name = "Publicacion.findByIdPublicacion", query = "SELECT p FROM Publicacion p WHERE p.idPublicacion = :idPublicacion"),
    @NamedQuery(name = "Publicacion.findByLugGeoPublicacion", query = "SELECT p FROM Publicacion p WHERE p.lugGeoPublicacion = :lugGeoPublicacion"),
    @NamedQuery(name = "Publicacion.findByDescripconPublicacion", query = "SELECT p FROM Publicacion p WHERE p.descripconPublicacion = :descripconPublicacion")})
public class Publicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPublicacion")
    private Integer idPublicacion;
    @Column(name = "lugGeoPublicacion")
    private String lugGeoPublicacion;
    @Column(name = "descripconPublicacion")
    private String descripconPublicacion;

    public Publicacion() {
    }

    public Publicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getLugGeoPublicacion() {
        return lugGeoPublicacion;
    }

    public void setLugGeoPublicacion(String lugGeoPublicacion) {
        this.lugGeoPublicacion = lugGeoPublicacion;
    }

    public String getDescripconPublicacion() {
        return descripconPublicacion;
    }

    public void setDescripconPublicacion(String descripconPublicacion) {
        this.descripconPublicacion = descripconPublicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPublicacion != null ? idPublicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicacion)) {
            return false;
        }
        Publicacion other = (Publicacion) object;
        if ((this.idPublicacion == null && other.idPublicacion != null) || (this.idPublicacion != null && !this.idPublicacion.equals(other.idPublicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "echoservers.Publicacion[ idPublicacion=" + idPublicacion + " ]";
    }
    
}
