package TicketMarketplaceDAO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Communication.InteractiveIO;
import TicketMarketplaceEntities.Event;
import TicketMarketplaceEntities.Venue;
import TicketMarketplaceEntities.Seller;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class DAO_Event extends DatabaseConnection {

    /*
    public static ArrayList<Event> Select_Event_By_Seller_Username(String seller_username,ArrayList<Event> list)
    {
        ArrayList<Event> seller_list = new ArrayList<Event>();
        for(Event ev : list)
        {
            if(ev.getSeller().equals(seller_username))
            {
                seller_list.add(ev);
            }
        }
        return seller_list;
    }
    
    public static ArrayList<Event> Insert_Event(Event event,ArrayList<Event> list)
    {
        list.add(event);
        
        return list;
    }
    
    public static ArrayList<Event> Update_Event_By_Id(Event event,ArrayList<Event> list)
    {
        for(Event ev : list)
        {
            if(ev.getId() ==event.getId())
            {
                list.remove(ev);
                list.add(event);
            }
        }
        return list;
    }
     */

    public DAO_Event() throws Exception {
        super();
        System.out.println(InteractiveIO.GreenMessage("DAO_EVENT IS CONNECTED"));
    }

    public ArrayList<Event> Select_Event_By_Seller(String seller_username) throws Exception {
        ArrayList<Event> events = new ArrayList<Event>();

        String SQLQuery = "SELECT * FROM events WHERE seller_username=?;";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, seller_username);

        System.out.println("SQL QUERY: \n" + prst);
        this.setResult(this.Read(String.valueOf(prst)));

        Event buffer;
        while (this.getResult().next()) {
            Venue nVenue = new Venue();
            nVenue.setId(this.getResult().getInt("venue_id"));
            Seller nSeller = new Seller();
            nSeller.setUsername(this.getResult().getString("seller_username"));
            buffer = new Event(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    this.getResult().getString("description"),
                    this.getResult().getTimestamp("startDateTime").toLocalDateTime().toLocalDate(),
                    nVenue,
                    nSeller
            );
            
            events.add(buffer);
        }
        if (events.size() > 0) {
            return events;
        } else {
            throw new Exception("Failure in receiving events data from database - no data matches the parameter");
        }

    }

   public ArrayList<Event> Select_Event_By_Name(String name) throws Exception {
        ArrayList<Event> events = new ArrayList<Event>();

        String SQLQuery = "SELECT * FROM events WHERE name like %?%;";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, name);

        System.out.println("SQL QUERY: \n" + prst);
        this.setResult(this.Read(String.valueOf(prst)));

        Event buffer;
        while (this.getResult().next()) {
            Venue nVenue = new Venue();
            nVenue.setId(this.getResult().getInt("venue_id"));
            Seller nSeller = new Seller();
            nSeller.setUsername(this.getResult().getString("seller_username"));
            buffer = new Event(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    this.getResult().getString("description"),
                    this.getResult().getTimestamp("startDateTime").toLocalDateTime().toLocalDate(),
                    nVenue,
                    nSeller
            );
            
            events.add(buffer);
        }
        if (events.size() > 0) {
            return events;
        } else {
            throw new Exception("Failure in receiving events data from database - no data matches the parameter");
        }

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
