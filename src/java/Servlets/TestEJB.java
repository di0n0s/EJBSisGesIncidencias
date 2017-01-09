/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.IncidenciasEJB;
import Entities.Empleados;
import Entities.Historial;
import Entities.Incidencias;
import Entities.Ranking;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sfcar
 */
public class TestEJB extends HttpServlet {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
    Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
    String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
    
    @EJB IncidenciasEJB miGestor; 
    Empleados e1 = new Empleados("cbravo","mas254","Carlos Bravo Donosti","+34688454289");
    Empleados e2 = new Empleados("cbravo","ahf209","Carola Bravo Ferrán","+34695632111");
    Empleados e3 = new Empleados("amartinez");
    Empleados e4 = new Empleados("pzamora", "kna234", "Pedro Zamora Gutiérrez", "+34666547741");
    Incidencias i1 = new Incidencias(1);
    Incidencias i2 = new Incidencias(0, fechaHoraenTexto, "Urgente", "Ya queda menos para terminar la práctica", e4, e3);
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestEJB</title>");            
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Listado de Empleados</h1>");
//            List<Empleados> allEmpleados = miGestor.getAllEmpleados();
//            for (Empleados empleadoActual : allEmpleados){
//                out.println("<p>"+empleadoActual+"<p>");
//            }
            
            //3A. Insertar un empleado nuevo en la B.D.
            out.println("<h1>3A. Insertar un empleado nuevo en la B.D.</h1>");
            out.println("<p>Insertando usuario: "+e1.getUsername()+"...");
            if(miGestor.existeEmpleado(e1.getUsername())){
                out.println("<p>No se ha podido insertar "+e1.getUsername()+": Ya existe en la BBDD</p>");
            } else{
                miGestor.insertEmpleado(e1);
                out.println("<p>Empleado insertado!</p>");
            }
            out.println("<p>------------------------------------------</p>");
            
            //3B. Validar la entrada de un empleado (suministrando usuario y contraseña)
            out.println("<h1>3B. Validar la entrada de un empleado (suministrando usuario y contraseña)</h1>");
            out.println("<p>Validando usuario: "+e4.getUsername()+"...");
            if(miGestor.validarEmpleado(e4)){
                out.println("<p>Empleado Validado!</p>");
                out.println("<p>Se ha insertado un historial de inicio de sesión!</p>");
            } else out.println("<p>Empleado no Validado: Username y/o Password incorrectos</p>");
            out.println("<p>------------------------------------------</p>");
            
            //3C. Modificar el perfil de un empleado existente.
            out.println("<h1>3C. Modificar el perfil de un empleado existente.</h1>");
            out.println("<p>Modificando usuario: "+e2.getUsername()+"...");
            if(miGestor.modificarEmplado(e2)){
                out.println("<p>Empleado Modificado!</p>");                
            } else out.println("<p>Error: El Empleado no se ha modificado!</p>");
            out.println("<p>------------------------------------------</p>");
            
            //3D. Cambiar la contraseña de un empleado existente.
            out.println("<h1>3D. Cambiar la contraseña de un empleado existente.</h1>");
            out.println("<p>Modificando password de  usuario: "+e2.getUsername()+"...");
            if(miGestor.modificarPassword(e2)){
                out.println("<p>Password Modificado!</p>");                
            } else out.println("<p>Error: El Password no se ha modificado!</p>");
            out.println("<p>------------------------------------------</p>");
            
            //3E. Eliminar Empleado
            out.println("<h1>3E. Eliminar Empleado</h1>");
            out.println("<p>Eliminando usuario: "+e1.getUsername()+"...");
            if(miGestor.existeEmpleado(e1.getUsername())){
                miGestor.eliminarEmpleado(e1);
                out.println("<p>Usuario eliminado!</p>");
            } else{
                out.println("<p>Error: El usuario no ha podido eliminarse</p>");
            }
            out.println("<p>------------------------------------------</p>");
            
            //4A. Obtener un objeto Incidencia a partir de su Id
            out.println("<h1>4A. Obtener un objeto Incidencia a partir de su Id</h1>");
            out.println("<p>Obteniendo Incidencia número "+i1.getIdIncidencia()+"...");
            Object iByID = miGestor.getIncidenciaByID(i1);
            out.println("<p>"+iByID+"</p>");
            out.println("<p>------------------------------------------</p>");            
            
