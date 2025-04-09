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
        System.out.println("Server is starting up");
        service = new ServerService();
        while (true) {
            ServerOption();
        }
    }

    private static void ServerOption() {

        //Server accepting message from client
        System.out.println("Server is ready to perform!");
        service.ReceivingFromClient();
        if (service.GetClientCommand().equals("")) {
            return;
        }
        System.out.println("Performing task!");
        if (service.GetClientCommand().equals("LI")) {
            service.UserLogIn(service.GetClientCommandData()[0],service.GetClientCommandData()[0]);
        } 
        else if (service.GetClientCommand().equals("SU")) {
            service.UserSignUp(service.GetClientCommandData()[0], service.GetClientCommandData()[1], service.GetClientCommandData()[2], service.GetClientCommandData()[3], LocalDate.parse(service.GetClientCommandData()[4]));
        }
        
        //For Debugging
        //MenuCLI.StartUpMenuCLI();
    }

}
