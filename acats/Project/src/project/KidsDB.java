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
public class KidsDB {
    public static boolean flag = false;
    public static void insertIntoKidsDB(String brand, String model, int price, int qty, String description, String imagePath) throws ClassNotFoundException{
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                           
            Statement ps = con.createStatement();
            System.out.println(" succesful statement");
            String query = "INSERT INTO kids(mbrand, mmodel, mprice,mquantity, mdescription, mphoto) VALUES ('"+brand+"','"+model+"',"+price+","+qty+",'"+description+"','"+imagePath+"');";
         
            
            if(ps.executeUpdate(query)==1)
                JOptionPane.showMessageDialog(null, "Entry successful!");
            
        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
    public static void updateKidsDB(String model, int qty) throws ClassNotFoundException{
         try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                           
            Statement ps = con.createStatement();
            System.out.println(" succesful statement");

            String query = "UPDATE kids SET mquantity="+qty+" WHERE mmodel='"+model+"';";
            int res = ps.executeUpdate(query);
            if(res==0)
                JOptionPane.showMessageDialog(null, "Entry does not exist!");
            else if(res==1 && flag){
                JOptionPane.showMessageDialog(null, "Stock updated successfully!");
                flag = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(KidsDB.class.getName()).log(Level.SEVERE, null, ex);

    }
    }
    
    public static ArrayList<ProductList> TableGenerator() throws ClassNotFoundException{
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                           
            Statement ps = con.createStatement();
            System.out.println(" succesful statement");
         
            ResultSet rs = ps.executeQuery("SELECT mbrand, mmodel, mprice,mquantity, mdescription, mphoto FROM kids");
            
            ProductList pl;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        rs.getInt("mprice"),rs.getInt("mquantity"),rs.getString("mdescription"),
                        rs.getString("mphoto"));
                
                list.add(pl);

            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
}
    public static ArrayList<ProductList> homePageContent() throws ClassNotFoundException{
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                           
            Statement ps = con.createStatement();
            System.out.println(" succesful statement");
            ResultSet rs = ps.executeQuery("SELECT mbrand, mmodel, mprice,mquantity, mdescription, mphoto FROM kids ORDER BY id DESC");
            
            ProductList pl;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        rs.getInt("mprice"),rs.getInt("mquantity"),rs.getString("mdescription"),
                        rs.getString("mphoto"));
                
                list.add(pl);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
    
    public static ArrayList<ProductList> checkStock() throws ClassNotFoundException{
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                           
            Statement ps = con.createStatement();
            System.out.println(" succesful statement");
            ResultSet rs = ps.executeQuery("SELECT mbrand, mmodel, mprice, mquantity FROM kids");
            
            ProductList pl;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        0, rs.getInt("mquantity"),null, null);
                
                list.add(pl);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
    
       public static void delete(String model) throws ClassNotFoundException{
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                           
            Statement ps = con.createStatement();
            System.out.println(" succesful statement");
            String query ="DELETE FROM kids WHERE mmodel=?";
            int res = ps.executeUpdate(query);
            if(res==0)
                JOptionPane.showMessageDialog(null, "Entry does not exist!");
            else
                JOptionPane.showMessageDialog(null, "Entry deleted successfully!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
   }
    
    
    
}
