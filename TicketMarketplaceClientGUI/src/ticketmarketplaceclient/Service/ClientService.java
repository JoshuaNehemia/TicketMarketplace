/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TicketMarketplaceClient.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Communication.*;
import ticketmarketplaceclient.Service.TCPManager;



/**
 *
 * @author joshu
 */
public class ClientService {

    public boolean UserSignUp(String username, String password, String fullname, String email ) {
        List<String> data = new ArrayList<>();
        data.add(username);
        data.add(password);
        data.add(fullname);
        data.add(email);
        //data.add(birthdate.toString());
        
        String task = "SU";
        String dividers = ";";
        String message = Communication.TranslateToCommunication(task, data.toArray(new String[0]), dividers);
        System.out.println(message);
        
        tcp.setCommunicationToServer(message);
        tcp.SendToServer();
//        res=tcp.ReceivedFromServer();
        System.out.println("Pesan dari server: " + tcp.getCommunicationFromServer());
        //...send to server
        boolean res=true;
//        if(res){
//            
//        }
        
//       successful or unsucessful
        return res;
    }
    
    TCPManager tcp;
    public void ClientStartUp(){
        System.out.println("Client connection is Running");
        tcp = new TCPManager("localhost",6000);
    }
    
    
  public boolean UserLogIn(String username, String password) {
        List<String> data = new ArrayList<>();
        data.add(username);
        data.add(password);
        
        String task = "LI";
        String dividers = ";";
        String message = Communication.TranslateToCommunication(task, data.toArray(new String[0]), dividers);
        System.out.println(message);
        
        tcp.setCommunicationToServer(message);
        tcp.SendToServer();
//        res=tcp.ReceivedFromServer();
        System.out.println("Pesan dari server: " + tcp.getCommunicationFromServer());
   
        //...send to server
        boolean res=true;
//        if(res){
//            
//        }
        
//       successful or unsucessful
        return res;
    }
    
    public static void main(String[] args) {
        
    }
    
}
