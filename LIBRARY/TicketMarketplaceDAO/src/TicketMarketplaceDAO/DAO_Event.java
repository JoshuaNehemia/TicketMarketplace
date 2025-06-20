package TicketMarketplaceDAO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Entities.Event;
import Entities.Venue;
import Entities.Account.Seller;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class DAO_Event extends DatabaseConnection {

    public DAO_Event() throws Exception {
        super();
        System.out.println("DAO_EVENT IS CONNECTED");
    }
    
    public ArrayList<Event> Select_Event_By_Seller(Seller seller) throws Exception {
        ArrayList<Event> events = new ArrayList<Event>();

        String SQLQuery = "SELECT * FROM events WHERE seller_username=?;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, seller.getUsername());

        this.Read();

        Event buffer;
        while (this.getResult().next()) {
            buffer = new Event(this.getResult().getInt("id"),
            this.getResult().getString("name"),
            this.getResult().getString("description"),
            this.getResult().getTimestamp("startTime").toLocalDateTime(),
            new Venue(),
            seller
            );
            
            events.add(buffer);
        }
        return events;
    }
        public ArrayList<Event> Select_Event_By_CompanyName(String CompanyName) throws Exception {
        ArrayList<Event> events = new ArrayList<Event>();

        String SQLQuery = "SELECT * FROM events WHERE seller_username=?;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, CompanyName);

        this.Read();

        Event buffer;
        while (this.getResult().next()) {
            buffer = new Event(this.getResult().getInt("id"),
            this.getResult().getString("name"),
            this.getResult().getString("description"),
            this.getResult().getTimestamp("startTime").toLocalDateTime(),
            new Venue(),
            new Seller()
            );
            
            events.add(buffer);
        }
        return events;
    }
        public ArrayList<Event> Select_Event_By_Name(String name) throws Exception {
        ArrayList<Event> events = new ArrayList<Event>();

        String SQLQuery = "SELECT * FROM events WHERE seller_username=?;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, seller.getUsername());

        this.Read();

        Event buffer;
        while (this.getResult().next()) {
            buffer = new Event(this.getResult().getInt("id"),
            this.getResult().getString("name"),
            this.getResult().getString("description"),
            this.getResult().getTimestamp("startTime").toLocalDateTime(),
            new Venue(),
            seller
            );
            
            events.add(buffer);
        }
        return events;
    }

    public void Insert_Events(Event _event) throws Exception {
        String SQLQuery = "INSERT INTO Events (name, description, startDateTime, venue_id, seller_id) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, _event.getName());
        prst.setString(2, _event.getDescription());
        prst.setString(3, _event.getStartDateTime().toString());
        prst.setString(4, String.valueOf(_event.getVenue().getId()));
        prst.setString(5, String.valueOf(_event.getSeller().getUsername()));

        this.Create(String.valueOf(prst));
    }

    public void Update_Event(Event _event) throws Exception {
        String SQLQuery = "UPDATE Events SET name = ?, description = ?, startDateTime = ?, venue_id = ?, seller_id = ? WHERE id = ?;";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, _event.getName());;
        prst.setString(2, _event.getDescription());;
        prst.setString(3, _event.getStartDateTime().toString());;
        prst.setString(4, String.valueOf(_event.getVenue().getId()));
        prst.setString(5, String.valueOf(_event.getSeller().getUsername()));
        

        this.Update(String.valueOf(prst));
    }

    public void Delete_Venue(int id) throws Exception {
        String SQLQuery = "DELETE FROM events WHERE id=? AND address=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, String.valueOf(id));

        this.Delete(String.valueOf(prst));
    }
}
