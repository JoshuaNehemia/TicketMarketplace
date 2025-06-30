/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Message;

import DAO.Connection.DatabaseConnection;
import Entities.Account.Seller;
import Entities.Event;
import Entities.Values.City;
import Entities.Venue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class DAO_Broadcast {
    public static ArrayList<String> Select_Broadcast() throws Exception {
        ArrayList<String> bc = new ArrayList<>();
        
        String SQLQuery = "SELECT * FROM broadcasts AS 'bc' ORDER BY `bc`.`created_time` DESC";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));

        ResultSet rslt = prst.executeQuery();

        while (rslt.next()) {
            String message = rslt.getString("message");
            bc.add(message);
        }
        return bc;
    }
    public static int Insert_Broadcast(String message) throws Exception {
        String SQLQuery = "INSERT INTO broadcast (message) VALUES (?);";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));

        int num = prst.executeUpdate();
        prst.close();
        
        return num;
    }
            
}
