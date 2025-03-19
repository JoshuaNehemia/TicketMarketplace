package TicketMarketplaceEntities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Franly
 */
public class Venue {
    private int id;
    private String name;
    private String address;
    private int maxCapacity;
    private int area;
    private City cityId; 

    public Venue(int id, String name, String address, int maxCapacity, int area, City cityId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.maxCapacity = maxCapacity;
        this.area = area;
        this.cityId = cityId;
    }
    
    public Venue() {
        this.id = 0;
        this.name = "";
        this.address = "";
        this.maxCapacity = 0;
        this.area = 0;
        this.cityId = null;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public City getCitiesId() {
        return cityId;
    }

    public void setCitiesId(City cityId) {
        this.cityId = cityId;
    }
}
