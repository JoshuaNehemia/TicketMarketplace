/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplaceserver;

import Communication.Communication;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
    private String messageFromClient;
    private String messageToClient;

    public ServerSocketHandler(Socket clientSocket, ServerService server) throws Exception {
        this.server = server;
        this.clientSocket = clientSocket;
        try {

        } catch (Exception ex) {
            throw new Exception(ex);
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

    public String getMessageFromClient() {
        return messageFromClient;
    }

    public void setMessageFromClient(String messageFromClient) {
        this.messageFromClient = messageFromClient;
    }

    public String getMessageToClient() {
        return messageToClient;
    }

    public void setMessageToClient(String messageToClient) {
        this.messageToClient = messageToClient;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Function ----------------------------------------------------------------
    public final void SendMessage(String _message) {
        try {
            System.out.println("Sending Message...");
            this.sendToClient.writeBytes(_message + "\n");
            System.out.println("Message sent: " + _message);
            server.RemoveClient(this);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            
            // Getting client address
            this.sendToClient = new DataOutputStream(this.clientSocket.getOutputStream());

            // Getting client message
            this.receivedFromClient = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            setMessageFromClient(receivedFromClient.readLine());

            // Getting username 
            this.setUsername(new Communication(this.messageFromClient).getUsername());
            if ((this.messageFromClient = receivedFromClient.readLine()) != null) {
                System.out.println("Received from client: " + messageFromClient);
            }
        } 
        catch (IOException ex) {
            System.out.println("Client disconnected or error: " + ex.getMessage());
        }
    }


}
