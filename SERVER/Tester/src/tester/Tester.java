/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tester;

import DAO.Connection.DatabaseConnection;
import DAO.*;
import Entities.Account.Seller;
import Entities.Account.User;

/**
 *
 * @author joshu
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Tester.testuserdb();
        }
        catch(Exception ex){
            System.out.println("ERROR: "+ex);
        }
        
    }
    
    
        public static void testuserdb()throws Exception{
            
            DatabaseConnection db = new DatabaseConnection();
            User us = DAO_User.Select_User("user123");
            System.out.println("Username: " + us.getUsername());
        }

}
