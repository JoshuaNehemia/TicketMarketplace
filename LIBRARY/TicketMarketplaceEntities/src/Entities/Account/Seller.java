package Entities.Account;

import Entities.Account.Account;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Franly
 */
public class Seller extends Account {

    //FIELDS
    private String companyAddress;

    //CONSTRUCTOR
    public Seller(String username, String password, String companyName, String phoneNumber, String email, String companyAddress) throws Exception {
        super(username, password, companyName, email, phoneNumber);
        this.setCompanyAddress(companyAddress);
    }

    public Seller() {
        super();
        this.companyAddress = "";
    }

    public String getCompanyName() {
        return this.getFullName();
    }

    public void setCompanyName(String companyName) throws Exception {
        this.setFullName(companyName);
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) throws Exception{
        if (companyAddress.equals("") || companyAddress == null) {
            throw new Exception("Seller company address can't be empty");
        } else {
            this.companyAddress = companyAddress;
        }
    }

    //Function
    public String[] GetSellerData() {
        String[] data = new String[6];

        data[0] = this.getUsername();
        data[1] = this.getPassword();
        data[2] = this.getCompanyName();
        data[3] = this.getEmail();
        data[4] = this.getPhoneNumber();
        data[5] = this.getCompanyAddress();

        return data;
    }

}
