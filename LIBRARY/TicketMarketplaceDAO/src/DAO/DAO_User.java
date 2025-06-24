package DAO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import DAO.Connection.DatabaseConnection;
import Entities.Account.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    public static User Select_User(String username) throws Exception {
        User selectedUser = new User();

        String SQLQuery = "SELECT * FROM users WHERE username=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, username);

        ResultSet rslt = prst.executeQuery();

        if (rslt.next()) {
            selectedUser = new User(
                    rslt.getString("username"),
                    rslt.getString("password"),
                    rslt.getString("fullname"),
                    rslt.getString("email"),
                    rslt.getString("phoneNumber"),
                    rslt.getTimestamp("birthdate").toLocalDateTime().toLocalDate()
            );
        }

        prst.close();

        return selectedUser;

    }

    public static int Insert_User(User _user) throws Exception {
        String SQLQuery = "INSERT INTO users ('username','password','fullname','phoneNumber',email','birthdate') VALUES(?,?,?,?,?)";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, _user.getUsername());
        prst.setString(2, _user.getPassword());
        prst.setString(3, _user.getFullName());
        prst.setString(4, _user.getEmail());
        prst.setString(5, _user.getPhoneNumber());
        prst.setString(6, _user.getBirthdate().toString());

        int num = prst.executeUpdate();

        prst.clearBatch();
        prst.close();

        return num;
    }

    public static int Update_User(User _user) throws Exception {
        String SQLQuery = "UPDATE FROM users SET password=?,fullname=?,email=?,,phoneNumber=?,birthdate=? WHERE username=?";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(6, _user.getUsername());
        prst.setString(1, _user.getPassword());
        prst.setString(2, _user.getFullName());
        prst.setString(3, _user.getEmail());
        prst.setString(4, _user.getPhoneNumber());
        prst.setString(5, _user.getBirthdate().toString());

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
    }

    public static int Delete_User(String username, String password) throws Exception {
        String SQLQuery = "DELETE FROM users WHERE username=? AND password=?";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, username);
        prst.setString(2, password);

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
    }

}
