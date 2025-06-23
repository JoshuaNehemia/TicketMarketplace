package DAO;

import DAO.Connection.DatabaseConnection;
import Entities.Event;
import Entities.EventClass;
import Entities.Format.Default;
import Entities.Ticket;
import java.time.LocalDateTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joshu
 */
public class DAO_Ticket extends DatabaseConnection {

    public DAO_Ticket() throws Exception {
        super();
        System.out.println("DAO_TICKET IS CONNECTED ");
    }

    public Ticket Select_Ticket_By_Id(String id) throws Exception {
        Ticket selectedTicket = new Ticket();

        String SQLQuery = "SELECT\n" + "ti.*,\n" + "ec.`name` AS 'eventclass_name',\n" + "ev.`name` AS 'event_name',\n" + "pm.`name` AS 'paymentmethod_name'\n" + "FROM\n" + "`tickets` AS ti\n" + "INNER JOIN\n" + "`eventclasses` AS ec\n" + "ON\n" + "ti.`eventClass_id` = ec.`id`\n" + "INNER JOIN\n" + "`events` AS ev\n" + "ON\n" + "ev.`id` = ec.`event_id`\n" + "INNER JOIN\n" + "`paymentmethods` AS pm\n" + "ON\n" + "ti.`paymentMethod_id` = pm.id\n" + "WHERE\n" + "ti.`id` = ?;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, id);

        this.Read();

        if (this.getResult().next()) {

            Event buff = new Event(this.getResult().getInt("event_id"), this.getResult().getString("event_name"));
            buff.addEventClasses(new EventClass(this.getResult().getInt("eventclass_id"),this.getResult().getString("eventclass_name")));
            selectedTicket = new Ticket(
                    this.getResult().getString("id"),
                    buff,
                    this.getResult().getString("eventclass_name"),
                    LocalDateTime.parse(this.getResult().getString("paidTime"),Default.getDateTimeFormatter()),
                    this.getResult().getDouble("price"),
                    this.getResult().getString("status"),
                    this.getResult().getBoolean("isClaimed")
            );
        }

        return selectedTicket;
    }
    
    public Ticket Select_Ticket_By_User(String user) throws Exception {
        Ticket selectedTicket = new Ticket();

        String SQLQuery = "SELECT\n" + "ti.*,\n" + "ec.`name` AS 'eventclass_name',\n" + "ev.`name` AS 'event_name',\n" + "pm.`name` AS 'paymentmethod_name'\n" + "FROM\n" + "`tickets` AS ti\n" + "INNER JOIN\n" + "`eventclasses` AS ec\n" + "ON\n" + "ti.`eventClass_id` = ec.`id`\n" + "INNER JOIN\n" + "`events` AS ev\n" + "ON\n" + "ev.`id` = ec.`event_id`\n" + "INNER JOIN\n" + "`paymentmethods` AS pm\n" + "ON\n" + "ti.`paymentMethod_id` = pm.id\n" + "WHERE\n" + "ti.`user` = ?;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, user);

        this.Read();

        if (this.getResult().next()) {

            Event buff = new Event(this.getResult().getInt("event_id"), this.getResult().getString("event_name"));
            buff.addEventClasses(new EventClass(this.getResult().getInt("eventclass_id"),this.getResult().getString("eventclass_name")));
            selectedTicket = new Ticket(
                    this.getResult().getString("id"),
                    buff,
                    this.getResult().getString("eventclass_name"),
                    LocalDateTime.parse(this.getResult().getString("paidTime"),Default.getDateTimeFormatter()),
                    this.getResult().getDouble("price"),
                    this.getResult().getString("status"),
                    this.getResult().getBoolean("isClaimed")
            );
        }

        return selectedTicket;
    }

}
