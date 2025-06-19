package TicketMarketplaceEntities;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Franly
 */
public class Seller {
    private String username;
    private String password;
    private String email;
    private String companyName;
    private String companyAddress;
    private String phoneNumber;
    private ArrayList<Event> events;

    public Seller(String username, String password, String companyName, String companyAddress, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.events = new ArrayList<Event>();
    }
    public Seller() {
        this.username = "";
        this.password = "";
        this.companyName = "";
        this.companyAddress = "";
        this.phoneNumber = "";
        this.email = "";
        this.events = new ArrayList<Event>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setEvents(ArrayList<Event> events)
    {
        this.events = events;
    }
    
    public ArrayList<Event> getEvents()
    {
        return this.events;
    }
    
    public void addEvents(Event event)
    {
        this.events.add(event);
    }
    
    //Function
    
    public String[] GetSellerData()
    {
        String[] data = new String[6];
        
        data[0] = this.getUsername();
        data[1] = this.getPassword();
        data[2] = this.getCompanyName();
        data[3] = this.getCompanyAddress();
        data[4] = this.getPhoneNumber();
        data[5] = this.getEmail();
        
        return data;
    }
    
}

