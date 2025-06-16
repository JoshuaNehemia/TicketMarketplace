package TicketMarketplaceDAO;

import TicketMarketplaceEntities.Province;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author joshu
 */
public class DAO_Province {
    
    
    public static Province Select_Province_By_Name(String province_name,ArrayList<Province> list)
    {
        Province selectedProvince = new Province();
        
        for(Province prov : list)
        {
            if(prov.getName().equals(province_name))
            {
                return selectedProvince;
            }
        }
        
        return selectedProvince;
    }
}
