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

/**
 *
 * @author joshu
 */
public class ServerService {

    // Temporary database buat tugas progress project
    public RepositoryTemp repo;
    public User currentUser; //For Debugging

    public ServerService() {
        repo = new RepositoryTemp(); //For Temporary Database
        currentUser = new User();
    }

    public void UserSignUp(String username, String password, String fullname, String email, LocalDate birthdate) {
        // Add to temporary database
        repo.ListUser.add(new User(username, password, fullname, email, birthdate));
        // DEBUG
        System.out.println(repo.ListUser.size());
        for (User u : repo.ListUser) {
            u.DebugUserData();
        }
        // Add to SQL database
        //DAO_User.XXXXXXXXXXXX
    }
    
   /*
    * @param username 
    * @param password
    */
    public User UserLogIn(String username, String password) {
        //Using temporary Database
        for (User u : repo.ListUser) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                this.currentUser = u;
                return u;
            }
        }
        return new User();
    }
    
    public void SellerSIgnUp(String username, String password, String companyName, String companyAddress, String phoneNumber, String email){
        // Add to temporary database
        repo.ListSeller.add(new Seller(username, password, companyName, companyAddress,phoneNumber,email));
        
        // Add to SQL database
        //DAO_Seller.XXXXXXXXXXXX
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
    
    public void InsertNewVenue(String name, String address, int maxCapacity, int area, City cityId)
    {
        int newId = 1;
        int listVenueSize = repo.ListVenue.size();
        if(listVenueSize > 1)
        {
            newId = repo.ListVenue.get(listVenueSize-1).getId() + 1;
        }
        repo.ListVenue.add(new Venue(newId,name,address,maxCapacity,area,cityId));
        
    }
    
    public Venue SelectVenue(String venueName)
    {
        for (Venue v : repo.ListVenue) {
            if (v.getName().equals(venueName)) {
                return v;
            }
        }
        return new Venue();
    }
    
    public void InsertEvent(int id, String name, String description, LocalDate dateTime, int maxBuy, Venue venue, Seller seller_username, Event_category event_category)
    {
        int newId = 1;
        int listEventSize = repo.ListEvent.size();
        if(listEventSize > 1)
        {
            newId = repo.ListEvent.get(listEventSize-1).getId() + 1;
        }
        repo.ListEvent.add(new Event(newId,name,description,dateTime,maxBuy,venue,seller_username,event_category));
    }
    
}
