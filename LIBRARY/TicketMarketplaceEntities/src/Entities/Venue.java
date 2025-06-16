package Entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Franly
 */
public class Venue {

    //FIELDS
    private int id;
    private String name;
    private String city;
    private String address;
    private int area;
    private int maxCapacity;

    //CONSTRUCTOR
    public Venue(int id, String name, String city, String address, int area, int maxCapacity) throws Exception {
        this.setId(id);
        this.setName(name);
        this.setCity(city);
        this.setAddress(address);
        this.setArea(area);
        this.setMaxCapacity(maxCapacity);
    }

    public Venue(String name, String city, String address, int area, int maxCapacity) throws Exception {
        this.id = 0;
        this.setName(name);
        this.setCity(city);
        this.setAddress(address);
        this.setArea(area);
        this.setMaxCapacity(maxCapacity);
    }

    public Venue() {
        this.id = 0;
        this.name = "";
        this.city = "";
        this.address = "";
        this.area = 0;
        this.maxCapacity = 0;
    }

    //GETTER AND SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) throws Exception {
        if (id == 0) {
            throw new Exception("Venue id can't be zero");
        } else {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name == null || name.equals("")) {
            throw new Exception("Venue name can't be null");
        } else {
            this.name = name;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws Exception {
        if (address == null || address.equals("")) {
            throw new Exception("Venue address can't be null");
        } else {
            this.address = address;
        }
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

    public String getCity() {
        return city;
    }

    public void setCity(String _city) throws Exception{
        if (_city.equals("") || _city == null) {
            throw new Exception("Venue city can't be empty");
        } else {
            this.city = _city;
        }
    }

    //FUNCTION
    public String[] GetVenueData() {
        String[] data = new String[6];

        data[0] = String.valueOf(this.getId());
        data[1] = this.getName();
        data[2] = this.getCity();
        data[3] = this.getAddress();
        data[4] = String.valueOf(this.getArea());
        data[5] = String.valueOf(this.getMaxCapacity());

        return data;
    }

}
