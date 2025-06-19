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

        String SQLQuery = "SELECT ct.id AS 'id', ct.name AS 'name', pr.id AS 'province_id', pr.name AS 'province_name' FROM city ct INNER JOIN provinces pr ON ct.province_id = pr.id";
        System.out.println("SQL QUERY: \n" + SQLQuery);
        this.setResult(this.Read(SQLQuery));
        
        City buffer;
        while (this.getResult().next()) {
            buffer = new City(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),new Province(
                    this.getResult().getInt("province_id"),
                    this.getResult().getString("province_name"))
            );
            cities.add(buffer);
        }
        if(cities.size()>0)
        {
            return cities;
        }
        else throw new Exception ("Failure in receiving city data from database - no data matches the parameter");
    }
}
