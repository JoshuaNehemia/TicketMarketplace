package DAO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import DAO.Connection.DatabaseConnection;
import static DAO.DAO_EventClass.Select_EventClass_By_Event_Id;
import Entities.Event;
import Entities.Venue;
import Entities.Account.Seller;
import Entities.EventClass;
import Entities.Format.Default;
import Entities.Values.City;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joshu
 */
public class DAO_Event {
    

            public static List<Event> Select_All_Events(String filter) throws Exception {
            ArrayList<Event> events = new ArrayList<>();
            String SQLQuery="";
            if(filter.equals("terbaru")){
                SQLQuery = "SELECT e.*, " +
                  "s.username AS seller_username, s.companyName, " +
                  "v.id AS venue_id, v.name AS venue_name, v.address AS venue_address, v.area AS venue_area, v.maxCapacity AS venue_maxCapacity, " +
                  "c.id AS city_id, c.name AS city_name " +
                  "FROM events e " +
                  "INNER JOIN sellers s ON e.seller = s.username " +
                  "INNER JOIN venues v ON e.venue_id = v.id " +
                  "INNER JOIN cities c ON v.city_id = c.id " +
                  "WHERE STR_TO_DATE(e.startDateTime, '%Y-%m-%d %H:%i:%s') > NOW() " +
                  "ORDER BY e.create_time DESC";
            }else if(filter.equals("harga terendah")){
                     SQLQuery = "SELECT e.*, ec_stats.min_price, " +
                            "s.username AS seller_username, s.companyName, " +
                            "v.id AS venue_id, v.name AS venue_name, v.address AS venue_address, v.area AS venue_area, v.maxCapacity AS venue_maxCapacity, " +
                            "c.id AS city_id, c.name AS city_name " +
                            "FROM events e " +
                            "INNER JOIN ( " +
                                "SELECT event_id, MIN(price) AS min_price " +
                                "FROM eventclasses " +
                                "GROUP BY event_id " +
                            ") ec_stats ON e.id = ec_stats.event_id " +
                            "INNER JOIN sellers s ON e.seller = s.username " +
                            "INNER JOIN venues v ON e.venue_id = v.id " +
                            "INNER JOIN cities c ON v.city_id = c.id " +
                            "WHERE STR_TO_DATE(e.startDateTime, '%Y-%m-%d %H:%i:%s') > NOW() " +
                            "ORDER BY ec_stats.min_price ASC";
                }else if(filter.equals("harga tertinggi")){
                    SQLQuery = "SELECT e.*, ec_stats.max_price, " +
                        "s.username AS seller_username, s.companyName, " +
                        "v.id AS venue_id, v.name AS venue_name, v.address AS venue_address, v.area AS venue_area, v.maxCapacity AS venue_maxCapacity, " +
                        "c.id AS city_id, c.name AS city_name " +
                        "FROM events e " +
                        "INNER JOIN ( " +
                            "SELECT event_id, MAX(price) AS max_price " +
                            "FROM eventclasses " +
                            "GROUP BY event_id " +
                        ") ec_stats ON e.id = ec_stats.event_id " +
                        "INNER JOIN sellers s ON e.seller = s.username " +
                        "INNER JOIN venues v ON e.venue_id = v.id " +
                        "INNER JOIN cities c ON v.city_id = c.id " +
                        "WHERE STR_TO_DATE(e.startDateTime, '%Y-%m-%d %H:%i:%s') > NOW() " +
                        "ORDER BY ec_stats.max_price DESC";
                }else if(filter.equals("terdekat")){
                    SQLQuery = "SELECT e.*, " +
                      "s.username AS seller_username, s.companyName, " +
                      "v.id AS venue_id, v.name AS venue_name, v.address AS venue_address, v.area AS venue_area, v.maxCapacity AS venue_maxCapacity, " +
                      "c.id AS city_id, c.name AS city_name " +
                      "FROM events e " +
                      "INNER JOIN sellers s ON e.seller = s.username " +
                      "INNER JOIN venues v ON e.venue_id = v.id " +
                      "INNER JOIN cities c ON v.city_id = c.id " +
                      "WHERE STR_TO_DATE(e.startDateTime, '%Y-%m-%d %H:%i:%s') > NOW() " +
                      "ORDER BY STR_TO_DATE(e.startDateTime, '%Y-%m-%d %H:%i:%s') ASC";
                }
                else{
                SQLQuery = "SELECT e.*, " +
                    "s.username AS seller_username, s.companyName, " +
                    "v.id AS venue_id, v.name AS venue_name, v.address AS venue_address, v.area AS venue_area, v.maxCapacity AS venue_maxCapacity, " +
                    "c.id AS city_id, c.name AS city_name " +
                    "FROM events e " +
                    "INNER JOIN sellers s ON e.seller = s.username " +
                    "INNER JOIN venues v ON e.venue_id = v.id " +
                    "INNER JOIN cities c ON v.city_id = c.id " +
                    "WHERE STR_TO_DATE(e.startDateTime, '%Y-%m-%d-%H-%i')";
            }

            PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
            ResultSet rslt = prst.executeQuery();

            while (rslt.next()) {
                Event e = new Event();
                e.setId(rslt.getInt("id"));
                e.setName(rslt.getString("name"));
                e.setStartTime(rslt.getString("startDateTime"));
                e.setDescription(rslt.getString("description"));

                // Venue
                Venue v = new Venue();
                v.setId(rslt.getInt("venue_id"));
                v.setName(rslt.getString("venue_name"));
                v.setAddress(rslt.getString("venue_address"));
                v.setArea(rslt.getInt("venue_area"));
                v.setMaxCapacity(rslt.getInt("venue_maxCapacity"));

                // City
                City c = new City();
                c.setId(rslt.getInt("city_id"));
                c.setName(rslt.getString("city_name"));
                v.setCity(c); 

                e.setVenue(v); 

                // Seller
                Seller s = new Seller();
                s.setUsername(rslt.getString("seller_username"));
                s.setCompanyName(rslt.getString("companyName"));
                e.setSeller(s);

                // EventClass
                ArrayList<EventClass> classes = Select_EventClass_By_Event_Id(e.getId());
                e.setEventClasses(classes);

                events.add(e);
            }

            prst.close();
            return events;
        }





