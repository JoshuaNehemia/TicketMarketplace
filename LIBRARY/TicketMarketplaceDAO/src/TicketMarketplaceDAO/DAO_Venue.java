package TicketMarketplaceDAO;

import Entities.Venue;
import Entities.Values.City;
import java.sql.PreparedStatement;
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

    public ArrayList<Venue> Select_Venue(String seller_username) throws Exception {
        ArrayList<Venue> venues = new ArrayList<Venue>();

        String SQLQuery = "SELECT v.id AS id, v.name AS name, v.address AS address, v.maxCapacity AS maxCapacity, v.area AS area, c.id AS city_id, c.name as city_name FROM venue v INNER JOIN city c ON v.city_id = c.id;";
        this.setResult(this.Read(SQLQuery));

        Venue buffer;
        while (this.getResult().next()) {
            buffer = new Venue(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    this.getResult().getString("address"),
                    this.getResult().getInt("maxCapacity"),
                    this.getResult().getInt("area"),
                    new City(this.getResult().getInt("city_id"), this.getResult().getString("name"))
            );
            venues.add(buffer);
        }
        if (venues.size() > 0) {

            return venues;
        } else {
            throw new Exception("Failure in receiving venue data from database - no data matches the parameter");
        }

    }

    public ArrayList<Venue> Select_Venue_By_City(int city_id) throws Exception {
        ArrayList<Venue> venues = new ArrayList<Venue>();

        String SQLQuery = "SELECT v.id AS id, v.name AS name, v.address AS address, v.maxCapacity AS maxCapacity, v.area AS area, c.id AS city_id, c.name as city_name FROM venue v INNER JOIN city c ON v.city_id = c.id WHERE v.city_id=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, String.valueOf(city_id));

        System.out.println("SQL QUERY: \n" + prst);
        this.setResult(this.Read(String.valueOf(prst)));

        Venue buffer;
        while (this.getResult().next()) {
            buffer = new Venue(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    this.getResult().getString("address"),
                    this.getResult().getInt("maxCapacity"),
                    this.getResult().getInt("area"),
                    new City(this.getResult().getInt("city_id"), this.getResult().getString("name"))
            );
            venues.add(buffer);
        }
        if (venues.size() > 0) {

            return venues;
        } else {
            throw new Exception("Failure in receiving venue data from database - no data matches the parameter");
        }

    }

    public ArrayList<Venue> Select_Venue_By_Name(String name) throws Exception {
        ArrayList<Venue> venues = new ArrayList<Venue>();

        String SQLQuery = "SELECT v.id AS id, v.name AS name, v.address AS address, v.maxCapacity AS maxCapacity, v.area AS area, c.id AS city_id, c.name as city_name FROM venue v INNER JOIN city c ON v.city_id = c.id WHERE v.name=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, name);

        System.out.println("SQL QUERY: \n" + prst);
        this.setResult(this.Read(String.valueOf(prst)));

        Venue buffer;
        while (this.getResult().next()) {
            buffer = new Venue(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    this.getResult().getString("address"),
                    this.getResult().getInt("maxCapacity"),
                    this.getResult().getInt("area"),
                    new City(this.getResult().getInt("city_id"), this.getResult().getString("name"))
            );
            venues.add(buffer);
        }
        if (venues.size() > 0) {

            return venues;
        } else {
            throw new Exception("Failure in receiving venue data from database - no data matches the parameter");
        }

    }

    public void Insert_Venue(Venue _venue) throws Exception {
        String SQLQuery = "INSERT INTO venues ('name','address','maxCapacity','area','city_id') VALUES(?,?,?,?,?)";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, _venue.getName());
        prst.setString(2, _venue.getAddress());
        prst.setString(3, String.valueOf(_venue.getMaxCapacity()));
        prst.setString(4, String.valueOf(_venue.getArea()));
        prst.setString(5, String.valueOf(_venue.getCity().getId()));

        this.Create(String.valueOf(prst));
    }

    public void Update_Venue(Venue _venue) throws Exception {
        String SQLQuery = "UPDATE venues SET name=?, address=?, maxCapacity=?, area=?, city_id=? WHERE id=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, _venue.getName());
        prst.setString(2, _venue.getAddress());
        prst.setString(3, String.valueOf(_venue.getMaxCapacity()));
        prst.setString(4, String.valueOf(_venue.getArea()));
        prst.setString(5, String.valueOf(_venue.getCity().getId()));
        prst.setString(6, String.valueOf(_venue.getId()));

        this.Update(String.valueOf(prst));
    }    
    public void Delete_Venue(int id) throws Exception {
        String SQLQuery = "DELETE FROM venues WHERE id=?";
        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        prst.setString(1, String.valueOf(id));

        this.Delete(String.valueOf(prst));
    }


}
