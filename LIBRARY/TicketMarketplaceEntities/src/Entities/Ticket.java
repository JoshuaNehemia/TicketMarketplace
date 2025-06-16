package Entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Entities.Account.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Franly
 */
public class Ticket {

    //FIELD
    private String id;
    private Event event;
    private EventClass eventClass;
    private LocalDate paidDate;
    private Double price;
    private String status; //Paid, Canceled, Refunded
    private boolean isClaimed;

    //CONSTRUCTOR
    public Ticket(String id, Event event, EventClass eventClass, LocalDate paidDate, Double price, String status, boolean isClaimed) {
        this.id = id;
        this.event = event;
        this.eventClass = eventClass;
        this.paidDate = paidDate;
        this.price = price;
        this.status = status;
        this.isClaimed = isClaimed;
    }
    
    public Ticket(Event event, EventClass eventClass, LocalDate paidDate, Double price, String status, boolean isClaimed) {
        this.id = "";
        this.event = event;
        this.eventClass = eventClass;
        this.paidDate = paidDate;
        this.price = price;
        this.status = status;
        this.isClaimed = isClaimed;
    }
    
    //GETTER AND SETTER
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public EventClass getEventClass() {
        return eventClass;
    }

    public void setEventClass(EventClass eventClass) {
        this.eventClass = eventClass;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsClaimed() {
        return isClaimed;
    }

    public void setIsClaimed(boolean isClaimed) {
        this.isClaimed = isClaimed;
    }

    
    //FUNCTION
    public String CreateID(String username){
        return ""+this.getEvent().getName()+"-"+this.getEventClass().getName()+"-"+username;
    }
}
