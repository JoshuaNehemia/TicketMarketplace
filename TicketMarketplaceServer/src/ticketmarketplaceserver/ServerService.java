/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplaceserver;

import java.time.LocalDate;

// Importing Our Own Library
import TicketMarketplaceDAO.*;
import TicketMarketplaceEntities.*;
import Communication.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joshu
 */
public class ServerService implements Runnable {

    // Variable Declaration for preapartion ====================================        - Var Start -
    // For Temporary database (buat tugas progress project)
    private RepositoryTemp repo;

    //For TCP
    private ServerSocket serverSocket;

    //For Multi-Client Connection
    ArrayList<ServerSocketHandler> clients = new ArrayList<ServerSocketHandler>();

    // For Multithreading ------------------------------------------------------
    Thread t;

    // ==============================||=========================================
    // Constructor -------------------------------------------------------------        - Var End -
    public ServerService(int port) {
        repo = new RepositoryTemp(); //For Temporary Database
        //Starting serverSocket
        this.StartingServerSocket(port);
    }

    // Multithreading ----------------------------------------------------------        - Multithreading Start -
    @Override
    public void run() {
        try {
            while (true) {
                // Receiving client request for connection
                // Three-Way Handshake 
                System.out.println("Server is listening - waiting for new client...");
                Socket incomingClient = serverSocket.accept();
                System.out.println("Client connected: " + incomingClient);

                // Add client to SocketHandler
                // For server to know which client is requesting and can send response to the right client
                ServerSocketHandler clientHandler = new ServerSocketHandler(incomingClient, this);
                clientHandler.start();
                clients.add(clientHandler);
                System.out.println("Client added");
            }
        } catch (Exception ex) {
            Logger.getLogger(ServerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread (this,"client");
            t.start();
        }
    }

    // -------------------------------||----------------------------------------        - Multithreading End - 
    //TCPManager ---------------------------------------------------------------
    private void StartingServerSocket(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server is running!");
        } catch (Exception ex) {
            System.out.println("Warning : " + ex.getMessage());
        }
    }

    public final void SendToClient(String _username, String _message) {
        try {
            ServerSocketHandler _client = this.DetermineClient(_username);
            System.out.println("Server preparing to send a message: ");
            System.out.println(_message);

            _client.SendMessage(_message);

            System.out.println("Message sent");
        } catch (Exception ex) {
            System.out.println("Warning : " + ex.getMessage());
        }
    }

    private ServerSocketHandler DetermineClient(String _username) {
        for (ServerSocketHandler client : this.clients) {
            if (client.getUsername().equals(_username)) {
                return client;
            }
        }
        return null;
    }

    public final void CloseServerSocket() {
        try {
            this.serverSocket.close();
        } catch (Exception ex) {
            System.out.println("Warning : " + ex.getMessage());
        }
    }

    public void broadcast(String _message, ServerSocketHandler sender) throws IOException {
        for (ServerSocketHandler client : clients) {
            if (client != sender) {
                client.SendMessage(_message);
            }
        }
    }

    public void RemoveClient(ServerSocketHandler clientHandler) {
        clients.remove(clientHandler);
    }

    // Service Runnable --------------------------------------------------------
    //User ---------------------------------------------------------------------
    public void UserSignUp(String username, String password, String fullname, String email, LocalDate birthdate) throws IOException {
        System.out.println("SIGN UP (SU)");

        //Using temporary Database
        try {
            repo.ListUser.add(new User(username, password, fullname, email, birthdate));
        } catch (Exception ex) {

        }

        this.SendToClient(username, new Communication(username, "SUCCESS", null).getMessage());
    }

    /*
    * @param username 
    * @param password
     */
    public void UserLogIn(String _username, String password) throws IOException {
        System.out.println("LOG IN (LI)");
        User buffer = new User();

        //Using temporary Database
        for (User u : repo.ListUser) {
            if (u.getUsername().equals(_username) && u.getPassword().equals(password)) {
                buffer = u;
            }
        }
        String communication = "";
        if (!buffer.getUsername().equals("")) {
            communication = new Communication(_username, "SUCCESS", buffer.GetUserData()).getMessage();
        } else {
            communication = new Communication(_username, "FAILED", null).getMessage();
        }
        this.SendToClient(_username, communication);
    }

    //Seller -------------------------------------------------------------------
    public void SellerSignUp(String username, String password, String companyName, String companyAddress, String phoneNumber, String email) {

        // Add to temporary database
        repo.ListSeller.add(new Seller(username, password, companyName, companyAddress, phoneNumber, email));

    }

    /*
    * @param username 
    * @param password
     */
    public Seller SellerLogIn(String username, String password) {
        //Using temporary Database
        for (Seller s : repo.ListSeller) {
            if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
                return s;
            }
        }
        return new Seller();
    }

    // Venue -------------------------------------------------------------------
    public void InsertNewVenue(String name, String address, int maxCapacity, int area, City cityId) {
        int newId = 1;
        int listVenueSize = repo.ListVenue.size();
        if (listVenueSize > 1) {
            newId = repo.ListVenue.get(listVenueSize - 1).getId() + 1;
        }
        repo.ListVenue.add(new Venue(newId, name, address, maxCapacity, area, cityId));

    }

    public Venue SelectVenue(String venueName) {
        for (Venue v : repo.ListVenue) {
            if (v.getName().equals(venueName)) {
                return v;
            }
        }
        return new Venue();
    }

    public void InsertEvent(int id, String name, String description, LocalDate dateTime, int maxBuy, Venue venue, Seller seller_username, Event_category event_category) {
        int newId = 1;
        int listEventSize = repo.ListEvent.size();
        if (listEventSize > 1) {
            newId = repo.ListEvent.get(listEventSize - 1).getId() + 1;
        }
        repo.ListEvent.add(new Event(newId, name, description, dateTime, maxBuy, venue, seller_username, event_category));
    }

}
