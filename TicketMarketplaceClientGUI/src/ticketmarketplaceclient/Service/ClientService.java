/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplaceclient.Service;

import Communication.Communication;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import TicketMarketplaceEntities.*;
import java.io.IOException;

/**
 *
 * @author joshu
 */
public class ClientService {

    //For TCP
    private TCPManager tcp;
    private String commandReceived = "";
    private String[] dataReceived;

    //Communication
    private String dividers = ";";
    
    //Service 
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ClientService() {
        System.out.println("Client connection is Running");
        tcp = new TCPManager("localhost", 6000);
        currentUser = new User();
    }

    public void SendToServer(String message)
    {
        tcp.setCommunicationToServer(message);
        tcp.SendToServer();
        System.out.println("Sent to server");
    }
    
    private void ReceivedFromServer() throws IOException
    {   
        tcp.ReceivedFromServer();
        String message = tcp.getCommunicationFromServer();
        System.out.println("Message received from server: \n" + message);
        this.commandReceived=  message;
        System.out.println("Message processed!");
        System.out.println("Data received!");
        tcp.CloseClientSocket();
        tcp.ConnectToServer();
        
    }
    public boolean UserSignUp(String username, String password, String fullname, String email, LocalDate birthdate) throws IOException {
        List<String> data = new ArrayList<>();
        data.add(username);
        data.add(password);
        data.add(fullname);
        data.add(email);
        data.add(birthdate.toString());

        String task = "SU";
        String message = new Communication(username,task,data.toArray(new String[0])).getMessage();
        System.out.println(message);
        this.SendToServer(message);
        this.ReceivedFromServer();
        Communication received = new Communication(this.commandReceived);
        dataReceived=received.getData();
        boolean res = false;
        if(received.getCommand().equals("SUCCESS"))res=true;
        
        return res;
    }

    public boolean UserLogIn(String username, String password) throws IOException {
        List<String> data = new ArrayList<>();
        data.add(username);
        data.add(password);

        String task = "LI";
        String message = new Communication(username,task,data.toArray(new String[0])).getMessage();
        System.out.println(message);
        this.SendToServer(message);
        this.ReceivedFromServer();
        Communication received = new Communication(this.commandReceived);
        dataReceived=received.getData();
        boolean res = false;
        if(received.getCommand().equals("SUCCESS"))
        {
            res=true;
            this.setCurrentUser(new User(this.dataReceived[0],this.dataReceived[1],this.dataReceived[2],this.dataReceived[3],LocalDate.parse(this.dataReceived[4])));
        }
        
        return res;
    }
}
