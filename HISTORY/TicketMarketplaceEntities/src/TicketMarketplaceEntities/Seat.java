package TicketMarketplaceEntities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Franly
 */
public class Seat {

    private int id;
    private Event_class event_class;
    private String rows;
    private int column;
    private boolean isTaken;

    public Seat(int id, String rows, Event_class event_class,int column, boolean isTaken) {
        this.id = id;
        this.rows = rows;
        this.event_class = event_class;
        this.column = column;
        this.isTaken = isTaken;
    }

    public Seat() {
        this.id = 0;
        this.rows = "";
        this.column = 0;
        this.isTaken = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(boolean isTaken) {
        this.isTaken = isTaken;
    }

    public Event_class getEvent_class() {
        return event_class;
    }

    public void setEvent_class(Event_class event_class) {
        this.event_class = event_class;
    }
    
    //Function
    public String[] GetSeatData() {
        String[] data = new String[4];

        data[0] = String.valueOf(this.getId());
        data[1] = String.valueOf(this.getRows());
        data[2] = String.valueOf(this.getColumn());
        data[3] = String.valueOf(this.getIsTaken());
        
        return data;
    }
    
    @Override
    public String toString()
    {
        return this.getRows()+this.getColumn();
    }

}
