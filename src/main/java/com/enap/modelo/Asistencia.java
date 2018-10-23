/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enap.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author MARCO
 */
@Entity
@Table(name = "asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a")
    , @NamedQuery(name = "Asistencia.findByAsistenciaID", query = "SELECT a FROM Asistencia a WHERE a.asistenciaID = :asistenciaID")
    , @NamedQuery(name = "Asistencia.findByFecha", query = "SELECT a FROM Asistencia a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "Asistencia.findByHoraInicio", query = "SELECT a FROM Asistencia a WHERE a.horaInicio = :horaInicio")
    , @NamedQuery(name = "Asistencia.findByHoraFin", query = "SELECT a FROM Asistencia a WHERE a.horaFin = :horaFin")
    , @NamedQuery(name = "Asistencia.findByDinamica", query = "SELECT a FROM Asistencia a WHERE a.dinamica = :dinamica")
    , @NamedQuery(name = "Asistencia.findByIsAusente", query = "SELECT a FROM Asistencia a WHERE a.isAusente = :isAusente")
    , @NamedQuery(name = "Asistencia.findByEstado", query = "SELECT a FROM Asistencia a WHERE a.estado = :estado")
    , @NamedQuery(name = "Asistencia.findByMotivoInasistencia", query = "SELECT a FROM Asistencia a WHERE a.motivoInasistencia = :motivoInasistencia")
    , @NamedQuery(name = "Asistencia.findByObservacion", query = "SELECT a FROM Asistencia a WHERE a.observacion = :observacion")})
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "asistenciaID")
    private Integer asistenciaID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horaInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horaFin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "dinamica")
    private String dinamica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isAusente")
    private boolean isAusente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Size(max = 35)
    @Column(name = "motivoInasistencia")
    private String motivoInasistencia;
    @Size(max = 535)
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "asignaturaID", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Asignatura asignaturaID;
    @JoinColumn(name = "estudianteID", referencedColumnName = "estudianteID")
    @ManyToOne(optional = false)
    private Estudiante estudianteID;
    @JoinColumn(name = "tema", referencedColumnName = "temaID")
    @ManyToOne(optional = false)
    private Tema tema;

    public Asistencia() {
    }

    public Asistencia(Integer asistenciaID) {
        this.asistenciaID = asistenciaID;
    }

    public Asistencia(Integer asistenciaID, Date fecha, Date horaInicio, Date horaFin, String dinamica, boolean isAusente, boolean estado) {
        this.asistenciaID = asistenciaID;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dinamica = dinamica;
        this.isAusente = isAusente;
        this.estado = estado;
    }

    public Integer getAsistenciaID() {
        return asistenciaID;
    }

    public void setAsistenciaID(Integer asistenciaID) {
        this.asistenciaID = asistenciaID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public String getDinamica() {
        return dinamica;
    }

    public void setDinamica(String dinamica) {
        this.dinamica = dinamica;
    }

    public boolean getIsAusente() {
        return isAusente;
    }

    public void setIsAusente(boolean isAusente) {
        this.isAusente = isAusente;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMotivoInasistencia() {
        return motivoInasistencia;
    }

    public void setMotivoInasistencia(String motivoInasistencia) {
        this.motivoInasistencia = motivoInasistencia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Asignatura getAsignaturaID() {
        return asignaturaID;
    }

    public void setAsignaturaID(Asignatura asignaturaID) {
        this.asignaturaID = asignaturaID;
    }

    public Estudiante getEstudianteID() {
        return estudianteID;
    }

    public void setEstudianteID(Estudiante estudianteID) {
        this.estudianteID = estudianteID;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asistenciaID != null ? asistenciaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.asistenciaID == null && other.asistenciaID != null) || (this.asistenciaID != null && !this.asistenciaID.equals(other.asistenciaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.enap.modelo.Asistencia[ asistenciaID=" + asistenciaID + " ]";
    }
    
}
