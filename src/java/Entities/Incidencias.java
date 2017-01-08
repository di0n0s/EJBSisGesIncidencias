/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sfcar
 */
@Entity
@Table(name = "incidencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidencias.findAll", query = "SELECT i FROM Incidencias i"),
    @NamedQuery(name = "Incidencias.findByIdIncidencia", query = "SELECT i FROM Incidencias i WHERE i.idIncidencia = :idIncidencia"),
    @NamedQuery(name = "Incidencias.findByFechaHora", query = "SELECT i FROM Incidencias i WHERE i.fechaHora = :fechaHora"),
    @NamedQuery(name = "Incidencias.findByTipo", query = "SELECT i FROM Incidencias i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "Incidencias.findByDetalle", query = "SELECT i FROM Incidencias i WHERE i.detalle = :detalle")})
public class Incidencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_incidencia")
    private Integer idIncidencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fecha_hora")
    private String fechaHora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "detalle")
    private String detalle;
    @JoinColumn(name = "origen", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Empleados origen;
    @JoinColumn(name = "destino", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Empleados destino;

    public Incidencias() {
    }

    public Incidencias(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Incidencias(Integer idIncidencia, String fechaHora, String tipo, String detalle) {
        this.idIncidencia = idIncidencia;
        this.fechaHora = fechaHora;
        this.tipo = tipo;
        this.detalle = detalle;
    }

    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Empleados getOrigen() {
        return origen;
    }

    public void setOrigen(Empleados origen) {
        this.origen = origen;
    }

    public Empleados getDestino() {
        return destino;
    }

    public void setDestino(Empleados destino) {
        this.destino = destino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIncidencia != null ? idIncidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incidencias)) {
            return false;
        }
        Incidencias other = (Incidencias) object;
        if ((this.idIncidencia == null && other.idIncidencia != null) || (this.idIncidencia != null && !this.idIncidencia.equals(other.idIncidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Incidencias[ idIncidencia=" + idIncidencia + " ]";
    }
    
}
