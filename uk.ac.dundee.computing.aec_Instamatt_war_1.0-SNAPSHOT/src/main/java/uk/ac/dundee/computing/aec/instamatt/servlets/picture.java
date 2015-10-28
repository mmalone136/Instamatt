/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instamatt.servlets;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "picture", urlPatterns = {"/picture", "/picture/*"})
public class picture extends HttpServlet {

    private Cluster cluster;

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
       // response.setContentType("text/html;charset=UTF-8");
        //try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

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
               //processRequest(request, response);

        String picid = (String) request.getParameter("pic");
        String owner = (String) request.getParameter("owner");
        Session session = cluster.connect("instamatt");
        UUID THINGS = UUID.fromString(picid);
        PreparedStatement ps = session.prepare("select comment,userleft,timeleft FROM comments WHERE picid =? ALLOW FILTERING ");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        THINGS));

        ArrayList<String[]> comList = new ArrayList<String[]>();
        if (rs.isExhausted()) {
            out.println("this has been failed");
            request.setAttribute("found", "no");
        } else {
            request.setAttribute("found", "yes");
            comList = readDataBase(rs);

            request.setAttribute("ComList", comList);

        }

        //String url = request.getRequestURL().toString();
        //String pic = url.substring(40);
        request.setAttribute("owner",owner);
        String pic = picid;
        request.setAttribute("pic", pic);

        RequestDispatcher rd = request.getRequestDispatcher("/SinglePic.jsp");
        rd.forward(request, response);
    }

    protected ArrayList<String[]> readDataBase(ResultSet rs) {


        String comment = "";
        String user = "";
        Date time;

        ArrayList<String[]> comList = new ArrayList<String[]>();

        for (Row row : rs) {
            String[] toAdd = new String[3];

            comment = row.getString("comment");
            user = row.getString("userleft");
            time = row.getDate("timeleft");

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String x = df.format(time);

            toAdd[0] = comment;
            toAdd[1] = user;
            toAdd[2] = x;

            comList.add(toAdd);

        }

        return comList;
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

        String comment = (String) request.getParameter("comment");
        String owner = (String) request.getParameter("owner");
        

        out.println("COMMENT | " + comment);
        String userleft = (String) request.getParameter("user");
        out.println("USERLEFT | " + userleft);
        String picid = (String) request.getParameter("picid");
        out.println("PICID | " + picid);
        UUID pic = UUID.fromString(picid);
        out.println("PID-UUID | " + pic);

        if (comment != "") {
            Session session = cluster.connect("instamatt");

            out.println("STARTING TO DO THE THINGS ABOUT STUFF");

            PreparedStatement ps = session.prepare("insert into comments (timeleft,picid,comment,userleft) values(?,?,?,?)");
            BoundStatement bs = new BoundStatement(ps);

            Date DateAdded = new Date();
            out.println("DATE | " + DateAdded);

            //UUID timeadded = UUID.fromString(DateAdded); 
            out.println("ABOUT TO DO THE THINGS OF POSTING AND SUCH");
            session.execute(bs.bind(DateAdded, pic, comment, userleft));

            out.println("DONE THE THINGS OF POSTING AND SUCH");

            session.close();
        }
        request.setAttribute("pic", picid);
        out.println("PICID | " + picid);

        
        
        
        
        //String picid = (String) request.getParameter("pic");
        Session session = cluster.connect("instamatt");
        UUID picuuid = UUID.fromString(picid);
        PreparedStatement ps = session.prepare("select comment,userleft,timeleft FROM comments WHERE picid =? ALLOW FILTERING");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        picuuid));

        ArrayList<String[]> comList = new ArrayList<String[]>();
        if (rs.isExhausted()) {
            out.println("this has been failed");
            request.setAttribute("found", "no");
        } else {
            request.setAttribute("found", "yes");
            comList = readDataBase(rs);

            request.setAttribute("ComList", comList);

        }
        
        request.setAttribute("owner", owner);
        
        //RequestDispatcher rd = request.getRequestDispatcher("../picture/" + picid);
        RequestDispatcher rd = request.getRequestDispatcher("../SinglePic.jsp");
        rd.forward(request, response);
        //response.sendRedirect("../picture/" + picid);
        
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
