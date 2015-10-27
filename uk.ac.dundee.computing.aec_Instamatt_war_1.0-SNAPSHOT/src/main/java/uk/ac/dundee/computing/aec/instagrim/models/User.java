/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instamatt.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import uk.ac.dundee.computing.aec.instamatt.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instamatt.stores.Pic;

import java.util.Set;
import java.util.UUID;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instamatt.stores.LoggedIn;

/**
 *
 * @author Administrator
 */

public class User {
    Cluster cluster;
    public User(){
        
    }
    
    public boolean RegisterUser(String username, String Password, String first_name,String last_name,Set<String> email){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();            
        
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        //UUID profpic=null;
        
        
        Session session = cluster.connect("instamatt");
        
        


        //PreparedStatement ps = session.prepare("insert into userprofiles (login,password) Values(?,?)");
       
        PreparedStatement ps = session.prepare("insert into userprofiles (login,password,first_name,last_name,email) Values(?,?,?,?,?) IF NOT EXISTS");
        
        BoundStatement boundStatement = new BoundStatement(ps);
        
        try{
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username,EncodedPassword,first_name,last_name,email));
        }
        catch(Exception e)
        {
            out.println("error during query execution");
        }
        
        
        return true;
    }
    
    
    public void setProfPicDefault(){

    }
    
    public boolean IsValidUser(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instamatt");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
   
    
    return false;  
    }
    
    
        public String getDataForUser(String Username) {
        //public String [] getDataForUser(String Username) {
        //ArrayList<String> userData = new ArrayList<String>();
        Session session = cluster.connect("instamatt");
        PreparedStatement ps = session.prepare("select * FROM userprofiles WHERE login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        Username));
        
        
        if (rs.isExhausted()) {
            System.out.println("No Datas returned");
            return null;
        } else {
            for (Row row : rs) {
                //Pic pic = new Pic();
                String x;
                //StoredData[3] = row.getString("first_name");
                //java.util.UUID UUID = row.getUUID("picid");
                x = row.getString("first_name");
                          
                //userData.add(x);
                return x;
            }
        }
        //String [] data = userData.toArray(new String[userData.size()]);
               
        //return data;
        return "FAILED";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
}
