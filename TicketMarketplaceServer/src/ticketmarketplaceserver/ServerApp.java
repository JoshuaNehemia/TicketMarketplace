/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ticketmarketplaceserver;

import java.time.LocalDate;
import Communication.*;


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
        TCPManager tcp = new TCPManager(6000);
        while(true)
        {
            //Server accepting message from client
            tcp.ReceivedCommunication();
            String[] command = Communication.TranslateToListOfCommand(tcp.getCommunicationFromClient(), ";");
            if(command[0].equals("LI"))
            {
                service.UserLogIn(command[1], command[2]);
            }
            else if(command[1].equals("SU"))
            {
                service.UserSignUp(command[1], command[2],command[3],command[4],LocalDate.parse(command[5]));
            }
            //For Debugging
            //MenuCLI.StartUpMenuCLI();
        }
    }
    
    

    
}
