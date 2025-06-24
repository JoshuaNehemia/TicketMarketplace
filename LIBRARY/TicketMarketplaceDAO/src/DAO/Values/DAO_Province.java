package DAO.Values;

import DAO.Connection.DatabaseConnection;
import Entities.Values.Province;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joshu
 */
public class DAO_Province {
    
    public static ArrayList<Province> Select_Province() throws Exception {
        ArrayList<Province> provinces = new ArrayList<Province>();

        String SQLQuery = "SELECT* FROM provinces";

        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        ResultSet rslt = prst.executeQuery();

        Province buffer;
        while (rslt.next()) {
            buffer = new Province(
                    rslt.getInt("id"),
                    rslt.getString("name")
            );
            provinces.add(buffer);
        }

        prst.clearBatch();
        prst.close();
        rslt.close();

        return provinces;
    }
}
