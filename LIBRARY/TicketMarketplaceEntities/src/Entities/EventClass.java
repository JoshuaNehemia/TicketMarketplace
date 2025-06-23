package Entities;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Franly
 */
public class EventClass {

    //FIELDS
    private int id;
    private String name;
    private double price;
    private String description;
    private int stock; //Origin
    private int availableStock;

    
    //CONSTRUCTOR
    public EventClass(int id,String name, double price, String description,int stock,int availableStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.availableStock = availableStock;
    }    
    

    public EventClass(String name, double price, String description,int stock) {
        this.id = 0;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.availableStock = stock;
    }    
    
    ////FOR DISPLAY
    public EventClass(int id,String name)
    {
        this.id = id;
        this.name = name;
    }
    
    public EventClass() {
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.description = "";
        this.stock = 0;
        this.availableStock =0;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public int getAvailableStock(){
        return this.availableStock;
    }
    
    public void setAvailableStock(int availableStock){
        this.availableStock = availableStock;
    }
    
    //FUNCTION 
    public String[] GetEventClassData()
    {
        String[] data = new String[6];
        data[0] = String.valueOf(this.getId());
        data[1] = this.getName();
        data[2] = String.valueOf(this.getPrice());
        data[3] = this.getDescription();
        data[4] = String.valueOf(this.getStock());
        data[5] = String.valueOf(this.getAvailableStock());
        
        return data;
    }
    
    @Override 
    public String toString()
    {
        return this.getName() + "\nStock: " + this.getStock() + "\nDescription:" + this.getDescription() +"\nStocks: " +this.getStock()+"\nAvailable Stocks: " + this.getAvailableStock();
    }
}
