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
import java.util.Arrays;
import java.util.Optional;
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
            } else if (_command.equals("SU-SELLER")) {
                comm = this.SellerSignUp(_data[0], _data[1], _data[4], _data[5], _data[3], _data[2]);
            } else if (_command.equals("LI-SELLER")) {
                comm = this.SellerLogIn(_data[0], _data[1]);
            } else if (_command.equals("BT")) {
//                comm = this.BuyTicket(_data[0], _data[1],_data[2],_data[2],_data[3], data[4]);
                //username namaEvent classKursi rowKursi colKursi paymentMethod
            } else if (_command.equals("SLV")) {
                comm = this.SelectListVenue();
            } else if (_command.equals("SV")) {
                comm = this.SelectVenue(_data[0]);
            } else if (_command.equals("SP")) {
                comm = this.SelectProvince(_data[0]);
            } else if (_command.equals("SC")) {
                comm = this.SelectCity(_data[0]);
            } else if (_command.equals("INE")) {
                comm = this.InsertNewEvent(_data[1], _data[2], LocalDate.parse(_data[3]), this.SelectVenueByName(_data[4]), this.SelectSellerByUsername(_data[5]));
            } else if (_command.equals("INEC")) {
                comm = this.InsertNewEventClass(Integer.parseInt(_data[0]), Integer.parseInt(_data[1]), _data[2], Double.parseDouble(_data[3]), _data[4], Integer.parseInt(_data[5]), Integer.parseInt(_data[6]));
            }else if (_command.equals("SEI")) {
                //comm = this.InsertNewEventClass(Integer.parseInt(_data[0]), Integer.parseInt(_data[1]), _data[2], Double.parseDouble(_data[3]), _data[4], Integer.parseInt(_data[5]), Integer.parseInt(_data[6]));
            }else if (_command.equals("SSE")) {
                //comm = this.InsertNewEventClass(Integer.parseInt(_data[0]), Integer.parseInt(_data[1]), _data[2], Double.parseDouble(_data[3]), _data[4], Integer.parseInt(_data[5]), Integer.parseInt(_data[6]));
            }else if (_command.equals("SEC")) {
                //comm = this.InsertNewEventClass(Integer.parseInt(_data[0]), Integer.parseInt(_data[1]), _data[2], Double.parseDouble(_data[3]), _data[4], Integer.parseInt(_data[5]), Integer.parseInt(_data[6]));
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
                String errorMsg = "Wrong Username or Password";
                return new Communication(username, "FAILED" + errorMsg, null);
            }
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            return null;
        }
    }

    // Ticket ------------------------------------------------------------------
    public Communication BuyTicket(String username, String classKursi, String rowKursi, String colKursi, String paymentMethod) {

        System.out.println(InteractiveIO.YellowMessage("BUY TICKET (BT)"));
        /*
        //Using temporary Database
        try {
            //find seat
            Optional<Seat> optSeat = repo.listSeat.stream()
            .filter(s -> 
                //s.getEvent_class().getEvent().getName().equalsIgnoreCase(namaEvent) &&
                s.getEvent_class().getName().equalsIgnoreCase(classKursi) &&
                s.getRows().equalsIgnoreCase(rowKursi) &&
                s.getColumn() == Integer.parseInt(colKursi)
            )
            .findFirst();
        Seat seat = optSeat.get();
        Event_class eventClass = seat.getEvent_class();
        eventClass.setStock(eventClass.getStock() - 1);
        
        

        // generate next TicketID
        int nextId = repo.ListTicket.stream()
                         .mapToInt(Ticket::getId)
                         .max()
                         .orElse(0) + 1;
        
        // find payment method
        Optional<Payment_method> optPm = repo.listPaymentMethod.stream()
            .filter(pm -> pm.getNama().equalsIgnoreCase(paymentMethod))
            .findFirst();
        Payment_method pm = optPm.get();
        
        //find user
        Optional<User> optUser = repo.ListUser.stream()
            .filter(u -> u.getUsername().equals(username))
            .findFirst();

        User user = optUser.get();
        
         Ticket newTicket = new Ticket(
            user,
            seat,
            nextId,
            LocalDate.now(),
            pm
        );
        repo.ListTicket.add(newTicket);
        return new Communication(username, "SUCCESS", null);
        
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            return null;
        }*/
        return null; //for temporary NANTI DIHAPUS
    }

    //Private
    private Venue SelectVenueByName(String venueName) {
        for (Venue v : this.repo.ListVenue) {
            if (v.getName().equals(venueName)) {
                return v;
            }
        }
        return null;
    }

    private Seller SelectSellerByUsername(String sellerUsername) {
        for (Seller v : this.repo.ListSeller) {
            if (v.getUsername().equals(sellerUsername)) {
                return v;
            }
        }
        return null;
    }

    private int SelectEventById(int eventId) {
        for (int i = 0; i < this.repo.ListEvent.size(); i++) {
            if (this.repo.ListEvent.get(i).getId() == eventId) {
                return i;
            }
        }
        return -1;
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

    public Communication SelectListVenue() {
        System.out.println(InteractiveIO.YellowMessage("SELECT LIST VENUE (SLV)"));
        try {
            String[] data = new String[this.repo.ListVenue.size() + 1];
            data[0] = String.valueOf(this.repo.ListVenue.size());
            for (int i = 0; i < this.repo.ListVenue.size(); i++) {
                data[1 + i] = repo.ListVenue.get(i).getName();
            }
            return new Communication("SLV", "SUCCESS", data);
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            return null;
        }

    }

    public Communication SelectProvince(String provinceName) {
        System.out.println(InteractiveIO.YellowMessage("SELECT PROVINCE (SP)"));
        try {
            for (Province p : this.repo.ListProvince) {
                if (p.getName().equals(provinceName)) {
                    return new Communication("SP", "SUCCESS", p.GetProvinceData());
                }
            }
            return new Communication("SP", "FAILED", null);
        } catch (Exception ex) {
            return null;
        }
    }

    public Communication SelectCity(String cityName) {
        System.out.println(InteractiveIO.YellowMessage("SELECT CITY (SC)"));
        try {
            for (City c : this.repo.ListCity) {
                if (c.getName().equals(cityName)) {
                    return new Communication("dummy", "SUCCESS", c.GetCityData());
                }
            }
            return new Communication("SC", "FAILED", null);
        } catch (Exception ex) {
            return null;
        }
    }

    public Communication SelectVenue(String venueName) {
        System.out.println(InteractiveIO.YellowMessage("SELECT VENUE (SV)"));
        try {
            for (Venue v : this.repo.ListVenue) {
                if (v.getName().equals(venueName)) {
                    return new Communication("SV", "SUCCESS", v.GetVenueData());
                }
            }
            return new Communication("SV", "FAILED", null);
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            return null;
        }

    }

    public Communication InsertNewEvent(String name, String description, LocalDate startDateTime, Venue venue, Seller seller) {
        try {
            System.out.println(InteractiveIO.YellowMessage("INSERT NEW EVENT (INE)"));
            int newId = 1;
            if (repo.ListEvent.size() != 0) {
                newId = repo.ListEvent.get(repo.ListEvent.size() - 1).getId() + 1;
            }
            this.repo.ListEvent.add(new Event(newId, name, description, startDateTime, venue, seller));
            String[] data = new String[1];
            data[0] = String.valueOf(newId);
            return new Communication(seller.getUsername(), "SUCCESS", data);
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            return null;
        }
    }

    public Communication InsertNewEventClass(int eventId, int ecId, String name, double price, String description, int numRows, int numCols) {
        try {
            System.out.println(InteractiveIO.YellowMessage("INSERT NEW EVENT CLASS (INEC)"));
            int index = this.SelectEventById(eventId);
            this.repo.ListEvent.get(index).addEventClasses(new Event_class(ecId, name, price, description, numRows, numCols));
            return new Communication("dummy", "SUCCESS", null);
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex);
            return null;
        }
    }

    public Communication SelectEvent(int eventId) { 
        try {
            System.out.println(InteractiveIO.YellowMessage("SELECT EVENT ID (SEI)"));
            for (Event v : repo.ListEvent) {
                if (v.getId() == (eventId)) {
                    return new Communication("SEI", "SUCCESS", v.GetEventData());
                }
            }
            return new Communication("SEI", "FAILED", null);
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            return null;
        }

    }

    public Communication SellerSelectEvent(String username) { 
        try {
            System.out.println(InteractiveIO.YellowMessage("SELLER SELECT EVENT (SE)"));
            ArrayList<String> list = new ArrayList<String>();
            for (Event v : repo.ListEvent) {
                if (v.getSeller().getUsername().equals(username)) {
                    list.add(String.valueOf(v.getId()));
                }
            }
            if (list.size() > 0) {
                return new Communication("SSE", "SUCCESS", list.toArray(new String[0]));
            } else {
                return new Communication("SSE", "FAILED", null);

            }
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            return null;
        }

    }

    public Communication SelectEventClass(int eventId, int eventClassId) {
        try {
            System.out.println(InteractiveIO.YellowMessage("SELECT EVENT CLASS (SEC)"));
            int index = this.SelectEventById(eventId);
            for (Event_class ec : this.repo.ListEvent.get(index).getEventClasses()) {
                if (ec.getId() == eventClassId) {
                    return new Communication("SEC", "SUCCESS", ec.GetEventClassData());
                }
            }
            return new Communication("SEC", "FAILED", null);
        } catch (Exception ex) {
            System.out.println(InteractiveIO.RedMessage("WARNING - EXCEPTION THROWN: ") + ex.getMessage());
            return null;
        }
    }
}
