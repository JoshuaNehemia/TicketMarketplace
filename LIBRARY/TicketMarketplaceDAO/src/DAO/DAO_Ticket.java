package DAO;

import DAO.Connection.DatabaseConnection;
import Entities.Event;
import Entities.EventClass;
import Entities.Format.Default;
import Entities.Ticket;
import Entities.Values.PaymentMethod;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joshu
 */
public class DAO_Ticket {

    public static Ticket Select_Ticket_By_Id(String id) throws Exception {
        Ticket selectedTicket = new Ticket();

        String SQLQuery = "SELECT\n" + "ti.*,\n" + "ec.`name` AS 'eventclass_name',\n" + "ev.`name` AS 'event_name',\n" + "pm.`name` AS 'paymentmethod_name'\n" + "FROM\n" + "`tickets` AS ti\n" + "INNER JOIN\n" + "`eventclasses` AS ec\n" + "ON\n" + "ti.`eventClass_id` = ec.`id`\n" + "INNER JOIN\n" + "`events` AS ev\n" + "ON\n" + "ev.`id` = ec.`event_id`\n" + "INNER JOIN\n" + "`paymentmethods` AS pm\n" + "ON\n" + "ti.`paymentMethod_id` = pm.id\n" + "WHERE\n" + "ti.`id` = ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, id);

        ResultSet rslt = prst.executeQuery();

       if (rslt.next()) {
    Event buff = new Event(
        rslt.getInt("event_id"),
        rslt.getString("event_name")
    );
    buff.addEventClasses(new EventClass(
        rslt.getInt("eventclass_id"),
        rslt.getString("eventclass_name")
    ));

    PaymentMethod pm = new PaymentMethod(
        rslt.getInt("paymentMethod_id"),
        rslt.getString("paymentmethod_name")
    );

    selectedTicket = new Ticket(
        rslt.getString("id"),
        buff,
        rslt.getString("eventclass_name"),
        pm.getId(),
        LocalDateTime.parse(rslt.getString("paidTime"), Default.getDateTimeFormatter()),
        rslt.getString("status"),
        rslt.getBoolean("isClaimed")
    );
}


        prst.close();

        return selectedTicket;
    }

    public static Ticket Select_Ticket_By_User(String user) throws Exception {
        Ticket selectedTicket = new Ticket();

        String SQLQuery = "SELECT\n" + "ti.*,\n" + "ec.`name` AS 'eventclass_name',\n" + "ev.`name` AS 'event_name',\n" + "pm.`name` AS 'paymentmethod_name'\n" + "FROM\n" + "`tickets` AS ti\n" + "INNER JOIN\n" + "`eventclasses` AS ec\n" + "ON\n" + "ti.`eventClass_id` = ec.`id`\n" + "INNER JOIN\n" + "`events` AS ev\n" + "ON\n" + "ev.`id` = ec.`event_id`\n" + "INNER JOIN\n" + "`paymentmethods` AS pm\n" + "ON\n" + "ti.`paymentMethod_id` = pm.id\n" + "WHERE\n" + "ti.`user` = ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, user);

        ResultSet rslt = prst.executeQuery();

        if (rslt.next()) {

            Event buff = new Event(rslt.getInt("event_id"), rslt.getString("event_name"));
            buff.addEventClasses(new EventClass(rslt.getInt("eventclass_id"), rslt.getString("eventclass_name")));
            selectedTicket = new Ticket(
                    rslt.getString("id"),
                    buff,
                    rslt.getString("eventclass_name"),
                    rslt.getInt("paymentmethod_id"),
                    LocalDateTime.parse(rslt.getString("paidTime"), Default.getDateTimeFormatter()),
                    rslt.getString("status"),
                    rslt.getBoolean("isClaimed")
            );
        }

        prst.close();

        return selectedTicket;
    }

   public static int Insert_Ticket(Ticket ticket, String buyer_username, int eventclass_id) throws Exception {
    String SQLQuery = "INSERT INTO `ticketmarketplace`.`tickets` " +
                      "(`id`, `user`, `eventClass_id`, `paymentMethod_id`, `paymentStatus`, `isClaimed`) " +
                      "VALUES (?, ?, ?, ?, ?, ?)";

    PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
    prst.setString(1, ticket.getId());
    prst.setString(2, buyer_username);
    prst.setInt(3, eventclass_id); // ganti jadi setInt karena tipenya int
    prst.setInt(4, ticket.getPaymentMethod());
    prst.setString(5, "UNPAID");
    prst.setBoolean(6, ticket.isIsClaimed()); // gunakan boolean langsung

    int num = prst.executeUpdate();
    prst.close();

    return num;
}

    
    public static int Update_Ticket_Status(String ticket_id,String status) throws Exception {
        String SQLQuery = "UPDATE `ticketmarketplace`.`tickets` SET `status` = ? WHERE id = ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, ticket_id);
        prst.setString(2, status);
        

        int num = prst.executeUpdate();
        prst.close();

        return num;
    }
    public static int Update_Ticket_Paid(String ticket_id, LocalDateTime paidDate) throws Exception {
        String SQLQuery = "UPDATE `ticketmarketplace`.`tickets` SET `paidDate` = ?, `status` = 'PAID' WHERE `id`= ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, paidDate.toString());
        prst.setString(2, ticket_id);
        int num = prst.executeUpdate();
        prst.close();

        return num;
    }
    
    
    public static int Update_Ticket_isClaimed(String ticket_id) throws Exception {
        String SQLQuery = "UPDATE `ticketmarketplace`.`tickets` SET `isClaimed` = 1 WHERE `id`= ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, ticket_id);
        int num = prst.executeUpdate();
        prst.close();

        return num;
    }
}
