/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplaceclient.Service;

import Communication.Communication;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import TicketMarketplaceEntities.*;
import java.io.IOException;
import java.util.Arrays;
import static ticketmarketplaceclient.GUI.FormLogin.service;

/**
 *
 * @author joshu
 */
public class ClientService {

    //For TCP
    private TCPManager tcp;
    private String messageReceived = "";
    private String[] dataReceived;

    //Communication
    private String dividers = ";";

    //Service 
    public RepoTemp repo;

    private User currentUser;

    private Seller currentSeller;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Seller getCurrentSeller() {
        return currentSeller;
    }

    public void setCurrentSeller(Seller currentSeller) {
        this.currentSeller = currentSeller;
    }

    //Constructor
    public ClientService() {
        System.out.println("Client connection is Running");
        tcp = new TCPManager("localhost", 6000);
        currentUser = new User();
        currentSeller = new Seller();
        this.repo = new RepoTemp();
    }

    public void SendToServer(String message) {
        tcp.setCommunicationToServer(message);
        tcp.SendToServer();
        System.out.println("Sent to server");
    }

    private void ReceivedFromServer() throws IOException {
        tcp.ReceivedFromServer();
        String message = tcp.getCommunicationFromServer();
        System.out.println("Message received from server: \n" + message);
        this.messageReceived = message;
        System.out.println("Message processed!");
        System.out.println("Data received!");
        tcp.CloseClientSocket();
        tcp.ConnectToServer();

    }

