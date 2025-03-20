/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmarketplaceserver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author joshu
 */
public class MenuCLI {

    // COMMAND LINE INTERFACE (FOR DEBUGGING) ----------------------------------
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static Scanner myScan = new Scanner(System.in);
    
    public static void StartUpMenuCLI() {
        String content
                = "Welcome to Ticket MarketPlace"
                + "\n1. Log In  \t (CC : LI)"
                + "\n2. Sign Up \t (CC : SU)"
                + "\nPlease select your option (1/2) ?";
        System.out.println(content);

        String input = myScan.nextLine();

        if (input.equals("1") || input.equals("LI")) {
            UserLogInMenuCLI();
        } else {
            UserSignUpMenuCLI();
        }

    }

    public static void UserLogInMenuCLI() {
        
        String content = "Please Log In! \nPlease fill your username and password! \nUsername : ";
        System.out.println(content);
        String username = myScan.nextLine();

        content = "Password :";
        System.out.println(content);
        String password = myScan.nextLine();

        ServerApp.service.UserLogIn(username, password);
        if ("".equals(ServerApp.service.currentUser.getUsername())) {
            System.out.println("Wrong Username or Password");
        } else {
            MainMenuCLI();
        }
    }

    public static void UserSignUpMenuCLI() {
        String content = "Please Sign Up and fIll the required data\nUsername :";
        System.out.println(content);
        
        String username = myScan.nextLine();
        
        content = "Password :";
        System.out.println(content);
        String password = myScan.nextLine();

        content = "Full Name :";
        System.out.println(content);
        String fullName = myScan.nextLine();

        content = "Email :";
        System.out.println(content);
        String email = myScan.nextLine();

        content = "Birthdate (yyyy-MM-dd) :";
        System.out.println(content);
        String birthdate = myScan.nextLine();

        LocalDate birthDate = LocalDate.parse(birthdate);

        ServerApp.service.UserSignUp(username, password, fullName, email, birthDate);
    }

    public static void MainMenuCLI() {
        String content = "Hello user!\nUsername : "
                + ServerApp.service.currentUser.getUsername()
                + "\nFull Name : "
                + ServerApp.service.currentUser.getFullname()
                + "\nEmail : "
                + ServerApp.service.currentUser.getEmail()
                + "\nBirthdate : "
                + ServerApp.service.currentUser.getBirthdate();
        System.out.println(content);
    }
}
