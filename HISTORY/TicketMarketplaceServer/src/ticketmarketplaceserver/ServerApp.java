/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ticketmarketplaceserver;

import java.time.LocalDate;
import Communication.*;
import java.util.Arrays;
import TicketMarketplaceEntities.*;

/**
 *
 * @author joshu
 */
public class ServerApp {

    public static ServerService service;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServerStartUp();
    }

    private static void ServerStartUp() {
        try {
            System.out.println("Server is starting up");
            service = new ServerService(6000);
            service.start();
        }
        catch (Exception ex)
        {
            System.out.println("Server failed to start: " + ex);
        }
    }

    private static void ServerOption() {

        //Using Map and Runnable for ServerService
    }

}
