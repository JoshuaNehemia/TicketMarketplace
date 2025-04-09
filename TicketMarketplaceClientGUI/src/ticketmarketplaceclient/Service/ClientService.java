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
    
    private void ReceivedFromServer()
    {   
        tcp.ReceivedFromServer();
        String communication = tcp.getCommunicationFromServer();
        System.out.println("Message received from server: \n" + communication);
        this.commandReceived= Communication.TranslateToListOfCommand(communication, dividers)[0];
        System.out.println("Message processed!");
        this.dataReceived = Communication.GetDataFromCommunication(communication, dividers);
        System.out.println("Data received!");
        tcp.CloseClientSocket();
        tcp.ConnectToServer();
        
    }
    public boolean UserSignUp(String username, String password, String fullname, String email, LocalDate birthdate) {
        List<String> data = new ArrayList<>();
        data.add(username);
        data.add(password);
        data.add(fullname);
        data.add(email);
        data.add(birthdate.toString());
        //data.add(birthdate.toString());

        String task = "SU";
        String message = Communication.TranslateToCommunication(task, data.toArray(new String[0]), dividers);
        System.out.println(message);
        this.SendToServer(message);
//        res=tcp.ReceivedFromServer();
        this.ReceivedFromServer();
        //...send to server
        boolean res = false;
        if (this.commandReceived.equals("SUCCESS")) {
            res = true;
        }

//       successful or unsucessful
        return res;
    }

    public boolean UserLogIn(String username, String password) {
        List<String> data = new ArrayList<>();
        data.add(username);
        data.add(password);

        String task = "LI";
        String message = Communication.TranslateToCommunication(task, data.toArray(new String[0]), dividers);
        System.out.println(message);
        this.SendToServer(message);
        
        this.ReceivedFromServer();
        boolean res = false;
        if(this.commandReceived.equals("SUCCESS"))
        {
            this.currentUser = new User(this.dataReceived[0],this.dataReceived[1],this.dataReceived[2],this.dataReceived[3],LocalDate.parse(this.dataReceived[4]));
            res = true;
        }
        else
        {
            res = false;
        }

                
//       successful or unsucessful
        return res;
    }
}
