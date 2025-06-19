/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplaceserver;

import Communication.Communication;
import Communication.InteractiveIO;
import TicketMarketplaceEntities.*;
import java.time.LocalDate;

/**
 *
 * @author joshu
 */
public class UserLogic {
    
    private RepositoryTemp repo;
    
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
}
