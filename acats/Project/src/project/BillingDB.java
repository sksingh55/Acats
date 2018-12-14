
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
public class BillingDB {
    
    public static void insertIntoBillingDB(String username, int price, String date) throws ClassNotFoundException{
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                            
            
            Statement ps = con.createStatement();
            System.out.println("succesful statement");
                    

            ps.executeUpdate("INSERT INTO billing(uname, bill, date) VALUES('"+username+"','"+price+"','"+date+"')");

        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
    public static ArrayList<BillObject> billlings() throws ClassNotFoundException{
        ArrayList<BillObject> customers = new ArrayList<>();
      
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
                            
            Statement ps = con.createStatement();
            System.out.println("succesful statement");
            ResultSet rs = ps.executeQuery("SELECT id, uname, bill, date FROM billing");
            
            BillObject pl;
            
            while(rs.next()){
                pl = new BillObject(rs.getInt("id"),rs.getString("uname"),
                        rs.getInt("bill"),rs.getString("date"));
                customers.add(pl);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }
    
    //Deleting billing log
    public static void deleteBillings() throws ClassNotFoundException{
         try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","sksingh55");
            System.out.println("succesful connection");
            Statement ps = con.createStatement();
            System.out.println("succesful statement");
            String qt = "DELETE FROM billing";
            ps.executeUpdate(qt);
            JOptionPane.showMessageDialog(null, "All entries have been deleted!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
