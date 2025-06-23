package DAO;

import DAO.Connection.DatabaseConnection;
import Entities.EventClass;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joshu
 */
public class DAO_EventClass extends DatabaseConnection {

    public DAO_EventClass() throws Exception {
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

        public EventClass Select_EventClass_By_Id(int id) throws Exception {
        ArrayList<EventClass> events = new ArrayList<>();

        String SQLQuery = "SELECT * FROM `eventclassess` WHERE `id` = ?;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, String.valueOf(id));

        this.Read();

        EventClass buffer = new EventClass();
        if (this.getResult().next()) {
            buffer = new EventClass(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    this.getResult().getDouble("price"),
                    this.getResult().getString("description"),
                    this.getResult().getInt("stock"),
                    this.getResult().getInt("availableStock")
            );
        }

        return buffer;
    }
    
    public void Insert_EventClass(int event_id, EventClass _ec) throws Exception {
        String SQLQuery = "INSERT INTO `ticketmarketplace`.`eventClasses` (`event_id`, `name`, `price`, `description`, `stock`, `availableStock`) VALUES(?,?,?,?,?,?);";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));

        this.getPreparedStatement().setString(1, String.valueOf(event_id));
        this.getPreparedStatement().setString(2, _ec.getName());
        this.getPreparedStatement().setDouble(3, _ec.getPrice());
        this.getPreparedStatement().setString(4, _ec.getDescription());
        this.getPreparedStatement().setString(5, String.valueOf(_ec.getStock()));
        this.getPreparedStatement().setString(6, String.valueOf(_ec.getAvailableStock()));

        this.Create();
    }

    public void Update_EventClass(EventClass _ec) throws Exception {
        String SQLQuery = "UPDATE `ticketmarketplace`.`eventClasses` SET `name`=?, `price`=?, `description`=?, `stock`=?, `availableStock`=? WHERE `id`=?;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));

        this.getPreparedStatement().setString(1, _ec.getName());
        this.getPreparedStatement().setDouble(2, _ec.getPrice());
        this.getPreparedStatement().setString(3, _ec.getDescription());
        this.getPreparedStatement().setString(4, String.valueOf(_ec.getStock()));
        this.getPreparedStatement().setString(5, String.valueOf(_ec.getAvailableStock()));
        this.getPreparedStatement().setString(6, String.valueOf(_ec.getId()));

        this.Create();
    }
    
    public void Update_EventClass_Stock(int id) throws Exception
    {   
        String SQLQuery = "UPDATE `ticketmarketplace`.`eventClasses` SET `availableStock` = `availableStock` - 1 WHERE `id`=?;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));

        this.getPreparedStatement().setString(1, String.valueOf(id));

        this.Create();
    }

    public void Delete_Event_Class(int eventClassId) throws Exception {
        String SQLQuery = "DELETE FROM event_classes WHERE id = ?;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1,String.valueOf(eventClassId));
        
        this.Delete();
    }
}
