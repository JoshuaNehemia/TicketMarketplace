package DAO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import DAO.Connection.DatabaseConnection;
import Entities.Event;
import Entities.Venue;
import Entities.Account.Seller;
import Entities.Format.Default;
import Entities.Values.City;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
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

    public ArrayList<Event> Select_Event_By_Seller(String seller_username) throws Exception {
        ArrayList<Event> events = new ArrayList<Event>();

        String SQLQuery = """
                          SELECT 
                            eve.*,
                            ven.`name` AS 'venue_name',
                            cit.`name` AS 'city_name',
                            ven.`address` AS 'venue_address',
                            sel.`companyName` AS 'seller_companyName',
                            sel.`email` AS 'seller_email',
                            sel.`phoneNumber` AS 'seller_phoneNumber'
                          FROM
                            `events` AS eve
                          INNER JOIN
                            `venues` AS ven
                          ON 
                            eve.`venue_id` = ven.`id`
                          INNER JOIN
                            `sellers` AS sel
                          ON 
                            eve.`seller` = sel.`username`
                          INNER JOIN
                            `cities` AS cit
                          ON 
                            ven.`city_id` = cit.`id`
                          WHERE
                          sel.`seller_usename` = ?;
                          """;
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, seller_username);

        this.Read();

        Event buffer;
        while (this.getResult().next()) {
            buffer = new Event(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    this.getResult().getString("description"),
                    LocalDateTime.parse(this.getResult().getString("startTime"), (Default.getDateTimeFormatter())),
                    new Venue(
                            this.getResult().getInt("id"),
                            this.getResult().getString("venue_name"),
                            new City(
                                    this.getResult().getInt("city_id"),
                                    this.getResult().getString("city_name")
                            ),
                            this.getResult().getString("venue_address")
                    ),
                    new Seller(
                            this.getResult().getString("seller_username"),
                            this.getResult().getString("seller_companyName"),
                            this.getResult().getString("seller_email"),
                            this.getResult().getString("seller_phoneNumber")
                    )
            );
            events.add(buffer);
        }
        return events;
    }

    public ArrayList<Event> Select_Event_By_Name(String name) throws Exception {
        ArrayList<Event> events = new ArrayList<Event>();

        String SQLQuery = """
                          SELECT 
                            eve.*,
                            ven.`name` AS 'venue_name',
                            cit.`name` AS 'city_name',
                            ven.`address` AS 'venue_address',
                            sel.`companyName` AS 'seller_companyName',
                            sel.`email` AS 'seller_email',
                            sel.`phoneNumber` AS 'seller_phoneNumber'
                          FROM
                            `events` AS eve
                          INNER JOIN
                            `venues` AS ven
                          ON 
                            eve.`venue_id` = ven.`id`
                          INNER JOIN
                            `sellers` AS sel
                          ON 
                            eve.`seller` = sel.`username`
                          INNER JOIN
                            `cities` AS cit
                          ON 
                            ven.`city_id` = cit.`id`
                          WHERE
                          eve.`name` LIKE ?;
                          """;
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1,"%"+ name + "%");

        this.Read();

        Event buffer;
        while (this.getResult().next()) {
            buffer = new Event(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    this.getResult().getString("description"),
                    LocalDateTime.parse(this.getResult().getString("startTime"), (Default.getDateTimeFormatter())),
                    new Venue(
                            this.getResult().getInt("id"),
                            this.getResult().getString("venue_name"),
                            new City(
                                    this.getResult().getInt("city_id"),
                                    this.getResult().getString("city_name")
                            ),
                            this.getResult().getString("venue_address")
                    ),
                    new Seller(
                            this.getResult().getString("seller_username"),
                            this.getResult().getString("seller_companyName"),
                            this.getResult().getString("seller_email"),
                            this.getResult().getString("seller_phoneNumber")
                    )
            );
            events.add(buffer);
        }
        return events;
    }
    public ArrayList<Event> Select_Event_By_CompanyName(String name) throws Exception {
        ArrayList<Event> events = new ArrayList<Event>();

        String SQLQuery = """
                          SELECT 
                            eve.*,
                            ven.`name` AS 'venue_name',
                            cit.`name` AS 'city_name',
                            ven.`address` AS 'venue_address',
                            sel.`companyName` AS 'seller_companyName',
                            sel.`email` AS 'seller_email',
                            sel.`phoneNumber` AS 'seller_phoneNumber'
                          FROM
                            `events` AS eve
                          INNER JOIN
                            `venues` AS ven
                          ON 
                            eve.`venue_id` = ven.`id`
                          INNER JOIN
                            `sellers` AS sel
                          ON 
                            eve.`seller` = sel.`username`
                          INNER JOIN
                            `cities` AS cit
                          ON 
                            ven.`city_id` = cit.`id`
                          WHERE
                          sel.`companyName` LIKE ?;
                          """;
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1,"%"+ name + "%");

        this.Read();

        Event buffer;
        while (this.getResult().next()) {
            buffer = new Event(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    this.getResult().getString("description"),
                    LocalDateTime.parse(this.getResult().getString("startTime"), (Default.getDateTimeFormatter())),
                    new Venue(
                            this.getResult().getInt("id"),
                            this.getResult().getString("venue_name"),
                            new City(
                                    this.getResult().getInt("city_id"),
                                    this.getResult().getString("city_name")
                            ),
                            this.getResult().getString("venue_address")
                    ),
                    new Seller(
                            this.getResult().getString("seller_username"),
                            this.getResult().getString("seller_companyName"),
                            this.getResult().getString("seller_email"),
                            this.getResult().getString("seller_phoneNumber")
                    )
            );
            events.add(buffer);
        }
        return events;
    }
    
    public void Insert_Events(Event _event) throws Exception {
        String SQLQuery = "INSERT INTO Events (name, description, startDateTime, venue_id, seller_id) VALUES (?, ?, ?, ?, ?);";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, _event.getName());
        this.getPreparedStatement().setString(2, _event.getDescription());
        this.getPreparedStatement().setString(3, _event.getStartTime().toString());
        this.getPreparedStatement().setString(4, String.valueOf(_event.getVenue().getId()));
        this.getPreparedStatement().setString(5, String.valueOf(_event.getSeller().getUsername()));

        this.Create();
    }

    public void Update_Event(Event _event) throws Exception {
        String SQLQuery = "UPDATE Events SET name = ?, description = ?, startDateTime = ?, venue_id = ?, seller_id = ? WHERE id = ?;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, _event.getName());
        this.getPreparedStatement().setString(2, _event.getDescription());
        this.getPreparedStatement().setString(3, _event.getStartTime().toString());
        this.getPreparedStatement().setString(4, String.valueOf(_event.getVenue().getId()));
        this.getPreparedStatement().setString(5, String.valueOf(_event.getSeller().getUsername()));
        this.getPreparedStatement().setString(1, String.valueOf(_event.getId()));

        this.Update();
    }

    public void Delete_Venue(int id) throws Exception {
        String SQLQuery = "DELETE FROM events WHERE id=?";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, String.valueOf(id));

        this.Delete();
    }
}
