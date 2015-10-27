/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instamatt.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.IOException;
import static java.lang.System.out;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instamatt.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instamatt.stores.Pic;

/**
 *
 * @author Matt
 */
//
@WebServlet(name = "Profile", urlPatterns = {"/profile", "/profile/*"})

//@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Profile extends HttpServlet {

    Cluster cluster;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 //String url = request.getRequestURL().toString();
        
        String Username = request.getParameter("username");

       // out.println("====================== " + Username);
        
        //String name = (String) request.
                //getAttribute("username");

        //out.println("---------------------- " + name);
        
        //Username = getUserFromUrl(url);
        Set<String> email = null;
        String first_name = "";
        String last_name = "";
        UUID profpic = null;

       
        Session session = cluster.connect("instamatt");
        
        PreparedStatement ps = session.prepare("select first_name,last_name,email,profpic FROM userprofiles WHERE login =?");
      ResultSet rs = null;
    
        BoundStatement boundStatement = new BoundStatement(ps);
       rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        Username));
       if (rs.isExhausted()) {
            out.println("this has been failed");

        } else {
            for (Row row : rs) {

                first_name = row.getString("first_name");
                last_name = row.getString("last_name");
                email = row.getSet("email", String.class);
                
                profpic = row.getUUID("profpic");
            }

            String[] newEmail = email.toArray(new String[email.size()]);
            String x = Arrays.toString(newEmail);

            int len = (x.length() - 1);
            x = x.substring(1, len);

            String profilepic;
            
            if(profpic!=null)
            {
                profilepic = profpic.toString();
            }else{
                profilepic = null;
            }
            
            
            request.setAttribute("first_name", first_name);
            request.setAttribute("last_name", last_name);
            request.setAttribute("email", x);
            request.setAttribute("profpic", profilepic);
        }
        request.setAttribute("username", Username);
        RequestDispatcher rd = request.getRequestDispatcher("/UserProfile.jsp");
        rd.forward(request, response);

    }

    private String getUserFromUrl(String url) {
        String user;
        user = url.substring(40);
        return user;
    }

}
