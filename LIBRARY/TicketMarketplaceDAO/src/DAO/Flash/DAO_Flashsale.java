/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Flash;

import DAO.Connection.DatabaseConnection;
import Entities.EventClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class DAO_Flashsale {

    public static ArrayList<EventClass> Select_FlashSale_By_Event_Id(int event_id) throws Exception {
        ArrayList<EventClass> events = new ArrayList<>();

        String SQLQuery = "SELECT "
                + "ecl.* "
                + "FROM "
                + "`eventClasses` AS ecl "
                + "INNER JOIN"
                + "`events` AS eve "
                + "ON "
                + "ecl.`event_id` = eve.`id` "
                + "INNER JOIN "
                + "`flashsales` AS fls "
                + "ON "
                + "ecl.`id` = fls.`id`"
                + "WHERE "
                + "eve.`id` = ?;";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, String.valueOf(event_id));

        ResultSet rslt = prst.executeQuery();

        EventClass buffer;
        while (rslt.next()) {
            buffer = new EventClass(
                    rslt.getInt("id"),
                    rslt.getString("name"),
                    rslt.getDouble("price"),
                    rslt.getString("description"),
                    rslt.getInt("stock"),
                    rslt.getInt("availableStock")
            );

            events.add(buffer);
        }

        prst.close();

        return events;
    }

    public static double Select_FlashSale_Price_By_EventClass_Id(int eventclass_id) throws Exception {

        String SQLQuery = "SELECT fs.price AS 'flashsale_price'\n"
                + "FROM\n"
                + "	`flashsales` AS fs\n"
                + "INNER JOIN \n"
                + "	`eventclasses` AS ec\n"
                + "ON \n"
                + "ec.`id` = fs.`eventclass_id`\n"
                + "WHERE \n"
                + "	STR_TO_DATE(fs.`startTime`,'%Y-%m-%d %H:%i:%s') < NOW()\n"
                + "AND\n"
                + "	STR_TO_DATE(fs.`endTime`,'%Y-%m-%d %H:%i:%s') > NOW()\n"
                + "AND\n"
                + "	fs.`stock` > 0\n"
                + ";";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        prst.setString(1, String.valueOf(eventclass_id));

        ResultSet rslt = prst.executeQuery();

        double res = 0.0;
        while (rslt.next()) {
            res = rslt.getDouble("flashsale_price");
        }

        prst.close();

        return res;
    }

    public static int Insert_Flashsale(int eventclass_id, double price, String starttime, String endtime, int stock) throws Exception {

        String SQLQuery = "INSERT INTO flashsales (eventClass_id, price, startTime, endTime, initialStock, stock)\n"
                + "VALUES ('?', ?, '?', '?', ?, ?);";
        PreparedStatement prst = (DatabaseConnection.getConnection().prepareStatement(SQLQuery));

        prst.setString(1, String.valueOf(eventclass_id));
        prst.setString(2, String.valueOf(price));
        prst.setString(3, starttime);
        prst.setString(4, endtime);
        prst.setString(5, String.valueOf(stock));
        prst.setString(6, String.valueOf(stock));

        int num = prst.executeUpdate();
        prst.clearBatch();
        prst.close();

        return num;
    }

}
