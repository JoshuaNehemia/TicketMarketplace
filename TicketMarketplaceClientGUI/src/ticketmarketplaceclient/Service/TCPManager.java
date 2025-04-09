/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplaceclient.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author joshu
 */
public class TCPManager {

    private String address = "";
    private int port = 0;
    private String communicationFromServer = "";
    private String communicationToServer = "";
    Socket clientSocket;

    public TCPManager(String address, int port) {
            this.setAddress(address);
            this.setPort(port);
            this.ConnectToServer();
    }

    public final void ConnectToServer() {
        try {
            clientSocket = new Socket(this.getAddress(), this.getPort());
            System.out.println("Connected to server");

        } catch (Exception ex) {
            System.out.println("Warning :" + ex.getMessage());
        }
    }
    
    public final void SendToServer() {
    try {
        // open connection if it not opened yet
        if (clientSocket == null || clientSocket.isClosed()) {
            this.ConnectToServer();
        }

        DataOutputStream sendToServer = new DataOutputStream(clientSocket.getOutputStream());
        System.out.println("Sending: " + communicationToServer);
        sendToServer.writeBytes(communicationToServer + "\n");
        sendToServer.flush();
    } catch (Exception ex) {
        System.out.println("Warning (SendToServer): " + ex.getMessage());
    }
}

public final void ReceivedFromServer() {
    try {
        BufferedReader receivedFromServer = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
        );
        communicationFromServer = receivedFromServer.readLine();
        System.out.println("Received: " + communicationFromServer);
        clientSocket.close();
    } catch (Exception ex) {
        System.out.println("Warning (ReceivedFromServer): " + ex.getMessage());
    }
}


    public String getCommunicationFromServer() {
        return communicationFromServer;
    }

    public void setCommunicationFromServer(String communicationFromServer) {
        this.communicationFromServer = communicationFromServer;
    }

    public String getCommunicationToServer() {
        return communicationToServer;
    }

    public void setCommunicationToServer(String communicationToServer) {
        this.communicationToServer = communicationToServer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
