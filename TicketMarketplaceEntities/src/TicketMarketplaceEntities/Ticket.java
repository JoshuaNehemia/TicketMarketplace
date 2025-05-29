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
public class Ticket {

    private String id;
    private Event event;
    private int eventClassId;
    private LocalDate paidDate;
    private Double price;
    private String BuyerUsername;
    private boolean isClaimed;

    public Ticket(String id, String BuyerUsername, Event event, int eventClassId, LocalDate paidDate, Double price) {
        this.id = id;
        this.event = event;
        this.eventClassId = eventClassId;
        this.paidDate = paidDate;
        this.price = price;
        this.BuyerUsername = BuyerUsername;
        this.isClaimed = false;
    }    
    
    public Ticket(String id, String BuyerUsername, Event event, int eventClassId, LocalDate paidDate, Double price,boolean isClaimed) {
        this.id = id;
        this.event = event;
        this.eventClassId = eventClassId;
        this.paidDate = paidDate;
        this.price = price;
        this.BuyerUsername = BuyerUsername;
        this.isClaimed = isClaimed;
    }

    public Ticket() {
        this.eventClassId = 0;
        this.price = 0.0;
        this.event = null;
        this.id = "";
        this.paidDate = null;
        this.BuyerUsername = null;
        this.isClaimed = false;
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

    public int getEventClassId() {
        return eventClassId;
    }

    public void setEventClassId(int eventClassId) {
        this.eventClassId = eventClassId;
    }

    public String GetTicketID(String username) {
        String newId = username + '-' + String.valueOf(LocalDate.now()) + '-' + this.getEvent().getName() + '-' + this.getEvent().getEventClasses().get(this.eventClassId - 1);
        return newId;
    }

    public boolean isIsClaimed() {
        return isClaimed;
    }

    public void setIsClaimed(boolean isClaimed) {
        this.isClaimed = isClaimed;
    }

    public String getBuyerUsername() {
        return BuyerUsername;
    }

    public void setBuyerUsername(String BuyerId) {
        this.BuyerUsername = BuyerId;
    }

    public String[] GetTicketData() {
        ArrayList<String> list = new ArrayList<String>();
        
        list.add(this.getId());
        list.add(this.getEvent().getName());
        list.add(String.valueOf(this.getEventClassId()));
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
