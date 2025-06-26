package DAO;

import DAO.Connection.DatabaseConnection;
import Entities.EventClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joshu
 */
public class DAO_EventClass {
    
        public static double CalculatePrice(int eventId, int eventClassId) throws Exception {
         double price = 0;
         String SQLQuery = "SELECT price FROM eventclasses WHERE id = ? AND event_id = ?";

         PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
         prst.setInt(1, eventClassId);
         prst.setInt(2, eventId);

         ResultSet rslt = prst.executeQuery();

         if (rslt.next()) {
             double basePrice = rslt.getDouble("price");
             price = basePrice + (0.007 * basePrice); 
         }

         prst.close();
         return price;
     }


    public static ArrayList<EventClass> Select_EventClass_By_Event_Id(int event_id) throws Exception {
        ArrayList<EventClass> events = new ArrayList<>();

        String SQLQuery = "SELECT * FROM `eventclasses` WHERE `event_id` = ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, String.valueOf(event_id));

        ResultSet rslt = prst.executeQuery();

        EventClass buffer;
        while (rslt.next()) {
            buffer = new EventClass(
                    rslt.getInt("id"),
                    rslt.getString("name"),
                    rslt.getDouble("price"),
                    rslt.getString("description"),
                    rslt.getInt("stock"),
                    rslt.getInt("availableStock")
            );

            events.add(buffer);
        }

        prst.close();

        return events;
    }

    public static EventClass Select_EventClass_By_Id(int id) throws Exception {
        ArrayList<EventClass> events = new ArrayList<>();

        String SQLQuery = "SELECT * FROM `eventclassess` WHERE `id` = ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, String.valueOf(id));

        ResultSet rslt = prst.executeQuery();

        EventClass buffer = new EventClass();
        if (rslt.next()) {
            buffer = new EventClass(
                    rslt.getInt("id"),
                    rslt.getString("name"),
                    rslt.getDouble("price"),
                    rslt.getString("description"),
                    rslt.getInt("stock"),
                    rslt.getInt("availableStock")
            );
        }

        prst.close();

        return buffer;
    }

    public static int Select_EventClass_Stock(int id) throws Exception {
        ArrayList<EventClass> events = new ArrayList<>();

        String SQLQuery = "SELECT stockAvailable FROM `eventclassess` WHERE `id` = ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, String.valueOf(id));

        ResultSet rslt = prst.executeQuery();

        int stock = 0;
        if (rslt.next()) {
            stock = rslt.getInt("stockAvailable");
        }

        prst.close();

        return stock;
    }

    public static int Insert_EventClass(int event_id, EventClass _ec) throws Exception {
        String SQLQuery = "INSERT INTO `ticketmarketplace`.`eventClasses` (`event_id`, `name`, `price`, `description`, `stock`, `availableStock`) VALUES(?,?,?,?,?,?);";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));

        prst.setString(1, String.valueOf(event_id));
        prst.setString(2, _ec.getName());
        prst.setDouble(3, _ec.getPrice());
        prst.setString(4, _ec.getDescription());
        prst.setString(5, String.valueOf(_ec.getStock()));
        prst.setString(6, String.valueOf(_ec.getAvailableStock()));

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
    }

    public static int Update_EventClass(EventClass _ec) throws Exception {
        String SQLQuery = "UPDATE `ticketmarketplace`.`eventClasses` SET `name`=?, `price`=?, `description`=?, `stock`=?, `availableStock`=? WHERE `id`=?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));

        prst.setString(1, _ec.getName());
        prst.setDouble(2, _ec.getPrice());
        prst.setString(3, _ec.getDescription());
        prst.setString(4, String.valueOf(_ec.getStock()));
        prst.setString(5, String.valueOf(_ec.getAvailableStock()));
        prst.setString(6, String.valueOf(_ec.getId()));

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
    }

    public static int Update_EventClass_Stock(int id) throws Exception {
        String SQLQuery = "UPDATE `ticketmarketplace`.`eventClasses` SET `availableStock` = `availableStock` - 1 WHERE `id`=? AND `availableStock` > 0;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));

        prst.setString(1, String.valueOf(id));

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
    }

    public static int Update_EventClass_Stock_Add(int id, int amount) throws Exception {
        String SQLQuery = "UPDATE `ticketmarketplace`.`eventClasses` SET `availableStock` = `availableStock` + ? WHERE `id`=? AND `availableStock` > 0;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));

        prst.setString(1, String.valueOf(amount));
        prst.setString(2, String.valueOf(id));

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
    }

    public static int Delete_Event_Class(int eventClassId) throws Exception {
        String SQLQuery = "DELETE FROM event_classes WHERE id = ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, String.valueOf(eventClassId));

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
    }
}
