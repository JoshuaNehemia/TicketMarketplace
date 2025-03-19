package TicketMarketplaceEntities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Franly
 */
public class City {
    private int id;
    private String name;
    private Province province;

    public City(int id, String name, Province province) {
        this.id = id;
        this.name = name;
        this.province = province;
    }
    public City() {
        this.id = 0;
        this.name = "";
        this.province = null;
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
    
}
