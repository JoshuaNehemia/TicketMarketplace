/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Multithreading;

import Protocol.Comm.Communication;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author joshu
 */
public class SocketHandler extends Thread {

    //FIELDS
    private final MultithreadedSocket parent;
    private final Socket clientSocket;
    private String username;
    private final BufferedReader incoming;
    private final DataOutputStream sending;

    //CONSTRUCTOR
    public SocketHandler(Socket client, MultithreadedSocket parent) throws Exception {
        this.username = "nousernamethatwillequalltothisbecausethisisdefaultvalue";
        this.clientSocket = client;
        this.parent = parent;
        this.incoming = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.sending = new DataOutputStream(this.clientSocket.getOutputStream());
    }
    
    
    public SocketHandler() throws Exception {
        this.username = "null";
        this.clientSocket = null;
        this.parent = null;
        this.incoming = null;
        this.sending = null;
    }


    //GETTER AND SETTER
    public Socket getClientSocket(){
        return this.clientSocket;
    }
    
    //FUNCTION
    public void ReceiveMessage() throws Exception {
        String message = this.incoming.readLine();
        System.out.println("RECEIVED FROM " + this.clientSocket + " : \n" + message);
        Communication comm = new Communication(message);
        if(comm.getCommand().equals("REGISTER")){
            System.out.println("REGISTER SUCCESFULL");
            this.username = comm.getData()[0];
            parent.AddingClient(this);
        }
        System.out.println("DATA RECEIVED: ");
        for(String s : comm.getData()){
            System.out.println(s);
        }
        parent.Runnable(comm,this);
    }

    public void SendMessage(Communication comm) throws Exception {
        System.out.println("SENDING: "+ comm.getMessage());
        this.sending.writeBytes(comm.getMessage() + "\n");
    }

    ////MULTITHREADING
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("READY TO RECEIVE MESSAGE FROM: " + this.clientSocket);
                this.ReceiveMessage();
            }
        } catch (Exception ex) {
            System.out.println("ERROR IN SOCKET HANDLER MULTITHREADING:\n" + ex);
        }
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
