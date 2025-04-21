/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplaceserver;

import Communication.Communication;
import Communication.InteractiveIO;
import ticketmarketplaceserver.ServerService;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalDate;

/**
 *
 * @author joshu
 */
public class ServerSocketHandler extends Thread {

    private ServerService server;
    private String username;
    private Socket clientSocket;
    private DataOutputStream sendToClient;
    private BufferedReader receivedFromClient;

    public ServerSocketHandler(Socket clientSocket, ServerService server) {
        try {
            this.server = server;
            this.clientSocket = clientSocket;
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
        }
    }

    // Getter and Setter
    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public DataOutputStream getSendToClient() {
        return sendToClient;
    }

    public void setSendToClient(DataOutputStream output) {
        this.sendToClient = output;
    }

    public BufferedReader getReceivedFromClient() {
        return receivedFromClient;
    }

    public void setReceivedFromClient(BufferedReader input) {
        this.receivedFromClient = input;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // METHOD ----------------------------------------------------------------
    public final void SendMessage(String _message) {
        try {
            // Getting client address
            this.sendToClient = new DataOutputStream(this.clientSocket.getOutputStream());

            //System IO for receiving Message
            System.out.println(InteractiveIO.BlueMessage("SENDING MESSAGE:") + InteractiveIO.BlueMessage("\nTO: ") + this.getClientSocket() + InteractiveIO.BlueMessage("\nMESSAGE: ") + _message);
            this.getSendToClient().writeBytes(_message + "\n");

            //System IO for alerting successful process
            System.out.println(InteractiveIO.GreenMessage("MESSAGE SENT: ") + _message);
            this.DisconnectClient();
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
        }
    }

    public final void DisconnectClient() {
        try {
            server.RemoveClient(this);
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            // create once, not on every loop:
            this.setReceivedFromClient(new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream())));
            Communication message;
            String buffer ="";
            while ((buffer = this.getReceivedFromClient().readLine()) != null) {
                message = new Communication(buffer);
                
                //System IO for receiving Message
                System.out.println(InteractiveIO.BlueMessage("RECEIVING MESSAGE:") + InteractiveIO.BlueMessage("\nFROM: ") + this.getClientSocket() + InteractiveIO.BlueMessage("\nMESSAGE: ") + message.getMessage());

                this.setUsername(message.getUsername());

                server.RunCommand(message.getCommand(), message.getData());
                // …handle other commands…
            }
        } catch (IOException ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
        }
    }

}
