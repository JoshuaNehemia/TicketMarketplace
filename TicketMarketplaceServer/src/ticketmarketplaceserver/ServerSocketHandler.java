/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplaceserver;

import Communication.Communication;
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
            // Getting client address
            this.sendToClient = new DataOutputStream(this.clientSocket.getOutputStream());
            System.out.println("Sending Message to" + this.clientSocket);
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
        // create once, not on every loop:
        this.receivedFromClient =
            new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

        String line;
        while ((line = receivedFromClient.readLine()) != null) {
            System.out.println("Received from client: " + line);

            this.messageFromClient = line;
            
            this.setUsername(new Communication(line).getUsername());

            Communication message = new Communication(line);
            String cmd  = message.getCommand();
            String[] data = message.getData();

            if ("SU".equals(cmd)) {
                LocalDate birthdate = LocalDate.parse(data[4]);
                server.UserSignUp(
                    data[0], data[1], data[2], data[3], birthdate
                );
            }else if("LI".equals(cmd)){
                server.UserLogIn(data[0], data[1]);
            }
            // …handle other commands…
        }
    } catch (IOException ex) {
        System.out.println("Client disconnected or error: " + ex.getMessage());
    }
}


}
