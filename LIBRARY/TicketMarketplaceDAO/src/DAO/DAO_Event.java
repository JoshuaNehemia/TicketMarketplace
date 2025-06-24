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
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class DAO_Event {

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
                    LocalDateTime.parse(rslt.getString("startTime"), (Default.getDateTimeFormatter())),
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
                    LocalDateTime.parse(rslt.getString("startTime"), (Default.getDateTimeFormatter())),
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
                    LocalDateTime.parse(rslt.getString("startTime"), (Default.getDateTimeFormatter())),
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
                    LocalDateTime.parse(rslt.getString("startTime"), (Default.getDateTimeFormatter())),
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
        String SQLQuery = "INSERT INTO Events (name, description, startDateTime, venue_id, seller_id) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, _event.getName());
        prst.setString(2, _event.getDescription());
        prst.setString(3, _event.getStartTime().toString());
        prst.setString(4, String.valueOf(_event.getVenue().getId()));
        prst.setString(5, String.valueOf(_event.getSeller().getUsername()));

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
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
