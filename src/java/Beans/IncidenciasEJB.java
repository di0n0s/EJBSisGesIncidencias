/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Empleados;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author sfcar
 */
@Stateless
public class IncidenciasEJB {
    
    @PersistenceUnit EntityManagerFactory emf;

    public List<Empleados> getAllEmpleados(){
        //List<Empleado> allEmpleados = emf.createEntityManager().createNamedQuery("Empleados.findAll").getResultList();
        //return allEmpleados;
        return emf.createEntityManager().createNamedQuery("Empleados.findAll").getResultList();
    }
    
    public void insertEmpleado(Empleados e){
        EntityManager em = emf.createEntityManager();
        if(!existeEmpleado(e.getUsername())){
            em.persist(e);
            em.flush(); //En principio no hace falta poque ya lo hace el close()
            em.close();            
        }

    }
    
    public boolean existeEmpleado (String username){
        EntityManager em = emf.createEntityManager();
        Empleados e = em.find(Empleados.class, username);
        em.close();
        return e != null;
    }
}
