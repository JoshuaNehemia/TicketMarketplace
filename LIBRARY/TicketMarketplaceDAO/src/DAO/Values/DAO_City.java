package DAO.Values;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import DAO.Connection.DatabaseConnection;
import Entities.Values.City;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class DAO_City {
    
    public static ArrayList<City> Select_City() throws Exception
    {
        ArrayList<City> cities = new ArrayList<City>();
        City buffer;

        String SQLQuery = "SELECT* FROM cities";
        
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        ResultSet rslt = prst.executeQuery();
        
        while (rslt.next()) {
            buffer = new City(
                    rslt.getInt("id"),
                    rslt.getString("name")
            );
            cities.add(buffer);
        }
        
        prst.close();
        return cities;
    }
    
    public static City Select_City(String name) throws Exception
    {
        City buffer = new City(0,"");

        String SQLQuery = "SELECT* FROM cities WHERE name LIKE ?;";
        
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1,"%" + name + "%");
        
        ResultSet rslt = prst.executeQuery();
        
        if (rslt.next()) {
            buffer = new City(
                    rslt.getInt("id"),
                    rslt.getString("name")
            );
        }
        
        prst.close();
       
        return buffer;
    }
}
