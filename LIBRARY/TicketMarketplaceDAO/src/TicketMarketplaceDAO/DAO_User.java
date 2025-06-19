package TicketMarketplaceDAO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Entities.Account.User;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

/**
 *
 * @author joshu
 */
public class DAO_User extends DatabaseConnection {

    //CONSTRUCTOR 
    public DAO_User() throws Exception {
        super();
        System.out.println("DAO_USER IS CONNECTED");
    }

    //FUNCTION
    public User Select_User(String username) throws Exception {
        User selectedUser = new User();

        String SQLQuery = "SELECT * FROM user WHERE username=?";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, username);
        
        this.Read();
        
        if (this.getResult().next()) {
            selectedUser = new User(
                    this.getResult().getString("username"),
                    this.getResult().getString("password"),
                    this.getResult().getString("fullname"),
                    this.getResult().getString("email"),
                    this.getResult().getString("phoneNumber"),
                    this.getResult().getTimestamp("birthdate").toLocalDateTime().toLocalDate()
            );
        }

        return selectedUser;

    }

    public void Insert_User(User _user) throws Exception {
        String SQLQuery = "INSERT INTO user ('username','password','fullname','phoneNumber',email','birthdate') VALUES(?,?,?,?,?)";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, _user.getUsername());
        this.getPreparedStatement().setString(2, _user.getPassword());
        this.getPreparedStatement().setString(3, _user.getFullName());
        this.getPreparedStatement().setString(4, _user.getEmail());
        this.getPreparedStatement().setString(5, _user.getPhoneNumber());
        this.getPreparedStatement().setString(6, _user.getBirthdate().toString());

        this.Create();
    }

    public void Update_User(User _user) throws Exception {
        String SQLQuery = "UPDATE FROM user SET password=?,fullname=?,email=?,,phoneNumber=?,birthdate=? WHERE username=?";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, _user.getPassword());
        this.getPreparedStatement().setString(2, _user.getFullName());
        this.getPreparedStatement().setString(3, _user.getEmail());
        this.getPreparedStatement().setString(4, _user.getPhoneNumber());
        this.getPreparedStatement().setString(5, _user.getBirthdate().toString());
        this.getPreparedStatement().setString(6, _user.getUsername());

        this.Update();
    }

    public void Delete_User(String username, String password) throws Exception {
        String SQLQuery = "DELETE FROM user WHERE username=? AND password=?";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, username);
        this.getPreparedStatement().setString(2, password);

        this.Delete();
    }

}
