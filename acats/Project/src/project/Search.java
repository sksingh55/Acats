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

/**
 *
 * @author Void
 */
public class Search {
    static int electronics = 0;
    static int mobile = 0;
    static int kids = 0;
    public static ArrayList<ProductList> mobileSearch(String model){
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
           Statement ps = con.createStatement();

            ResultSet rs = ps.executeQuery("SELECT * FROM electronics WHERE mbrand='"+model+"';");
            
            ProductList pl, gl, kl=null;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        rs.getInt("mprice"),rs.getInt("mquantity"),rs.getString("mdescription"),
                        rs.getString("mphoto"));
                electronics++;
                
                list.add(pl);

            }
            con.close();
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
            ps = con.createStatement();
            
            rs = ps.executeQuery("SELECT * FROM mobiles WHERE mbrand='"+model+"';");

            
            while(rs.next()){
                gl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        rs.getInt("mprice"),rs.getInt("mquantity"),rs.getString("mdescription"),
                        rs.getString("mphoto"));
                mobile++;
                list.add(gl);

            }
            con.close();
            
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
            ps = con.createStatement();
            
            rs = ps.executeQuery("SELECT * FROM kids WHERE mbrand='"+model+"';");;

            
            while(rs.next()){
                kl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        rs.getInt("mprice"),rs.getInt("mquantity"),rs.getString("mdescription"),
                        rs.getString("mphoto"));
                kids++;
                list.add(kl);

            }
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
    
}
