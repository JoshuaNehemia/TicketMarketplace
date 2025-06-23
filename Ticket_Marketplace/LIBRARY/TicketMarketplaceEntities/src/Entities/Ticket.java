package Entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.LocalDateTime;

/**
 *
 * @author Franly
 */
public class Ticket {

    //FIELD
    private String id;
    private Event event;
    private String eventClass;
    private LocalDateTime piadTime;
    private Double price;
    private String status; //ENUM('UNPAID','PAID','REQUEST REFUND','REFUNDED')
    private boolean isClaimed;

    //CONSTRUCTOR
    public Ticket(String id, Event event, String eventClass, LocalDateTime paidDate, Double price, String status, boolean isClaimed) {
        this.id = id;
        this.event = event;
        this.eventClass = eventClass;
        this.piadTime = paidDate;
        this.price = price;
        this.status = status;
        this.isClaimed = isClaimed;
    }
    
    public Ticket(Event event, String eventClass, Double price, String status, boolean isClaimed) {
        this.id = "";
        this.event = event;
        this.eventClass = eventClass;
        this.piadTime = null;
        this.price = price;
        this.status = status;
        this.isClaimed = isClaimed;
    }
    
    
    
    public Ticket() {
        this.id = "";
        this.event = null;
        this.eventClass = null;
        this.piadTime = null;
        this.price = 0.0;
        this.status = "";
        this.isClaimed = false;
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

    public String getEventClass() {
        return eventClass;
    }

    public void setEventClass(String eventClass) {
        this.eventClass = eventClass;
    }

    public LocalDateTime getPaidTime() {
        return piadTime;
    }

    public void setPaidTime(LocalDateTime paidTime) {
        this.piadTime = paidTime;
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
        return ""+this.getEvent().getName()+"-"+this.getEventClass()+"-"+username+"-"+LocalDateTime.now().toString();
    }
}
