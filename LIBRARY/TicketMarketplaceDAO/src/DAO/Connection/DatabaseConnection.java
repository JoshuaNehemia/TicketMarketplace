package DAO.Connection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Joshu
 */
public class DatabaseConnection {

    //FIELD
    //// DATABASE DATA
    private final String URL = "jdbc:mysql://localhost:3306/TicketMarketplace?useSSL=false&allowPublicKeyRetrieval=true";
    private final String USER = "root";
    private final String PASSWORD = "ganairsm";
    //// DATABASE CONNECTION
    private static Connection conn;

    //CONSTRUCTOR
    public DatabaseConnection() throws Exception {
        DatabaseConnection.conn = this.ConnectToDatabase();
    }

    //GETTER AND SETTER
    public static Connection getConnection() {
        return DatabaseConnection.conn;
    }

    //FUNCTION
    private Connection ConnectToDatabase() throws Exception {
        if (DatabaseConnection.conn == null) {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("SUCCESFULLY CONNECTED TO DATABASE: " + this.URL);
            return DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
        } else {
            return DatabaseConnection.conn;
        }
    }
}
