package TicketMarketplaceEntities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.LocalDate; 
/**
 *
 * @author Franly
 */
public class Ticket {
    private User user;
    private Seat seat;
    private int id;
    private LocalDate paidDate;
    private Payment_method payment_method;
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket(User user, Seat seat, int id, LocalDate paidDate, Payment_method payment_method) {
        this.user = user;
        this.seat = seat;
        this.id = id;
        this.paidDate = paidDate;
        this.payment_method = payment_method;
    }
    public Ticket() {
        this.user = null;
        this.seat = null;
        this.id = 0;
        this.paidDate = null;
        this.payment_method = null;
    }

    public Seat getSeats() {
        return seat;
    }

    public void setSeats(Seat seat) {
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public Payment_method getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(Payment_method payment_method) {
        this.payment_method = payment_method;
    }
}
