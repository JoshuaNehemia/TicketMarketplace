package DAO;

import DAO.Connection.DatabaseConnection;
import Entities.Account.Seller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joshu
 */
public class DAO_Seller {

        public static Seller Select_Seller(String username) throws Exception {
         String SQLQuery = "SELECT * FROM sellers WHERE username=?";
         PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
         prst.setString(1, username);

         ResultSet rslt = prst.executeQuery();

         if (rslt.next()) {
             System.out.println("Ditemukan seller: " + rslt.getString("username"));
             return new Seller(
                 rslt.getString("username"),
                 rslt.getString("password"),
                 rslt.getString("companyName"),
                 rslt.getString("email"),
                 rslt.getString("phoneNumber"),
                 rslt.getString("companyAddress")
             );
         }

         return null; 
     }

   public static int Insert_Seller(Seller _user) throws Exception {
    String SQLQuery = "INSERT INTO sellers (username, password, email, companyName, companyAddress, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);

    prst.setString(1, _user.getUsername());
    prst.setString(2, _user.getPassword());
    prst.setString(3, _user.getEmail());
    prst.setString(4, _user.getCompanyName());
    prst.setString(5, _user.getCompanyAddress());
    prst.setString(6, _user.getPhoneNumber());

    int num = prst.executeUpdate();
    prst.close();

    return num;
}


    public static int Update_Seller(Seller _user) throws Exception {
        String SQLQuery = "UPDATE FROM sellers SET password=?,email=?,companyName=?,companyAddress=?,phoneNumber=? WHERE username=? ";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(6, _user.getUsername());
        prst.setString(1, _user.getPassword());
        prst.setString(2, _user.getEmail());
        prst.setString(3, _user.getCompanyName());
        prst.setString(4, _user.getCompanyAddress());
        prst.setString(5, _user.getPhoneNumber());

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
    }

    public int Delete_Seller(String username, String password) throws Exception {
        String SQLQuery = "DELETE FROM sellers WHERE username=? and password=?";        
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, username);
        prst.setString(2, password);

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
    }

}
