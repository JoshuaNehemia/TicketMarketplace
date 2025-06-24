package DAO;

import DAO.Connection.DatabaseConnection;
import Entities.Venue;
import Entities.Values.City;
import Entities.Values.Province;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
 *
 * @author joshu
 */
public class DAO_Venue {

    public static ArrayList<Venue> Select_Venue_By_City(City _city) throws Exception {
        ArrayList<Venue> venues = new ArrayList<Venue>();
        Venue buffer;

        String SQLQuery = "SELECT * FROM `venues` WHERE city_id = (SELECT id FROM `cities` WHERE `name` =? LIMIT 1);";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, _city.getName());
        
        ResultSet rslt = prst.executeQuery();
        
        while (rslt.next()) {
            buffer = new Venue(
                    rslt.getInt("id"),
                    rslt.getString("name"),
                    _city,
                    rslt.getString("address"),
                    rslt.getInt("maxCapacity"),
                    rslt.getInt("area")
            );
            venues.add(buffer);
        }
        
        prst.close();
        
        return venues;
    }

    public static ArrayList<Venue> Select_Venue_By_Province(Province province) throws Exception {
        ArrayList<Venue> venues = new ArrayList<Venue>();
        Venue buffer;

        String SQLQuery = "SELECT v.*, c.id AS 'city_id', c.`name` AS 'city_name' FROM `venues` AS v JOIN `cities` AS c ON v.city_id = c.id JOIN `provinces` AS p ON c.province_id = p.id WHERE p.name LIKE ?;";

        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, province.getName());

        ResultSet rslt = prst.executeQuery();
        
        while (rslt.next()) {
            buffer = new Venue(
                    rslt.getInt("id"),
                    rslt.getString("name"),
                    new City(rslt.getInt("city_id"), rslt.getString("city_name")),
                    rslt.getString("address"),
                    rslt.getInt("maxCapacity"),
                    rslt.getInt("area")
            );
            venues.add(buffer);
        }
        
        prst.close();
        
        return venues;
    }

    public static ArrayList<Venue> Select_Venue_By_Name(String name) throws Exception {
        ArrayList<Venue> venues = new ArrayList<Venue>();
        Venue buffer;
        
        String SQLQuery = "SELECT v*,c.id as 'city_id',c.name as 'city_name' FROM `venues` v INNER JOIN `cities` c ON v.city_id = c.id WHERE v.`name` LIKE ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, "%" + name + "%");

        ResultSet rslt = prst.executeQuery();
        
        while (rslt.next()) {
            buffer = new Venue(
                    rslt.getInt("id"),
                    rslt.getString("name"),
                    new City(rslt.getInt("city_id"), rslt.getString("city_name")),
                    rslt.getString("address"),
                    rslt.getInt("maxCapacity"),
                    rslt.getInt("area")
            );
            venues.add(buffer);
        }
        
        prst.close();
        
        return venues;
    }

        public static Venue Select_Venue_By_Id(int id) throws Exception {
        Venue buffer = new Venue();

        String SQLQuery = "SELECT v*,c.id as 'city_id',c.name as 'city_name' FROM `venues` v INNER JOIN `cities` c ON v.city_id = c.id WHERE v.`id` = ? LIMIT 1;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setInt(1,id);

        ResultSet rslt = prst.executeQuery();
        
        if (rslt.next()) {
            buffer = new Venue(
                    rslt.getInt("id"),
                    rslt.getString("name"),
                    new City(rslt.getInt("city_id"), rslt.getString("city_name")),
                    rslt.getString("address"),
                    rslt.getInt("maxCapacity"),
                    rslt.getInt("area")
            );
        }
        
        prst.close();
        
        return buffer;
    }
    public static int Insert_Venue(Venue _venue) throws Exception {
        String SQLQuery = "INSERT INTO `venues` (`name`, `city_id`, `address`, `maxCapacity`, `area`) VALUES (?,?,?,?,?)";        
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, _venue.getName());
        prst.setString(2, _venue.getAddress());
        prst.setString(3, String.valueOf(_venue.getCity().getId()));
        prst.setString(4, String.valueOf(_venue.getMaxCapacity()));
        prst.setString(5, String.valueOf(_venue.getArea()));

        int num = prst.executeUpdate();
        prst.close();

        return num;
    }

    public static int Update_Venue(Venue _venue) throws Exception {
        String SQLQuery = "UPDATE venues SET name=?, address=?, maxCapacity=?, area=?, city_id=? WHERE id=?";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, _venue.getName());
        prst.setString(2, _venue.getAddress());
        prst.setString(3, String.valueOf(_venue.getCity().getId()));
        prst.setString(4, String.valueOf(_venue.getMaxCapacity()));
        prst.setString(5, String.valueOf(_venue.getArea()));
        prst.setString(6, String.valueOf(_venue.getId()));

        int num = prst.executeUpdate();
        prst.close();

        return num;
    }

    public static int Delete_Venue(int id) throws Exception {
        String SQLQuery = "DELETE FROM venues WHERE id=?";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, String.valueOf(id));

        int num = prst.executeUpdate();
        prst.close();

        return num;
    }

}
