/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticket_marketplace;

/**
 *
 * @author Franly
 */
public class Seat {
    private int id;
    private Event_class event_class;
    private String rows;
    private int column;

    public Seat(int id, Event_class event_class, String rows, int column) {
        this.id = id;
        this.event_class = event_class;
        this.rows = rows;
        this.column = column;
    }
    
    public Seat() {
        this.id = 0;
        this.event_class = null;
        this.rows = "";
        this.column = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event_class getEvent_class() {
        return event_class;
    }

    public void setEvent_class(Event_class event_class) {
        this.event_class = event_class;
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
}
