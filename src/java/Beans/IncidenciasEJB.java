/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Empleados;
import Entities.Historial;
import Entities.Incidencias;
import Entities.Ranking;
import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author sfcar
 */
@Stateless
public class IncidenciasEJB {
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
    Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
    String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
    
    @PersistenceUnit EntityManagerFactory emf;

//    public List<Empleados> getAllEmpleados(){
//        //List<Empleado> allEmpleados = emf.createEntityManager().createNamedQuery("Empleados.findAll").getResultList();
//        //return allEmpleados;
//        return emf.createEntityManager().createNamedQuery("Empleados.findAll").getResultList();
//    }
    
    //3A. Insertar un empleado nuevo en la B.D. (FUNCIONA)
    public void insertEmpleado(Empleados e){
        EntityManager em = emf.createEntityManager();
        em.persist(e);
        //em.flush(); //En principio no hace falta poque ya lo hace el close()
        em.close();                   
    }
    
    //3B. Validar la entrada de un empleado (suministrando usuario y contraseña) (FUNCIONA CON SUBMÉTODO)
    public boolean validarEmpleado(Empleados e){
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT e FROM Empleados e WHERE e.username = :username AND e.password = :password");
        consulta.setParameter("username", e.getUsername());
        consulta.setParameter("password", e.getPassword());
        Object e1 = consulta.getSingleResult();
        if(e1!=null){
            Historial h = new Historial(0,"I", fechaHoraenTexto, e);
            em.persist(h);
            return true;
        }
        em.close();
        return false;
    }
    
    //3C. Modificar el perfil de un empleado existente. (FUNCIONA)
    public boolean modificarEmplado(Empleados e){
        EntityManager em = emf.createEntityManager();
        Empleados e1 = em.find(Empleados.class, e.getUsername());
        if (e1 != null){
            e1.setPassword(e.getPassword());
            e1.setNombreCompleto(e.getNombreCompleto());
            e1.setTelefono(e.getTelefono());
            em.persist(e1);
        }
        em.close();
        return true;
    }
    
    //3D. Cambiar la contraseña de un empleado existente.
    public boolean modificarPassword(Empleados e){
        EntityManager em = emf.createEntityManager();
        Empleados e1 = em.find(Empleados.class, e.getUsername());
        if (e1 != null){
            e1.setPassword(e.getPassword());
            em.persist(e1);
        }
        em.close();
        return true;
    }
    
    //3E. Eliminar Empleado (FUNCIONA)
    public boolean eliminarEmpleado(Empleados e){
        EntityManager em = emf.createEntityManager();
        Empleados e1 = em.find(Empleados.class, e.getUsername());
        if(e1!=null){
            em.remove(e1);
        }
        em.close();
        return true;
    }
    
    //4A. Obtener un objeto Incidencia a partir de su Id (FUNCIONA)
    public Object getIncidenciaByID(Incidencias i){
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createNamedQuery("Incidencias.findByIdIncidencia");
        consulta.setParameter("idIncidencia", i.getIdIncidencia());
        Object i1 = consulta.getSingleResult();
        return i1;
    }
    
    //4B. Obtener la lista de todas las incidencias. (FUNCIONA)
    public List<Incidencias> getAllIncidencias(){
        return emf.createEntityManager().createNamedQuery("Incidencias.findAll").getResultList();
    }
    
    //4C. Insertar una incidencia a partir de un objeto de clase Incidencia definido adecuadamente según los campos que presenta (incluido el empleado que la origina y el empleado destino).  (FUNCIONA CON SUBMÉTODO)
    public boolean insertIncidencia(Incidencias i){
        EntityManager em = emf.createEntityManager();
        em.persist(i);
        if("Urgente".equals(i.getTipo())){
            Historial h = new Historial(0,"U", fechaHoraenTexto, i.getOrigen());
            em.persist(h);            
        }
        em.close();
        return true;
    }

    //4D. Obtener las incidencias para un empleado a partir de un objeto de clase Empleado. (FUNCIONA CON SUBMÉTODO)
    public List<Incidencias> getAllIncidenciasParaEmpleado(Empleados e){
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT i FROM Incidencias i WHERE i.destino =:destino");
        consulta.setParameter("destino", e);
        List<Incidencias> allIncidencias = consulta.getResultList();
        Historial h = new Historial(0,"C", fechaHoraenTexto, e);
        em.persist(h);                  
        em.close();
        return allIncidencias;
    }    
    
    //4E. Obtener las incidencias creadas por un empleado concreto. (FUNCIONA)
    public List<Incidencias> getAllIncidenciasByEmpleado(Empleados e){
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT i FROM Incidencias i WHERE i.origen =:origen");
        consulta.setParameter("origen", e);
        List<Incidencias> allIncidencias = consulta.getResultList();
        em.close();
        return allIncidencias;
    }
    
    //5B. Obtener la fecha-hora del último inicio de sesión para un empleado concreto. (FUNCIONA)
    public Historial getLastLogin(Empleados e){
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT h FROM Historial h WHERE h.username = :username ORDER BY h.fechaHora DESC");
        consulta.setParameter("username", e);
        List<Historial> allLogins = consulta.getResultList();
        Historial h = allLogins.get(0);
        em.close();
        return h;
    }
    
    //5C. Obtener el ranking de los empleados por cantidad de incidencias urgentes creadas (más incidencias primero).
    public List<Ranking> getRanking(){
        List<Ranking> ranking = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT h FROM Historial h WHERE h.tipoEvento = :tipoEvento");
        consulta.setParameter("tipoEvento", "U");
        List<Historial> allHistorial = consulta.getResultList();
        for(Historial historialActual : allHistorial){
            Ranking r = new Ranking(historialActual.getUsername().getUsername(), 1);
            if(ranking.contains(r)){
                Ranking actual = ranking.get(ranking.indexOf(r));
                actual.setNumeroUrgencias(actual.getNumeroUrgencias()+1);
            } else {                
                ranking.add(r);
            }
        }
        Collections.sort(ranking, Collections.reverseOrder());
        em.close();
        return ranking;
    }
    
    //5D. Obtener la posición dentro del ranking para un empleado concreto.
    public int posicionRanking(Empleados e){
        List<Ranking> ranking = getRanking();
        for(Ranking r : ranking){
            if(r.getUsername().equals(e.getUsername())){
                return ranking.indexOf(r)+1;
            }
        }
        return -1;
    }
    
//    public void eliminarEmpleadoParametrizado(Empleados e){
//        EntityManager em = emf.createEntityManager();
//        Query consulta = em.createQuery("delete from Empleados e where e.username = :username");
//        consulta.setParameter("username", e.getUsername());
//        consulta.executeUpdate();
//        em.close();
//    }
    
    public boolean existeEmpleado (String username){
        EntityManager em = emf.createEntityManager();
        Empleados e = em.find(Empleados.class, username);
        em.close();
        return e != null;
    }
    
   
     
}
