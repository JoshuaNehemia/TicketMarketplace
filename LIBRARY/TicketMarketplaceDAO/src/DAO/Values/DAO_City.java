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
public class DAO_City extends DatabaseConnection{
    
    public DAO_City() throws Exception
    {
        super();
        System.out.println(("DAO_CITY IS CONNECTED"));
    }
    
    public ArrayList<City> Select_City() throws Exception
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
        prst.clearBatch();
        prst.close();
        rslt.close();
        return cities;
    }
}
