/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enap.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MARCO
 */
@Entity
@Table(name = "tema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tema.findAll", query = "SELECT t FROM Tema t")
    , @NamedQuery(name = "Tema.findByTemaID", query = "SELECT t FROM Tema t WHERE t.temaID = :temaID")
    , @NamedQuery(name = "Tema.findByNombre", query = "SELECT t FROM Tema t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tema.findByVisto", query = "SELECT t FROM Tema t WHERE t.visto = :visto")})
public class Tema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "temaID")
    private Integer temaID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visto")
    private short visto;
    @JoinColumn(name = "asignatura", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Asignatura asignatura;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tema")
    private List<Asistencia> asistenciaList;

    public Tema() {
    }

    public Tema(Integer temaID) {
        this.temaID = temaID;
    }

    public Tema(Integer temaID, String nombre, short visto) {
        this.temaID = temaID;
        this.nombre = nombre;
        this.visto = visto;
    }

    public Integer getTemaID() {
        return temaID;
    }

    public void setTemaID(Integer temaID) {
        this.temaID = temaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getVisto() {
        return visto;
    }

    public void setVisto(short visto) {
        this.visto = visto;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (temaID != null ? temaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tema)) {
            return false;
        }
        Tema other = (Tema) object;
        if ((this.temaID == null && other.temaID != null) || (this.temaID != null && !this.temaID.equals(other.temaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.enap.modelo.Tema[ temaID=" + temaID + " ]";
    }
    
}
