/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ticket_marketplace;
import java.time.LocalDate; 

/**
 *
 * @author Franly
 */
public class User {
    private String username;
    private String fullname;
    private String email;
    private LocalDate birthdate;


    public User(String username, String fullname, String email, LocalDate birthdate) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.birthdate = birthdate;
    }
    
    public User() {
        this.username = "";
        this.fullname = "";
        this.email = "";
        this.birthdate = null; 
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
