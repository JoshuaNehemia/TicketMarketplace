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
            if (clientSocket == null || clientSocket.isClosed()) {
                clientSocket = new Socket(this.getAddress(), this.getPort());
            }
            else
            {
                this.CloseClientSocket();
                this.ConnectToServer();
            }
        } catch (Exception ex) {
            System.out.println("Warning :" + ex.getMessage());
        }
    }

    public final void SendToServer() {

        try {
            DataOutputStream sendToServer = new DataOutputStream(clientSocket.getOutputStream());
            sendToServer.writeBytes(communicationToServer + "\n");
        } catch (Exception ex) {
            System.out.println("Warning :" + ex.getMessage());
        }
    }

    public final void ReceivedFromServer() {
        try {
            BufferedReader receivedFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            setCommunicationFromServer(receivedFromServer.readLine());
        } catch (Exception ex) {
            System.out.println("Warning :" + ex.getMessage());
        }
    }
    
    public final void CloseClientSocket()
    {
       try {
            clientSocket.close();
        } catch (Exception ex) {
            System.out.println("Warning :" + ex.getMessage());
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
