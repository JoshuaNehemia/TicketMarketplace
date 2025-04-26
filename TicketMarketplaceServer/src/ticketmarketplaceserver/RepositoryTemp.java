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
    public ArrayList<City> ListCity;
    public ArrayList<Province> ListProvince;
    public ArrayList<Event_category> ListEventCategory;
    public ArrayList<Ticket> ListTicket;
    public ArrayList<Seat> listSeat;
    public ArrayList<Payment_method> listPaymentMethod;
    public RepositoryTemp() {
        ListProvince = new ArrayList<Province>();
        ListCity = new ArrayList<City>();
        ListUser = new ArrayList<User>();
        ListSeller = new ArrayList<Seller>();
        ListVenue = new ArrayList<Venue>();
        
        this.CreateProvince();
        this.CreateCity();
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
    
    private void CreateEventCategory()
    {
        this.ListEventCategory.add(new Event_category(1,"CONCERT"));
        this.ListEventCategory.add(new Event_category(2,"RELIGIOUS EVENT"));
        this.ListEventCategory.add(new Event_category(3,"ORCHESTRA"));
        this.ListEventCategory.add(new Event_category(4,"CLASS"));
    }
    
    private void CreateCity()
    {
        ListCity.add(new City(1,"Jakarta Pusat",ListProvince.get(10)));
        ListCity.add(new City(2,"Jakarta Selatan",ListProvince.get(10)));
        ListCity.add(new City(3,"Jakarta Utara",ListProvince.get(10)));
        ListCity.add(new City(4,"Jakarta Timur",ListProvince.get(10)));
        ListCity.add(new City(5,"Jakarta Barat",ListProvince.get(10)));
        ListCity.add(new City(6,"Surabaya",ListProvince.get(15)));
    }
    
    private void CreateProvince()
    {
        ListProvince.add(new Province(1, "Aceh"));
        ListProvince.add(new Province(2, "Sumatera Utara"));
        ListProvince.add(new Province(3, "Sumatera Barat"));
        ListProvince.add(new Province(4, "Riau"));
        ListProvince.add(new Province(5, "Jambi"));
        ListProvince.add(new Province(6, "Sumatera Selatan"));
        ListProvince.add(new Province(7, "Bengkulu"));
        ListProvince.add(new Province(8, "Lampung"));
        ListProvince.add(new Province(9, "Kepulauan Bangka Belitung"));
        ListProvince.add(new Province(10, "Kepulauan Riau"));
        ListProvince.add(new Province(11, "DI Jakarta"));
        ListProvince.add(new Province(12, "Jawa Barat"));
        ListProvince.add(new Province(13, "Banten"));
        ListProvince.add(new Province(14, "Jawa Tengah"));
        ListProvince.add(new Province(15, "DI Yogyakarta"));
        ListProvince.add(new Province(16, "Jawa Timur"));
        ListProvince.add(new Province(17, "Bali"));
        ListProvince.add(new Province(18, "Nusa Tenggara Barat"));
        ListProvince.add(new Province(19, "Nusa Tenggara Timur"));
        ListProvince.add(new Province(20, "Kalimantan Barat"));
        ListProvince.add(new Province(21, "Kalimantan Tengah"));
        ListProvince.add(new Province(22, "Kalimantan Selatan"));
        ListProvince.add(new Province(23, "Kalimantan Timur"));
        ListProvince.add(new Province(24, "Kalimantan Utara"));
        ListProvince.add(new Province(25, "Sulawesi Utara"));
        ListProvince.add(new Province(26, "Gorontalo"));
        ListProvince.add(new Province(27, "Sulawesi Tengah"));
        ListProvince.add(new Province(28, "Sulawesi Barat"));
        ListProvince.add(new Province(29, "Sulawesi Selatan"));
        ListProvince.add(new Province(30, "Sulawesi Tenggara"));
        ListProvince.add(new Province(31, "Maluku"));
        ListProvince.add(new Province(32, "Maluku Utara"));
        ListProvince.add(new Province(33, "Papua"));
        ListProvince.add(new Province(34, "Papua Barat"));
        ListProvince.add(new Province(35, "Papua Selatan"));
        ListProvince.add(new Province(36, "Papua Tengah"));
        ListProvince.add(new Province(37, "Papua Pegunungan"));
        ListProvince.add(new Province(38, "Papua Barat Daya"));
    }
    
    
}
