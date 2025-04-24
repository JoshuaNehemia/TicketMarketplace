package TicketMarketplaceEntities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
    private ArrayList<Event_class> eventClasses;

    public Event(int id, String name, String description, LocalDate dateTime, int maxBuy, Venue venue, Seller seller_username, Event_category event_category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.maxBuy = maxBuy;
        this.venue = venue;
        this.seller_username = seller_username;
        this.event_category = event_category;
        this.eventClasses = new ArrayList<Event_class>();
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

    public void setEventClass(ArrayList<Event_class> _eventClasses) {
        eventClasses = _eventClasses;
    }

    public ArrayList<Event_class> getEventClass() {
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

    public void setEvent_categories(Event_category event_category) {
        this.event_category = event_category;
    }

    //Function
    public String[] GetEventData() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // EventClasses size also inputted for communication
        ArrayList<String> data = new ArrayList<String>();

        data.add(String.valueOf(this.getId()));
        data.add(this.getName());
        data.add(this.getDescription());
        data.add(this.getDateTime().format(df));
        data.add(String.valueOf(this.getMaxBuy()));
        data.add(this.getVenue().toString());
        data.add(this.getSeller_username().getUsername());
        data.add(this.getEvent_categories().toString());
        data.add(String.valueOf(this.getEventClass().size())); //Size of event class
        
        for (int i = 0; i < this.getEventClass().size(); i++) {
            String[] buf = this.getEventClass().get(i).GetEventClassData();
            data.add(buf[0]);
            data.add(buf[1]);
            data.add(buf[2]);
            data.add(buf[3]);
            data.add(buf[4]);
        }
        
        
        String[] array = data.toArray(new String[0]);
        
        return array;
    }
}
