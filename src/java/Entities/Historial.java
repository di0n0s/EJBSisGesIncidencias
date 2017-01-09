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
@Table(name = "historial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h"),
    @NamedQuery(name = "Historial.findByIdIncidencia", query = "SELECT h FROM Historial h WHERE h.idIncidencia = :idIncidencia"),
    @NamedQuery(name = "Historial.findByTipoEvento", query = "SELECT h FROM Historial h WHERE h.tipoEvento = :tipoEvento"),
    @NamedQuery(name = "Historial.findByFechaHora", query = "SELECT h FROM Historial h WHERE h.fechaHora = :fechaHora")})
public class Historial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_incidencia")
    private Integer idIncidencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "tipo_evento")
    private String tipoEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fecha_hora")
    private String fechaHora;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Empleados username;

    public Historial() {
    }

    public Historial(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Historial(Integer idIncidencia, String tipoEvento, String fechaHora) {
        this.idIncidencia = idIncidencia;
        this.tipoEvento = tipoEvento;
        this.fechaHora = fechaHora;
    }

    public Historial(Integer idIncidencia, String tipoEvento, String fechaHora, Empleados username) {
        this.idIncidencia = idIncidencia;
        this.tipoEvento = tipoEvento;
        this.fechaHora = fechaHora;
        this.username = username;
    }
    
    

    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Empleados getUsername() {
        return username;
    }

    public void setUsername(Empleados username) {
        this.username = username;
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
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.idIncidencia == null && other.idIncidencia != null) || (this.idIncidencia != null && !this.idIncidencia.equals(other.idIncidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Historial{" + "idIncidencia=" + idIncidencia + ", tipoEvento=" + tipoEvento + ", fechaHora=" + fechaHora + ", username=" + username + '}';
    }


    
}
