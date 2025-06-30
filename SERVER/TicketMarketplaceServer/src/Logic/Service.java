/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

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
    private int serverPort = 1234;
    MultithreadedSocket socket;
    
    //CONSTRUCTOR
    public Service() throws Exception{
        this.InitiateServer(serverPort);
    }
    
    //GETTER AND SETTER
    
    
    //MAIN FUNCTION
    public void InitiateServer(int serverPort) throws Exception{
        socket = new MultithreadedSocket(serverPort);
        socket.start();
        System.out.println("SERVER STARTED");
    }
    
    
    ////LOGIC FUNCTION
    
    public void SendNotification(String username) throws Exception{
        this.socket.SelectingClientSocketByUsername(username).SendMessage(new Communication("NEWNOTIFICATION",true,null));
    }
    
    public void SendBroadcasts(String broadcastmessage) throws Exception{
        String[] data = new String[1];
        data[0] = broadcastmessage;
        for(SocketHandler client : socket.getClients()){
                client.SendMessage(new Communication("NEWBROADCAST",true,data));
        }
        
    }
    
}
