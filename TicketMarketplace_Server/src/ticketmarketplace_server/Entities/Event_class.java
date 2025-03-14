/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplace_server.Entities;

/**
 *
 * @author Franly
 */
public class Event_class {
    private int id;
    private Event event;
    private String name;
    private double price;
    private String description;

    public Event_class(int id, Event event, String name, double price, String description) {
        this.id = id;
        this.event = event;
        this.name = name;
        this.price = price;
        this.description = description;
    }
    
    public Event_class() {
        this.id = 0;
        this.event = null;
        this.name = "";
        this.price = 0;
        this.description = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvents_id() {
        return event;
    }

    public void setEvents_id(Event event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
