package TicketMarketplaceEntities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Franly
 */
public class Province {
    private int id;
    private String name;

    public Province(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Province() {
        this.id = 0;
        this.name = "";
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
    
    //Function
    
    @Override
    public String toString()
    {
        return this.getName();
    }
    
    public String[] GetProvinceData()
    {
        String[] data = new String[2];
        
        data[0] = String.valueOf(this.getId());
        data[1] = (this.getName());
        
        return data;
    }
}
