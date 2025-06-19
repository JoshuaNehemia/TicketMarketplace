package TicketMarketplaceDAO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Communication.InteractiveIO;
import TicketMarketplaceEntities.User;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

/**
 *
 * @author joshu
 */
public class DAO_User extends DatabaseConnection {

    /*
    public static User Select_User(String username, ArrayList<User> list) {
        User selectedUser = new User();

        for (User us : list) {
            if (us.getUsername().equals(username)) {
                selectedUser = us;
            }
        }

        return selectedUser;
    }

    public static User Select_User_LogIn(String username, String password, ArrayList<User> list) {
        User selectedUser = new User();

        selectedUser = DAO_User.Select_User(username, list);
        if (selectedUser.getPassword().equals(password)) {
            return selectedUser;
        }
        else
        {
            return null;
        }
    }

    public static ArrayList<User> Insert_User(User user, ArrayList<User> list) {
        list.add(user);

        return list;
    }

    public static ArrayList<User> Update_User(User user, ArrayList<User> list) {
        User selectedUser = Select_User(user.getUsername(), list);

        list.remove(selectedUser);
        list.add(user);

        return list;
    }

    public static ArrayList<User> Delete_User(String username, String password, ArrayList<User> list) {
        for (User us : list) {
            if (us.getUsername().equals(username) && us.getPassword().equals(password)) {
                list.remove(us);
            }
        }
        return list;
    }
     */
    
    
    // Database 
    public DAO_User() throws Exception {
        super();
        System.out.println(InteractiveIO.GreenMessage("DAO_USER IS CONNECTED"));
    }

    public User Select_User(String username) throws Exception {
        User selectedUser = new User();

        String SQLQuery = "SELECT * FROM user WHERE username=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, username);
        
        System.out.println("SQL QUERY: \n" + prst);
        this.setResult(this.Read(String.valueOf(prst)));

        if (this.getResult().next()) {
            selectedUser = new User(
                    this.getResult().getString("username"),
                    this.getResult().getString("password"),
                    this.getResult().getString("fullname"),
                    this.getResult().getString("email"),
                    this.getResult().getTimestamp("birthdate").toLocalDateTime().toLocalDate()
            );
            return selectedUser;
        }
        else
        {
            throw new Exception ("Failure in receiving user data from database - no data matches the parameter");
        }
        
    }
    
    public void Insert_User(User _user) throws Exception
    {
        String SQLQuery = "INSERT INTO user ('username','password','fullname','email','birthdate') VALUES(?,?,?,?,?)";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, _user.getUsername());
        prst.setString(2, _user.getPassword());
        prst.setString(3, _user.getFullname());
        prst.setString(4, _user.getEmail());
        prst.setString(5, _user.getBirthdate().toString());
        
        this.Create(String.valueOf(prst));
    }
    
    public void Update_User(User _user) throws Exception
    {
        String SQLQuery = "UPDATE FROM user SET password=?,fullname=?,email=?,birthdate=? WHERE username=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, _user.getPassword());
        prst.setString(2, _user.getFullname());
        prst.setString(3, _user.getEmail());
        prst.setString(4, _user.getBirthdate().toString());
        prst.setString(5, _user.getUsername());
        
        this.Update(String.valueOf(prst));
    }    
    
    public void Delete_User(String username, String password) throws Exception
    {
        String SQLQuery = "DELETE FROM user WHERE username=? AND password=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, username);
        prst.setString(2, password);
        
        this.Delete(String.valueOf(prst));
    }

}
