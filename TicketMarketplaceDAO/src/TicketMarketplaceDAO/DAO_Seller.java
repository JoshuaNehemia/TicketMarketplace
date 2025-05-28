package TicketMarketplaceDAO;

import Communication.InteractiveIO;
import TicketMarketplaceEntities.Seller;
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
    /*
        public static Seller Select_Seller(String username, String password, ArrayList<Seller> list) {
        Seller selectedUser = new Seller();

        for (Seller us : list) {
            if (us.getUsername().equals(username) && us.getPassword().equals(password)) {
                selectedUser = us;
            }
        }

        return selectedUser;
    }

    public static ArrayList<Seller> Insert_Seller(Seller seller, ArrayList<Seller> list) {
        list.add(seller);
        
        return list;
    }

    public static ArrayList<Seller> Update_User(Seller seller, ArrayList<Seller> list) {
        Seller selectedUser = Select_Seller(seller.getUsername(), seller.getPassword(), list);
        
        list.remove(selectedUser);
        list.add(seller);
        
        return list;
    }

    public static ArrayList<Seller> Delete_User(String username, String password, ArrayList<Seller> list) {
        for (Seller us : list) {
            if (us.getUsername().equals(username) && us.getPassword().equals(password)) {
                list.remove(us);
            }
        }
        return list;
    }
     */
    
    //Database
    public DAO_Seller() throws Exception
    {
        super();
        System.out.println(InteractiveIO.GreenMessage("DAO_SELLER IS CONNECTED"));
    }
    
    public Seller Select_Seller(String username) throws Exception {
        Seller selectedSeller = new Seller();

        String SQLQuery = "SELECT * FROM seller WHERE username=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, username);
        
        System.out.println("SQL QUERY: \n" + prst);
        this.setResult(this.Read(String.valueOf(prst)));

        if (this.getResult().next()) {
            selectedSeller = new Seller(
                    this.getResult().getString("username"),
                    this.getResult().getString("password"),
                    this.getResult().getString("email"),
                    this.getResult().getString("companyName"),
                    this.getResult().getString("companyAddress"),
                    this.getResult().getString("phoneNumber")
            );
            return selectedSeller;
        }
        else
        {
            throw new Exception ("Failure in receiving seller data from database - no data matches the parameter");
        }
        
    }
    
    public void Insert_Seller(Seller _user) throws Exception
    {
        String SQLQuery = "INSERT INTO seller ('username','password','email','companyName','companyAddress','phoneNumber') VALUES(?,?,?,?,?)";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, _user.getUsername());
        prst.setString(2, _user.getPassword());
        prst.setString(3, _user.getEmail());
        prst.setString(4, _user.getCompanyName());
        prst.setString(5, _user.getCompanyAddress());
        prst.setString(6, _user.getPhoneNumber());
        
        this.Create(String.valueOf(prst));
    }
    
    public void Update_Seller(Seller _user) throws Exception
    {
        String SQLQuery = "UPDATE FROM seller SET password=?,email=?,companyName=?,companyAddress=?,phoneNumber=? WHERE username=? ";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, _user.getPassword());
        prst.setString(2, _user.getEmail());
        prst.setString(3, _user.getCompanyName());
        prst.setString(4, _user.getCompanyAddress());
        prst.setString(5, _user.getPhoneNumber());
        prst.setString(6, _user.getUsername());
        
        this.Update(String.valueOf(prst));
    }    
    
    public void Delete_Seller(String username, String password) throws Exception
    {
        String SQLQuery = "DELETE FROM seller WHERE username=? and password=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, username);
        prst.setString(2, password);
        
        this.Delete(String.valueOf(prst));
    }

}
