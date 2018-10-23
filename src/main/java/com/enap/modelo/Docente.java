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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "docente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d")
    , @NamedQuery(name = "Docente.findByDocenteID", query = "SELECT d FROM Docente d WHERE d.docenteID = :docenteID")
    , @NamedQuery(name = "Docente.findByTipoDocente", query = "SELECT d FROM Docente d WHERE d.tipoDocente = :tipoDocente")
    , @NamedQuery(name = "Docente.findByFacultad", query = "SELECT d FROM Docente d WHERE d.facultad = :facultad")})
public class Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "docenteID")
    private Long docenteID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "tipoDocente")
    private String tipoDocente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "facultad")
    private String facultad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docente")
    private List<Asignatura> asignaturaList;
    @JoinColumn(name = "docenteID", referencedColumnName = "identificacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Docente() {
    }

    public Docente(Long docenteID) {
        this.docenteID = docenteID;
    }

    public Docente(Long docenteID, String tipoDocente, String facultad) {
        this.docenteID = docenteID;
        this.tipoDocente = tipoDocente;
        this.facultad = facultad;
    }

    public Long getDocenteID() {
        return docenteID;
    }

    public void setDocenteID(Long docenteID) {
        this.docenteID = docenteID;
    }

    public String getTipoDocente() {
        return tipoDocente;
    }

    public void setTipoDocente(String tipoDocente) {
        this.tipoDocente = tipoDocente;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    @XmlTransient
    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docenteID != null ? docenteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.docenteID == null && other.docenteID != null) || (this.docenteID != null && !this.docenteID.equals(other.docenteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.enap.modelo.Docente[ docenteID=" + docenteID + " ]";
    }
    
}
