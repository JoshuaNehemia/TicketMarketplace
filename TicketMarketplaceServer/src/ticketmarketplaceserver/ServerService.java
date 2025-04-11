/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplaceserver;

import java.time.LocalDate;

// Importing Our Own Library
import TicketMarketplaceDAO.*;
import TicketMarketplaceEntities.*;
import Communication.*;
import java.util.ArrayList;
import java.util.Arrays;
import static ticketmarketplaceserver.ServerApp.service;

/**
 *
 * @author joshu
 */
public class ServerService {

    // Temporary database buat tugas progress project
    private RepositoryTemp repo;

    //For TCP
    private TCPManager tcp;
    private String commandReceived = "";
    private String[] dataReceived;

    //Communication
    private String dividers = ";";
    String[] dummy = new String[3];
    
    //Constructor --------------------------------------------------------------
    public ServerService() {
        repo = new RepositoryTemp(); //For Temporary Database
        tcp = new TCPManager(6000);
    }

    //TCPManager ---------------------------------------------------------------
    public void ReceivingFromClient() {
        tcp.ReceivedCommunication();
        String communication = tcp.getCommunicationFromClient();
        System.out.println("Server processing communication message");
        this.commandReceived = Communication.TranslateToListOfCommand(communication, this.dividers)[0];
        System.out.println("Task received!");
        this.dataReceived = Communication.GetDataFromCommunication(communication, this.dividers);
        System.out.println("Data received");
        System.out.println(Arrays.toString(this.GetClientCommandData()));
    }

    public String GetClientCommand() {
        return this.commandReceived;
    }

    public String[] GetClientCommandData() {
        return this.dataReceived;
    }

    public void SendToClient(String communication) {
        tcp.setCommunicationToClient(communication);
        tcp.SendCommunication();
        this.commandReceived ="";
        this.dataReceived = null;
    }
    
    //User ---------------------------------------------------------------------
    public void UserSignUp(String username, String password, String fullname, String email, LocalDate birthdate) {
        System.out.println("SIGN UP (SU)");
        System.out.println("DATA : " + Arrays.toString(this.GetClientCommandData()));

        //Using temporary Database
        try {
            repo.ListUser.add(new User(username, password, fullname, email, birthdate));
        } catch (Exception ex) {

        }
        this.SendToClient(Communication.TranslateToCommunication("SUCCESS",dummy,dividers));
    }

    /*
    * @param username 
    * @param password
     */
    public void UserLogIn(String username, String password) {
        System.out.println("LOG IN (LI)");
        System.out.println("DATA : " + Arrays.toString(this.GetClientCommandData()));
        User buffer = new User();

        //Using temporary Database
        for (User u : repo.ListUser) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                buffer = u;
            }
        }
        String communication ="";
        if (!buffer.getUsername().equals("")) {
            communication = Communication.TranslateToCommunication("SUCCESS", buffer.GetUserData(), ";");
        } else {
            communication = Communication.TranslateToCommunication("FAILED",dummy , ";");
        }
        this.SendToClient(communication);
    }
    
    
    //Seller -------------------------------------------------------------------
    public void SellerSIgnUp(String username, String password, String companyName, String companyAddress, String phoneNumber, String email) {

        // Add to temporary database
        repo.ListSeller.add(new Seller(username, password, companyName, companyAddress, phoneNumber, email));

    }

    /*
    * @param username 
    * @param password
     */
    public Seller SellerLogIn(String username, String password) {
        //Using temporary Database
        for (Seller s : repo.ListSeller) {
            if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
                return s;
            }
        }
        return new Seller();
    }

    public void InsertNewVenue(String name, String address, int maxCapacity, int area, City cityId) {
        int newId = 1;
        int listVenueSize = repo.ListVenue.size();
        if (listVenueSize > 1) {
            newId = repo.ListVenue.get(listVenueSize - 1).getId() + 1;
        }
        repo.ListVenue.add(new Venue(newId, name, address, maxCapacity, area, cityId));

    }

    public Venue SelectVenue(String venueName) {
        for (Venue v : repo.ListVenue) {
            if (v.getName().equals(venueName)) {
                return v;
            }
        }
        return new Venue();
    }

    public void InsertEvent(int id, String name, String description, LocalDate dateTime, int maxBuy, Venue venue, Seller seller_username, Event_category event_category) {
        int newId = 1;
        int listEventSize = repo.ListEvent.size();
        if (listEventSize > 1) {
            newId = repo.ListEvent.get(listEventSize - 1).getId() + 1;
        }
        repo.ListEvent.add(new Event(newId, name, description, dateTime, maxBuy, venue, seller_username, event_category));
    }

}
