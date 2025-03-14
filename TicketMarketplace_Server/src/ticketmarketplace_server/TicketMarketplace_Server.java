/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ticketmarketplace_server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ticketmarketplace_server.Database.Database_Connection;

/**
 *
 * @author joshu
 */
public class TicketMarketplace_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = Database_Connection.getConnection();
        if (conn != null) {
            System.out.println("Koneksi berhasil!");
            try {
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
    }
    
}
