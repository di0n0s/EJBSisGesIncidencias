/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author sfcar
 */
@Entity
@Table(name = "empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e"),
    @NamedQuery(name = "Empleados.findByUsername", query = "SELECT e FROM Empleados e WHERE e.username = :username"),
    @NamedQuery(name = "Empleados.findByPassword", query = "SELECT e FROM Empleados e WHERE e.password = :password"),
    @NamedQuery(name = "Empleados.findByNombreCompleto", query = "SELECT e FROM Empleados e WHERE e.nombreCompleto = :nombreCompleto"),
    @NamedQuery(name = "Empleados.findByTelefono", query = "SELECT e FROM Empleados e WHERE e.telefono = :telefono")})
public class Empleados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<Historial> historialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "origen")
    private Collection<Incidencias> incidenciasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destino")
    private Collection<Incidencias> incidenciasCollection1;

    public Empleados() {
    }

    public Empleados(String username) {
        this.username = username;
    }

    public Empleados(String username, String password, String nombreCompleto, String telefono) {
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public Collection<Historial> getHistorialCollection() {
        return historialCollection;
    }

    public void setHistorialCollection(Collection<Historial> historialCollection) {
        this.historialCollection = historialCollection;
    }

    @XmlTransient
    public Collection<Incidencias> getIncidenciasCollection() {
        return incidenciasCollection;
    }

    public void setIncidenciasCollection(Collection<Incidencias> incidenciasCollection) {
        this.incidenciasCollection = incidenciasCollection;
    }

    @XmlTransient
    public Collection<Incidencias> getIncidenciasCollection1() {
        return incidenciasCollection1;
    }

    public void setIncidenciasCollection1(Collection<Incidencias> incidenciasCollection1) {
        this.incidenciasCollection1 = incidenciasCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empleado[ username=" + username + " ]";
    }
    
}
