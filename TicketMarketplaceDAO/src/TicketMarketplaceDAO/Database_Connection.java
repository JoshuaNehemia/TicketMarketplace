package TicketMarketplaceDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Franly
 */
public class Database_Connection {
    private static final String URL = "jdbc:mysql://localhost:3306/projectjava1";
    private static final String USER = "root"; 
    private static final String PASSWORD = "";
    
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public final static void ConnectToDatabase()
    {
        /*
        Connection conn = Database_Connection.getConnection();
        if (conn != null) {
            System.out.println("Koneksi berhasil!");
            try {
                // Query Testing
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM users"); 

                while (rs.next()) {
                    System.out.println("User: " + rs.getString("usernames"));
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Koneksi gagal!");
        }
        */
    }
}
