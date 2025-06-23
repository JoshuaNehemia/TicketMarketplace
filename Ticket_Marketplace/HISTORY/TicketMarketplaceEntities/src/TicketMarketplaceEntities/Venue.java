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
    private City city; 

    public Venue(int id, String name, String address, int maxCapacity, int area, City city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.maxCapacity = maxCapacity;
        this.area = area;
        this.city = city;
    }

    public Venue(int id, String name, String address, int maxCapacity, int area, int cityId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.maxCapacity = maxCapacity;
        this.area = area;
        this.city = null;
    }
    
    public Venue() {
        this.id = 0;
        this.name = "";
        this.address = "";
        this.maxCapacity = 0;
        this.area = 0;
        this.city = null;
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

    public City getCity() {
        return city;
    }

    public void setCity(City _city) {
        this.city = _city;
    }
    
    //Function
    
    public String[] GetVenueData()
    {
        String[] data = new String[6];
        
        data[0] = String.valueOf(this.getId());
        data[1] = this.getName();
        data[2] = this.getAddress();
        data[3] = String.valueOf(this.getMaxCapacity());
        data[4] = String.valueOf(this.getArea());
        data[5] = String.valueOf(this.getCity().getName());
        
        return data;
    }
    
}
