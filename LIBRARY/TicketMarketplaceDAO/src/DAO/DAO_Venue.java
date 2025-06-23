package DAO;

import DAO.Connection.DatabaseConnection;
import Entities.Venue;
import Entities.Values.City;
import Entities.Values.Province;
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
public class DAO_Venue extends DatabaseConnection {

    public DAO_Venue() throws Exception {
        super();
        System.out.println("DAO_VENUE IS CONNECTED");
    }

    public ArrayList<Venue> Select_Venue_By_City(City _city) throws Exception {
        ArrayList<Venue> venues = new ArrayList<Venue>();

        String SQLQuery = "SELECT * FROM `venues` WHERE city_id = (SELECT id FROM `cities` WHERE `name` =? LIMIT 1);";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, _city.getName());

        Venue buffer;
        while (this.getResult().next()) {
            buffer = new Venue(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    _city,
                    this.getResult().getString("address"),
                    this.getResult().getInt("maxCapacity"),
                    this.getResult().getInt("area")
            );
            venues.add(buffer);
        }
        return venues;
    }

    public ArrayList<Venue> Select_Venue_By_Province(Province province) throws Exception {
        ArrayList<Venue> venues = new ArrayList<Venue>();

        String SQLQuery = "SELECT v.*, c.id AS 'city_id', c.`name` AS 'city_name' FROM `venues` AS v JOIN `cities` AS c ON v.city_id = c.id JOIN `provinces` AS p ON c.province_id = p.id WHERE p.name LIKE ?;";

        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, province.getName());

        Venue buffer;
        while (this.getResult().next()) {
            buffer = new Venue(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    new City(this.getResult().getInt("city_id"), this.getResult().getString("city_name")),
                    this.getResult().getString("address"),
                    this.getResult().getInt("maxCapacity"),
                    this.getResult().getInt("area")
            );
            venues.add(buffer);
        }
        return venues;
    }

    public ArrayList<Venue> Select_Venue_By_Name(String name) throws Exception {
        ArrayList<Venue> venues = new ArrayList<Venue>();

        String SQLQuery = "SELECT v*,c.id as 'city_id',c.name as 'city_name' FROM `venues` v INNER JOIN `cities` c ON v.city_id = c.id WHERE v.`name` LIKE ?;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, "%" + name + "%");

        Venue buffer;
        while (this.getResult().next()) {
            buffer = new Venue(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    new City(this.getResult().getInt("city_id"), this.getResult().getString("city_name")),
                    this.getResult().getString("address"),
                    this.getResult().getInt("maxCapacity"),
                    this.getResult().getInt("area")
            );
            venues.add(buffer);
        }
        return venues;
    }

        public ArrayList<Venue> Select_Venue_By_Id(int id) throws Exception {
        ArrayList<Venue> venues = new ArrayList<Venue>();

        String SQLQuery = "SELECT v*,c.id as 'city_id',c.name as 'city_name' FROM `venues` v INNER JOIN `cities` c ON v.city_id = c.id WHERE v.`id` = ? LIMIT 1;";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setInt(1,id);

        Venue buffer;
        while (this.getResult().next()) {
            buffer = new Venue(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name"),
                    new City(this.getResult().getInt("city_id"), this.getResult().getString("city_name")),
                    this.getResult().getString("address"),
                    this.getResult().getInt("maxCapacity"),
                    this.getResult().getInt("area")
            );
            venues.add(buffer);
        }
        return venues;
    }
    public void Insert_Venue(Venue _venue) throws Exception {
        String SQLQuery = "INSERT INTO `venues` (`name`, `city_id`, `address`, `maxCapacity`, `area`) VALUES (?,?,?,?,?,?)";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, _venue.getName());
        this.getPreparedStatement().setString(2, _venue.getAddress());
        this.getPreparedStatement().setString(3, String.valueOf(_venue.getCity().getId()));
        this.getPreparedStatement().setString(4, String.valueOf(_venue.getMaxCapacity()));
        this.getPreparedStatement().setString(5, String.valueOf(_venue.getArea()));
        this.getPreparedStatement().setString(6, String.valueOf(_venue.getCity().getId()));

        this.Create();
    }

    public void Update_Venue(Venue _venue) throws Exception {
        String SQLQuery = "UPDATE venues SET name=?, address=?, maxCapacity=?, area=?, city_id=? WHERE id=?";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, _venue.getName());
        this.getPreparedStatement().setString(2, _venue.getAddress());
        this.getPreparedStatement().setString(3, String.valueOf(_venue.getMaxCapacity()));
        this.getPreparedStatement().setString(4, String.valueOf(_venue.getArea()));
        this.getPreparedStatement().setString(5, String.valueOf(_venue.getCity().getId()));
        this.getPreparedStatement().setString(6, String.valueOf(_venue.getId()));

        this.Update();
    }

    public void Delete_Venue(int id) throws Exception {
        String SQLQuery = "DELETE FROM venues WHERE id=?";
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        this.getPreparedStatement().setString(1, String.valueOf(id));

        this.Delete();
    }

}
