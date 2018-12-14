/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Void
 */
public class MobileDB {
    public static boolean flag = false;
    public static void insertIntoMobileDB(String brand, String model, int price, int qty, String description, String imagePath) throws ClassNotFoundException{
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                            
            
            Statement ps = con.createStatement();
            System.out.println("succesful statement");
         
           String qrty = "INSERT INTO mobiles(mbrand, mmodel, mprice,mquantity, mdescription, mphoto) VALUES('"+brand+"','"+model+"',"+price+","+qty+",'"+description+"','"+imagePath+"');";
         
            if(ps.executeUpdate(qrty)==1)
                JOptionPane.showMessageDialog(null, "Entry successful!");
            
        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
    public static void updateMobileDB(String model, int qty){
         try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                           
            Statement ps = con.createStatement();
            System.out.println(" succesful statement");
            String query = "UPDATE electronics SET mquantity="+qty+" WHERE mmodel='"+model+"';";
            int re = ps.executeUpdate(query);
            if(re==0)
                JOptionPane.showMessageDialog(null, "Entry does not exist!");
            else if(re==1 && flag){
                JOptionPane.showMessageDialog(null, "Stock updated successfully!");
                flag = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);

    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<ProductList> TableGenerator(){
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                           
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT mbrand, mmodel, mprice,mquantity, mdescription, mphoto FROM mobiles");
            
            ProductList pl;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        rs.getInt("mprice"),rs.getInt("mquantity"),rs.getString("mdescription"),
                        rs.getString("mphoto"));
                
                list.add(pl);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
}
    public static ArrayList<ProductList> homePageContent(){
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                           
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT mbrand, mmodel, mprice,mquantity, mdescription, mphoto FROM mobiles ORDER BY id DESC LIMIT 3");
            
            ProductList pl;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        rs.getInt("mprice"),rs.getInt("mquantity"),rs.getString("mdescription"),
                        rs.getString("mphoto"));
                
                list.add(pl);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
    
    public static ArrayList<ProductList> checkStock(){
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                           
            Statement ps = con.createStatement();
            
            ResultSet rs = ps.executeQuery("SELECT mbrand, mmodel, mprice, mquantity FROM mobiles");
            
            ProductList pl;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        0, rs.getInt("mquantity"),null, null);
                
                list.add(pl);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
    
       public static void delete(String model){
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                           
            Statement ps = con.createStatement();
            String query = "DELETE FROM mobiles WHERE mmodel='"+model+"';";

           if(ps.executeUpdate(query)==0)
                JOptionPane.showMessageDialog(null, "Entry does not exist!");
            else
                JOptionPane.showMessageDialog(null, "Entry deleted successfully!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicsDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
   }
    
    
    
}
