package TicketMarketplaceDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Franly
 */
public class DatabaseConnection {

    private final String URL = "jdbc:mysql://localhost:3306/TicketMarketplace";
    private final String USER = "root";
    private final String PASSWORD = "";


    private static Connection conn;

    public DatabaseConnection() throws Exception {
        this.conn = this.ConnectToDatabase();
    }

    //GETTER AND SETTER

    public static Connection getConnection() {
        return DatabaseConnection.conn;
    }

    // Function
    private Connection ConnectToDatabase() throws Exception {
        if (DatabaseConnection.conn == null) {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("SUCCESFULLY CONNECTED TO DATABASE");
            return DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
        } else {
            return this.conn;
        }
    }

    //Main Function
    public static void Create(String sqlQuery) throws Exception {
        PreparedStatement sql = (PreparedStatement) DatabaseConnection.getConnection().prepareStatement(sqlQuery);
        sql.executeUpdate();
        System.out.println("SUCCESFUL TO CREATE DATA IN DATABASE:\n" + sqlQuery);
        sql.close();
    }

    public static ResultSet Read(String sqlQuery) throws Exception {
        Statement stmt = DatabaseConnection.getConnection().createStatement();
        ResultSet rslt = stmt.executeQuery(sqlQuery);
        System.out.println("SUCCESFUL TO RETRIEVE DATA FROM DATABASE:\n" + sqlQuery);
        return rslt;
    }

    public static void Update(String sqlQuery) throws Exception {
        PreparedStatement sql = (PreparedStatement) DatabaseConnection.getConnection().prepareStatement(sqlQuery);
        sql.executeUpdate();
        System.out.println("SUCCESFUL TO UPDATE DATA IN DATABASE:\n"+ sqlQuery);
        sql.close();
    }

    public static void Delete(String sqlQuery) throws Exception {
        PreparedStatement sql = (PreparedStatement) DatabaseConnection.getConnection().prepareStatement(sqlQuery);
        sql.executeUpdate();
        System.out.println("SUCCESFUL TO DELETE DATA IN DATABASE:\n"+ sqlQuery);
        sql.close();
    }
}
