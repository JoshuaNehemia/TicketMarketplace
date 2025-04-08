/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ticketmarketplaceserver;

import java.util.Scanner;


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
    
    private static void ServerStartUp()
    {
        System.out.println("Server is Running");
        service = new ServerService();
        
        //For Debugging
        //while(true)
        {
            //MenuCLI.StartUpMenuCLI();
        }
    }
    
    

    
}