    public boolean UserSignUp(String username, String password, String fullname, String email, LocalDate birthdate) throws IOException {

        boolean res = false;
        try {
            List<String> data = new ArrayList<>();
            data.add(username);
            data.add(password);
            data.add(fullname);
            data.add(email);
            data.add(birthdate.toString());

            String task = "SU";
            String message = new Communication(username, task, data.toArray(new String[0])).getMessage();
            System.out.println(message);
            this.SendToServer(message);

            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            dataReceived = received.getData();
            if (received.getCommand().equals("SUCCESS")) {
                res = true;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return res;
    }

    public boolean UserLogIn(String username, String password) throws IOException {
        boolean res = false;
        try {
            List<String> data = new ArrayList<>();
            data.add(username);
            data.add(password);

            String task = "LI";
            String message = new Communication(username, task, data.toArray(new String[0])).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            dataReceived = received.getData();
            if (received.getCommand().equals("SUCCESS")) {
                res = true;
                this.setCurrentUser(new User(this.dataReceived[0], this.dataReceived[1], this.dataReceived[2], this.dataReceived[3], LocalDate.parse(this.dataReceived[4])));
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return res;
    }

    public boolean SellerSignUp(String username, String password, String email, String noTlp, String vendor, String alamat) throws IOException {

        boolean res = false;
        try {
            List<String> data = new ArrayList<>();
            data.add(username);
            data.add(password);
            data.add(email);
            data.add(noTlp);
            data.add(vendor);
            data.add(alamat);

            String task = "SU-SELLER";
            String message = new Communication(username, task, data.toArray(new String[0])).getMessage();
            System.out.println(message);
            this.SendToServer(message);

            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            dataReceived = received.getData();
            if (received.getCommand().equals("SUCCESS")) {
                res = true;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return res;
    }

    public boolean SellerLogIn(String username, String password) throws IOException {
        boolean res = false;
        try {
            List<String> data = new ArrayList<>();
            data.add(username);
            data.add(password);

            String task = "LI-SELLER";
            String message = new Communication(username, task, data.toArray(new String[0])).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            dataReceived = received.getData();
            if (received.getCommand().equals("SUCCESS")) {
                res = true;
                this.setCurrentSeller(new Seller(this.dataReceived[0], this.dataReceived[1], this.dataReceived[2], this.dataReceived[3], this.dataReceived[4], this.dataReceived[5]));
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return res;
    }

    public void SelectListVenue() throws IOException {
        try {
            String message = new Communication(this.currentSeller.getUsername(), "SLV", null).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            this.dataReceived = received.getData();
            for (int i = 0; i < Integer.parseInt(this.dataReceived[0]); i++) {
                this.repo.ListDisplay.add(dataReceived[1 + i]);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Venue SelectVenue(String name) throws IOException {
        try {
            String[] data = new String[1];
            data[0] = name;
            String message = new Communication(this.currentSeller.getUsername(), "SV", data).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            String[] buffer = received.getData();
            return new Venue(Integer.parseInt(buffer[0]), buffer[1], buffer[2], Integer.parseInt(buffer[3]), Integer.parseInt(buffer[4]), this.SelectCity(buffer[5]));
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public City SelectCity(String name) throws IOException {
        try {
            String[] data = new String[1];
            data[0] = name;
            String message = new Communication(this.currentSeller.getUsername(), "SC", data).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            String[] buffer = received.getData();
            return new City(Integer.parseInt(buffer[0]), buffer[1], this.SelectProvince(buffer[2]));
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public Province SelectProvince(String name) throws IOException {
        try {
            String[] data = new String[1];
            data[0] = name;
            String message = new Communication(this.currentSeller.getUsername(), "SP", data).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            String[] buffer = received.getData();
            return new Province(Integer.parseInt(buffer[0]), buffer[1]);
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }

    }
    
    public void InsertNewEvent (Event event) throws IOException
    {
        try {
            String message = new Communication(this.currentSeller.getUsername(), "INE", event.GetEventData()).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            String[] buffer = received.getData();
            for(Event_class ec : event.getEventClasses())
            {
                this.InsertNewEventClass(Integer.parseInt(buffer[0]),ec);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void InsertNewEventClass(int eventId,Event_class eventClass) throws IOException
    {
        try {
            String[] data = new String[8];
            String[] buffer = eventClass.GetEventClassData();
            data[0] = String.valueOf(eventId);
            for(int i=0;i<7;i++)
            {
                data[1+i] = buffer[i];
            }
            String message = new Communication(this.currentSeller.getUsername(), "INEC",data ).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void SellerSelectEvent() throws IOException //Seller Select all its EVENT (Semua event yang dia punya)
    {
        try {
            String[] data = new String[1];
            data[0] = this.currentSeller.getUsername();
            String message = new Communication(this.currentSeller.getUsername(), "SSE",data ).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            String[] dataReply = received.getData();
            for(int i=0;i<dataReply.length;i++)
            {
                this.SelectEvent(Integer.parseInt(dataReply[i]));
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void SelectEvent(int eventId) //Select event and EVent class by event ID
    {
        try {
            String[] data = new String[1];
            data[0] = String.valueOf(eventId);
            String message = new Communication(this.currentSeller.getUsername(), "SEI",data ).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            String[] dataReply = received.getData();
            this.currentSeller.addEvents(new Event(Integer.parseInt(dataReply[0]), dataReply[1], dataReply[2], LocalDate.parse(dataReply[3]),this.SelectVenue(dataReply[4]), this.currentSeller));
            for(int i=0;i<Integer.parseInt(dataReply[6]);i++)
            {
                this.currentSeller.getEvents().get(this.currentSeller.getEvents().size()-1).addEventClasses(this.SelectEventClass(Integer.parseInt(dataReply[0]),Integer.parseInt(dataReply[7+i])));
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
    
    public void UserSelectEvent(int eventId) //Select event and EVent class by event ID
    {
        try {
            String[] data = new String[1];
            data[0] = String.valueOf(eventId);
            String message = new Communication(this.currentUser.getUsername(), "SEI",data ).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            String[] dataReply = received.getData();
            if (dataReply == null) {
                Event emptyEvent = new Event(0, "Event Tidak Ditemukan", "", LocalDate.now(), null, null);
                service.repo.ListEvent.add(emptyEvent);
                return;
            }
            Event e = new Event(Integer.parseInt(dataReply[0]), dataReply[1], dataReply[2], LocalDate.parse(dataReply[3]),this.SelectVenue(dataReply[4]), this.currentSeller);
            ArrayList<Event_class> ec = UserSelectEventClass(0);    
            e.setEventClasses(ec);
            service.repo.ListEvent.add(e);            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
    
    
    
    public Event_class SelectEventClass(int eventId, int eventClassId)
    {
        try {
            String[] data = new String[2];
            data[0] = String.valueOf(eventId);
            data[1] = String.valueOf(eventClassId);
            String message = new Communication(this.currentSeller.getUsername(), "SEC",data ).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            String[] dataReply = received.getData();
            return new Event_class(Integer.parseInt(dataReply[0]), dataReply[1], Double.parseDouble(dataReply[2]),dataReply[3],Integer.parseInt(dataReply[4]),Integer.parseInt(dataReply[5]), Integer.parseInt(dataReply[6]));
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public String[] UserEventClassById(int eventId, int eventClassId)
    {
        try {
            String[] data = new String[2];
            data[0] = String.valueOf(eventId);
            data[1] = String.valueOf(eventClassId);
            String message = new Communication(this.currentUser.getUsername(), "SECBYID",data ).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            String[] dataReply = received.getData();
                        System.out.println("data ec"+Arrays.toString(dataReply));

            return dataReply;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    
    
//     public List<Event_class> UserSelectEventClass(int eventId)
//    {
//        try {
//            String[] data = new String[2];
//            data[0] = String.valueOf(eventId);
//            String message = new Communication(this.currentUser.getUsername(), "SEC",data ).getMessage();
//            System.out.println(message);
//            this.SendToServer(message);
//            this.ReceivedFromServer();
//            Communication received = new Communication(this.messageReceived);
//            String[] dataReply = received.getData();
//            return new Event_class(Integer.parseInt(dataReply[0]), dataReply[1], Double.parseDouble(dataReply[2]),dataReply[3],Integer.parseInt(dataReply[4]),Integer.parseInt(dataReply[5]), Integer.parseInt(dataReply[6]));
//        } catch (Exception ex) {
//            System.out.println(ex);
//            return null;
//        }
//    }
       public ArrayList<Event_class> UserSelectEventClass(int eventId) {
    ArrayList<Event_class> eventClasses = new ArrayList<>();
    try {
        String[] data = new String[1];
        data[0] = String.valueOf(eventId);
        String message = new Communication(this.currentUser.getUsername(), "SEC", data).getMessage();
        System.out.println(message);
        this.SendToServer(message);
        this.ReceivedFromServer();
        Communication received = new Communication(this.messageReceived);
        String[] dataReply = received.getData();
        
        for (int i = 0; i < dataReply.length; i += 7) {
            Event_class ec = new Event_class(
                Integer.parseInt(dataReply[i]), 
                dataReply[i + 1], 
                Double.parseDouble(dataReply[i + 2]), 
                dataReply[i + 3], 
                Integer.parseInt(dataReply[i + 4]), 
                Integer.parseInt(dataReply[i + 5]), 
                Integer.parseInt(dataReply[i + 6])
            );
            eventClasses.add(ec);
        }
        
    } catch (Exception ex) {
        System.out.println(ex);
    }
    return eventClasses;
}
       
   public double CalculatePrice(int eventId, int eventClassId){
    try {
        String[] data = new String[2];
        data[0] = String.valueOf(eventId);
        data[1] = String.valueOf(eventClassId);

        String message = new Communication(this.currentUser.getUsername(), "CP", data).getMessage();
        System.out.println(message);

        this.SendToServer(message);
        this.ReceivedFromServer();

        Communication received = new Communication(this.messageReceived);
        String[] dataReply = received.getData();

        return Double.parseDouble(dataReply[0]);
    } catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
        return -1;
    }
}
   
   public void SelectTicket(String username) {
    ArrayList<String> res = new ArrayList<>();
    try {
        String[] data = new String[1];
        data[0] = username;

        String message = new Communication(this.currentUser.getUsername(), "ST", data).getMessage();
        System.out.println(message);
        this.SendToServer(message);
        this.ReceivedFromServer();
        
        Communication received = new Communication(this.messageReceived);
        String[] dataReply = received.getData(); 

        if (dataReply != null) {
                System.out.println("ticketInfoPrice: " +  Arrays.toString(dataReply));
                for(int i = 0; i < dataReply.length; i += 6){
                    int ticketId = Integer.parseInt(dataReply[0+i]);
                    Event event = new Event();
                    int eventId =Integer.parseInt(dataReply[5+i]);
                    String eventName = dataReply[1+i];
                    event.setName(eventName);
                    event.setId(eventId);
                    int eventClassId = Integer.parseInt(dataReply[2+i]);
                    LocalDate paidDate = LocalDate.parse(dataReply[3+i]);
                    double price = Double.parseDouble(dataReply[4+i]);
                    
                    Ticket ticket = new Ticket();
                    ticket.setId(String.valueOf(ticketId));
                    ticket.setEventClassId(eventClassId);
                    ticket.setPaidDate(paidDate);
                    ticket.setPrice(price);
                    ticket.setBuyerUsername(username);
                    ticket.setEvent(event);

                    System.out.println("Push ke repo OK");

                    service.repo.ListTicket.add(ticket);
                }
               
                System.out.println("Push ke repo DONE");
        }else{
            System.out.println("dataReply selectTicket is null");
        }
        

    } catch (Exception ex) {
        System.out.println(ex);
    }
//    return res;
}

   
    public boolean BuyTicket(String buyerUsername, int eventId, int eventClassId) //Select event and EVent class by event ID
    {
        boolean res=false;
        try {
            String[] data = new String[6];
            data[0] = String.valueOf(buyerUsername);
            data[1] = String.valueOf(eventId);
            data[2] = String.valueOf(eventClassId);
            data[3] = "OVO";

            String message = new Communication(this.currentUser.getUsername(), "BT",data ).getMessage();
            System.out.println(message);
            this.SendToServer(message);
            this.ReceivedFromServer();
            Communication received = new Communication(this.messageReceived);
            
            if (received.getCommand().equals("SUCCESS")) {
                res = true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return res;
    }
}
