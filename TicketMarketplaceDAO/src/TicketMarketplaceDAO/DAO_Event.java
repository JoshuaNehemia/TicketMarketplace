package TicketMarketplaceDAO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import TicketMarketplaceEntities.Event;
import java.util.ArrayList;
/**
 *
 * @author joshu
 */
public class DAO_Event {
    
    public static ArrayList<Event> Select_Event_By_Seller_Username(String seller_username,ArrayList<Event> list)
    {
        ArrayList<Event> seller_list = new ArrayList<Event>();
        for(Event ev : list)
        {
            if(ev.getSeller().equals(seller_username))
            {
                seller_list.add(ev);
            }
        }
        return seller_list;
    }
    
    public static ArrayList<Event> Insert_Event(Event event,ArrayList<Event> list)
    {
        list.add(event);
        
        return list;
    }
    
    public static ArrayList<Event> Update_Event_By_Id(Event event,ArrayList<Event> list)
    {
        for(Event ev : list)
        {
            if(ev.getId() ==event.getId())
            {
                list.remove(ev);
                list.add(event);
            }
        }
        return list;
    }
    
    
}
