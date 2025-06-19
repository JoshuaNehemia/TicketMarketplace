package TicketMarketplaceEntities;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Franly
 */
public class Event_class {

    private int id;
    private String name;
    private double price;
    private String description;
    private int numRows;
    private int numCols;
    private int stock;

    public Event_class(int id,String name, double price, String description,int numRows, int numCols) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.numCols = numCols;
        this.numRows = numRows;
        this.stock = numCols*numRows;
    }    
    

    public Event_class(String name, double price, String description,int numRows, int numCols) {
        this.id = 0;
        this.name = name;
        this.price = price;
        this.description = description;
        this.numCols = numCols;
        this.numRows = numRows;
        this.stock = numCols*numRows;
    }    
    
    public Event_class(int id, String name, double price, String description,int numRows, int numCols, int stock,ArrayList<Seat>seats) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.numCols = numCols;
        this.numRows = numRows;
        this.stock = stock;
    }
    
    public Event_class(int id, String name, double price, String description,int numRows, int numCols, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.numCols = numCols;
        this.numRows = numRows;
        this.stock = stock;
    }
    
    
    public Event_class() {
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.stock = 0;
        this.numRows = 0;
        this.numCols =0;
        this.description = "";
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    //Function 
    public String[] GetEventClassData()
    {
        String[] data = new String[7];
        data[0] = String.valueOf(this.getId());
        data[1] = this.getName();
        data[2] = String.valueOf(this.getPrice());
        data[3] = this.getDescription();
        data[4] = String.valueOf(this.getNumRows());
        data[5] = String.valueOf(this.getNumCols());
        data[6] = String.valueOf(this.getStock());
        
        return data;
    }
    
    @Override 
    public String toString()
    {
        return this.getName() + "\nStock: " + this.getStock() + "\nDescription:" + this.getDescription() + "\nRows :" + this.getNumRows() + "\nColumns: " +this.getNumCols();
    }
}
