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
import java.util.Set;

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
    public ArrayList<SocketHandler> getClients(){
        return this.clients;
    }
    
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
    public SocketHandler SelectingClientSocketByUsername(String username){
        for(SocketHandler client : clients){
            if(client.getUsername().equals(username)){
                return client;
            }
        }
        return null;
    }
    
    public void RegisterToServer(SocketHandler socketH, String username){
        for(SocketHandler cl: clients){
            if(cl.getClientSocket().toString().equals(socketH.getClientSocket().toString())){
                clients.remove(cl);
                socketH.setUsername(username);
                clients.add(socketH);
                System.out.println("REGISTER SUCCESFUL");
            }
        }
    }
    
    ////TO SERVICE
    public Communication Runnable(Communication comm,SocketHandler client) throws Exception{
        if(comm.getCommand().equals("REGISTER")){
            this.RegisterToServer(client,comm.getData()[0]);
        }
        else if(comm.getCommand().equals("SENDNOTIFICATION")){
            parent.SendNotification(comm.getData()[0]);
        }
        else if (comm.getCommand().equals("SENDBROADCASTS")){
            parent.SendBroadcasts(comm.getData()[0]);
        }
        else if (comm.getCommand().equals("REQUESTREFUND")){
            parent.SendBroadcasts(comm.getData()[0]);
        }
        return new Communication();
    }

}
