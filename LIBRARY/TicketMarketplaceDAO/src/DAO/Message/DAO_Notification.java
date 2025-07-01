/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Message;

import DAO.Connection.DatabaseConnection;
import Entities.Account.User;
import Entities.Ticket;
import Entities.Event;
import Entities.EventClass;
import Entities.Message.Notification;
import Entities.Values.City;
import Entities.Values.PaymentMethod;
import Entities.Venue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class DAO_Notification {

    public static ArrayList<Notification> Select_User_Notifications(String username) throws Exception {
        ArrayList<Notification> notifications = new ArrayList<Notification>();

        String SQLQuery = "SELECT\n"
                + "    nf.id AS nf_id,\n"
                + "    nf.message,\n"
                + "    ti.*,\n"
                + "    ec.id AS eventclass_id,\n"
                + "    ec.event_id,\n"
                + "    ec.name AS eventclass_name,\n"
                + "    ev.name AS event_name,\n"
                + "    ev.startDateTime AS event_startDateTime,\n"
                + "    ev.venue_id,\n"
                + "    v.name AS venue_name,\n"
                + "    v.address AS venue_address,\n"
                + "    v.city_id,\n"
                + "    c.name AS city_name,\n"
                + "    pm.id AS paymentMethod_id,\n"
                + "    pm.name AS paymentmethod_name\n"
                + "FROM\n"
                + "    notifications AS nf\n"
                + "INNER JOIN\n"
                + "    tickets AS ti ON ti.id = nf.ticket_id\n"
                + "INNER JOIN\n"
                + "    eventclasses AS ec ON ti.eventClass_id = ec.id\n"
                + "INNER JOIN\n"
                + "    events AS ev ON ec.event_id = ev.id\n"
                + "INNER JOIN\n"
                + "    venues AS v ON ev.venue_id = v.id\n"
                + "INNER JOIN\n"
                + "    cities AS c ON v.city_id = c.id\n"
                + "INNER JOIN\n"
                + "    paymentmethods AS pm ON ti.paymentMethod_id = pm.id\n"
                + "WHERE\n"
                + "    nf.user_username=?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, username);

        ResultSet rslt = prst.executeQuery();

        while (rslt.next()) {
            // Venue & Kota
            Venue venue = new Venue(
                    rslt.getInt("venue_id"),
                    rslt.getString("venue_name")
            );
            venue.setAddress(rslt.getString("venue_address"));

            City city = new City();
            city.setName(rslt.getString("city_name"));
            venue.setCity(city);

            // Event
            Event event = new Event(
                    rslt.getInt("event_id"),
                    rslt.getString("event_name")
            );
            event.setStartTime(rslt.getString("event_startDateTime"));
            event.setVenue(venue);
            event.addEventClasses(new EventClass(
                    rslt.getInt("eventclass_id"),
                    rslt.getString("eventclass_name")
            ));

            // Payment Method
            PaymentMethod pm = new PaymentMethod(
                    rslt.getInt("paymentMethod_id"),
                    rslt.getString("paymentmethod_name")
            );

            // Ticket
            Ticket ticket = new Ticket(
                    rslt.getString("id"),
                    event,
                    rslt.getString("eventclass_name"),
                    pm.getId(),
                    rslt.getString("paidTime"),
                    rslt.getString("paymentStatus"),
                    rslt.getBoolean("isClaimed")
            );

            Notification notif = new Notification(
                    rslt.getInt("nf_id"),
                    rslt.getString("message"),
                    ticket
            );

            notifications.add(notif);
        }

        rslt.close();
        prst.close();

        return notifications;
    }

    public static Notification Select_Notification_By_Id(int id) throws Exception {
        Notification notif = new Notification();

        String SQLQuery = "SELECT\n"
                + "    nf.id AS nf_id,\n"
                + "    nf.message,\n"
                + "    ti.*,\n"
                + "    ec.id AS eventclass_id,\n"
                + "    ec.event_id,\n"
                + "    ec.name AS eventclass_name,\n"
                + "    ev.name AS event_name,\n"
                + "    ev.startDateTime AS event_startDateTime,\n"
                + "    ev.venue_id,\n"
                + "    v.name AS venue_name,\n"
                + "    v.address AS venue_address,\n"
                + "    v.city_id,\n"
                + "    c.name AS city_name,\n"
                + "    pm.id AS paymentMethod_id,\n"
                + "    pm.name AS paymentmethod_name\n"
                + "FROM\n"
                + "    notifications AS nf\n"
                + "INNER JOIN\n"
                + "    tickets AS ti ON ti.id = nf.ticket_id\n"
                + "INNER JOIN\n"
                + "    eventclasses AS ec ON ti.eventClass_id = ec.id\n"
                + "INNER JOIN\n"
                + "    events AS ev ON ec.event_id = ev.id\n"
                + "INNER JOIN\n"
                + "    venues AS v ON ev.venue_id = v.id\n"
                + "INNER JOIN\n"
                + "    cities AS c ON v.city_id = c.id\n"
                + "INNER JOIN\n"
                + "    paymentmethods AS pm ON ti.paymentMethod_id = pm.id\n"
                + "WHERE\n"
                + "    `nf`.`id`=?"
                + "ORDER BY"
                + "`nf`.`created_time` DESC;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, String.valueOf(id));

        ResultSet rslt = prst.executeQuery();

        if (rslt.next()) {
            // Venue & Kota
            Venue venue = new Venue(
                    rslt.getInt("venue_id"),
                    rslt.getString("venue_name")
            );
            venue.setAddress(rslt.getString("venue_address"));

            City city = new City();
            city.setName(rslt.getString("city_name"));
            venue.setCity(city);

            // Event
            Event event = new Event(
                    rslt.getInt("event_id"),
                    rslt.getString("event_name")
            );
            event.setStartTime(rslt.getString("event_startDateTime"));
            event.setVenue(venue);
            event.addEventClasses(new EventClass(
                    rslt.getInt("eventclass_id"),
                    rslt.getString("eventclass_name")
            ));

            // Payment Method
            PaymentMethod pm = new PaymentMethod(
                    rslt.getInt("paymentMethod_id"),
                    rslt.getString("paymentmethod_name")
            );

            // Ticket
            Ticket ticket = new Ticket(
                    rslt.getString("id"),
                    event,
                    rslt.getString("eventclass_name"),
                    pm.getId(),
                    rslt.getString("paidTime"),
                    rslt.getString("paymentStatus"),
                    rslt.getBoolean("isClaimed")
            );

            notif = new Notification(
                    rslt.getInt("nf_id"),
                    rslt.getString("message"),
                    ticket
            );

        }

        rslt.close();
        prst.close();

        return notif;
    }

    public static String Select_Seller_to_Notify(String ticket_id) throws Exception {
        String SQLQuery = "SELECT eve.`seller`\n"
                + "FROM\n"
                + "	`tickets` AS ti\n"
                + "INNER JOIN\n"
                + "	`eventclasses` AS ecl\n"
                + "ON \n"
                + "	ecl.`id` = ti.`eventclass_id`\n"
                + "INNER JOIN\n"
                + "	`events` AS eve\n"
                + "ON \n"
                + "ecl.`event_id` = eve.`id`\n"
                + "WHERE ti.`id` = ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, ticket_id);

        ResultSet rslt = prst.executeQuery();
        String username = "";
        if (rslt.next()) {
            username = rslt.getString("seller");
        }

        prst.close();

        return username;
    }

    public static int Insert_Notification(String message, String username, String ticket_id) throws Exception {
        String SQLQuery = "INSERT INTO notifications (message, user_username, ticket_id) VALUES (?, ?, ?);";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, message);
        prst.setString(2, username);
        prst.setString(3, ticket_id);

        int num = prst.executeUpdate();
        prst.close();

        return num;
    }
}
