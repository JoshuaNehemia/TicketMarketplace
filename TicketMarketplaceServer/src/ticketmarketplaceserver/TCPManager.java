    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplaceserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Communication.*;

/**
 *
 * @author joshu
 */
public class TCPManager {


    private String communicationFromClient="";
    private String communicationToClient="";
    Socket incomingSocket;
    ServerSocket s;

    public TCPManager(int port) {
        try {
            this.s = new ServerSocket(port);
        } catch (Exception ex) {
            System.out.println("Warning : " + ex.getMessage());
        }
    }

    public final void ReceivedCommunication() {
        try {
            incomingSocket = s.accept();
            BufferedReader receivedFromClient = new BufferedReader(new InputStreamReader(incomingSocket.getInputStream()));
            communicationFromClient = receivedFromClient.readLine();
        } catch (Exception ex) {
            System.out.println("Warning : " + ex.getMessage());
        }
    }
    
    public final void SendCommunication()
    {
        try{
            DataOutputStream sendToClient = new DataOutputStream (incomingSocket.getOutputStream());
            sendToClient.writeBytes(communicationToClient + "\n");
            s.close();
        }
        catch (Exception ex)
        {
            System.out.println("Warning : " + ex.getMessage()); 
        }
    }
        public String getCommunicationFromClient() {
        return communicationFromClient;
    }

    public void setCommunicationFromClient(String communicationFromClient) {
        this.communicationFromClient = communicationFromClient;
    }

    public String getCommunicationToClient() {
        return communicationToClient;
    }

    public void setCommunicationToClient(String communicationToClient) {
        this.communicationToClient = communicationToClient;
    }

    
}
