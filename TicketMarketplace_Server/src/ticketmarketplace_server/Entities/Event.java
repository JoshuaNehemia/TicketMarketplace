/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplace_server.Entities;
import ticketmarketplace_server.Entities.Venue;
import java.time.LocalDate; 
import java.util.List;
/**
 *
 * @author Franly
 */
public class Event {
    private int id;
    private String name;
    private String description;
    private LocalDate dateTime;
    private int maxBuy;
    private Venue venue;
    private Seller seller_username;
    private Event_category event_category;
    private List<Event_class> eventClasses; 

    public Event(int id, String name, String description, LocalDate dateTime, int maxBuy, Venue venue, Seller seller_username, Event_category event_category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.maxBuy = maxBuy;
        this.venue = venue;
        this.seller_username = seller_username;
        this.event_category = event_category;
    }
    
    public Event() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.dateTime = null;
        this.maxBuy = 0;
        this.venue = null;
        this.seller_username = null;
        this.event_category = null;
    }
    
    public void addEventClass(Event_class eventClass) {
        eventClasses.add(eventClass);
    }

    public List<Event_class> getEventClass() {
        return eventClasses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public int getMaxBuy() {
        return maxBuy;
    }

    public void setMaxBuy(int maxBuy) {
        this.maxBuy = maxBuy;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Seller getSeller_username() {
        return seller_username;
    }

    public void setSeller_username(Seller seller_username) {
        this.seller_username = seller_username;
    }

    public Event_category getEvent_categories() {
        return event_category;
    }

    public void setEvent_categories(Event_category event_category ) {
        this.event_category = event_category;
    }
}
