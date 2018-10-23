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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a")
    , @NamedQuery(name = "Asignatura.findByCodigo", query = "SELECT a FROM Asignatura a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Asignatura.findByNombre", query = "SELECT a FROM Asignatura a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Asignatura.findByNrohoras", query = "SELECT a FROM Asignatura a WHERE a.nrohoras = :nrohoras")
    , @NamedQuery(name = "Asignatura.findByEdificio", query = "SELECT a FROM Asignatura a WHERE a.edificio = :edificio")
    , @NamedQuery(name = "Asignatura.findByAula", query = "SELECT a FROM Asignatura a WHERE a.aula = :aula")})
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nrohoras")
    private int nrohoras;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "edificio")
    private String edificio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "aula")
    private String aula;
    @JoinTable(name = "asignatura_estudiante", joinColumns = {
        @JoinColumn(name = "codigoAsignatura", referencedColumnName = "codigo")}, inverseJoinColumns = {
        @JoinColumn(name = "estudianteID", referencedColumnName = "estudianteID")})
    @ManyToMany
    private List<Estudiante> estudianteList;
    @JoinColumn(name = "docente", referencedColumnName = "docenteID")
    @ManyToOne(optional = false)
    private Docente docente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura")
    private List<Tema> temaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignaturaID")
    private List<Asistencia> asistenciaList;

    public Asignatura() {
    }

    public Asignatura(Integer codigo) {
        this.codigo = codigo;
    }

    public Asignatura(Integer codigo, String nombre, int nrohoras, String edificio, String aula) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nrohoras = nrohoras;
        this.edificio = edificio;
        this.aula = aula;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNrohoras() {
        return nrohoras;
    }

    public void setNrohoras(int nrohoras) {
        this.nrohoras = nrohoras;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    @XmlTransient
    public List<Tema> getTemaList() {
        return temaList;
    }

    public void setTemaList(List<Tema> temaList) {
        this.temaList = temaList;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.enap.modelo.Asignatura[ codigo=" + codigo + " ]";
    }
    
}
