/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.Message;

import Entities.Ticket;

/**
 *
 * @author joshu
 */
public class Notification {

    private int id;
    private String message;
    private Ticket ticket;

    public Notification(int id, String message, Ticket ticket) {
        this.id = id;
        this.message = message;
        this.ticket = ticket;
    }
   
    public Notification(String message, Ticket ticket) {
        this.id = 0;
        this.message = message;
        this.ticket = ticket;
    }
    
    public Notification() {
        this.id = 0;
        this.message = "";
        this.ticket = null;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

}