            //4B. Obtener la lista de todas las incidencias.
            out.println("<h1>4B. Obtener la lista de todas las incidencias.</h1>");
            out.println("<p>Obteniendo el listado de todas las incidencias...");
            List<Incidencias> allIncidencias = miGestor.getAllIncidencias();
            for (Incidencias incidenciaActual : allIncidencias){
                out.println("<p>"+incidenciaActual+"<p>");
            }            
            out.println("<p>------------------------------------------</p>");             
            
            //4C. Insertar una incidencia a partir de un objeto de clase Incidencia definido adecuadamente según los campos que presenta (incluido el empleado que la origina y el empleado destino).  
            out.println("<h1>4C. Insertar una incidencia a partir de un objeto de clase Incidencia definido adecuadamente según los campos que presenta (incluido el empleado que la origina y el empleado destino).</h1>");
            out.println("<p>Insertando incidencia...");
            if(miGestor.insertIncidencia(i2)){
                out.println("<p>Incidencia Insertada!</p>");
                if("Urgente".equals(i2.getTipo())){
                    out.println("<p>Se ha insertado en el historial una Incidencia Urgente!</p>");
                }
            } else{
                out.println("<p>Error: No ha podido insertarse la Incidencia!</p>");
            }
            out.println("<p>------------------------------------------</p>");            
            
            //4D. Obtener las incidencias para un empleado a partir de un objeto de clase Empleado.
            out.println("<h1>4D. Obtener las incidencias para un empleado a partir de un objeto de clase Empleado.</h1>");
            out.println("<p>Obteniendo el listado de todas las incidencias para el empleado: "+e3.getUsername()+"</p>");
            out.println("<p>Se ha insertado en el historial esta consulta de Incidencias para el empleado: "+e3.getUsername()+"!</p>");
            List<Incidencias> allIncidenciasPara = miGestor.getAllIncidenciasParaEmpleado(e3);
            for (Incidencias incidenciaActual : allIncidenciasPara){
                out.println("<p>"+incidenciaActual+"<p>");
            }            
            out.println("<p>------------------------------------------</p>");            
            

            //4E. Obtener las incidencias creadas por un empleado concreto.
            out.println("<h1>4E. Obtener las incidencias creadas por un empleado concreto.</h1>");
            out.println("<p>Obteniendo el listado de todas las incidencias creadas por el empleado: "+e3.getUsername()+"</p>");            
            List<Incidencias> allIncidenciasByEmpleado = miGestor.getAllIncidenciasByEmpleado(e3);
            for (Incidencias incidenciaActual : allIncidenciasByEmpleado){
                out.println("<p>"+incidenciaActual+"</p>");
            }
            out.println("<p>------------------------------------------</p>");            

            //5B. Obtener la fecha-hora del último inicio de sesión para un empleado concreto.
            out.println("<h1>5B. Obtener la fecha-hora del último inicio de sesión para un empleado concreto.</h1>");
            out.println("<p>Obteniendo el último login del empleado: "+e4.getUsername()+"</p>");            
            Historial login = miGestor.getLastLogin(e4);
            out.println("<p>"+login+"</p>");
            out.println("<p>------------------------------------------</p>");
            
            //5C. Obtener el ranking de los empleados por cantidad de incidencias urgentes creadas (más incidencias primero).
            out.println("<h1>5C. Obtener el ranking de los empleados por cantidad de incidencias urgentes creadas (más incidencias primero).</h1>");
            out.println("<p>Obteniendo el Ranking...</p>");            
            List<Ranking> ranking = miGestor.getRanking();
            for (Ranking posicionActual : ranking){
                int posicion = ranking.indexOf(posicionActual)+1;
                out.println("<p>Posición "+posicion+" --> "+posicionActual+"</p>");
            }
            out.println("<p>------------------------------------------</p>");            
            
            //5D. Obtener la posición dentro del ranking para un empleado concreto.
            out.println("<h1>5D. Obtener la posición dentro del ranking para un empleado concreto.</h1>");
            out.println("<p>Obteniendo la posición en el Ranking para el empleado "+e3.getUsername()+"...</p>");            
            int posicion = miGestor.posicionRanking(e3);
            out.println("<p>La Posición de "+e3.getUsername()+" es la "+posicion+"</p>");
            }
            out.println("<p>------------------------------------------</p>");               

            
            out.println("</body>");
            out.println("</html>");
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
