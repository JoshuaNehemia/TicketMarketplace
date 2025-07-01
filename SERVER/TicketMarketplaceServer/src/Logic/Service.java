/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import DAO.Connection.DatabaseConnection;
import DAO.DAO_Ticket;
import DAO.Message.DAO_Notification;
import Entities.Message.Notification;
import Multithreading.MultithreadedSocket;
import Multithreading.SocketHandler;
import Protocol.Comm.Communication;
import java.net.Socket;

/**
 *
 * @author joshu
 */
public class Service {

    //FIELD
    DatabaseConnection dbconn;
    private int serverPort = 1234;
    MultithreadedSocket socket;

    //CONSTRUCTOR
    public Service() throws Exception {
        dbconn = new DatabaseConnection();
        this.InitiateServer(serverPort);
    }

    //GETTER AND SETTER
    //MAIN FUNCTION
    public void InitiateServer(int serverPort) throws Exception {
        socket = new MultithreadedSocket(this,serverPort);
        socket.start();
        System.out.println("SERVER STARTED");
    }

    ////LOGIC FUNCTION
    
    public void SendNotification(String username, boolean status) throws Exception {
        System.out.println("SEND NOTIFICATION");
        SocketHandler sh = this.socket.SelectingClientSocketByUsername(username);
        System.out.println("SENT TO: "+ sh.getClientSocket());
        String[] data = new String[1];
        data[0] = username;
        sh.SendMessage(new Communication("NEWNOTIFICATION", status, data));
    }

    public void SendNotificationSeller (String username,String ticket_id, boolean status) throws Exception {
        System.out.println("SEND NOTIFICATION");
        SocketHandler sh = this.socket.SelectingClientSocketByUsername(username);
        System.out.println("SENT TO: "+ sh.getClientSocket());
        String[] data = new String[1];
        data[0] = ticket_id;
        sh.SendMessage(new Communication("NEWNOTIFICATION", status, data));
    }
    public void SendBroadcasts(String broadcastmessage) throws Exception {
        System.out.println("SEND BROADCASTS");
        String[] data = new String[1];
        data[0] = broadcastmessage;
        for (SocketHandler client : socket.getClients()) {
            client.SendMessage(new Communication("NEWBROADCAST", true, data));
        }

    }

    public void RequestRefund(String ticket_id) {
        System.out.println("REQUEST REFUND");
        try {
            String seller_username = DAO_Notification.Select_Seller_to_Notify(ticket_id);
            System.out.println("SET SELLER TO NOTIFY: " + seller_username);
            System.out.println("SENDING REQUESTS");
            this.SendNotificationSeller(seller_username,ticket_id, true);
        } catch (Exception ex) {
            System.out.println("ERROR IN REQUEST REFUND: " + ex);
        }
    }

    public void ResponseRefund(boolean status, String ticket_id) throws Exception {
        System.out.println("RESPONSE REFUND");
        String message ="";
        if (status) {
            message = "Request refund is approved by seller and your payment will be refunded shortly. Please make sure to check your account balance, in this 24 hours.";
        } else {
            message = "Request refund is denied by seller, therefore your ticket is still active under your name. You can still use your ticket to attend the events, or requesting refund again to the seller.";
        }
        String user = DAO_Notification.Select_User_to_Notify(ticket_id);
        System.out.println("USER: " + user);
        int num = DAO_Notification.Insert_Notification(message, user, ticket_id);
        this.SendNotification(user, status);
    }

}
