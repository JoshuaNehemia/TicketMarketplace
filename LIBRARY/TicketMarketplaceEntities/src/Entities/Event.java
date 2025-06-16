package Entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Entities.Account.Seller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Franly
 */
public class Event {

    //FIELDS
    private int id;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private Venue venue;
    private Seller seller;
    private ArrayList<EventClass> eventClasses;

    //CONSTRUCTOR
    //This constructor likely no use
    public Event(int id, String name, String description, LocalDateTime startDateTime, Venue venue, Seller seller, ArrayList<EventClass> eventClasses) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startDateTime;
        this.venue = venue;
        this.seller = seller;
        this.eventClasses = eventClasses;
    }

    public Event(String name, String description, LocalDateTime startDateTime, Venue venue, Seller seller) {
        this.id = 0;
        this.name = name;
        this.description = description;
        this.startTime = startDateTime;
        this.venue = venue;
        this.seller = seller;
        this.eventClasses = new ArrayList<>();
    }

    public Event(int id, String name, String description, LocalDateTime startDateTime, Venue venue, Seller seller) throws Exception{
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startDateTime;
        this.venue = venue;
        this.seller = seller;
        this.eventClasses = new ArrayList<EventClass>();
    }

    public Event() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.startTime = null;
        this.venue = null;
        this.seller = null;
        this.eventClasses = new ArrayList<EventClass>();
    }

    //GETTER AND SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) throws Exception{
        if (id == 0) {
            throw new Exception("Event ID can't be zero");
        }
        else
        {
            this.id = id;
        }
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startDateTime) {
        this.startTime = startDateTime;
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

    public ArrayList<EventClass> getEventClasses() {
        return eventClasses;
    }

    public void setEventClasses(ArrayList<EventClass> eventClasses) {
        this.eventClasses = eventClasses;
    }

    public void addEventClasses(EventClass eventClass) {
        this.eventClasses.add(eventClass);
    }

    //FUNCTION
    public String[] GetEventData() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // EventClasses size also inputted for communication
        ArrayList<String> data = new ArrayList<String>();

        data.add(String.valueOf(this.getId()));
        data.add(this.getName());
        data.add(this.getDescription());
        data.add(this.getStartTime().format(df));
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
