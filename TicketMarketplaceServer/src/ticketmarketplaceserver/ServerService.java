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

    // Variable Declaration for preapartion
    // For Temporary database (buat tugas progress project)
    private RepositoryTemp repo;

    //For TCP
    private ServerSocket serverSocket;

    //For Multi-Client Connection
    ArrayList<ServerSocketHandler> clients = new ArrayList<ServerSocketHandler>();

    // For Multithreading
    Thread t;

    // Constructor
    public ServerService(int port) {
        repo = new RepositoryTemp(); //For Temporary Database
        //Starting serverSocket
        this.StartingServerSocket(port);
    }

    // Multithreading 
    @Override
    public void run() {
        try {
            while (true) {
                // Receiving client request for connection
                // Three-Way Handshake 
                System.out.println(InteractiveIO.BlueMessage("SERVER IS LISTENING - WAITING FOR NEW CLIENT..."));
                Socket incomingClient = serverSocket.accept();
                System.out.println(InteractiveIO.GreenMessage("NEW CLIENT CONNECTED: ") + incomingClient);

                // Add client to SocketHandler
                // For server to know which client is requesting and can send response to the right client
                ServerSocketHandler clientHandler = new ServerSocketHandler(incomingClient, this);
                clientHandler.start();
                clients.add(clientHandler);
                System.out.println(InteractiveIO.GreenMessage("CLIENT SUCCESFULLY ADDED"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ServerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, "SERVER");
            t.start();
        }
    }

    //TCPManager ---------------------------------------------------------------
    private void StartingServerSocket(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println(InteractiveIO.GreenMessage("SERVER IS RUNNING!"));
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
        }
    }

    public final void CloseServerSocket() {
        try {
            this.serverSocket.close();
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
        }
    }

    public void broadcast(String _message, ServerSocketHandler _sender) throws IOException {
        for (ServerSocketHandler client : clients) {
            if (client != _sender) {
                client.SendMessage(_message);
            }
        }
    }

    public void RemoveClient(ServerSocketHandler clientHandler) {
        clients.remove(clientHandler);
    }

    // Service Runnable --------------------------------------------------------
    public void RunCommand(String _command, String[] _data, ServerSocketHandler client) {
        Communication comm = new Communication();
        try {
            if (_command.equals("SU")) {
                comm = this.UserSignUp(_data[0], _data[1], _data[2], _data[3], LocalDate.parse(_data[4]));
            } else if (_command.equals("LI")) {
                comm = this.UserLogIn(_data[0], _data[1]);
            } else {

            }

            if (comm != null) {
                client.SendMessage(comm.getMessage());
            } else {
                String errorMsg = "SYSTEM-FAILED";
                client.SendMessage(new Communication(client.getUsername(), errorMsg, null).getMessage());
            }
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
        }

    }

    //User ---------------------------------------------------------------------
    public Communication UserSignUp(String username, String password, String fullname, String email, LocalDate birthdate) {
        System.out.println(InteractiveIO.YellowMessage("SIGN UP (SU)"));

        boolean usernameExists = repo.ListUser.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(username));

        try {
            if (usernameExists) {
                String errorMsg = "The username '" + username + "' is already taken";
                System.out.println(InteractiveIO.RedMessage("WARNING: " + errorMsg));
                return new Communication(username, "FAILED" + errorMsg, null);
            }
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
        }

        try {
            repo.ListUser.add(new User(username, password, fullname, email, birthdate));
            return new Communication(username, "SUCCESS", null);
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            try {
                return new Communication(username, "FAILED" + ex.getMessage(), null);
            } catch (Exception excep) {
                System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
                return null;
            }
        }
    }


    /*
    * @param username 
    * @param password
     */
    public Communication UserLogIn(String _username, String _password) {
        System.out.println(InteractiveIO.YellowMessage("LOG IN (LI)"));
        User buffer = new User();

        //Using temporary Database
        try {
            for (User u : repo.ListUser) {
                if (u.getUsername().equals(_username) && u.getPassword().equals(_password)) {
                    buffer = u;
                }
            }
            String communication = "";
            if (!buffer.getUsername().equals("")) {
                return new Communication(buffer.getUsername(), "SUCCESS", buffer.GetUserData());
            } else {
                return new Communication(_username, "FAILED", null);
            }
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            return null;
        }
    }

    //Seller -------------------------------------------------------------------
    public Communication SellerSignUp(String username, String password, String companyName, String companyAddress, String phoneNumber, String email) {
        //Seller Sign Up SSU

        System.out.println(InteractiveIO.YellowMessage("SELLER SIGN UP (SSU)"));

        boolean usernameExists = repo.ListSeller.stream().anyMatch(seller -> seller.getUsername().equalsIgnoreCase(username));

        try {
            if (usernameExists) {
                String errorMsg = "The username '" + username + "' is already taken";
                System.out.println(InteractiveIO.RedMessage("WARNING: " + errorMsg));
                return new Communication(username, "FAILED" + errorMsg, null);
            }
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
        }

        try {
            repo.ListSeller.add(new Seller(username, password, companyName, companyAddress, phoneNumber, email));
            return new Communication(username, "SUCCESS", null);
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            try {
                return new Communication(username, "FAILED" + ex.getMessage(), null);
            } catch (Exception excep) {
                System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
                return null;
            }
        }

    }

    /*
    * @param username 
    * @param password
     */
    public Communication SellerLogIn(String username, String password) {

        System.out.println(InteractiveIO.YellowMessage("SELLER LOG IN (SLI)"));
        Seller buffer = new Seller();

        //Using temporary Database
        try {
            for (Seller sel : repo.ListSeller) {
                if (sel.getUsername().equals(username) && sel.getPassword().equals(password)) {
                    buffer = sel;
                }
            }
            String communication = "";
            if (!buffer.getUsername().equals("")) {
                return new Communication(buffer.getUsername(), "SUCCESS", buffer.GetSellerData());
            } else {
                return new Communication(username, "FAILED", null);
            }
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            return null;
        }
    }

    // Venue -------------------------------------------------------------------
    public void InsertNewVenue(String name, String address, int maxCapacity, int area, City cityId) {
        int newId = 1;
        if (repo.ListVenue.size() != 0) {
            newId = repo.ListVenue.get(repo.ListVenue.size() - 1).getId() + 1;
        }
        int listVenueSize = repo.ListVenue.size();
        if (listVenueSize > 1) {
            newId = repo.ListVenue.get(listVenueSize - 1).getId() + 1;
        }
        repo.ListVenue.add(new Venue(newId, name, address, maxCapacity, area, cityId));

    }

    public Communication SelectVenue(String venueName) {
        try {
            for (Venue v : repo.ListVenue) {
                if (v.getName().equals(venueName)) {
                    return new Communication("dummy", "SUCCESS", v.GetVenueData());
                }
            }
            return new Communication("dummy", "FAILED", null);
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            return null;
        }

    }

    public void InsertEvent(int id, String name, String description, LocalDate dateTime, int maxBuy, Venue venue, Seller seller_username, Event_category event_category) {
        int newId = 1;
        if (repo.ListEvent.size() != 0) {
            newId = repo.ListEvent.get(repo.ListEvent.size() - 1).getId() + 1;
        }
        repo.ListEvent.add(new Event(newId, name, description, dateTime, maxBuy, venue, seller_username, event_category));
    }

    public Communication SelectEvent(String eventName) {
        try {
            for (Event v : repo.ListEvent) {
                if (v.getName().equals(eventName)) {
                    return new Communication("dummy", "SUCCESS", v.GetEventData());
                }
            }
            return new Communication("dummy", "FAILED", null);
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            return null;
        }

    }
    
    


}
