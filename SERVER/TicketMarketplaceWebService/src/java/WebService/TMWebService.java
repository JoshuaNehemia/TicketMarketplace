/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebService;

import DAO.Connection.DatabaseConnection;
import DAO.*;
import DAO.Values.*;
import Entities.Account.*;
import Entities.Event;
import Entities.EventClass;
import Entities.Ticket;
import Entities.Values.City;
import Entities.Values.PaymentMethod;
import Entities.Values.Province;
import Entities.Venue;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author joshu
 */
@WebService(serviceName = "TMWebService")
public class TMWebService {

    //FIELDS
    ////DATABASE CONNECTION
    DatabaseConnection conn;

    @WebMethod(operationName = "ConnectionTest")
    public String ConnectionTest() {
        return "CONNECTION SUCCESFULL!";
    }

    //VALUES
    ////CITY
    @WebMethod(operationName = "GetCities")
    public ArrayList<City> GetCities() {
        ArrayList<City> cities = new ArrayList<>();
        try {
            this.ConnectToDatabase();
            cities = DAO_City.Select_City();
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return cities;
    }

    ////PAYMENT METHOD
    @WebMethod(operationName = "GetPaymentMethods")
    public ArrayList<PaymentMethod> GetPaymentMethods() {
        ArrayList<PaymentMethod> pms = new ArrayList<>();
        try {
            this.ConnectToDatabase();
            pms = DAO_PaymentMethod.Select_PaymentMethods();
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return pms;
    }

    //USER (BUYER)
    @WebMethod(operationName = "UserLogIn")
    public User UserLogIn(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
     
        try {
            this.ConnectToDatabase();
            User buff = DAO_User.Select_User(username);
            if (buff.getPassword().equals(password)) {
                return buff;
            } else {
                return new User();
            }
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
            return new User();
        }
    }

    @WebMethod(operationName = "UserSignUp")
    public int UserSignUp(@WebParam(name = "user") User user) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = DAO_User.Insert_User(user);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }

    @WebMethod(operationName = "UserUpdateData")
    public int UserUpdateData(@WebParam(name = "user") User user) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = DAO_User.Update_User(user);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }

    //SELLER
    @WebMethod(operationName = "SellerLogIn")
    public Seller SellerLogIn(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        Seller buff = new Seller();
        try {
            this.ConnectToDatabase();
            buff = DAO_Seller.Select_Seller(username);
            if (buff.getPassword().equals(password)) {
                return buff;
            } else {
                return new Seller();
            }
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
            return buff;
        }
    }

