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
            tcp.setCommunicationToClient("");
//            System.out.println(tcp.getCommunicationFromClient());
            String[] command = Communication.TranslateToListOfCommand(tcp.getCommunicationFromClient(), ";");


            if(command[1].equals("LI"))
            {
                try{
                    service.UserLogIn(command[3], command[4]);
                    tcp.setCommunicationToClient("login ok");
                }catch(Exception err){
                    tcp.setCommunicationToClient("login err");
                }
            }
            else if(command[1].equals("SU"))
            {
                try {
                    service.UserSignUp(command[3], command[4], command[5], command[6], LocalDate.parse(command[7]));
                    tcp.setCommunicationToClient("register ok");
                } catch (Exception err) {
                    tcp.setCommunicationToClient("register err: " + err.getMessage());
                }
            }
            tcp.SendCommunication();
            
            try {
              tcp.incomingSocket.close();
            } catch (Exception e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
            //For Debugging
            //MenuCLI.StartUpMenuCLI();
        }
    }
    
    

    
}
