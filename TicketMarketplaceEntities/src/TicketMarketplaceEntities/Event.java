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
    private LocalDate startDateTime;
    private Venue venue;
    private Seller seller;
    private ArrayList<Event_class> eventClasses;

    public Event(int id, String name, String description, LocalDate startDateTime, Venue venue, Seller seller, ArrayList<Event_class> eventClasses) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDateTime = startDateTime;
        this.venue = venue;
        this.seller = seller;
        this.eventClasses = eventClasses;
    }
    
    public Event( String name, String description, LocalDate startDateTime, Venue venue, Seller seller) {
        this.id = 0;
        this.name = name;
        this.description = description;
        this.startDateTime = startDateTime;
        this.venue = venue;
        this.seller = seller;
        this.eventClasses = new ArrayList<Event_class>();
    }    
    public Event(int id, String name, String description, LocalDate startDateTime, Venue venue, Seller seller) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDateTime = startDateTime;
        this.venue = venue;
        this.seller = seller;
        this.eventClasses = new ArrayList<Event_class>();
    }

    
    public Event() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.startDateTime = null;
        this.venue = null;
        this.seller = null;
        this.eventClasses = new ArrayList<Event_class>();
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

    public LocalDate getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDate startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public ArrayList<Event_class> getEventClasses() {
        return eventClasses;
    }

    public void setEventClasses(ArrayList<Event_class> eventClasses) {
        this.eventClasses = eventClasses;
    }   
    public void addEventClasses(Event_class eventClass) {
        this.eventClasses.add(eventClass);
    }


    //Function
    public String[] GetEventData() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // EventClasses size also inputted for communication
        ArrayList<String> data = new ArrayList<String>();

        data.add(String.valueOf(this.getId()));
        data.add(this.getName());
        data.add(this.getDescription());
        data.add(this.getStartDateTime().format(df));
        data.add(this.getVenue().getName());
        data.add(this.getSeller().getUsername());
        data.add(String.valueOf(this.getEventClasses().size())); //Size of event class
        for (int i = 0; i < this.getEventClasses().size(); i++) {
            data.add(String.valueOf(this.getEventClasses().get(i).getId()));
        }
        String[] array = data.toArray(new String[0]);
        
        return array;
    }
}
