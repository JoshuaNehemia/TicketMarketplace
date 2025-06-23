/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebService;

import DAO.Connection.DatabaseConnection;
import DAO.*;
import DAO.Values.*;
import Entities.Account.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author joshu
 */
@WebService(serviceName = "TicketMarketplaceWebService")
public class TicketMarketplaceWebService {

    //FIELDS
    ////DATABASE CONNECTION
    DatabaseConnection conn;


    @WebMethod(operationName = "ConnectionTest")
    public String ConnectionTest() throws Exception{
        return "CONNECTION SUCCESFULL!";
    }

    //USER (BUYER)
    @WebMethod(operationName = "UserLogIn")
    public User UserLogIn(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        User buff = new User();
        try {
            this.ConnectToDatabase();
            buff = dbuser.Select_User(username);
            if (buff.getPassword().equals(password)) {
                return buff;
            } else {
                return new User();
            }
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
            return buff;
        }
    }

    @WebMethod(operationName = "UserSignUp")
    public int UserSignUp(@WebParam(name = "user") User user) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = dbuser.Insert_User(user);
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
            num = dbuser.Update_User(user);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }
    
    //SELLER
    @WebMethod(operationName = "UserLogIn")
    public Seller SellerLogIn(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        Seller buff = new Seller();
        try {
            this.ConnectToDatabase();
            buff = dbseller.Select_Seller(username);
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

    @WebMethod(operationName = "UserSignUp")
    public int SellerSignUp(@WebParam(name = "user") Seller user) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            num = dbseller.Insert_Seller(user);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }
    
    @WebMethod(operationName = "UserUpdateData")
    public int SellerUpdateData(@WebParam(name = "user") Seller user) {
        int num = 0;
        try {
            this.ConnectToDatabase();
            DAO_Seller dbseller = (DAO_Seller)conn;
            num = dbseller.Update_Seller(user);
        } catch (Exception ex) {
            System.out.println("ERROR IN WEBSERVICE: " + ex);
        }
        return num;
    }
    

    //FUNCTION
    ////DATABSE CONNECTION
    private void ConnectToDatabase() throws Exception {
        conn = new DatabaseConnection();
    }
}
