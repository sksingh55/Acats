/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JFrame;

//import javax.swing.JPanel;

/**
 *
 * @author Void
 */
public class DbConnector {

    public static int insertToUser(String username, String name, String email, String password, String card) {
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                            
            
            Statement ps = con.createStatement();
            System.out.println("succesful statement");
            String sql = "INSERT INTO userinfo (username, name, email, password, card) VALUES('"+username+"','"+name+"','"+email+"','"+password+"','"+card+"')";
         
            ps.executeUpdate(sql);
            System.out.println("succesful execution");
            System.out.println("succesful execution");
            //JOptionPane.showMessageDialog(null, "Signing up successful!");
            JFrame notification = new NotificationForm();
            
            
        } catch (SQLException e) {
            if(e.getErrorCode()==19) //Duplicate username
                return 19;
            
            JFrame errorOc = new ErrorOccured();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;

    }
    
    public static boolean signer(String username, String pass) throws ClassNotFoundException{
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                            
            
            Statement ps = con.createStatement();
            System.out.println("succesful statement");
            String check = "SELECT username, password FROM userinfo WHERE username='"+username+"' AND password='"+pass+"'";
            ResultSet rs = ps.executeQuery(check);
            
            return rs.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static void passRecovery(String username) throws MessagingException, ClassNotFoundException{
        String[] query= new String[2];
        
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                            
            
            Statement ps = con.createStatement();
            System.out.println("succesful statement");
            String check = "SELECT username, email, password FROM userinfo WHERE username='"+username+"'";
           
            ResultSet rs = ps.executeQuery(check);
            
           if(rs.next()){
                query[0]=rs.getString("email");
                query[1]=rs.getString("password");
                
                
                 String DATA=Email.sendMail("sksinjghcoding@gmail.com","9709919055",query[0],"Password Recovery","Your password: "+query[1]);
        //System.out.println(DATA+"vdfvbdvijdbv");
                NotificationForm nf = new NotificationForm();
                if(!DATA.equals("true"))
                    nf.jLabel7.setText(DATA);
                else
                    nf.jLabel7.setText("An email has been sent to "+query[0]);
               
           }
           else{
               ErrorOccured foo = new ErrorOccured();
               foo.errorMessage.setText("Username not found");
           }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
