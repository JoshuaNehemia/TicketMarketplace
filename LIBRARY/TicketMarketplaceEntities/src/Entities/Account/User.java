package Entities.Account;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Entities.Account.Account;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Franly
 */
public class User extends Account{

    
    //FIELDS
    private LocalDate birthdate;

    
    //CONSTRUCTOR
    public User(String username, String password, String fullName, String email,String phoneNumber, LocalDate birthdate) throws Exception{
        super(username,password,fullName,email,phoneNumber);
        this.birthdate = birthdate;
    }

    public User() {
        super();
        this.birthdate = null;
    }

    //GETTER AND SETTER
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getBirthdateString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.getBirthdate().format(df);
    }

    public void setBirthdate(LocalDate birthdate) {
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
