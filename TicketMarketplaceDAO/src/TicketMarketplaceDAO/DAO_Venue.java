package TicketMarketplaceDAO;

import TicketMarketplaceEntities.Venue;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joshu
 */
public class DAO_Venue {

    public static Venue Select_Venue_By_Name(String venue_name, ArrayList<Venue> list) {
        Venue selectedVenue = new Venue();

        for (Venue ven : list) {
            if (ven.getName().equals(venue_name)) {
                return selectedVenue;
            }
        }

        return selectedVenue;
    }
}
