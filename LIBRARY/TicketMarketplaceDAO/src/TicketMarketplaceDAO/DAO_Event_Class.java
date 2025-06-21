package TicketMarketplaceDAO;

import Entities.EventClass;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joshu
 */
public class DAO_Event_Class extends DatabaseConnection {

    public DAO_Event_Class() throws Exception {
        super();
        System.out.println("DAO_EVENT_CLASS IS CONNECTED");
    }

    public ArrayList<EventClass> Select_EventClass_By_Event_Id(int event_id) throws Exception {
        ArrayList<EventClass> events = new ArrayList<>();

        String SQLQuery = "SELECT * FROM `eventclassess` WHERE `event_id` = ?;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, String.valueOf(event_id));
        
        this.Read();

        EventClass buffer;
        while (this.getResult().next()) {
            buffer = new EventClass(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    this.getResult().getDouble("price"),
                    this.getResult().getString("description"),
                    this.getResult().getInt("stock"),
                    this.getResult().getInt("availableStock")
            );

            events.add(buffer);
        }
        
        return events;
    }

    public void Update_EventClass(EventClass _ec) throws Exception {
        if (_ec.getId() == 0) {
            throw new IllegalArgumentException("Event_class ID must be set for update operation.");
        }

        String SQLQuery = "UPDATE event_classes SET name = ?, price = ?, description = ?, num_rows = ?, num_cols = ?, stock = ? WHERE id = ?;";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);

        prst.setString(1, _ec.getName());
        prst.setDouble(2, _ec.getPrice());
        prst.setString(3, _ec.getDescription());
        prst.setInt(4, _ec.getNumRows());
        prst.setInt(5, _ec.getNumCols());
        prst.setInt(6, _ec.getStock());
        prst.setInt(7, _ec.getId());

        int rowsAffected = prst.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Event_class with ID " + _ec.getId() + " successfully updated.");
        } else {
            throw new Exception("No Event_class found with ID " + _ec.getId() + ". Nothing Updated.");
        }
        prst.close();
    }

    public void Delete_Event_Class(int eventClassId) throws Exception {
        if (eventClassId <= 0) {
            throw new IllegalArgumentException("A valid Event_class ID must be provided for deletion.");
        }

        String SQLQuery = "DELETE FROM event_classes WHERE id = ?;";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);

        prst.setInt(1, eventClassId);

        int rowsAffected = prst.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Event_class with ID " + eventClassId + " successfully deleted.");
        } else {
            throw new Exception("No Event_class found with ID " + eventClassId + ". Nothing deleted.");
        }
        prst.close();
    }
}
