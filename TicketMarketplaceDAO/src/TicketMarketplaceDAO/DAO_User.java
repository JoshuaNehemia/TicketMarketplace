package TicketMarketplaceDAO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import TicketMarketplaceEntities.User;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class DAO_User {

    public static User Select_User(String username, String password, ArrayList<User> list) {
        User selectedUser = new User();

        for (User us : list) {
            if (us.getUsername().equals(username) && us.getPassword().equals(password)) {
                selectedUser = us;
            }
        }

        return selectedUser;
    }

    public static ArrayList<User> Insert_User(User user, ArrayList<User> list) {
        list.add(user);
        
        return list;
    }

    public static ArrayList<User> Update_User(User user, ArrayList<User> list) {
        User selectedUser = Select_User(user.getUsername(), user.getPassword(), list);
        
        list.remove(selectedUser);
        list.add(user);
        
        return list;
    }

    public static ArrayList<User> Delete_User(String username, String password, ArrayList<User> list) {
        for (User us : list) {
            if (us.getUsername().equals(username) && us.getPassword().equals(password)) {
                list.remove(us);
            }
        }
        return list;
    }
}
