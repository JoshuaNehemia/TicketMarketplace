package TicketMarketplaceDAO;

import Entities.Account.Seller;
import java.sql.PreparedStatement;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joshu
 */
public class DAO_Seller extends DatabaseConnection{
   
    //Database
    public DAO_Seller() throws Exception
    {
        super();
        System.out.println("DAO_SELLER IS CONNECTED");
    }
    
    public Seller Select_Seller(String username) throws Exception {
        Seller selectedSeller = new Seller();

        String SQLQuery = "SELECT * FROM seller WHERE username=?";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, username);
        
        this.Read();
        
        if (this.getResult().next()) {
            selectedSeller = new Seller(
                    this.getResult().getString("username"),
                    this.getResult().getString("password"),
                    this.getResult().getString("companyName"),
                    this.getResult().getString("email"),
                    this.getResult().getString("phoneNumber"),
                    this.getResult().getString("companyAddress")
            );
            return selectedSeller;
        }
        else
        {
            return selectedSeller;
        }
        
    }
    
    public void Insert_Seller(Seller _user) throws Exception
    {
        String SQLQuery = "INSERT INTO seller ('username','password','email','companyName','companyAddress','phoneNumber') VALUES(?,?,?,?,?)";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, _user.getUsername());
        this.getPreparedStatement().setString(2, _user.getPassword());
        this.getPreparedStatement().setString(3, _user.getEmail());
        this.getPreparedStatement().setString(4, _user.getCompanyName());
        this.getPreparedStatement().setString(5, _user.getCompanyAddress());
        this.getPreparedStatement().setString(6, _user.getPhoneNumber());
        
        this.Create();
    }
    
    public void Update_Seller(Seller _user) throws Exception
    {
        String SQLQuery = "UPDATE FROM seller SET password=?,email=?,companyName=?,companyAddress=?,phoneNumber=? WHERE username=? ";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, _user.getPassword());
        this.getPreparedStatement().setString(2, _user.getEmail());
        this.getPreparedStatement().setString(3, _user.getCompanyName());
        this.getPreparedStatement().setString(4, _user.getCompanyAddress());
        this.getPreparedStatement().setString(5, _user.getPhoneNumber());
        this.getPreparedStatement().setString(6, _user.getUsername());
        
        this.Update();
    }    
    
    public void Delete_Seller(String username, String password) throws Exception
    {
        String SQLQuery = "DELETE FROM seller WHERE username=? and password=?";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, username);
        this.getPreparedStatement().setString(2, password);
        
        this.Delete();
    }

}
