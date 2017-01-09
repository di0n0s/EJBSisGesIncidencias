/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author sfcar
 */
public class Ranking implements Comparable<Ranking>{
    
    private String username;
    private int numeroUrgencias;

    public Ranking(String username, int numeroUrgencias) {
        this.username = username;
        this.numeroUrgencias = numeroUrgencias;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumeroUrgencias() {
        return numeroUrgencias;
    }

    public void setNumeroUrgencias(int numeroUrgencias) {
        this.numeroUrgencias = numeroUrgencias;
    }

    @Override
    public String toString() {
        return "Ranking{" + "username=" + username + ", numeroUrgencias=" + numeroUrgencias + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ranking other = (Ranking) obj;
        return Objects.equals(this.username, other.username);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.username);
        return hash;
    }
    @Override
    public int compareTo(Ranking o) {
        if (numeroUrgencias > o.getNumeroUrgencias()) {
            return 1;
        }
        if (numeroUrgencias < o.getNumeroUrgencias()) {
            return -1;
        } 
        return 0;
    }
    
    
}
