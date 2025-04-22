/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplaceserver;



import java.util.ArrayList;

// Importing Our Own Library
import TicketMarketplaceDAO.*;
import TicketMarketplaceEntities.*;
import java.time.LocalDate;

/**
 *
 * @author joshu
 */
public class RepositoryTemp {
    
    // Buat sementara, biar bisa kumpul tugas
    public ArrayList<User> ListUser;
    public ArrayList<Seller> ListSeller;
    public ArrayList<Venue> ListVenue;
    public ArrayList<Event> ListEvent;
    
    public RepositoryTemp() {
        ListUser = new ArrayList<User>();
        ListSeller = new ArrayList<Seller>();
        ListVenue = new ArrayList<Venue>();
        
        this.CreateUser();
    }
    
    private void CreateUser()
    {
        ListUser.add(new User("username","password","Full Name","email@email.com",LocalDate.parse("2001-01-01")));
        ListUser.add(new User("joshua123","12345678","Joshua Nehemia S","joshua@email.com",LocalDate.parse("2001-01-01")));
        ListUser.add(new User("franly123","12345678","Franly Budi Pramana","franly@email.com",LocalDate.parse("2001-01-01")));
        ListUser.add(new User("gabriel123","12345678","Gabriel","gabriel@email.com",LocalDate.parse("2001-01-01")));
        ListUser.add(new User("evan123","12345678","Full Name","evan@email.com",LocalDate.parse("2001-01-01")));
        ListUser.add(new User("admin","admin1234","Admin","Admin@email.com",LocalDate.parse("1990-01-01")));
    }
    private void CreateSeller()
    {
        
    }
    private void CreateVenue()
    {
        
    }
    private void CreateEvent()
    {
        
    }
    
    
}