    @WebMethod(operationName = "SellerSignUp")
    public int SellerSignUp(@WebParam(name = "user") Seller user) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = DAO_Seller.Insert_Seller(user);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }

    @WebMethod(operationName = "SellerUpdateData")
    public int SellerUpdateData(@WebParam(name = "user") Seller user) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = DAO_Seller.Update_Seller(user);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }

    //VENUE
    @WebMethod(operationName = "GetVenueWithCity")
    public ArrayList<Venue> GetVenueWithCity(@WebParam(name = "city") City city) {
        ArrayList<Venue> venues = new ArrayList<>();
        try {
            this.ConnectToDatabase();
            venues = DAO_Venue.Select_Venue_By_City(city);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return venues;
    }

    @WebMethod(operationName = "GetVenueWithProvince")
    public ArrayList<Venue> GetVenueWithProvince(@WebParam(name = "province") Province province) {
        ArrayList<Venue> venues = new ArrayList<>();
        try {
            this.ConnectToDatabase();
            venues = DAO_Venue.Select_Venue_By_Province(province);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return venues;
    }

    @WebMethod(operationName = "GetVenueWithName")
    public ArrayList<Venue> GetVenueWithName(@WebParam(name = "name") String name) {
        ArrayList<Venue> venues = new ArrayList<>();
        try {
            this.ConnectToDatabase();
            venues = DAO_Venue.Select_Venue_By_Name(name);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return venues;
    }

    @WebMethod(operationName = "CreateNewVenue")
    public int CreateNewVenue(@WebParam(name = "venue") Venue venue) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = DAO_Venue.Insert_Venue(venue);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }

    @WebMethod(operationName = "UpdateVenueData")
    public int UpdateVenueData(@WebParam(name = "name") Venue venue) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = DAO_Venue.Update_Venue(venue);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }

    //EVENT
    @WebMethod(operationName = "GetCompanyEvent")
    public ArrayList<Event> GetCompanyEvent(@WebParam(name = "companyName") String companyName) {
        ArrayList<Event> events = new ArrayList<>();
        try {
            this.ConnectToDatabase();
            events = DAO_Event.Select_Event_By_CompanyName(companyName);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return events;
    }

    @WebMethod(operationName = "GetSellerEvent")
    public ArrayList<Event> GetSellerEvent(@WebParam(name = "seller") String seller) {
        ArrayList<Event> events = new ArrayList<>();
        try {
            this.ConnectToDatabase();
            events = DAO_Event.Select_Event_By_Seller(seller);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return events;
    }

    @WebMethod(operationName = "GetEvent")
    public ArrayList<Event> GetEvent(@WebParam(name = "eventName") String eventName) {
        ArrayList<Event> events = new ArrayList<>();
        try {
            this.ConnectToDatabase();
            events = DAO_Event.Select_Event_By_Name(eventName);
            for (Event ev : events) {
                ev.setEventClasses(DAO_EventClass.Select_EventClass_By_Event_Id(ev.getId()));
            }
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return events;
    }

    @WebMethod(operationName = "CreateNewEvent")
    public Event CreateNewEvent(@WebParam(name = "event") Event event, @WebParam(name = "seller_username") String seller_username) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = DAO_Event.Insert_Event(event);
            for (EventClass ec : event.getEventClasses()) {
                num += DAO_EventClass.Insert_EventClass(event.getId(), ec);
            }
            return DAO_Event.Select_SingleEvent_By_Name(event.getName(), seller_username);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
            return new Event();
        }
    }

    @WebMethod(operationName = "UpdateEventData")
    public int UpdateEventData(@WebParam(name = "event") Event event, @WebParam(name = "seller_username") String seller_username) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = DAO_Event.Update_Event(event);
            for (EventClass ec : event.getEventClasses()) {
                num += DAO_EventClass.Update_EventClass(ec);
            }
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }

    //TICKET
    @WebMethod(operationName = "BuyTicket")
    public Ticket BuyTicket(@WebParam(name = "ticket") Ticket ticket, String user) {
        Ticket result = new Ticket();
        try {
            this.ConnectToDatabase();
            int stock = 0;
            int eventClassId = 0;
            for (EventClass ec : ticket.getEvent().getEventClasses()) {
                if (ec.getName().equals(ticket.getEventClass())) {
                    eventClassId = ec.getId();
                }
            }
            if (eventClassId == 0) {
                return new Ticket();
            }
            stock = DAO_EventClass.Select_EventClass_Stock(eventClassId);
            if (stock == 0) {
                return new Ticket();
            } else {
                int num = DAO_EventClass.Update_EventClass_Stock(eventClassId);
                if (num == 0) {
                    return new Ticket();
                }
                ticket.setId(ticket.CreateID(user));
                num = DAO_Ticket.Insert_Ticket(ticket, user, eventClassId);

                if (num == 0) {
                    num = DAO_EventClass.Update_EventClass_Stock_Add(eventClassId, 1);
                    return new Ticket();
                }
                result = DAO_Ticket.Select_Ticket_By_Id(ticket.getId());
            }
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return result;
    }

    @WebMethod(operationName = "PayTicket")
    public int PayTicket(@WebParam(name = "ticket_id") String ticket_id) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = DAO_Ticket.Update_Ticket_Paid(ticket_id, LocalDateTime.now());
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }

    @WebMethod(operationName = "RefundTicket")
    public int RefundTicket(@WebParam(name = "ticket_id") String ticket_id) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = DAO_Ticket.Update_Ticket_Status(ticket_id, "REFUND");
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }

    @WebMethod(operationName = "CancelTicketOrder")
    public int CancelTicketOrder(@WebParam(name = "ticket") Ticket ticket) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = DAO_Ticket.Update_Ticket_Status(ticket.getId(), "CANCELLED");
            num += DAO_EventClass.Update_EventClass_Stock_Add(this.GetEventClassId(ticket), 1);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }

    @WebMethod(operationName = "ApproveRefundTicket")
    public int ApproveRefundTicket(@WebParam(name = "ticket") Ticket ticket) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = DAO_Ticket.Update_Ticket_Status(ticket.getId(), "REFUND");
            num += DAO_EventClass.Update_EventClass_Stock_Add(this.GetEventClassId(ticket), 1);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }

    @WebMethod(operationName = "ClaimTicket")
    public int ClaimTicket(@WebParam(name = "ticket") Ticket ticket) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            if (!ticket.getStatus().equals("PAID")) {
                return -1;
            }
            if (ticket.isIsClaimed()) {
                return -2;
            }
            num = DAO_Ticket.Update_Ticket_isClaimed(ticket.getId());
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }

    //FUNCTION
    ////DATABASE CONNECTION
    private void ConnectToDatabase() throws Exception {
        conn = new DatabaseConnection();
    }

    ////TICKET
    //GET EVENT CLASS ID
    private int GetEventClassId(Ticket ticket) {
        int eventClassId = 0;
        for (EventClass ec : ticket.getEvent().getEventClasses()) {
            if (ec.getName().equals(ticket.getEventClass())) {
                eventClassId = ec.getId();
            }
        }
        return eventClassId;
    }
}
