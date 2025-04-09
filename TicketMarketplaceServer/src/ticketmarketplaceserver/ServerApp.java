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
        TCPManager tcp = new TCPManager(6000);
        while (true) {
            //Server accepting message from client
            tcp.ReceivedCommunication();
            System.out.println("server processing message");
            String[] command = Communication.TranslateToListOfCommand(tcp.getCommunicationFromClient(), ";");
            String[] data = Communication.GetDataFromCommand(command);
            System.out.println(command[0]);
            System.out.println("TASK RECEIVED :");
            if (command[0].equals("LI")) {
                System.out.println("LOG IN (LI)");
                System.out.println("DATA : " + Arrays.toString(data));
                User buffer = service.UserLogIn(data[0], data[1]);
                if (!buffer.getUsername().equals("")) {
                    data = buffer.GetUserData();
                    tcp.setCommunicationToClient(Communication.TranslateToCommunication("SUCCESS",data, ";"));
                    tcp.SendCommunication();
                }
                else
                {
                    tcp.setCommunicationToClient("FAILED");
                    tcp.SendCommunication();
                }
            } else if (command[0].equals("SU")) {
                System.out.println("SIGN UP (SU)");
                System.out.println("DATA : " + Arrays.toString(data));
                service.UserSignUp(data[0], data[1], data[2], data[3], LocalDate.parse(data[4]));
                tcp.setCommunicationToClient("SUCCESS");
                tcp.SendCommunication();
            }
            //For Debugging
            //MenuCLI.StartUpMenuCLI();
        }
    }

}
