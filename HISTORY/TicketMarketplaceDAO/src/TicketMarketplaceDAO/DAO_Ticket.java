package TicketMarketplaceDAO;

import TicketMarketplaceEntities.Event;
import TicketMarketplaceEntities.Seat;
import TicketMarketplaceEntities.Ticket;
import TicketMarketplaceEntities.User;
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
public class DAO_Ticket extends DatabaseConnection {

    public DAO_Ticket() throws Exception {
        super();
        System.out.println("DAO_TICKET IS CONNECTED ");
    }

    public Ticket Select_Ticket_By_Id(String id) throws Exception {
        Ticket selectedTicket = new Ticket();

        String SQLQuery = "SELECT * FROM tickets WHERE id=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, id);

        System.out.println("SQL QUERY: \n" + prst);
        this.setResult(this.Read(String.valueOf(prst)));

        if (this.getResult().next()) {
            selectedTicket = new Ticket();
            return selectedTicket;
        } else {
            throw new Exception("Failure in receiving ticket data from database - no data matches the parameter");
        }

    }

    public ArrayList<Ticket> Select_Ticket_By_Username(String username) throws Exception {
        ArrayList<Ticket> list = new ArrayList<>();
        Ticket selectedTicket;

        String SQLQuery = "SELECT * FROM tickets WHERE username=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, username);

        System.out.println("SQL QUERY: \n" + prst);
        this.setResult(this.Read(String.valueOf(prst)));

        while (this.getResult().next()) {
            selectedTicket = new Ticket(
                    this.getResult().getString("id"),
                    new User(),
                    new Event(),
                    new Seat(),
                    this.getResult().getTimestamp("paidDate").toLocalDateTime().toLocalDate(),
                    this.getResult().getDouble("price"),
                    this.getResult().getBoolean("isClaimed"),
                    this.getResult().getString("status")
            );
            list.add(selectedTicket);
        }
        return list;

    }

    public void Insert_Ticket(Ticket nticket) throws Exception {
        String SQLQuery = "INSERT INTO tickets (`id`,`username`,`seats_id`,`paidDate`,`status`,`price`,`isClaimed`) VALUES(?,?,?,?,?,?,?)"; //nanti ganti insert
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, "ganti ticket data");

        System.out.println("SQL QUERY: \n" + prst);
        this.Create(String.valueOf(prst));
    }

    public void Update_Ticket(Ticket nticket) throws Exception {
        String SQLQuery = "SELECT * FROM tickets WHERE username=?"; //nanti ganti update
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, "ganti ticket data");

        System.out.println("SQL QUERY: \n" + prst);
        this.Create(String.valueOf(prst));
    }
}
