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



/**
 *
 * @author joshu
 */
public class ServerService {
    
    // Temporary database buat tugas progress project
    public RepositoryTemp repo;

    public ServerService() {
        repo = new RepositoryTemp();
    }
    
    public void AddUserToDatabase(String username,  String password, String fullname, String email, LocalDate birthdate)
    {
        // For temporary database
        RepositoryTemp repo = new RepositoryTemp();
        // Add to temporary database
        repo.ListUser.add(new User(username,password,fullname,email,birthdate));
        
        // Add to SQL database
    }
    
}
