/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instamatt.servlets;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instamatt.lib.CassandraHosts;

/**
 *
 * @author Matt
 */
@WebServlet(name = "Update", urlPatterns = {"/update"})
public class Update extends HttpServlet {

    Cluster cluster = null;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

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

    }

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
        //processRequest(request, response);

        String username =  request.getParameter("user");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");

        //String toAdd = "";
        //String Values;

        
        if (first_name != "" && last_name != "" && email != "") {
          
        Set<String> x = new HashSet<String>(Arrays.asList(email));

        //System.out.println(first_name + " | " + last_name + " | " + email);

        Session session = cluster.connect("instamatt");
        
        
        String statement = ("update userprofiles set first_name = '" + first_name + "', last_name = '" + last_name + "', email = ? WHERE login = ?");        
        //out.println(statement);
        PreparedStatement ps = session.prepare(statement);

        BoundStatement boundStatement = new BoundStatement(ps);

        
        try{
                session.execute( // this is where the query is executed
                   boundStatement.bind( // here you are binding the 'boundStatement'
        x,username));
        //We are assuming this always works.  Also a transaction would be good here !
        }catch(Exception e){}

        response.sendRedirect("/instamatt");
        }else{
        
        out.println("FAILURES");
        response.sendRedirect("/UpdateDetails.jsp");
        }
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