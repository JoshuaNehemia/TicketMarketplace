package Entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Entities.Values.PaymentMethod;
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
    private double price;
    private String piadTime;
    private String status; //ENUM('UNPAID','PAID','REQUEST REFUND','REFUNDED')
    private boolean isClaimed;
    private String username;
    private int paymentMethod;

    //CONSTRUCTOR
    public Ticket(String id, Event event, String eventClass, int paymentMethod, String paidDate, String status, boolean isClaimed) {
        this.id = id;
        this.event = event;
        this.eventClass = eventClass;
        this.paymentMethod = paymentMethod;
        this.piadTime = paidDate;
        this.status = status;
        this.isClaimed = isClaimed;
    }
    
    public Ticket(String id, Event event, String eventClass, double price, int paymentMethod, String paidDate, String status, boolean isClaimed) {
        this.id = id;
        this.event = event;
        this.eventClass = eventClass;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.piadTime = paidDate;
        this.status = status;
        this.isClaimed = isClaimed;
    }

    public Ticket(String username, Event event, String eventClass, int paymentMethod, String status, boolean isClaimed) {
        this.id = "";
        this.event = event;
        this.eventClass = eventClass;
        this.paymentMethod = paymentMethod;
        this.piadTime = null;
        this.status = status;
        this.isClaimed = isClaimed;
        this.username = "";
    }

    public Ticket(String username, Event event, String eventClass, double price,int paymentMethod, String status, boolean isClaimed) {
        this.id = "";
        this.event = event;
        this.eventClass = eventClass;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.piadTime = null;
        this.status = status;
        this.isClaimed = isClaimed;
        this.username = "";
    }

    public Ticket() {
        this.id = "";
        this.event = null;
        this.eventClass = null;
        this.piadTime = null;
        this.status = "";
        this.isClaimed = false;
        this.username = "";
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

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaidTime() {
        return piadTime;
    }

    public void setPaidTime(String paidTime) {
        this.piadTime = paidTime;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //FUNCTION
    public String CreateID(String username) {
        return "" + this.getEvent().getName() + "+" + this.getEventClass() + "+" + username + "+" + 1 + "+" + LocalDateTime.now().toString();
    }

    public String CreateID(String username, int iteration) {
        return "" + this.getEvent().getName() + "+" + this.getEventClass() + "+" + username + "+" + iteration + "+" + LocalDateTime.now().toString();
    }
}
