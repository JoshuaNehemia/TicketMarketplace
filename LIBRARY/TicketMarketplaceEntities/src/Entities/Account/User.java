package Entities.Account;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.LocalDate;

/**
 *
 * @author Franly
 */
public class User extends Account{

    
    //FIELDS
    private String birthdate;

    
    //CONSTRUCTOR
    public User(String username, String password, String fullName, String email,String phoneNumber, String birthdate) throws Exception{
        super(username,password,fullName,email,phoneNumber);
        this.birthdate = birthdate;
    }

    public User() {
        super();
        this.birthdate = null;
    }

    //GETTER AND SETTER
    public String getBirthdate() {
        return birthdate;
    }

    public String getBirthdateString() {
        return this.getBirthdate().toString();
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    //FUNCTION
    public String[] GetUserData() {
        String[] userData = new String[5];
        userData[0] = this.getUsername();
        userData[1] = this.getPassword();
        userData[2] = this.getFullName();
        userData[3] = this.getEmail();
        userData[4] = this.getBirthdateString();

        return userData;
    }


}
