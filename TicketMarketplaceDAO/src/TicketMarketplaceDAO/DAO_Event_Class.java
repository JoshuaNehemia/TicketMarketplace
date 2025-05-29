package TicketMarketplaceDAO;

import Communication.InteractiveIO;
import TicketMarketplaceEntities.Event_class;
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
    public DAO_Event_Class() throws Exception
    {
        super();
        System.out.println(InteractiveIO.GreenMessage("DAO_EVENT_CLASS IS CONNECTED"));
    }
    
    public ArrayList<Event_class> Select_Event_Class_By_Event(int event_id) throws Exception
    {
                ArrayList<Event_class> events = new ArrayList<Event_class>();

        String SQLQuery = "SELECT * FROM event_classess WHERE event_id=?;";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, String.valueOf(event_id));

        System.out.println("SQL QUERY: \n" + prst);
        this.setResult(this.Read(String.valueOf(prst)));

        Event_class buffer;
        while (this.getResult().next()) {
            buffer = new Event_class(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    this.getResult().getDouble("price"),
                    this.getResult().getString("description"),
                    this.getResult().getInt("numRows"),
                    this.getResult().getInt("numCols"),
                    this.getResult().getInt("stock")
            );
            
            events.add(buffer);
        }
        if (events.size() > 0) {
            return events;
        } else {
            throw new Exception("Failure in receiving event_classes data from database - no data matches the parameter");
        }
    }
    public void Update_Event_Class(Event_class _ec) throws Exception {
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