    public static ArrayList<Event> Select_Event_By_Seller(String seller_username) throws Exception {
        ArrayList<Event> events = new ArrayList<Event>();
        Event buffer;
        
        String SQLQuery = "SELECT\n" + "  eve.*,\n" + "  ven.`name` AS 'venue_name',\n" + "  cit.`name` AS 'city_name',\n" + "  ven.`address` AS 'venue_address',\n" + "  sel.`companyName` AS 'seller_companyName',\n" + "  sel.`email` AS 'seller_email',\n" + "  sel.`phoneNumber` AS 'seller_phoneNumber'\n" + "FROM\n" + "  `events` AS eve\n" + "INNER JOIN\n" + "  `venues` AS ven\n" + "ON\n" + "  eve.`venue_id` = ven.`id`\n" + "INNER JOIN\n" + "  `sellers` AS sel\n" + "ON\n" + "  eve.`seller` = sel.`username`\n" + "INNER JOIN\n" + "  `cities` AS cit\n" + "ON\n" + "  ven.`city_id` = cit.`id`\n" + "WHERE\n" + "sel.`seller_usename` = ?;\n";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, seller_username);

        ResultSet rslt = prst.executeQuery();

        while (rslt.next()) {
            buffer = new Event(
                    rslt.getInt("id"),
                    rslt.getString("name"),
                    rslt.getString("description"),
                    rslt.getString("startDateTime"),
                    new Venue(
                            rslt.getInt("id"),
                            rslt.getString("venue_name"),
                            new City(
                                    rslt.getInt("city_id"),
                                    rslt.getString("city_name")
                            ),
                            rslt.getString("venue_address")
                    ),
                    new Seller(
                            rslt.getString("seller_username"),
                            rslt.getString("seller_companyName"),
                            rslt.getString("seller_email"),
                            rslt.getString("seller_phoneNumber")
                    )
            );
            events.add(buffer);
        }
        return events;
    }

    public static ArrayList<Event> Select_Event_By_Name(String name) throws Exception {
        ArrayList<Event> events = new ArrayList<Event>();

        String SQLQuery = "SELECT\n" + "  eve.*,\n" + "  ven.`name` AS 'venue_name',\n" + "  cit.`name` AS 'city_name',\n" + "  ven.`address` AS 'venue_address',\n" + "  sel.`companyName` AS 'seller_companyName',\n" + "  sel.`email` AS 'seller_email',\n" + "  sel.`phoneNumber` AS 'seller_phoneNumber'\n" + "FROM\n" + "  `events` AS eve\n" + "INNER JOIN\n" + "  `venues` AS ven\n" + "ON\n" + "  eve.`venue_id` = ven.`id`\n" + "INNER JOIN\n" + "  `sellers` AS sel\n" + "ON\n" + "  eve.`seller` = sel.`username`\n" + "INNER JOIN\n" + "  `cities` AS cit\n" + "ON\n" + "  ven.`city_id` = cit.`id`\n" + "WHERE\n" + "eve.`name` LIKE ?;\n";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, "%" + name + "%");

        ResultSet rslt = prst.executeQuery();

        Event buffer;
        while (rslt.next()) {
            buffer = new Event(
                    rslt.getInt("id"),
                    rslt.getString("name"),
                    rslt.getString("description"),
                    rslt.getString("startDateTime"),
                    new Venue(
                            rslt.getInt("id"),
                            rslt.getString("venue_name"),
                            new City(
                                    rslt.getInt("city_id"),
                                    rslt.getString("city_name")
                            ),
                            rslt.getString("venue_address")
                    ),
                    new Seller(
                            rslt.getString("seller_username"),
                            rslt.getString("seller_companyName"),
                            rslt.getString("seller_email"),
                            rslt.getString("seller_phoneNumber")
                    )
            );
            events.add(buffer);
        }

        prst.close();

        return events;
    }

    public static Event Select_SingleEvent_By_Name(String name, String seller_username) throws Exception {

        String SQLQuery = "SELECT\n" + "  eve.*,\n" + "  ven.`name` AS 'venue_name',\n" + "  cit.`name` AS 'city_name',\n" + "  ven.`address` AS 'venue_address',\n" + "  sel.`companyName` AS 'seller_companyName',\n" + "  sel.`email` AS 'seller_email',\n" + "  sel.`phoneNumber` AS 'seller_phoneNumber'\n" + "FROM\n" + "  `events` AS eve\n" + "INNER JOIN\n" + "  `venues` AS ven\n" + "ON\n" + "  eve.`venue_id` = ven.`id`\n" + "INNER JOIN\n" + "  `sellers` AS sel\n" + "ON\n" + "  eve.`seller` = sel.`username`\n" + "INNER JOIN\n" + "  `cities` AS cit\n" + "ON\n" + "  ven.`city_id` = cit.`id`\n" + "WHERE\n" + "eve.`name` = ? AND eve.`seller` = ?;\n";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, name);
        prst.setString(2, seller_username);

        ResultSet rslt = prst.executeQuery();

        Event buffer = new Event();
        if (rslt.next()) {
            buffer = new Event(
                    rslt.getInt("id"),
                    rslt.getString("name"),
                    rslt.getString("description"),
                    rslt.getString("startDateTime"),
                    new Venue(
                            rslt.getInt("id"),
                            rslt.getString("venue_name"),
                            new City(
                                    rslt.getInt("city_id"),
                                    rslt.getString("city_name")
                            ),
                            rslt.getString("venue_address")
                    ),
                    new Seller(
                            rslt.getString("seller_username"),
                            rslt.getString("seller_companyName"),
                            rslt.getString("seller_email"),
                            rslt.getString("seller_phoneNumber")
                    )
            );
        }

        prst.close();

        return buffer;
    }

    public static ArrayList<Event> Select_Event_By_CompanyName(String name) throws Exception {
        ArrayList<Event> events = new ArrayList<Event>();

        String SQLQuery = "SELECT\n" + "  eve.*,\n" + "  ven.`name` AS 'venue_name',\n" + "  cit.`name` AS 'city_name',\n" + "  ven.`address` AS 'venue_address',\n" + "  sel.`companyName` AS 'seller_companyName',\n" + "  sel.`email` AS 'seller_email',\n" + "  sel.`phoneNumber` AS 'seller_phoneNumber'\n" + "FROM\n" + "  `events` AS eve\n" + "INNER JOIN\n" + "  `venues` AS ven\n" + "ON\n" + "  eve.`venue_id` = ven.`id`\n" + "INNER JOIN\n" + "  `sellers` AS sel\n" + "ON\n" + "  eve.`seller` = sel.`username`\n" + "INNER JOIN\n" + "  `cities` AS cit\n" + "ON\n" + "  ven.`city_id` = cit.`id`\n" + "WHERE\n" + "sel.`companyName` LIKE ?;\n";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, "%" + name + "%");

        ResultSet rslt = prst.executeQuery();

        Event buffer;
        while (rslt.next()) {
            buffer = new Event(
                    rslt.getInt("id"),
                    rslt.getString("name"),
                    rslt.getString("description"),
                    rslt.getString("startDateTime"),
                    new Venue(
                            rslt.getInt("id"),
                            rslt.getString("venue_name"),
                            new City(
                                    rslt.getInt("city_id"),
                                    rslt.getString("city_name")
                            ),
                            rslt.getString("venue_address")
                    ),
                    new Seller(
                            rslt.getString("seller_username"),
                            rslt.getString("seller_companyName"),
                            rslt.getString("seller_email"),
                            rslt.getString("seller_phoneNumber")
                    )
            );
            events.add(buffer);
        }

        prst.clearBatch();
        prst.close();
        rslt.close();

        return events;
    }

    public static int Insert_Event(Event _event) throws Exception {
    String SQLQuery = "INSERT INTO Events (name, description, startDateTime, venue_id, seller) VALUES (?, ?, ?, ?, ?);";

    PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS);
    
    prst.setString(1, _event.getName());
    prst.setString(2, _event.getDescription());
    prst.setString(3, _event.getStartTime());
    prst.setInt(4, _event.getVenue().getId());
    prst.setString(5, _event.getSeller().getUsername());

    int affectedRows = prst.executeUpdate();

    // Ambil ID yang baru dibuat dan set ke objek event
    try (ResultSet generatedKeys = prst.getGeneratedKeys()) {
        if (generatedKeys.next()) {
            int newId = generatedKeys.getInt(1);
            _event.setId(newId);  // supaya bisa dipakai waktu insert eventClass
        } else {
            throw new SQLException("Creating event failed, no ID obtained.");
        }
    }

    prst.close();
    return affectedRows;
}


    public static int Update_Event(Event _event) throws Exception {
        String SQLQuery = "UPDATE Events SET name = ?, description = ?, startDateTime = ?, venue_id = ?, seller_id = ? WHERE id = ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, _event.getName());
        prst.setString(2, _event.getDescription());
        prst.setString(3, _event.getStartTime().toString());
        prst.setString(4, String.valueOf(_event.getVenue().getId()));
        prst.setString(5, String.valueOf(_event.getSeller().getUsername()));
        prst.setString(1, String.valueOf(_event.getId()));

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
    }

    public static int Delete_Venue(int id) throws Exception {
        String SQLQuery = "DELETE FROM events WHERE id=?";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, String.valueOf(id));

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
    }
}
