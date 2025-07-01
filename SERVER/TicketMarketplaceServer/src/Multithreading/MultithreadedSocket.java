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
    public MultithreadedSocket(Service par, int serverPort) throws Exception {
        this.parent = par;
        this.serverSocket = new ServerSocket(serverPort);
        clients = new ArrayList<>();
    }

    //GETTER AND SETTER
    public ArrayList<SocketHandler> getClients() {
        return this.clients;
    }

    //FUNCTION
    ////MULTITHREAD
    @Override
    public void run() {
        try {
            while (true) {
                Socket incomingClient = this.serverSocket.accept();
                System.out.println("INCOMING CLIENT: \n" + incomingClient);
                SocketHandler client = new SocketHandler(incomingClient, this);
                client.start();
            }
        } catch (Exception ex) {
            System.out.println("ERROR IN SERVER SOCKET MULTITHREAD: \n" + ex);
        }
    }

    ////SOCKET HANDLER
    public SocketHandler SelectingClientSocketByUsername(String username) throws Exception {
        System.out.println("SELECTING CLIENT");
        for (SocketHandler client : clients) {
            if (client.getUsername().equals(username)) {
                System.out.println("CLIENT SELECTED: " + client.getUsername());
                return client;
            }
        }
        System.out.println("NO CLIENT SELECTED");
        return null;
    }

    public void AddingClient(SocketHandler sh) {
        clients.add(sh);
        System.out.println("CLIENT ADDED");
    }

    ////TO SERVICE
    public void Runnable(Communication comm, SocketHandler client) {
        System.out.println("RUNNABLE: " + client.getClientSocket());
        System.out.println("COMM DATA: ");
        for (String s : comm.getData()) {
            System.out.println(s);
        }
        try {
            if (comm.getCommand().equals("SENDBROADCASTS")) {
                System.out.println("SEND BROADCAST");
                parent.SendBroadcasts(comm.getData()[0]);
            } else if (comm.getCommand().equals("REQUESTREFUND")) {
                System.out.println("REQUEST REFUND");
                parent.RequestRefund(comm.getData()[0]);
            } else if (comm.getCommand().equals("REFUNDRESPONSE")) {
                System.out.println("RESPONSE REFUND");
                parent.ResponseRefund(comm.getStatus(), comm.getData()[0]);
            }
        } catch (Exception ex) {
            System.out.println("ERROR IN RUNNABLE: " + ex);
        }
    }

}
