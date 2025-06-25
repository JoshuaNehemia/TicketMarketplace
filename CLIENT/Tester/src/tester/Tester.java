/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tester;

import DAO.Connection.DatabaseConnection;
import DAO.DAO_User;
import webservice.Account;
import java.time.LocalDate;
import webservice.User;

/**
 *
 * @author joshu
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    DatabaseConnection conn;

    public static void main(String[] args) {
        // TODO code application logic here
        User us = new User();
        us.setUsername("joshua123");
        us.setPassword("123456");
        us.setFullName("joshuanehemia");
        us.setPhoneNumber("08123456789");
        us.setEmail("joshua@email.com");
        us.setBirthdate(java.time.LocalDate.now());
        System.out.println(String.valueOf(userSignUp(us)));
        
        
    }

    private static int userSignUp(webservice.User user) {
        webservice.TMWebService_Service service = new webservice.TMWebService_Service();
        webservice.TMWebService port = service.getTMWebServicePort();
        return port.userSignUp(user);
    }


}
