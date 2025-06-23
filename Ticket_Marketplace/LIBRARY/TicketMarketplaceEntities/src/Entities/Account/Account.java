/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.Account;

import java.util.regex.Pattern;

/**
 *
 * @author joshu
 */
public class Account {

    //FIELDS
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;

    //CONSTRUCTORS
    public Account(String username, String password, String fullname, String email, String phoneNumber) throws Exception{
        this.setUsername(username);
        this.setPassword(password);
        this.setFullName(fullname);
        this.setEmail(email);
    }
    
    ////FOR DISPLAY
    public Account(String username, String fullname, String email, String phoneNumber) throws Exception{
        this.setUsername(username);
        this.setFullName(fullname);
        this.setEmail(email);
    }
    
    
    public Account()
    {
        this.username="";
        this.password="";
        this.fullName="";
        this.email="";
        this.phoneNumber="";
    }

    //GETTER AND SETTER
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception {
        if(this.isText(username)){
            throw new Exception("Account's username can't be empty and contain illegal expressions");
        }
        else
        {
            this.username = username;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if(this.isText(password)){
            throw new Exception("Account's password can't be empty and contain illegal expressions");
        }
        else
        {
            this.password = password;
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullname) throws Exception {
        if(fullName.equals("") || fullName == null)
        {
            throw new Exception("Account's full name can't be empty.");
        }
        else {
            this.fullName = fullname;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        if (this.isEmail(email)) {
            this.email = email;
        }
        else throw new Exception("Account's email is not in email forms. Please input it in user@domain.tld.");
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) throws Exception{
        if(this.isPhoneNumber(phoneNumber))
        {
            this.phoneNumber = phoneNumber;
        }
        else throw new Exception("Account's phone number is not in phone number forms.");
    }

    //FUNCTION
    private boolean isEmail(String email) {
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        if (email == null) {
            return false;
        } else {
            return emailPattern.matcher(email).matches();
        }
    }
    private boolean isText(String input) {
        String TEXT_REGEX = "^[a-zA-Z0-9@$!%*?&]+$";
        Pattern textPattern = Pattern.compile(TEXT_REGEX);
        if (input == null || input.equals("")) {
            return false;
        } else {
            return textPattern.matcher(input).matches();
        }
    }
    
    private boolean isPhoneNumber(String input){
        String NUM_REGEX = "\\d+";
        Pattern textPattern = Pattern.compile(NUM_REGEX);
        if (input == null || input.equals("")) {
            return false;
        } else {
            return textPattern.matcher(input).matches();
        }
    }
}
