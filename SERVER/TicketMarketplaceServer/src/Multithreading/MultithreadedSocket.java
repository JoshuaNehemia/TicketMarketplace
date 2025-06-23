/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Multithreading;

import Logic.Service;
import Protocol.Comm.Communication;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class MultithreadedSocket extends Thread {

    //FIELDS
    private Service parent;
    private ServerSocket serverSocket;
    private ArrayList<SocketHandler> clients;

    //CONSTRUCTOR
    public MultithreadedSocket(int serverPort) throws Exception {
        this.serverSocket = new ServerSocket(serverPort);
        clients = new ArrayList<>();
    }

    //GETTER AND SETTER
    
    
    //FUNCTION
    ////MULTITHREAD
    @Override
    public void run() {
        try {
            while(true){
                Socket incomingClient = this.serverSocket.accept();
                System.out.println("INCOMING CLIENT: \n" + incomingClient);
                SocketHandler client = new SocketHandler(incomingClient,this);
                client.start();
                clients.add(client);
                System.out.println("CLIENT ADDED");
            }
        } catch (Exception ex) {
            System.out.println("ERROR IN SERVER SOCKET MULTITHREAD: \n" + ex);
        }
    }
    
    ////SOCKET HANDLER
    public void BroadcastMessage(String message) throws Exception{
        for(SocketHandler sh:clients){
            sh.SendMessage(message);
        }
    }
    
    ////TO SERVICE
    public Communication Runnable(String[] data){
        
        return new Communication();
    }

}
