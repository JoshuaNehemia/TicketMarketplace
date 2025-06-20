package TicketMarketplaceDAO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Entities.Values.City;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class DAO_City extends DatabaseConnection{
    
    public DAO_City() throws Exception
    {
        super();
        System.out.println(("DAO_CITY IS CONNECTED"));
    }
    
    public ArrayList<City> Select_City() throws Exception
    {
        ArrayList<City> cities = new ArrayList<City>();

        String SQLQuery = "SELECT* FROM cities";
        
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        
        City buffer;
        while (this.getResult().next()) {
            buffer = new City(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name")
            );
            cities.add(buffer);
        }
        return cities;
    }
}
