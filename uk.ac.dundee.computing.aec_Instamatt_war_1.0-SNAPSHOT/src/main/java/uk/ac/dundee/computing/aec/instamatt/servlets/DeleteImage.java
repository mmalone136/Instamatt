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
import uk.ac.dundee.computing.aec.instamatt.models.PicModel;

/**
 *
 * @author Matt
 */
@WebServlet(name = "DeleteImage", urlPatterns = {"/DeleteImage", "/DeleteImage/*"})
public class DeleteImage extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

        }
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
        out.println("Got to the delete");

        String user = (String) request.getParameter("user");
        String picid = (String) request.getParameter("picid");

        out.println("THIS IS THE USER : " + user);
        out.println("THIS IS THE PICID : " + picid);

        //PicModel tm = new PicModel();
        //out.println("WHAT IS HAPPENING?");
        //tm.deletePic(picid, user);
        
        
        out.println("Commence picture deletion");
        try (Session session = cluster.connect("instamatt")) {
            out.println("Connected to cluster");
            //Delete From pics WHERE picid = '';
            //Delete From userpiclist WHERE user = 'instamattuser' AND pic_added =
            
            UUID pic = null;
            pic = pic.fromString(picid);
            
            try{
            Date d = getTime(pic, user);
            
            out.println("DATETIMETHING" + d);
            
            //Remove from userpics
            PreparedStatement psUserPic = session.prepare("Delete From userpiclist WHERE user = ? AND pic_added =?");
            BoundStatement bsUserPic = new BoundStatement(psUserPic);
            session.execute(bsUserPic.bind(user, d));
            out.println("Removal from piclist success - maybe");
            //Deletes pic
            
            PreparedStatement psDeletePic = session.prepare("Delete From pics WHERE picid = ?");
            BoundStatement bsDeletePic = new BoundStatement(psDeletePic);
            session.execute(bsDeletePic.bind(pic));
            out.println("Removal of pic success");
            
            }catch(Exception e){
            out.println("Exception occurred during picture delete");
            }
        }


        out.println("IS IT DONE?");

        
        //RequestDispatcher rd = request.getRequestDispatcher("../instamatt");
        //rd.forward(request, response);
        response.sendRedirect("../instamatt/index.jsp");
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
   public Date getTime(UUID picid, String user) {

        out.println("get da date");
        Date newDate = null;
        //newDate = null;

        Session session = cluster.connect("instamatt");
        out.println("CONNECTED");
        PreparedStatement ps = session.prepare("select pic_added,picid from userpiclist where user =?");
        out.println("PS DONE");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        out.println("BS DONE");
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        user));
        out.println("EXECUTED");

        if (rs.isExhausted()) {
            System.out.println("No date returned");
            return null;
        } else {
            Date d = new Date();
            for (Row row : rs) {
                UUID id = row.getUUID("picid");
                out.println("====================");
                out.println("Compare ID | " + id);
                out.println("Compare PICID | " + picid);
                out.println("====================");
                if (id.equals(picid)) {
                    out.println("MATCH ID | PICID");
                    out.println("MATCH ID | " + id);
                    out.println("MATCH PICID | " + picid);
                    java.util.UUID UUID = row.getUUID("picid");
                    d = row.getDate("pic_added");
                    out.println("pic_added   =====  " + d);
                }
            }
            newDate = d;
        }
        out.println("done apparently");
        return newDate;
    }
}
