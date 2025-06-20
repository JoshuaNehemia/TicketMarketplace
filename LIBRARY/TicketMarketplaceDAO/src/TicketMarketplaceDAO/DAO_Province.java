package TicketMarketplaceDAO;

import Entities.Values.Province;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author joshu
 */
public class DAO_Province extends DatabaseConnection{
    
    public DAO_Province() throws Exception
    {
        super();
        System.out.println("DAO_PROVINCE IS CONNECTED");
    }
    
    
    public ArrayList<Province> Select_Province() throws Exception
    {
        ArrayList<Province> provinces = new ArrayList<Province>();

        String SQLQuery = "SELECT* FROM provinces";
        
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.Read();
        
        Province buffer;
        while (this.getResult().next()) {
            buffer = new Province(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name")
            );
            provinces.add(buffer);
        }
        return provinces;
    }
}
