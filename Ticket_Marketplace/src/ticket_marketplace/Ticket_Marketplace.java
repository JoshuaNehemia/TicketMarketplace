/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ticket_marketplace;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;  // Tambahkan ini
import java.sql.ResultSet;  // Tambahkan ini




/**
 *
 * @author joshu
 */
public class Ticket_Marketplace {
    public static void main(String[] args) {
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
