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
    public User currentUser; //For Temporary

    public ServerService() {
        repo = new RepositoryTemp(); //For Temporary Database
        currentUser = new User();
    }

    public void UserSignUp(String username, String password, String fullname, String email, LocalDate birthdate) {
        // Add to temporary database
        repo.ListUser.add(new User(username, password, fullname, email, birthdate));
        // DEBUG
        System.out.println(repo.ListUser.size());
        for (User u : repo.ListUser) {
            u.DebugUserData();
        }
        // Add to SQL database
        //DAO_USer.XXXXXXXXXXXX
    }

    public void UserLogIn(String username, String password) {
        for (User u : repo.ListUser) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                this.currentUser = u;
                break;
            }
        }
    }
}
