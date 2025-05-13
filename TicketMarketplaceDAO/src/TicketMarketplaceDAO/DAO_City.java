package TicketMarketplaceDAO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import TicketMarketplaceEntities.*;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class DAO_City {
    
    public static City Select_City_By_Name(String city_name,ArrayList<City> list)
    {
        City selectedCity = new City();
        
        for(City ci : list)
        {
            if(ci.getName().equals(city_name))
            {
                return selectedCity;
            }
        }
        
        return selectedCity;
    }
}
