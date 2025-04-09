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

    TCPManager tcp;
    private String dividers = ";";
    private User currentUser;

    public ClientService() {
        System.out.println("Client connection is Running");
        tcp = new TCPManager("localhost", 6000);
        currentUser = new User();
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

        tcp.setCommunicationToServer(message);
        tcp.SendToServer();
//        res=tcp.ReceivedFromServer();
        String reply = tcp.getCommunicationFromServer();
        System.out.println("Pesan dari server: \n" + reply);
        //...send to server
        boolean res = false;
        if (reply.equals("SUCCESS")) {
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

        tcp.setCommunicationToServer(message);
        tcp.SendToServer();
//        res=tcp.ReceivedFromServer();
        tcp.ReceivedFromServer();
        String reply = tcp.getCommunicationFromServer();
        String command = Communication.TranslateToListOfCommand(reply, ";")[0];
        String[] buffer = Communication.GetDataFromCommunication(reply,";");
        System.out.println("Pesan dari server: \n" + reply);
        boolean res = false;
        if(command.equals("SUCCESS"))
        {
            this.currentUser = new User(buffer[0],buffer[1],buffer[2],buffer[3],LocalDate.parse(buffer[4]));
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
