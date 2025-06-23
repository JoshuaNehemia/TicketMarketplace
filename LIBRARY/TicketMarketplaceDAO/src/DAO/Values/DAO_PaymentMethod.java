package DAO.Values;

import DAO.Connection.DatabaseConnection;
import Entities.Values.PaymentMethod;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author joshu
 */
public class DAO_PaymentMethod extends DatabaseConnection{
    
    public DAO_PaymentMethod() throws Exception
    {
        super();
        System.out.println(("DAO_PaymentMethod IS CONNECTED"));
    }
    
    public ArrayList<PaymentMethod> Select_PaymentMethods() throws Exception
    {
        ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();

        String SQLQuery = "SELECT* FROM paymentmethods";
        
        this.setPreparedStatement(DatabaseConnection.getConnection().prepareStatement(SQLQuery));
        
        PaymentMethod buffer;
        while (this.getResult().next()) {
            buffer = new PaymentMethod(
                    this.getResult().getInt("id"),
                    this.getResult().getString("name")
            );
            paymentMethods.add(buffer);
        }
        return paymentMethods;
    }
}
