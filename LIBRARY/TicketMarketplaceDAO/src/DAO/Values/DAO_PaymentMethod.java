package DAO.Values;

import DAO.Connection.DatabaseConnection;
import Entities.Values.PaymentMethod;
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
public class DAO_PaymentMethod {
    
    public static PaymentMethod Select_PaymentMethod_By_Id(int id) throws Exception {
    PaymentMethod paymentMethod = null;

    String SQLQuery = "SELECT * FROM paymentmethods WHERE id = ?";

    PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
    prst.setInt(1, id);
    ResultSet rslt = prst.executeQuery();

    if (rslt.next()) {
        paymentMethod = new PaymentMethod(
            rslt.getInt("id"),
            rslt.getString("name")
        );
    }

    rslt.close();
    prst.close();

    return paymentMethod;
}

    public static ArrayList<PaymentMethod> Select_PaymentMethods() throws Exception {
        ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();
        PaymentMethod buffer;

        String SQLQuery = "SELECT* FROM paymentmethods";

        PreparedStatement prst = DatabaseConnection.getConnection().prepareStatement(SQLQuery);
        ResultSet rslt = prst.executeQuery();
        while (rslt.next()) {
            buffer = new PaymentMethod(
                    rslt.getInt("id"),
                    rslt.getString("name")
            );
            paymentMethods.add(buffer);
        }
       
        prst.close();
        return paymentMethods;
    }
}
