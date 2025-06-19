package TicketMarketplaceEntities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Franly
 */
public class Ticket {

    private String id;
    private Event event;
    private Seat seats;
    private LocalDate paidDate;
    private Double price;
    private User buyer;
    private String status; //Paid, Canceled, Refunded
    private boolean isClaimed;

    public Ticket(String id, User BuyerUsername, Event event, Seat eventClassId, LocalDate paidDate, Double price) {
        this.id = id;
        this.event = event;
        this.seats = eventClassId;
        this.paidDate = paidDate;
        this.price = price;
        this.buyer = BuyerUsername;
        this.isClaimed = false;
        this.status ="";
    }    
    
    public Ticket(String id, User BuyerUsername, Event event, Seat eventClassId, LocalDate paidDate, Double price,boolean isClaimed, String status) {
        this.id = id;
        this.event = event;
        this.seats = eventClassId;
        this.paidDate = paidDate;
        this.price = price;
        this.buyer = BuyerUsername;
        this.isClaimed = isClaimed;
    }

    public Ticket() {
        this.seats = null;
        this.price = 0.0;
        this.event = null;
        this.id = "";
        this.paidDate = null;
        this.buyer = null;
        this.isClaimed = false;
        this.status="";
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Seat getSeats() {
        return seats;
    }

    public void setSeats(Seat seats) {
        this.seats = seats;
    }

    public boolean isIsClaimed() {
        return isClaimed;
    }

    public void setIsClaimed(boolean isClaimed) {
        this.isClaimed = isClaimed;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String CreateTicketID() {
        return ""+this.getBuyer().getUsername() + "-" + String.valueOf(LocalDateTime.now()) + "-" + this.getEvent().getName() + "-" + this.getSeats().toString() + "-" + this.getSeats().getId();
    }
    
    public String[] GetTicketData() {
        ArrayList<String> list = new ArrayList<String>();
        
        list.add(this.getId());
        list.add(this.getEvent().getName());
        list.add(String.valueOf(this.getSeats()));
        list.add(String.valueOf(this.getPaidDate()));
        list.add(String.valueOf(this.getPrice()));
        
        String[] array = list.toArray(new String[0]);
        
        return array;
    }

    @Override
    public String toString() {
        return "";
    }

}
